/**
 * 
 */
package com.crud.articles.restclient.constants;

/**
 * @author Santosh Dubey
 * 
 * <p>This is common interface for web service, all new rest URI should be added here. </p>
 *
 */
public interface RestClientConsumeConstants {

	static String urlForGet = "http://localhost:8080/ArticleZone//find/{id}";
	static String urlForGetAll = "http://localhost:8080/ArticleZone/getAll";
	static String urlForPost = "http://localhost:8080/ArticleZone/add";
	static String urlForUpdate = "http://localhost:8080/ArticleZone/update";
	static String urlForDelete = "http://localhost:8080/ArticleZone/delete/{id}";
	
}
