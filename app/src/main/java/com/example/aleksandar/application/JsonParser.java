package com.example.aleksandar.application;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;

/**
 * Created by aleksandar on 5/18/2016.
 */
public class JsonParser {

    ArrayList<Categories> categoriesArrayList = new ArrayList<>();
    ArrayList<Subcategories> subcategoriesArrayList = new ArrayList<>();
    ArrayList<Subsubcategories> subsubcategoriesArrayList = new ArrayList<>();

    public void jsonParser(String response_string) {

        JSONParser parser = new JSONParser();

        try {

            JSONObject jsonObject = (JSONObject) parser.parse(response_string);
            JSONArray categories = (JSONArray) jsonObject.get("categories");
            Iterator i = categories.iterator();

            while (i.hasNext()) {
                JSONObject innerObj = (JSONObject) i.next();

                Categories categoriesObj = new Categories();
                categoriesObj.setCategory_id(innerObj.get("category_id").toString());
                Log.v("category", "Id: " + innerObj.get("category_id").toString());
                categoriesObj.setCategory_name(innerObj.get("category_name").toString());
                Log.v("category", "Name: " + innerObj.get("category_name").toString());

                categoriesArrayList.add(categoriesObj);
            }

            JSONArray subcategories = (JSONArray) jsonObject.get("subcategories");
            Iterator iterator = subcategories.iterator();

            while (iterator.hasNext()) {
                JSONObject innerObj = (JSONObject) iterator.next();

                Subcategories subcategoriesObj = new Subcategories();
                subcategoriesObj.setCategory_id(innerObj.get("category_id").toString());
                Log.v("subcategory", "Id: " + innerObj.get("category_id").toString());

                subcategoriesObj.setCover(innerObj.get("cover").toString());
                Log.v("subcategory", "Cover: " + innerObj.get("cover").toString());

                subcategoriesObj.setSubcategory_id(innerObj.get("subcategory_id").toString());
                Log.v("subcategory", "Subcategory_id: " + innerObj.get("subcategory_id").toString());

                subcategoriesObj.setSubcategory_name(innerObj.get("subcategory_name").toString());
                Log.v("subcategory", "Subcategory_name: " + innerObj.get("subcategory_name").toString());

                JSONArray subSubCategories = (JSONArray) jsonObject.get("subcategories");

                // CHECK FOR EMPTY JSON OBJECT
                if (subSubCategories.isEmpty()) {
                    subsubcategoriesArrayList.add(null);
                }

                Iterator iteratorSubCategories = subSubCategories.iterator();

                while (iteratorSubCategories.hasNext()) {
                    JSONObject innerObjSub = (JSONObject) iteratorSubCategories.next();

                    Subsubcategories subsubcategoriesObj = new Subsubcategories();

                    // RECHECK REQUEST
                    try {
                        subsubcategoriesObj.setSub_subcategory_id(innerObjSub.get("sub_subcategory_id").toString());
                        Log.v("subcategory", "Sub_Subcategory_id: " + innerObjSub.get("sub_subcategory_id").toString());
                    } catch (Exception e) {
                        Log.v("Parser", "sub_subcategory_id does not exist!");
                        subsubcategoriesObj.setSub_subcategory_id("");
                    }

                    try {
                        subsubcategoriesObj.setSub_subcategory_id(innerObjSub.get("subcategory_id").toString());
                        Log.v("subcategory", "subcategory_id: " + innerObjSub.get("subcategory_id").toString());
                    } catch (Exception e) {
                        Log.v("Parser", "subcategory_id does not exist!");
                        subsubcategoriesObj.setSub_subcategory_id("");
                    }

                    try {
                        subsubcategoriesObj.setSub_subcategory_name(innerObjSub.get("sub_subcategory_name").toString());
                        Log.v("subcategory", "sub_subcategory_name: " + innerObjSub.get("sub_subcategory_name").toString());
                    } catch (Exception e) {
                        Log.v("Parser", "sub_subcategory_name does not exist!");
                        subsubcategoriesObj.setSub_subcategory_name("");
                    }

                    subsubcategoriesArrayList.add(subsubcategoriesObj);

                }
                subcategoriesArrayList.add(subcategoriesObj);
            }

        } catch (
                ParseException e
                )

        {
            e.printStackTrace();
        }

    }

    public ArrayList<Categories> getCategories() {
        return categoriesArrayList;
    }

    public ArrayList<Subcategories> getSubCategories() {
        return subcategoriesArrayList;
    }

    public ArrayList<Subsubcategories> getSubSubCategories() {
        return subsubcategoriesArrayList;
    }
}
