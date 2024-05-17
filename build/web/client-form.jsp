<%-- 
    Document   : client-form
    Created on : 12 mai 2024, 20:48:36
    Author     : TechAima
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
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
                    <a href="https://www.xadmin.net" class="navbar-brand"> CaftanShop </a>
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
        <div class="container col-md-5">
            <div class="card">
                <div class="card-body">
                    <c:if test="${client != null}">
                        <form action="updatec" method="post">
                        </c:if>
                        <c:if test="${client == null}">
                            <form action="insertc" method="post">
                            </c:if>

                            <caption>
                                <h2>
                                    <c:if test="${client != null}">
                                        Modifier
                                    </c:if>
                                    <c:if test="${client == null}">
                                        Ajouter Client
                                    </c:if>
                                </h2>
                            </caption>

                            <c:if test="${client != null}">
                                <input type="hidden" name="user_id" value="<c:out value='${client.id}' />" />
                            </c:if>

                            <fieldset class="form-group">
                                <label>CIN</label> <input type="text"
                                                                   value="<c:out value='${client.cin}' />" class="form-control"
                                                                   name="cin" required="required">
                            </fieldset>

                            <fieldset class="form-group">
                                <label>Nom du Client</label> <input type="text"
                                                                   value="<c:out value='${client.name}' />" class="form-control"
                                                                   name="username">
                            </fieldset>

                            <fieldset class="form-group">
                                <label>Email</label> <input type="text"
                                                                    value="<c:out value='${client.email}' />" class="form-control"
                                                                    name="email">
                            </fieldset>
                            <fieldset class="form-group">
                                <label>Location</label> <input type="text"
                                                                  value="<c:out value='${client.location}' />" class="form-control"
                                                                  name="location_details">
                            </fieldset>
                            <c:if test="${client != null}">
                                <label>Téléphone</label> <input type="text" name="phone" value="<c:out value='${client.phone}' />" />
                            </c:if>
                            </fieldset>
                            <c:if test="${client != null}">
                                <input type="hidden" name="registration_time" value="<c:out value='${client.time}' />" />
                            </c:if>


                            <button type="submit" class="btn btn-success">Enregistrer</button>
                        </form>
                </div>
            </div>
        </div>
    </body>
</html>
