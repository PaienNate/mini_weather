package com.example.myapplication.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CovidNewsBean {


    /**
     * data
     */
    @SerializedName("data")
    private DataBean data;
    /**
     * errno
     */
    @SerializedName("errno")
    private Integer errno;
    /**
     * errmsg
     */
    @SerializedName("errmsg")
    private String errmsg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public Integer getErrno() {
        return errno;
    }

    public void setErrno(Integer errno) {
        this.errno = errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public static class DataBean {
        /**
         * items
         */
        @SerializedName("items")
        private List<ItemsBean> items;

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
            /**
             * title
             */
            @SerializedName("title")
            private String title;
            /**
             * publishTime
             */
            @SerializedName("publish_time")
            private String publishTime;
            /**
             * newsUrl
             */
            @SerializedName("news_url")
            private String newsUrl;
            /**
             * srcfrom
             */
            @SerializedName("srcfrom")
            private String srcfrom;
            /**
             * shortcut
             */
            @SerializedName("shortcut")
            private String shortcut;
            /**
             * cmsId
             */
            @SerializedName("cms_id")
            private String cmsId;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(String publishTime) {
                this.publishTime = publishTime;
            }

            public String getNewsUrl() {
                return newsUrl;
            }

            public void setNewsUrl(String newsUrl) {
                this.newsUrl = newsUrl;
            }

            public String getSrcfrom() {
                return srcfrom;
            }

            public void setSrcfrom(String srcfrom) {
                this.srcfrom = srcfrom;
            }

            public String getShortcut() {
                return shortcut;
            }

            public void setShortcut(String shortcut) {
                this.shortcut = shortcut;
            }

            public String getCmsId() {
                return cmsId;
            }

            public void setCmsId(String cmsId) {
                this.cmsId = cmsId;
            }
        }
    }
}
