angular.module('remoteSpace').service('serviceManager', function ($http,$q) {
	this.autentication = function(usr, pwd){
		return 	$http({
			  method: 'GET',
			  url: 'http://localhost:8080/RemoteSpace/resources/scripts/json/autenticationResponse.json'
			}).then(function successCallback(response) {
				 return response.data.autentication;
		  }, function errorCallback(response) {
		    // TODO 
		  });
	};
	
	
});