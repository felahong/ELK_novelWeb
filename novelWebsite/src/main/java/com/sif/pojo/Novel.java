package com.sif.pojo;

import java.util.Date;

public class Novel {
    private String nid;

    private String nname;

    private String nauthor;

    private Integer nstatic;

    private Integer nclass;

    private String nlabel;

    private String nlogo;

    private Integer nhot;

    private String nintro;

    private Date ncreatetime;

    private Integer lastchapter;

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid == null ? null : nid.trim();
    }

    public String getNname() {
        return nname;
    }

    public void setNname(String nname) {
        this.nname = nname == null ? null : nname.trim();
    }

    public String getNauthor() {
        return nauthor;
    }

    public void setNauthor(String nauthor) {
        this.nauthor = nauthor == null ? null : nauthor.trim();
    }

    public Integer getNstatic() {
        return nstatic;
    }

    public void setNstatic(Integer nstatic) {
        this.nstatic = nstatic;
    }

    public Integer getNclass() {
        return nclass;
    }

    public void setNclass(Integer nclass) {
        this.nclass = nclass;
    }

    public String getNlabel() {
        return nlabel;
    }

    public void setNlabel(String nlabel) {
        this.nlabel = nlabel == null ? null : nlabel.trim();
    }

    public String getNlogo() {
        return nlogo;
    }

    public void setNlogo(String nlogo) {
        this.nlogo = nlogo == null ? null : nlogo.trim();
    }

    public Integer getNhot() {
        return nhot;
    }

    public void setNhot(Integer nhot) {
        this.nhot = nhot;
    }

    public String getNintro() {
        return nintro;
    }

    public void setNintro(String nintro) {
        this.nintro = nintro == null ? null : nintro.trim();
    }

    public Date getNcreatetime() {
        return ncreatetime;
    }

    public void setNcreatetime(Date ncreatetime) {
        this.ncreatetime = ncreatetime;
    }

    public Integer getLastchapter() {
        return lastchapter;
    }

    public void setLastchapter(Integer lastchapter) {
        this.lastchapter = lastchapter;
    }

    @Override
    public String toString() {
        return "Novel{" +
                "nid='" + nid + '\'' +
                ", nname='" + nname + '\'' +
                ", nauthor='" + nauthor + '\'' +
                ", nstatic=" + nstatic +
                ", nclass=" + nclass +
                ", nlabel='" + nlabel + '\'' +
                ", nlogo='" + nlogo + '\'' +
                ", nhot=" + nhot +
                ", nintro='" + nintro + '\'' +
                ", ncreatetime=" + ncreatetime +
                ", lastchapter=" + lastchapter +
                '}';
    }
}