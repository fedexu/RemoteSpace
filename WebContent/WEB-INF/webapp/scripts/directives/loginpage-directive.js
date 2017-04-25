angular.module('remoteSpace').directive('loginDirective', function () {
	return {
		restrict: 'EA',
		//scope: true,
		template: '<ng-include src="loginhtml"/>',
		//templateUrl: '/RemoteSpace/resources/views/login.html',
		link : function(scope, elm, $attrs) {
		    scope.$watch("loginShow", function(newValue, oldValue) {
		    	if(!scope.loginShow) scope.loginhtml = '/RemoteSpace/resources/views/login.html';
			   	else scope.loginhtml = '';
		    });
		 }
	  };
});