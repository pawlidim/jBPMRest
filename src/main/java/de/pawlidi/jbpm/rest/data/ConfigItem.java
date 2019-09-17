/**
 * 
 */
package de.pawlidi.jbpm.rest.data;

import java.io.Serializable;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author PAWLIDIM
 *
 */
@XmlRootElement(name = "kie-server-config-item")
@XmlAccessorType(XmlAccessType.FIELD)
public class ConfigItem implements Serializable {

	@XmlElement(name = "itemName")
	private String name;
	@XmlElement(name = "itemValue")
	private String value;
	@XmlElement(name = "itemType")
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
