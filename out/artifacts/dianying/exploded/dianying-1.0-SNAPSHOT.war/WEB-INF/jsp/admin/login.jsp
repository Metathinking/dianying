<%--
  Created by IntelliJ IDEA.
  User: luyilaosan1
  Date: 2017/4/20.0020
  Time: 9:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="/resources/bootstrap.min.css"/>
    <script type="text/javascript" src="/resources/angular-1.5.0.min.js"></script>
</head>
<body>
<div class="container" style="margin-top: 100px">
    <div class="panel panel-info">
        <div class="panel-heading">
            <div class="panel-title">
                <h3>登录</h3>
            </div>
        </div>
        <div class="panel-body text-center" >
            <form class="input-group" style="margin:100px auto" method="post" action="/adminLogin">
                <input type="text" name="name" class="form-control" placeholder="请输入用户名" >
                <input type="password" name="password" class="form-control" placeholder="请输入密码" >
                <input type="submit" value="登录" class="btn btn-primary" style="margin-top: 20px">
            </form>
        </div>
    </div>
</div>
</body>
</html>
<script>
    angular.module("app",[])
            .controller("loginController",function($scope,$http){
                $scope.name="";
                $socpe.password="";
                $scope.login=function(){
                    var request = {
                        method: 'POST',
                        url: '/adminLogin',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        data:{
                            name:$scope.name,
                            password:$scope.password
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
            })
</script>
