package com.example.myapplication.bean;

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
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * diseaseh5Shelf
         */
        @SerializedName("diseaseh5Shelf")
        private Diseaseh5ShelfBean diseaseh5Shelf;
        /**
         * localCityNCOVDataList
         */
        @SerializedName("localCityNCOVDataList")
        private List<LocalCityNCOVDataListBean> localCityNCOVDataList;

        public Diseaseh5ShelfBean getDiseaseh5Shelf() {
            return diseaseh5Shelf;
        }

        public void setDiseaseh5Shelf(Diseaseh5ShelfBean diseaseh5Shelf) {
            this.diseaseh5Shelf = diseaseh5Shelf;
        }

        public List<LocalCityNCOVDataListBean> getLocalCityNCOVDataList() {
            return localCityNCOVDataList;
        }

        public void setLocalCityNCOVDataList(List<LocalCityNCOVDataListBean> localCityNCOVDataList) {
            this.localCityNCOVDataList = localCityNCOVDataList;
        }

        public static class Diseaseh5ShelfBean {
            /**
             * isShowAdd
             */
            @SerializedName("isShowAdd")
            private Boolean isShowAdd;
            /**
             * showAddSwitch
             */
            @SerializedName("showAddSwitch")
            private ShowAddSwitchBean showAddSwitch;
            /**
             * areaTree
             */
            @SerializedName("areaTree")
            private List<AreaTreeBean> areaTree;
            /**
             * lastUpdateTime
             */
            @SerializedName("lastUpdateTime")
            private String lastUpdateTime;
            /**
             * chinaTotal
             */
            @SerializedName("chinaTotal")
            private ChinaTotalBean chinaTotal;
            /**
             * chinaAdd
             */
            @SerializedName("chinaAdd")
            private ChinaAddBean chinaAdd;

            public Boolean getIsShowAdd() {
                return isShowAdd;
            }

            public void setIsShowAdd(Boolean isShowAdd) {
                this.isShowAdd = isShowAdd;
            }

            public ShowAddSwitchBean getShowAddSwitch() {
                return showAddSwitch;
            }

            public void setShowAddSwitch(ShowAddSwitchBean showAddSwitch) {
                this.showAddSwitch = showAddSwitch;
            }

            public List<AreaTreeBean> getAreaTree() {
                return areaTree;
            }

            public void setAreaTree(List<AreaTreeBean> areaTree) {
                this.areaTree = areaTree;
            }

            public String getLastUpdateTime() {
                return lastUpdateTime;
            }

            public void setLastUpdateTime(String lastUpdateTime) {
                this.lastUpdateTime = lastUpdateTime;
            }

            public ChinaTotalBean getChinaTotal() {
                return chinaTotal;
            }

            public void setChinaTotal(ChinaTotalBean chinaTotal) {
                this.chinaTotal = chinaTotal;
            }

            public ChinaAddBean getChinaAdd() {
                return chinaAdd;
            }

            public void setChinaAdd(ChinaAddBean chinaAdd) {
                this.chinaAdd = chinaAdd;
            }

            public static class ShowAddSwitchBean {
                /**
                 * confirm
                 */
                @SerializedName("confirm")
                private Boolean confirm;
                /**
                 * suspect
                 */
                @SerializedName("suspect")
                private Boolean suspect;
                /**
                 * importedCase
                 */
                @SerializedName("importedCase")
                private Boolean importedCase;
                /**
                 * noInfect
                 */
                @SerializedName("noInfect")
                private Boolean noInfect;
                /**
                 * localinfeciton
                 */
                @SerializedName("localinfeciton")
                private Boolean localinfeciton;
                /**
                 * all
                 */
                @SerializedName("all")
                private Boolean all;
                /**
                 * dead
                 */
                @SerializedName("dead")
                private Boolean dead;
                /**
                 * heal
                 */
                @SerializedName("heal")
                private Boolean heal;
                /**
                 * nowConfirm
                 */
                @SerializedName("nowConfirm")
                private Boolean nowConfirm;
                /**
                 * nowSevere
                 */
                @SerializedName("nowSevere")
                private Boolean nowSevere;
                /**
                 * localConfirm
                 */
                @SerializedName("localConfirm")
                private Boolean localConfirm;

                public Boolean getConfirm() {
                    return confirm;
                }

                public void setConfirm(Boolean confirm) {
                    this.confirm = confirm;
                }

                public Boolean getSuspect() {
                    return suspect;
                }

                public void setSuspect(Boolean suspect) {
                    this.suspect = suspect;
                }

                public Boolean getImportedCase() {
                    return importedCase;
                }

                public void setImportedCase(Boolean importedCase) {
                    this.importedCase = importedCase;
                }

                public Boolean getNoInfect() {
                    return noInfect;
                }

                public void setNoInfect(Boolean noInfect) {
                    this.noInfect = noInfect;
                }

                public Boolean getLocalinfeciton() {
                    return localinfeciton;
                }

                public void setLocalinfeciton(Boolean localinfeciton) {
                    this.localinfeciton = localinfeciton;
                }

                public Boolean getAll() {
                    return all;
                }

                public void setAll(Boolean all) {
                    this.all = all;
                }

                public Boolean getDead() {
                    return dead;
                }

                public void setDead(Boolean dead) {
                    this.dead = dead;
                }

                public Boolean getHeal() {
                    return heal;
                }

                public void setHeal(Boolean heal) {
                    this.heal = heal;
                }

                public Boolean getNowConfirm() {
                    return nowConfirm;
                }

                public void setNowConfirm(Boolean nowConfirm) {
                    this.nowConfirm = nowConfirm;
                }

                public Boolean getNowSevere() {
                    return nowSevere;
                }

                public void setNowSevere(Boolean nowSevere) {
                    this.nowSevere = nowSevere;
                }

                public Boolean getLocalConfirm() {
                    return localConfirm;
                }

                public void setLocalConfirm(Boolean localConfirm) {
                    this.localConfirm = localConfirm;
                }
            }

            public static class ChinaTotalBean {
                /**
                 * heal
                 */
                @SerializedName("heal")
                private Integer heal;
                /**
                 * noInfectH5
                 */
                @SerializedName("noInfectH5")
                private Integer noInfectH5;
                /**
                 * localConfirmH5
                 */
                @SerializedName("localConfirmH5")
                private Integer localConfirmH5;
                /**
                 * nowLocalWzz
                 */
                @SerializedName("nowLocalWzz")
                private Integer nowLocalWzz;
                /**
                 * mediumRiskAreaNum
                 */
                @SerializedName("mediumRiskAreaNum")
                private Integer mediumRiskAreaNum;
                /**
                 * localConfirm
                 */
                @SerializedName("localConfirm")
                private Integer localConfirm;
                /**
                 * mRiskTime
                 */
                @SerializedName("mRiskTime")
                private String mRiskTime;
                /**
                 * importedCase
                 */
                @SerializedName("importedCase")
                private Integer importedCase;
                /**
                 * showLocalConfirm
                 */
                @SerializedName("showLocalConfirm")
                private Integer showLocalConfirm;
                /**
                 * confirmAdd
                 */
                @SerializedName("confirmAdd")
                private Integer confirmAdd;
                /**
                 * mtime
                 */
                @SerializedName("mtime")
                private String mtime;
                /**
                 * localConfirmAdd
                 */
                @SerializedName("localConfirmAdd")
                private Integer localConfirmAdd;
                /**
                 * highRiskAreaNum
                 */
                @SerializedName("highRiskAreaNum")
                private Integer highRiskAreaNum;
                /**
                 * dead
                 */
                @SerializedName("dead")
                private Integer dead;
                /**
                 * suspect
                 */
                @SerializedName("suspect")
                private Integer suspect;
                /**
                 * showlocalinfeciton
                 */
                @SerializedName("showlocalinfeciton")
                private Integer showlocalinfeciton;
                /**
                 * localAccConfirm
                 */
                @SerializedName("local_acc_confirm")
                private Integer localAccConfirm;
                /**
                 * confirm
                 */
                @SerializedName("confirm")
                private Integer confirm;
                /**
                 * nowSevere
                 */
                @SerializedName("nowSevere")
                private Integer nowSevere;
                /**
                 * noInfect
                 */
                @SerializedName("noInfect")
                private Integer noInfect;
                /**
                 * localWzzAdd
                 */
                @SerializedName("localWzzAdd")
                private Integer localWzzAdd;
                /**
                 * deadAdd
                 */
                @SerializedName("deadAdd")
                private Integer deadAdd;
                /**
                 * nowConfirm
                 */
                @SerializedName("nowConfirm")
                private Integer nowConfirm;

                public Integer getHeal() {
                    return heal;
                }

                public void setHeal(Integer heal) {
                    this.heal = heal;
                }

                public Integer getNoInfectH5() {
                    return noInfectH5;
                }

                public void setNoInfectH5(Integer noInfectH5) {
                    this.noInfectH5 = noInfectH5;
                }

                public Integer getLocalConfirmH5() {
                    return localConfirmH5;
                }

                public void setLocalConfirmH5(Integer localConfirmH5) {
                    this.localConfirmH5 = localConfirmH5;
                }

                public Integer getNowLocalWzz() {
                    return nowLocalWzz;
                }

                public void setNowLocalWzz(Integer nowLocalWzz) {
                    this.nowLocalWzz = nowLocalWzz;
                }

                public Integer getMediumRiskAreaNum() {
                    return mediumRiskAreaNum;
                }

                public void setMediumRiskAreaNum(Integer mediumRiskAreaNum) {
                    this.mediumRiskAreaNum = mediumRiskAreaNum;
                }

                public Integer getLocalConfirm() {
                    return localConfirm;
                }

                public void setLocalConfirm(Integer localConfirm) {
                    this.localConfirm = localConfirm;
                }

                public String getMRiskTime() {
                    return mRiskTime;
                }

                public void setMRiskTime(String mRiskTime) {
                    this.mRiskTime = mRiskTime;
                }

                public Integer getImportedCase() {
                    return importedCase;
                }

                public void setImportedCase(Integer importedCase) {
                    this.importedCase = importedCase;
                }

                public Integer getShowLocalConfirm() {
                    return showLocalConfirm;
                }

                public void setShowLocalConfirm(Integer showLocalConfirm) {
                    this.showLocalConfirm = showLocalConfirm;
                }

                public Integer getConfirmAdd() {
                    return confirmAdd;
                }

                public void setConfirmAdd(Integer confirmAdd) {
                    this.confirmAdd = confirmAdd;
                }

                public String getMtime() {
                    return mtime;
                }

                public void setMtime(String mtime) {
                    this.mtime = mtime;
                }

                public Integer getLocalConfirmAdd() {
                    return localConfirmAdd;
                }

                public void setLocalConfirmAdd(Integer localConfirmAdd) {
                    this.localConfirmAdd = localConfirmAdd;
                }

                public Integer getHighRiskAreaNum() {
                    return highRiskAreaNum;
                }

                public void setHighRiskAreaNum(Integer highRiskAreaNum) {
                    this.highRiskAreaNum = highRiskAreaNum;
                }

                public Integer getDead() {
                    return dead;
                }

                public void setDead(Integer dead) {
                    this.dead = dead;
                }

                public Integer getSuspect() {
                    return suspect;
                }

                public void setSuspect(Integer suspect) {
                    this.suspect = suspect;
                }

                public Integer getShowlocalinfeciton() {
                    return showlocalinfeciton;
                }

                public void setShowlocalinfeciton(Integer showlocalinfeciton) {
                    this.showlocalinfeciton = showlocalinfeciton;
                }

                public Integer getLocalAccConfirm() {
                    return localAccConfirm;
                }

                public void setLocalAccConfirm(Integer localAccConfirm) {
                    this.localAccConfirm = localAccConfirm;
                }

                public Integer getConfirm() {
                    return confirm;
                }

                public void setConfirm(Integer confirm) {
                    this.confirm = confirm;
                }

                public Integer getNowSevere() {
                    return nowSevere;
                }

                public void setNowSevere(Integer nowSevere) {
                    this.nowSevere = nowSevere;
                }

                public Integer getNoInfect() {
                    return noInfect;
                }

                public void setNoInfect(Integer noInfect) {
                    this.noInfect = noInfect;
                }

                public Integer getLocalWzzAdd() {
                    return localWzzAdd;
                }

                public void setLocalWzzAdd(Integer localWzzAdd) {
                    this.localWzzAdd = localWzzAdd;
                }

                public Integer getDeadAdd() {
                    return deadAdd;
                }

                public void setDeadAdd(Integer deadAdd) {
                    this.deadAdd = deadAdd;
                }

                public Integer getNowConfirm() {
                    return nowConfirm;
                }

                public void setNowConfirm(Integer nowConfirm) {
                    this.nowConfirm = nowConfirm;
                }
            }

            public static class ChinaAddBean {
                /**
                 * noInfectH5
                 */
                @SerializedName("noInfectH5")
                private Integer noInfectH5;
                /**
                 * localConfirmH5
                 */
                @SerializedName("localConfirmH5")
                private Integer localConfirmH5;
                /**
                 * heal
                 */
                @SerializedName("heal")
                private Integer heal;
                /**
                 * dead
                 */
                @SerializedName("dead")
                private Integer dead;
                /**
                 * nowConfirm
                 */
                @SerializedName("nowConfirm")
                private Integer nowConfirm;
                /**
                 * nowSevere
                 */
                @SerializedName("nowSevere")
                private Integer nowSevere;
                /**
                 * noInfect
                 */
                @SerializedName("noInfect")
                private Integer noInfect;
                /**
                 * confirm
                 */
                @SerializedName("confirm")
                private Integer confirm;
                /**
                 * suspect
                 */
                @SerializedName("suspect")
                private Integer suspect;
                /**
                 * importedCase
                 */
                @SerializedName("importedCase")
                private Integer importedCase;
                /**
                 * localConfirm
                 */
                @SerializedName("localConfirm")
                private Integer localConfirm;

                public Integer getNoInfectH5() {
                    return noInfectH5;
                }

                public void setNoInfectH5(Integer noInfectH5) {
                    this.noInfectH5 = noInfectH5;
                }

                public Integer getLocalConfirmH5() {
                    return localConfirmH5;
                }

                public void setLocalConfirmH5(Integer localConfirmH5) {
                    this.localConfirmH5 = localConfirmH5;
                }

                public Integer getHeal() {
                    return heal;
                }

                public void setHeal(Integer heal) {
                    this.heal = heal;
                }

                public Integer getDead() {
                    return dead;
                }

                public void setDead(Integer dead) {
                    this.dead = dead;
                }

                public Integer getNowConfirm() {
                    return nowConfirm;
                }

                public void setNowConfirm(Integer nowConfirm) {
                    this.nowConfirm = nowConfirm;
                }

                public Integer getNowSevere() {
                    return nowSevere;
                }

                public void setNowSevere(Integer nowSevere) {
                    this.nowSevere = nowSevere;
                }

                public Integer getNoInfect() {
                    return noInfect;
                }

                public void setNoInfect(Integer noInfect) {
                    this.noInfect = noInfect;
                }

                public Integer getConfirm() {
                    return confirm;
                }

                public void setConfirm(Integer confirm) {
                    this.confirm = confirm;
                }

                public Integer getSuspect() {
                    return suspect;
                }

                public void setSuspect(Integer suspect) {
                    this.suspect = suspect;
                }

                public Integer getImportedCase() {
                    return importedCase;
                }

                public void setImportedCase(Integer importedCase) {
                    this.importedCase = importedCase;
                }

                public Integer getLocalConfirm() {
                    return localConfirm;
                }

                public void setLocalConfirm(Integer localConfirm) {
                    this.localConfirm = localConfirm;
                }
            }

            public static class AreaTreeBean {
                /**
                 * name
                 */
                @SerializedName("name")
                private String name;
                /**
                 * today
                 */
                @SerializedName("today")
                private TodayBean today;
                /**
                 * total
                 */
                @SerializedName("total")
                private TotalBean total;
                /**
                 * children
                 */
                @SerializedName("children")
                private List<ChildrenBean> children;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public TodayBean getToday() {
                    return today;
                }

                public void setToday(TodayBean today) {
                    this.today = today;
                }

                public TotalBean getTotal() {
                    return total;
                }

                public void setTotal(TotalBean total) {
                    this.total = total;
                }

                public List<ChildrenBean> getChildren() {
                    return children;
                }

                public void setChildren(List<ChildrenBean> children) {
                    this.children = children;
                }

                public static class TodayBean {
                    /**
                     * confirm
                     */
                    @SerializedName("confirm")
                    private Integer confirm;
                    /**
                     * isUpdated
                     */
                    @SerializedName("isUpdated")
                    private Boolean isUpdated;

                    public Integer getConfirm() {
                        return confirm;
                    }

                    public void setConfirm(Integer confirm) {
                        this.confirm = confirm;
                    }

                    public Boolean getIsUpdated() {
                        return isUpdated;
                    }

                    public void setIsUpdated(Boolean isUpdated) {
                        this.isUpdated = isUpdated;
                    }
                }

                public static class TotalBean {
                    /**
                     * nowConfirm
                     */
                    @SerializedName("nowConfirm")
                    private Integer nowConfirm;
                    /**
                     * wzz
                     */
                    @SerializedName("wzz")
                    private Integer wzz;
                    /**
                     * continueDayZeroLocalConfirm
                     */
                    @SerializedName("continueDayZeroLocalConfirm")
                    private Integer continueDayZeroLocalConfirm;
                    /**
                     * heal
                     */
                    @SerializedName("heal")
                    private Integer heal;
                    /**
                     * continueDayZeroLocalConfirmAdd
                     */
                    @SerializedName("continueDayZeroLocalConfirmAdd")
                    private Integer continueDayZeroLocalConfirmAdd;
                    /**
                     * confirm
                     */
                    @SerializedName("confirm")
                    private Integer confirm;
                    /**
                     * dead
                     */
                    @SerializedName("dead")
                    private Integer dead;
                    /**
                     * provinceLocalConfirm
                     */
                    @SerializedName("provinceLocalConfirm")
                    private Integer provinceLocalConfirm;
                    /**
                     * highRiskAreaNum
                     */
                    @SerializedName("highRiskAreaNum")
                    private Integer highRiskAreaNum;
                    /**
                     * mtime
                     */
                    @SerializedName("mtime")
                    private String mtime;
                    /**
                     * showRate
                     */
                    @SerializedName("showRate")
                    private Boolean showRate;
                    /**
                     * showHeal
                     */
                    @SerializedName("showHeal")
                    private Boolean showHeal;
                    /**
                     * mediumRiskAreaNum
                     */
                    @SerializedName("mediumRiskAreaNum")
                    private Integer mediumRiskAreaNum;
                    /**
                     * adcode
                     */
                    @SerializedName("adcode")
                    private String adcode;

                    public Integer getNowConfirm() {
                        return nowConfirm;
                    }

                    public void setNowConfirm(Integer nowConfirm) {
                        this.nowConfirm = nowConfirm;
                    }

                    public Integer getWzz() {
                        return wzz;
                    }

                    public void setWzz(Integer wzz) {
                        this.wzz = wzz;
                    }

                    public Integer getContinueDayZeroLocalConfirm() {
                        return continueDayZeroLocalConfirm;
                    }

                    public void setContinueDayZeroLocalConfirm(Integer continueDayZeroLocalConfirm) {
                        this.continueDayZeroLocalConfirm = continueDayZeroLocalConfirm;
                    }

                    public Integer getHeal() {
                        return heal;
                    }

                    public void setHeal(Integer heal) {
                        this.heal = heal;
                    }

                    public Integer getContinueDayZeroLocalConfirmAdd() {
                        return continueDayZeroLocalConfirmAdd;
                    }

                    public void setContinueDayZeroLocalConfirmAdd(Integer continueDayZeroLocalConfirmAdd) {
                        this.continueDayZeroLocalConfirmAdd = continueDayZeroLocalConfirmAdd;
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

                    public Integer getProvinceLocalConfirm() {
                        return provinceLocalConfirm;
                    }

                    public void setProvinceLocalConfirm(Integer provinceLocalConfirm) {
                        this.provinceLocalConfirm = provinceLocalConfirm;
                    }

                    public Integer getHighRiskAreaNum() {
                        return highRiskAreaNum;
                    }

                    public void setHighRiskAreaNum(Integer highRiskAreaNum) {
                        this.highRiskAreaNum = highRiskAreaNum;
                    }

                    public String getMtime() {
                        return mtime;
                    }

                    public void setMtime(String mtime) {
                        this.mtime = mtime;
                    }

                    public Boolean getShowRate() {
                        return showRate;
                    }

                    public void setShowRate(Boolean showRate) {
                        this.showRate = showRate;
                    }

                    public Boolean getShowHeal() {
                        return showHeal;
                    }

                    public void setShowHeal(Boolean showHeal) {
                        this.showHeal = showHeal;
                    }

                    public Integer getMediumRiskAreaNum() {
                        return mediumRiskAreaNum;
                    }

                    public void setMediumRiskAreaNum(Integer mediumRiskAreaNum) {
                        this.mediumRiskAreaNum = mediumRiskAreaNum;
                    }

                    public String getAdcode() {
                        return adcode;
                    }

                    public void setAdcode(String adcode) {
                        this.adcode = adcode;
                    }
                }

                public static class ChildrenBean {
                    /**
                     * name
                     */
                    @SerializedName("name")
                    private String name;
                    /**
                     * adcode
                     */
                    @SerializedName("adcode")
                    private String adcode;
                    /**
                     * date
                     */
                    @SerializedName("date")
                    private String date;
                    /**
                     * today
                     */
                    @SerializedName("today")
                    private TodayBean today;
                    /**
                     * total
                     */
                    @SerializedName("total")
                    private TotalBean total;
                    /**
                     * children
                     */
                    @SerializedName("children")
                    private List<_ChildrenBean> children;

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getAdcode() {
                        return adcode;
                    }

                    public void setAdcode(String adcode) {
                        this.adcode = adcode;
                    }

                    public String getDate() {
                        return date;
                    }

                    public void setDate(String date) {
                        this.date = date;
                    }

                    public TodayBean getToday() {
                        return today;
                    }

                    public void setToday(TodayBean today) {
                        this.today = today;
                    }

                    public TotalBean getTotal() {
                        return total;
                    }

                    public void setTotal(TotalBean total) {
                        this.total = total;
                    }

                    public List<_ChildrenBean> getChildren() {
                        return children;
                    }

                    public void setChildren(List<_ChildrenBean> children) {
                        this.children = children;
                    }

                    public static class TodayBean {
                        /**
                         * abroadConfirmAdd
                         */
                        @SerializedName("abroad_confirm_add")
                        private Integer abroadConfirmAdd;
                        /**
                         * deadAdd
                         */
                        @SerializedName("dead_add")
                        private Integer deadAdd;
                        /**
                         * confirm
                         */
                        @SerializedName("confirm")
                        private Integer confirm;
                        /**
                         * confirmCuts
                         */
                        @SerializedName("confirmCuts")
                        private Integer confirmCuts;
                        /**
                         * isUpdated
                         */
                        @SerializedName("isUpdated")
                        private Boolean isUpdated;
                        /**
                         * tip
                         */
                        @SerializedName("tip")
                        private String tip;
                        /**
                         * wzzAdd
                         */
                        @SerializedName("wzz_add")
                        private Integer wzzAdd;
                        /**
                         * localConfirmAdd
                         */
                        @SerializedName("local_confirm_add")
                        private Integer localConfirmAdd;

                        public Integer getAbroadConfirmAdd() {
                            return abroadConfirmAdd;
                        }

                        public void setAbroadConfirmAdd(Integer abroadConfirmAdd) {
                            this.abroadConfirmAdd = abroadConfirmAdd;
                        }

                        public Integer getDeadAdd() {
                            return deadAdd;
                        }

                        public void setDeadAdd(Integer deadAdd) {
                            this.deadAdd = deadAdd;
                        }

                        public Integer getConfirm() {
                            return confirm;
                        }

                        public void setConfirm(Integer confirm) {
                            this.confirm = confirm;
                        }

                        public Integer getConfirmCuts() {
                            return confirmCuts;
                        }

                        public void setConfirmCuts(Integer confirmCuts) {
                            this.confirmCuts = confirmCuts;
                        }

                        public Boolean getIsUpdated() {
                            return isUpdated;
                        }

                        public void setIsUpdated(Boolean isUpdated) {
                            this.isUpdated = isUpdated;
                        }

                        public String getTip() {
                            return tip;
                        }

                        public void setTip(String tip) {
                            this.tip = tip;
                        }

                        public Integer getWzzAdd() {
                            return wzzAdd;
                        }

                        public void setWzzAdd(Integer wzzAdd) {
                            this.wzzAdd = wzzAdd;
                        }

                        public Integer getLocalConfirmAdd() {
                            return localConfirmAdd;
                        }

                        public void setLocalConfirmAdd(Integer localConfirmAdd) {
                            this.localConfirmAdd = localConfirmAdd;
                        }
                    }

                    public static class TotalBean {
                        /**
                         * nowConfirm
                         */
                        @SerializedName("nowConfirm")
                        private Integer nowConfirm;
                        /**
                         * dead
                         */
                        @SerializedName("dead")
                        private Integer dead;
                        /**
                         * showHeal
                         */
                        @SerializedName("showHeal")
                        private Boolean showHeal;
                        /**
                         * adcode
                         */
                        @SerializedName("adcode")
                        private String adcode;
                        /**
                         * continueDayZeroConfirm
                         */
                        @SerializedName("continueDayZeroConfirm")
                        private Integer continueDayZeroConfirm;
                        /**
                         * continueDayZeroLocalConfirmAdd
                         */
                        @SerializedName("continueDayZeroLocalConfirmAdd")
                        private Integer continueDayZeroLocalConfirmAdd;
                        /**
                         * confirm
                         */
                        @SerializedName("confirm")
                        private Integer confirm;
                        /**
                         * showRate
                         */
                        @SerializedName("showRate")
                        private Boolean showRate;
                        /**
                         * heal
                         */
                        @SerializedName("heal")
                        private Integer heal;
                        /**
                         * provinceLocalConfirm
                         */
                        @SerializedName("provinceLocalConfirm")
                        private Integer provinceLocalConfirm;
                        /**
                         * highRiskAreaNum
                         */
                        @SerializedName("highRiskAreaNum")
                        private Integer highRiskAreaNum;
                        /**
                         * wzz
                         */
                        @SerializedName("wzz")
                        private Integer wzz;
                        /**
                         * mediumRiskAreaNum
                         */
                        @SerializedName("mediumRiskAreaNum")
                        private Integer mediumRiskAreaNum;
                        /**
                         * continueDayZeroConfirmAdd
                         */
                        @SerializedName("continueDayZeroConfirmAdd")
                        private Integer continueDayZeroConfirmAdd;
                        /**
                         * mtime
                         */
                        @SerializedName("mtime")
                        private String mtime;

                        public Integer getNowConfirm() {
                            return nowConfirm;
                        }

                        public void setNowConfirm(Integer nowConfirm) {
                            this.nowConfirm = nowConfirm;
                        }

                        public Integer getDead() {
                            return dead;
                        }

                        public void setDead(Integer dead) {
                            this.dead = dead;
                        }

                        public Boolean getShowHeal() {
                            return showHeal;
                        }

                        public void setShowHeal(Boolean showHeal) {
                            this.showHeal = showHeal;
                        }

                        public String getAdcode() {
                            return adcode;
                        }

                        public void setAdcode(String adcode) {
                            this.adcode = adcode;
                        }

                        public Integer getContinueDayZeroConfirm() {
                            return continueDayZeroConfirm;
                        }

                        public void setContinueDayZeroConfirm(Integer continueDayZeroConfirm) {
                            this.continueDayZeroConfirm = continueDayZeroConfirm;
                        }

                        public Integer getContinueDayZeroLocalConfirmAdd() {
                            return continueDayZeroLocalConfirmAdd;
                        }

                        public void setContinueDayZeroLocalConfirmAdd(Integer continueDayZeroLocalConfirmAdd) {
                            this.continueDayZeroLocalConfirmAdd = continueDayZeroLocalConfirmAdd;
                        }

                        public Integer getConfirm() {
                            return confirm;
                        }

                        public void setConfirm(Integer confirm) {
                            this.confirm = confirm;
                        }

                        public Boolean getShowRate() {
                            return showRate;
                        }

                        public void setShowRate(Boolean showRate) {
                            this.showRate = showRate;
                        }

                        public Integer getHeal() {
                            return heal;
                        }

                        public void setHeal(Integer heal) {
                            this.heal = heal;
                        }

                        public Integer getProvinceLocalConfirm() {
                            return provinceLocalConfirm;
                        }

                        public void setProvinceLocalConfirm(Integer provinceLocalConfirm) {
                            this.provinceLocalConfirm = provinceLocalConfirm;
                        }

                        public Integer getHighRiskAreaNum() {
                            return highRiskAreaNum;
                        }

                        public void setHighRiskAreaNum(Integer highRiskAreaNum) {
                            this.highRiskAreaNum = highRiskAreaNum;
                        }

                        public Integer getWzz() {
                            return wzz;
                        }

                        public void setWzz(Integer wzz) {
                            this.wzz = wzz;
                        }

                        public Integer getMediumRiskAreaNum() {
                            return mediumRiskAreaNum;
                        }

                        public void setMediumRiskAreaNum(Integer mediumRiskAreaNum) {
                            this.mediumRiskAreaNum = mediumRiskAreaNum;
                        }

                        public Integer getContinueDayZeroConfirmAdd() {
                            return continueDayZeroConfirmAdd;
                        }

                        public void setContinueDayZeroConfirmAdd(Integer continueDayZeroConfirmAdd) {
                            this.continueDayZeroConfirmAdd = continueDayZeroConfirmAdd;
                        }

                        public String getMtime() {
                            return mtime;
                        }

                        public void setMtime(String mtime) {
                            this.mtime = mtime;
                        }
                    }

                    public static class _ChildrenBean {
                        /**
                         * name
                         */
                        @SerializedName("name")
                        private String name;
                        /**
                         * adcode
                         */
                        @SerializedName("adcode")
                        private String adcode;
                        /**
                         * date
                         */
                        @SerializedName("date")
                        private String date;
                        /**
                         * today
                         */
                        @SerializedName("today")
                        private TodayBean today;
                        /**
                         * total
                         */
                        @SerializedName("total")
                        private TotalBean total;

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }

                        public String getAdcode() {
                            return adcode;
                        }

                        public void setAdcode(String adcode) {
                            this.adcode = adcode;
                        }

                        public String getDate() {
                            return date;
                        }

                        public void setDate(String date) {
                            this.date = date;
                        }

                        public TodayBean getToday() {
                            return today;
                        }

                        public void setToday(TodayBean today) {
                            this.today = today;
                        }

                        public TotalBean getTotal() {
                            return total;
                        }

                        public void setTotal(TotalBean total) {
                            this.total = total;
                        }

                        public static class TodayBean {
                            /**
                             * localConfirmAdd
                             */
                            @SerializedName("local_confirm_add")
                            private Integer localConfirmAdd;
                            /**
                             * confirm
                             */
                            @SerializedName("confirm")
                            private Integer confirm;
                            /**
                             * confirmCuts
                             */
                            @SerializedName("confirmCuts")
                            private Integer confirmCuts;
                            /**
                             * isUpdated
                             */
                            @SerializedName("isUpdated")
                            private Boolean isUpdated;
                            /**
                             * wzzAdd
                             */
                            @SerializedName("wzz_add")
                            private String wzzAdd;

                            public Integer getLocalConfirmAdd() {
                                return localConfirmAdd;
                            }

                            public void setLocalConfirmAdd(Integer localConfirmAdd) {
                                this.localConfirmAdd = localConfirmAdd;
                            }

                            public Integer getConfirm() {
                                return confirm;
                            }

                            public void setConfirm(Integer confirm) {
                                this.confirm = confirm;
                            }

                            public Integer getConfirmCuts() {
                                return confirmCuts;
                            }

                            public void setConfirmCuts(Integer confirmCuts) {
                                this.confirmCuts = confirmCuts;
                            }

                            public Boolean getIsUpdated() {
                                return isUpdated;
                            }

                            public void setIsUpdated(Boolean isUpdated) {
                                this.isUpdated = isUpdated;
                            }

                            public String getWzzAdd() {
                                return wzzAdd;
                            }

                            public void setWzzAdd(String wzzAdd) {
                                this.wzzAdd = wzzAdd;
                            }
                        }

                        public static class TotalBean {
                            /**
                             * showRate
                             */
                            @SerializedName("showRate")
                            private Boolean showRate;
                            /**
                             * continueDayZeroLocalConfirmAdd
                             */
                            @SerializedName("continueDayZeroLocalConfirmAdd")
                            private Integer continueDayZeroLocalConfirmAdd;
                            /**
                             * adcode
                             */
                            @SerializedName("adcode")
                            private String adcode;
                            /**
                             * continueDayZeroLocalConfirm
                             */
                            @SerializedName("continueDayZeroLocalConfirm")
                            private Integer continueDayZeroLocalConfirm;
                            /**
                             * mtime
                             */
                            @SerializedName("mtime")
                            private String mtime;
                            /**
                             * dead
                             */
                            @SerializedName("dead")
                            private Integer dead;
                            /**
                             * wzz
                             */
                            @SerializedName("wzz")
                            private Integer wzz;
                            /**
                             * mediumRiskAreaNum
                             */
                            @SerializedName("mediumRiskAreaNum")
                            private Integer mediumRiskAreaNum;
                            /**
                             * highRiskAreaNum
                             */
                            @SerializedName("highRiskAreaNum")
                            private Integer highRiskAreaNum;
                            /**
                             * showHeal
                             */
                            @SerializedName("showHeal")
                            private Boolean showHeal;
                            /**
                             * provinceLocalConfirm
                             */
                            @SerializedName("provinceLocalConfirm")
                            private Integer provinceLocalConfirm;
                            /**
                             * nowConfirm
                             */
                            @SerializedName("nowConfirm")
                            private Integer nowConfirm;
                            /**
                             * confirm
                             */
                            @SerializedName("confirm")
                            private Integer confirm;
                            /**
                             * heal
                             */
                            @SerializedName("heal")
                            private Integer heal;

                            public Boolean getShowRate() {
                                return showRate;
                            }

                            public void setShowRate(Boolean showRate) {
                                this.showRate = showRate;
                            }

                            public Integer getContinueDayZeroLocalConfirmAdd() {
                                return continueDayZeroLocalConfirmAdd;
                            }

                            public void setContinueDayZeroLocalConfirmAdd(Integer continueDayZeroLocalConfirmAdd) {
                                this.continueDayZeroLocalConfirmAdd = continueDayZeroLocalConfirmAdd;
                            }

                            public String getAdcode() {
                                return adcode;
                            }

                            public void setAdcode(String adcode) {
                                this.adcode = adcode;
                            }

                            public Integer getContinueDayZeroLocalConfirm() {
                                return continueDayZeroLocalConfirm;
                            }

                            public void setContinueDayZeroLocalConfirm(Integer continueDayZeroLocalConfirm) {
                                this.continueDayZeroLocalConfirm = continueDayZeroLocalConfirm;
                            }

                            public String getMtime() {
                                return mtime;
                            }

                            public void setMtime(String mtime) {
                                this.mtime = mtime;
                            }

                            public Integer getDead() {
                                return dead;
                            }

                            public void setDead(Integer dead) {
                                this.dead = dead;
                            }

                            public Integer getWzz() {
                                return wzz;
                            }

                            public void setWzz(Integer wzz) {
                                this.wzz = wzz;
                            }

                            public Integer getMediumRiskAreaNum() {
                                return mediumRiskAreaNum;
                            }

                            public void setMediumRiskAreaNum(Integer mediumRiskAreaNum) {
                                this.mediumRiskAreaNum = mediumRiskAreaNum;
                            }

                            public Integer getHighRiskAreaNum() {
                                return highRiskAreaNum;
                            }

                            public void setHighRiskAreaNum(Integer highRiskAreaNum) {
                                this.highRiskAreaNum = highRiskAreaNum;
                            }

                            public Boolean getShowHeal() {
                                return showHeal;
                            }

                            public void setShowHeal(Boolean showHeal) {
                                this.showHeal = showHeal;
                            }

                            public Integer getProvinceLocalConfirm() {
                                return provinceLocalConfirm;
                            }

                            public void setProvinceLocalConfirm(Integer provinceLocalConfirm) {
                                this.provinceLocalConfirm = provinceLocalConfirm;
                            }

                            public Integer getNowConfirm() {
                                return nowConfirm;
                            }

                            public void setNowConfirm(Integer nowConfirm) {
                                this.nowConfirm = nowConfirm;
                            }

                            public Integer getConfirm() {
                                return confirm;
                            }

                            public void setConfirm(Integer confirm) {
                                this.confirm = confirm;
                            }

                            public Integer getHeal() {
                                return heal;
                            }

                            public void setHeal(Integer heal) {
                                this.heal = heal;
                            }
                        }
                    }
                }
            }
        }

        public static class LocalCityNCOVDataListBean {
            /**
             * mtime
             */
            @SerializedName("mtime")
            private String mtime;
            /**
             * mediumRiskAreaNum
             */
            @SerializedName("mediumRiskAreaNum")
            private Integer mediumRiskAreaNum;
            /**
             * highRiskAreaNum
             */
            @SerializedName("highRiskAreaNum")
            private Integer highRiskAreaNum;
            /**
             * isSpecialCity
             */
            @SerializedName("isSpecialCity")
            private Boolean isSpecialCity;
            /**
             * province
             */
            @SerializedName("province")
            private String province;
            /**
             * isUpdated
             */
            @SerializedName("isUpdated")
            private Boolean isUpdated;
            /**
             * date
             */
            @SerializedName("date")
            private String date;
            /**
             * localConfirmAdd
             */
            @SerializedName("local_confirm_add")
            private Integer localConfirmAdd;
            /**
             * localWzzAdd
             */
            @SerializedName("local_wzz_add")
            private String localWzzAdd;
            /**
             * city
             */
            @SerializedName("city")
            private String city;
            /**
             * adcode
             */
            @SerializedName("adcode")
            private String adcode;

            public String getMtime() {
                return mtime;
            }

            public void setMtime(String mtime) {
                this.mtime = mtime;
            }

            public Integer getMediumRiskAreaNum() {
                return mediumRiskAreaNum;
            }

            public void setMediumRiskAreaNum(Integer mediumRiskAreaNum) {
                this.mediumRiskAreaNum = mediumRiskAreaNum;
            }

            public Integer getHighRiskAreaNum() {
                return highRiskAreaNum;
            }

            public void setHighRiskAreaNum(Integer highRiskAreaNum) {
                this.highRiskAreaNum = highRiskAreaNum;
            }

            public Boolean getIsSpecialCity() {
                return isSpecialCity;
            }

            public void setIsSpecialCity(Boolean isSpecialCity) {
                this.isSpecialCity = isSpecialCity;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public Boolean getIsUpdated() {
                return isUpdated;
            }

            public void setIsUpdated(Boolean isUpdated) {
                this.isUpdated = isUpdated;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public Integer getLocalConfirmAdd() {
                return localConfirmAdd;
            }

            public void setLocalConfirmAdd(Integer localConfirmAdd) {
                this.localConfirmAdd = localConfirmAdd;
            }

            public String getLocalWzzAdd() {
                return localWzzAdd;
            }

            public void setLocalWzzAdd(String localWzzAdd) {
                this.localWzzAdd = localWzzAdd;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getAdcode() {
                return adcode;
            }

            public void setAdcode(String adcode) {
                this.adcode = adcode;
            }
        }
    }
}