package de.pawlidi.jbpm.rest.data;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Containers implements Serializable {

	private String type;
	private String msg;
	private ContainersResult result;

	public Containers() {
		super();
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

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return the result
	 */
	public ContainersResult getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(ContainersResult result) {
		this.result = result;
	}

}
