package net;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import dao.RentalDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Rental;

/**
 * ControllerServlet.java This servlet acts as a page controller for the
 * application, handling all requests from the user.
 *
 * @email Ramesh Fadatare
 */
@WebServlet("/")
public class RentalServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private RentalDAO rentalDAO;

    public void init() {
        rentalDAO = new RentalDAO();
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
                    insertRental(request, response);
                    break;
                case "/delete":
                    deleteRental(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateRental(request, response);
                    break;
                default:
                    listRental(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listRental(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Rental> listRental = rentalDAO.selectAllRental();
        request.setAttribute("listRental", listRental);
        RequestDispatcher dispatcher = request.getRequestDispatcher("rental-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("rental-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Rental existingRental = rentalDAO.selectRental(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("rental-form.jsp");
        request.setAttribute("rental", existingRental);
        dispatcher.forward(request, response);

    }

    /*private void insertRental(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        int item = Integer.parseInt(request.getParameter("item_id"));
        int user = Integer.parseInt(request.getParameter("user_id"));
        // Vérifier si les paramètres start_date et end_date ne sont pas nuls
        String startDateParam = request.getParameter("start_date");
        String endDateParam = request.getParameter("end_date");
        Date start = null;
        Date end = null;
        if (startDateParam != null && endDateParam != null) {
            try {
                start = dateFormat.parse(startDateParam);
                end = dateFormat.parse(endDateParam);
            } catch (ParseException ex) {
                Logger.getLogger(RentalServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            // Gérer le cas où les paramètres start_date ou end_date sont nuls
            // Par exemple, afficher un message d'erreur ou renvoyer à une page d'erreur
            // Vous pouvez également définir des valeurs par défaut pour start et end si nécessaire
            // Par exemple : start = new Date(); end = new Date();
            // Assurez-vous d'adapter cette partie en fonction de votre logique d'application
            // ou des exigences de l'utilisateur.
            // Ici, nous affichons simplement un message d'erreur dans la console.
            System.err.println("Les paramètres start_date ou end_date sont nuls.");
        }

        Rental newRental = new Rental(item, user, start, end);
        rentalDAO.insertRental(newRental);
        response.sendRedirect("list");
    }*/
    private void insertRental(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            int item = Integer.parseInt(request.getParameter("item_id"));
            int user = Integer.parseInt(request.getParameter("user_id"));

            // Vérifiez si les paramètres ne sont pas nuls avant de les utiliser
            if (request.getParameter("start_date") != null && request.getParameter("end_date") != null) {
                Date start = dateFormat.parse(request.getParameter("start_date"));
                Date end = dateFormat.parse(request.getParameter("end_date"));

                Rental newRental = new Rental(item, user, start, end);
                rentalDAO.insertRental(newRental);
                response.sendRedirect("list");
            } else {
                // Gérer le cas où les paramètres start_date ou end_date sont nuls
                // Par exemple, afficher un message d'erreur ou renvoyer à une page d'erreur
                System.err.println("Les paramètres start_date ou end_date sont nuls.");
            }
        } catch (NumberFormatException | ParseException ex) {
            // Gérer les exceptions de conversion de type ou de parsing
            ex.printStackTrace(); // Affichez les détails de l'exception dans la console ou le journal
            // Gérer l'erreur ou renvoyer à une page d'erreur appropriée
        }
    }

    private void updateRental(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int id = Integer.parseInt(request.getParameter("rental_id"));
        int item = Integer.parseInt(request.getParameter("item_id"));
        int user = Integer.parseInt(request.getParameter("user_id"));
        Date start = null;
        Date end = null;
        int somme = Integer.parseInt(request.getParameter("somme"));
        try {
            start = dateFormat.parse(request.getParameter("start_date"));
            end = dateFormat.parse(request.getParameter("end_date"));
        } catch (ParseException ex) {
            Logger.getLogger(RentalServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        Rental book = new Rental(id, item, user, start, end, somme);
        rentalDAO.updateRental(book);
        response.sendRedirect("list");
    }

    private void deleteRental(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String rentalIdParam = request.getParameter("id");
        if (rentalIdParam != null && !rentalIdParam.isEmpty()) {
            int id = Integer.parseInt(rentalIdParam);
            rentalDAO.deleteRental(id);
        }
        response.sendRedirect("list");
}


}
