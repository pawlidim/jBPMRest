package de.pawlidi.jbpm.rest.data;

public enum TaskStatus {
	Created("Created"), Ready("Ready"), Reserved("Reserved"), InProgress("InProgress"), Suspended(
			"Suspended"), Completed(
					"Completed"), Failed("Failed"), Error("Error"), Exited("Exited"), Obsolete("Obsolete");

	private final String name;

	TaskStatus(final String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

}
