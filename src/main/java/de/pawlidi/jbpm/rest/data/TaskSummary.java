package de.pawlidi.jbpm.rest.data;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * https://www.jbpm.org/api-docs/kie-server/definitions.html#_task-summary
 * 
 * @author PAWLIDIM
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskSummary implements Serializable {

	public TaskSummary() {
		super();
	}

}
