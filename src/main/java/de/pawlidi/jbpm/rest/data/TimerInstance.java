package de.pawlidi.jbpm.rest.data;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * 
 * @author PAWLIDIM
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TimerInstance implements Serializable {

	@JsonProperty("id")
	private Long id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("activation-time")
	private LocalDateTime activationTime;
	@JsonProperty("last-fire-time")
	private LocalDateTime lastFireTime;
	@JsonProperty("next-fire-time")
	private LocalDateTime nextFireTime;
	@JsonProperty("delay")
	private Long delay;
	@JsonProperty("period")
	private Long period;
	@JsonProperty("repeat-limit")
	private Integer repeatLimit;
	@JsonProperty("process-instance-id")
	private Long processInstance;
	@JsonProperty("session-id")
	private Long sessionId;

	public TimerInstance() {
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
	 * @return the lastFireTime
	 */
	public LocalDateTime getLastFireTime() {
		return lastFireTime;
	}

	/**
	 * @param lastFireTime
	 *            the lastFireTime to set
	 */
	public void setLastFireTime(LocalDateTime lastFireTime) {
		this.lastFireTime = lastFireTime;
	}

	/**
	 * @return the nextFireTime
	 */
	public LocalDateTime getNextFireTime() {
		return nextFireTime;
	}

	/**
	 * @param nextFireTime
	 *            the nextFireTime to set
	 */
	public void setNextFireTime(LocalDateTime nextFireTime) {
		this.nextFireTime = nextFireTime;
	}

	/**
	 * @return the delay
	 */
	public Long getDelay() {
		return delay;
	}

	/**
	 * @param delay
	 *            the delay to set
	 */
	public void setDelay(Long delay) {
		this.delay = delay;
	}

	/**
	 * @return the period
	 */
	public Long getPeriod() {
		return period;
	}

	/**
	 * @param period
	 *            the period to set
	 */
	public void setPeriod(Long period) {
		this.period = period;
	}

	/**
	 * @return the repeatLimit
	 */
	public Integer getRepeatLimit() {
		return repeatLimit;
	}

	/**
	 * @param repeatLimit
	 *            the repeatLimit to set
	 */
	public void setRepeatLimit(Integer repeatLimit) {
		this.repeatLimit = repeatLimit;
	}

	/**
	 * @return the processInstance
	 */
	public Long getProcessInstance() {
		return processInstance;
	}

	/**
	 * @param processInstance
	 *            the processInstance to set
	 */
	public void setProcessInstance(Long processInstance) {
		this.processInstance = processInstance;
	}

	/**
	 * @return the sessionId
	 */
	public Long getSessionId() {
		return sessionId;
	}

	/**
	 * @param sessionId
	 *            the sessionId to set
	 */
	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}

}
