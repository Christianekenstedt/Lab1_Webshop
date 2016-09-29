package Controllers;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
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
            if(!httpReq.getRequestURI().equals("/login.jsp")){
                System.out.println("User is not authorized.");
                if(!resp.isCommitted())
                    httpResp.sendRedirect("/login.jsp");
            }
        }
        else{
            System.out.println("User is authorized.");
            if(!resp.isCommitted())
                httpReq.getRequestDispatcher("/home.jsp").forward(httpReq, httpResp);

        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
