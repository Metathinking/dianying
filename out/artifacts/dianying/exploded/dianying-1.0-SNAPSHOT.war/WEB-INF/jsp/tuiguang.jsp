<%--
  Created by IntelliJ IDEA.
  User: luyilaosan1
  Date: 2017/4/20.0020
  Time: 0:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>推广</title>
    <link rel="stylesheet" type="text/css" href="/resources/bootstrap.min.css"/>
    <script type="text/javascript" src="/resources/angular-1.5.0.min.js"></script>
</head>
<body ng-app="app" ng-controller="tuiguangController">
<div class="container" style="margin-top: 100px">
    <div class="panel panel-info">
        <div class="panel-heading">
            <div class="panel-title">
                <h3>推广页</h3>
            </div>
        </div>
        <div class="panel-body ">
            <div class="row text-center">
                <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                    <div class="input-group" style="margin:100px auto">
                        <input class="form-control" placeholder="请输入微信号" ng-model="weixinNo">
                        <button class="btn btn-primary" style="margin-top: 20px" ng-click="add()">我要推广</button>
                    </div>
                    <div class="alert alert-info" ng-if="code">
                        复制以下链接，即可进行推广：<br/>
                        http://${pageContext.request.serverName}:${pageContext.request.serverPort}/home?code={{code}}
                    </div>
                    <div ng-if="count>=10">
                        <a href="/getLingQuMa/{{weixinNo}}">我要领取码</a>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                    <h3>推广记录</h3>
                    <table class="table table-default">
                        <tr>
                            <td>微信号</td>
                            <td>时间</td>
                        </tr>
                        <tr ng-repeat="item in list">
                            <td><span ng-bind="item.weixinNo"></span></td>
                            <td><span ng-bind="item.time|date:'yyyy-MM-dd HH:mm'"></span></td>
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
    angular.module("app", [])
            .controller("tuiguangController", function ($scope, $http) {
                        $scope.weixinNo = "";
                        $scope.code = "";
                        $scope.count = 0;
                        $scope.list = "";
                        $scope.add = function () {
                            var request = {
                                method: 'POST',
                                url: '/tuiguang/' + $scope.weixinNo,
                                headers: {
                                    'Content-Type': 'application/json'
                                }
                            };
                            $http(request).success(function (response, status, headers, cfg) {
                                if (response.success) {
                                    $scope.code = response.data.code;
                                    $scope.count = response.data.count;
                                    $scope.list = response.data.list;
                                } else {

                                }
                            }).error(function (response, status, headers, cfg) {

                            })

                        }
                    }
            );
</script>
