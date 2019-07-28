package com.sq.mcaguide;


public class CardItem
{
    private String subjectName;
    private String semName;
    private String sid;

    public CardItem( String subjectName, String semName,String sid)
    {
        this.subjectName = subjectName;
        this.semName = semName;
        this.sid=sid;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getSemName() {
        return semName;
    }

    public String getSid() {
        return sid;
    }
}
