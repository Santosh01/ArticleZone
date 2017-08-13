/**
 * 
 */
package com.crud.articles.restclient.Controller;

import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.crud.articles.bo.ArticleBO;

/**
 * @author Santosh Dubey
 * 
 * <p>This rest controller class have been implemented to consume rest web service response</p>
 *
 */

public class RestClientConsumerUtilImpl implements RestClientConsumerUtil{

	private ArticleBO articleBO;
	private RestTemplate restTemplate;
	private HttpHeaders headers;
	private HttpEntity<String> requestEntityString;
	private HttpEntity<ArticleBO> requestEntityArticleBO;
	private ResponseEntity<ArticleBO[]> responseEntityArray;
	private ResponseEntity<ArticleBO> responseEntity;

	public void getArticleById() {
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		restTemplate = new RestTemplate();
		requestEntityString = new HttpEntity<String>(headers);
		responseEntity = restTemplate.exchange(urlForGet, HttpMethod.GET, requestEntityString, ArticleBO.class, 1);
		articleBO = responseEntity.getBody();
		System.out.println("Id:" + articleBO.getArticleId() + ", Title:" + articleBO.getTitle() + ", Category:"
				+ articleBO.getCategory());
	}

	public void getAllArticles() {
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		restTemplate = new RestTemplate();
		requestEntityString = new HttpEntity<String>(headers);
		responseEntityArray = restTemplate.exchange(urlForGetAll, HttpMethod.GET, requestEntityString, ArticleBO[].class);
		ArticleBO[] articlesArray = responseEntityArray.getBody();
		for (ArticleBO articleBO : articlesArray) {
			System.out.println("Id:" + articleBO.getArticleId() + ", Title:" + articleBO.getTitle() + ", Category: "
					+ articleBO.getCategory());
		}
	}

	public void addArticle() {
    	headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        restTemplate = new RestTemplate();
        articleBO = new ArticleBO();
        articleBO.setTitle("test3");
        articleBO.setCategory("Spring-BootTest3");
        requestEntityArticleBO = new HttpEntity<ArticleBO>(articleBO, headers);
        URI uri = restTemplate.postForLocation(urlForPost, requestEntityArticleBO);
        System.out.println(uri.getPath());    	
    }
	
    public void updateArticleDemo() {
    	headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        restTemplate = new RestTemplate();
        articleBO = new ArticleBO();
        articleBO.setArticleId(1);
        articleBO.setTitle("Update:Java Concurrency");
        articleBO.setCategory("Java");
        requestEntityArticleBO = new HttpEntity<ArticleBO>(articleBO, headers);
        restTemplate.put(urlForUpdate, requestEntityArticleBO);
    }
    
    public void deleteArticleDemo() {
    headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        restTemplate = new RestTemplate();
        requestEntityArticleBO = new HttpEntity<ArticleBO>(headers);
        restTemplate.exchange(urlForDelete, HttpMethod.DELETE, requestEntityArticleBO, Void.class, 4);        
    }	
	

}
