package com.sq.mcaguide;


public class CardItem
{
    private String subjectName;
    private String semName;
    private String url;

    public CardItem( String subjectName, String semName,String url)
    {
        this.subjectName = subjectName;
        this.semName = semName;
        this.url=url;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getSemName() {
        return semName;
    }

    public String getUrl() {
        return url;
    }
}
