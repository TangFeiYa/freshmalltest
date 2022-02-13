/**
 * 功能说明：买家拓展信息服务层
 */
app.service('tbBuyerInfoService', function($http) {
	// 功能说明：根据主键进行查找
	this.findOne = function(buyerId) {
		return $http.get(adminConfig.apiServer + '/tbBuyerInfo/findOne.do?buyerId=' + buyerId);
	};
	
	// 功能说明：查找所有对象列表
	this.findAll = function() {
		return $http.get(adminConfig.apiServer + '/tbBuyerInfo/findAll.do');
	};
	
	// 功能说明：根据列表进行查找
	this.findList = function(buyerIds) {
		return $http.get(adminConfig.apiServer + '/tbBuyerInfo/findList.do?buyerIds=' + buyerIds);
	};
	
	// 功能说明：根据主键进行删除
	this.deleteOne = function(buyerId) {
		return $http.get(adminConfig.apiServer + '/tbBuyerInfo/deleteOne.do?buyerId=' + buyerId);
	};
	
	// 功能说明：删除所有对象列表
	this.deleteAll = function() {
		return $http.get(adminConfig.apiServer + '/tbBuyerInfo/deleteAll.do');
	};
	
	// 功能说明：根据列表进行删除
	this.deleteList = function(buyerIds) {
		return $http.get(adminConfig.apiServer + '/tbBuyerInfo/deleteList.do?buyerIds=' + buyerIds);
	};
	
	// 功能说明：更新对象
	this.update = function(entity) {
		return $http.post(adminConfig.apiServer + '/tbBuyerInfo/update.do', entity);
	};
	
	// 功能说明：添加对象
	this.add = function(entity) {
		return $http.post(adminConfig.apiServer + '/tbBuyerInfo/add.do', entity);
	};
	
	// 功能说明：分页查询
	this.search = function(pageNum, pageSize, searchEntity) {
		return $http.post(adminConfig.apiServer + '/tbBuyerInfo/search.do?pageNum=' + pageNum + "&pageSize=" + pageSize, searchEntity);
	};
});
