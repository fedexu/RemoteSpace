angular.module('remoteSpace').controller('loginForm', function($scope,$rootScope,serviceManager) {
	
	this.tryLogin = function(usr, pwd){
		serviceManager.autentication(usr,pwd).then(function(autentication) {
			if (autentication == true){
				$rootScope.homeShow = false;
				$rootScope.loginShow = true;
			}
		 });
	};
	
});