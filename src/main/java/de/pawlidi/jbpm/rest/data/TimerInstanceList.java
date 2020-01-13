package de.pawlidi.jbpm.rest.data;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author PAWLIDIM
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TimerInstanceList implements Serializable {

	@JsonProperty("timer-instance")
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	private List<TimerInstance> timerInstances;

	public TimerInstanceList() {
		super();
	}

	/**
	 * @return the timerInstances
	 */
	public List<TimerInstance> getTimerInstances() {
		return timerInstances;
	}

	/**
	 * @param timerInstances
	 *            the timerInstances to set
	 */
	public void setTimerInstances(List<TimerInstance> timerInstances) {
		this.timerInstances = timerInstances;
	}

}
