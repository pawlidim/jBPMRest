package de.pawlidi.jbpm.rest.data;

/**
 * 
 * @author pawlidim
 *
 */
public enum ProcessInstanceStatus {

	Pending(0), Active(1), Completed(2), Aborted(3), Suspended(4);

	private final int status;

	private ProcessInstanceStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

}
