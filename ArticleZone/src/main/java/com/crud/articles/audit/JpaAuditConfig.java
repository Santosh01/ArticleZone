/**
 * 
 */
package com.crud.articles.audit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author Santosh Dubey
 *
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class JpaAuditConfig {

	@Bean
	public AuditorAware<String> auditorAware() {
		return new AuditorWareImpl();

	}
}
