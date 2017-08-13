/**
 * 
 */
package com.crud.articles.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.crud.articles.bo.ArticleBO;
import com.crud.articles.dao.ArticleDAO;

/**
 * @author Santosh Dubey
 *
 */

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	ArticleDAO articleDAO;

	/*
	 * 
	 * 
	 * @see com.crud.articles.service.ArticleService#getArticleById(int)
	 */
	@Override
	public ArticleBO getArticleById(int articleId) {
		ArticleBO articleBO = articleDAO.getArticleById(articleId);
		return articleBO;
	}

	/*
	 * 
	 * 
	 * @see com.crud.articles.service.ArticleService#getAllArticles()
	 */
	@Override
	public List<ArticleBO> getAllArticles() {
		List<ArticleBO> articleBOList = articleDAO.getAllArticles();
		return articleBOList;
	}

	/*
	 * 
	 * 
	 * @see
	 * com.crud.articles.service.ArticleService#addArticle(com.crud.articles.bo.
	 * ArticleBO)
	 */

	public synchronized boolean addArticleIfNotExists(ArticleBO articleBO) {
		if (articleDAO.isArticleExistsByTitleAndCategory(articleBO.getTitle(), articleBO.getCategory())) {
			return false;
		} else {
			articleDAO.addArticle(articleBO);
			return true;
		}
	}

	@Override
	public synchronized void addArticle(ArticleBO articleBO) {
		if (articleDAO.isArticleExistsByTitleAndCategory(articleBO.getTitle(), articleBO.getCategory()) == false) {

			articleDAO.addArticle(articleBO);
		}

	}

	/*
	 * 
	 * 
	 * @see com.crud.articles.service.ArticleService#updateArticleBO(com.crud.
	 * articles.bo.ArticleBO)
	 */
	@Override
	public void updateArticleBO(ArticleBO articleBO) {
		articleDAO.updateArticleBO(articleBO);

	}

	/*
	 * 
	 * 
	 * @see com.crud.articles.service.ArticleService#deleteArticleById(int)
	 */
	@Override
	public void deleteArticleById(int articleId) {
		articleDAO.deleteArticleById(articleId);

	}

	/*
	 * 
	 * 
	 * @see com.crud.articles.service.ArticleService#
	 * isArticleExistsByTitleAndCategory(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isArticleExistsByTitleAndCategory(String title, String category) {
		boolean isExists = articleDAO.isArticleExistsByTitleAndCategory(title, category);
		return isExists;
	}

	/*
	 * 
	 * 
	 * @see com.crud.articles.service.ArticleService#isExistsByID(int)
	 */
	@Override
	public boolean isExistsByID(int Id) {
		boolean isExists = articleDAO.isExistsByID(Id);
		return isExists;
	}

	/*
	 * 
	 * 
	 * @see com.crud.articles.service.ArticleService#getAllArticlesByQuery()
	 */
	@Override
	public List<ArticleBO> getAllArticlesByQuery() {
		List<ArticleBO> articleBOList = articleDAO.getAllArticlesByQuery();
		return articleBOList;
	}

	@Override
	public Page<ArticleBO> findCategoryRecordsByParam(String category, int page, int size) {
		return articleDAO.findCategoryRecordsByParam(category, page, size);
	}

}
