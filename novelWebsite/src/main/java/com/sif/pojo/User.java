package com.sif.pojo;

public class User {
    private String uid;

    private String username;

    private String upassword;

    private String umail;

    private Integer ustate;

    private String ucode;

    private Integer uisauthor;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword == null ? null : upassword.trim();
    }

    public String getUmail() {
        return umail;
    }

    public void setUmail(String umail) {
        this.umail = umail == null ? null : umail.trim();
    }

    public Integer getUstate() {
        return ustate;
    }

    public void setUstate(Integer ustate) {
        this.ustate = ustate;
    }

    public String getUcode() {
        return ucode;
    }

    public void setUcode(String ucode) {
        this.ucode = ucode == null ? null : ucode.trim();
    }

    public Integer getUisauthor() {
        return uisauthor;
    }

    public void setUisauthor(Integer uisauthor) {
        this.uisauthor = uisauthor;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", username='" + username + '\'' +
                ", upassword='" + upassword + '\'' +
                ", umail='" + umail + '\'' +
                ", ustate=" + ustate +
                ", ucode='" + ucode + '\'' +
                ", uisauthor=" + uisauthor +
                '}';
    }
}