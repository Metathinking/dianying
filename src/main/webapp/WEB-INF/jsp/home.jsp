<%--
  Created by IntelliJ IDEA.
  User: luyilaosan1
  Date: 2017/4/19.0019
  Time: 22:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>人民的名义</title>
    <meta name="Keywords" content="人民的名义,全集,送审样片,种子,限时">
    <meta name="Description" content="人民的名义55集全集下载，送审样片，无剪辑，限时下载">
    <link rel="stylesheet" type="text/css" href="/resources/bootstrap.min.css"/>
    <script type="text/javascript" src="/resources/angular-1.5.0.min.js"></script>
</head>
<body>
<div class="container" style="margin-top: 100px" ng-app="app" ng-controller="homeController">
    <div class="panel panel-info">
        <div class="panel-heading">
            <div class="panel-title">
                <h3>人民的名义《送审样片》</h3>
                <h3 class="pull-right">2017-4-22 22点，网站关闭</h3>
            </div>
        </div>
        <div class="panel-body text-center" >
            <div class="input-group" style="margin:100px auto">
                <input class="form-control" placeholder="请输入领取码" ng-model="code">
                <a href="/goDownload?code={{code}}" class="btn btn-primary" style="margin-top: 20px">获取种子</a>
            </div>
            <a href="/goLingQuMa">我要领取码</a>
        </div>
        <c:if test="${error!=null}">
            <div  class="panel-footer">
                <div class="alert alert-danger">
                    ${error}
                </div>
            </div>
        </c:if>
    </div>
</div>
</body>
</html>
<script>
    angular.module("app",[])
            .controller("homeController",function($scope){
               $scope.code="";

            });
</script>
