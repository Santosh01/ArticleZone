package com.crud.articles;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.crud.articles.restclient.Controller.RestClientConsumerUtilImpl;

/**
 *@author Santosh Dubey
 *<p>This is runner class to test rest controller test class.</p> 
 * 
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleZoneTests {

	@Test
	public void contextLoads() {
	}

	public static void main(String[] args) {

		RestClientConsumerUtilImpl restClientUtil = new RestClientConsumerUtilImpl();

//		restClientUtil.addArticle();
		
		restClientUtil.getAllArticles();
	}

}
