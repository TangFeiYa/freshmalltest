/**
 * 功能说明：订单服务层
 */
app.service('tbOrderService', function($http) {
	// 功能说明：根据主键进行查找
	this.findOne = function(orderId) {
		return $http.get(adminConfig.apiServer + '/tbOrder/findOne.do?orderId=' + orderId);
	};

	// 功能说明：查找所有对象列表
	this.findAll = function() {
		return $http.get(adminConfig.apiServer + '/tbOrder/findAll.do');
	};

	// 功能说明：根据列表进行查找
	this.findList = function(orderIds) {
		return $http.get(adminConfig.apiServer + '/tbOrder/findList.do?orderIds=' + orderIds);
	};

	// 功能说明：根据主键进行删除
	this.deleteOne = function(orderId) {
		return $http.get(adminConfig.apiServer + '/tbOrder/deleteOne.do?orderId=' + orderId);
	};

	// 功能说明：删除所有对象列表
	this.deleteAll = function() {
		return $http.get(adminConfig.apiServer + '/tbOrder/deleteAll.do');
	};

	// 功能说明：根据列表进行删除
	this.deleteList = function(orderIds) {
		return $http.get(adminConfig.apiServer + '/tbOrder/deleteList.do?orderIds=' + orderIds);
	};

	// 功能说明：更新对象
	this.update = function(entity) {
		return $http.post(adminConfig.apiServer + '/tbOrder/update.do', entity);
	};

	// 功能说明：添加对象
	this.add = function(entity) {
		return $http.post(adminConfig.apiServer + '/tbOrder/add.do', entity);
	};

	// 功能说明：分页查询
	this.search = function(pageNum, pageSize, searchEntity) {
		return $http.post(adminConfig.apiServer + '/tbOrder/search.do?pageNum=' + pageNum + "&pageSize=" + pageSize, searchEntity);
	};

	// 功能说明：查询当月所有订单数
	this.getCurrentMonthCounts = function() {
		return $http.get(adminConfig.apiServer + '/tbOrder/getCurrentMonthCounts.do');
	};
});
