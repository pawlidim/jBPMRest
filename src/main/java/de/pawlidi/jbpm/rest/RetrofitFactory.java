package de.pawlidi.jbpm.rest;

import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

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

	/** Invisible constructor */
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
			addConverter(builder);

			// add client
			addClient(builder, username, password);

			return builder.build();
		}
		return null;
	}

	private static void addConverter(Retrofit.Builder builder) {

		// add java time module
		JavaTimeModule javaTimeModule = new JavaTimeModule();
		LocalDateTimeDeserializer deserializer = new LocalDateTimeDeserializer();
		javaTimeModule.addDeserializer(LocalDateTime.class, deserializer);

		// add parameter names module
		ParameterNamesModule parameterNamesModule = new ParameterNamesModule();
		Jdk8Module jdk8Module = new Jdk8Module();

		// create json mapper and add modules
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(javaTimeModule);
		mapper.registerModule(parameterNamesModule);
		mapper.registerModule(jdk8Module);

		// add mapper to converter factory
		JacksonConverterFactory factory = JacksonConverterFactory.create(mapper);

		// add factory to retrofit http api
		builder.addConverterFactory(factory);
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
