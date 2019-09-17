/**
 * 
 */
package de.pawlidi.jbpm.rest.data;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author PAWLIDIM
 *
 */
@XmlRootElement(name = "kie-scanner")
@XmlAccessorType(XmlAccessType.FIELD)
public class Scanner implements Serializable {

	@XmlElement(name = "status")
	private ScannerStatus status;
	@XmlElement(name = "pollInterval")
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
