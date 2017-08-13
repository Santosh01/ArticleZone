
package com.crud.articles.repository;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.crud.articles.bo.ArticleBO;
import com.crud.articles.bo.UtilitiesHQLQuery;

/**
 * @author Santosh Dubey
 *
 */
@Repository
public interface BaseJPARepository extends JpaRepository<ArticleBO, Serializable>, UtilitiesHQLQuery{

	@Query(value = QUERY_TO_GET_RECORDS_CONTAINING_KEYWORDS_IN_CATEGORY_FROM_ARTICLES, nativeQuery = false)
	public Page<ArticleBO> findCategoryRecordsByParam(@Param("category") String category, Pageable pageable);

}
