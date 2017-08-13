/**
 * 
 */

var app = angular.module("MyApp", []);
app.controller("ArticleHttpController", function($scope, $http, $location, $filter, $interval) {

	$scope.paginationInput = "";
	$scope.pageCount = 0;
	$scope.size = 10;
	$scope.pagesList = [];
	var globalScopeVariable = this;

	$scope.getAll = function() {
		$http({
			method : 'get',
			url : '/ArticleZone/getAll'
		}).then(function(response) {
			$scope.data = response.data;
		}, function(error) {
			console.log(error, 'can not get data.');
		});
	};



	$scope.createArticle = function() {
		var formData = $scope.getFormValueForCreate();
		$http({
			method : 'post',
			url : '/ArticleZone/add',
			data : JSON.stringify(formData),
			headers : {
				'Content-Type' : 'application/json;charset=utf-8;'
			}
		}).then(function(response) {
			$scope.pagesList.push(formData);
			console.log(response);
		}, function(error) {
			console.log(error, 'Status code: ' + error.status + ' Article not saved');
		});
	};


	$scope.deleteArticle = function(articleId) {
		$http({
			method : 'delete',
			url : '/ArticleZone/delete/' + articleId,
			headers : {
				'Content-Type' : 'application/json;charset=utf-8'
			}
		}).then(function(response) {
			console.log(response);
		}, function(error) {
			console.log(error, 'Status code: ' + error.status + ', Article-ID: ' + articleId + ' - Article not found');
		});
		setTimeout(function() {
			$scope.getCategoryPagination();
		}, 20)
	};


	$scope.getFormValueForCreate = function() {
		var formData = {
			title : $scope.titles,
			category : $scope.category.name,
			link : $scope.link,
			description : $scope.description
		};
		return formData;
	};

	$scope.getCategoryPagination = function() {
		$http({
			method : 'get',
			url : '/ArticleZone/articlePaginated?category=' + $scope.paginationInput + '&page=' + $scope.pageCount + '&size=' + $scope.size
		}).then(function(response) {

			$scope.paginationData = response.data;
			console.log($scope.paginationData);
			$scope.pagesList = new Array(response.data.totalPages);
		}, function(error) {
			console.log(error, 'can not get data.');
		});
	};

	$scope.navigateToPageIndex = function(index) {
		$scope.pageCount = index;
		$scope.getCategoryPagination();
	};




	$scope.getFormValueForUpdate = function() {
		var formData = {
			articleId : globalScopeVariable.articleIdUpdate,
			title : globalScopeVariable.titleUpdate,
			category : globalScopeVariable.categoryUpdate.name,
			link : globalScopeVariable.linkUpdate,
			description : globalScopeVariable.descriptionUpdate
		};
		return formData;
	};

	globalScopeVariable.updateArticle = function(articleId) {
		var formData = $scope.getFormValueForUpdate();
		$http({
			method : 'put',
			url : '/ArticleZone/update',
			data : JSON.stringify(formData),
		}).then(function(response) {
			console.log(response);
		}, function(error) {
			console.log(error, 'Status code: ' + error.status + ' , Article-ID: ' + formData.articleId + ' - Article not updated');
		});
	};


	/*
	 * 
	 * 
	 * 
	 * */
	function searchArticleByID(articleId) {
		var result = $filter('filter')($scope.paginationData.content, {
			articleId : articleId
		})[0];
		return result;
	}
	;

	$scope.setValueOnUpdateModal = function(articleId) {
		var formBean = searchArticleByID(articleId);

		globalScopeVariable.articleIdUpdate = formBean.articleId;
		globalScopeVariable.titleUpdate = formBean.title;
		var category = formBean.category;
		console.log(category);
		globalScopeVariable.linkUpdate = formBean.link;
		globalScopeVariable.descriptionUpdate = formBean.description;
		var count = 0;

		angular.forEach($scope.categoryDropdownOptions, function(item) {
			console.log(category);
			if (item.name == category) {
				globalScopeVariable.categoryUpdate = $scope.categoryDropdownOptions[count];
			} else {
				count++;
			}
		});
	};

	
	function _clearUpdateDataModal() {
		$scope.titleUpdate = "";
		$scope.linkUpdate = "";
		$scope.categoryUpdate = "";
		$scope.descriptionUpdate = "";
	}
	;

	$scope._clearCreateDataModal = function() {
		$scope.titles = "";
		$scope.link = "";
		$scope.category = "";
		$scope.description = "";
	};

	/*
	 *
	 * 
	 * 
	 * */
	$scope.categoryDropdownOptions = [
		{
			code : 'DB',
			name : 'Database/NoSQL'
		},
		{
			code : 'F-Spring',
			name : 'Framework-Spring'
		},
		{
			code : 'F-Struts',
			name : 'Framework-Struts'
		},
		{
			code : 'Micro',
			name : 'Microservices'
		},
		{
			code : 'DEVOps-AWS',
			name : 'AWS'
		},
		{
			code : 'DEVOps-Docker',
			name : 'Docker'
		},
		{
			code : 'F-Angular',
			name : 'Framework-Angular'
		},
		{
			code : 'F-CSS',
			name : 'Framework-Bootstrap'
		},
		{
			code : 'F-JS',
			name : 'Framework-Javascript'
		},
		{
			code : 'JPA',
			name : 'Java Persistance API'
		},
		{
			code : 'VC',
			name : 'Version Control'
		},
		{
			code : 'JAVA',
			name : 'Java/J2EE'
		},
		{
			code : 'Cert',
			name : 'Certifications'
		},
		{
			code : 'Office',
			name : 'Office'
		},
		{
			code : 'Online',
			name : 'Online informative Blog/Articles'
		},
		{
			code : 'Books',
			name : 'Books'
		},
		{
			code : 'Oth',
			name : 'Others'
		}

	];

});