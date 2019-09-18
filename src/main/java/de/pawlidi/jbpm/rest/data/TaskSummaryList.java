package de.pawlidi.jbpm.rest.data;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskSummaryList implements Serializable {

	@JsonProperty("task-summary")
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	private List<TaskSummary> taskSummaries;

	public TaskSummaryList() {
		super();
	}

	/**
	 * @return the taskSummaries
	 */
	public List<TaskSummary> getTaskSummaries() {
		return taskSummaries;
	}

	/**
	 * @param taskSummaries the taskSummaries to set
	 */
	public void setTaskSummaries(List<TaskSummary> taskSummaries) {
		this.taskSummaries = taskSummaries;
	}

}
