/**
 * 功能说明：商品描述服务层
 */
app.service('tbGoodsDescService', function($http) {
	// 功能说明：根据主键进行查找
	this.findOne = function(goodsId) {
		return $http.get(adminConfig.apiServer + '/tbGoodsDesc/findOne.do?goodsId=' + goodsId);
	};
	
	// 功能说明：查找所有对象列表
	this.findAll = function() {
		return $http.get(adminConfig.apiServer + '/tbGoodsDesc/findAll.do');
	};
	
	// 功能说明：根据列表进行查找
	this.findList = function(goodsIds) {
		return $http.get(adminConfig.apiServer + '/tbGoodsDesc/findList.do?goodsIds=' + goodsIds);
	};
	
	// 功能说明：根据主键进行删除
	this.deleteOne = function(goodsId) {
		return $http.get(adminConfig.apiServer + '/tbGoodsDesc/deleteOne.do?goodsId=' + goodsId);
	};
	
	// 功能说明：删除所有对象列表
	this.deleteAll = function() {
		return $http.get(adminConfig.apiServer + '/tbGoodsDesc/deleteAll.do');
	};
	
	// 功能说明：根据列表进行删除
	this.deleteList = function(goodsIds) {
		return $http.get(adminConfig.apiServer + '/tbGoodsDesc/deleteList.do?goodsIds=' + goodsIds);
	};
	
	// 功能说明：更新对象
	this.update = function(entity) {
		return $http.post(adminConfig.apiServer + '/tbGoodsDesc/update.do', entity);
	};
	
	// 功能说明：添加对象
	this.add = function(entity) {
		return $http.post(adminConfig.apiServer + '/tbGoodsDesc/add.do', entity);
	};
	
	// 功能说明：分页查询
	this.search = function(pageNum, pageSize, searchEntity) {
		return $http.post(adminConfig.apiServer + '/tbGoodsDesc/search.do?pageNum=' + pageNum + "&pageSize=" + pageSize, searchEntity);
	};
});
