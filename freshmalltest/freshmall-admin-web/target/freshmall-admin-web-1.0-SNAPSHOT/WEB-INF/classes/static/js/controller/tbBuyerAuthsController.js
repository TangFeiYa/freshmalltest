/**
 * 功能说明：登录授权控制层
 */
app.controller('tbBuyerAuthsController', function($scope, $controller, tbBuyerAuthsService) {
	
	$controller('baseController', {
		$scope : $scope
	});
	
	// 功能说明：根据主键进行查找
	$scope.findOne = function(id) {
		tbBuyerAuthsService.findOne(id).success(function(response) {
			$scope.entity = response;
		});
	};
	
	// 功能说明：查找所有对象列表
	$scope.findAll = function() {
		tbBuyerAuthsService.findAll().success(function(response) {
			$scope.list = response;
		});
	};
	
	// 功能说明：根据列表进行查找
	$scope.findList = function() {
		tbBuyerAuthsService.findList($scope.selectIds).success(function(response) {
			$scope.list = response;
		});
	};
	
	// 功能说明：根据主键进行删除
	$scope.deleteOne = function(id) {
		zeroModal.confirm("您确定要删除吗？", function() {
			tbBuyerAuthsService.deleteOne(id).success(function(response) {
				if (response.success) {
					zeroModal.success(response.message);
					$scope.reloadList();
				} else {
					zeroModal.error(response.message);
				}
			});
		});
	};
	
	// 功能说明：删除所有对象列表
	$scope.deleteAll = function() {
		tbBuyerAuthsService.deleteAll().success(function(response) {
			if (response.success) {
				zeroModal.success(response.message);
				$scope.reloadList();
			} else {
				zeroModal.error(response.message);
			}
		});
	};
	
	// 功能说明：根据列表进行删除
	$scope.deleteList = function() {
		if ($scope.selectIds.length == 0) {
			zeroModal.alert('请您勾选需要删除的记录！');
			return;
		}
		
		zeroModal.confirm("您确定要删除吗？", function() {
			tbBuyerAuthsService.deleteList($scope.selectIds).success(function(response) {
				if (response.success) {
					zeroModal.success(response.message);
					$scope.reloadList();
					
					$scope.selectIds = [];
				} else {
					zeroModal.error(response.message);
				}
			});
		});
	};
	
	// 功能说明：更新对象
	$scope.update = function() {
		tbBuyerAuthsService.update($scope.entity).success(function(response) {
			if (response.success) {
				zeroModal.success(response.message);
				$scope.reloadList();
			} else {
				zeroModal.error(response.message);
			}
		});
	};
	
	// 功能说明：添加对象
	$scope.add = function() {
		tbBuyerAuthsService.add($scope.entity).success(function(response) {
			if (response.success) {
				zeroModal.success(response.message);
				$scope.reloadList();
			} else {
				zeroModal.error(response.message);
			}
		});
	};
	
	// 功能说明：保存对象
	$scope.save = function() {
		var serviceObject;
		if ($scope.entity.id != null) {
			serviceObject = tbBuyerAuthsService.update($scope.entity);
		} else {
			serviceObject = tbBuyerAuthsService.add($scope.entity);
		}
		serviceObject.success(function(response) {
			if (response.success) {
				zeroModal.success(response.message);
				$scope.reloadList();
			} else {
				zeroModal.error(response.message);
			}
		});
	};
	
	// 功能说明：分页查询
	$scope.search = function(pageNum, pageSize) {
		tbBuyerAuthsService.search(pageNum, pageSize, $scope.searchEntity).success(function(response) {
			$scope.list = response.rows;
			$scope.paginationConf.totalItems = response.total;
		});
	};
	
	// 功能说明：授权类型
	$scope.typeList = ['手机','邮箱','用户','微信','微博','扣扣'];
	
	// 功能说明：授权校验
	$scope.verifiedList = ['未校验','已校验'];
	
	// 功能说明：授权状态
	$scope.enableList = ['启用','禁用'];
	
});
