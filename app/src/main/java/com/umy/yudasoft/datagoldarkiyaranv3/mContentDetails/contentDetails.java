package com.umy.yudasoft.datagoldarkiyaranv3.mContentDetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.umy.yudasoft.datagoldarkiyaranv3.MainActivity;
import com.umy.yudasoft.datagoldarkiyaranv3.R;
import com.umy.yudasoft.datagoldarkiyaranv3.mDatabase.DBHelper;


/**
 * Created by YudaRanger on 05/04/2018.
 */

public class contentDetails extends AppCompatActivity {

    DBHelper DH = new DBHelper(this);
    TextView name, goldar1, jk1, tgllahir1, alamat1;
    String name1;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.model_details_goldar);
        Intent intent = getIntent();

        String nama = intent.getStringExtra("nama");
        String goldar = intent.getStringExtra("goldar");
        String jk = intent.getStringExtra("jk");
        String tgllahir = intent.getStringExtra("tgllahir");
        String alamat = intent.getStringExtra("alamat");
        id = intent.getIntExtra("id",0) ;



        name = (TextView) findViewById(R.id.namatxt);
        goldar1 = (TextView) findViewById(R.id.goldartxt);
        jk1 = (TextView) findViewById(R.id.jktxt);
        tgllahir1 = (TextView) findViewById(R.id.tglahirtxt);
        alamat1 = (TextView) findViewById(R.id.alamatxt);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab2);


        name.setText(nama);
        goldar1.setText(goldar);
        jk1.setText(jk);
        tgllahir1.setText(tgllahir);
        alamat1.setText(alamat);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();

                String nama = intent.getStringExtra("nama");


                if(name.getText().toString() != null &&
                        goldar1.getText().toString().trim().length() != 0){
                    DH.deleteData(nama);
                    Toast.makeText(getApplicationContext(), "Data berhasil dihapus!",Toast.LENGTH_SHORT).show();
                    callHomeActivity(view);
                }

                else{
                    Toast.makeText(getApplicationContext(),
                            "Data gagal dihapus!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void callHomeActivity(View view){
        Intent objintent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(objintent);
    }
}
