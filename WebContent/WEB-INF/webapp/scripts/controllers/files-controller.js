angular.module('remoteSpace').controller('filesController', function($rootScope,serviceManager) {
	
	$rootScope.currentPath = "";
	
	//prima di abilitare la visualizzazione della parte di pagina dei file, 
	//carico la variabile in scope files.
	serviceManager.getCurrentPath().then(function(path){
		$rootScope.currentPath = path;
		serviceManager.getFileList($rootScope.currentPath).then(function(files) {
			$rootScope.files = files;
			$rootScope.filescontentShow = false;
		 });
	});
	
	//funzione di click adibita al chiamare un nuovo percorso
	this.goTo = function(folder){
		folder = {dir: folder};
		
		serviceManager.getFileList(folder).then(function(files) {
			$rootScope.files = files;
		 });
	};
	
	//funzione di click adibita a chiamare un download di file
	this.getFile = function(file){
		serviceManager.getFile(file);
	};
	
	
	
});
	