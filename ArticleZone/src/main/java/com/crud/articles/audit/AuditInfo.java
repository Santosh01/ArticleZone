/**
 * 
 */
package com.crud.articles.audit;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author Santosh Dubey
 *         <p>
 *         This is JPA/Entity class is for audit column mapping.
 *         </p>
 *         <strong>Columns: </strong>
 *         <ul>
 *         		<li>created_date</li>
 *         		<li>created_by</li>
 *         		<li>updated_date</li>
 *         		<li>updated_by</li>
 *         </ul>
 * 
 */

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class AuditInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5470956210011715732L;

	@Column(name = "created_date", insertable = true, updatable = false)
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;

	@Column(name = "created_by", insertable = true, updatable = false)
	@CreatedBy
	private String createdBy;

	@Column(name = "updated_date", insertable = false, updatable = true)
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedOn;

	@Column(name = "updated_by", insertable = false, updatable = true)
	@LastModifiedBy
	private String updatedBy;

	/**
	 * @return the createdOn
	 */
	public Date getCreatedOn() {
		return createdOn;
	}

	/**
	 * @param createdOn
	 *            the createdOn to set
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the updatedOn
	 */
	public Date getUpdatedOn() {
		return updatedOn;
	}

	/**
	 * @param updatedOn
	 *            the updatedOn to set
	 */
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	/**
	 * @return the updatedBy
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy
	 *            the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

}
