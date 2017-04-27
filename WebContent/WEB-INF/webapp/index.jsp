<!doctype html>
<html lang="it">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<meta charset="UTF-8">

<!--Call to the AngularJS Framework !-->
<script src="<c:url value="/resources/scripts/lib/angular.min.js" />"></script>
<script src="<c:url value="/resources/scripts/lib/FileSaver.min.js" />"></script>
<script src="<c:url value="/resources/scripts/app.js" />"></script>
<!--Servizi !-->
<script	src="<c:url value="/resources/scripts/services/servicesManager-services.js" />"></script>
<!--Controller !-->
<script	src="<c:url value="/resources/scripts/controllers/hiding-controller.js" />"></script>
<script	src="<c:url value="/resources/scripts/controllers/login-controller.js" />"></script>
<script	src="<c:url value="/resources/scripts/controllers/home-controller.js" />"></script>
<script	src="<c:url value="/resources/scripts/controllers/files-controller.js" />"></script>

<!--Direttive !-->
<script	src="<c:url value="/resources/scripts/directives/loginpage-directive.js" />"></script>
<script	src="<c:url value="/resources/scripts/directives/homeSpace-directive.js" />"></script>
<script	src="<c:url value="/resources/scripts/directives/filesContent-directive.js" />"></script>

</head>

<body ng-app = "remoteSpace" ng-controller="hidingController" >
	<div ng-controller="loginForm as l" login-directive></div>
	<div ng-controller="homeMainController as hc" home-directive></div>
</body>
</html>