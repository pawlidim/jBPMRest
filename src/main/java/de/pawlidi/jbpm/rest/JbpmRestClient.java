/**
 * 
 */
package de.pawlidi.jbpm.rest;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import de.pawlidi.jbpm.rest.data.Containers;
import de.pawlidi.jbpm.rest.data.KieServerService;
import de.pawlidi.jbpm.rest.data.ProcessDefinitions;
import de.pawlidi.jbpm.rest.data.ProcessInstance;
import de.pawlidi.jbpm.rest.data.ProcessInstanceStatus;
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
		if (StringUtils.isBlank(url) || StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			throw new IllegalArgumentException("Invalid jbpm client parameters!");
		}
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
	public Optional<ProcessDefinitions> getProcessDefinitions(String containerId, Integer page, Integer pageSize,
			String sort, Boolean sortOrder) {
		// check container id
		if (StringUtils.isBlank(containerId)) {
			return Optional.empty();
		}

		Call<ProcessDefinitions> call = service.getProcessDefinitions(containerId, page, pageSize, sort, sortOrder);
		try {
			Response<ProcessDefinitions> response = call.execute();
			if (response.isSuccessful()) {
				return Optional.of(response.body());
			}
		} catch (IOException e) {
			log.log(Level.SEVERE, "Could not get process definitions", e);
		}
		return Optional.empty();
	}

	/**
	 * 
	 * @param containerId
	 * @return
	 */
	public Optional<ProcessInstances> getProcessInstances(String containerId, Integer page, Integer pageSize,
			String sort, Boolean sortOrder, ProcessInstanceStatus status) {
		// check container id
		if (StringUtils.isBlank(containerId)) {
			return Optional.empty();
		}
		Call<ProcessInstances> call = service.getProcessInstances(containerId, page, pageSize, sort, sortOrder,
				status.getStatus());
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
	 * @param initiator
	 * @param page
	 * @param pageSize
	 * @param processName
	 * @param sort
	 * @param sortOrder
	 * @param status
	 * @return
	 */
	public Optional<ProcessInstances> getProcessInstancesForInitiator(String initiator, Integer page, Integer pageSize,
			String processName, String sort, Boolean sortOrder, ProcessInstanceStatus status) {
		if (StringUtils.isBlank(initiator) || StringUtils.isBlank(processName) || status == null) {
			return Optional.empty();
		}

		Call<ProcessInstances> call = service.getProcessInstancesForInitiator(initiator, page, pageSize, processName,
				sort, sortOrder, status.getStatus());

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
	public Optional<ProcessInstance> getProcessInstance(String containerId, Integer processInstanceId) {
		return getProcessInstance(containerId, processInstanceId, true);
	}

	/**
	 * 
	 * @param containerId
	 * @param processInstanceId
	 * @param withVars
	 * @return
	 */
	public Optional<ProcessInstance> getProcessInstance(String containerId, Integer processInstanceId,
			Boolean withVars) {
		// check container id
		if (StringUtils.isBlank(containerId) || processInstanceId == null) {
			return Optional.empty();
		}

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

	/**
	 * 
	 * @param containerId
	 * @param processId
	 * @param vars
	 * @return
	 */
	public Optional<Integer> startProcess(String containerId, String process, Map<String, String> vars) {
		// check container id
		if (StringUtils.isBlank(containerId) || StringUtils.isBlank(process)) {
			return Optional.empty();
		}
		Call<Integer> call = service.startProcess(containerId, process, vars);
		try {
			Response<Integer> response = call.execute();
			if (response.isSuccessful()) {
				return Optional.of(response.body());
			}
		} catch (IOException e) {
			log.log(Level.SEVERE, "Could not start process", e);
		}
		return Optional.empty();
	}

	/**
	 * 
	 * @param containerId
	 * @param processInstanceId
	 * @return
	 */
	public boolean abortProcessInstance(String containerId, Integer processInstanceId) {
		if (StringUtils.isBlank(containerId) || processInstanceId == null) {
			return false;
		}
		Call<Void> call = service.abortProcessInstance(containerId, processInstanceId);
		try {
			Response<Void> response = call.execute();
			if (response.isSuccessful()) {
				return true;
			}
		} catch (IOException e) {
			log.log(Level.SEVERE, "Could not abort process", e);
		}
		return false;
	}

	/**
	 * 
	 * @param containerId
	 * @param processInstanceId
	 * @param variables
	 * @return
	 */
	public boolean updateVariables(String containerId, Integer processInstanceId, Map<String, String> variables) {
		if (StringUtils.isBlank(containerId) || processInstanceId == null || MapUtils.isEmpty(variables)) {
			return false;
		}
		Call<Void> call = service.updateVariables(containerId, processInstanceId, variables);
		try {
			Response<Void> response = call.execute();
			if (response.isSuccessful()) {
				return true;
			}
		} catch (IOException e) {
			log.log(Level.SEVERE, "Could not update process variables", e);
		}
		return false;
	}

	/**
	 * 
	 * @param containerId
	 * @param processInstanceId
	 * @return
	 */
	public Optional<Map<String, String>> getProcessVariables(String containerId, Integer processInstanceId) {
		if (StringUtils.isBlank(containerId) || processInstanceId == null) {
			return Optional.empty();
		}
		Call<Map<String, String>> call = service.getAllVariables(containerId, processInstanceId);
		try {
			Response<Map<String, String>> response = call.execute();
			if (response.isSuccessful()) {
				return Optional.of(response.body());
			}
		} catch (IOException e) {
			log.log(Level.SEVERE, "Could not update process variables", e);
		}
		return Optional.empty();
	}

}
