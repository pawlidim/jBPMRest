/**
 * 
 */
package de.pawlidi.jbpm.rest.data;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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

	@JsonProperty("process-instance-id")
	private String intstanceId;
	@JsonProperty("process-id")
	private String processId;
	@JsonProperty("process-name")
	private String name;
	@JsonProperty("process-version")
	private String version;
	@JsonProperty("process-instance-state")
	private ProcessInstanceStatus status;
	@JsonProperty("container-id")
	private String containerId;
	@JsonProperty("initiator")
	private String initiator;
	// @JsonProperty("start-date")
	private Date startDate;
	@JsonProperty("process-instance-desc")
	private String description;
	@JsonProperty("correlation-key")
	private String correlationKey;
	@JsonProperty("parent-instance-id")
	private String parentInstanceId;
	@JsonProperty("active-user-tasks")
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	private Collection<Taks> tasks;
	@JsonProperty("process-instance-variables")
	@JsonFormat(with = JsonFormat.Feature.WRITE_SORTED_MAP_ENTRIES)
	private Map<String, String> variables;

	/**
	 * 
	 */
	public ProcessInstance() {
		super();
	}

	/**
	 * @return the intstanceId
	 */
	public String getIntstanceId() {
		return intstanceId;
	}

	/**
	 * @param intstanceId the intstanceId to set
	 */
	public void setIntstanceId(String intstanceId) {
		this.intstanceId = intstanceId;
	}

	/**
	 * @return the processId
	 */
	public String getProcessId() {
		return processId;
	}

	/**
	 * @param processId the processId to set
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
	 * @param name the name to set
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
	 * @param version the version to set
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
	 * @param state the state to set
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
	 * @param containerId the containerId to set
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
	 * @param initiator the initiator to set
	 */
	public void setInitiator(String initiator) {
		this.initiator = initiator;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
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
	 * @param correlationKey the correlationKey to set
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
	 * @param parentInstanceId the parentInstanceId to set
	 */
	public void setParentInstanceId(String parentInstanceId) {
		this.parentInstanceId = parentInstanceId;
	}

	/**
	 * @return the tasks
	 */
	public Collection<Taks> getTasks() {
		return tasks;
	}

	/**
	 * @param tasks the tasks to set
	 */
	public void setTasks(Collection<Taks> tasks) {
		this.tasks = tasks;
	}

	/**
	 * @return the variables
	 */
	public Map<String, String> getVariables() {
		return variables;
	}

	/**
	 * @param variables the variables to set
	 */
	public void setVariables(Map<String, String> variables) {
		this.variables = variables;
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
