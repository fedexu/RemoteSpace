angular.module('remoteSpace').controller('filesController', function($rootScope,serviceManager) {
	
	//prima di abilitare la visualizzazione della parte di pagina dei file, 
	//carico la variabile in scope files.
	serviceManager.getUserFilesList().then(function(files) {
		$rootScope.files = files;
		$rootScope.filescontentShow = false;
	 });
	
	//funzione di click adibita al chiamare un nuovo percorso
	this.goTo = function(name){
		console.log(name);
	};
	
	//funzione di click adibita a chiamare un download di file
	this.getFile = function(file){
		//$rootScope.iWantToSaveThatFile = file;
		    window.location.href = "http://localhost:8080/RemoteSpace/getFile?file="+file;
		    //window.location.href = "http://localhost:8080/RemoteSpace/getFile?file="+file.name;
		/*serviceManager.getUserFiles(file).then(function(data) {
			var file = new Blob([data], { type: 'application/'+$rootScope.iWantToSaveThatFile.extension });
            saveAs(file, $rootScope.iWantToSaveThatFile.name);
		 });*/
	};
	
});
	