package de.pawlidi.jbpm.rest.data;

import java.io.Serializable;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProcessInstances implements Serializable {

	@JsonProperty("process-instance")
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	private Collection<ProcessInstance> processInstances;

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

}
