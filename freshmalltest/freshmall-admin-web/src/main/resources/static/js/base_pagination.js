/**
 * 功能说明：创建模块（分页插件）
 */
var app = angular.module('fresh-admin', [ 'pagination' ]);

/**
 * 功能说明：信任源码
 */
app.filter('trustHtml', [ '$sce', function($sce) {
	return function(data) {
		return $sce.trustAsHtml(data);
	}
} ]);
