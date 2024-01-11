package com.example.zerobasestudy22;

import org.mariadb.jdbc.Connection;

import java.sql.*;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class HistoryService {

    public void insertHistory(String x, String y) throws ClassNotFoundException, SQLException {
        PreparedStatement preparedStatement = null;

        Class.forName("org.mariadb.jdbc.Driver");
        Connection connection = (Connection) DriverManager.getConnection("jdbc:mariadb://localhost:3306/db1", "root", "1234");


        String sql = "insert into history (`x-coordinate`, `y-coordinate`, checkdate)\n" +
                     "values (?, ?, now())";

        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, x);
        preparedStatement.setString(2, y);

        int affected = preparedStatement.executeUpdate();

        if (affected > 0) {
            System.out.println("history등록 완료");
        } else {
            System.out.println("history등록 실패");
        }

        connection.close();

    }

    public List<HistoryInfo> selectHistory() throws ClassNotFoundException, SQLException {
        List<HistoryInfo> history = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        Statement statement = null;
        Class.forName("org.mariadb.jdbc.Driver");
        Connection connection = (Connection) DriverManager.getConnection("jdbc:mariadb://localhost:3306/db1", "root", "1234");
        statement = connection.createStatement();

        String sql1 = "set @count = 0";
        statement.executeQuery(sql1);

        String sql2 = "update history set Id = @count:=@count+1";
        statement.executeQuery(sql2);

        String sql = "select *\n" +
                "from history\n" +
                "order by ID DESC";

        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            int ID = rs.getInt("ID");
            String x = rs.getString("x-coordinate");
            String y = rs.getString("y-coordinate");
            LocalDateTime checkDate = LocalDateTime.parse(rs.getString("checkDate"),formatter);

            HistoryInfo info = new HistoryInfo(ID, x, y, checkDate);
            history.add(info);
        }

        connection.close();

        return history;
    }


}

