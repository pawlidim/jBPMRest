package de.pawlidi.jbpm.rest.data;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Retrofit based kie server rest service interface.
 * 
 * @author pawlidim
 *
 */
public interface KieServerService {

	/**
	 * Returns a list of all containers.
	 * 
	 * @return
	 */
	@Headers({ "accept: application/json" })
	@GET("server/containers")
	Call<Containers> getContainers();

	/**
	 * Returns a list of process definitions in a specified KIE container.
	 * 
	 * @param containerId container id that process instance belongs to
	 * @param page        optional pagination - at which page to start, defaults to
	 *                    0 (meaning first)
	 * @param pageSize    optional pagination - size of the result, defaults to 10
	 * @param sort        optional sort column, no default
	 * @param sortOrder   optional sort direction (asc, desc) - defaults to asc
	 * @return
	 */
	@Headers({ "accept: application/json" })
	@GET("server/containers/{containerId}/processes")
	Call<ProcessDefinitions> getProcessDefinitions(@Path("containerId") String containerId, @Query("page") Integer page,
			@Query("pageSize") Integer pageSize, @Query("sort") String sort, @Query("sortOrder") Boolean sortOrder);

	/**
	 * Returns a list of process instances in a specified KIE container.
	 * 
	 * @param containerId container id that process instance belongs to
	 * @param page        optional pagination - at which page to start, defaults to
	 *                    0 (meaning first)
	 * @param pageSize    optional pagination - size of the result, defaults to 10
	 * @param sort        optional sort column, no default
	 * @param sortOrder   optional sort direction (asc, desc) - defaults to asc
	 * @param status      optional process instance status (active, completed,
	 *                    aborted) - defaults is active (1) only
	 * @return
	 */
	@Headers({ "accept: application/json" })
	@GET("server/containers/{containerId}/processes/instances")
	Call<ProcessInstances> getProcessInstances(@Path("containerId") String containerId, @Query("page") Integer page,
			@Query("pageSize") Integer pageSize, @Query("sort") String sort, @Query("sortOrder") Boolean sortOrder,
			@Query("status") int status);

	/**
	 * Returns information about a specified process instance in a specified KIE
	 * container.
	 * 
	 * @param containerId       container id that process instance belongs to
	 * @param processInstanceId identifier of the process instance to be fetched
	 * @param withVars          indicates if process instance variables should be
	 *                          loaded or not
	 * @return instances
	 */
	@Headers({ "accept: application/json" })
	@GET("server/containers/{containerId}/processes/instances/{processInstanceId}")
	Call<ProcessInstance> getProcessInstance(@Path("containerId") String containerId,
			@Path("processInstanceId") Integer processInstanceId, @Query("withVars") Boolean withVars);

	/**
	 * Returns all process instances filtered by optional parameters.
	 * 
	 * @param initiator   optional process instance initiator - user who started
	 *                    process instance to filter process instances
	 * @param page        optional pagination - at which page to start, defaults to
	 *                    0 (meaning first)
	 * @param pageSize    optional pagination - size of the result, defaults to 10
	 * @param processName optional process name to filter process instances
	 * @param sort        optional sort column, no default
	 * @param sortOrder   optional sort direction (asc, desc) - defaults to asc
	 * @param status      optional process instance status (active, completed,
	 *                    aborted) - defaults ot active (1) only
	 * @return instances
	 */
	@Headers({ "accept: application/json" })
	@GET("server/queries/processes/instances")
	Call<ProcessInstances> getProcessInstancesForInitiator(@Query("initiator") String initiator,
			@Query("page") Integer page, @Query("pageSize") Integer pageSize, @Query("processName") String processName,
			@Query("sort") String sort, @Query("sortOrder") Boolean sortOrder, @Query("status") int status);

	/**
	 * Aborts a specified process instance in a specified KIE container.
	 * 
	 * @param containerId       container id that process instance belongs to
	 * @param processInstanceId identifier of the process instance to be aborted
	 * @return
	 */
	@Headers({ "accept: application/json" })
	@DELETE("server/containers/{containerId}/processes/instances/{processInstanceId}")
	Call<Void> abortProcessInstance(@Path("containerId") String containerId,
			@Path("processInstanceId") Integer processInstanceId);

	/**
	 * Updates the values of one or more variable for a specified process instance.
	 * The request is a map in which the key is the variable name and the value is
	 * the new variable value.
	 * 
	 * @param containerId       container id that process instance belongs to
	 * @param processInstanceId identifier of the process instance to be updated
	 * @param variables         variable data give as map
	 * @return
	 */
	@Headers({ "accept: application/json" })
	@POST("server/containers/{containerId}/processes/instances/{processInstanceId}/variables")
	Call<Void> updateVariables(@Path("containerId") String containerId,
			@Path("processInstanceId") Integer processInstanceId, @Body Map<String, String> variables);

	/**
	 * Retrieves all variables for a specified process instance as a map in which
	 * the key is the variable name and the value is the variable value.
	 * 
	 * @param containerId       container id that process instance belongs to
	 * @param processInstanceId identifier of the process instance that variables
	 *                          should be retrieved from
	 * @return variables
	 */
	@Headers({ "accept: application/json" })
	@GET("server/containers/{containerId}/processes/instances/{processInstanceId}/variables")
	Call<Map<String, String>> getAllVariables(@Path("containerId") String containerId,
			@Path("processInstanceId") Integer processInstanceId);

	/**
	 * Starts a new process instance of a specified process.
	 * 
	 * @param containerId container id where the process definition resides
	 * @param processId   process id that new instance should be created from
	 * @param vars        optional map of process variables
	 * @return processInstanceId
	 */
	@Headers({ "accept: application/json" })
	@POST("server/containers/{containerId}/processes/{processId}/instances")
	Call<Integer> startProcess(@Path("containerId") String containerId, @Path("processId") String processId,
			@Body Map<String, String> vars);

	/**
	 * Returns task instances associated with a specified process instance.
	 * 
	 * @param processInstanceId process instance id to filter task instances
	 * @param page              optional pagination - at which page to start,
	 *                          defaults to 0 (meaning first)
	 * @param pageSize          optional pagination - size of the result, defaults
	 *                          to 10
	 * @param sort              optional sort column, no default
	 * @param sortOrder         optional sort direction (asc, desc) - defaults to
	 *                          asc
	 * @param statu             optional task status (Created, Ready, Reserved,
	 *                          InProgress, Suspended, Completed, Failed, Error,
	 *                          Exited, Obsolete)
	 * @return
	 */
	@Headers({ "accept: application/json" })
	@GET("server/queries/tasks/instances/process/{processInstanceId}")
	Call<TaskSummaryList> getTasksForInstance(@Path("processInstanceId") Integer processInstanceId,
			@Query("page") Integer page, @Query("pageSize") Integer pageSize, @Query("sort") String sort,
			@Query("sortOrder") Boolean sortOrder, @Query("status") int statu);

	/**
	 * Returns information about a specified task instance.
	 * 
	 * @param containerId
	 * @param taskInstanceId
	 * @param withAssignments
	 * @param withInputData
	 * @param withOutputData
	 * @return
	 */
	@Headers({ "accept: application/json" })
	@GET("server/containers/{containerId}/tasks/{taskInstanceId}")
	Call<TaskSummary> getTaskById(@Path("containerId") String containerId,
			@Path("taskInstanceId") Integer taskInstanceId, @Query("withAssignments") Boolean withAssignments,
			@Query("withInputData") Boolean withInputData, @Query("withOutputData") Boolean withOutputData);

	/**
	 * Adds a comment to a specified task instance and returns the ID of the new
	 * comment.
	 * 
	 * @param containerId    container id that task instance belongs to
	 * @param taskInstanceId identifier of the task instance that comment should be
	 *                       added to
	 * @param comment        comment data as TaskComment
	 * @return
	 */
	@Headers({ "accept: application/json" })
	@POST("server/containers/{containerId}/tasks/{taskInstanceId}/comments")
	Call<Integer> addComment(@Path("containerId") String containerId, @Path("taskInstanceId") Integer taskInstanceId,
			@Body TaskComment comment);
}
