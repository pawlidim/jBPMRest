/**
 * 
 */
package de.pawlidi.jbpm.rest;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.pawlidi.jbpm.rest.data.Containers;
import de.pawlidi.jbpm.rest.data.KieServerService;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * 
 * 
 * 
 * @author pawlidim
 *
 */
public class JbpmRestClient {

	private static final Logger log = Logger.getLogger(JbpmRestClient.class.getName());

	private static final String BASE_PATH = "/kie-server/services/rest/";
	private Retrofit retrofit;
	private KieServerService service;

	public JbpmRestClient(final String url, final String username, final String password) {
		super();
		this.retrofit = RetrofitFactory.createRetrofit(url + BASE_PATH, username, password);
		this.service = retrofit.create(KieServerService.class);
	}

	public Containers getContainers() {
		Call<Containers> call = service.getContainers();
		try {
			Response<Containers> response = call.execute();
			if (response.isSuccessful()) {
				return response.body();
			}
		} catch (IOException e) {
			log.log(Level.SEVERE, "Could not get containers", e);
		}
		return null;
	}
}
