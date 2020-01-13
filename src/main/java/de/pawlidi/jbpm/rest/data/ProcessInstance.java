/**
 * 
 */
package de.pawlidi.jbpm.rest.data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author pawlidim
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProcessInstance implements Serializable {

	@JsonProperty("process-id")
	private String processId;
	@JsonProperty("process-name")
	private String name;
	@JsonProperty("process-version")
	private String version;
	@JsonProperty("process-instance-id")
	private Long intstanceId;
	@JsonProperty("process-instance-state")
	private ProcessInstanceStatus status;
	@JsonProperty("container-id")
	private String containerId;
	@JsonProperty("initiator")
	private String initiator;
	@JsonProperty("start-date")
	private LocalDateTime startDate;
	@JsonProperty("process-instance-desc")
	private String description;
	@JsonProperty("correlation-key")
	private String correlationKey;
	@JsonProperty("parent-instance-id")
	private String parentInstanceId;
	@JsonProperty("process-instance-variables")
	@JsonFormat(with = JsonFormat.Feature.WRITE_SORTED_MAP_ENTRIES)
	private Map<String, String> variables;
	@JsonProperty("active-user-tasks")
	private TaskSummaryList taskSummaryList;

	/**
	 * 
	 */
	public ProcessInstance() {
		super();
	}

	/**
	 * @return the intstanceId
	 */
	public Long getIntstanceId() {
		return intstanceId;
	}

	/**
	 * @param intstanceId
	 *            the intstanceId to set
	 */
	public void setIntstanceId(Long intstanceId) {
		this.intstanceId = intstanceId;
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
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the state
	 */
	public ProcessInstanceStatus getStatus() {
		return status;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setStatus(ProcessInstanceStatus status) {
		this.status = status;
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
	 * @return the initiator
	 */
	public String getInitiator() {
		return initiator;
	}

	/**
	 * @param initiator
	 *            the initiator to set
	 */
	public void setInitiator(String initiator) {
		this.initiator = initiator;
	}

	/**
	 * @return the startDate
	 */
	public LocalDateTime getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
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
	 * @return the correlationKey
	 */
	public String getCorrelationKey() {
		return correlationKey;
	}

	/**
	 * @param correlationKey
	 *            the correlationKey to set
	 */
	public void setCorrelationKey(String correlationKey) {
		this.correlationKey = correlationKey;
	}

	/**
	 * @return the parentInstanceId
	 */
	public String getParentInstanceId() {
		return parentInstanceId;
	}

	/**
	 * @param parentInstanceId
	 *            the parentInstanceId to set
	 */
	public void setParentInstanceId(String parentInstanceId) {
		this.parentInstanceId = parentInstanceId;
	}

	/**
	 * @return the variables
	 */
	public Map<String, String> getVariables() {
		return variables;
	}

	/**
	 * @param variables
	 *            the variables to set
	 */
	public void setVariables(Map<String, String> variables) {
		this.variables = variables;
	}

	/**
	 * @return the taskSummaryList
	 */
	public TaskSummaryList getTaskSummaryList() {
		return taskSummaryList;
	}

	/**
	 * @param taskSummaryList
	 *            the taskSummaryList to set
	 */
	public void setTaskSummaryList(TaskSummaryList taskSummaryList) {
		this.taskSummaryList = taskSummaryList;
	}

	@Override
	public int hashCode() {
		return Objects.hash(containerId, intstanceId, processId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ProcessInstance)) {
			return false;
		}
		ProcessInstance other = (ProcessInstance) obj;
		return Objects.equals(containerId, other.containerId) && Objects.equals(intstanceId, other.intstanceId)
				&& Objects.equals(processId, other.processId);
	}

}
