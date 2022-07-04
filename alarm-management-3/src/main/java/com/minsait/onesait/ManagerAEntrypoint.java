package com.minsait.onesait;

import java.util.Random;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ManagerAEntrypoint {

	private static final String API_KEY = "49832a9c2d7645caa0ef497706edef34";
	private static final String API_ENDPOINT ="https://onesait-telco-poc.onesaitplatform.com/api-manager/server/api/v1/ticket-management/manage/";
	private static final String AGENT="Agent_C";

	public String handleRequest(String id) {
		String result = "Processed ticket with id " + id + ".";
		try {
			final long x = 500L;
			final long y = 3000L;
			final Random r = new Random();
			final long sleep = x+(long)(r.nextDouble()*(y-x));
			Thread.sleep(sleep);
			final Request request = new Request.Builder().url(API_ENDPOINT + AGENT + "/" +id).get()
					.build();
			final Response response = clientHttp().newCall(request).execute();
			return "{\"status\":\""+result + response.body().string()+"\"}";
		} catch (final Exception e) {
			result = "Failed to process ticket with id " + id + "\n "+e.getLocalizedMessage();
		}
		return "{\"status\":\""+result+"\"}";
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
