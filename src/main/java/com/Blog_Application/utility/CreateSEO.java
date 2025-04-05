package com.Blog_Application.utility;

public class CreateSEO {

    public String SlugCreation(String categoryName){

        String slug=categoryName.toLowerCase().replace(" ","-");
        System.out.println("slug name"+""+slug);
        return slug;
     }

}
