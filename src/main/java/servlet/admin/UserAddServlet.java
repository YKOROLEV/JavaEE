package servlet.admin;

import model.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/admin/userAdd")
public class UserAddServlet extends HttpServlet {

    private UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/userAdd.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        transformUserFromRequest(request)
                .ifPresent(userService::save);

        response.sendRedirect("/admin/userList");
    }

    private Optional<User> transformUserFromRequest(HttpServletRequest request) {
        String login = request.getParameter("login");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        boolean success = login != null
                && name != null
                && password != null
                && role != null;

        return success ?
                Optional.of(new User(login, name, password, role)) :
                Optional.empty();
    }
}