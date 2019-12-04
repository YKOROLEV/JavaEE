package filter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/admin/*"})
public class AdminFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = request.getSession();

        if (hasAuthorization(session) && hasAdmin(session)) {
            chain.doFilter(request, response);
        } else {
            response.sendRedirect("/user");
        }
    }

    private boolean hasAuthorization(HttpSession session) {
        return session.getAttribute("user_id") != null
                && session.getAttribute("user_role") != null;
    }

    private boolean hasAdmin(HttpSession session) {
        return "admin".equals(session.getAttribute("user_role"));
    }
}