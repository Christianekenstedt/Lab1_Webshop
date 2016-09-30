package Controllers;

import VO.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

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
                case "checkout":
                    operationCheckout(request,response);
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
                case "goHome":
                    operationGoHome(request, response);
                    break;
                case "cartRemoveItem":
                    operationRemoveFromCart(request, response);
                    break;
                case "signup":
                    operationSignup(request, response);
                    break;
                case "goSignup":
                    request.getRequestDispatcher("/signup.jsp").forward(request,response);
                    break;
                case "adminCenter":
                    operationAdministration(request,response);
                    break;
                case "manageItems":
                    request.getRequestDispatcher("/items.jsp").forward(request,response);
                    break;
                case "addItem":
                    operationAddItem(request,response);
                    break;
                case "deleteItem":
                    operationDeleteItem(request,response);
                    break;
                case "createCategory":
                    operationCreateCategory(request,response);
                    break;
                case "deleteCategory":
                    operationDeleteCategory(request,response);
                    break;
                case "viewUsers":
                    request.getRequestDispatcher("/view-users.jsp").forward(request,response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    break;
            }
        }

    }

    private void operationCreateCategory(HttpServletRequest request, HttpServletResponse response) {
        int parent = Integer.parseInt(request.getParameter("parentCategoryId"));
        if (parent == -1) {}
        String name = request.getParameter("categoryName");
        ItemCategoryVO.createCategory(parent,name);

    }

    private void operationDeleteItem(HttpServletRequest request, HttpServletResponse response) {
        UserVO user = UserVO.getUserByID(Integer.parseInt(request.getSession().getAttribute("userId").toString()));
        if (user.getRole().getName().equals("Admin")){
            ItemVO.deleteItem(Integer.parseInt(request.getParameter("deleteItemId")));
        }
    }

    private void operationAddItem(HttpServletRequest request, HttpServletResponse response) {
        String itemName = request.getParameter("itemName");
        int amount = Integer.parseInt(request.getParameter("itemStock"));
        String s = request.getParameter("itemCategory");
        int categoryId = Integer.parseInt(s);

        ItemVO.addItem(itemName,amount,categoryId);
    }

    private void operationAdministration(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserVO user = UserVO.getUserByID(Integer.parseInt(request.getSession().getAttribute("userId").toString()));
        System.out.println("in");
        if (user.getRole().getName().equals("Admin")){
            request.getRequestDispatcher("/administrator.jsp").forward(request,response);
        }
    }

    private void operationDeleteCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        UserVO user = UserVO.getUserByID(Integer.parseInt(request.getSession().getAttribute("userId").toString()));
        if (user.getRole().getName().equals("Admin")){
            ItemCategoryVO.deleteCategory(Integer.parseInt(request.getParameter("categoryId")));
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
        request.getRequestDispatcher("/cart.jsp").forward(request, response);
    }

    private void operationGoHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.getRequestDispatcher("/home.jsp").forward(request, response);
    }

    private void operationRemoveFromCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int removeItemId = Integer.parseInt(request.getParameter("removeItemId").toString());
        ShoppingCartVO.removeItemFromCart(removeItemId);
        request.getRequestDispatcher("/cart.jsp").forward(request, response);
    }

    private void operationSignup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String username = request.getParameter("username");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        int roleId = 1;
        if(password1.equals(password2)){
            UserVO.addUser(username, password1, roleId);
            request.getRequestDispatcher("/home.jsp").forward(request, response);
        }
        else{
            request.getRequestDispatcher("/signup.jsp").forward(request, response);
        }
    }

    private void operationCheckout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderVO.postOrder(Integer.parseInt(request.getSession().getAttribute("userId").toString()));
        request.setAttribute("orderCreated",true);
        request.getRequestDispatcher("/orders.jsp").forward(request,response);
    }

}
