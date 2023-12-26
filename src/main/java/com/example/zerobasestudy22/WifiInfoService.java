package com.example.zerobasestudy22;
import org.mariadb.jdbc.Connection;
import java.sql.*;
import java.util.List;

import static java.lang.Class.forName;

public class WifiInfoService {
    public boolean register(WifiInfo info) throws SQLException, ClassNotFoundException {

//        String url = "jdbc:mariadb://localhost:3306/db1";
//        String userName = "root";
//        String password = "1234";

        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        Class.forName("org.mariadb.jdbc.Driver");
        Connection connection = (Connection) DriverManager.getConnection("jdbc:mariadb://localhost:3306/db1","root","1234");


        String sql = "insert into wifi (mgr_no,wrdofc,main_nm,adres1,adres2,instl_ty,instl_mby,svc_se,cmcwr,cnstc_year,inout_door,lat,lnt,work_dttm) " +
                "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,info.getMgr_no());
        preparedStatement.setString(2,info.getWrdofc());
        preparedStatement.setString(3,info.getMain_nm());
        preparedStatement.setString(4,info.getAdres1());
        preparedStatement.setString(5,info.getAdres2());
        preparedStatement.setString(6,info.getInstl_ty());
        preparedStatement.setString(7,info.getInstl_mby());
        preparedStatement.setString(8,info.getSvc_se());
        preparedStatement.setString(9,info.getCmcwr());
        preparedStatement.setString(10,info.getCnstc_year());
        preparedStatement.setString(11,info.getInout_door());
        preparedStatement.setString(12,info.getLat());
        preparedStatement.setString(13,info.getLnt());
        preparedStatement.setTimestamp(14,Timestamp.valueOf(info.getWork_dttm()));


        int affected = preparedStatement.executeUpdate();

        connection.close();
        if (affected > 0) {
            return true;
        } else {
            return false;
        }

    }

    public void deleteWifi() throws SQLException, ClassNotFoundException {
//        String url = "jdbc:mariadb://localhost:3306/db1";
//        String userName = "root";
//        String password = "1234";

        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        Class.forName("org.mariadb.jdbc.Driver");
        Connection connection = (Connection) DriverManager.getConnection("jdbc:mariadb://localhost:3306/db1","root","1234");
        statement = connection.createStatement();

        String sql = "delete from wifi ";

        //삭제시 쿼리수행 성공여부 숫자로 반환
        int affected = statement.executeUpdate(sql);

        if (affected > 0) {
            System.out.println("삭제 성공");
        } else {
            System.out.println("삭제 실패");
        }
        connection.close();

    }

    public List<WifiInfo> getList() {

        return null;
    }



}
