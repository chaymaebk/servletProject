

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
                    <a href="https://www.xadmin.net" class="navbar-brand"> CaftanShop </a>
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
        <div class="container col-md-5">
            <div class="card">
                <div class="card-body">
                    <c:if test="${rental != null}">
                        <form action="update" method="post">
                        </c:if>
                        <c:if test="${rental == null}">
                            <form action="insert" method="post">
                            </c:if>

                            <caption>
                                <h2>
                                    <c:if test="${rental != null}">
                                        Modifier
                                    </c:if>
                                    <c:if test="${rental == null}">
                                        Ajouter Réservation
                                    </c:if>
                                </h2>
                            </caption>

                            <c:if test="${rental != null}">
                                <input type="hidden" name="rental_id" value="<c:out value='${rental.id}' />" />
                            </c:if>

                            <fieldset class="form-group">
                                <label>ID du caftan</label> <input type="number"
                                                                   value="<c:out value='${rental.item}' />" class="form-control"
                                                                   name="item_id" required="required">
                            </fieldset>

                            <fieldset class="form-group">
                                <label>ID du client</label> <input type="number"
                                                                   value="<c:out value='${rental.user}' />" class="form-control"
                                                                   name="user_id">
                            </fieldset>

                            <fieldset class="form-group">
                                <label>Date de début</label> <input type="date"
                                                                    value="<c:out value='${rental.start}' />" class="form-control"
                                                                    name="start_date">
                            </fieldset>
                            <fieldset class="form-group">
                                <label>Date de fin</label> <input type="date"
                                                                  value="<c:out value='${rental.end}' />" class="form-control"
                                                                  name="end_date">
                            </fieldset>
                            <c:if test="${rental != null}">
                                <input type="hidden" name="somme" value="<c:out value='${rental.somme}' />" />
                            </c:if>


                            <button type="submit" class="btn btn-success">Enregistrer</button>
                        </form>
                </div>
            </div>
        </div>
    </body>
</html>