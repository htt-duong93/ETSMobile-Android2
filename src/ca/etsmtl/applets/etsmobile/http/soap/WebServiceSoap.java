package ca.etsmtl.applets.etsmobile.http.soap;

//----------------------------------------------------
//
// Generated by www.easywsdl.com
// Version: 2.0.3.1
//
// Created by Quasar Development at 03-03-2014
//
//---------------------------------------------------

import java.util.List;

import org.ksoap2.HeaderProperty;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.transport.HttpTransportSE;

import android.os.AsyncTask;
import ca.etsmtl.applets.etsmobile.model.ArrayOfFicheEmploye;
import ca.etsmtl.applets.etsmobile.model.ArrayOfFicheEmployeDate;
import ca.etsmtl.applets.etsmobile.model.ArrayOfService;
import ca.etsmtl.applets.etsmobile.model.FicheEmploye;
import ca.etsmtl.applets.etsmobile.model.OperationResult;

public class WebServiceSoap {
	interface IWcfMethod {
		ExtendedSoapSerializationEnvelope CreateSoapEnvelope()
				throws java.lang.Exception;

		Object ProcessResult(ExtendedSoapSerializationEnvelope envelope,
				SoapObject result) throws java.lang.Exception;
	}

	String url = "http://etsmtl.ca/cmspages/webservice.asmx";
	int timeOut = 60000;
	public List<HeaderProperty> httpHeaders;

	IServiceEvents callback;

	public WebServiceSoap() {
	}

	public WebServiceSoap(IServiceEvents callback) {
		this.callback = callback;
	}

	public WebServiceSoap(IServiceEvents callback, String url) {
		this.callback = callback;
		this.url = url;
	}

	public WebServiceSoap(IServiceEvents callback, String url, int timeOut) {
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

	protected void sendRequest(String methodName,
			ExtendedSoapSerializationEnvelope envelope,
			org.ksoap2.transport.Transport transport)
			throws java.lang.Exception {
		transport.call(methodName, envelope, httpHeaders);
	}

	Object getResult(java.lang.Class destObj, SoapObject source,
			String resultName, ExtendedSoapSerializationEnvelope __envelope)
			throws java.lang.Exception {
		if (source.hasProperty(resultName)) {
			Object j = source.getProperty(resultName);
			if (j == null) {
				return null;
			}
			Object instance = __envelope.get(j, destObj);
			return instance;
		} else if (source.getName().equals(resultName)) {
			Object instance = __envelope.get(source, destObj);
			return instance;
		}
		return null;
	}

	public ArrayOfFicheEmploye Recherche(final String FiltreNom,
			final String FiltrePrenom, final String FiltreServiceCode)
			throws java.lang.Exception {
		return (ArrayOfFicheEmploye) execute(new IWcfMethod() {
			@Override
			public ExtendedSoapSerializationEnvelope CreateSoapEnvelope() {
				ExtendedSoapSerializationEnvelope __envelope = createEnvelope();
				SoapObject __soapReq = new SoapObject("http://etsmtl.ca/",
						"Recherche");
				__envelope.setOutputSoapObject(__soapReq);

				PropertyInfo __info = null;
				__info = new PropertyInfo();
				__info.namespace = "http://etsmtl.ca/";
				__info.name = "FiltreNom";
				__info.type = PropertyInfo.STRING_CLASS;
				__info.setValue(FiltreNom);
				__soapReq.addProperty(__info);
				__info = new PropertyInfo();
				__info.namespace = "http://etsmtl.ca/";
				__info.name = "FiltrePrenom";
				__info.type = PropertyInfo.STRING_CLASS;
				__info.setValue(FiltrePrenom);
				__soapReq.addProperty(__info);
				__info = new PropertyInfo();
				__info.namespace = "http://etsmtl.ca/";
				__info.name = "FiltreServiceCode";
				__info.type = PropertyInfo.STRING_CLASS;
				__info.setValue(FiltreServiceCode);
				__soapReq.addProperty(__info);
				return __envelope;
			}

			@Override
			public Object ProcessResult(
					ExtendedSoapSerializationEnvelope __envelope,
					SoapObject __result) throws java.lang.Exception {
				return (ArrayOfFicheEmploye) getResult(
						ArrayOfFicheEmploye.class, __result, "RechercheResult",
						__envelope);
			}
		}, "http://etsmtl.ca/Recherche");
	}

	public void RechercheAsync(final String FiltreNom,
			final String FiltrePrenom, final String FiltreServiceCode) {
		executeAsync(new Functions.IFunc<ArrayOfFicheEmploye>() {
			public ArrayOfFicheEmploye Func() throws java.lang.Exception {
				return Recherche(FiltreNom, FiltrePrenom, FiltreServiceCode);
			}
		});
	}

	public ArrayOfFicheEmployeDate RechercheDate(final String FiltreNom,
			final String FiltrePrenom, final String FiltreServiceCode)
			throws java.lang.Exception {
		return (ArrayOfFicheEmployeDate) execute(new IWcfMethod() {
			@Override
			public ExtendedSoapSerializationEnvelope CreateSoapEnvelope() {
				ExtendedSoapSerializationEnvelope __envelope = createEnvelope();
				SoapObject __soapReq = new SoapObject("http://etsmtl.ca/",
						"RechercheDate");
				__envelope.setOutputSoapObject(__soapReq);

				PropertyInfo __info = null;
				__info = new PropertyInfo();
				__info.namespace = "http://etsmtl.ca/";
				__info.name = "FiltreNom";
				__info.type = PropertyInfo.STRING_CLASS;
				__info.setValue(FiltreNom);
				__soapReq.addProperty(__info);
				__info = new PropertyInfo();
				__info.namespace = "http://etsmtl.ca/";
				__info.name = "FiltrePrenom";
				__info.type = PropertyInfo.STRING_CLASS;
				__info.setValue(FiltrePrenom);
				__soapReq.addProperty(__info);
				__info = new PropertyInfo();
				__info.namespace = "http://etsmtl.ca/";
				__info.name = "FiltreServiceCode";
				__info.type = PropertyInfo.STRING_CLASS;
				__info.setValue(FiltreServiceCode);
				__soapReq.addProperty(__info);
				return __envelope;
			}

			@Override
			public Object ProcessResult(
					ExtendedSoapSerializationEnvelope __envelope,
					SoapObject __result) throws java.lang.Exception {
				return (ArrayOfFicheEmployeDate) getResult(
						ArrayOfFicheEmployeDate.class, __result,
						"RechercheDateResult", __envelope);
			}
		}, "http://etsmtl.ca/RechercheDate");
	}

	public void RechercheDateAsync(final String FiltreNom,
			final String FiltrePrenom, final String FiltreServiceCode) {
		executeAsync(new Functions.IFunc<ArrayOfFicheEmployeDate>() {
			public ArrayOfFicheEmployeDate Func() throws java.lang.Exception {
				return RechercheDate(FiltreNom, FiltrePrenom, FiltreServiceCode);
			}
		});
	}

	public FicheEmploye GetFicheData(final String Id)
			throws java.lang.Exception {
		return (FicheEmploye) execute(new IWcfMethod() {
			@Override
			public ExtendedSoapSerializationEnvelope CreateSoapEnvelope() {
				ExtendedSoapSerializationEnvelope __envelope = createEnvelope();
				SoapObject __soapReq = new SoapObject("http://etsmtl.ca/",
						"GetFicheData");
				__envelope.setOutputSoapObject(__soapReq);

				PropertyInfo __info = null;
				__info = new PropertyInfo();
				__info.namespace = "http://etsmtl.ca/";
				__info.name = "Id";
				__info.type = PropertyInfo.STRING_CLASS;
				__info.setValue(Id);
				__soapReq.addProperty(__info);
				return __envelope;
			}

			@Override
			public Object ProcessResult(
					ExtendedSoapSerializationEnvelope __envelope,
					SoapObject __result) throws java.lang.Exception {
				return (FicheEmploye) getResult(FicheEmploye.class, __result,
						"GetFicheDataResult", __envelope);
			}
		}, "http://etsmtl.ca/GetFicheData");
	}

	public void GetFicheDataAsync(final String Id) {
		executeAsync(new Functions.IFunc<FicheEmploye>() {
			public FicheEmploye Func() throws java.lang.Exception {
				return GetFicheData(Id);
			}
		});
	}

	public ArrayOfService GetListeDepartement() throws java.lang.Exception {
		return (ArrayOfService) execute(new IWcfMethod() {
			@Override
			public ExtendedSoapSerializationEnvelope CreateSoapEnvelope() {
				ExtendedSoapSerializationEnvelope __envelope = createEnvelope();
				SoapObject __soapReq = new SoapObject("http://etsmtl.ca/",
						"GetListeDepartement");
				__envelope.setOutputSoapObject(__soapReq);

				PropertyInfo __info = null;
				return __envelope;
			}

			@Override
			public Object ProcessResult(
					ExtendedSoapSerializationEnvelope __envelope,
					SoapObject __result) throws java.lang.Exception {
				return (ArrayOfService) getResult(ArrayOfService.class,
						__result, "GetListeDepartementResult", __envelope);
			}
		}, "http://etsmtl.ca/GetListeDepartement");
	}

	public void GetListeDepartementAsync() {
		executeAsync(new Functions.IFunc<ArrayOfService>() {
			public ArrayOfService Func() throws java.lang.Exception {
				return GetListeDepartement();
			}
		});
	}

	public String GetFiche(final String numero, final String PathFiche)
			throws java.lang.Exception {
		return (String) execute(new IWcfMethod() {
			@Override
			public ExtendedSoapSerializationEnvelope CreateSoapEnvelope() {
				ExtendedSoapSerializationEnvelope __envelope = createEnvelope();
				SoapObject __soapReq = new SoapObject("http://etsmtl.ca/",
						"GetFiche");
				__envelope.setOutputSoapObject(__soapReq);

				PropertyInfo __info = null;
				__info = new PropertyInfo();
				__info.namespace = "http://etsmtl.ca/";
				__info.name = "numero";
				__info.type = PropertyInfo.STRING_CLASS;
				__info.setValue(numero);
				__soapReq.addProperty(__info);
				__info = new PropertyInfo();
				__info.namespace = "http://etsmtl.ca/";
				__info.name = "PathFiche";
				__info.type = PropertyInfo.STRING_CLASS;
				__info.setValue(PathFiche);
				__soapReq.addProperty(__info);
				return __envelope;
			}

			@Override
			public Object ProcessResult(
					ExtendedSoapSerializationEnvelope __envelope,
					SoapObject __result) throws java.lang.Exception {
				Object obj = __result.getProperty("GetFicheResult");
				if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
					SoapPrimitive j = (SoapPrimitive) __result
							.getProperty("GetFicheResult");
					return j.toString();
				}
				return null;
			}
		}, "http://etsmtl.ca/GetFiche");
	}

	public void GetFicheAsync(final String numero, final String PathFiche) {
		executeAsync(new Functions.IFunc<String>() {
			public String Func() throws java.lang.Exception {
				return GetFiche(numero, PathFiche);
			}
		});
	}

	protected Object execute(IWcfMethod wcfMethod, String methodName)
			throws java.lang.Exception {
		org.ksoap2.transport.Transport __httpTransport = createTransport();
		ExtendedSoapSerializationEnvelope __envelope = wcfMethod
				.CreateSoapEnvelope();
		sendRequest(methodName, __envelope, __httpTransport);
		Object __retObj = __envelope.bodyIn;
		if (__retObj instanceof org.ksoap2.SoapFault) {
			org.ksoap2.SoapFault __fault = (org.ksoap2.SoapFault) __retObj;
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
				} catch (java.lang.Exception ex) {
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

	java.lang.Exception convertToException(org.ksoap2.SoapFault fault,
			ExtendedSoapSerializationEnvelope envelope) {
		return new java.lang.Exception(fault.faultstring);
	}
}
