/**
 * 功能说明：商品信息控制层
 */
app.controller('tbGoodsController', function($scope, $controller ,$location , tbGoodsService, uploadService, tbItemCatService, tbTypeTemplateService) {
	
	$controller('baseController', {
		$scope : $scope
	});
	
	// 初始化商品对象
	$scope.entity={
		"goods":{},
		"goodsDesc":{}
	}
	
	// 功能说明：根据主键进行查找
	$scope.findOne = function(id) {
		var id = $location.search()['id'];//获取参数值
		if (id == null) {
			return;
		}
		
		tbGoodsService.findOne(id).success(function(response) {
			// 显示商品基本信息
			$scope.entity = response;

			// 显示编辑器的内容
			editor1.html($scope.entity.goodsDesc.introduction);
			editor2.html($scope.entity.goodsDesc.packageList);
			editor3.html($scope.entity.goodsDesc.saleService);

			// 显示图片列表集合
			$scope.entity.goodsDesc.itemImages = JSON.parse($scope.entity.goodsDesc.itemImages);

			// 显示商品扩展属性
			$scope.entity.goodsDesc.customAttributeItems = JSON.parse($scope.entity.goodsDesc.customAttributeItems);

			// 显示商品规格列表				
			$scope.entity.goodsDesc.specificationItems = JSON.parse($scope.entity.goodsDesc.specificationItems);

			// 显示商品规格明细				
			for (var i = 0; i < $scope.entity.itemList.length; i++) {
				$scope.entity.itemList[i].spec = JSON.parse($scope.entity.itemList[i].spec);
			}
		});
	};
	
	// 功能说明：查找所有对象列表
	$scope.findAll = function() {
		tbGoodsService.findAll().success(function(response) {
			$scope.list = response;
		});
	};
	
	// 功能说明：根据列表进行查找
	$scope.findList = function() {
		tbGoodsService.findList($scope.selectIds).success(function(response) {
			$scope.list = response;
		});
	};
	
	// 功能说明：根据主键进行删除
	$scope.deleteOne = function(id) {
		zeroModal.confirm("您确定要删除吗？", function() {
			tbGoodsService.deleteOne(id).success(function(response) {
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
		tbGoodsService.deleteAll().success(function(response) {
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
			tbGoodsService.deleteList($scope.selectIds).success(function(response) {
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
		tbGoodsService.update($scope.entity).success(function(response) {
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
		$scope.entity.goodsDesc.introduction=editor1.html();
		$scope.entity.goodsDesc.packageList=editor2.html();
		$scope.entity.goodsDesc.saleService=editor3.html();
		
		tbGoodsService.add($scope.entity).success(function(response) {
			if (response.success) {
				zeroModal.success(response.message);
				
				// 清空对象
				$scope.entity = {
					goods : {},
					goodsDesc : {
						itemImages : [],
						specificationItems : []
					}
				};
				
				editor1.html('');//清空文本编辑器1
				editor2.html('');//清空文本编辑器2
				editor3.html('');//清空文本编辑器3
			} else {
				zeroModal.error(response.message);
			}
		});
	};
	
	// 功能说明：保存对象
	$scope.save = function() {
		// 将富文本编辑器的内容绑定到提交对象中
		$scope.entity.goodsDesc.introduction=editor1.html();
		$scope.entity.goodsDesc.packageList=editor2.html();
		$scope.entity.goodsDesc.saleService=editor3.html();
		
		var serviceObject;
		if ($scope.entity.goods.id != null) {
			serviceObject = tbGoodsService.update($scope.entity);
		} else {
			serviceObject = tbGoodsService.add($scope.entity);
		}
		serviceObject.success(function(response) {
			if (response.success) {
				zeroModal.success(response.message);

				// 清空对象
				$scope.entity = {
					goods : {},
					goodsDesc : {
						itemImages : [],
						specificationItems : []
					}
				};
				
				editor1.html('');//清空文本编辑器1
				editor2.html('');//清空文本编辑器2
				editor3.html('');//清空文本编辑器3
				
				// 返回商品列表
				location.href="tbGoods.html";
			} else {
				zeroModal.error(response.message);
			}
		});
	};
	
	// 功能说明：分页查询
	$scope.search = function(pageNum, pageSize) {
		tbGoodsService.search(pageNum, pageSize, $scope.searchEntity).success(function(response) {
			$scope.list = response.rows;
			$scope.paginationConf.totalItems = response.total;
		});
	};
	
	// 功能说明：上传文件
	$scope.uploadFile = function() {
		uploadService.uploadFile().success(function(response) {
			if (response.success) {
				$scope.image_entity.url = response.message;
			} else {
				zeroModal.error(response.message);
			}
		}).error(function(ex) {
			zeroModal.error(ex);
		});
	};

	// 功能说明：商品组合实体对象
	$scope.entity = {
		goods : {},
		goodsDesc : {
			itemImages : [],
			specificationItems : []
		}
	};
	
	// 功能说明：添加图片列表
	$scope.add_image_entity = function() {
		$scope.entity.goodsDesc.itemImages.push($scope.image_entity);
	};

	// 功能说明：列表中移除图片
	$scope.remove_image_entity = function(index) {
		$scope.entity.goodsDesc.itemImages.splice(index, 1);
	};
	
	// 功能说明：加载一级分类
	$scope.selectItemCat1List = function() {
		tbItemCatService.findByParentId(0).success(function(response) {
			$scope.itemCat1List = response;
		});
	};

	// 功能说明：加载二级分类
	$scope.$watch('entity.goods.category1Id', function(newValue, oldValue) {
		tbItemCatService.findByParentId(newValue).success(function(response) {
			$scope.itemCat2List = response;
		});
	});

	// 功能说明：加载三级分类
	$scope.$watch('entity.goods.category2Id', function(newValue, oldValue) {
		tbItemCatService.findByParentId(newValue).success(function(response) {
			$scope.itemCat3List = response;
		});
	});
	
	// 功能说明：加载分类模板
	$scope.$watch('entity.goods.category3Id', function(newValue, oldValue) {
		tbItemCatService.findOne(newValue).success(function(response) {
			$scope.entity.goods.typeTemplateId = response.typeId;
		})
	});
	
	// 功能说明：模板ID选择后  更新品牌列表
	$scope.$watch('entity.goods.typeTemplateId', function(newValue, oldValue) {
		// 查询模板对象
		tbTypeTemplateService.findOne(newValue).success(function(response) {
			$scope.typeTemplate = response;//分类模板
			if ($location.search()['id'] == null){
				$scope.entity.goodsDesc.customAttributeItems = JSON.parse($scope.typeTemplate.customAttributeItems);//扩展属性	
			}
		});
		
    	// 查询规格列表
		tbTypeTemplateService.findSpecList(newValue).success(function(response){
	    	$scope.specList=response;
		});
	});
	
	// 功能说明：商品规格勾选
	$scope.updateSpecAttribute = function($event, name, value) {
		var object = $scope.searchObjectByKey($scope.entity.goodsDesc.specificationItems, 'attributeName', name);
		if (object != null) {
			if ($event.target.checked) {
				object.attributeValue.push(value);
			} else {//取消勾选
				object.attributeValue.splice(object.attributeValue.indexOf(value), 1);//移除选项
				//如果选项都取消了，将此条记录移除
				if (object.attributeValue.length == 0) {
					$scope.entity.goodsDesc.specificationItems.splice($scope.entity.goodsDesc.specificationItems.indexOf(object), 1);
				}
			}
		} else {
			$scope.entity.goodsDesc.specificationItems.push({
				"attributeName" : name,
				"attributeValue" : [ value ]
			});
		}
	}
	
	// 功能说明：根据用户选择的规格参数列表自动生成SKU记录列表
	$scope.createItemList = function() {
		//初始化空的SKU默认列表，当用户选择的时候，重新创建，依次生成
		$scope.entity.itemList = [ {
			spec : {},
			price : 0,
			num : 99999,
			status : 1,
			isDefault : 0
		} ];

		//[{"attributeName":"网络","attributeValue":["移动3G","移动4G"]},{"attributeName":"机身内存","attributeValue":["16G","32G","16G","32G"]}]
		//获取用户选择的规格参数列表，用来循环的集合
		var items = $scope.entity.goodsDesc.specificationItems;

		for (var i = 0; i < items.length; i++) {
			$scope.entity.itemList = addColumn($scope.entity.itemList, items[i]['attributeName'], items[i]['attributeValue']);
		}
	};

	/**
	 * 功能说明：动态添加一列
	 * @param list			:需要遍历的集合
	 * @param columnNamem	:列名称
	 * @param columnValues	:列集合
	 */
	addColumn = function(list, columnName, columnValues) {
		var newList = [];

		for (var i = 0; i < list.length; i++) {
			var oldRow = list[i];

			for (var j = 0; j < columnValues.length; j++) {
				var newRow = JSON.parse(JSON.stringify(oldRow));//深克隆
				newRow.spec[columnName] = columnValues[j];
				newList.push(newRow);
			}
		}

		return newList;
	}
	
	// 功能说明：根据规格名称和选项名称返回是否被勾选
	$scope.checkAttributeValue=function(specName,optionName){
		var items= $scope.entity.goodsDesc.specificationItems;
		var object= $scope.searchObjectByKey(items,'attributeName',specName);
		if(object==null){
			return false;
		}else{
			if(object.attributeValue.indexOf(optionName)>=0){
				return true;
			}else{
				return false;
			}
		}			
	}
	
});
