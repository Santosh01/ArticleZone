package com.crud.articles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * <p>
 * Main method need to be in parents directory example:
 * if controller is in <b>com.crud.articles.controller</b> then main method need to be in
 *  <b>com.crud.articles</b> package so that sprig boot can scan the package of the controller
 * </p>
 * 
 * 
 * */

@SpringBootApplication
public class ArticleZone {

	public static void main(String[] args) {
		SpringApplication.run(ArticleZone.class, args);
	}
}
