/**
 * 功能说明：模板服务层
 */
app.service('tbTypeTemplateService', function($http) {
	// 功能说明：根据主键进行查找
	this.findOne = function(id) {
		if (id == undefined) {
			id = -1;
		}
		return $http.get(adminConfig.apiServer + '/tbTypeTemplate/findOne.do?id=' + id);
	};
	
	// 功能说明：查找所有对象列表
	this.findAll = function() {
		return $http.get(adminConfig.apiServer + '/tbTypeTemplate/findAll.do');
	};
	
	// 功能说明：根据列表进行查找
	this.findList = function(ids) {
		return $http.get(adminConfig.apiServer + '/tbTypeTemplate/findList.do?ids=' + ids);
	};
	
	// 功能说明：根据主键进行删除
	this.deleteOne = function(id) {
		return $http.get(adminConfig.apiServer + '/tbTypeTemplate/deleteOne.do?id=' + id);
	};
	
	// 功能说明：删除所有对象列表
	this.deleteAll = function() {
		return $http.get(adminConfig.apiServer + '/tbTypeTemplate/deleteAll.do');
	};
	
	// 功能说明：根据列表进行删除
	this.deleteList = function(ids) {
		return $http.get(adminConfig.apiServer + '/tbTypeTemplate/deleteList.do?ids=' + ids);
	};
	
	// 功能说明：更新对象
	this.update = function(entity) {
		return $http.post(adminConfig.apiServer + '/tbTypeTemplate/update.do', entity);
	};
	
	// 功能说明：添加对象
	this.add = function(entity) {
		return $http.post(adminConfig.apiServer + '/tbTypeTemplate/add.do', entity);
	};
	
	// 功能说明：分页查询
	this.search = function(pageNum, pageSize, searchEntity) {
		return $http.post(adminConfig.apiServer + '/tbTypeTemplate/search.do?pageNum=' + pageNum + "&pageSize=" + pageSize, searchEntity);
	};
	
	// 功能说明：下拉框列表
	this.selectOptionList = function(){
		return $http.get(adminConfig.apiServer + '/tbTypeTemplate/selectOptionList.do');
	};
	
	//查询规格列表
	this.findSpecList = function(id){
		if (id == undefined){
			id = -1;
		}
		return $http.get(adminConfig.apiServer + '/tbTypeTemplate/findSpecList.do?id='+id);
	}
});
