package com.example.inmodroid.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.inmodroid.R;
import com.example.inmodroid.fragments.InmueblesFavoritosFragment;
import com.example.inmodroid.fragments.InmueblesFragment;
import com.example.inmodroid.fragments.MisPropiedadesFragment;
import com.example.inmodroid.fragments.dummy.DummyContent;
import com.example.inmodroid.listeners.OnListInmueblesInteractionListener;
import com.example.inmodroid.util.Util;
import com.example.inmodroid.util.UtilToken;

public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnListInmueblesInteractionListener, MisPropiedadesFragment.OnListFragmentInteractionListener {
        MenuItem oculto1,oculto2,oculto3;
        FrameLayout contenedor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabAddHome);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardActivity.this,PropertyAddActivity.class));
            }
        });

        //cabecera usuario
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView =  findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        View headerView = navigationView.getHeaderView(0);

        ImageView imagenUsuario = headerView.findViewById(R.id.picture);
        TextView nombreUsuario = headerView.findViewById(R.id.userName);
        TextView email = headerView.findViewById(R.id.emailUser);

        if(Util.getEmailUser(DashboardActivity.this) != null){

            Glide.with(this).load(Util.getPhotoUser(DashboardActivity.this)).apply(RequestOptions.circleCropTransform()).into(imagenUsuario);
            nombreUsuario.setText(Util.getNombreUser(DashboardActivity.this).toUpperCase());
            email.setText(Util.getEmailUser(DashboardActivity.this));

        }


        //contenedor para fragments
        contenedor = findViewById(R.id.contenedor);

        //Objetos que quiero ocultar
        oculto1 = navigationView.getMenu().findItem(R.id.goFavourites);
        oculto2 = navigationView.getMenu().findItem(R.id.goLogout);
        oculto3 = navigationView.getMenu().findItem(R.id.goUserProperties);

        //if para ocultar
        if(Util.getToken(this) == null){
            oculto1.setVisible(false);
            oculto2.setVisible(false);
            oculto3.setVisible(false);
            fab.hide();
        }

        getSupportFragmentManager().beginTransaction()
                .add(R.id.contenedor, new InmueblesFragment()).commit();



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

        if (id == R.id.goLogin) {
            startActivity(new Intent(DashboardActivity.this,LoginActivity.class));
            
        } else if(id == R.id.goPropertiesList){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contenedor,new InmueblesFragment()).commit();

        } else if (id == R.id.goFavourites) {

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contenedor, new InmueblesFavoritosFragment()).commit();


        } else if (id == R.id.goLogout) {
            Util.clearSharedPreferences(this);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contenedor, new InmueblesFragment()).commit();

        }else if (id == R.id.goUserProperties){

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contenedor, new MisPropiedadesFragment()).commit();
        }

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        
    }
}
