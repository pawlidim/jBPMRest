package de.pawlidi.jbpm.rest;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Factory class for retrofit library.
 * 
 * @author PAWLIDIM
 *
 */
public final class RetrofitFactory {

	private RetrofitFactory() {
		super();
	}

	/**
	 * Creates retrofit service for given base url, username and password.
	 * 
	 * @param baseUrl
	 * @param username
	 * @param password
	 * @return retrofit, null otherwise
	 */
	public static Retrofit createRetrofit(final String baseUrl, final String username, final String password) {
		if (baseUrl != null && !baseUrl.trim().isEmpty()) {
			Retrofit.Builder builder = new Retrofit.Builder();
			builder.baseUrl(baseUrl);

			// add converter
			builder.addConverterFactory(JacksonConverterFactory.create());

			// add client
			addClient(builder, username, password);

			return builder.build();
		}
		return null;
	}

	private static void addClient(Builder builder, final String username, final String password) {
		OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
		// add other interceptors
		httpClientBuilder.addInterceptor(new LoggingInterceptor());
		// add http authenticator
		httpClientBuilder.authenticator(new Authenticator() {
			@Override
			public Request authenticate(Route route, Response response) throws IOException {
				String credential = Credentials.basic(username, password);
				return response.request().newBuilder().header("Authorization", credential).build();
			}
		});

		builder.client(httpClientBuilder.build());
	}
}
