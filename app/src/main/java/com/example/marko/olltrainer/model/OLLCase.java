package com.example.marko.olltrainer.model;

import android.util.Log;

import com.example.marko.olltrainer.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by marko on 05-Aug-18.
 */

public class OLLCase {

    private final static Random rand = new Random();

    static {
        rand.setSeed(1234);
    }


    private String imagePath;
    private String caseName;
    private ArrayList<String> scrambles;

    public OLLCase(String caseName,String imagePath,ArrayList<String> scrambles) {
        this.imagePath = imagePath;
        this.caseName = caseName;
        this.scrambles = scrambles;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getCaseName() {
        return caseName;
    }

    public String getScramble() {
        int ind = rand.nextInt(scrambles.size());
        return scrambles.get(ind);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof OLLCase) {
            OLLCase ollCase = (OLLCase) obj;
            return ollCase.getCaseName().equals(this.getCaseName());
        }
        return super.equals(obj);
    }

    public static ArrayList<OLLCase> deserialize_cases(MainActivity mainInstance) {
        ArrayList<OLLCase> cases = new ArrayList<OLLCase>();
        try {
            InputStream is = mainInstance.getAssets().open("oll_cases.json");
            int size =  is.available();
            byte buffer[] = new byte[size];

            is.read(buffer);
            is.close();

            String json = new String(buffer,"UTF-8");

            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("cases");
            for(int i=0;i<jsonArray.length();i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                String caseName = obj.getString("name");
                String imagePath = "oll_" + (i+1);


                JSONArray jsonScrambles = obj.getJSONArray("scrambles");
                ArrayList<String> scrambles = new ArrayList<String>();
                for(int j=0;j<jsonScrambles.length();j++) {
                    String scramble = jsonScrambles.getString(j);
                    scrambles.add(scramble);
                }

                cases.add(new OLLCase(caseName,imagePath,scrambles));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cases;
    }

}
