angular.module('remoteSpace').directive('homeDirective', function () {
	return {
		restrict: 'EA',
		//scope: true,
		template: '<ng-include src="homehtml"/>',
		//templateUrl: '/RemoteSpace/resources/views/home.html',
		link : function(scope, elm, $attrs) {
		    scope.$watch("homeShow", function(newValue, oldValue) {
		    	if(!scope.homeShow) scope.homehtml = '/RemoteSpace/resources/views/home.html';
			   	else scope.homehtml = '';
		    });
		 }
	  };
});