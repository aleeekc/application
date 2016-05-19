package com.example.aleksandar.application;

import java.util.ArrayList;

/**
 * Created by aleksandar on 5/18/2016.
 */
public class Subcategories {

    private String category_id;
    private String subcategory_id;
    private String subcategory_name;
    private String cover;
    private ArrayList<Subsubcategories> subsubcategories;


    public ArrayList<Subsubcategories> getSubsubcategoriesList() {

        return subsubcategories;
    }

    public void setSubsubcategories(ArrayList<Subsubcategories> subsubcategories) {
        this.subsubcategories = subsubcategories;
    }


    public String getCategory_id() {

        return category_id;
    }

    public void setCategory_id(String category_id) {

        this.category_id = category_id;
    }

    public String getSubcategory_name() {

        return subcategory_name;
    }

    public void setSubcategory_name(String subcategory_name) {
        this.subcategory_name = subcategory_name;
    }

    public String getCover() {

        return cover;
    }

    public void setCover(String cover) {

        this.cover = cover;
    }

    public String getSubcategory_id() {

        return subcategory_id;
    }

    public void setSubcategory_id(String subcategory_id) {

        this.subcategory_id = subcategory_id;
    }
}
