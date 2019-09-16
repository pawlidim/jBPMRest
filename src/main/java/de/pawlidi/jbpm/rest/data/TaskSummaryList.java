package de.pawlidi.jbpm.rest.data;

import java.io.Serializable;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskSummaryList implements Serializable {

	@JsonProperty("task-summary")
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	private Collection<TaskSummary> processDefinitions;

	public TaskSummaryList() {
		super();
	}

	/**
	 * @return the processDefinitions
	 */
	public Collection<TaskSummary> getProcessDefinitions() {
		return processDefinitions;
	}

	/**
	 * @param processDefinitions the processDefinitions to set
	 */
	public void setProcessDefinitions(Collection<TaskSummary> processDefinitions) {
		this.processDefinitions = processDefinitions;
	}

}
