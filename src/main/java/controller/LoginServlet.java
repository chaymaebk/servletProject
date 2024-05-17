package controller;

import java.io.IOException;
import java.sql.SQLException;

import model.Login;
import model.LoginDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String email = request.getParameter("email");
    	String password = request.getParameter("password");


        // Authentifier l'utilisateur
        LoginDAO loginDAO = new LoginDAO();
        try {
            Login login = loginDAO.authenticate(email, password);
            if (login != null) {
                // Authentification réussie, stocker l'utilisateur en session et rediriger vers la page dashboard
                HttpSession session = request.getSession();
                session.setAttribute("loginDAO", login);
                response.sendRedirect("dashboard.jsp");
            } else {
                // Authentification échouée, renvoyer l'utilisateur vers la page de connexion avec un message d'erreur
                request.setAttribute("error", "Invalid username or password");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new ServletException("Database access error", e);
        }
    }
}
