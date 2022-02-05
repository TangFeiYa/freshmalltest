/**
 * 功能说明：base控制层
 */
app.controller('baseController', function($scope) {
	
	// 配置页面信息
	$scope.adminConfig = adminConfig;
	
	// 选中列表集合
	$scope.selectIds = [];
	
	// 查询对象实体
	$scope.searchEntity = {};
	
	// 配置分页插件
	$scope.paginationConf = {
		currentPage : 1,
		totalItems : 13,
		itemsPerPage : 13,
		perPageOptions : [ 10, 20, 30, 40, 50, 60, 70, 80, 90 ],
		onChange : function() {
			$scope.reloadList();
		}
	};
	
	// 重新加载列表
	$scope.reloadList = function() {
		$scope.search($scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
	};
	
	// 更新复选集合
	$scope.selectOne = function($event, id) {
		if ($event.target.checked) {
			$scope.selectIds.push(id);
		} else {
			var idx = $scope.selectIds.indexOf(id);
			$scope.selectIds.splice(idx, 1);
		}
	};
	
	// JSON转换为String
	$scope.jsonToString = function(jsonString, key){
		var json = JSON.parse(jsonString);
		var value = "";
		for(var i = 0;i < json.length;i++){		
			if(i > 0){
				value+=","
			}
			value += json[i][key];			
		}
		return value;
	};
	
	// 从集合中按照key查询对象
	$scope.searchObjectByKey=function(list, key, keyValue){
		for(var i = 0;i < list.length;i++){
			if(list[i][key] == keyValue){
				return list[i];
			}
		}
		return null;
	}
	
});
