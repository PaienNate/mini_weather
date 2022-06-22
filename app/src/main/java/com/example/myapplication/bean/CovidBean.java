package com.example.myapplication.bean;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CovidBean {


    /**
     * ret
     */
    @SerializedName("ret")
    private Integer ret;
    /**
     * info
     */
    @SerializedName("info")
    private String info;
    /**
     * data
     */
    @SerializedName("data")
    private List<DataBean> data;

    public Integer getRet() {
        return ret;
    }

    public void setRet(Integer ret) {
        this.ret = ret;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * year
         */
        @SerializedName("year")
        private Integer year;
        /**
         * date
         */
        @SerializedName("date")
        private String date;
        /**
         * 国家
         */
        @SerializedName("country")
        private String country;
        /**
         * 省份(如果用城市，就是City)
         * 因为用城市还是用省份不确定（或者可能都用），所以设置如下，允许为空
         */
        //
        @Nullable
        @SerializedName("province")
        private String province;



        @Nullable
        @SerializedName("city")
        private String city;
        /**
         * 确诊
         */
        @SerializedName("confirm")
        private Integer confirm;
        /**
         * 去世
         */

        @SerializedName("dead")
        private Integer dead;
        /**
         * 治好了
         */
        @SerializedName("heal")
        private Integer heal;
        /**
         * 又加了的数量
         */
        @SerializedName("confirm_add")
        private String confirmAdd;
        /**
         * confirmCuts
         */
        @SerializedName("confirm_cuts")
        private String confirmCuts;
        /**
         * deadCuts
         */
        @SerializedName("dead_cuts")
        private String deadCuts;
        /**
         * nowConfirmCuts
         */
        @SerializedName("now_confirm_cuts")
        private String nowConfirmCuts;
        /**
         * healCuts
         */
        @SerializedName("heal_cuts")
        private String healCuts;
        /**
         * newConfirm
         */
        @SerializedName("newConfirm")
        private Integer newConfirm;
        /**
         * newHeal
         */
        @SerializedName("newHeal")
        private Integer newHeal;
        /**
         * newDead
         */
        @SerializedName("newDead")
        private Integer newDead;
        /**
         * description
         */
        @SerializedName("description")
        private String description;
        /**
         * wzz
         */
        @SerializedName("wzz")
        private Integer wzz;
        /**
         * wzzAdd
         */
        @SerializedName("wzz_add")
        private Integer wzzAdd;
        /**
         * 获取时间
         */
        @SerializedName("_mtime")
        private String mtime;
        /**
         * allLocalConfirmAdd
         */
        @SerializedName("all_local_confirm_add")
        private Integer allLocalConfirmAdd;
        /**
         * deadAdd
         */
        @SerializedName("deadAdd")
        private Integer deadAdd;


        @Nullable
        public String getCity() {
            return city;
        }

        public void setCity(@Nullable String city) {
            this.city = city;
        }
        public Integer getYear() {
            return year;
        }

        public void setYear(Integer year) {
            this.year = year;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }
        //同样加上如此所示……
        @Nullable
        public String getProvince() {
            return province;
        }

        public void setProvince( @Nullable String province) {
            this.province = province;
        }

        public Integer getConfirm() {
            return confirm;
        }

        public void setConfirm(Integer confirm) {
            this.confirm = confirm;
        }

        public Integer getDead() {
            return dead;
        }

        public void setDead(Integer dead) {
            this.dead = dead;
        }

        public Integer getHeal() {
            return heal;
        }

        public void setHeal(Integer heal) {
            this.heal = heal;
        }

        public String getConfirmAdd() {
            return confirmAdd;
        }

        public void setConfirmAdd(String confirmAdd) {
            this.confirmAdd = confirmAdd;
        }

        public String getConfirmCuts() {
            return confirmCuts;
        }

        public void setConfirmCuts(String confirmCuts) {
            this.confirmCuts = confirmCuts;
        }

        public String getDeadCuts() {
            return deadCuts;
        }

        public void setDeadCuts(String deadCuts) {
            this.deadCuts = deadCuts;
        }

        public String getNowConfirmCuts() {
            return nowConfirmCuts;
        }

        public void setNowConfirmCuts(String nowConfirmCuts) {
            this.nowConfirmCuts = nowConfirmCuts;
        }

        public String getHealCuts() {
            return healCuts;
        }

        public void setHealCuts(String healCuts) {
            this.healCuts = healCuts;
        }

        public Integer getNewConfirm() {
            return newConfirm;
        }

        public void setNewConfirm(Integer newConfirm) {
            this.newConfirm = newConfirm;
        }

        public Integer getNewHeal() {
            return newHeal;
        }

        public void setNewHeal(Integer newHeal) {
            this.newHeal = newHeal;
        }

        public Integer getNewDead() {
            return newDead;
        }

        public void setNewDead(Integer newDead) {
            this.newDead = newDead;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Integer getWzz() {
            return wzz;
        }

        public void setWzz(Integer wzz) {
            this.wzz = wzz;
        }

        public Integer getWzzAdd() {
            return wzzAdd;
        }

        public void setWzzAdd(Integer wzzAdd) {
            this.wzzAdd = wzzAdd;
        }

        public String getMtime() {
            return mtime;
        }

        public void setMtime(String mtime) {
            this.mtime = mtime;
        }

        public Integer getAllLocalConfirmAdd() {
            return allLocalConfirmAdd;
        }

        public void setAllLocalConfirmAdd(Integer allLocalConfirmAdd) {
            this.allLocalConfirmAdd = allLocalConfirmAdd;
        }

        public Integer getDeadAdd() {
            return deadAdd;
        }

        public void setDeadAdd(Integer deadAdd) {
            this.deadAdd = deadAdd;
        }
    }
}
