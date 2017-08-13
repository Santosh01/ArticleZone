package com.crud.articles.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.crud.articles.audit.AuditInfo;

/**
 * @author Santosh Dubey
 *         <p>
 *         This is JPA/Entity class for table mapping, this DTO will be updated
 *         if new column is added to the table.
 *         </p>
 *         <strong>Database: </strong>
 *         		<li>articlesdb</li> 
 *         <strong>Table: </strong>
 *         		<li>articles</li>
 * 
 *         <strong>Columns: </strong>
 *         <ul>
 *        		 <li>article_id</li>
 *         		<li>title</li>
 *         		<li>category</li>
 *         		<li>link</li>
 *         		<li>description</li>
 *         </ul>
 * 
 */
@Entity
@EnableJpaAuditing
@EntityListeners(AuditingEntityListener.class)
@Table(name = "articles")
public class ArticleBO extends AuditInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "article_id")
	private int articleId;

	@Column(name = "title")
	private String title;

	@Column(name = "category")
	private String category;

	@Column(name = "link")
	private String link;

	@Column(name = "description")
	private String description;

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @param link
	 *            the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}