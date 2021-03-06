package de.pawlidi.jbpm.rest.data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskComment implements Serializable {
	@JsonProperty("comment-id")
	private Long id;
	@JsonProperty("comment")
	private String text;
	@JsonProperty("comment-added-by")
	private String addedBy;
	@JsonProperty("comment-added-at")
	private LocalDateTime addedAt;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
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
	 * @param text
	 *            the text to set
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
	 * @param addedBy
	 *            the addedBy to set
	 */
	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	/**
	 * @return the addedAt
	 */
	public LocalDateTime getAddedAt() {
		return addedAt;
	}

	/**
	 * @param addedAt
	 *            the addedAt to set
	 */
	public void setAddedAt(LocalDateTime addedAt) {
		this.addedAt = addedAt;
	}

	@Override
	public int hashCode() {
		return Objects.hash(addedAt, addedBy, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof TaskComment)) {
			return false;
		}
		TaskComment other = (TaskComment) obj;
		return Objects.equals(addedAt, other.addedAt) && Objects.equals(addedBy, other.addedBy)
				&& Objects.equals(id, other.id);
	}

}
