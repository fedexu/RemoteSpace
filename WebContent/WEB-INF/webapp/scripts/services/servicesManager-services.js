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
	this.getFileList = function(path){
		return 	$http({
			  method: 'POST',
			  url: 'http://localhost:8080/RemoteSpace/getFileList',
			  data: path
			}).then(function successCallback(response) {
				return response.data;
		  }, function errorCallback(response) {
		    // TODO 
		  });
	};
	
	this.getCurrentPath = function(){
		return 	$http({
			  method: 'POST',
			  url: 'http://localhost:8080/RemoteSpace/getCurrentPath'
			}).then(function successCallback(response) {
				return response.data;
		  }, function errorCallback(response) {
		    // TODO 
		  });
	};
	
	this.getFullPath = function(){
		return 	$http({
			  method: 'POST',
			  url: 'http://localhost:8080/RemoteSpace/getFullPath'
			}).then(function successCallback(response) {
				return response.data;
		  }, function errorCallback(response) {
		    // TODO 
		  });
	};
	
	
	//chiamata al servizio per scaricare un file specifico
	this.getFile = function(file){
		window.location.href = "http://localhost:8080/RemoteSpace/getFile?file="+encodeURIComponent(JSON.stringify(file));
	};
	
	
});
