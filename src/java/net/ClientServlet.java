/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net;

import dao.ClientDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Client;

/**
 *
 * @author TechAima
 */
@WebServlet("/")
public class ClientServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ClientDAO clientDAO; 

    public void init() {
        clientDAO = new ClientDAO();
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
                case "/newc":
                    showNewForm(request, response);
                    break;
                case "/insertc":
                    insertClient(request, response);
                    break;
                case "/deletec":
                    deleteClient(request, response);
                    break;
                case "/editc":
                    showEditForm(request, response);
                    break;
                case "/updatec":
                    updateClient(request, response);
                    break;
                default:
                    listClient(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    private void listClient(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Client> listClient = clientDAO.selectAllClient();
        request.setAttribute("listClient", listClient);
        RequestDispatcher dispatcherc = request.getRequestDispatcher("client-list.jsp");
        dispatcherc.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("client-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Client existingClient = clientDAO.selectClient(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("client-form.jsp");
        request.setAttribute("client", existingClient);
        dispatcher.forward(request, response);

    }

    private void insertClient(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String cin = request.getParameter("cin");
        String name = request.getParameter("username");
        String email = request.getParameter("email");
        String location = request.getParameter("location_details");
        String phone = request.getParameter("phone");
        // Vérifiez si les paramètres ne sont pas nuls avant de les utiliser
        Client newClient = new Client(cin, name, email, location, phone);
        clientDAO.insertClient(newClient);
        response.sendRedirect("client");
    }

    private void updateClient(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("user_id"));
        String cin = request.getParameter("cin");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String location = request.getParameter("location_details");
        String phone = request.getParameter("phone");
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp time = null;
        try {
            // Parse the date string into a java.util.Date object
            Date date = dateTimeFormat.parse(request.getParameter("registration_time"));
            // Convert the java.util.Date object into a Timestamp object
            time = new Timestamp(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace(); // Handle parsing exception
        }
        Client book = new Client(id, cin, username, email, location, phone, time);
        clientDAO.updateClient(book);
        response.sendRedirect("client");
    }

    private void deleteClient(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String clientIdParam = request.getParameter("id");
        if (clientIdParam != null && !clientIdParam.isEmpty()) {
            int id = Integer.parseInt(clientIdParam);
            clientDAO.deleteClient(id);
        }
        response.sendRedirect("client");
    }

}
