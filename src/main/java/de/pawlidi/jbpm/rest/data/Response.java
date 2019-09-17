package de.pawlidi.jbpm.rest.data;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "response")
@XmlType(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class Response implements Serializable {

	private String msg;
	private ResponseType type;
//	@XmlAnyElement(lax = true)
//	protected Object result;

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
	 * @return the type
	 */
	public ResponseType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(ResponseType type) {
		this.type = type;
	}
//
//	/**
//	 * @return the result
//	 */
//	public Object getResult() {
//		return result;
//	}
//
//	/**
//	 * @param result the result to set
//	 */
//	public void setResult(Object result) {
//		this.result = result;
//	}

}
