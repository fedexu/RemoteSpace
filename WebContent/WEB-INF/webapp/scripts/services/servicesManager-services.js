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
	this.getUserFilesList = function(path){
		return 	$http({
			  method: 'POST',
			  url: 'http://localhost:8080/RemoteSpace/getFileList',
			  data: path
			}).then(function successCallback(response) {
				console.log(response.data);
				return response.data;
		  }, function errorCallback(response) {
		    // TODO 
		  });
	};
	
	this.getUserFilePath = function(data){
		return 	$http({
			  method: 'POST',
			  url: 'http://localhost:8080/RemoteSpace/getUserFilePath'
			}).then(function successCallback(response) {
				console.log(response.data);
				return response.data;
		  }, function errorCallback(response) {
		    // TODO 
		  });
	};
	
	
	//chiamata al servizio per scaricare un file specifico
	this.getUserFiles = function(file){
		window.location.href = "http://localhost:8080/RemoteSpace/getFile?file="+JSON.stringify(file);
	};
	
	
});