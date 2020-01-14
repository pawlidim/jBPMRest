package de.pawlidi.jbpm.rest.data;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * https://www.jbpm.org/api-docs/kie-server/definitions.html#_task-summary
 * 
 * @author PAWLIDIM
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskSummary implements Serializable {

	@JsonProperty("task-id")
	private Long id;
	@JsonProperty("task-name")
	private String name;
	@JsonProperty("task-subject")
	private String subject;
	@JsonProperty("task-description")
	private String description;
	@JsonProperty("task-status")
	private TaskStatus status;
	@JsonProperty("task-priority")
	private Integer priority;
	@JsonProperty("task-is-skipable")
	private Boolean skipable;
	@JsonProperty("task-actual-owner")
	private String actualOwner;
	@JsonProperty("task-created-by")
	private String createdBy;
	@JsonProperty("task-created-on")
	private LocalDateTime createdOn;
	@JsonProperty("task-activation-time")
	private LocalDateTime activationTime;
	@JsonProperty("task-expiration-time")
	private LocalDateTime expirationTime;
	@JsonProperty("task-proc-inst-id")
	private Long processInstanceId;
	@JsonProperty("task-proc-def-id")
	private String processId;
	@JsonProperty("task-container-id")
	private String containerId;
	@JsonProperty("task-parent-id")
	private Long parentId;

	public TaskSummary() {
		super();
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject
	 *            the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the status
	 */
	public TaskStatus getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	/**
	 * @return the priority
	 */
	public Integer getPriority() {
		return priority;
	}

	/**
	 * @param priority
	 *            the priority to set
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	/**
	 * @return the skipable
	 */
	public Boolean getSkipable() {
		return skipable;
	}

	/**
	 * @param skipable
	 *            the skipable to set
	 */
	public void setSkipable(Boolean skipable) {
		this.skipable = skipable;
	}

	/**
	 * @return the actualOwner
	 */
	public String getActualOwner() {
		return actualOwner;
	}

	/**
	 * @param actualOwner
	 *            the actualOwner to set
	 */
	public void setActualOwner(String actualOwner) {
		this.actualOwner = actualOwner;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdOn
	 */
	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	/**
	 * @param createdOn
	 *            the createdOn to set
	 */
	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * @return the activationTime
	 */
	public LocalDateTime getActivationTime() {
		return activationTime;
	}

	/**
	 * @param activationTime
	 *            the activationTime to set
	 */
	public void setActivationTime(LocalDateTime activationTime) {
		this.activationTime = activationTime;
	}

	/**
	 * @return the expirationTime
	 */
	public LocalDateTime getExpirationTime() {
		return expirationTime;
	}

	/**
	 * @param expirationTime
	 *            the expirationTime to set
	 */
	public void setExpirationTime(LocalDateTime expirationTime) {
		this.expirationTime = expirationTime;
	}

	/**
	 * @return the processInstanceId
	 */
	public Long getProcessInstanceId() {
		return processInstanceId;
	}

	/**
	 * @param processInstanceId
	 *            the processInstanceId to set
	 */
	public void setProcessInstanceId(Long processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	/**
	 * @return the processId
	 */
	public String getProcessId() {
		return processId;
	}

	/**
	 * @param processId
	 *            the processId to set
	 */
	public void setProcessId(String processId) {
		this.processId = processId;
	}

	/**
	 * @return the containerId
	 */
	public String getContainerId() {
		return containerId;
	}

	/**
	 * @param containerId
	 *            the containerId to set
	 */
	public void setContainerId(String containerId) {
		this.containerId = containerId;
	}

	/**
	 * @return the parentId
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * @param parentId
	 *            the parentId to set
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Override
	public String toString() {
		return "TaskSummary [id=" + id + ", name=" + name + ", subject=" + subject + ", description=" + description
				+ ", status=" + status + ", priority=" + priority + ", skipable=" + skipable + ", actualOwner="
				+ actualOwner + ", createdBy=" + createdBy + ", createdOn=" + createdOn + ", activationTime="
				+ activationTime + ", expirationTime=" + expirationTime + ", processInstanceId=" + processInstanceId
				+ ", processId=" + processId + ", containerId=" + containerId + ", parentId=" + parentId + "]";
	}

}
