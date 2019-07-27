package com.sq.mcaguide;


public class CardItem
{
    private String subjectName;
    private String semName;
    private String url;

    public CardItem( String subjectName, String semName)
    {
        this.subjectName = subjectName;
        this.semName = semName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getSemName() {
        return semName;
    }
}
