angular.module('remoteSpace').controller('hidingController', function($rootScope) {

	//controller adibito solo a settare la visibilità iniziale delle parti settando le variabili in scope
	$rootScope.homeShow = true; 
	$rootScope.loginShow = false; 
	$rootScope.filescontentShow = true; 
	
});
	