/**
 * 功能说明：规格项目服务层
 */
app.service('tbSpecificationOptionService', function($http) {
	// 功能说明：根据主键进行查找
	this.findOne = function(id) {
		return $http.get(adminConfig.apiServer + '/tbSpecificationOption/findOne.do?id=' + id);
	};
	
	// 功能说明：查找所有对象列表
	this.findAll = function() {
		return $http.get(adminConfig.apiServer + '/tbSpecificationOption/findAll.do');
	};
	
	// 功能说明：根据列表进行查找
	this.findList = function(ids) {
		return $http.get(adminConfig.apiServer + '/tbSpecificationOption/findList.do?ids=' + ids);
	};
	
	// 功能说明：根据主键进行删除
	this.deleteOne = function(id) {
		return $http.get(adminConfig.apiServer + '/tbSpecificationOption/deleteOne.do?id=' + id);
	};
	
	// 功能说明：删除所有对象列表
	this.deleteAll = function() {
		return $http.get(adminConfig.apiServer + '/tbSpecificationOption/deleteAll.do');
	};
	
	// 功能说明：根据列表进行删除
	this.deleteList = function(ids) {
		return $http.get(adminConfig.apiServer + '/tbSpecificationOption/deleteList.do?ids=' + ids);
	};
	
	// 功能说明：更新对象
	this.update = function(entity) {
		return $http.post(adminConfig.apiServer + '/tbSpecificationOption/update.do', entity);
	};
	
	// 功能说明：添加对象
	this.add = function(entity) {
		return $http.post(adminConfig.apiServer + '/tbSpecificationOption/add.do', entity);
	};
	
	// 功能说明：分页查询
	this.search = function(pageNum, pageSize, searchEntity) {
		return $http.post(adminConfig.apiServer + '/tbSpecificationOption/search.do?pageNum=' + pageNum + "&pageSize=" + pageSize, searchEntity);
	};
});
