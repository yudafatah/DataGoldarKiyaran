package com.umy.yudasoft.datagoldarkiyaranv3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.umy.yudasoft.datagoldarkiyaranv3.mDataObject.Planet;
import com.umy.yudasoft.datagoldarkiyaranv3.mDatabase.DBAdapter;
import com.umy.yudasoft.datagoldarkiyaranv3.mDatabase.DBHelper;
import com.umy.yudasoft.datagoldarkiyaranv3.mListView.mCustomAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListView lv;
    EditText nameed;
    EditText goldared;
    EditText alamated;
    EditText tgllahired;
    EditText jked;
    Button savebtn,retrievebtn;
    mCustomAdapter adapter;
    ArrayList<Planet> planets = new ArrayList<>();
    SearchView sv;
    DBHelper DH = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.fab1);

        lv = (ListView) findViewById(R.id.lv);
        sv = (SearchView) findViewById(R.id.sv);
        SearchView searchView = (SearchView) findViewById(R.id.sv);
        searchView.setQueryHint("Ketikan gologan darah");

        adapter= new mCustomAdapter(this,planets);

        this.getPlanet(null);

        //this.getPlanet(null);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayDialog();
            }
        });
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPlanet(null);
            }
        });

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                getPlanet(newText);
                return false;
            }
        });



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    private void displayDialog(){
        Dialog d=new Dialog(this);
        d.setTitle("Input Data Golongan Darah");
        d.setContentView(R.layout.dialog_layout);
        nameed = (EditText) d.findViewById(R.id.nameET);
        goldared = (EditText) d.findViewById(R.id.goldarET);
        alamated = (EditText) d.findViewById(R.id.alamatET);
        savebtn = (Button) d.findViewById(R.id.btnsave);
        retrievebtn = (Button) d.findViewById(R.id.btnretrieve);
        tgllahired = (EditText) d.findViewById(R.id.tgllahirET);
        jked = (EditText) d.findViewById(R.id.jkET);

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save(nameed.getText().toString(),goldared.getText().toString(),alamated.getText().toString(),tgllahired.getText().toString(),jked.getText().toString());
            }
        });
        retrievebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPlanet(null);

            }
        });

        d.show();
    }

    private void  save(String name, String goldar, String alamat, String tgllahir, String jk){
        DBAdapter db= new DBAdapter(this);
        db.openDB();
        if(db.add(name,goldar,alamat, tgllahir, jk)){
            nameed.setText("");
            goldared.setText("");
            alamated.setText("");
            tgllahired.setText("");
            jked.setText("");


        }else {
            Toast.makeText(this, "Data gagal disimpan!",Toast.LENGTH_SHORT).show();
        }

        db.closeDB();

        this.getPlanet(null);
    }

    private void getPlanet(String searchTerm){
        planets.clear();

        DBAdapter db = new DBAdapter(this);
        db.openDB();
        Planet p= null;
        Cursor c =db.retrieve(searchTerm);
        while (c.moveToNext()){
            int id = c.getInt(0);
            String name = c.getString(1);
            String goldar = c.getString(2);
            String alamat = c.getString(3);
            String tgllahir = c.getString(4);
            String jk = c.getString(5);

            p=new Planet();
            p.setId(id);
            p.setName(name);
            p.setGoldar(goldar);
            p.setJk(jk);
            p.setTgllahir(tgllahir);
            p.setAlamat(alamat);

            planets.add(p);
        }

        db.closeDB();

        lv.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            showHapusDialog();
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showHapusDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Apakah Anda Yakin Ingin Menghapus Semua Data yang Ada di Daftar?");
        builder.setCancelable(true);
        builder.setPositiveButton("Ya", new OkOnClickListener());
        builder.setNegativeButton("Tidak", new CancelOnClicklistener());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private final class CancelOnClicklistener
            implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialog, int which){
            Toast.makeText(getApplicationContext(),
                    "Aplikasi akan dilanjutkan", Toast.LENGTH_LONG).show();
        }
    }
    private final class OkOnClickListener
            implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialog, int which){
            DH.hapusAllData();
            getPlanet(null);
        }
    }
}
