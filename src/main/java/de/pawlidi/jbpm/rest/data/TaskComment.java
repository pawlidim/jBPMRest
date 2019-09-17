package de.pawlidi.jbpm.rest.data;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskComment implements Serializable {
	@JsonProperty("comment-id")
	private Long id;

	@JsonProperty("comment")
	private String text;

	@JsonProperty("comment-added-by")
	private String addedBy;

	@JsonProperty("comment-added-at")
	private Date addedAt;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the addedBy
	 */
	public String getAddedBy() {
		return addedBy;
	}

	/**
	 * @param addedBy the addedBy to set
	 */
	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	/**
	 * @return the addedAt
	 */
	public Date getAddedAt() {
		return addedAt;
	}

	/**
	 * @param addedAt the addedAt to set
	 */
	public void setAddedAt(Date addedAt) {
		this.addedAt = addedAt;
	}

}
