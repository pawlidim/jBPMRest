/**
 * 
 */
package de.pawlidi.jbpm.rest.data;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author PAWLIDIM
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Scanner implements Serializable {

	@JsonProperty("status")
	private ScannerStatus status;
	@JsonProperty("pollInterval")
	private Long pollInterval;

	/**
	 * 
	 */
	protected Scanner() {
		super();
	}

	/**
	 * @return the status
	 */
	public ScannerStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(ScannerStatus status) {
		this.status = status;
	}

	/**
	 * @return the pollInterval
	 */
	public Long getPollInterval() {
		return pollInterval;
	}

	/**
	 * @param pollInterval the pollInterval to set
	 */
	public void setPollInterval(Long pollInterval) {
		this.pollInterval = pollInterval;
	}

}
