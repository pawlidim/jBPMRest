package de.pawlidi.jbpm.rest.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * 
 * @author pawlidim
 *
 */
public interface KieServerService {

	@Headers({ "accept: application/json" })
	@GET("server/containers")
	Call<Containers> getContainers();

	@Headers({ "accept: application/json" })
	@GET("server/containers/{containerId}/processes/instances")
	Call<ProcessInstances> getProcessInstances(@Path("containerId") String containerId);
}
