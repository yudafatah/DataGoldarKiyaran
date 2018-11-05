package com.umy.yudasoft.datagoldarkiyaranv3.mListView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;


import com.umy.yudasoft.datagoldarkiyaranv3.R;
import com.umy.yudasoft.datagoldarkiyaranv3.mContentDetails.contentDetails;
import com.umy.yudasoft.datagoldarkiyaranv3.mDataObject.Planet;

import java.util.ArrayList;

/**
 * Created by YudaRanger on 04/04/2018.
 */

public class mCustomAdapter extends BaseAdapter {
    Context c;
    ArrayList<Planet> planets;
    LayoutInflater inflater;

    public mCustomAdapter(Context c, ArrayList<Planet> planets) {
        this.c = c;
        this.planets = planets;
    }

    @Override
    public int getCount() {
        return planets.size();
    }

    @Override
    public Object getItem(int i) {
        return planets.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (inflater == null){
            inflater=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(view == null){
            view = inflater.inflate(R.layout.model,viewGroup, false);
        }

        TextView nameTXT = (TextView) view.findViewById(R.id.nametxt);
        nameTXT.setText(planets.get(i).getName());
        TextView goldarTXT = (TextView) view.findViewById(R.id.goldartxt);
        goldarTXT.setText(planets.get(i).getGoldar());
        TextView jkTXT = (TextView) view.findViewById(R.id.jktxt);
        jkTXT.setText(planets.get(i).getJk());
        String tgllahir = planets.get(i).getTgllahir();
        String alamat = planets.get(i).getAlamat();
        int id = planets.get(i).getId();

        final int pos = i;

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(c,planets.get(pos).getGoldar(),Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                Intent intent = new Intent(view.getContext(), contentDetails.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                bundle.putInt("id",planets.get(pos).getId());
                bundle.putString("nama",planets.get(pos).getName().toString());
                bundle.putString("goldar",planets.get(pos).getGoldar().toString());
                bundle.putString("jk",planets.get(pos).getJk().toString());
                bundle.putString("tgllahir",planets.get(pos).getTgllahir().toString());
                bundle.putString("alamat",planets.get(pos).getAlamat().toString());

                intent.putExtras(bundle);
                view.getContext().startActivity(intent);
            }
        });

        return view;
    }
}