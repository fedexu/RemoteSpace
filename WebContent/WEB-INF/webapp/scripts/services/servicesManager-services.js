angular.module('remoteSpace').service('serviceManager', function ($http) {
	
	//chiamata al servizio Java adibito al controllo autenticazione, promise ritornata al chiamante
	this.autentication = function(usr, pwd){
		return 	$http({
			  method: 'POST',
			  url: 'http://localhost:8080/RemoteSpace/dologin',
			  data: {username : usr, password : pwd}
			}).then(function successCallback(response) {
				return response.data.autentication;
		  }, function errorCallback(response) {
		    // TODO 
		  });
	};
	
	//chiamata al servizio per la lista di file/cartelle visualizzabili sul percorso
	this.getUserFilesList = function(){
		return 	$http({
			  method: 'GET',
			  url: 'http://localhost:8080/RemoteSpace/resources/scripts/json/filesResponse.json'
			}).then(function successCallback(response) {
				return response.data;
		  }, function errorCallback(response) {
		    // TODO 
		  });
	}
	
	//chiamata al servizio per scaricare un file specifico
	this.getUserFiles = function(file){
		return 	$http({
			  method: 'POST',
			  url: 'http://localhost:8080/RemoteSpace/getFile',
			  data: file
			}).then(function successCallback(response) {
				console.log(response);
				return response.data;
		  }, function errorCallback(response) {
		    // TODO 
		  });
	}
	
});