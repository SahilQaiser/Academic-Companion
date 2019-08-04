package com.sq.mcaguide;

public class Globals
{
    public static String getSubjectID(String subjectName,String semester)
    {
        String id = "";
        String words[]= subjectName.split(" ");

        for (int i=0;i<words.length;i++)
        {
            id=id+words[i].charAt(0);
        }
        id=id+"_"+semester.charAt(0);

        return  id;
    }
}
