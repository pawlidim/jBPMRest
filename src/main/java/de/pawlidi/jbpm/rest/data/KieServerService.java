package de.pawlidi.jbpm.rest.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * 
 * @author pawlidim
 *
 */
public interface KieServerService {

	@Headers({ "accept: application/json" })
	@GET("server/containers")
	Call<Containers> getContainers();
}
