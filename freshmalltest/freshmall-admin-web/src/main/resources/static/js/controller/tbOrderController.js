/**
 * 功能说明：订单控制层
 */
app.controller('tbOrderController', function($scope, $controller, tbOrderService) {
	
	$controller('baseController', {
		$scope : $scope
	});
	
	// 功能说明：根据主键进行查找
	$scope.findOne = function(orderId) {
		tbOrderService.findOne(orderId).success(function(response) {
			$scope.entity = response;
		});
	};
	
	// 功能说明：查找所有对象列表
	$scope.findAll = function() {
		tbOrderService.findAll().success(function(response) {
			$scope.list = response;
		});
	};
	
	// 功能说明：根据列表进行查找
	$scope.findList = function() {
		tbOrderService.findList($scope.selectIds).success(function(response) {
			$scope.list = response;
		});
	};
	
	// 功能说明：根据主键进行删除
	$scope.deleteOne = function(orderId) {
		zeroModal.confirm("您确定要删除吗？", function() {
			tbOrderService.deleteOne(orderId).success(function(response) {
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
		tbOrderService.deleteAll().success(function(response) {
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
			tbOrderService.deleteList($scope.selectIds).success(function(response) {
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
		tbOrderService.update($scope.entity).success(function(response) {
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
		tbOrderService.add($scope.entity).success(function(response) {
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
		if ($scope.entity.orderId != null) {
			serviceObject = tbOrderService.update($scope.entity);
		} else {
			serviceObject = tbOrderService.add($scope.entity);
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
		tbOrderService.search(pageNum, pageSize, $scope.searchEntity).success(function(response) {
			$scope.list = response.rows;
			$scope.paginationConf.totalItems = response.total;
		});
	};
	
	// 功能说明：支付方式
	$scope.payTypeList = ['网银支付','微信支付','支付宝支付','货到付款'];
	
	// 功能说明：支付状态
	$scope.payStatus = ['待付款','待发货','待收货','待评价','交易成功','交易失败'];
	
});
