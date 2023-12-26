package db.test;

import com.example.zerobasestudy22.WifiInfo;

import java.sql.*;

public class DbTest {
    public void dbSelect() throws SQLException {
        String url = "jdbc:mariadb://localhost:3306/db1";
        String userName = "root";
        String password = "1234";

        Statement statement = null;
        ResultSet rs = null;

        Connection connection = DriverManager.getConnection(url, userName, password);
        statement = connection.createStatement();

        String sql = "select * from member";


        rs = statement.executeQuery(sql);

        while (rs.next()) {
            String id = rs.getString("id");
            String name = rs.getString("name");
            String pwd = rs.getString("password");
            System.out.println(id + " " + name + " " + pwd);


            rs.close();
            connection.close();
        }
    }

    public void dbSelect2() throws SQLException {
        String url = "jdbc:mariadb://localhost:3306/db1";
        String userName = "root";
        String password = "1234";

        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        Connection connection = DriverManager.getConnection(url, userName, password);

        String sql = "select * " +
                "from member " +
                "where name = ?";

        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "jj");
        rs = preparedStatement.executeQuery();

        while (rs.next()) {
            String id = rs.getString("id");
            String name = rs.getString("name");
            System.out.println(id + " " +name);

            rs.close();
            rs.close();
            connection.close();
        }
    }

    public void dbInsert() throws SQLException {
        String url = "jdbc:mariadb://localhost:3306/db1";
        String userName = "root";
        String password = "1234";

        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        String idIn = "cvbbn";
        String nameIn = "tonff";
        String passwordIn = "1234";


        Connection connection = DriverManager.getConnection(url, userName, password);


        String sql = "insert into member (id, name, password) values (?,?,?)";

        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,idIn);
        preparedStatement.setString(2,nameIn);
        preparedStatement.setString(3,passwordIn);

        int affected = preparedStatement.executeUpdate();

        if (affected > 0) {
            System.out.println("입력 성공");
        } else {
            System.out.println("입력 실패");
        }
    }
    public void dbInsert2(WifiInfo info) throws SQLException {
        String url = "jdbc:mariadb://localhost:3306/db1";
        String userName = "root";
        String password = "1234";

        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        Connection connection = DriverManager.getConnection(url, userName, password);


        String sql = "insert into member (mgr_no, wrdofc, main_nm) values (?,?,?)";

        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,info.getMgr_no());
        preparedStatement.setString(2,info.getWrdofc());
        preparedStatement.setString(3,info.getMain_nm());

        int affected = preparedStatement.executeUpdate();

        if (affected > 0) {
            System.out.println("입력 성공");
        } else {
            System.out.println("입력 실패");
        }
    }

    public void dbUpdate() throws SQLException {
        String url = "jdbc:mariadb://localhost:3306/db1";
        String userName = "root";
        String password = "1234";

        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        String idIn = "asdf";
        String nameIn = "tt44";
        //String passwordIn = "1234";


        Connection connection = DriverManager.getConnection(url, userName, password);


        String sql = "update member set name = ? where id = ?";

        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,nameIn);
        preparedStatement.setString(2,idIn);


        int affected = preparedStatement.executeUpdate();

        if (affected > 0) {
            System.out.println("수정 성공");
        } else {
            System.out.println("수정 실패");
        }
    }

    public void dbDelete() throws SQLException {
        String url = "jdbc:mariadb://localhost:3306/db1";
        String userName = "root";
        String password = "1234";

        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        String idIn = "yu";
        //String nameIn = "tt44";
        //String passwordIn = "1234";


        Connection connection = DriverManager.getConnection(url, userName, password);


        String sql = "delete from member where id = ?";

        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,idIn);


        //삭제시 쿼리수행 성공여부 숫자로 반환
        int affected = preparedStatement.executeUpdate();

        if (affected > 0) {
            System.out.println("삭제 성공");
        } else {
            System.out.println("삭제 실패");
        }

        rs.close();
        rs.close();
        connection.close();
    }


}
