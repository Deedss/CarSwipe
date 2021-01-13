package com.example.vroomrr;

public class Opinion {

    private String user_id;
    private String user_id_match;
    private String opinion;

    public String getUserId() {
        return user_id;
    }

    public void setUserId(String user_id) {
        this.user_id = user_id;
    }

    public String getUserIdMatch() {
        return user_id_match;
    }

    public void setUserIdMatch(String u) {
        user_id_match = u;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String u) {
        opinion = u;
    }
}
