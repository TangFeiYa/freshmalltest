/**
 * 功能说明：首页欢迎控制层
 */
app.controller('welcomeController', function($scope, $controller, welcomeService, tbOrderService, tbBuyerService, tbFeedbackService, browseService) {

	$controller('baseController', {
		$scope : $scope
	});

	// 功能说明：根据主键进行查找问题编号
	$scope.findFeedbackOne = function(id) {
		tbFeedbackService.findOne(id).success(function(response) {
			$scope.entity = response;
		});
	};

	// 功能说明：分页查询问题编号
	$scope.findFeedbackSearch = function(pageNum, pageSize) {
		$scope.searchFeedbackEntity = {};
		tbFeedbackService.search(pageNum, pageSize, $scope.searchFeedbackEntity).success(function(response) {
			$scope.list = response.rows;
		});
	};

	// 功能说明：根据支付标志进行查询
	$scope.initCounts = function() {
		// 查询待付款笔数
		$scope.searchEntity1 = {};
		$scope.searchEntity1.Status = '0';
		tbOrderService.search(1, 2, $scope.searchEntity1).success(function(response) {
			$scope.dfk = response.total;
		});

		// 查询已付款笔数
		$scope.searchEntity2 = {};
		$scope.searchEntity2.Status = '1';
		tbOrderService.search(1, 2, $scope.searchEntity2).success(function(response) {
			$scope.yfk = response.total;
		});

		// 查询待发货数量
		$scope.searchEntity3 = {};
		$scope.searchEntity3.Status = '1';
		tbOrderService.search(1, 2, $scope.searchEntity3).success(function(response) {
			$scope.dfh = response.total;
		});

		// 查询已发货数量
		$scope.searchEntity4 = {};
		$scope.searchEntity4.Status = '2';
		tbOrderService.search(1, 2, $scope.searchEntity4).success(function(response) {
			$scope.yfh = response.total;
		});

		// 查询所有订单数
		$scope.searchEntity5 = {};
		tbOrderService.search(1, 2, $scope.searchEntity5).success(function(response) {
			$scope.qbd = response.total;
		});

		// 查询当月订单数
		tbOrderService.getCurrentMonthCounts().success(function(response) {
			$scope.dydd = response;
		});

		// 查询所有用户
		$scope.searchEntity6 = {};
		tbBuyerService.search(1, 2, $scope.searchEntity6).success(function(response) {
			$scope.qbyh = response.total;
		});

		// 查询当月用户
		tbBuyerService.getCurrentMonthCounts().success(function(response) {
			$scope.dyyh = response;
		});

		// 初始化历史记录
		browseService.findByViewPerson().success(function(datas) {
			var line = new Morris.Line({
				element : 'line-chart',
				resize : true,
				data : datas,
				xkey : 'y',
				ykeys : [ 'item' ],
				labels : [ '访问人数' ],
				lineColors : [ '#3c8dbc' ],
				hideHover : 'auto',
				xLabels : "day",
				xLabelFormat : function(x) {
					var date = new Date(x);
					var y = date.getYear();
					var m = date.getMonth() + 1;
					var d = date.getDate();

					if (m < 0) {
						m = "0" + m;
					}
					if (d < 0) {
						d = "0" + d;
					}

					return m + "月" + d + "日";
				}
			});
		});
	}

});