package de.pawlidi.jbpm.rest.data;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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
	 * @param containerId
	 *            container id that process instance belongs to
	 * @param page
	 *            optional pagination - at which page to start, defaults to 0
	 *            (meaning first)
	 * @param pageSize
	 *            optional pagination - size of the result, defaults to 10
	 * @param sort
	 *            optional sort column, no default
	 * @param sortOrder
	 *            optional sort direction (asc, desc) - defaults to asc
	 * @return
	 */
	@Headers({ "accept: application/json" })
	@GET("server/containers/{containerId}/processes")
	Call<ProcessDefinitions> getProcessDefinitions(@Path("containerId") String containerId, @Query("page") Integer page,
			@Query("pageSize") Integer pageSize, @Query("sort") String sort, @Query("sortOrder") Boolean sortOrder);

	/**
	 * Returns a list of process instances in a specified KIE container.
	 * 
	 * @param containerId
	 *            container id that process instance belongs to
	 * @param page
	 *            optional pagination - at which page to start, defaults to 0
	 *            (meaning first)
	 * @param pageSize
	 *            optional pagination - size of the result, defaults to 10
	 * @param sort
	 *            optional sort column, no default
	 * @param sortOrder
	 *            optional sort direction (asc, desc) - defaults to asc
	 * @param status
	 *            optional process instance status (active, completed, aborted) -
	 *            defaults is active (1) only
	 * @return
	 */
	@Headers({ "accept: application/json" })
	@GET("server/containers/{containerId}/processes/instances")
	Call<ProcessInstances> getProcessInstances(@Path("containerId") String containerId, @Query("page") Integer page,
			@Query("pageSize") Integer pageSize, @Query("sort") String sort, @Query("sortOrder") Boolean sortOrder,
			@Query("status") Integer status);

	/**
	 * Returns information about a specified process instance in a specified KIE
	 * container.
	 * 
	 * @param containerId
	 *            container id that process instance belongs to
	 * @param processInstanceId
	 *            identifier of the process instance to be fetched
	 * @param withVars
	 *            indicates if process instance variables should be loaded or not
	 * @return instances
	 */
	@Headers({ "accept: application/json" })
	@GET("server/containers/{containerId}/processes/instances/{processInstanceId}")
	Call<ProcessInstance> getProcessInstance(@Path("containerId") String containerId,
			@Path("processInstanceId") Long processInstanceId, @Query("withVars") Boolean withVars);

	/**
	 * Returns all process instances filtered by optional parameters.
	 * 
	 * @param initiator
	 *            optional process instance initiator - user who started process
	 *            instance to filter process instances
	 * @param page
	 *            optional pagination - at which page to start, defaults to 0
	 *            (meaning first)
	 * @param pageSize
	 *            optional pagination - size of the result, defaults to 10
	 * @param processName
	 *            optional process name to filter process instances
	 * @param sort
	 *            optional sort column, no default
	 * @param sortOrder
	 *            optional sort direction (asc, desc) - defaults to asc
	 * @param status
	 *            optional process instance status (active, completed, aborted) -
	 *            defaults ot active (1) only
	 * @return instances
	 */
	@Headers({ "accept: application/json" })
	@GET("server/queries/processes/instances")
	Call<ProcessInstances> getProcessInstancesForInitiator(@Query("initiator") String initiator,
			@Query("page") Integer page, @Query("pageSize") Integer pageSize, @Query("processName") String processName,
			@Query("sort") String sort, @Query("sortOrder") Boolean sortOrder, @Query("status") Integer status);

	/**
	 * Aborts a specified process instance in a specified KIE container.
	 * 
	 * @param containerId
	 *            container id that process instance belongs to
	 * @param processInstanceId
	 *            identifier of the process instance to be aborted
	 * @return
	 */
	@Headers({ "accept: application/json" })
	@DELETE("server/containers/{containerId}/processes/instances/{processInstanceId}")
	Call<Void> abortProcessInstance(@Path("containerId") String containerId,
			@Path("processInstanceId") Long processInstanceId);

	/**
	 * Updates the values of one or more variable for a specified process instance.
	 * The request is a map in which the key is the variable name and the value is
	 * the new variable value.
	 * 
	 * @param containerId
	 *            container id that process instance belongs to
	 * @param processInstanceId
	 *            identifier of the process instance to be updated
	 * @param variables
	 *            variable data give as map
	 * @return
	 */
	@Headers({ "accept: application/json" })
	@POST("server/containers/{containerId}/processes/instances/{processInstanceId}/variables")
	Call<Void> updateVariables(@Path("containerId") String containerId,
			@Path("processInstanceId") Long processInstanceId, @Body Map<String, String> variables);

	/**
	 * Retrieves all variables for a specified process instance as a map in which
	 * the key is the variable name and the value is the variable value.
	 * 
	 * @param containerId
	 *            container id that process instance belongs to
	 * @param processInstanceId
	 *            identifier of the process instance that variables should be
	 *            retrieved from
	 * @return variables
	 */
	@Headers({ "accept: application/json" })
	@GET("server/containers/{containerId}/processes/instances/{processInstanceId}/variables")
	Call<Map<String, String>> getAllVariables(@Path("containerId") String containerId,
			@Path("processInstanceId") Long processInstanceId);

	/**
	 * Starts a new process instance of a specified process.
	 * 
	 * @param containerId
	 *            container id where the process definition resides
	 * @param processId
	 *            process id that new instance should be created from
	 * @param vars
	 *            optional map of process variables
	 * @return processInstanceId
	 */
	@Headers({ "accept: application/json" })
	@POST("server/containers/{containerId}/processes/{processId}/instances")
	Call<Long> startProcess(@Path("containerId") String containerId, @Path("processId") String processId,
			@Body Map<String, String> vars);

	/**
	 * Returns task instances associated with a specified process instance.
	 * 
	 * @param processInstanceId
	 *            process instance id to filter task instances
	 * @param page
	 *            optional pagination - at which page to start, defaults to 0
	 *            (meaning first)
	 * @param pageSize
	 *            optional pagination - size of the result, defaults to 10
	 * @param sort
	 *            optional sort column, no default
	 * @param sortOrder
	 *            optional sort direction (asc, desc) - defaults to asc
	 * @param status
	 *            optional task status (Created, Ready, Reserved, InProgress,
	 *            Suspended, Completed, Failed, Error, Exited, Obsolete)
	 * @return
	 */
	@Headers({ "accept: application/json" })
	@GET("server/queries/tasks/instances/process/{processInstanceId}")
	Call<TaskSummaryList> getTasksForInstance(@Path("processInstanceId") Long processInstanceId,
			@Query("page") Integer page, @Query("pageSize") Integer pageSize, @Query("sort") String sort,
			@Query("sortOrder") Boolean sortOrder, @Query("status") List<String> status);

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
	Call<TaskSummary> getTaskById(@Path("containerId") String containerId, @Path("taskInstanceId") Long taskInstanceId,
			@Query("withAssignments") Boolean withAssignments, @Query("withInputData") Boolean withInputData,
			@Query("withOutputData") Boolean withOutputData);

	/**
	 * Adds a comment to a specified task instance and returns the ID of the new
	 * comment.
	 * 
	 * @param containerId
	 *            container id that task instance belongs to
	 * @param taskInstanceId
	 *            identifier of the task instance that comment should be added to
	 * @param comment
	 *            comment data as TaskComment
	 * @return comment id
	 */
	@Headers({ "accept: application/json" })
	@POST("server/containers/{containerId}/tasks/{taskInstanceId}/comments")
	Call<Long> addComment(@Path("containerId") String containerId, @Path("taskInstanceId") Long taskInstanceId,
			@Body TaskComment comment);

	/**
	 * Returns all comments in a specified task instance.
	 * 
	 * @param containerId
	 *            container id that task instance belongs to
	 * @param taskInstanceId
	 *            identifier of the task instance that comments should be loaded for
	 * @return
	 */
	@Headers({ "accept: application/json" })
	@GET("server/containers/{containerId}/tasks/{taskInstanceId}/comments")
	Call<TaskComments> getComments(@Path("containerId") String containerId,
			@Path("taskInstanceId") Long taskInstanceId);

	/**
	 * Activates a specified task instance to be progressed.
	 * 
	 * @param containerId
	 *            container id that task instance belongs to
	 * @param taskInstanceId
	 *            identifier of the task instance that should be activated
	 * @param actorId
	 *            optional user id to be used instead of authenticated user - only
	 *            when bypass authenticated user is enabled
	 * @return
	 */
	@Headers({ "accept: application/json" })
	@PUT("server/containers/{containerId}/tasks/{taskInstanceId}/states/activated")
	Call<Void> activateTask(@Path("containerId") String containerId, @Path("taskInstanceId") Long taskInstanceId,
			@Query("user") String actorId);

	/**
	 * Claims (reserves) a specified task instance for the user sending the request.
	 * 
	 * @param containerId
	 *            container id that task instance belongs to
	 * @param taskInstanceId
	 *            identifier of the task instance that should be claimed
	 * @param actorId
	 *            optional user id to be used instead of authenticated user - only
	 *            when bypass authenticated user is enabled
	 * @return
	 */
	@Headers({ "accept: application/json" })
	@PUT("server/containers/{containerId}/tasks/{taskInstanceId}/states/claimed")
	Call<Void> claimTask(@Path("containerId") String containerId, @Path("taskInstanceId") Long taskInstanceId,
			@Query("user") String actorId);

	/**
	 * Completes a specified task instance.
	 * 
	 * @param containerId
	 *            container id that task instance belongs to
	 * @param taskInstanceId
	 *            identifier of the task instance that should be completed
	 * @param autoProgress
	 *            optional flag that allows to directlu claim and start task (if
	 *            needed) before completion
	 * @param actorId
	 *            optional user id to be used instead of authenticated user - only
	 *            when bypass authenticated user is enabled
	 * @param vars
	 *            optional map of output variables
	 * @return
	 */
	@Headers({ "accept: application/json" })
	@PUT("server/containers/{containerId}/tasks/{taskInstanceId}/states/completed")
	Call<Void> completeTask(@Path("containerId") String containerId, @Path("taskInstanceId") Long taskInstanceId,
			@Query("auto-progress") Boolean autoProgress, @Query("user") String actorId,
			@Body Map<String, String> vars);

	/**
	 * Delegates a specified task instance to a specified target user as the new
	 * task owner.
	 * 
	 * @param containerId
	 *            container id that task instance belongs to
	 * @param taskInstanceId
	 *            identifier of the task instance that should be delegated
	 * @param targetUser
	 *            user that task should be dalegated to
	 * @param actorId
	 *            optional user id to be used instead of authenticated user - only
	 *            when bypass authenticated user is enabled
	 * @return
	 */
	@Headers({ "accept: application/json" })
	@PUT("server/containers/{containerId}/tasks/{taskInstanceId}/states/delegated")
	Call<Void> delegateTask(@Path("containerId") String containerId, @Path("taskInstanceId") Long taskInstanceId,
			@Query("targetUser") String targetUser, @Query("user") String actorId);

	/**
	 * Exits a specified task instance.
	 * 
	 * @param containerId
	 *            container id that task instance belongs to
	 * @param taskInstanceId
	 *            identifier of the task instance that should be exited
	 * @param actorId
	 *            optional user id to be used instead of authenticated user - only
	 *            when bypass authenticated user is enabled
	 * @return
	 */
	@Headers({ "accept: application/json" })
	@PUT("server/containers/{containerId}/tasks/{taskInstanceId}/states/exited")
	Call<Void> exitTask(@Path("containerId") String containerId, @Path("taskInstanceId") Long taskInstanceId,
			@Query("user") String actorId);

	/**
	 * Fails a specified task instance.
	 * 
	 * @param containerId
	 *            container id that task instance belongs to
	 * @param taskInstanceId
	 *            identifier of the task instance that should be failed
	 * @param actorId
	 *            optional user id to be used instead of authenticated user - only
	 *            when bypass authenticated user is enabled
	 * @param vars
	 *            optional map of output variables
	 * @return
	 */
	@Headers({ "accept: application/json" })
	@PUT("server/containers/{containerId}/tasks/{taskInstanceId}/states/failed")
	Call<Void> failTask(@Path("containerId") String containerId, @Path("taskInstanceId") Long taskInstanceId,
			@Query("user") String actorId, @Body Map<String, String> vars);

	/**
	 * Forwards a specified task instance to a specified target user for review or
	 * for suggested delegation.
	 * 
	 * @param containerId
	 *            container id that task instance belongs to
	 * @param taskInstanceId
	 *            identifier of the task instance that should be delegated
	 * @param targetUser
	 *            user that the task should be forwarded to
	 * @param actorId
	 *            optional user id to be used instead of authenticated user - only
	 *            when bypass authenticated user is enabled
	 * @return
	 */
	@Headers({ "accept: application/json" })
	@PUT("server/containers/{containerId}/tasks/{taskInstanceId}/states/forwarded")
	Call<Void> forwardTask(@Path("containerId") String containerId, @Path("taskInstanceId") Long taskInstanceId,
			@Query("targetUser") String targetUser, @Query("user") String actorId);

	/**
	 * Nominates one or more potential owners to whom the task instance should be
	 * assigned.
	 * 
	 * @param containerId
	 *            container id that task instance belongs to
	 * @param taskInstanceId
	 *            identifier of the task instance that should be nominated
	 * @param potOwners
	 *            list of users that the task should be nominated to
	 * @param actorId
	 *            optional user id to be used instead of authenticated user - only
	 *            when bypass authenticated user is enabled
	 * @return
	 */
	@Headers({ "accept: application/json" })
	@PUT("server/containers/{containerId}/tasks/{taskInstanceId}/states/nominated")
	Call<Void> nominateTask(@Path("containerId") String containerId, @Path("taskInstanceId") Long taskInstanceId,
			@Query("potOwner[]") List<String> potOwners, @Query("user") String actorId);

	/**
	 * Releases a specified task instance from being claimed by the task owner.
	 * 
	 * @param containerId
	 *            container id that task instance belongs to
	 * @param taskInstanceId
	 *            identifier of the task instance that should be released
	 * @param actorId
	 *            optional user id to be used instead of authenticated user - only
	 *            when bypass authenticated user is enabled
	 * @return
	 */
	@Headers({ "accept: application/json" })
	@PUT("server/containers/{containerId}/tasks/{taskInstanceId}/states/released")
	Call<Void> releaseTask(@Path("containerId") String containerId, @Path("taskInstanceId") Long taskInstanceId,
			@Query("user") String actorId);

	/**
	 * Resumes a specified task instance.
	 * 
	 * @param containerId
	 *            container id that task instance belongs to
	 * @param taskInstanceId
	 *            identifier of the task instance that should be resumed
	 * @param actorId
	 *            optional user id to be used instead of authenticated user - only
	 *            when bypass authenticated user is enabled
	 * @return
	 */
	@Headers({ "accept: application/json" })
	@PUT("server/containers/{containerId}/tasks/{taskInstanceId}/states/resumed")
	Call<Void> resumeTask(@Path("containerId") String containerId, @Path("taskInstanceId") Long taskInstanceId,
			@Query("user") String actorId);

	/**
	 * Skips a specified task instance within the sequence of tasks in the process
	 * instance
	 * 
	 * @param containerId
	 *            container id that task instance belongs to
	 * @param taskInstanceId
	 *            identifier of the task instance that should be skipped
	 * @param actorId
	 *            optional user id to be used instead of authenticated user - only
	 *            when bypass authenticated user is enabled
	 * @return
	 */
	@Headers({ "accept: application/json" })
	@PUT("server/containers/{containerId}/tasks/{taskInstanceId}/states/skipped")
	Call<Void> skipTask(@Path("containerId") String containerId, @Path("taskInstanceId") Long taskInstanceId,
			@Query("user") String actorId);

	/**
	 * Starts a specified task instance.
	 * 
	 * @param containerId
	 *            container id that task instance belongs to
	 * @param taskInstanceId
	 *            identifier of the task instance that should be started
	 * @param actorId
	 *            optional user id to be used instead of authenticated user - only
	 *            when bypass authenticated user is enabled
	 * @return
	 */
	@Headers({ "accept: application/json" })
	@PUT("server/containers/{containerId}/tasks/{taskInstanceId}/states/started")
	Call<Void> startTask(@Path("containerId") String containerId, @Path("taskInstanceId") Long taskInstanceId,
			@Query("user") String actorId);

	/**
	 * Stops a specified task instance.
	 * 
	 * @param containerId
	 *            container id that task instance belongs to
	 * @param taskInstanceId
	 *            identifier of the task instance that should be stopped
	 * @param actorId
	 *            optional user id to be used instead of authenticated user - only
	 *            when bypass authenticated user is enabled
	 * @return
	 */
	@Headers({ "accept: application/json" })
	@PUT("server/containers/{containerId}/tasks/{taskInstanceId}/states/stopped")
	Call<Void> stopTask(@Path("containerId") String containerId, @Path("taskInstanceId") Long taskInstanceId,
			@Query("user") String actorId);

	/**
	 * Suspends a specified task instance.
	 * 
	 * @param containerId
	 *            container id that task instance belongs to
	 * @param taskInstanceId
	 *            identifier of the task instance that should be suspended
	 * @param actorId
	 *            optional user id to be used instead of authenticated user - only
	 *            when bypass authenticated user is enabled
	 * @return
	 */
	@Headers({ "accept: application/json" })
	@PUT("server/containers/{containerId}/tasks/{taskInstanceId}/states/suspended")
	Call<Void> suspendTask(@Path("containerId") String containerId, @Path("taskInstanceId") Long taskInstanceId,
			@Query("user") String actorId);

	/**
	 * Returns all timers for a specified process instance.
	 * 
	 * @param containerId
	 *            container id that process instance belongs to
	 * @param processInstanceId
	 *            identifier of process instance that timer instances should be
	 *            collected for
	 * @return
	 */
	@Headers({ "accept: application/json" })
	@GET("server/admin/containers/{containerId}/processes/instances/{processInstanceId}/timers")
	Call<TimerInstanceList> getTimers(@Path("containerId") String containerId,
			@Path("processInstanceId") Long processInstanceId);

	/**
	 * Updates a specified timer for a specified process instance.
	 * 
	 * @param containerId
	 *            container id that process instance belongs to
	 * @param processInstanceId
	 *            identifier of process instance that timer belongs to
	 * @param timerId
	 *            identifier of timer instance to be updated
	 * @param relative
	 *            optional flag that indicates if the time expression is relative to
	 *            the current date or not, defaults to true
	 * @param timerData
	 *            Map of timer expressions - deplay, perios and repeat are allowed
	 *            values in the map
	 * @return
	 */
	@Headers({ "accept: application/json" })
	@PUT("/server/admin/containers/{containerId}/processes/instances/{processInstanceId}/timers/{timerId}")
	Call<Void> updateTimer(@Path("containerId") String containerId, @Path("processInstanceId") Long processInstanceId,
			@Path("timerId") Long timerId, @Query("relative") Boolean relative, @Body Map<String, String> timerData);
}
