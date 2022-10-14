package com.diabunity.diabunityapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RankingResponse {

    @JsonProperty("user_info")
    private UserInfo userInfo;
    private List<RankedUser> ranking;


    public RankingResponse() {
    }

    public RankingResponse(List<RankedUser> ranking) {
        this.ranking = ranking;
    }

    public RankingResponse(UserInfo userInfo, List<RankedUser> ranking) {
        this.userInfo = userInfo;
        this.ranking = ranking;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public List<RankedUser> getRanking() {
        return ranking;
    }

    public void setRanking(List<RankedUser> ranking) {
        this.ranking = ranking;
    }

    public class RankedUser {

        @JsonIgnore
        private String userID;

        private String username;

        private String picture;

        private Integer percentage;

        public RankedUser(String userID, String username, String picture, Integer percentage) {
            this.userID = userID;
            this.username = username;
            this.picture = picture;
            this.percentage = percentage;
        }

        public String getUserID() {
            return userID;
        }

        public void setUserID(String userID) {
            this.userID = userID;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public Integer getPercentage() {
            return percentage;
        }

        public void setPercentage(Integer percentage) {
            this.percentage = percentage;
        }
    }

    public class UserInfo {

        private Integer position;

        public UserInfo(Integer position) {
            this.position = position;
        }

        public Integer getPosition() {
            return position;
        }

        public void setPosition(Integer position) {
            this.position = position;
        }
    }
}
