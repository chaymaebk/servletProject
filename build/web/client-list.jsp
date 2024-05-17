<%--
   Document   : client-list
    Created on : 12 mai 2024, 20:49:12
    Author     : TechAima
--%>
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
                           <li><a href="/connexion/Login.jsp"
                           class="nav-link">Déconnexion</a></li>
                </ul>
            </nav>
        </header>
        <br>

        <div class="row">
            <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->
            <div class="container">
                <h3 class="text-center">Liste des clients</h3>
                <hr>
                <div class="container text-left">

                    <a href="<%=request.getContextPath()%>/newc" class="btn btn-success">Ajouter
                        Client</a>
                </div>
                <br>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>CIN</th>
                            <th>Nom du client</th>
                            <th>Email</th>
                            <th>Location</th>
                            <th>Téléphone</th>
                            <th>Date d'inscription</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${empty listClient}">

                            <tr>

                                <td colspan="8">No clients found.</td>

                            </tr>

                        </c:if>
                        <c:forEach var="client" items="${listClient}">

                            <tr>
                                <td><c:out value="${client.id}" /></td>
                                <td><c:out value="${client.cin}" /></td>
                                <td><c:out value="${client.name}" /></td>
                                <td><c:out value="${client.email}" /></td>
                                <td><c:out value="${client.location}" /></td>
                                <td><c:out value="${client.phone}" /></td>
                                <td><c:out value="${client.time}" /></td>
                                <td><a href="editc?id=<c:out value='${client.id}' />">Modifier</a>
                                    &nbsp;&nbsp;&nbsp;&nbsp; <a
                                        href="deletec?id=<c:out value='${client.id}' />">Supprimmer</a></td>
                            </tr>
                        </c:forEach>

                    </tbody>

                </table>
            </div>
        </div>
    </body>
</html>