/**
 * 
 */
package de.pawlidi.jbpm.rest;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.pawlidi.jbpm.rest.data.Containers;
import de.pawlidi.jbpm.rest.data.KieServerService;
import de.pawlidi.jbpm.rest.data.ProcessInstance;
import de.pawlidi.jbpm.rest.data.ProcessInstances;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * 
 * jBPM rest client.
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
	public Optional<Containers> getContainers() {
		Call<Containers> call = service.getContainers();
		try {
			Response<Containers> response = call.execute();
			if (response.isSuccessful()) {
				return Optional.of(response.body());
			}
		} catch (IOException e) {
			log.log(Level.SEVERE, "Could not get containers", e);
		}
		return Optional.empty();
	}

	/**
	 * 
	 * @param containerId
	 * @return
	 */
	public Optional<ProcessInstances> getProcessInstances(String containerId) {
		Call<ProcessInstances> call = service.getProcessInstances(containerId);
		try {
			Response<ProcessInstances> response = call.execute();
			if (response.isSuccessful()) {
				return Optional.of(response.body());
			}
		} catch (IOException e) {
			log.log(Level.SEVERE, "Could not get process instances", e);
		}
		return Optional.empty();
	}

	/**
	 * 
	 * @param containerId
	 * @param processInstanceId
	 * @return
	 */
	public Optional<ProcessInstance> getProcessInstance(String containerId, String processInstanceId) {
		return getProcessInstance(containerId, processInstanceId, true);
	}

	/**
	 * 
	 * @param containerId
	 * @param processInstanceId
	 * @param withVars
	 * @return
	 */
	public Optional<ProcessInstance> getProcessInstance(String containerId, String processInstanceId,
			Boolean withVars) {
		Call<ProcessInstance> call = service.getProcessInstance(containerId, processInstanceId, withVars);
		try {
			Response<ProcessInstance> response = call.execute();
			if (response.isSuccessful()) {
				return Optional.of(response.body());
			}
		} catch (IOException e) {
			log.log(Level.SEVERE, "Could not get process instance", e);
		}
		return Optional.empty();
	}

	public Optional<Long> startProcess(String containerId, String processId, Map<String, String> vars) {
		Call<Long> call = service.startProcess(containerId, processId, vars);
		try {
			Response<Long> response = call.execute();
			if (response.isSuccessful()) {
				return Optional.of(response.body());
			}
		} catch (IOException e) {
			log.log(Level.SEVERE, "Could not start process", e);
		}
		return Optional.empty();
	}
}
