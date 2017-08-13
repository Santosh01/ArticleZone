/**
 * 
 */
package com.crud.articles.ExceptionHandlerAndValidation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Santosh Dubey
 *
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@RequestMapping(value = "article/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> deleteArticleException() {
	    throw new ForbiddenException();
	}
	
}
