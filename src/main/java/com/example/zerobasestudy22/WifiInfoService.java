package com.example.zerobasestudy22;
import org.mariadb.jdbc.Connection;
import java.sql.*;
import java.util.List;

import static java.lang.Class.forName;

public class WifiInfoService {
    static int count;
    public String register(List<WifiInfo> wifiList) throws SQLException, ClassNotFoundException {

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
        for (WifiInfo pInfo : wifiList) {
            preparedStatement.setString(1, pInfo.getMgr_no());
            preparedStatement.setString(2, pInfo.getWrdofc());
            preparedStatement.setString(3, pInfo.getMain_nm());
            preparedStatement.setString(4, pInfo.getAdres1());
            preparedStatement.setString(5, pInfo.getAdres2());
            preparedStatement.setString(6, pInfo.getInstl_ty());
            preparedStatement.setString(7, pInfo.getInstl_mby());
            preparedStatement.setString(8, pInfo.getSvc_se());
            preparedStatement.setString(9, pInfo.getCmcwr());
            preparedStatement.setString(10, pInfo.getCnstc_year());
            preparedStatement.setString(11, pInfo.getInout_door());
            preparedStatement.setString(12, pInfo.getLat());
            preparedStatement.setString(13, pInfo.getLnt());
            preparedStatement.setTimestamp(14, Timestamp.valueOf(pInfo.getWork_dttm()));


            int affected = preparedStatement.executeUpdate();
            count++;
        }

        connection.close();

        return String.valueOf(count);

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
