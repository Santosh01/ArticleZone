/**
 * 
 */
package com.crud.articles.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.crud.articles.bo.ArticleBO;

/**
 * @author Santosh Dubey
 *
 */
public interface ArticleDAO {
	
	public ArticleBO getArticleById(int articleId);
	public List<ArticleBO> getAllArticles();
	public void addArticle(ArticleBO articleBO);
	public void updateArticleBO(ArticleBO articleBO);
	public void deleteArticleById(int articleId);
	public boolean isArticleExistsByTitleAndCategory(String title, String category);
	public boolean isExistsByID(int Id);
	public List<ArticleBO> getAllArticlesByQuery();
	public Page<ArticleBO> findCategoryRecordsByParam(String category, int page, int size);
}
