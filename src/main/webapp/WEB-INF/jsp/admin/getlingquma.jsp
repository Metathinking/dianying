<%--
  Created by IntelliJ IDEA.
  User: luyilaosan1
  Date: 2017/4/20.0020
  Time: 9:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>领取码</title>
    <link rel="stylesheet" type="text/css" href="/resources/bootstrap.min.css"/>
    <script type="text/javascript" src="/resources/angular-1.5.0.min.js"></script>
</head>
<body ng-app="app" ng-controller="createController">
<div class="container" style="margin-top: 100px">
    <div class="panel panel-info">
        <div class="panel-heading">
            <div class="panel-title">
                <h3>获取领取码</h3>
                <a href="/admin/lingquma/list">领取码</a>
                <a href="/admin/tuiguang/list">推广记录</a>
            </div>
        </div>
        <div class="panel-body" >
            <div class="row text-center">
                <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                    <div class="input-group" style="margin:100px auto">
                        <input class="form-control" placeholder="请输入数量" ng-model="count">
                        <button class="btn btn-primary" style="margin-top: 20px" ng-click="create()">生成推广码</button>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                    <h3>推广码</h3>
                    <button class="btn btn-primary">下载</button>
                    <table class="table table-default">
                        <tr>
                            <td>编号</td>
                            <td>领取码</td>
                        </tr>
                        <tr ng-repeat="item in list">
                            <td><span ng-bind="item.id"></span></td>
                            <td><span ng-bind="item.code"></span></td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<script>
    angular.module("app",[])
            .controller("createController",function ($scope,$http) {
                $scope.count=0;
                $scope.create=function(){
                    var request = {
                        method: 'POST',
                        url: '/admin/getLingQuMa?count='+$scope.count,
                        headers: {
                            'Content-Type': 'application/json'
                        }
                    };
                    $http(request).success(function (response, status, headers, cfg) {
                        if (response.success) {
                            $scope.list = response.data
                        } else {

                        }
                    }).error(function (response, status, headers, cfg) {

                    })
                }
            })
</script>
