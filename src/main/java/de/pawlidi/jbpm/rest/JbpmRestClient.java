/**
 * 
 */
package de.pawlidi.jbpm.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import de.pawlidi.jbpm.rest.data.Containers;
import de.pawlidi.jbpm.rest.data.KieServerService;
import de.pawlidi.jbpm.rest.data.ProcessDefinitions;
import de.pawlidi.jbpm.rest.data.ProcessInstance;
import de.pawlidi.jbpm.rest.data.ProcessInstanceStatus;
import de.pawlidi.jbpm.rest.data.ProcessInstances;
import de.pawlidi.jbpm.rest.data.TaskComment;
import de.pawlidi.jbpm.rest.data.TaskComments;
import de.pawlidi.jbpm.rest.data.TaskStatus;
import de.pawlidi.jbpm.rest.data.TaskSummary;
import de.pawlidi.jbpm.rest.data.TaskSummaryList;
import de.pawlidi.jbpm.rest.data.TimerInstanceList;
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
	 * Invisible constructor.
	 */
	protected JbpmRestClient() {
		super();
	}

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
	 * Returns a list of all containers.
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
		Integer ordinalStatus = status != null ? status.getStatus() : null;
		Call<ProcessInstances> call = service.getProcessInstances(containerId, page, pageSize, sort, sortOrder,
				ordinalStatus);
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
		if (StringUtils.isBlank(initiator) || StringUtils.isBlank(processName)) {
			return Optional.empty();
		}
		Integer ordinalStatus = status != null ? status.getStatus() : null;

		Call<ProcessInstances> call = service.getProcessInstancesForInitiator(initiator, page, pageSize, processName,
				sort, sortOrder, ordinalStatus);

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
	public Optional<ProcessInstance> getProcessInstance(String containerId, Long processInstanceId) {
		return getProcessInstance(containerId, processInstanceId, true);
	}

	/**
	 * 
	 * @param containerId
	 * @param processInstanceId
	 * @param withVars
	 * @return
	 */
	public Optional<ProcessInstance> getProcessInstance(String containerId, Long processInstanceId, Boolean withVars) {
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
	public Optional<Long> startProcess(String containerId, String process, Map<String, String> vars) {
		// check container id
		if (StringUtils.isBlank(containerId) || StringUtils.isBlank(process)) {
			return Optional.empty();
		}
		Call<Long> call = service.startProcess(containerId, process, vars);
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

	/**
	 * 
	 * @param containerId
	 * @param processInstanceId
	 * @return
	 */
	public boolean abortProcessInstance(String containerId, Long processInstanceId) {
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
	public boolean updateVariables(String containerId, Long processInstanceId, Map<String, String> variables) {
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
	public Optional<Map<String, String>> getProcessVariables(String containerId, Long processInstanceId) {
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

	/**
	 * 
	 * @param processInstanceId
	 * @param page
	 * @param pageSize
	 * @param sort
	 * @param sortOrder
	 * @param status
	 * @return
	 */
	public Optional<TaskSummaryList> getTasksForInstance(Long processInstanceId, Integer page, Integer pageSize,
			String sort, Boolean sortOrder, TaskStatus... status) {
		if (processInstanceId == null) {
			return Optional.empty();
		}
		List<String> ordinalStatus = new ArrayList<>();
		if (status != null && status.length > 0) {
			for (TaskStatus taskStatus : status) {
				ordinalStatus.add(taskStatus.getName());
			}
		}
		Call<TaskSummaryList> call = service.getTasksForInstance(processInstanceId, page, pageSize, sort, sortOrder,
				ordinalStatus);
		try {
			Response<TaskSummaryList> response = call.execute();
			if (response.isSuccessful()) {
				return Optional.ofNullable(response.body());
			}
		} catch (IOException e) {
			log.log(Level.SEVERE, "Could not load tasks for instance", e);
		}
		return Optional.empty();
	}

	/**
	 * 
	 * @param containerId
	 * @param taskInstanceId
	 * @param withAssignments
	 * @param withInputData
	 * @param withOutputData
	 * @return
	 */
	public Optional<TaskSummary> getTaskById(String containerId, Long taskInstanceId, Boolean withAssignments,
			Boolean withInputData, Boolean withOutputData) {
		if (StringUtils.isBlank(containerId) || taskInstanceId == null) {
			return Optional.empty();
		}
		Call<TaskSummary> call = service.getTaskById(containerId, taskInstanceId, withAssignments, withInputData,
				withOutputData);
		try {
			Response<TaskSummary> response = call.execute();
			if (response.isSuccessful()) {
				return Optional.ofNullable(response.body());
			}
		} catch (IOException e) {
			log.log(Level.SEVERE, "Could not load tasks for task instance", e);
		}
		return Optional.empty();
	}

	/**
	 * 
	 * @param containerId
	 * @param taskInstanceId
	 * @param comment
	 * @return
	 */
	public Optional<Long> addComment(String containerId, Long taskInstanceId, TaskComment comment) {
		if (StringUtils.isBlank(containerId) || taskInstanceId == null || comment == null) {
			return Optional.empty();
		}
		Call<Long> call = service.addComment(containerId, taskInstanceId, comment);
		try {
			Response<Long> response = call.execute();
			if (response.isSuccessful()) {
				return Optional.ofNullable(response.body());
			}
		} catch (IOException e) {
			log.log(Level.SEVERE, "Could not add comment", e);
		}
		return Optional.empty();
	}

	/**
	 * 
	 * @param containerId
	 * @param taskInstanceId
	 * @return
	 */
	public Optional<TaskComments> getComments(String containerId, Long taskInstanceId) {
		if (StringUtils.isBlank(containerId) || taskInstanceId == null) {
			return Optional.empty();
		}
		Call<TaskComments> call = service.getComments(containerId, taskInstanceId);
		try {
			Response<TaskComments> response = call.execute();
			if (response.isSuccessful()) {
				return Optional.ofNullable(response.body());
			}
		} catch (IOException e) {
			log.log(Level.SEVERE, "Could not get comments", e);
		}
		return Optional.empty();
	}

	/**
	 * 
	 * @param containerId
	 * @param taskInstanceId
	 * @param actorId
	 * @return
	 */
	public boolean activateTask(String containerId, Long taskInstanceId, String actorId) {
		if (StringUtils.isBlank(containerId) || taskInstanceId == null) {
			return false;
		}
		Call<Void> call = service.activateTask(containerId, taskInstanceId, actorId);
		try {
			Response<Void> response = call.execute();
			if (response.isSuccessful()) {
				return true;
			}
		} catch (IOException e) {
			log.log(Level.SEVERE, "Could not activate task", e);
		}
		return false;
	}

	/**
	 * 
	 * @param containerId
	 * @param taskInstanceId
	 * @param actorId
	 * @return
	 */
	public boolean claimTask(String containerId, Long taskInstanceId, String actorId) {
		if (StringUtils.isBlank(containerId) || taskInstanceId == null) {
			return false;
		}
		Call<Void> call = service.claimTask(containerId, taskInstanceId, actorId);
		try {
			Response<Void> response = call.execute();
			if (response.isSuccessful()) {
				return true;
			}
		} catch (IOException e) {
			log.log(Level.SEVERE, "Could not claim task", e);
		}
		return false;
	}

	/**
	 * 
	 * @param containerId
	 * @param taskInstanceId
	 * @param autoProgress
	 * @param actorId
	 * @param vars
	 * @return
	 */
	public boolean completeTask(String containerId, Long taskInstanceId, Boolean autoProgress, String actorId,
			Map<String, String> vars) {
		if (StringUtils.isBlank(containerId) || taskInstanceId == null) {
			return false;
		}
		Call<Void> call = service.completeTask(containerId, taskInstanceId, autoProgress, actorId, vars);
		try {
			Response<Void> response = call.execute();
			if (response.isSuccessful()) {
				return true;
			}
		} catch (IOException e) {
			log.log(Level.SEVERE, "Could not complete task", e);
		}
		return false;
	}

	/**
	 * 
	 * @param containerId
	 * @param taskInstanceId
	 * @param targetUser
	 * @param actorId
	 * @return
	 */
	public boolean delegateTask(String containerId, Long taskInstanceId, String targetUser, String actorId) {
		if (StringUtils.isBlank(containerId) || taskInstanceId == null || StringUtils.isBlank(targetUser)) {
			return false;
		}
		Call<Void> call = service.delegateTask(containerId, taskInstanceId, targetUser, actorId);
		try {
			Response<Void> response = call.execute();
			if (response.isSuccessful()) {
				return true;
			}
		} catch (IOException e) {
			log.log(Level.SEVERE, "Could not delegate task", e);
		}
		return false;
	}

	/**
	 * 
	 * @param containerId
	 * @param taskInstanceId
	 * @param actorId
	 * @return
	 */
	public boolean exitTask(String containerId, Long taskInstanceId, String actorId) {
		if (StringUtils.isBlank(containerId) || taskInstanceId == null) {
			return false;
		}
		Call<Void> call = service.exitTask(containerId, taskInstanceId, actorId);
		try {
			Response<Void> response = call.execute();
			if (response.isSuccessful()) {
				return true;
			}
		} catch (IOException e) {
			log.log(Level.SEVERE, "Could not exit task", e);
		}
		return false;
	}

	/**
	 * 
	 * @param containerId
	 * @param taskInstanceId
	 * @param actorId
	 * @param vars
	 * @return
	 */
	public boolean failTask(String containerId, Long taskInstanceId, String actorId, Map<String, String> vars) {
		if (StringUtils.isBlank(containerId) || taskInstanceId == null) {
			return false;
		}
		Call<Void> call = service.failTask(containerId, taskInstanceId, actorId, vars);
		try {
			Response<Void> response = call.execute();
			if (response.isSuccessful()) {
				return true;
			}
		} catch (IOException e) {
			log.log(Level.SEVERE, "Could not fail task", e);
		}
		return false;
	}

	/**
	 * 
	 * @param containerId
	 * @param taskInstanceId
	 * @param targetUser
	 * @param actorId
	 * @return
	 */
	public boolean forwardTask(String containerId, Long taskInstanceId, String targetUser, String actorId) {
		if (StringUtils.isBlank(containerId) || taskInstanceId == null || StringUtils.isBlank(targetUser)) {
			return false;
		}
		Call<Void> call = service.forwardTask(containerId, taskInstanceId, targetUser, actorId);
		try {
			Response<Void> response = call.execute();
			if (response.isSuccessful()) {
				return true;
			}
		} catch (IOException e) {
			log.log(Level.SEVERE, "Could not forward task", e);
		}
		return false;
	}

	/**
	 * 
	 * @param containerId
	 * @param taskInstanceId
	 * @param owners
	 * @param actorId
	 * @return
	 */
	public boolean nominateTask(String containerId, Long taskInstanceId, List<String> owners, String actorId) {
		if (StringUtils.isBlank(containerId) || taskInstanceId == null || CollectionUtils.isEmpty(owners)) {
			return false;
		}
		Call<Void> call = service.nominateTask(containerId, taskInstanceId, owners, actorId);
		try {
			Response<Void> response = call.execute();
			if (response.isSuccessful()) {
				return true;
			}
		} catch (IOException e) {
			log.log(Level.SEVERE, "Could not forward task", e);
		}
		return false;
	}

	/**
	 * 
	 * @param containerId
	 * @param taskInstanceId
	 * @param actorId
	 * @return
	 */
	public boolean releaseTask(String containerId, Long taskInstanceId, String actorId) {
		if (StringUtils.isBlank(containerId) || taskInstanceId == null) {
			return false;
		}
		Call<Void> call = service.releaseTask(containerId, taskInstanceId, actorId);
		try {
			Response<Void> response = call.execute();
			if (response.isSuccessful()) {
				return true;
			}
		} catch (IOException e) {
			log.log(Level.SEVERE, "Could not release task", e);
		}
		return false;
	}

	/**
	 * 
	 * @param containerId
	 * @param taskInstanceId
	 * @param actorId
	 * @return
	 */
	public boolean resumeTask(String containerId, Long taskInstanceId, String actorId) {
		if (StringUtils.isBlank(containerId) || taskInstanceId == null) {
			return false;
		}
		Call<Void> call = service.resumeTask(containerId, taskInstanceId, actorId);
		try {
			Response<Void> response = call.execute();
			if (response.isSuccessful()) {
				return true;
			}
		} catch (IOException e) {
			log.log(Level.SEVERE, "Could not resume task", e);
		}
		return false;
	}

	/**
	 * 
	 * @param containerId
	 * @param taskInstanceId
	 * @param actorId
	 * @return
	 */
	public boolean skipTask(String containerId, Long taskInstanceId, String actorId) {
		if (StringUtils.isBlank(containerId) || taskInstanceId == null) {
			return false;
		}
		Call<Void> call = service.skipTask(containerId, taskInstanceId, actorId);
		try {
			Response<Void> response = call.execute();
			if (response.isSuccessful()) {
				return true;
			}
		} catch (IOException e) {
			log.log(Level.SEVERE, "Could not skip task", e);
		}
		return false;
	}

	/**
	 * 
	 * @param containerId
	 * @param taskInstanceId
	 * @param actorId
	 * @return
	 */
	public boolean startTask(String containerId, Long taskInstanceId, String actorId) {
		if (StringUtils.isBlank(containerId) || taskInstanceId == null) {
			return false;
		}
		Call<Void> call = service.startTask(containerId, taskInstanceId, actorId);
		try {
			Response<Void> response = call.execute();
			if (response.isSuccessful()) {
				return true;
			}
		} catch (IOException e) {
			log.log(Level.SEVERE, "Could not start task", e);
		}
		return false;
	}

	/**
	 * 
	 * @param containerId
	 * @param taskInstanceId
	 * @param actorId
	 * @return
	 */
	public boolean stopTask(String containerId, Long taskInstanceId, String actorId) {
		if (StringUtils.isBlank(containerId) || taskInstanceId == null) {
			return false;
		}
		Call<Void> call = service.stopTask(containerId, taskInstanceId, actorId);
		try {
			Response<Void> response = call.execute();
			if (response.isSuccessful()) {
				return true;
			}
		} catch (IOException e) {
			log.log(Level.SEVERE, "Could not stop task", e);
		}
		return false;
	}

	/**
	 * 
	 * @param containerId
	 * @param taskInstanceId
	 * @param actorId
	 * @return
	 */
	public boolean suspendTask(String containerId, Long taskInstanceId, String actorId) {
		if (StringUtils.isBlank(containerId) || taskInstanceId == null) {
			return false;
		}
		Call<Void> call = service.suspendTask(containerId, taskInstanceId, actorId);
		try {
			Response<Void> response = call.execute();
			if (response.isSuccessful()) {
				return true;
			}
		} catch (IOException e) {
			log.log(Level.SEVERE, "Could not suspend task", e);
		}
		return false;
	}

	/**
	 * 
	 * @param containerId
	 * @param processInstanceId
	 * @return
	 */
	public Optional<TimerInstanceList> getTimers(String containerId, Long processInstanceId) {
		if (StringUtils.isBlank(containerId) || processInstanceId == null) {
			return Optional.empty();
		}
		Call<TimerInstanceList> call = service.getTimers(containerId, processInstanceId);
		try {
			Response<TimerInstanceList> response = call.execute();
			if (response.isSuccessful()) {
				return Optional.ofNullable(response.body());
			}
		} catch (IOException e) {
			log.log(Level.SEVERE, "Could not get timers", e);
		}
		return Optional.empty();
	}

	/**
	 * 
	 * @param containerId
	 * @param processInstanceId
	 * @param timerId
	 * @param relative
	 * @param timerData
	 * @return
	 */
	public boolean updateTimer(String containerId, Long processInstanceId, Long timerId, Boolean relative,
			Map<String, String> timerData) {
		if (StringUtils.isBlank(containerId) || processInstanceId == null || timerId == null) {
			return false;
		}
		Call<Void> call = service.updateTimer(containerId, processInstanceId, timerId, relative, timerData);
		try {
			Response<Void> response = call.execute();
			if (response.isSuccessful()) {
				return true;
			}
		} catch (IOException e) {
			log.log(Level.SEVERE, "Could not update timer", e);
		}
		return false;
	}
}
