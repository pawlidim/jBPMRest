package de.pawlidi.jbpm.rest.data;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author pawlidim
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContainerData implements Serializable {

	@JsonProperty("container-id")
	private String id;
	@JsonProperty("status")
	private String status;
	@JsonProperty("container-alias")
	private String alias;
	@JsonProperty("release-id")
	private ContainerRelease release;
	@JsonProperty("resolved-release-id")
	private ContainerRelease resolvedRelease;

	public ContainerData() {
		super();
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * @param alias the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * @return the release
	 */
	public ContainerRelease getRelease() {
		return release;
	}

	/**
	 * @param release the release to set
	 */
	public void setRelease(ContainerRelease release) {
		this.release = release;
	}

	/**
	 * @return the resolvedRelease
	 */
	public ContainerRelease getResolvedRelease() {
		return resolvedRelease;
	}

	/**
	 * @param resolvedRelease the resolvedRelease to set
	 */
	public void setResolvedRelease(ContainerRelease resolvedRelease) {
		this.resolvedRelease = resolvedRelease;
	}

	@Override
	public int hashCode() {
		return Objects.hash(alias, id, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ContainerData)) {
			return false;
		}
		ContainerData other = (ContainerData) obj;
		return Objects.equals(alias, other.alias) && Objects.equals(id, other.id)
				&& Objects.equals(status, other.status);
	}
}
