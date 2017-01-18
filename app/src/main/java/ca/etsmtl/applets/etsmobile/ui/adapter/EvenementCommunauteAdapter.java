package ca.etsmtl.applets.etsmobile.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import at.markushi.ui.CircleButton;
import ca.etsmtl.applets.etsmobile.model.applets_events.EvenementCommunaute;
import ca.etsmtl.applets.etsmobile.util.EventsComparator;
import ca.etsmtl.applets.etsmobile.views.AnimatedExpandableListView;
import ca.etsmtl.applets.etsmobile2.R;


/**
 * Created by gnut3ll4 on 12/12/14.
 */
public class EvenementCommunauteAdapter extends AnimatedExpandableListView.AnimatedExpandableListAdapter {

    private final AnimatedExpandableListView expandableListView;
    private Context context;
    private ArrayList<EvenementCommunaute> listEvenements;
    private int lastExpandedGroupPosition = -1;


    public EvenementCommunauteAdapter(Context context, ArrayList<EvenementCommunaute> listEvenements, AnimatedExpandableListView expandableListView) {
        this.listEvenements = listEvenements;
        this.context = context;
        this.expandableListView = expandableListView;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
//        if (groupPosition != lastExpandedGroupPosition) {
//            expandableListView.collapseGroupWithAnimation(lastExpandedGroupPosition);
//        }
//
        super.onGroupExpanded(groupPosition);
//        lastExpandedGroupPosition = groupPosition;
    }

    public Object getChild(int listPosition, int expandedListPosition) {
        return this.listEvenements.get(listPosition);
    }

    public void addEvents(ArrayList<EvenementCommunaute> events) {
        this.listEvenements.addAll(events);
        Collections.sort(listEvenements, new EventsComparator());
        notifyDataSetChanged();
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getRealChildView(final int listPosition, final int expandedListPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final EvenementCommunaute item = (EvenementCommunaute) getChild(listPosition, expandedListPosition);

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.row_applets_events, null);
        }


        TextView textViewDescription = (TextView) convertView.findViewById(R.id.tv_description);
        ImageView imageViewLogoOrganisateur = (ImageView) convertView.findViewById(R.id.iv_logo_organisateur);
        TextView textViewNomOrganisateur = (TextView) convertView.findViewById(R.id.tv_nom_organisateur);
        CircleButton buttonLocaliser = (CircleButton) convertView.findViewById(R.id.btn_localiser);
        CircleButton buttonCalendrier = (CircleButton) convertView.findViewById(R.id.btn_calendrier);
        CircleButton buttonDetails = (CircleButton) convertView.findViewById(R.id.btn_details);
        CircleButton buttonPartager = (CircleButton) convertView.findViewById(R.id.btn_partager);


        textViewDescription.setText(item.getDescription());
        textViewNomOrganisateur.setText(item.getSourceEvenement().getName());

        String urlImageOrganisateur = item.getSourceEvenement().getUrlImage();

        if (!urlImageOrganisateur.isEmpty()) {
            Picasso.with(convertView.getContext())
                    .load(urlImageOrganisateur)
//                    .placeholder(R.drawable.)
                    .into(imageViewLogoOrganisateur);
        }

        Button.OnClickListener buttonClicked = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, ((Button) view).getText() + " clicked", Toast.LENGTH_SHORT).show();
            }
        };


//        buttonLocaliser.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//        buttonCalendrier.setOnClickListener(buttonClicked);
//        buttonDetails.setOnClickListener(buttonClicked);
//        buttonPartager.setOnClickListener(buttonClicked);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(view instanceof Button)) {
                    expandableListView.collapseGroupWithAnimation(listPosition);
                }
            }
        });

        return convertView;
    }

    @Override
    public int getRealChildrenCount(int listPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.listEvenements.get(listPosition);
    }


    @Override
    public int getGroupCount() {
        return this.listEvenements.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        final ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {

            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInflater.inflate(R.layout.row_header_applets_events, parent, false);
            holder = new ViewHolder();

            holder.imageViewEvenement = (ImageView) convertView.findViewById(R.id.iv_evenement);
            holder.textViewNomEvenement = (TextView) convertView.findViewById(R.id.tv_nom_evenement);
            holder.textViewMois = (TextView) convertView.findViewById(R.id.tv_mois);
            holder.textViewJour = (TextView) convertView.findViewById(R.id.tv_jour);

            convertView.setTag(holder);
        }

        final EvenementCommunaute item = (EvenementCommunaute) getGroup(listPosition);

        String urlImageEvenement = item.getImage();
        final String urlImageOrganisateur = item.getSourceEvenement().getUrlImage();

        if (!urlImageEvenement.isEmpty()) {
            Picasso.with(context)
                    .load(urlImageEvenement)
                    .placeholder(R.drawable.event_header)
                    .into(holder.imageViewEvenement);
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTime date = dateTimeFormatter.parseDateTime(item.getDebut());

        String monthString = date.toString("MMMM");
        monthString = monthString.substring(0, 1).toUpperCase() + monthString.substring(1, 3);


        holder.textViewMois.setText(monthString);
        holder.textViewJour.setText(date.toString("dd", Locale.CANADA_FRENCH));
        holder.textViewNomEvenement.setText(item.getNom());


        return convertView;
    }


    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }

    static class ViewHolder {
        ImageView imageViewEvenement;
        TextView textViewNomEvenement;
        TextView textViewMois;
        TextView textViewJour;
    }
}
