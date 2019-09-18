package de.pawlidi.jbpm.rest.data;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskCommentList implements Serializable {

	@JsonProperty("task-comment")
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	private List<TaskComment> comments;

	/**
	 * 
	 */
	public TaskCommentList() {
		super();
	}

	/**
	 * @return the comments
	 */
	public List<TaskComment> getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(List<TaskComment> comments) {
		this.comments = comments;
	}

}
