package de.pawlidi.jbpm.rest.data;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author pawlidim
 *
 */
public class Container implements Serializable {

	@JsonProperty("kie-container")
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	private Collection<ContainerData> containerDataList;

	public Container() {
		super();
	}

	/**
	 * @return the containerDataList
	 */
	public Collection<ContainerData> getContainerDataList() {
		return containerDataList;
	}

	/**
	 * @param containerDataList the containerDataList to set
	 */
	public void setContainerDataList(Collection<ContainerData> containerDataList) {
		this.containerDataList = containerDataList;
	}

	@Override
	public int hashCode() {
		return Objects.hash(containerDataList);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Container)) {
			return false;
		}
		Container other = (Container) obj;
		return Objects.equals(containerDataList, other.containerDataList);
	}

}
