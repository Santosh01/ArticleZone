/**
 * 
 */
package com.crud.articles.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crud.articles.bo.ArticleBO;
import com.crud.articles.bo.UtilitiesHQLQuery;
import com.crud.articles.repository.BaseJPARepository;

/**
 * @author Santosh Dubey
 *
 */

@Service("articleService")
@Transactional
public class ArticleDAOImpl implements ArticleDAO, UtilitiesHQLQuery {

	@Autowired
	private BaseJPARepository baseArticleJPARepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public ArticleBO getArticleById(int articleId) {
		return baseArticleJPARepository.findOne(articleId);
	}
	

	@Override
	public List<ArticleBO> getAllArticles() {
		return baseArticleJPARepository.findAll();
	}
	
	@SuppressWarnings("unchecked")
	public List<ArticleBO> getAllArticlesByQuery() {
		String hql = QUERY_TO_GET_ALL_RECORDS_FROM_ARTICLES;
		return (List<ArticleBO>) entityManager.createQuery(hql).getResultList();
	}

	@Override
	public void addArticle(ArticleBO articleBO) {
			baseArticleJPARepository.save(articleBO);
	}

	@Override
	public void updateArticleBO(ArticleBO articleBO) {
		entityManager.merge(articleBO);
	}

	@Override
	public void deleteArticleById(int articleId) {
		baseArticleJPARepository.delete(articleId);

	}

	@Override
	public boolean isExistsByID(int Id) {
		return baseArticleJPARepository.exists(Id);
	}
	
	public boolean isArticleExistsByTitleAndCategory(String title, String category) {
		String hql = QUERY_TO_GET_RECORDS_FROM_TITLE_CATEGORY_FROM_ARTICLE;
		int count = entityManager.createQuery(hql).setParameter(1, title).setParameter(2, category).getResultList()
				.size();
		return count > 0 ? true : false;
	}


	@Override
	public Page<ArticleBO> findCategoryRecordsByParam(String category, int page, int size) {
		Page<ArticleBO> categoryRecordPaginatedData = baseArticleJPARepository.findCategoryRecordsByParam(category, new PageRequest(page, size));
		return categoryRecordPaginatedData;
	}


}
