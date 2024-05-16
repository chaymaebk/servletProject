<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>CaftanShop</title>
        <link rel="stylesheet"
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
              crossorigin="anonymous">
    </head>
    <body>

        <header>
            <nav class="navbar navbar-expand-md navbar-dark"
                 style="background-color: palevioletred">
                <div><!--<!-- palevioletred  -->
                    <a href="https://www.xadmin.net" class="navbar-brand">
                        CaftanShop </a>
                </div>

                <ul class="navbar-nav">
                    <li><a href="/reservation"
                           class="nav-link">Réservations</a></li>
                    <li><a href="/client"
                           class="nav-link">Clients</a></li>
                    <li><a href="/caftan"
                           class="nav-link">Caftans</a></li>
                </ul>
            </nav>
        </header>
        <br>

        <div class="row">
            <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

            <div class="container">
                <h3 class="text-center">Liste des caftans</h3>
                <hr>
                <div class="container text-left">

                    <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Ajouter
                        Caftan</a>
                </div>
                <br>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Item ID</th>
                            <th>Image</th>
                            <th>Item Type</th>
                            <th>Description</th>
                            <th>Price</th>
                            <th>Characteristics</th>
                            <th>Availability</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>

                        <c:forEach var="caftan" items="${listCaftan}">

                            <tr>
                                <td>${caftan.itemId}</td>
                                <td><img src="data:image/jpeg;base64,${caftan.image}" width="200px" height="200px" /></td>
                                <td>${caftan.itemType}</td>
                                <td>${caftan.description}</td>
                                <td>${caftan.price}</td>
                                <td>${caftan.characteristics}</td>
                                <td>${caftan.availability}</td>
                                <td><a href="edit?itemId=${caftan.itemId}">Modifier</a><br>
                                    <a href="delete?itemId=${caftan.itemId}">Supprimer</a></td>
                            </tr>
                        </c:forEach>

                    </tbody>

                </table>
            </div>
        </div>
    </body>
</html>

