package com.minsait.onesait;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ManagerAEntrypoint {

	private static final String API_KEY = "49832a9c2d7645caa0ef497706edef34";
	private static final String API_ENDPOINT ="https://onesait-telco-poc.onesaitplatform.com/api-manager/server/api/v1/alarm-fire/complete-status/";

	public String handleRequest(String id) {
		String result = "Alarm with id " + id + " triggered correctly. ";
		try {
			Thread.sleep(6500);
			final Request request = new Request.Builder().url(API_ENDPOINT + id).get()
					.build();
			final Response response = clientHttp().newCall(request).execute();
			return result + response.body().string();
		} catch (final Exception e) {
			result = "Failed to handle alarm with id " + id + "\n "+e.getLocalizedMessage();
		}
		return result;
	}

	private OkHttpClient clientHttp() {
		final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
		httpClient.addInterceptor(chain -> {
			final Request request = chain.request();
			Request newRequest;

			newRequest = request.newBuilder().addHeader("X-OP-APIKey", API_KEY).addHeader("Accept", "application/json")
					.build();
			return chain.proceed(newRequest);
		});
		return httpClient.build();
	}

}
