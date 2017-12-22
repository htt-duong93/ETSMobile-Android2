package ca.etsmtl.applets.etsmobile.model.Moodle;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import javax.inject.Singleton;

import ca.etsmtl.applets.etsmobile.ApplicationManager;
import ca.etsmtl.applets.etsmobile.http.MoodleWebService;
import ca.etsmtl.applets.etsmobile.model.RemoteResource;
import ca.etsmtl.applets.etsmobile.model.UserCredentials;
import ca.etsmtl.applets.etsmobile.util.SecurePreferences;
import ca.etsmtl.applets.etsmobile2.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Sonphil on 31-08-17.
 */
@Singleton
public class MoodleRepository {

    private static String TAG = "MoodleRepository";

    private Context context;
    private MoodleWebService moodleWebService;
    private MutableLiveData<RemoteResource<List<MoodleAssignmentCourse>>> assignmentCourses = new MutableLiveData<>();
    private MutableLiveData<RemoteResource<MoodleProfile>> profile = new MutableLiveData<>();
    private MutableLiveData<RemoteResource<MoodleCourses>> courses = new MutableLiveData<>();

    public MoodleRepository(@NonNull Application application, MoodleWebService moodleWebService) {
        this.context = application;
        this.moodleWebService = moodleWebService;
    }

    private LiveData<RemoteResource<MoodleToken>> getToken() {

        String tokenStr = ApplicationManager.userCredentials.getMoodleToken();
        MutableLiveData<RemoteResource<MoodleToken>> token = new MutableLiveData<>();

        if (tokenStr != null && !tokenStr.isEmpty()) {
            token.setValue(RemoteResource.success(new MoodleToken(tokenStr)));
        } else {
            token.setValue(RemoteResource.loading(null));

            moodleWebService.getToken(ApplicationManager.userCredentials.getUsername(), ApplicationManager.userCredentials.getPassword()).enqueue(new Callback<MoodleToken>() {
                @Override
                public void onResponse(@NonNull Call<MoodleToken> call, @NonNull Response<MoodleToken> response) {
                    if (response.isSuccessful()) {
                        MoodleToken moodleToken = response.body();

                        SecurePreferences securePreferences = new SecurePreferences(context);
                        securePreferences.edit().putString(UserCredentials.MOODLE_TOKEN, moodleToken.getToken()).apply();

                        ApplicationManager.userCredentials.setMoodleToken(moodleToken.getToken());

                        if (moodleToken.getToken().equals("")) {
                            token.setValue(RemoteResource.error(context.getString(R.string.moodle_error_cant_get_token) + "\n" + response.message(), null));
                        }

                        token.setValue(RemoteResource.success(moodleToken));
                    } else {
                        token.setValue(RemoteResource.error(context.getString(R.string.moodle_error_cant_get_token) + "\n" + response.message(), null));
                    }
                }

                @Override
                public void onFailure(Call<MoodleToken> call, Throwable t) {
                    token.setValue(RemoteResource.error(context.getString(R.string.moodle_error_cant_get_token), null));
                }
            });
        }
        return token;
    }

    private LiveData<RemoteResource<List<MoodleAssignmentCourse>>> getAssignmentCourses(final int[] coursesIds) {
        LiveData<RemoteResource<MoodleToken>> token = getToken();

        token.observeForever(new Observer<RemoteResource<MoodleToken>>() {
            @Override
            public void onChanged(@Nullable RemoteResource<MoodleToken> remoteToken) {

                if (remoteToken == null || remoteToken.status == RemoteResource.ERROR) {
                    assignmentCourses.setValue(RemoteResource.error(context.getString(R.string.moodle_error_cant_get_token), null));
                } else if (remoteToken.data != null && remoteToken.status != RemoteResource.LOADING) {
                    assignmentCourses.setValue(RemoteResource.loading(null));

                    moodleWebService.getAssignmentCourses(remoteToken.data.getToken(), coursesIds).enqueue(new Callback<MoodleAssignmentCourses>() {
                        @Override
                        public void onResponse(@NonNull Call<MoodleAssignmentCourses> call, @NonNull Response<MoodleAssignmentCourses> response) {
                            MoodleAssignmentCourses moodleAssignmentCourses = response.body();

                            if (response.isSuccessful() && moodleAssignmentCourses != null) {
                                List<MoodleAssignmentCourse> courses = moodleAssignmentCourses.getCourses();
                                assignmentCourses.setValue(RemoteResource.success(courses));
                            } else {
                                assignmentCourses.setValue(RemoteResource.error(response.message(), null));
                            }
                        }

                        @Override
                        public void onFailure(Call<MoodleAssignmentCourses> call, Throwable t) {
                            assignmentCourses.setValue(RemoteResource.error(context.getString(R.string.moodle_error_connection_failure), null));
                        }
                    });

                    token.removeObserver(this);
                }
            }
        });

        return assignmentCourses;
    }

    public LiveData<RemoteResource<List<MoodleAssignmentCourse>>> getAssignmentCourses() {

        if (assignmentCourses.getValue() == null || assignmentCourses.getValue().status != RemoteResource.SUCCESS) {
            assignmentCourses.setValue(RemoteResource.loading(null));

            RemoteResource<MoodleCourses> moodleCourses = courses.getValue();

            if (moodleCourses != null && moodleCourses.data != null && moodleCourses.status != RemoteResource.LOADING) {
                int[] coursesIds = new int[moodleCourses.data.size()];
                for (int i = 0; i < moodleCourses.data.size(); i++) {
                    coursesIds[i] = moodleCourses.data.get(i).getId();
                }

                return getAssignmentCourses(coursesIds);
            } else {
                getCourses().observeForever(new Observer<RemoteResource<MoodleCourses>>() {
                    @Override
                    public void onChanged(@Nullable RemoteResource<MoodleCourses> moodleCoursesRemoteResource) {
                        if (moodleCoursesRemoteResource == null || moodleCoursesRemoteResource.status == RemoteResource.ERROR) {
                            String errorMessage = moodleCoursesRemoteResource == null ? context.getString(R.string.moodle_error_cant_get_courses) : moodleCoursesRemoteResource.message;
                            assignmentCourses.setValue(RemoteResource.error(errorMessage, null));
                            courses.removeObserver(this);
                        } else if (moodleCoursesRemoteResource.status == RemoteResource.SUCCESS) {
                            getAssignmentCourses();
                            courses.removeObserver(this);
                        }
                    }
                });
            }
        }

        return assignmentCourses;
    }

    public LiveData<RemoteResource<MoodleProfile>> getProfile() {
        if (profile.getValue() == null || profile.getValue().status != RemoteResource.SUCCESS) {
            profile.setValue(RemoteResource.loading(null));

            LiveData<RemoteResource<MoodleToken>> token = getToken();

            token.observeForever(new Observer<RemoteResource<MoodleToken>>() {
                @Override
                public void onChanged(@Nullable RemoteResource<MoodleToken> remoteToken) {
                    if (remoteToken == null || remoteToken.status == RemoteResource.ERROR) {
                        profile.setValue(RemoteResource.error(context.getString(R.string.moodle_error_cant_get_token), null));
                    } else if (remoteToken.data != null && remoteToken.status != RemoteResource.LOADING) {
                        moodleWebService.getProfile(remoteToken.data.getToken()).enqueue(new Callback<MoodleProfile>() {
                            @Override
                            public void onResponse(@NonNull Call<MoodleProfile> call, @NonNull Response<MoodleProfile> response) {
                                MoodleProfile moodleProfile = response.body();

                                if (response.isSuccessful() && moodleProfile != null)
                                    profile.setValue(RemoteResource.success(moodleProfile));
                                else
                                    profile.setValue(RemoteResource.error(context.getString(R.string.moodle_error_cant_get_profile) + "\n" + response.message(), moodleProfile));
                            }

                            @Override
                            public void onFailure(Call<MoodleProfile> call, Throwable t) {
                                profile.setValue(RemoteResource.error(context.getString(R.string.moodle_error_connection_failure), null));
                            }
                        });

                        token.removeObserver(this);
                    }
                }
            });
        }

        return profile;
    }

    public LiveData<RemoteResource<MoodleCourses>> getCourses() {
        if (courses.getValue() == null || courses.getValue().status != RemoteResource.SUCCESS) {
            courses.setValue(RemoteResource.loading(null));

            final RemoteResource<MoodleProfile> remoteProfile = profile.getValue();

            if (remoteProfile != null && remoteProfile.data != null && remoteProfile.status != RemoteResource.LOADING) {
                moodleWebService.getCourses(ApplicationManager.userCredentials.getMoodleToken(), remoteProfile.data.getUserId()).enqueue(new Callback<MoodleCourses>() {
                    @Override
                    public void onResponse(@NonNull Call<MoodleCourses> call, @NonNull Response<MoodleCourses> response) {
                        MoodleCourses moodleCourses = response.body();

                        if (response.isSuccessful() && moodleCourses != null)
                            courses.setValue(RemoteResource.success(moodleCourses));
                        else
                            courses.setValue(RemoteResource.error(context.getString(R.string.moodle_error_cant_get_courses) + "\n" + response.message(), moodleCourses));
                    }

                    @Override
                    public void onFailure(Call<MoodleCourses> call, Throwable t) {
                        courses.setValue(RemoteResource.error(context.getString(R.string.moodle_error_connection_failure), null));
                    }
                });
            } else {
                getProfile().observeForever(new Observer<RemoteResource<MoodleProfile>>() {
                    @Override
                    public void onChanged(@Nullable RemoteResource<MoodleProfile> moodleProfileRemoteResource) {
                        if (moodleProfileRemoteResource == null || moodleProfileRemoteResource.status == RemoteResource.ERROR) {
                            String errorMessage = moodleProfileRemoteResource == null ? context.getString(R.string.moodle_error_cant_get_profile) : moodleProfileRemoteResource.message;
                            courses.setValue(RemoteResource.error(errorMessage, null));
                            profile.removeObserver(this);
                        } else if (moodleProfileRemoteResource.status == RemoteResource.SUCCESS) {
                            getCourses();
                            profile.removeObserver(this);
                        }
                    }
                });
            }
        }

        return courses;
    }

    public LiveData<RemoteResource<MoodleAssignmentSubmission>> getAssignmentSubmission(final int assignId) {
        final MutableLiveData<RemoteResource<MoodleAssignmentSubmission>> submission = new MutableLiveData<>();
        submission.setValue(RemoteResource.loading(null));

        LiveData<RemoteResource<MoodleToken>> token = getToken();
        token.observeForever(new Observer<RemoteResource<MoodleToken>>() {
            @Override
            public void onChanged(@Nullable RemoteResource<MoodleToken> remoteToken) {
                if (remoteToken == null || remoteToken.status == RemoteResource.ERROR) {
                    submission.setValue(RemoteResource.error(context.getString(R.string.moodle_error_cant_get_token), null));
                } else if (remoteToken.data != null && remoteToken.status != RemoteResource.LOADING) {
                    moodleWebService.getAssignmentSubmission(remoteToken.data.getToken(), assignId).enqueue(new Callback<MoodleAssignmentSubmission>() {
                        @Override
                        public void onResponse(@NonNull Call<MoodleAssignmentSubmission> call, @NonNull Response<MoodleAssignmentSubmission> response) {
                            MoodleAssignmentSubmission assignmentSubmission = response.body();

                            if (response.isSuccessful() && assignmentSubmission != null) {
                                submission.setValue(RemoteResource.success(assignmentSubmission));
                            } else {
                                submission.setValue(RemoteResource.error(response.message(), assignmentSubmission));
                            }
                        }

                        @Override
                        public void onFailure(Call<MoodleAssignmentSubmission> call, Throwable t) {
                            submission.setValue(RemoteResource.error(context.getString(R.string.moodle_error_connection_failure), null));
                        }
                    });
                }

                token.removeObserver(this);
            }
        });

        return submission;
    }
}
