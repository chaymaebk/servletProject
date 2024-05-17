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
                <div>
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
                <h3 class="text-center">Liste des réservations</h3>
                <hr>
                <div class="container text-left">

                    <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Ajouter
                        Réservation</a>
                </div>
                <br>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>ID du caftan</th>
                            <th>ID du client</th>
                            <th>Date de début</th>
                            <th>Date de fin</th>
                            <th>Somme</th>
                        </tr>
                    </thead>
                    <tbody>

                        <c:forEach var="rental" items="${listRental}">

                            <tr>
                                <td><c:out value="${rental.id}" /></td>
                                <td><c:out value="${rental.item}" /></td>
                                <td><c:out value="${rental.user}" /></td>
                                <td><c:out value="${rental.start}" /></td>
                                <td><c:out value="${rental.end}" /></td>
                                <td><c:out value="${rental.somme}" /></td>
                                <td><a href="edit?id=<c:out value='${rental.id}' />">Modifier</a>
                                    &nbsp;&nbsp;&nbsp;&nbsp; <a
                                        href="delete?id=<c:out value='${rental.id}' />">Supprimmer</a></td>
                            </tr>
                        </c:forEach>

                    </tbody>

                </table>
            </div>
        </div>
    </body>
</html>