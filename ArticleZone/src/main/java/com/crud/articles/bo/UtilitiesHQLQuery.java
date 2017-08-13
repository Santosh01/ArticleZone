/**
 * 
 */
package com.crud.articles.bo;

/**
 * @author Santosh Dubey
 *
 *<p>This is common interface for all HQL or SQL queries used other DAO action classes.
 *All future queries will be added here. </p>
 *
 */
public interface UtilitiesHQLQuery {

	static final String QUERY_TO_GET_ALL_RECORDS_FROM_ARTICLES = "SELECT atcl FROM ArticleBO atcl";
	static final String QUERY_TO_GET_ARTICLEBO_FROM_ARTICLES = "FROM ArticleBO as atcl";

	static final String QUERY_TO_GET_RECORDS_CONTAINING_KEYWORDS_IN_CATEGORY_FROM_ARTICLES = QUERY_TO_GET_ALL_RECORDS_FROM_ARTICLES
			+ " WHERE atcl.category LIKE lower('%' || :category || '%')";
	static final String QUERY_TO_GET_RECORDS_FROM_TITLE_CATEGORY_FROM_ARTICLE = "FROM ArticleBO as atcl WHERE atcl.title = ? and atcl.category = ?";

}
