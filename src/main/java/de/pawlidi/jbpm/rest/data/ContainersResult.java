package de.pawlidi.jbpm.rest.data;

import java.io.Serializable;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author pawlidim
 *
 */
public class ContainersResult implements Serializable {

	@JsonProperty("kie-containers")
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	private Collection<Container> containers;

	public ContainersResult() {
		super();
	}

	/**
	 * @return the containers
	 */
	public Collection<Container> getContainers() {
		return containers;
	}

	/**
	 * @param containers the containers to set
	 */
	public void setContainers(Collection<Container> containers) {
		this.containers = containers;
	}
}
