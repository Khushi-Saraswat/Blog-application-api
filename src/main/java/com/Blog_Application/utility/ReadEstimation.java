package com.Blog_Application.utility;

public class ReadEstimation {
    
    public int ReadTimeEstimation(String content){

           String[]words=content.split(" ");
           int len=words.length;
           System.out.println(len+"length of word");
           int readTime=(int)Math.ceil(len/200);
           return Math.max(readTime,1);


    }
}
