package de.pawlidi.jbpm.rest.data;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
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
	 * @return
	 */
	@Headers({ "accept: application/json" })
	@GET("server/containers/{containerId}/processes/instances/{processInstanceId}")
	Call<ProcessInstance> getProcessInstance(@Path("containerId") String containerId,
			@Path("processInstanceId") String processInstanceId, @Query("withVars") Boolean withVars);

	/**
	 * Starts a new process instance of a specified process.
	 * 
	 * @param containerId container id where the process definition resides
	 * @param processId   process id that new instance should be created from
	 * @param vars        optional map of process variables
	 * @return process id
	 */
	@Headers({ "accept: application/json" })
	@POST("server/containers/{containerId}/processes/{processId}/instances")
	Call<Long> startProcess(@Path("containerId") String containerId, @Path("processId") String processId,
			@Body Map<String, String> vars);

}
