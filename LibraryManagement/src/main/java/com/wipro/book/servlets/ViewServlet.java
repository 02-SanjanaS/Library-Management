package com.wipro.book.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wipro.book.bean.BookBean;

@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        BookBean book = (BookBean) session.getAttribute("book");

        out.print("<html><body>");
        out.print("<h2>Book Details</h2>");
        out.print("Title : " + book.getBookName() + "<br>");
        out.print("Author : " + book.getAuthor().getAuthorName() + "<br>");
        out.print("Contact : " + book.getAuthor().getContactNo() + "<br>");
        out.print("ISBN : " + book.getIsbn() + "<br>");
        out.print("</body></html>");
    }
}
