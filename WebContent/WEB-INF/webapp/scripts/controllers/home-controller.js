angular.module('remoteSpace').controller('homeMainController', function($rootScope,serviceManager) {

	
	this.back = function(){
		$rootScope.homeShow = true;
		$rootScope.loginShow = false;
	};
	
});