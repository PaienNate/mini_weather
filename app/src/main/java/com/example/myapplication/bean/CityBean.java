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
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        @SerializedName("code")
        private String code;
        @SerializedName("name")
        private String name;
        @SerializedName("pchilds")
        private List<PchildsBean> pchilds;

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

        public List<PchildsBean> getPchilds() {
            return pchilds;
        }

        public void setPchilds(List<PchildsBean> pchilds) {
            this.pchilds = pchilds;
        }

        public static class PchildsBean {
            @SerializedName("code")
            private String code;
            @SerializedName("name")
            private String name;
            @SerializedName("cchilds")
            private List<CchildsBean> cchilds;

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

            public List<CchildsBean> getCchilds() {
                return cchilds;
            }

            public void setCchilds(List<CchildsBean> cchilds) {
                this.cchilds = cchilds;
            }

            public static class CchildsBean {
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
