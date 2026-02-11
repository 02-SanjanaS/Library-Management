package com.wipro.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wipro.book.bean.AuthorBean;
import com.wipro.book.util.DBUtil;

public class AuthorDAO {

    public AuthorBean getAuthor(String authorName) {

        Connection connection = DBUtil.getDBConnection();
        String query = "SELECT * FROM AUTHOR_TBL WHERE AUTHOR_NAME = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, authorName);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                AuthorBean author = new AuthorBean();
                author.setAuthorCode(rs.getInt("AUTHOR_CODE"));
                author.setAuthorName(rs.getString("AUTHOR_NAME"));
                author.setContactNo(rs.getLong("CONTACT_NO"));
                return author;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public AuthorBean getAuthor(int authorCode) {

        Connection connection = DBUtil.getDBConnection();
        String query = "SELECT * FROM AUTHOR_TBL WHERE AUTHOR_CODE = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, authorCode);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                AuthorBean author = new AuthorBean();
                author.setAuthorCode(rs.getInt("AUTHOR_CODE"));
                author.setAuthorName(rs.getString("AUTHOR_NAME"));
                author.setContactNo(rs.getLong("CONTACT_NO"));
                return author;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
