package de.pawlidi.jbpm.rest.data;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author pawlidim
 *
 */
public class ContainerRelease implements Serializable {

	@JsonProperty("group-id")
	private String groupId;
	@JsonProperty("artifact-id")
	private String artefactId;
	@JsonProperty("version")
	private String version;

	public ContainerRelease() {
		super();
	}

	/**
	 * @return the groupId
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the artefactId
	 */
	public String getArtefactId() {
		return artefactId;
	}

	/**
	 * @param artefactId the artefactId to set
	 */
	public void setArtefactId(String artefactId) {
		this.artefactId = artefactId;
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

	@Override
	public int hashCode() {
		return Objects.hash(artefactId, groupId, version);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ContainerRelease)) {
			return false;
		}
		ContainerRelease other = (ContainerRelease) obj;
		return Objects.equals(artefactId, other.artefactId) && Objects.equals(groupId, other.groupId)
				&& Objects.equals(version, other.version);
	}

}
