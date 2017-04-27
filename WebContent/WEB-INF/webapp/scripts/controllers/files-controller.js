angular.module('remoteSpace').controller('filesController', function($rootScope,serviceManager) {
	
	$rootScope.userFilePath = "";
	
	//prima di abilitare la visualizzazione della parte di pagina dei file, 
	//carico la variabile in scope files.
	serviceManager.getUserFilePath($rootScope.userFilePath).then(function(path){
		$rootScope.userFilePath = path;
		serviceManager.getUserFilesList(findPath(0)).then(function(files) {
			$rootScope.files = files;
			$rootScope.filescontentShow = false;
		 });
	});
	
	//funzione di click adibita al chiamare un nuovo percorso
	this.goTo = function(folder){
		console.log(findPath(0)+"/"+folder);
		serviceManager.getUserFilePath(findPath(0)+"/"+folder).then(function(data) {
			console.log(data)
		 });
	};
	
	//funzione di click adibita a chiamare un download di file
	this.getFile = function(file){
		serviceManager.getUserFiles(file);
	};
	
	
	function findPath(num){
		var i = 0;
		var path = "";
		while(i <= num){
			path = path + "/" + $rootScope.userFilePath[i].dir;
			i++;
		}
		return path;
	}
});
	