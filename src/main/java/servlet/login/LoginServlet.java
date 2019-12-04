package servlet.login;

import model.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login/index.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        Optional<User> user = userService.findByLogin(login);

        if (user.isPresent()) {
            if (user.get().getPassword().equals(password)) {
                HttpSession session = request.getSession();
                session.setAttribute("user_id", user.get().getId());
                session.setAttribute("user_role", user.get().getRole());

                redirectFromRole(response, user.get().getRole());
            }
        }
    }

    private void redirectFromRole(HttpServletResponse response, String role) throws IOException {
        if ("admin".equals(role)) {
            response.sendRedirect("/admin/userList");
        } else {
            response.sendRedirect("/user");
        }
    }
}