package com.example.zerobasestudy22;
import org.mariadb.jdbc.Connection;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Class.forName;

public class WifiInfoService {
    static int count;

    public String register(List<WifiInfo> wifiList) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = null;

        Class.forName("org.mariadb.jdbc.Driver");
        Connection connection = (Connection) DriverManager.getConnection("jdbc:mariadb://localhost:3306/db1", "root", "1234");


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

        Statement statement = null;

        Class.forName("org.mariadb.jdbc.Driver");
        Connection connection = (Connection) DriverManager.getConnection("jdbc:mariadb://localhost:3306/db1", "root", "1234");
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

    public List<WifiInfo> getList(String myLat, String myLnt) throws ClassNotFoundException, SQLException, ParseException {
        List<WifiInfo> list = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


        Statement statement = null;
        Class.forName("org.mariadb.jdbc.Driver");
        Connection connection = (Connection) DriverManager.getConnection("jdbc:mariadb://localhost:3306/db1", "root", "1234");
        statement = connection.createStatement();

        String sql = "select * from wifi";

        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            //double distance = Double.parseDouble(rs.getString("distance"));
            String mgr_no = rs.getString("mgr_no");
            String wrdofc = rs.getString("wrdofc");
            String main_nm = rs.getString("main_nm");
            String adres1 = rs.getString("adres1");
            String adres2 = rs.getString("adres2");
            String instl_ty = rs.getString("instl_ty");
            String instl_mby = rs.getString("instl_mby");
            String svc_se = rs.getString("svc_se");
            String cmcwr = rs.getString("cmcwr");
            String cnstc_year = rs.getString("cnstc_year");
            String inout_door = rs.getString("inout_door");
            String lat = rs.getString("lat");
            String lnt = rs.getString("lnt");
            LocalDateTime work_dttm = LocalDateTime.parse(rs.getString("work_dttm"), formatter);

            WifiInfo info = new WifiInfo(mgr_no, wrdofc, main_nm, adres1, adres2, instl_ty, instl_mby, svc_se, cmcwr, cnstc_year, inout_door, lat, lnt, work_dttm);
            list.add(info);
        }
        connection.close();

        for (WifiInfo info : list) {
            //내좌표, DB로우들 비교하여 거리계산
            double distance = calDistance(myLat, myLnt, info.getLat(), info.getLnt());
            info.setDistance(distance);
            //거리칼럼 DB에 업데이트
            if (distance < 2.0) {
                updateDistance(info.getMgr_no(), distance);
            }
        }

        //가까운 상위20개 wifi리스트 조회.
        List<WifiInfo> nearWifiList = nearWifiList();


        return nearWifiList;
    }
    public void updateDistance(String mgr_no, double distance) throws ClassNotFoundException, SQLException {

        PreparedStatement preparedStatement = null;

        Class.forName("org.mariadb.jdbc.Driver");
        Connection connection = (Connection) DriverManager.getConnection("jdbc:mariadb://localhost:3306/db1", "root", "1234");


        String sql = "update wifi\n" +
                     "set distance = ?\n" +
                     "where mgr_no = ?"
                     ;

        preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setDouble(1, distance);
        preparedStatement.setString(2, mgr_no);

        int affected = preparedStatement.executeUpdate();


        connection.close();
    }

    public List<WifiInfo> nearWifiList() throws ClassNotFoundException, SQLException {
        List<WifiInfo> nearWifiList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        Statement statement = null;
        Class.forName("org.mariadb.jdbc.Driver");
        Connection connection = (Connection) DriverManager.getConnection("jdbc:mariadb://localhost:3306/db1", "root", "1234");
        statement = connection.createStatement();

        String sql = "select *\n" +
                "from wifi\n" +
                "where distance is not null\n" +
                "order by distance\n" +
                "limit 20";

        ResultSet rs = statement.executeQuery(sql);


        while (rs.next()) {
            double distance = Double.parseDouble(rs.getString("distance"));
            String mgr_no = rs.getString("mgr_no");
            String wrdofc = rs.getString("wrdofc");
            String main_nm = rs.getString("main_nm");
            String adres1 = rs.getString("adres1");
            String adres2 = rs.getString("adres2");
            String instl_ty = rs.getString("instl_ty");
            String instl_mby = rs.getString("instl_mby");
            String svc_se = rs.getString("svc_se");
            String cmcwr = rs.getString("cmcwr");
            String cnstc_year = rs.getString("cnstc_year");
            String inout_door = rs.getString("inout_door");
            String lat = rs.getString("lat");
            String lnt = rs.getString("lnt");
            LocalDateTime work_dttm = LocalDateTime.parse(rs.getString("work_dttm"), formatter);

            WifiInfo info = new WifiInfo(distance, mgr_no, wrdofc, main_nm, adres1, adres2, instl_ty, instl_mby, svc_se, cmcwr, cnstc_year, inout_door, lat, lnt, work_dttm);
            nearWifiList.add(info);
        }

        connection.close();

        return nearWifiList;
    }


    public double calDistance(String myLat, String myLnt, String lat, String lnt) {
        double dMyLat = Double.parseDouble(myLat);
        double dMyLnt = Double.parseDouble(myLnt);
        double dLat = Double.parseDouble(lat);
        double dLnt = Double.parseDouble(lnt);


        double theta = dMyLnt - dLnt;
        double dist = Math.sin(deg2rad(dMyLat)) * Math.sin(deg2rad(dLat)) + Math.cos(deg2rad(dMyLat)) * Math.cos(deg2rad(dLat)) * Math.cos(deg2rad(theta));

        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;

//        if (unit == "kilometer") {
//            dist = dist * 1.609344;
//        } else if (unit == "meter") {
//            dist = dist * 1609.344;
//        }
        dist = dist * 1.609344;//kilometer

        return Math.round(dist * 10000) / 10000.0;

    }

    // This function converts decimal degrees to radians
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    // This function converts radians to decimal degrees
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }


}

