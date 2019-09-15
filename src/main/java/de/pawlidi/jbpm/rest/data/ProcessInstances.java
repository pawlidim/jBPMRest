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
public class ProcessInstances implements Serializable {

	@JsonProperty("process-instance")
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	private Collection<ProcessInstance> processInstances;

	/**
	 * Default constructor for process instances.
	 */
	public ProcessInstances() {
		super();
	}

	/**
	 * @return the processInstances
	 */
	public Collection<ProcessInstance> getProcessInstances() {
		return processInstances;
	}

	/**
	 * @param processInstances the processInstances to set
	 */
	public void setProcessInstances(Collection<ProcessInstance> processInstances) {
		this.processInstances = processInstances;
	}

	@Override
	public int hashCode() {
		return Objects.hash(processInstances);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ProcessInstances)) {
			return false;
		}
		ProcessInstances other = (ProcessInstances) obj;
		return Objects.equals(processInstances, other.processInstances);
	}

}
