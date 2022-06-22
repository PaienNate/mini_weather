package com.example.myapplication.bean;

import com.google.gson.annotations.SerializedName;

public class Ip2CityBean {
    /**
     * ret
     */
    @SerializedName("ret")
    private Integer ret;
    /**
     * errMsg
     */
    @SerializedName("errMsg")
    private String errMsg;
    /**
     * ip
     */
    @SerializedName("ip")
    private String ip;
    /**
     * provcode
     */
    @SerializedName("provcode")
    private String provcode;
    /**
     * citycode
     */
    @SerializedName("citycode")
    private String citycode;
    /**
     * country
     */
    @SerializedName("country")
    private String country;
    /**
     * province
     */
    @SerializedName("province")
    private String province;
    /**
     * city
     */
    @SerializedName("city")
    private String city;
    /**
     * district
     */
    @SerializedName("district")
    private String district;
    /**
     * isp
     */
    @SerializedName("isp")
    private String isp;
    /**
     * districtCode
     */
    @SerializedName("districtCode")
    private String districtCode;

    public Integer getRet() {
        return ret;
    }

    public void setRet(Integer ret) {
        this.ret = ret;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getProvcode() {
        return provcode;
    }

    public void setProvcode(String provcode) {
        this.provcode = provcode;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }
}
