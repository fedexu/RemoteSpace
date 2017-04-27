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
	this.getUserFiles = function(fileData){
		return 	$http({
			  method: 'GET',
			  url: 'http://localhost:8080/RemoteSpace/getFile',
			  //responseType: 'arraybuffer',  //per passare dei dati nella post, maniera brutta pero
			  params: {file : fileData}
			}).then(function successCallback(response) {
				return response.data;
		  }, function errorCallback(response) {
		    // TODO 
		  });
	}
	
	this.str2bytes = function(str) {
	    var bytes = new Uint8Array(str.length);
	    for (var i=0; i<str.length; i++) {
	        bytes[i] = str.charCodeAt(i);
	    }
	    return bytes;
	}
	
});