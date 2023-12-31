package com.example.zerobasestudy22;

import java.time.LocalDateTime;

public class WifiInfo {

    private double distance;
    private String mgr_no;
    private String wrdofc;
    private String main_nm;
    private String adres1;
    private String adres2;
    private String instl_ty;
    private String instl_mby;
    private String svc_se;
    private String cmcwr;
    private String cnstc_year;
    private String inout_door;
    private String lat;
    private String lnt;
    private LocalDateTime work_dttm;

    public WifiInfo() {

    }
    public WifiInfo(String mgr_no, String wrdofc, String main_nm, String adres1, String adres2, String instl_ty, String instl_mby, String svc_se, String cmcwr, String cnstc_year, String inout_door, String lat, String lnt, LocalDateTime work_dttm) {

        this.mgr_no = mgr_no;
        this.wrdofc = wrdofc;
        this.main_nm = main_nm;
        this.adres1 = adres1;
        this.adres2 = adres2;
        this.instl_ty = instl_ty;
        this.instl_mby = instl_mby;
        this.svc_se = svc_se;
        this.cmcwr = cmcwr;
        this.cnstc_year = cnstc_year;
        this.inout_door = inout_door;
        this.lat = lat;
        this.lnt = lnt;
        this.work_dttm = work_dttm;

    }

    public WifiInfo(double distance, String mgr_no, String wrdofc, String main_nm, String adres1, String adres2, String instl_ty, String instl_mby, String svc_se, String cmcwr, String cnstc_year, String inout_door, String lat, String lnt, LocalDateTime work_dttm) {

        this.distance = distance;
        this.mgr_no = mgr_no;
        this.wrdofc = wrdofc;
        this.main_nm = main_nm;
        this.adres1 = adres1;
        this.adres2 = adres2;
        this.instl_ty = instl_ty;
        this.instl_mby = instl_mby;
        this.svc_se = svc_se;
        this.cmcwr = cmcwr;
        this.cnstc_year = cnstc_year;
        this.inout_door = inout_door;
        this.lat = lat;
        this.lnt = lnt;
        this.work_dttm = work_dttm;

    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getMgr_no() {
        return mgr_no;
    }

    public void setMgr_no(String mgr_no) {
        this.mgr_no = mgr_no;
    }

    public String getWrdofc() {
        return wrdofc;
    }

    public void setWrdofc(String wrdofc) {
        this.wrdofc = wrdofc;
    }

    public String getMain_nm() {
        return main_nm;
    }

    public void setMain_nm(String main_nm) {
        this.main_nm = main_nm;
    }

    public String getAdres1() {
        return adres1;
    }

    public void setAdres1(String adres1) {
        this.adres1 = adres1;
    }

    public String getAdres2() {
        return adres2;
    }

    public void setAdres2(String adres2) {
        this.adres2 = adres2;
    }

    public String getInstl_ty() {
        return instl_ty;
    }

    public void setInstl_ty(String instl_ty) {
        this.instl_ty = instl_ty;
    }

    public String getInstl_mby() {
        return instl_mby;
    }

    public void setInstl_mby(String instl_mby) {
        this.instl_mby = instl_mby;
    }

    public String getSvc_se() {
        return svc_se;
    }

    public void setSvc_se(String svc_se) {
        this.svc_se = svc_se;
    }

    public String getCmcwr() {
        return cmcwr;
    }

    public void setCmcwr(String cmcwr) {
        this.cmcwr = cmcwr;
    }

    public String getCnstc_year() {
        return cnstc_year;
    }

    public void setCnstc_year(String cnstc_year) {
        this.cnstc_year = cnstc_year;
    }

    public String getInout_door() {
        return inout_door;
    }

    public void setInout_door(String inout_door) {
        this.inout_door = inout_door;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLnt() {
        return lnt;
    }

    public void setLnt(String lnt) {
        this.lnt = lnt;
    }

    public LocalDateTime getWork_dttm() {
        return work_dttm;
    }

    public void setWork_dttm(LocalDateTime work_dttm) {
        this.work_dttm = work_dttm;
    }
}
