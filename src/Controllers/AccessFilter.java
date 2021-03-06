package Controllers;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * This filter will block unauthorized clients and send them to login-page.
 * Created by Anton on 2016-09-29.
 */
public class AccessFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(req, resp);
        HttpServletRequest httpReq = (HttpServletRequest)req;
        HttpServletResponse httpResp = (HttpServletResponse)resp;
        HttpSession session = httpReq.getSession();



        if(session.getAttribute("username") == null){
            if(!resp.isCommitted())
                httpReq.getRequestDispatcher("/login.jsp").forward(httpReq, httpResp);
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
