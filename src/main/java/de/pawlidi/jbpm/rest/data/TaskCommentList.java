package de.pawlidi.jbpm.rest.data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskCommentList {

	@JsonProperty("task-comment")
	private List<TaskComment> comments;

}
