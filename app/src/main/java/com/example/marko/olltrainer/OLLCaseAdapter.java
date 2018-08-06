package com.example.marko.olltrainer;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.marko.olltrainer.model.OLLCase;

import java.util.ArrayList;

/**
 * Created by marko on 05-Aug-18.
 */

public class OLLCaseAdapter extends ArrayAdapter<OLLCase> {

    private static final String TAG = "OLLCaseAdapter";

    private Context mContext;
    private int mResource;

    public OLLCaseAdapter(Context context, int resource,ArrayList<OLLCase> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        OLLCase ollCase = getItem(position);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);

        TextView tvCaseName = convertView.findViewById(R.id.list_text_item);
        ImageView ivCaseImage = convertView.findViewById(R.id.list_image_item);


        int resourceId = mContext.getResources().getIdentifier(ollCase.getImagePath(), "drawable", mContext.getPackageName());

        tvCaseName.setText(ollCase.getCaseName());
        ivCaseImage.setImageResource(resourceId);

        if(!MainActivity.isSelectedCasesDefault() && MainActivity.getSelectedCases().contains(ollCase)) {
            convertView.setBackgroundColor(Color.GRAY);
        }

        return convertView;

    }
}
