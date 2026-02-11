package com.wipro.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wipro.book.bean.BookBean;
import com.wipro.book.util.DBUtil;

public class BookDAO {

	public int createBook(BookBean bookBean) {

	    Connection connection = DBUtil.getDBConnection();

	    String query = "INSERT INTO BOOK_TABLE (ISBN, BOOK_TITLE, BOOK_TYPE, AUTHOR_CODE, BOOK_COST) VALUES (?,?,?,?,?)";

	    try {
	        PreparedStatement ps = connection.prepareStatement(query);

	        ps.setString(1, bookBean.getIsbn());
	        ps.setString(2, bookBean.getBookName());
	        ps.setString(3, String.valueOf(bookBean.getBookType()));
	        ps.setInt(4, bookBean.getAuthor().getAuthorCode());
	        ps.setFloat(5, bookBean.getCost());

	        return ps.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return 0;
	}


    public BookBean fetchBook(String isbn) {

        Connection connection = DBUtil.getDBConnection();
        String query = "SELECT * FROM BOOK_TABLE WHERE ISBN = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, isbn);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                BookBean book = new BookBean();
                book.setIsbn(rs.getString("ISBN"));
                book.setBookName(rs.getString("BOOK_TITLE"));
                book.setBookType(rs.getString("BOOK_TYPE").charAt(0));
                book.setCost(rs.getFloat("BOOK_COST"));
                book.setAuthor(new AuthorDAO().getAuthor(rs.getInt("AUTHOR_CODE")));
                return book;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
