<%-- 
    Document   : UpdateInv
    Created on : Apr 19, 2020, 10:36:41 PM
    Author     : n.riley
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inventory Update</title>
    </head>
    <body>
        <p>
            Branch #: <b>${store.storeid}</b><br>
            Branch Name: <b>${store.storename}</b><br>
            Branch Location: <b>${store.storeaddr}</b>
        </p>
        ${msg}
        <p>
            Book id: <b>${book.bookid}</b><br>
            Book Title: <b>${book.title}</b><br>
            Author: <b>${book.author}</b><br>
            On Hand: <b>${book.onhand}</b>
        </p>
        <form action="UpdateInventory" method="post" >
            <p>
                Inventory On hand in Branch 
                <input type="text" name="update" id="update"><br>
                <input type="submit" value="Update Inventory">
            </p>
        </form>
        
        <form action="StoreSelection.jsp" method="post">
            <input type="submit" value="Cancel">
        </form>
    </body>
</html>

