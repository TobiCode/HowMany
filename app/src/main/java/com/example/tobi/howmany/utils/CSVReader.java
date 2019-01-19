package com.example.tobi.howmany.utils;

import android.content.Context;
import android.util.Log;

import com.example.tobi.howmany.R;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * Created by Tobi on 18.01.2019.
 */

public class CSVReader {


    public static LinkedList<String> readExcelFileFromAssets(Context context) {
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        LinkedList<String> resultList = new LinkedList<String>();

        int resId = R.raw.questions;
        try {

            InputStream ins = context.getResources().openRawResource(resId);
            //Or utf-8 or ansi or Windows-1252
            InputStreamReader inputStreamReader = new InputStreamReader(ins, "utf-8");
            br = new BufferedReader(inputStreamReader);
            br.readLine(); // this will read the first line
            while ((line = br.readLine()) != null) {
                String question = line;
                resultList.add(question);
                Log.i("Debug Encoding: ", question);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultList;

    }

}


