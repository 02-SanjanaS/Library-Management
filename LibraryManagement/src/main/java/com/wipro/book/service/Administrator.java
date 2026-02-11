package com.wipro.book.service;

import com.wipro.book.bean.BookBean;
import com.wipro.book.dao.BookDAO;

public class Administrator {

    public String addBook(BookBean bookBean) {

        if (bookBean == null ||
            bookBean.getIsbn() == null || bookBean.getIsbn().isEmpty() ||
            bookBean.getBookName() == null || bookBean.getBookName().isEmpty() ||
            bookBean.getAuthor() == null ||
            (bookBean.getBookType() != 'G' && bookBean.getBookType() != 'T') ||
            bookBean.getCost() <= 0) {

            return "INVALID";
        }

        BookDAO dao = new BookDAO();
        return dao.createBook(bookBean) > 0 ? "SUCCESS" : "FAILURE";
    }

    public BookBean viewBook(String isbn) {
        return new BookDAO().fetchBook(isbn);
    }
}
