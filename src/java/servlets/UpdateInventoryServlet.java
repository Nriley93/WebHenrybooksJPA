package servlets;

import business.BookDB;
import business.BookInv;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author n.riley
 */
public class UpdateInventoryServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String URL="/UpdateInv.jsp";
        String msg="";
        int Onhand;
    
        try {           
            BookInv book = (BookInv) request.getSession().getAttribute("book");
        try {
                Onhand = Integer.parseInt(request.getParameter("update"));
                if(Onhand >= 100 & Onhand <= 0) {
                    msg="Invalid Amount.<br>";
                } else {
                    book.setOnhand(Onhand);
                }
            } catch(NumberFormatException e) {
                msg = "OnHand Error: " + e.getMessage() + "<br>";
            }
            if(msg.isEmpty()) { 
                BookDB.updtOnHand(book);
                msg+="OnHand was updated!<br>";
            }    
        } catch(Exception e){
            msg+="Servlet Error: " + e.getMessage() + "<br>";
        }
        request.setAttribute("msg", msg);
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
