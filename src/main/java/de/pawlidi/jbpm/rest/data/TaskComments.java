package de.pawlidi.jbpm.rest.data;

import java.io.Serializable;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskComments implements Serializable {

	@JsonProperty("task-comment")
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	private Collection<TaskComment> taskComments;

	public TaskComments() {
		super();
	}

	/**
	 * @return the taskComments
	 */
	public Collection<TaskComment> getTaskComments() {
		return taskComments;
	}

	/**
	 * @param taskComments the taskComments to set
	 */
	public void setTaskComments(Collection<TaskComment> taskComments) {
		this.taskComments = taskComments;
	}

}
