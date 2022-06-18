package com.example.myapplication.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

//获取城市信息 - 自动生成Bean
public class CityBean {
    @SerializedName("code")
    private Integer code;
    @SerializedName("msg")
    private String msg;
    @SerializedName("data")
    private List<Province> province;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Province> getProvince() {
        return province;
    }

    public void setProvince(List<Province> province) {
        this.province = province;
    }

    public static class Province {
        //添加自增主键
        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @SerializedName("code")
        private String code;
        @SerializedName("name")
        private String name;
        @SerializedName("pchilds")
        private List<City> pchilds;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<City> getPchilds() {
            return pchilds;
        }

        public void setPchilds(List<City> pchilds) {
            this.pchilds = pchilds;
        }

        public static class City {
            //添加自增主键
            private int id;
            //添加所在省的ID
            private int provinceId;

            public int getProvinceId() {
                return provinceId;
            }

            public void setProvinceId(int provinceId) {
                this.provinceId = provinceId;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            @SerializedName("code")
            private String code;
            @SerializedName("name")
            private String name;
            @SerializedName("cchilds")
            private List<County> cchilds;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<County> getCchilds() {
                return cchilds;
            }

            public void setCchilds(List<County> cchilds) {
                this.cchilds = cchilds;
            }

            public static class County {
                //添加自增主键
                private int id;
                //添加cityId
                private int cityId;

                public int getCityId() {
                    return cityId;
                }

                public void setCityId(int cityId) {
                    this.cityId = cityId;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                @SerializedName("code")
                private String code;
                @SerializedName("name")
                private String name;

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }
    }
}
