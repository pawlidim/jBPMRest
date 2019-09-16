package de.pawlidi.jbpm.rest.data;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author PAWLIDIM
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProcessDefinition implements Serializable {

	@JsonProperty("process-id")
	private String id;
	@JsonProperty("process-name")
	private String name;
	@JsonProperty("process-version")
	private String version;
	@JsonProperty("package")
	private String pkg;
	@JsonProperty("container-id")
	private String containerId;
	@JsonProperty("dynamic")
	private Boolean dynamic;
	@JsonFormat(with = JsonFormat.Feature.WRITE_SORTED_MAP_ENTRIES)
	private Map<String, Collection<String>> associatedEntities;
	@JsonFormat(with = JsonFormat.Feature.WRITE_SORTED_MAP_ENTRIES)
	private Map<String, String> processVariables;
	@JsonFormat(with = JsonFormat.Feature.WRITE_SORTED_MAP_ENTRIES)
	private Map<String, String> serviceTasks;

	public ProcessDefinition() {
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
	 * @return the pkg
	 */
	public String getPkg() {
		return pkg;
	}

	/**
	 * @param pkg the pkg to set
	 */
	public void setPkg(String pkg) {
		this.pkg = pkg;
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
	 * @return the dynamic
	 */
	public Boolean getDynamic() {
		return dynamic;
	}

	/**
	 * @param dynamic the dynamic to set
	 */
	public void setDynamic(Boolean dynamic) {
		this.dynamic = dynamic;
	}

	/**
	 * @return the associatedEntities
	 */
	public Map<String, Collection<String>> getAssociatedEntities() {
		return associatedEntities;
	}

	/**
	 * @param associatedEntities the associatedEntities to set
	 */
	public void setAssociatedEntities(Map<String, Collection<String>> associatedEntities) {
		this.associatedEntities = associatedEntities;
	}

	/**
	 * @return the processVariables
	 */
	public Map<String, String> getProcessVariables() {
		return processVariables;
	}

	/**
	 * @param processVariables the processVariables to set
	 */
	public void setProcessVariables(Map<String, String> processVariables) {
		this.processVariables = processVariables;
	}

	/**
	 * @return the serviceTasks
	 */
	public Map<String, String> getServiceTasks() {
		return serviceTasks;
	}

	/**
	 * @param serviceTasks the serviceTasks to set
	 */
	public void setServiceTasks(Map<String, String> serviceTasks) {
		this.serviceTasks = serviceTasks;
	}

	@Override
	public int hashCode() {
		return Objects.hash(containerId, id, name, pkg, version);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ProcessDefinition)) {
			return false;
		}
		ProcessDefinition other = (ProcessDefinition) obj;
		return Objects.equals(containerId, other.containerId) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(pkg, other.pkg)
				&& Objects.equals(version, other.version);
	}

}
