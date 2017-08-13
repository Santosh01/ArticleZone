package com.crud.articles.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.crud.articles.bo.ArticleBO;
import com.crud.articles.service.ArticleService;

/**
 * @author Santosh Dubey
 *         <p>
 *         This is REST Controller class responsible for performing all
 *         <b>CRUD</b> operation.
 *         </p>
 *         <b>Rest URI <code>/ArticleZone</code> used on all functions:</b>
 *         <ul>
 *         		<li><b>GET by ID</b>: <code> /find/{id} OR /search/{id} </code></li>
 *         		<li><b>GET All records</b>: <code> /getAll OR /all  </code></li>
 *         		<li><b>GET by Category</b>:
 *         		<code> /articlePaginated OR /paginated </code></li>
 *         		<li><b>POST</b>: <code> /add OR /addNewArticle </code></li>
 *         		<li><b>PUT</b>: <code> /update OR /modify </code></li>
 *         		<li><b>DELETE</b>: <code> /delete/{id} OR /remove/{id} </code></li>
 *
 *         </ul>
 */

@RestController
@RequestMapping("/ArticleZone")
public class ProcessArticlesControllerAction {

	@Autowired
	ArticleService articleService;

	/**
	 * <p>
	 * This method is of GET type which retrieves article BO based on the
	 * provided <b>Article ID</b> and produces results in: <b>JSON or XML</b>
	 * format.
	 * </p>
	 * 
	 * <strong>Mapping: </strong>
	 * <ul>
	 * 		<li>/find/{id}</li>
	 * 		<li>/search/{id}</li>
	 * </ul>
	 * 
	 * @param id
	 * @return <code>ResponseEntity object containing Article BO with HTTP status code </code>
	 */
	@RequestMapping(value = { "/find/{id}", "/search/{id}" }, method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE }, consumes = { "application/*",
					"text/*" }, headers = { "content-type=application/*" })
	public ResponseEntity<ArticleBO> getArticleById(@RequestBody @PathVariable("id") Integer id) {
		if (articleService.isExistsByID(id)) {
			ArticleBO articleBO = articleService.getArticleById(id);
			return new ResponseEntity<ArticleBO>(articleBO, HttpStatus.OK);
		} else {
			return new ResponseEntity<ArticleBO>(HttpStatus.NOT_FOUND);
		}

	}

	/**
	 * <p>
	 * This method is of GET type and retrieve all records from Database and
	 * produces results in: <b>JSON or XML</b> format.
	 * </p>
	 * <strong>Mapping: </strong>
	 * <ul>
	 * 		<li>/getAll</li>
	 *		 <li>/all</li>
	 * </ul>
	 * 
	 * 
	 * @return <code>ResponseEntity object containing List of Article BO with HTTP status code </code>
	 */
	@RequestMapping(value = { "/getAll", "/all" }, method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE }, consumes = { "application/*",
					"text/*" }, headers = { "content-type=text/*" })
	public ResponseEntity<List<ArticleBO>> getAllArticles() {
		List<ArticleBO> list = articleService.getAllArticles();
		return new ResponseEntity<List<ArticleBO>>(list, HttpStatus.OK);
	}

	/**
	 * <p>
	 * This method is of GET type and retrieves records from Database based on
	 * the provided parameter. This method has been used in
	 * <b>Pagination/Search</b> where By default page size is set to 10 and
	 * Search is based on the the category name.
	 * </p>
	 * 
	 * <strong>@RequestParam:</strong>
	 * <ul>
	 * 		<li>page number : 0</li>
	 * 		<li>Page Size : 10</li>
	 * 		<li>Category : empty</li>
	 * </ul>
	 * <strong>Mapping: </strong>
	 * <ul>
	 *		 <li>/articlePaginated</li>
	 * 		<li>/paginated</li>
	 * </ul>
	 * 
	 * @param
	 * @return <code>Page<ArticleBO> </code>
	 */
	@GetMapping({ "/articlePaginated", "/paginated" })
	public Page<ArticleBO> findCategoryRecordsByParam(
			@RequestParam(name = "category", defaultValue = "") String category,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		Page<ArticleBO> paginatedTitleRecord = articleService.findCategoryRecordsByParam(category, page, size);
		return paginatedTitleRecord;

	}

	/**
	 * <p>
	 * This method is of POST type and inserts record in Database.
	 * </p>
	 * 
	 * <strong>@RequestBody:</strong>
	 * <ul>
	 * 		<li>ArticleBO</li>
	 * 		<li>UriComponentsBuilder</li>
	 * </ul>
	 * 
	 * <strong>Mapping: </strong>
	 * <ul>
	 * 		<li>/add</li>
	 * 		<li>/addNewArticle</li>
	 * </ul>
	 * 
	 * @param
	 * @return ResponseEntity<Void>(HttpHeaders, HttpStatus.CREATED)
	 */
	@RequestMapping(value = { "/add", "/addNewArticle" }, method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE }, consumes = { "application/*",
					"text/*" }, headers = { "content-type=text/*" })
	public ResponseEntity<Void> addArticle(@RequestBody ArticleBO articleBO, UriComponentsBuilder builder) {
		if (articleBO != null) {
			boolean flag = articleService.addArticleIfNotExists(articleBO);
			if (flag == false) {
				return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			}
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("/find/{id}").buildAndExpand(articleBO.getArticleId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		} else {

			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * <p>
	 * This method is of PUT type and updates record in Database.
	 * </p>
	 * 
	 * <strong>@RequestBody:</strong>
	 * <ul>
	 * 		<li>ArticleBO</li>
	 * </ul>
	 * 
	 * <strong>Mapping: </strong>
	 * <ul>
	 * 		<li>/update</li>
	 * 		<li>/modify</li>
	 * </ul>
	 * 
	 * @param
	 * @return ResponseEntity<String>(HttpStatus.OK)
	 */
	@PutMapping({ "/update", "/modify" })
	@ResponseBody
	public ResponseEntity<String> updateArticleBO(@RequestBody ArticleBO articleBO) {
		articleService.updateArticleBO(articleBO);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	/**
	 * <p>
	 * 	This method is of DELETE type and deletes record in Database.
	 * </p>
	 * 
	 * <strong>Mapping: </strong>
	 * <ul>
	 * 		<li>/delete/{id}</li>
	 * 		<li>/remove/{id}</li>
	 * </ul>
	 * 
	 * @param
	 * @return ResponseEntity<String>(HttpStatus.OK)
	 */
	@RequestMapping(value = { "/delete/{id}", "/remove/{id}" }, method = RequestMethod.DELETE, consumes = {
			"application/*", "text/*" }, headers = { "content-type=text/*" })
	public ResponseEntity<String> deleteArticleById(@PathVariable("id") int articleId) {
		if (articleService.isExistsByID(articleId)) {
			articleService.deleteArticleById(articleId);
			return new ResponseEntity<String>(HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}

	}

}
