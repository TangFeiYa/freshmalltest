/**
 * 功能说明：browse服务层
 */
app.service('browseService', function($http) {

	// 功能说明：首页访问插入记录
	this.findByViewPerson = function() {
		return $http.get(adminConfig.apiServer + '/browse/findByViewPerson.do');
	};

});
