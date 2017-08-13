/**
 * 
 */
package com.crud.articles.audit;

import org.springframework.data.domain.AuditorAware;

/**
 * @author Santosh Dubey
 *
 */
public class AuditorWareImpl implements AuditorAware<String> {

	private final String AUDITOR_NAME = "Santosh Dubey";

	@Override
	public String getCurrentAuditor() {
		return AUDITOR_NAME;
	}

}
