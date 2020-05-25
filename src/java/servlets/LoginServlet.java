package servlets;

import business.Store;
import business.StoreDB;
import business.User;
import business.UserDB;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author n.riley
 */
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String msg, userid="";
        int patt;
        String URL="/Login.jsp";
        User u;
        List<Store> stores;
//        User login
        try {
            userid = request.getParameter("userid").trim();
            u = UserDB.getUserByID(userid);
            if(u == null) {
                msg = "No User record found.<br>";
            } else {
                patt = Integer.parseInt(request.getParameter("password"));
                u.setPwdattempt(patt);
                if(!u.isAuthenticated()) {
                    msg = "Member found but not Authenticated.<br>";
                } else {
                    msg = "Member Authenticated!<br>";
                    URL = "/StoreSelection.jsp";
                }
                request.getSession().setAttribute("u", u);
            }
//            List of available stores
            stores = StoreDB.getStoresList();
            if(stores == null) {
                msg = "Store List returned empty.<br>";
            } else {
                 request.getSession().setAttribute("stores", stores);
            }
        } catch(NumberFormatException e) {
            msg = "Password not numeric.<br>";
        } catch(Exception e) {
            msg = "Servlet Exception: " + e.getMessage() + "<br>";
        }
        request.setAttribute("msg", msg);
        Cookie uid = new Cookie("userid", userid);
        uid.setMaxAge(60*10);
        uid.setPath("/");
        response.addCookie(uid);
        RequestDispatcher disp =
                getServletContext().getRequestDispatcher(URL);
        disp.forward(request, response);
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
