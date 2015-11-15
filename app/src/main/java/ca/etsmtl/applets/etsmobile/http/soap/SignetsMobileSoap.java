package ca.etsmtl.applets.etsmobile.http.soap;

//----------------------------------------------------
//
// Generated by www.easywsdl.com
// Version: 2.0.0.4
//
// Created by Quasar Development at 15-01-2014
//
//---------------------------------------------------

import android.os.AsyncTask;

import org.ksoap2.HeaderProperty;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.AttributeContainer;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.transport.HttpTransportSE;

import java.util.List;

import ca.etsmtl.applets.etsmobile.model.Etudiant;
import ca.etsmtl.applets.etsmobile.model.ListeDeCours;
import ca.etsmtl.applets.etsmobile.model.ListeDeSessions;
import ca.etsmtl.applets.etsmobile.model.ListeDesElementsEvaluation;
import ca.etsmtl.applets.etsmobile.model.OperationResult;
import ca.etsmtl.applets.etsmobile.model.listeCoursHoraire;
import ca.etsmtl.applets.etsmobile.model.listeDesActivitesEtProf;
import ca.etsmtl.applets.etsmobile.model.listeDesCoequipiers;
import ca.etsmtl.applets.etsmobile.model.listeDesProgrammes;
import ca.etsmtl.applets.etsmobile.model.listeHoraireExamensFinaux;
import ca.etsmtl.applets.etsmobile.model.listeJoursRemplaces;
import ca.etsmtl.applets.etsmobile.model.listeSeances;

public class SignetsMobileSoap {
	public List<HeaderProperty> httpHeaders;
	String url = "https://signets-ens.etsmtl.ca/Secure/WebServices/SignetsMobile.asmx";
	int timeOut = 60000;
	IServiceEvents callback;

	public SignetsMobileSoap() {
	}

	public SignetsMobileSoap(IServiceEvents callback) {
		this.callback = callback;
	}

	public SignetsMobileSoap(IServiceEvents callback, String url) {
		this.callback = callback;
		this.url = url;
	}

	public SignetsMobileSoap(IServiceEvents callback, String url, int timeOut) {
		this.callback = callback;
		this.url = url;
		this.timeOut = timeOut;
	}

	protected org.ksoap2.transport.Transport createTransport() {
		return new HttpTransportSE(url, timeOut);
	}

	protected ExtendedSoapSerializationEnvelope createEnvelope() {
		return new ExtendedSoapSerializationEnvelope();
	}

	protected void sendRequest(String methodName, ExtendedSoapSerializationEnvelope envelope,
							   org.ksoap2.transport.Transport transport) throws Exception {
		transport.call(methodName, envelope, httpHeaders);
	}

	Object getResult(Class destObj, SoapObject source, String resultName, ExtendedSoapSerializationEnvelope __envelope)
			throws Exception {
		if (source.hasProperty(resultName)) {
			Object j = source.getProperty(resultName);
			if (j == null) {
				return null;
			}
			Object instance = __envelope.get((AttributeContainer) j, destObj);
			return instance;
		} else if (source.getName().equals(resultName)) {
			Object instance = __envelope.get(source, destObj);
			return instance;
		}
		return null;
	}

	public String HelloWorld() throws Exception {
		return (String) execute(new IWcfMethod() {
			@Override
			public ExtendedSoapSerializationEnvelope CreateSoapEnvelope() {
				ExtendedSoapSerializationEnvelope __envelope = createEnvelope();
				SoapObject __soapReq = new SoapObject("http://etsmtl.ca/", "HelloWorld");
				__envelope.setOutputSoapObject(__soapReq);

				PropertyInfo __info = null;
				return __envelope;
			}

			@Override
			public Object ProcessResult(ExtendedSoapSerializationEnvelope __envelope, SoapObject __result)
					throws Exception {
				Object obj = __result.getProperty("HelloWorldResult");
				if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
					SoapPrimitive j = (SoapPrimitive) __result.getProperty("HelloWorldResult");
					return j.toString();
				}
				return null;
			}
		}, "http://etsmtl.ca/HelloWorld");
	}

	public void HelloWorldAsync() {
		executeAsync(new Functions.IFunc<String>() {
			public String Func() throws Exception {
				return HelloWorld();
			}
		});
	}

	public String echo(final String chaine) throws Exception {
		return (String) execute(new IWcfMethod() {
			@Override
			public ExtendedSoapSerializationEnvelope CreateSoapEnvelope() {
				ExtendedSoapSerializationEnvelope __envelope = createEnvelope();
				SoapObject __soapReq = new SoapObject("http://etsmtl.ca/", "echo");
				__envelope.setOutputSoapObject(__soapReq);

				PropertyInfo __info = null;
				__info = new PropertyInfo();
				__info.namespace = "http://etsmtl.ca/";
				__info.name = "chaine";
				__info.type = PropertyInfo.STRING_CLASS;
				__info.setValue(chaine);
				__soapReq.addProperty(__info);
				return __envelope;
			}

			@Override
			public Object ProcessResult(ExtendedSoapSerializationEnvelope __envelope, SoapObject __result)
					throws Exception {
				Object obj = __result.getProperty("echoResult");
				if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
					SoapPrimitive j = (SoapPrimitive) __result.getProperty("echoResult");
					return j.toString();
				}
				return null;
			}
		}, "http://etsmtl.ca/echo");
	}

	public void echoAsync(final String chaine) {
		executeAsync(new Functions.IFunc<String>() {
			public String Func() throws Exception {
				return echo(chaine);
			}
		});
	}

	/**
	 * Information de base sur l'��tudiant: nom, pr��nom, code permanent, solde
	 */
	public Etudiant infoEtudiant(final String codeAccesUniversel, final String motPasse) throws Exception {
		return (Etudiant) execute(new IWcfMethod() {
			@Override
			public ExtendedSoapSerializationEnvelope CreateSoapEnvelope() {
				ExtendedSoapSerializationEnvelope __envelope = createEnvelope();
				SoapObject __soapReq = new SoapObject("http://etsmtl.ca/", "infoEtudiant");
				__envelope.setOutputSoapObject(__soapReq);

				PropertyInfo __info = null;
				__info = new PropertyInfo();
				__info.namespace = "http://etsmtl.ca/";
				__info.name = "codeAccesUniversel";
				__info.type = PropertyInfo.STRING_CLASS;
				__info.setValue(codeAccesUniversel);
				__soapReq.addProperty(__info);
				__info = new PropertyInfo();
				__info.namespace = "http://etsmtl.ca/";
				__info.name = "motPasse";
				__info.type = PropertyInfo.STRING_CLASS;
				__info.setValue(motPasse);
				__soapReq.addProperty(__info);
				return __envelope;
			}

			@Override
			public Object ProcessResult(ExtendedSoapSerializationEnvelope __envelope, SoapObject __result)
					throws Exception {
				return (Etudiant) getResult(Etudiant.class, __result, "infoEtudiantResult", __envelope);
			}
		}, "http://etsmtl.ca/infoEtudiant");
	}

	/**
	 * Information de base sur l'��tudiant: nom, pr��nom, code permanent, solde
	 */
	public void infoEtudiantAsync(final String codeAccesUniversel, final String motPasse) {
		executeAsync(new Functions.IFunc<Etudiant>() {
			public Etudiant Func() throws Exception {
				return infoEtudiant(codeAccesUniversel, motPasse);
			}
		});
	}

	/**
	 * Liste de tous les cours de l'��tudiant: sigle, groupe, session,
	 * programme, cote finale, nombre de cr��dits et titre du cours, tri��e par
	 * session et sigle.
	 */
	public ListeDeCours listeCours(final String codeAccesUniversel, final String motPasse) throws Exception {
		return (ListeDeCours) execute(new IWcfMethod() {
			@Override
			public ExtendedSoapSerializationEnvelope CreateSoapEnvelope() {
				ExtendedSoapSerializationEnvelope __envelope = createEnvelope();
				SoapObject __soapReq = new SoapObject("http://etsmtl.ca/", "listeCours");
				__envelope.setOutputSoapObject(__soapReq);

				PropertyInfo __info = null;
				__info = new PropertyInfo();
				__info.namespace = "http://etsmtl.ca/";
				__info.name = "codeAccesUniversel";
				__info.type = PropertyInfo.STRING_CLASS;
				__info.setValue(codeAccesUniversel);
				__soapReq.addProperty(__info);
				__info = new PropertyInfo();
				__info.namespace = "http://etsmtl.ca/";
				__info.name = "motPasse";
				__info.type = PropertyInfo.STRING_CLASS;
				__info.setValue(motPasse);
				__soapReq.addProperty(__info);
				return __envelope;
			}

			@Override
			public Object ProcessResult(ExtendedSoapSerializationEnvelope __envelope, SoapObject __result)
					throws Exception {
				return (ListeDeCours) getResult(ListeDeCours.class, __result, "listeCoursResult", __envelope);
			}
		}, "http://etsmtl.ca/listeCours");
	}

	/**
	 * Liste de tous les cours de l'��tudiant: sigle, groupe, session,
	 * programme, cote finale, nombre de cr��dits et titre du cours, tri��e par
	 * session et sigle.
	 */
	public void listeCoursAsync(final String codeAccesUniversel, final String motPasse) {
		executeAsync(new Functions.IFunc<ListeDeCours>() {
			public ListeDeCours Func() throws Exception {
				return listeCours(codeAccesUniversel, motPasse);
			}
		});
	}

	/**
	 * Retourne 'true' si le code d'acc��s universel et le mot de passe sont
	 * valides dans AD des ��tudiants
	 */
	public Boolean donneesAuthentificationValides(final String codeAccesUniversel, final String motPasse)
			throws Exception {
		return (Boolean) execute(new IWcfMethod() {
			@Override
			public ExtendedSoapSerializationEnvelope CreateSoapEnvelope() {
				ExtendedSoapSerializationEnvelope __envelope = createEnvelope();
				SoapObject __soapReq = new SoapObject("http://etsmtl.ca/", "donneesAuthentificationValides");
				__envelope.setOutputSoapObject(__soapReq);

				PropertyInfo __info = null;
				__info = new PropertyInfo();
				__info.namespace = "http://etsmtl.ca/";
				__info.name = "codeAccesUniversel";
				__info.type = PropertyInfo.STRING_CLASS;
				__info.setValue(codeAccesUniversel);
				__soapReq.addProperty(__info);
				__info = new PropertyInfo();
				__info.namespace = "http://etsmtl.ca/";
				__info.name = "motPasse";
				__info.type = PropertyInfo.STRING_CLASS;
				__info.setValue(motPasse);
				__soapReq.addProperty(__info);
				return __envelope;
			}

			@Override
			public Object ProcessResult(ExtendedSoapSerializationEnvelope __envelope, SoapObject __result)
					throws Exception {
				Object obj = __result.getProperty("donneesAuthentificationValidesResult");
				if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
					SoapPrimitive j = (SoapPrimitive) __result.getProperty("donneesAuthentificationValidesResult");
					return new Boolean(j.toString());
				}
				return null;
			}
		}, "http://etsmtl.ca/donneesAuthentificationValides");
	}

	/**
	 * Retourne 'true' si le code d'acc��s universel et le mot de passe sont
	 * valides dans AD des ��tudiants
	 */
	public void donneesAuthentificationValidesAsync(final String codeAccesUniversel, final String motPasse) {
		executeAsync(new Functions.IFunc<Boolean>() {
			public Boolean Func() throws Exception {
				return donneesAuthentificationValides(codeAccesUniversel, motPasse);
			}
		});
	}

	/**
	 * Liste des cours de l'��tudiant entre deux sessions : sigle, groupe,
	 * session, programme, cote finale, nombre de cr��dits et titre du cours,
	 * tri��e par session et sigle.
	 */
	public ListeDeCours listeCoursIntervalleSessions(final String codeAccesUniversel, final String motPasse,
													 final String SesDebut, final String SesFin) throws Exception {
		return (ListeDeCours) execute(new IWcfMethod() {
			@Override
			public ExtendedSoapSerializationEnvelope CreateSoapEnvelope() {
				ExtendedSoapSerializationEnvelope __envelope = createEnvelope();
				SoapObject __soapReq = new SoapObject("http://etsmtl.ca/", "listeCoursIntervalleSessions");
				__envelope.setOutputSoapObject(__soapReq);

				PropertyInfo __info = null;
				__info = new PropertyInfo();
				__info.namespace = "http://etsmtl.ca/";
				__info.name = "codeAccesUniversel";
				__info.type = PropertyInfo.STRING_CLASS;
				__info.setValue(codeAccesUniversel);
				__soapReq.addProperty(__info);
				__info = new PropertyInfo();
				__info.namespace = "http://etsmtl.ca/";
				__info.name = "motPasse";
				__info.type = PropertyInfo.STRING_CLASS;
				__info.setValue(motPasse);
				__soapReq.addProperty(__info);
				__info = new PropertyInfo();
				__info.namespace = "http://etsmtl.ca/";
				__info.name = "SesDebut";
				__info.type = PropertyInfo.STRING_CLASS;
				__info.setValue(SesDebut);
				__soapReq.addProperty(__info);
				__info = new PropertyInfo();
				__info.namespace = "http://etsmtl.ca/";
				__info.name = "SesFin";
				__info.type = PropertyInfo.STRING_CLASS;
				__info.setValue(SesFin);
				__soapReq.addProperty(__info);
				return __envelope;
			}

			@Override
			public Object ProcessResult(ExtendedSoapSerializationEnvelope __envelope, SoapObject __result)
					throws Exception {
				return (ListeDeCours) getResult(ListeDeCours.class, __result, "listeCoursIntervalleSessionsResult",
						__envelope);
			}
		}, "http://etsmtl.ca/listeCoursIntervalleSessions");
	}

	/**
	 * Liste des cours de l'��tudiant entre deux sessions : sigle, groupe,
	 * session, programme, cote finale, nombre de cr��dits et titre du cours,
	 * tri��e par session et sigle.
	 */
	public void listeCoursIntervalleSessionsAsync(final String codeAccesUniversel, final String motPasse,
												  final String SesDebut, final String SesFin) {
		executeAsync(new Functions.IFunc<ListeDeCours>() {
			public ListeDeCours Func() throws Exception {
				return listeCoursIntervalleSessions(codeAccesUniversel, motPasse, SesDebut, SesFin);
			}
		});
	}

	/**
	 * Liste de toutes les sessions o�� l'��tudiant a ��t�� actif �� l'��TS, en
	 * version courte (A2011) et longue (Automne 2011)
	 */
	public ListeDeSessions listeSessions(final String codeAccesUniversel, final String motPasse)
			throws Exception {
		return (ListeDeSessions) execute(new IWcfMethod() {
			@Override
			public ExtendedSoapSerializationEnvelope CreateSoapEnvelope() {
				ExtendedSoapSerializationEnvelope __envelope = createEnvelope();
				SoapObject __soapReq = new SoapObject("http://etsmtl.ca/", "listeSessions");
				__envelope.setOutputSoapObject(__soapReq);

				PropertyInfo __info = null;
				__info = new PropertyInfo();
				__info.namespace = "http://etsmtl.ca/";
				__info.name = "codeAccesUniversel";
				__info.type = PropertyInfo.STRING_CLASS;
				__info.setValue(codeAccesUniversel);
				__soapReq.addProperty(__info);
				__info = new PropertyInfo();
				__info.namespace = "http://etsmtl.ca/";
				__info.name = "motPasse";
				__info.type = PropertyInfo.STRING_CLASS;
				__info.setValue(motPasse);
				__soapReq.addProperty(__info);
				return __envelope;
			}

			@Override
			public Object ProcessResult(ExtendedSoapSerializationEnvelope __envelope, SoapObject __result)
					throws Exception {
				return (ListeDeSessions) getResult(ListeDeSessions.class, __result, "listeSessionsResult", __envelope);
			}
		}, "http://etsmtl.ca/listeSessions");
	}

	/**
	 * Liste de toutes les sessions o�� l'��tudiant a ��t�� actif �� l'��TS, en
	 * version courte (A2011) et longue (Automne 2011)
	 */
	public void listeSessionsAsync(final String codeAccesUniversel, final String motPasse) {
		executeAsync(new Functions.IFunc<ListeDeSessions>() {
			public ListeDeSessions Func() throws Exception {
				return listeSessions(codeAccesUniversel, motPasse);
			}
		});
	}

	/**
	 * Liste des programmes d'��tudes de l'��tudiant: code, libell��, moyenne,
	 * cr��dits r��ussis, etc.)
	 */
	public listeDesProgrammes listeProgrammes(final String codeAccesUniversel, final String motPasse)
			throws Exception {
		return (listeDesProgrammes) execute(new IWcfMethod() {
			@Override
			public ExtendedSoapSerializationEnvelope CreateSoapEnvelope() {
				ExtendedSoapSerializationEnvelope __envelope = createEnvelope();
				SoapObject __soapReq = new SoapObject("http://etsmtl.ca/", "listeProgrammes");
				__envelope.setOutputSoapObject(__soapReq);

				PropertyInfo __info = null;
				__info = new PropertyInfo();
				__info.namespace = "http://etsmtl.ca/";
				__info.name = "codeAccesUniversel";
				__info.type = PropertyInfo.STRING_CLASS;
				__info.setValue(codeAccesUniversel);
				__soapReq.addProperty(__info);
				__info = new PropertyInfo();
				__info.namespace = "http://etsmtl.ca/";
				__info.name = "motPasse";
				__info.type = PropertyInfo.STRING_CLASS;
				__info.setValue(motPasse);
				__soapReq.addProperty(__info);
				return __envelope;
			}

			@Override
			public Object ProcessResult(ExtendedSoapSerializationEnvelope __envelope, SoapObject __result)
					throws Exception {
				return (listeDesProgrammes) getResult(listeDesProgrammes.class, __result, "listeProgrammesResult",
						__envelope);
			}
		}, "http://etsmtl.ca/listeProgrammes");
	}

	/**
	 * Liste des programmes d'��tudes de l'��tudiant: code, libell��, moyenne,
	 * cr��dits r��ussis, etc.)
	 */
	public void listeProgrammesAsync(final String codeAccesUniversel, final String motPasse) {
		executeAsync(new Functions.IFunc<listeDesProgrammes>() {
			public listeDesProgrammes Func() throws Exception {
				return listeProgrammes(codeAccesUniversel, motPasse);
			}
		});
	}

	/**
	 * Liste de co��quipiers de l'��tudiant pour le cours-groupe et l'��l��ment
	 * d'��valuation pass��s en param��tre: nom, pr��nom et courriel
	 */
	public listeDesCoequipiers listeCoequipiers(final String codeAccesUniversel, final String motPasse,
												final String pSigle, final String pGroupe, final String pSession, final String pNomElementEval)
			throws Exception {
		return (listeDesCoequipiers) execute(new IWcfMethod() {
			@Override
			public ExtendedSoapSerializationEnvelope CreateSoapEnvelope() {
				ExtendedSoapSerializationEnvelope __envelope = createEnvelope();
				SoapObject __soapReq = new SoapObject("http://etsmtl.ca/", "listeCoequipiers");
				__envelope.setOutputSoapObject(__soapReq);

				PropertyInfo __info = null;
				__info = new PropertyInfo();
				__info.namespace = "http://etsmtl.ca/";
				__info.name = "codeAccesUniversel";
				__info.type = PropertyInfo.STRING_CLASS;
				__info.setValue(codeAccesUniversel);
				__soapReq.addProperty(__info);
				__info = new PropertyInfo();
				__info.namespace = "http://etsmtl.ca/";
				__info.name = "motPasse";
				__info.type = PropertyInfo.STRING_CLASS;
				__info.setValue(motPasse);
				__soapReq.addProperty(__info);
				__info = new PropertyInfo();
				__info.namespace = "http://etsmtl.ca/";
				__info.name = "pSigle";
				__info.type = PropertyInfo.STRING_CLASS;
				__info.setValue(pSigle);
				__soapReq.addProperty(__info);
				__info = new PropertyInfo();
				__info.namespace = "http://etsmtl.ca/";
				__info.name = "pGroupe";
				__info.type = PropertyInfo.STRING_CLASS;
				__info.setValue(pGroupe);
				__soapReq.addProperty(__info);
				__info = new PropertyInfo();
				__info.namespace = "http://etsmtl.ca/";
				__info.name = "pSession";
				__info.type = PropertyInfo.STRING_CLASS;
				__info.setValue(pSession);
				__soapReq.addProperty(__info);
				__info = new PropertyInfo();
				__info.namespace = "http://etsmtl.ca/";
				__info.name = "pNomElementEval";
				__info.type = PropertyInfo.STRING_CLASS;
				__info.setValue(pNomElementEval);
				__soapReq.addProperty(__info);
				return __envelope;
			}

			@Override
			public Object ProcessResult(ExtendedSoapSerializationEnvelope __envelope, SoapObject __result)
					throws Exception {
				return (listeDesCoequipiers) getResult(listeDesCoequipiers.class, __result, "listeCoequipiersResult",
						__envelope);
			}
		}, "http://etsmtl.ca/listeCoequipiers");
	}

	/**
	 * Liste de co��quipiers de l'��tudiant pour le cours-groupe et l'��l��ment
	 * d'��valuation pass��s en param��tre: nom, pr��nom et courriel
	 */
	public void listeCoequipiersAsync(final String codeAccesUniversel, final String motPasse, final String pSigle,
									  final String pGroupe, final String pSession, final String pNomElementEval) {
		executeAsync(new Functions.IFunc<listeDesCoequipiers>() {
			public listeDesCoequipiers Func() throws Exception {
				return listeCoequipiers(codeAccesUniversel, motPasse, pSigle, pGroupe, pSession, pNomElementEval);
			}
		});
	}

	/**
	 * Liste des ��l��ments d'��valuation (devoirs, labos, examens, etc.) avec
	 * la note obtenue et les statisques, comme dans SIGNETS
	 */
	public ListeDesElementsEvaluation listeElementsEvaluation(final String codeAccesUniversel, final String motPasse,
															  final String pSigle, final String pGroupe, final String pSession) throws Exception {
		return (ListeDesElementsEvaluation) execute(new IWcfMethod() {
			@Override
			public ExtendedSoapSerializationEnvelope CreateSoapEnvelope() {
				ExtendedSoapSerializationEnvelope __envelope = createEnvelope();
				SoapObject __soapReq = new SoapObject("http://etsmtl.ca/", "listeElementsEvaluation");
				__envelope.setOutputSoapObject(__soapReq);

				PropertyInfo __info = null;
				__info = new PropertyInfo();
				__info.namespace = "http://etsmtl.ca/";
				__info.name = "codeAccesUniversel";
				__info.type = PropertyInfo.STRING_CLASS;
				__info.setValue(codeAccesUniversel);
				__soapReq.addProperty(__info);
				__info = new PropertyInfo();
				__info.namespace = "http://etsmtl.ca/";
				__info.name = "motPasse";
				__info.type = PropertyInfo.STRING_CLASS;
				__info.setValue(motPasse);
				__soapReq.addProperty(__info);
				__info = new PropertyInfo();
				__info.namespace = "http://etsmtl.ca/";
				__info.name = "pSigle";
				__info.type = PropertyInfo.STRING_CLASS;
				__info.setValue(pSigle);
				__soapReq.addProperty(__info);
				__info = new PropertyInfo();
				__info.namespace = "http://etsmtl.ca/";
				__info.name = "pGroupe";
				__info.type = PropertyInfo.STRING_CLASS;
				__info.setValue(pGroupe);
				__soapReq.addProperty(__info);
				__info = new PropertyInfo();
				__info.namespace = "http://etsmtl.ca/";
				__info.name = "pSession";
				__info.type = PropertyInfo.STRING_CLASS;
				__info.setValue(pSession);
				__soapReq.addProperty(__info);
				return __envelope;
			}

			@Override
			public Object ProcessResult(ExtendedSoapSerializationEnvelope __envelope, SoapObject __result)
					throws Exception {
				return (ListeDesElementsEvaluation) getResult(ListeDesElementsEvaluation.class, __result,
						"listeElementsEvaluationResult", __envelope);
			}
		}, "http://etsmtl.ca/listeElementsEvaluation");
	}

	/**
	 * Liste des ��l��ments d'��valuation (devoirs, labos, examens, etc.) avec
	 * la note obtenue et les statisques, comme dans SIGNETS
	 */
	public void listeElementsEvaluationAsync(final String codeAccesUniversel, final String motPasse,
											 final String pSigle, final String pGroupe, final String pSession) {
		executeAsync(new Functions.IFunc<ListeDesElementsEvaluation>() {
			public ListeDesElementsEvaluation Func() throws Exception {
				return listeElementsEvaluation(codeAccesUniversel, motPasse, pSigle, pGroupe, pSession);
			}
		});
	}

	/**
	 * Liste de activit��s (cours, TP, Lab, etc) avec leur horaire et leur
	 * local, ainsi que les enseignants
	 */
	public listeDesActivitesEtProf listeHoraireEtProf(final String codeAccesUniversel, final String motPasse,
													  final String pSession) throws Exception {
		return (listeDesActivitesEtProf) execute(new IWcfMethod() {
			@Override
			public ExtendedSoapSerializationEnvelope CreateSoapEnvelope() {
				ExtendedSoapSerializationEnvelope __envelope = createEnvelope();
				SoapObject __soapReq = new SoapObject("http://etsmtl.ca/", "listeHoraireEtProf");
				__envelope.setOutputSoapObject(__soapReq);

				PropertyInfo __info = null;
				__info = new PropertyInfo();
				__info.namespace = "http://etsmtl.ca/";
				__info.name = "codeAccesUniversel";
				__info.type = PropertyInfo.STRING_CLASS;
				__info.setValue(codeAccesUniversel);
				__soapReq.addProperty(__info);
				__info = new PropertyInfo();
				__info.namespace = "http://etsmtl.ca/";
				__info.name = "motPasse";
				__info.type = PropertyInfo.STRING_CLASS;
				__info.setValue(motPasse);
				__soapReq.addProperty(__info);
				__info = new PropertyInfo();
				__info.namespace = "http://etsmtl.ca/";
				__info.name = "pSession";
				__info.type = PropertyInfo.STRING_CLASS;
				__info.setValue(pSession);
				__soapReq.addProperty(__info);
				return __envelope;
			}

			@Override
			public Object ProcessResult(ExtendedSoapSerializationEnvelope __envelope, SoapObject __result)
					throws Exception {
				return (listeDesActivitesEtProf) getResult(listeDesActivitesEtProf.class, __result,
						"listeHoraireEtProfResult", __envelope);
			}
		}, "http://etsmtl.ca/listeHoraireEtProf");
	}

	/**
	 * Liste de activit��s (cours, TP, Lab, etc) avec leur horaire et leur
	 * local, ainsi que les enseignants
	 */
	public void listeHoraireEtProfAsync(final String codeAccesUniversel, final String motPasse, final String pSession) {
		executeAsync(new Functions.IFunc<listeDesActivitesEtProf>() {
			public listeDesActivitesEtProf Func() throws Exception {
				return listeHoraireEtProf(codeAccesUniversel, motPasse, pSession);
			}
		});
	}

	public listeHoraireExamensFinaux listeHoraireExamensFin(final String codeAccesUniversel, final String motPasse, final String pSession) throws Exception {
		return (listeHoraireExamensFinaux) execute(new IWcfMethod() {

			@Override
			public Object ProcessResult(ExtendedSoapSerializationEnvelope __envelope, SoapObject __result) throws Exception {
				return (listeHoraireExamensFinaux) getResult(listeHoraireExamensFinaux.class, __result,
						"listeHoraireExamensFinResult", __envelope);
			}

			@Override
			public ExtendedSoapSerializationEnvelope CreateSoapEnvelope()
					throws Exception {
				ExtendedSoapSerializationEnvelope __envelope = createEnvelope();
				SoapObject __soapReq = new SoapObject("http://etsmtl.ca/", "listeHoraireExamensFin");
				__envelope.setOutputSoapObject(__soapReq);

				PropertyInfo __info = null;
				__info = new PropertyInfo();
				__info.namespace = "http://etsmtl.ca/";
				__info.name = "codeAccesUniversel";
				__info.type = PropertyInfo.STRING_CLASS;
				__info.setValue(codeAccesUniversel);
				__soapReq.addProperty(__info);
				__info = new PropertyInfo();
				__info.namespace = "http://etsmtl.ca/";
				__info.name = "motPasse";
				__info.type = PropertyInfo.STRING_CLASS;
				__info.setValue(motPasse);
				__soapReq.addProperty(__info);
				__info = new PropertyInfo();
				__info.namespace = "http://etsmtl.ca/";
				__info.name = "pSession";
				__info.type = PropertyInfo.STRING_CLASS;
				__info.setValue(pSession);
				__soapReq.addProperty(__info);
				return __envelope;
			}
		}, "http://etsmtl.ca/listeHoraireExamensFin");
	}

	public void listeHoraireExamensFinAsync(final String codeAccesUniversel, final String motPasse, final String pSession) {
		executeAsync(new Functions.IFunc<listeHoraireExamensFinaux>() {
			public listeHoraireExamensFinaux Func() throws java.lang.Exception {
				return listeHoraireExamensFin(codeAccesUniversel, motPasse, pSession);
			}
		});
	}

	/**
	 * Liste des cours pour le trimestre et le sigle de cours partiel pass��s en
	 * param��tres. Tous les CTN1 �� l'hiver 2012, par exemple
	 */
	public listeCoursHoraire lireHoraire(final String pSession, final String prefixeSigleCours)
			throws Exception {
		return (listeCoursHoraire) execute(new IWcfMethod() {
			@Override
			public ExtendedSoapSerializationEnvelope CreateSoapEnvelope() {
				ExtendedSoapSerializationEnvelope __envelope = createEnvelope();
				SoapObject __soapReq = new SoapObject("http://etsmtl.ca/", "lireHoraire");
				__envelope.setOutputSoapObject(__soapReq);

				PropertyInfo __info = null;
				__info = new PropertyInfo();
				__info.namespace = "http://etsmtl.ca/";
				__info.name = "pSession";
				__info.type = PropertyInfo.STRING_CLASS;
				__info.setValue(pSession);
				__soapReq.addProperty(__info);
				__info = new PropertyInfo();
				__info.namespace = "http://etsmtl.ca/";
				__info.name = "prefixeSigleCours";
				__info.type = PropertyInfo.STRING_CLASS;
				__info.setValue(prefixeSigleCours);
				__soapReq.addProperty(__info);
				return __envelope;
			}

			@Override
			public Object ProcessResult(ExtendedSoapSerializationEnvelope __envelope, SoapObject __result)
					throws Exception {
				return (listeCoursHoraire) getResult(listeCoursHoraire.class, __result, "lireHoraireResult", __envelope);
			}
		}, "http://etsmtl.ca/lireHoraire");
	}

	/**
	 * Liste des cours pour le trimestre et le sigle de cours partiel pass��s en
	 * param��tres. Tous les CTN1 �� l'hiver 2012, par exemple
	 */
	public void lireHoraireAsync(final String pSession, final String prefixeSigleCours) {
		executeAsync(new Functions.IFunc<listeCoursHoraire>() {
			public listeCoursHoraire Func() throws Exception {
				return lireHoraire(pSession, prefixeSigleCours);
			}
		});
	}

	/**
	 * Liste des jours qui en remplacent d'autres, par exemple, les cours du
	 * lundi 8 octobre sont donn��s le mercredi 21 novembre.
	 */
	public listeJoursRemplaces lireJoursRemplaces(final String pSession) throws Exception {
		return (listeJoursRemplaces) execute(new IWcfMethod() {
			@Override
			public ExtendedSoapSerializationEnvelope CreateSoapEnvelope() {
				ExtendedSoapSerializationEnvelope __envelope = createEnvelope();
				SoapObject __soapReq = new SoapObject("http://etsmtl.ca/", "lireJoursRemplaces");
				__envelope.setOutputSoapObject(__soapReq);

				PropertyInfo __info = null;
				__info = new PropertyInfo();
				__info.namespace = "http://etsmtl.ca/";
				__info.name = "pSession";
				__info.type = PropertyInfo.STRING_CLASS;
				__info.setValue(pSession);
				__soapReq.addProperty(__info);
				return __envelope;
			}

			@Override
			public Object ProcessResult(ExtendedSoapSerializationEnvelope __envelope, SoapObject __result)
					throws Exception {
				return (listeJoursRemplaces) getResult(listeJoursRemplaces.class, __result, "lireJoursRemplacesResult",
						__envelope);
			}
		}, "http://etsmtl.ca/lireJoursRemplaces");
	}

	/**
	 * Liste des jours qui en remplacent d'autres, par exemple, les cours du
	 * lundi 8 octobre sont donn��s le mercredi 21 novembre.
	 */
	public void lireJoursRemplacesAsync(final String pSession) {
		executeAsync(new Functions.IFunc<listeJoursRemplaces>() {
			public listeJoursRemplaces Func() throws Exception {
				return lireJoursRemplaces(pSession);
			}
		});
	}

	public listeSeances lireHoraireDesSeances(final String codeAccesUniversel, final String motPasse, final String pCoursGroupe, final String pSession, final String pDateDebut, final String pDateFin) throws java.lang.Exception {
		return (listeSeances) execute(new IWcfMethod() {

			@Override
			public Object ProcessResult(ExtendedSoapSerializationEnvelope __envelope, SoapObject __result) throws Exception {
				return (listeSeances) getResult(listeSeances.class, __result,
						"lireHoraireDesSeancesResult", __envelope);
			}

			@Override
			public ExtendedSoapSerializationEnvelope CreateSoapEnvelope()
					throws Exception {
				ExtendedSoapSerializationEnvelope __envelope = createEnvelope();
				SoapObject __soapReq = new SoapObject("http://etsmtl.ca/", "lireHoraireDesSeances");
				__envelope.setOutputSoapObject(__soapReq);

				PropertyInfo __info = null;
				__info = new PropertyInfo();
				__info.namespace = "http://etsmtl.ca/";
				__info.name = "codeAccesUniversel";
				__info.type = PropertyInfo.STRING_CLASS;
				__info.setValue(codeAccesUniversel);
				__soapReq.addProperty(__info);
				__info = new PropertyInfo();
				__info.namespace = "http://etsmtl.ca/";
				__info.name = "motPasse";
				__info.type = PropertyInfo.STRING_CLASS;
				__info.setValue(motPasse);
				__soapReq.addProperty(__info);

				__info = new PropertyInfo();
				__info.namespace = "http://etsmtl.ca/";
				__info.name = "pCoursGroupe";
				__info.type = PropertyInfo.STRING_CLASS;
				__info.setValue(pCoursGroupe);
				__soapReq.addProperty(__info);

				__info = new PropertyInfo();
				__info.namespace = "http://etsmtl.ca/";
				__info.name = "pSession";
				__info.type = PropertyInfo.STRING_CLASS;
				__info.setValue(pSession);
				__soapReq.addProperty(__info);
				__info = new PropertyInfo();
				__info.namespace = "http://etsmtl.ca/";
				__info.name = "pDateDebut";
				__info.type = PropertyInfo.STRING_CLASS;
				__info.setValue(pDateDebut);
				__soapReq.addProperty(__info);
				__info = new PropertyInfo();
				__info.namespace = "http://etsmtl.ca/";
				__info.name = "pDateFin";
				__info.type = PropertyInfo.STRING_CLASS;
				__info.setValue(pDateFin);
				__soapReq.addProperty(__info);
				return __envelope;
			}
		}, "http://etsmtl.ca/lireHoraireDesSeances");
	}

	public void lireHoraireDesSeancesAsync(final String codeAccesUniversel, final String motPasse, final String pCoursGroupe, final String pSession, final String pDateDebut, final String pDateFin) {
		executeAsync(new Functions.IFunc<listeSeances>() {
			public listeSeances Func() throws java.lang.Exception {
				return lireHoraireDesSeances(codeAccesUniversel, motPasse, pCoursGroupe, pSession, pDateDebut, pDateFin);
			}
		});
	}

	protected Object execute(IWcfMethod wcfMethod, String methodName) throws java.lang.Exception {
		org.ksoap2.transport.Transport __httpTransport = createTransport();
		ExtendedSoapSerializationEnvelope __envelope = wcfMethod.CreateSoapEnvelope();
		sendRequest(methodName, __envelope, __httpTransport);
		Object __retObj = __envelope.bodyIn;
		if (__retObj instanceof SoapFault) {
			SoapFault __fault = (SoapFault) __retObj;
			throw convertToException(__fault, __envelope);
		} else {
			SoapObject __result = (SoapObject) __retObj;
			return wcfMethod.ProcessResult(__envelope, __result);
		}
	}

	protected <T> void executeAsync(final Functions.IFunc<T> func) {
		new AsyncTask<Void, Void, OperationResult<T>>() {
			@Override
			protected void onPreExecute() {
				callback.Starting();
			};

			@Override
			protected OperationResult<T> doInBackground(Void... params) {
				OperationResult<T> result = new OperationResult<T>();
				try {
					result.Result = func.Func();
				} catch (Exception ex) {
					ex.printStackTrace();
					result.Exception = ex;
				}
				return result;
			}

			@Override
			protected void onPostExecute(OperationResult<T> result) {
				callback.Completed(result);
			}
		}.execute();
	}

	Exception convertToException(SoapFault fault, ExtendedSoapSerializationEnvelope envelope) {
		return new Exception(fault.faultstring);
	}

	interface IWcfMethod {
		ExtendedSoapSerializationEnvelope CreateSoapEnvelope() throws Exception;

		Object ProcessResult(ExtendedSoapSerializationEnvelope envelope, SoapObject result) throws Exception;
	}
}