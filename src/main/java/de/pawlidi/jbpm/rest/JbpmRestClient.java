/**
 * 
 */
package de.pawlidi.jbpm.rest;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.pawlidi.jbpm.rest.data.Containers;
import de.pawlidi.jbpm.rest.data.KieServerService;
import de.pawlidi.jbpm.rest.data.ProcessInstances;
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

	/**
	 * Default constructor for jbpm rest client.
	 * 
	 * @param url
	 * @param username
	 * @param password
	 */
	public JbpmRestClient(final String url, final String username, final String password) {
		super();
		this.retrofit = RetrofitFactory.createRetrofit(url + BASE_PATH, username, password);
		this.service = retrofit.create(KieServerService.class);
	}

	/**
	 * 
	 * @return
	 */
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

	/**
	 * 
	 * @param containerId
	 * @return
	 */
	public ProcessInstances getProcessInstances(String containerId) {
		Call<ProcessInstances> call = service.getProcessInstances(containerId);
		try {
			Response<ProcessInstances> response = call.execute();
			if (response.isSuccessful()) {
				return response.body();
			}
		} catch (IOException e) {
			log.log(Level.SEVERE, "Could not get process instances", e);
		}
		return null;
	}
}
