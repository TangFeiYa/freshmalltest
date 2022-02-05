/**
 * 功能说明：商品类目控制层
 */
app.controller('tbItemCatController', function($scope, $controller, tbItemCatService, tbTypeTemplateService, uploadService) {
	
	$controller('baseController', {
		$scope : $scope
	});
	
	// 功能说明：根据主键进行查找
	$scope.findOne = function(id) {
		tbItemCatService.findOne(id).success(function(response) {
			$scope.entity = response;
		});
	};
	
	// 功能说明：查找所有对象列表
	$scope.findAll = function() {
		tbItemCatService.findAll().success(function(response) {
			$scope.list = response;
		});
	};
	
	// 功能说明：根据列表进行查找
	$scope.findList = function() {
		tbItemCatService.findList($scope.selectIds).success(function(response) {
			$scope.list = response;
		});
	};
	
	// 功能说明：根据主键进行删除
	$scope.deleteOne = function(id) {
		zeroModal.confirm("您确定要删除吗？", function() {
			tbItemCatService.deleteOne(id).success(function(response) {
				if (response.success) {
					zeroModal.success(response.message);
					// $scope.reloadList();
					$scope.findByParentId($scope.parentId); // 重新查询
				} else {
					zeroModal.error(response.message);
				}
			});
		});
	};
	
	// 功能说明：删除所有对象列表
	$scope.deleteAll = function() {
		tbItemCatService.deleteAll().success(function(response) {
			if (response.success) {
				zeroModal.success(response.message);
				// $scope.reloadList();
				$scope.findByParentId($scope.parentId); // 重新查询
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
			tbItemCatService.deleteList($scope.selectIds).success(function(response) {
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
		tbItemCatService.update($scope.entity).success(function(response) {
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
		tbItemCatService.add($scope.entity).success(function(response) {
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
			serviceObject = tbItemCatService.update($scope.entity);
		} else {
			$scope.entity.parentId = $scope.parentId;// 添加上衣主键
			serviceObject = tbItemCatService.add($scope.entity);
		}
		serviceObject.success(function(response) {
			if (response.success) {
				zeroModal.success(response.message);
				// $scope.reloadList();
				$scope.findByParentId($scope.parentId); // 重新查询
			} else {
				zeroModal.error(response.message);
			}
		});
	};
	
	// 功能说明：分页查询
	$scope.search = function(pageNum, pageSize) {
		tbItemCatService.search(pageNum, pageSize, $scope.searchEntity).success(function(response) {
			$scope.list = response.rows;
			$scope.paginationConf.totalItems = response.total;
		});
	};
	
    // 功能说明：根据上级ID显示下级列表 
	$scope.findByParentId=function(parentId){
		$scope.parentId=parentId; // 记住上一级主键
		tbItemCatService.findByParentId(parentId).success(function(response){
			$scope.list=response;
		});
	};
	
	$scope.grade=1;		// 默认一级
	$scope.parentId=0;	// 上级主键
	
	// 功能说明：设置级别
	$scope.setGrade=function(value){
		$scope.grade=value;
	}
	
	// 功能说明：读取列表
	$scope.selectList=function(p_entity){			
		if($scope.grade==1){//如果为1级
			$scope.entity_1=null;	
			$scope.entity_2=null;
		}
		if($scope.grade==2){//如果为2级
			$scope.entity_1=p_entity;	
			$scope.entity_2=null;
		}
		if($scope.grade==3){//如果为3级
			$scope.entity_2=p_entity;		
		}
		//查询此级下级列表
		$scope.findByParentId(p_entity.id);
	}
	
	// 功能说明：下拉框列表
	$scope.typeTemplateList = {data:[]};
	$scope.selectOptionList = function(){
		tbTypeTemplateService.selectOptionList().success(function(response) {
			$scope.typeTemplateList={data:response};
		});
	};
	
	// 功能说明：上传文件
	$scope.image_entity = {};
	$scope.uploadFile = function() {
		uploadService.uploadFile().success(function(response) {
			if (response.success) {
				$scope.image_entity.url = response.message;
				$scope.entity.image = response.message;
			} else {
				zeroModal.error(response.message);
			}
		}).error(function(ex) {
			zeroModal.error(ex);
		});
	};	

});
