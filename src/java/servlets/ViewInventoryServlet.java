package servlets;

import business.BookDB;
import business.BookInv;
import business.StoreDB;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author n.riley
 */
public class ViewInventoryServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String msg="", bookid;
        String URL="/ViewInventory.jsp";
        String action = request.getParameter("action");
        int storeid;
        if(action == null) {action = "Inventory";}
//        View Store Inventory
        if(action.equals("Inventory")) {
            try {
                storeid = Integer.parseInt(request.getParameter("storeid"));
                request.getSession().setAttribute("storeid", storeid);
                if(StoreDB.storeFound(storeid)) {
                    request.getSession().setAttribute
                                        ("store", StoreDB.getStore(storeid));
                }
                List<BookInv> books;
                books = BookDB.getBooksList(storeid);
                if(books == null) {
                    msg = "Inventory List returned Empty.<br>";
                } else {
                    request.getSession().setAttribute("books", books);
                }
            } catch(NumberFormatException e) {
                URL = "/StoreSelection.jsp";
                msg += "Inventory Error: " + e.getMessage();
            }
//            If User is 'Admin'
        } else if(action.equals("update")) {
//            Select Book for update
            try {
                storeid = (int) request.getSession().getAttribute("storeid");
                bookid = request.getParameter("bookid").trim();
                if(BookDB.bookFound(bookid, storeid)) {
                    URL = "/UpdateInv.jsp";
                    msg = "Book found!";
                    request.getSession().
                            setAttribute("book", BookDB.getBook(bookid,storeid));
                } else {
                    URL = "/StoreSelection.jsp";
                    msg = "Could not find book.";
                }
            } catch(Exception e) {
                msg += "record Error: " + e.getMessage() + ".<br>";
            }  
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