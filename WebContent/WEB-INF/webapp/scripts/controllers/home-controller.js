angular.module('remoteSpace').controller('homeMainController', function($scope,$rootScope) {

	this.back = function(){
		$rootScope.homeShow = true;
		$rootScope.loginShow = false;
	};
	
});