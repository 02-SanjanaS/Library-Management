package com.wipro.book.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wipro.book.bean.AuthorBean;
import com.wipro.book.bean.BookBean;
import com.wipro.book.dao.AuthorDAO;
import com.wipro.book.service.Administrator;

@WebServlet("/Mainservlet")
public class MainServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String operation = request.getParameter("operation");

        if ("AddBook".equals(operation)) {

            String result = addBook(request);

            if ("SUCCESS".equals(result))
                response.sendRedirect("menu.html");
            else if ("INVALID".equals(result))
                response.sendRedirect("Invalid.html");
            else
                response.sendRedirect("failure.html");

        } else if ("Search".equals(operation)) {

            String isbn = request.getParameter("isbn");
            BookBean book = new Administrator().viewBook(isbn);

            if (book == null)
                response.sendRedirect("Invalid.html");
            else {
                HttpSession session = request.getSession();
                session.setAttribute("book", book);

                RequestDispatcher rd = request.getRequestDispatcher("/ViewServlet");
                rd.forward(request, response);
            }
        }
    }

    private String addBook(HttpServletRequest request) {

        try {
            String isbn = request.getParameter("isbn");
            String bookName = request.getParameter("bookName");
            char bookType = request.getParameter("bookType").charAt(0);
            String authorName = request.getParameter("authorName");
            float cost = Float.parseFloat(request.getParameter("cost"));

            AuthorDAO dao = new AuthorDAO();
            AuthorBean author = dao.getAuthor(authorName);

            if (author == null) return "INVALID";

            BookBean book = new BookBean();
            book.setIsbn(isbn);
            book.setBookName(bookName);
            book.setBookType(bookType);
            book.setCost(cost);
            book.setAuthor(author);

            return new Administrator().addBook(book);

        } catch (Exception e) {
            e.printStackTrace();
            return "FAILURE";
        }
    }
}
