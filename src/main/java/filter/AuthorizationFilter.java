package filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/*"})
public class AuthorizationFilter extends BaseFilter {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = request.getSession();

        if (hasAuthorization(session)) {
            chain.doFilter(request, response);
        } else {
            redirectToLoginPage(request, response, chain);
        }
    }

    private void redirectToLoginPage(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if ("/login".equals(request.getRequestURI())) {
            chain.doFilter(request, response);
        } else {
            response.sendRedirect("/login");
        }
    }
}