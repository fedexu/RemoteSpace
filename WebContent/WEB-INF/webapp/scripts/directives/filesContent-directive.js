angular.module('remoteSpace').directive('filesContentDirective', function () {
	return {
		restrict: 'EA',
		template: '<ng-include src="filescontenthtml"/>',
		link : function(scope, elm, $attrs) {
		    scope.$watch("filescontentShow", function(newValue, oldValue) {
		    	if(!scope.filescontentShow) scope.filescontenthtml = '/RemoteSpace/resources/views/files-content.html';
			   	else scope.filescontenthtml = '';
		    });
		 }
	  };
});