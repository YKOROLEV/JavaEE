package filter;

import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpSession;

public abstract class BaseFilter extends HttpFilter {

    protected long getUserId(HttpSession session) {
        return (long) session.getAttribute("user_id");
    }

    protected boolean hasAuthorization(HttpSession session) {
        return session.getAttribute("user_id") != null
                && session.getAttribute("user_role") != null;
    }

    protected boolean hasAdmin(HttpSession session) {
        return hasRole(session, "admin");
    }

    protected boolean hasUser(HttpSession session) {
        return hasRole(session, "user");
    }

    private boolean hasRole(HttpSession session, String role) {
        return role.equals(session.getAttribute("user_role"));
    }
}