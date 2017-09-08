<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: luyilaosan1
  Date: 2017/4/20.0020
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>推广记录</title>
    <link rel="stylesheet" type="text/css" href="/resources/bootstrap.min.css"/>
    <script type="text/javascript" src="/resources/angular-1.5.0.min.js"></script>
</head>
<body ng-app="app" ng-controller="recordController">
    <div class="container">
        <h2>推广记录</h2>
        <table class="table table-hover">
            <tr>
                <td>编号</td>
                <td>微信号</td>
                <td>推广员</td>
                <td>code</td>
                <%--<td>时间</td>--%>
            </tr>
            <c:forEach items="${list}" var="item">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.weixinNo}</td>
                    <td>${item.fatherNo}</td>
                    <td>${item.code}</td>
                    <%--<td><fmt:formatDate value="${item.time}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>--%>
                </tr>
            </c:forEach>
        </table>
        <c:if test="${list.size()!=0}">
            <section class="center">
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <li class="${pageQuery.index==1?'disabled':''}">
                            <a href="javascript:void(0)" aria-label="Previous" ng-click="gotoPage(1)">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                        <li class="${pageQuery.index==1?'disabled':''}">
                            <a href="javascript:void(0)" aria-label="Previous" ng-click="prePage()">
                                <span aria-hidden="true">上一页</span>
                            </a>
                        </li>
                        <c:forEach begin="${pageQuery.startPage}" end="${pageQuery.endPage}" var="index">
                            <li class="${index==pageQuery.index?'active':''}"><a href="javascript:void(0)" ng-click="gotoPage('${index}')">${index}</a> </li>
                        </c:forEach>
                        <li class="${pageQuery.index==pageQuery.pageCount?'disabled':''}">
                            <a href="javascript:void(0)" aria-label="Next" ng-click="nextPage()">
                                <span aria-hidden="true">下一页</span>
                            </a>
                        </li>
                        <li class="${pageQuery.index==pageQuery.pageCount?'disabled':''}">
                            <a href="javascript:void(0)" aria-label="Next" ng-click="gotoPage('${pageQuery.pageCount}')">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                        <li class="disabled">
                            <span>共${pageQuery.pageCount}页，${pageQuery.count}条</span>
                        </li>
                    </ul>
                </nav>
            </section>
        </c:if>
    </div>
</body>
</html>
<script>
    angular.module("app", [])
            .controller("recordController", function ($scope, $http) {
                $scope.prePage=function () {
                    if(${pageQuery.index==1}){
                        return;
                    }
                    $scope.gotoPage(${pageQuery.index-1>0?pageQuery.index-1:1})
                };
                $scope.nextPage=function () {
                    if(${pageQuery.index==pageQuery.pageCount}){
                        return;
                    }
                    $scope.gotoPage(${pageQuery.index+1<pageQuery.pageCount?pageQuery.index+1:pageQuery.pageCount})
                }
                $scope.gotoPage=function(index){
                    window.location.href="/admin/tuiguang/list?index="+index;
                }
            })
</script>