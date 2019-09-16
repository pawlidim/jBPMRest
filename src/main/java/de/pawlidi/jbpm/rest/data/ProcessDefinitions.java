package de.pawlidi.jbpm.rest.data;

import java.io.Serializable;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProcessDefinitions implements Serializable {

	@JsonProperty("processes")
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	private Collection<ProcessDefinition> processDefinitions;

	public ProcessDefinitions() {
		super();
	}

	/**
	 * @return the processDefinitions
	 */
	public Collection<ProcessDefinition> getProcessDefinitions() {
		return processDefinitions;
	}

	/**
	 * @param processDefinitions the processDefinitions to set
	 */
	public void setProcessDefinitions(Collection<ProcessDefinition> processDefinitions) {
		this.processDefinitions = processDefinitions;
	}

}
