/**
 * 功能说明：支付日志服务层
 */
app.service('tbPayLogService', function($http) {
	// 功能说明：根据主键进行查找
	this.findOne = function(outTradeNo) {
		return $http.get(adminConfig.apiServer + '/tbPayLog/findOne.do?outTradeNo=' + outTradeNo);
	};
	
	// 功能说明：查找所有对象列表
	this.findAll = function() {
		return $http.get(adminConfig.apiServer + '/tbPayLog/findAll.do');
	};
	
	// 功能说明：根据列表进行查找
	this.findList = function(outTradeNos) {
		return $http.get(adminConfig.apiServer + '/tbPayLog/findList.do?outTradeNos=' + outTradeNos);
	};
	
	// 功能说明：根据主键进行删除
	this.deleteOne = function(outTradeNo) {
		return $http.get(adminConfig.apiServer + '/tbPayLog/deleteOne.do?outTradeNo=' + outTradeNo);
	};
	
	// 功能说明：删除所有对象列表
	this.deleteAll = function() {
		return $http.get(adminConfig.apiServer + '/tbPayLog/deleteAll.do');
	};
	
	// 功能说明：根据列表进行删除
	this.deleteList = function(outTradeNos) {
		return $http.get(adminConfig.apiServer + '/tbPayLog/deleteList.do?outTradeNos=' + outTradeNos);
	};
	
	// 功能说明：更新对象
	this.update = function(entity) {
		return $http.post(adminConfig.apiServer + '/tbPayLog/update.do', entity);
	};
	
	// 功能说明：添加对象
	this.add = function(entity) {
		return $http.post(adminConfig.apiServer + '/tbPayLog/add.do', entity);
	};
	
	// 功能说明：分页查询
	this.search = function(pageNum, pageSize, searchEntity) {
		return $http.post(adminConfig.apiServer + '/tbPayLog/search.do?pageNum=' + pageNum + "&pageSize=" + pageSize, searchEntity);
	};
});
