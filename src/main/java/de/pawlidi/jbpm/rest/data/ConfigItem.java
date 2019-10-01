/**
 * 
 */
package de.pawlidi.jbpm.rest.data;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author PAWLIDIM
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfigItem implements Serializable {

	@JsonProperty("itemName")
	private String name;
	@JsonProperty("itemValue")
	private String value;
	@JsonProperty("itemType")
	private String type;

	protected ConfigItem() {
		super();
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
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, type, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ConfigItem)) {
			return false;
		}
		ConfigItem other = (ConfigItem) obj;
		return Objects.equals(name, other.name) && Objects.equals(type, other.type)
				&& Objects.equals(value, other.value);
	}

}
