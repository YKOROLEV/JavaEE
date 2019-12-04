package filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/*"})
public class AuthorizationFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = request.getSession();

        if (hasAuthorization(session)) {
            chain.doFilter(request, response);
        } else {
            redirectToLoginPage(request, response, chain);
        }
    }

    private boolean hasAuthorization(HttpSession session) {
        return session.getAttribute("user_id") != null
                && session.getAttribute("user_role") != null;
    }

    private void redirectToLoginPage(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if ("/login".equals(request.getRequestURI())) {
            chain.doFilter(request, response);
        } else {
            response.sendRedirect("/login");
        }
    }
}