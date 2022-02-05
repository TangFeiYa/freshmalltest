/**
 * 功能说明：index控制层
 */
app.controller('indexController', function($scope, $controller) {
	
	$controller('baseController', {
		$scope : $scope
	});
	
});
