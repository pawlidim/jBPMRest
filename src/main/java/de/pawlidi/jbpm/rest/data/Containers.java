package de.pawlidi.jbpm.rest.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Containers implements Serializable {

	@SerializedName("kie-containers")
	private List<Container> containers;

	public Containers() {
		super();
		this.containers = new ArrayList<>();
	}

	/**
	 * @return the containers
	 */
	public List<Container> getContainers() {
		return containers;
	}

	/**
	 * @param containers the containers to set
	 */
	public void setContainers(List<Container> containers) {
		this.containers = containers;
	}

}
