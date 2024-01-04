package com.example.zerobasestudy22;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;

import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GetWifi {
    static int totalCnt;
    public int getCount() throws ParseException, SQLException {
        String key = "6e616e764c7374613535704d437466";

        URL url = null;
        HttpURLConnection con= null;
        JSONObject result = null;
        StringBuilder sb = new StringBuilder();
        int start = 1;
        int end = 1;
        String baseUrl = "http://openapi.seoul.go.kr:8088/" + key + "/" +
                "json/TbPublicWifiInfo/"+ start + "/"+end+"/";

        try {
            url = new URL(baseUrl);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-type", "application/json");
            con.setDoOutput(true);

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            while(br.ready()) {
                sb.append(br.readLine());
            }
            con.disconnect();
        }catch(Exception e) {
            e.printStackTrace();
        }

        result = (JSONObject) new JSONParser().parse(sb.toString());

        StringBuilder out = new StringBuilder();

        JSONObject data = (JSONObject) result.get("TbPublicWifiInfo");
        totalCnt = Integer.parseInt(data.get("list_total_count").toString());
//        System.out.println(data);
//        System.out.println(totalCnt);
        
        return totalCnt;
    }

    public void getData() throws ParseException, SQLException, ClassNotFoundException {
        WifiInfoService service = new WifiInfoService();
        service.deleteWifi();
        List<WifiInfo> wifiList = new ArrayList<>();

        int start = 0;
        int end = 0;
        int total = 0;
        DateTimeFormatter sFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        String key = "6e616e764c7374613535704d437466";

        totalCnt = getCount();
        int pageEnd = totalCnt / 1000;
        int remain = totalCnt % 1000;

        for (int i = 0; i <= pageEnd; i++) {
            start = (int) 1000 * i + 1;

            if (i == pageEnd) {
                end = start + remain - 1;
            } else {
                end = (int) 1000 * (i+1);
            }

            String baseUrl = "http://openapi.seoul.go.kr:8088/" + key + "/" +
                    "json/TbPublicWifiInfo/"+ start + "/"+end+"/";

            URL url = null;
            HttpURLConnection con = null;
            JSONObject result = null;
            StringBuilder sb = new StringBuilder();

            try {
                url = new URL(baseUrl);
                con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("Content-type", "application/json");
                con.setDoOutput(true);

                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                while (br.ready()) {
                    sb.append(br.readLine());
                }
                con.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
            result = (JSONObject) new JSONParser().parse(sb.toString());

            JSONObject data = (JSONObject) result.get("TbPublicWifiInfo");
            JSONArray array = (JSONArray) data.get("row");


            for (int j = 0; j < array.size(); j++) {
                JSONObject object = (JSONObject) array.get(j);
                String mgr_no = (String) object.get("X_SWIFI_MGR_NO");
                String wrdofc = (String) object.get("X_SWIFI_WRDOFC");
                String main_nm = (String) object.get("X_SWIFI_MAIN_NM");
                String adres1 = (String) object.get("X_SWIFI_ADRES1");
                String adres2 = (String) object.get("X_SWIFI_ADRES2");
                String instl_ty = (String) object.get("X_SWIFI_INSTL_TY");
                String instl_mby = (String) object.get("X_SWIFI_INSTL_MBY");
                String svc_se = (String) object.get("X_SWIFI_SVC_SE");
                String cmcwr = (String) object.get("X_SWIFI_CMCWR");
                String cnstc_year = (String) object.get("X_SWIFI_CNSTC_YEAR");
                String inout_door = (String) object.get("X_SWIFI_INOUT_DOOR");
                String lat = (String) object.get("LAT");
                String lnt = (String) object.get("LNT");
                String time = (String) object.get("WORK_DTTM");
                LocalDateTime work_dttm = LocalDateTime.parse(time,sFormat);

                WifiInfo info = new WifiInfo(mgr_no,wrdofc,main_nm,adres1,adres2,instl_ty,instl_mby,svc_se,cmcwr,cnstc_year,inout_door,lat,lnt,work_dttm);
                wifiList.add(info);

            }
        }
        service.register(wifiList);
    }
}
//    WifiInfo wifiInfo = new WifiInfo();
//
//                wifiInfo.setMgr_no(mgr_no);
//                        wifiInfo.setWrdofc(wrdofc);
//                        wifiInfo.setMain_nm(main_nm);
//                        wifiInfo.setAdres1(adres1);
//                        wifiInfo.setAdres2(adres2);
//                        wifiInfo.setInstl_ty(instl_ty);
//                        wifiInfo.setInstl_mby(instl_mby);
//                        wifiInfo.setSvc_se(svc_se);
//                        wifiInfo.setCmcwr(cmcwr);
//                        wifiInfo.setCnstc_year(cnstc_year);
//                        wifiInfo.setInout_door(inout_door);
//                        wifiInfo.setLat(lat);
//                        wifiInfo.setLnt(lnt);
//                        wifiInfo.setWork_dttm(work_dttm);