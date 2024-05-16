<%-- 
    Document   : caftan-form
    Created on : 16 mai 2024, 00:57:35
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
                </ul>
            </nav>
        </header>
        <br>
        <div class="container col-md-5">
            <div class="card">
                <div class="card-body">
                    <c:if test="${caftan != null}">
                        <form action="update" method="post" enctype="multipart/form-data">
                        </c:if>
                        <c:if test="${caftan == null}">
                            <form action="insert" method="post" enctype="multipart/form-data">
                            </c:if>

                            <caption>
                                <h2>
                                    <c:if test="${caftan != null}">
                                        Modifier
                                    </c:if>
                                    <c:if test="${caftan == null}">
                                        Ajouter Caftan
                                    </c:if>
                                </h2>
                            </caption>

                            <c:if test="${caftan != null}">
                                <input type="hidden" name="itemId" value="<c:out value='${caftan.itemId}' />" />
                            </c:if>
                            <form action="ImageUploadServlet" method="post" enctype="multipart/form-data">
                                <fieldset class="form-group">
                                    <label>Type</label> <input type="text"
                                                               value="<c:out value='${caftan.itemType}' />" class="form-control"
                                                               name="itemType" required="required">
                                </fieldset>

                                <fieldset class="form-group">
                                    <label>Description</label> <input type="textarea"
                                                                      value="<c:out value='${caftan.description}' />" class="form-control"
                                                                      name="description">
                                </fieldset>

                                <fieldset class="form-group">
                                    <label>Prix</label> <input type="number"
                                                               value="<c:out value='${caftan.price}' />" class="form-control"
                                                               name="price">
                                </fieldset>
                                <fieldset class="form-group">
                                    <label>Caractéristiques</label> <input type="text"
                                                                           value="<c:out value='${caftan.characteristics}' />" class="form-control"
                                                                           name="characteristics">
                                </fieldset>
                                <fieldset class="form-group">
                                    <label>Disponibilité</label>
                                    <div>
                                        <label class="radio-inline">
                                            <input type="radio" name="availability" value="yes" <c:if test="${caftan.availability == 'yes'}">checked</c:if>> Yes
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="availability" value="no" <c:if test="${caftan.availability == 'no'}">checked</c:if>> No
                                            </label>
                                        </div>
                                    </fieldset>

                                    <fieldset class="form-group">
                                        <label>Image</label> <input type="file"
                                                                    value="<c:out value='${caftan.image}' />" class="form-control"
                                        name="image" onchange="previewImage(event)">
                                    <br>
                                    <img id="imagePreview" src="#" alt="Image Preview" style="max-width: 200px; max-height: 200px;">
                                </fieldset>


                                <script>
                                    function previewImage(event) {
                                        var input = event.target;
                                        var preview = document.getElementById('imagePreview');

                                        if (input.files && input.files[0]) {
                                            var reader = new FileReader();

                                            reader.onload = function (e) {
                                                preview.src = e.target.result;
                                            };

                                            reader.readAsDataURL(input.files[0]);
                                        } else {
                                            preview.src = "#";
                                        }
                                    }
                                </script>

                                <button type="submit" class="btn btn-success">Enregistrer</button>
                            </form></form>
                </div>
            </div>
        </div>
    </body>
</html>
