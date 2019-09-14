package de.pawlidi.jbpm.rest.data;

import java.io.Serializable;
import java.util.Objects;

import com.google.gson.annotations.SerializedName;

/**
 * 
 * @author pawlidim
 *
 */
public class Container implements Serializable {

	@SerializedName("container-id")
	private String id;
	@SerializedName("status")
	private String status;
	@SerializedName("container-alias")
	private String alias;

	public Container() {
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

	@Override
	public int hashCode() {
		return Objects.hash(alias, id, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Container)) {
			return false;
		}
		Container other = (Container) obj;
		return Objects.equals(alias, other.alias) && Objects.equals(id, other.id)
				&& Objects.equals(status, other.status);
	}

}
