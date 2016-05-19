package com.example.aleksandar.application;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by aleksandar on 5/19/2016.
 */
public class DataHelper implements Serializable {

    private ArrayList<?> data = new ArrayList();

    public DataHelper(ArrayList<?> data) {
        this.data = data;
    }

    public ArrayList<?> getList() {
        return this.data;
    }
}
