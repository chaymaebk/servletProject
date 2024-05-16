/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package net;

import dao.CaftanDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 *
 * @author TechAima
 */
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import model.Caftan;

@WebServlet("/")
@MultipartConfig
public class CaftanServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private CaftanDAO caftanDAO;

    public void init() {
        caftanDAO = new CaftanDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertCaftan(request, response);
                    break;
                case "/delete":
                    deleteCaftan(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateCaftan(request, response);
                    break;
                default:
                    listCaftan(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listCaftan(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Caftan> listCaftan = caftanDAO.selectAllCaftans();
        request.setAttribute("listCaftan", listCaftan);
        RequestDispatcher dispatcher = request.getRequestDispatcher("caftan-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("caftan-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        Caftan existingCaftan = caftanDAO.selectCaftan(itemId);
        RequestDispatcher dispatcher = request.getRequestDispatcher("caftan-form.jsp");
        request.setAttribute("caftan", existingCaftan);
        dispatcher.forward(request, response);
    }

    private void insertCaftan(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String itemType = request.getParameter("itemType");
        String description = request.getParameter("description");
        String priceParameter = request.getParameter("price");
        BigDecimal price = null;
        if (priceParameter != null && !priceParameter.isEmpty()) {
            try {
                price = new BigDecimal(priceParameter);
            } catch (NumberFormatException e) {
                // Handle the case where the price parameter is not a valid numeric value
                // For example, you could set a default price or return an error message
                e.printStackTrace(); // Log the exception for debugging purposes
            }
        } else {
            // Handle the case where the price parameter is null or empty
            // For example, you could set a default price or return an error message
        }
        //BigDecimal price = new BigDecimal(request.getParameter("price"));
        String characteristics = request.getParameter("characteristics");
        String availability = request.getParameter("availability");
        //byte[] image = request.getParameter("image").getBytes();
        Part filePart = request.getPart("image");
        String image = null;
        if (filePart != null) {
            byte[] imageBytes = filePart.getInputStream().readAllBytes();
            image = Base64.getEncoder().encodeToString(imageBytes);
        }
        Caftan newCaftan = new Caftan(itemType, description, price, characteristics, availability, image);
        caftanDAO.insertCaftan(newCaftan);
        response.sendRedirect("list");
    }

    private void updateCaftan(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        String itemType = request.getParameter("itemType");
        String description = request.getParameter("description");
        BigDecimal price = new BigDecimal(request.getParameter("price"));
        String characteristics = request.getParameter("characteristics");
        String availability = request.getParameter("availability");
        byte[] image = request.getParameter("image").getBytes();

        Caftan caftan = new Caftan(itemId, itemType, description, price, characteristics, availability, Base64.getEncoder().encodeToString(image));
        caftanDAO.updateCaftan(caftan);
        response.sendRedirect("list");
    }

    private void deleteCaftan(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        caftanDAO.deleteCaftan(itemId);
        response.sendRedirect("list");
    }
}
