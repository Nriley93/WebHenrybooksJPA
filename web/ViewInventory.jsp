<%-- 
    Document   : ViewInventory
    Created on : Apr 16, 2020, 8:30:55 PM
    Author     : n.riley
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inventory</title>
    </head>
    <body>
        <br>
        Branch #: <b>${store.storeid}</b><br>
        Branch Name: <b>${store.storename}</b><br>
        Branch Location: <b>${store.storeaddr}</b>
        <br>
        ${msg}
        <c:if test="${u.adminlevel == 'Admn'}">
            <form action="ViewInventory" method="post">
                <input type="hidden" name="action" value="update" >
                <b><p>Book Id:
                        <input type="text" name="bookid" id="bookid">
                    <input type="submit" value="Edit Record"> 
                </p></b>
            </form>
        </c:if>
        
        <table border="1">      
            <tr>
                <th>Store</th>
                <th>Book Id</th>
                <th>Title</th>
                <th>Retail Price</th>
                <th>Quantity</th>
            </tr>
            <c:forEach var="b" items="${books}">
                <tr>
                    <td align="right">${b.storeid}</td>
                    <td align="right">${b.bookid}</td>
                    <td align="left">${b.title}</td>
                    <td align="right">${b.pricefmt}</td>
                    <td align="right">${b.onhand}</td>
                </tr>
            </c:forEach>
        </table>
        <a href="StoreSelection.jsp">Back to Stores</a>
    </body>
</html>
