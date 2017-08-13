/**
 * 
 */
package com.crud.articles.ExceptionHandlerAndValidation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Santosh Dubey
 *
 */

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Article not found")
public class ArticleNotFoundException extends Exception {
	private static final long serialVersionUID = 100L;
}
