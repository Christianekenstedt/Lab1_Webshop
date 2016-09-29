package Controllers;

import VO.UserVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Anton on 2016-09-29.
 */
public class WebshopController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("operation");

        if(operation != null){
            switch(operation){
                case "login":
                    operationLogin(request, response);
                    break;
                case "logout":
                    operationLogout(request, response);
                    break;
                case "articlesShow":
                    request.getRequestDispatcher("/articles.jsp").forward(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    break;
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    /**
     * TODO: one servlet for each operation area (ie. authentication) or one controller for all?
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void operationLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserVO user = UserVO.authenticate(request.getParameter("username"), request.getParameter("password"));
        if(user != null){
            request.getSession().setAttribute("username", user.getUsername());
            request.getRequestDispatcher("/home.jsp").forward(request, response);
        }else{
            request.getRequestDispatcher("/login-error.jsp").forward(request, response);
        }
    }

    private void operationLogout (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.getSession().setAttribute("username", null);
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
