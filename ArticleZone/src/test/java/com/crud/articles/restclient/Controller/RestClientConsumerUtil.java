/**
 * 
 */
package com.crud.articles.restclient.Controller;

import com.crud.articles.restclient.constants.RestClientConsumeConstants;

/**
 * @author Santosh Dubey
 * 
 * <p>This is common interface for Rest controller client. </p>
 *
 */
public interface RestClientConsumerUtil extends RestClientConsumeConstants{
	
	public void getAllArticles();
	public void addArticle();
	public void updateArticleDemo();
	public void deleteArticleDemo();

}
