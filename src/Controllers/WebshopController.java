package Controllers;

import VO.OrderVO;
import VO.ItemCategoryVO;
import VO.ShoppingCartVO;
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
                case "myOrders":
                    request.getRequestDispatcher("/orders.jsp").forward(request, response);
                    break;
                case "manageOrder":
                        request.getRequestDispatcher("/viewOrder.jsp").forward(request,response);
                    break;
                case "packOrder":
                        manageOrder(request,response);
                    break;
                case "checkOut":

                    break;
                case "selectCategory":
                    operationSelectCategory(request, response);
                    break;
                case "buyItem":
                    operationBuyItem(request, response);
                    break;
                case "viewCart":
                    operationViewCart(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    break;
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
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
            request.getSession().setAttribute("userId", user.getId());
            request.getRequestDispatcher("/home.jsp").forward(request, response);
        }else{
            request.getRequestDispatcher("/login-error.jsp").forward(request, response);
        }
    }

    private void operationLogout (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.getSession().setAttribute("username", null);
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    private void operationSelectCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setAttribute("selectedCategory", request.getParameter("selectedCategory"));
        request.getRequestDispatcher("/home.jsp").forward(request, response);
    }

    private void operationBuyItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
        int itemId = Integer.parseInt(request.getParameter("buyItemId"));
        int amount = Integer.parseInt(request.getParameter("buyAmount"));
        ShoppingCartVO.addItemToCart(userId, itemId, amount);
        request.getRequestDispatcher("/home.jsp").forward(request, response);
    }

    private void manageOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        OrderVO.updateOrder(Integer.parseInt(request.getParameter("pOrder")));
        request.getRequestDispatcher("/orders.jsp").forward(request,response);
    }

    private void operationViewCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //// TODO: 2016-09-30  
        
    }
    
}
