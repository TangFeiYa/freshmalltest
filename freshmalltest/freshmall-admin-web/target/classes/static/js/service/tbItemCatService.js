/**
 * 功能说明：商品类目服务层
 */
app.service('tbItemCatService', function($http) {
	// 功能说明：根据主键进行查找
	this.findOne = function(id) {
		if(id == undefined){
			id = -1;
		}
		return $http.get(adminConfig.apiServer + '/tbItemCat/findOne.do?id=' + id);
	};
	
	// 功能说明：查找所有对象列表
	this.findAll = function() {
		return $http.get(adminConfig.apiServer + '/tbItemCat/findAll.do');
	};
	
	// 功能说明：根据列表进行查找
	this.findList = function(ids) {
		return $http.get(adminConfig.apiServer + '/tbItemCat/findList.do?ids=' + ids);
	};
	
	// 功能说明：根据主键进行删除
	this.deleteOne = function(id) {
		return $http.get(adminConfig.apiServer + '/tbItemCat/deleteOne.do?id=' + id);
	};
	
	// 功能说明：删除所有对象列表
	this.deleteAll = function() {
		return $http.get(adminConfig.apiServer + '/tbItemCat/deleteAll.do');
	};
	
	// 功能说明：根据列表进行删除
	this.deleteList = function(ids) {
		return $http.get(adminConfig.apiServer + '/tbItemCat/deleteList.do?ids=' + ids);
	};
	
	// 功能说明：更新对象
	this.update = function(entity) {
		return $http.post(adminConfig.apiServer + '/tbItemCat/update.do', entity);
	};
	
	// 功能说明：添加对象
	this.add = function(entity) {
		return $http.post(adminConfig.apiServer + '/tbItemCat/add.do', entity);
	};
	
	// 功能说明：分页查询
	this.search = function(pageNum, pageSize, searchEntity) {
		return $http.post(adminConfig.apiServer + '/tbItemCat/search.do?pageNum=' + pageNum + "&pageSize=" + pageSize, searchEntity);
	};
	
	//根据上级ID查询下级列表
	this.findByParentId=function(parentId){
		if (parentId == undefined) {
			parentId = -1;
		}
		return $http.get(adminConfig.apiServer + '/tbItemCat/findByParentId.do?parentId='+parentId);	
	};

});
