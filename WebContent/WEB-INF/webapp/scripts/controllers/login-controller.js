angular.module('remoteSpace').controller('loginForm', function($rootScope,serviceManager) {
	
	//chiamata al servizio per il login. viene ritornata una promise e il blocco login viene tolto solo dopo 
	//l'arrivo della response dal login
	this.tryLogin = function(usr, pwd){
		serviceManager.autentication(usr,pwd).then(function(autentication) {
			if (autentication == true){
				$rootScope.homeShow = false;
				$rootScope.loginShow = true;
			}
		 });
	};
	
});