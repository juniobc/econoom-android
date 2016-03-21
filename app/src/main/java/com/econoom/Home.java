package com.econoom;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.econoom.auxiliar.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.econoom.auxiliar.ExpandedMenuModel;
import com.econoom.entidade.GrupoMat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ListGprMatFragment.OnGprMatSelectedListener {

    private Fragment fragment = null;
    private FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
    ExpandableListAdapter mMenuAdapter;
    ExpandableListView expandableList;
    DrawerLayout drawer;
    List<ExpandedMenuModel> listDataHeader;
    HashMap<ExpandedMenuModel, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        expandableList= (ExpandableListView) findViewById(R.id.navigationmenu);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        criaMenu();
        mMenuAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild, expandableList);

        // setting list adapter
        expandableList.setAdapter(mMenuAdapter);
        expandableList.setDivider(null);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        /*GprMatFragment gprFragment = new GprMatFragment();
        ft.add(R.id.cadastra_gpr, gprFragment);
        ft.commit();*/

        expandableList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                if (groupPosition == 0) {

                    Intent i = new Intent(Home.this, CadastroProduto.class);
                    startActivity(i);

                }

                if (groupPosition == 2) {

                    Intent i = new Intent(Home.this, ListaNotas.class);
                    startActivity(i);

                }

                return false;
            }
        });

        expandableList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                String title = null;

                fragment = null;

                if (groupPosition == 1 && childPosition == 0) {
                    fragment = new GprMatFragment();
                    title = "Cadastra Grupo";
                }

                if (groupPosition == 1 && childPosition == 1) {
                    fragment = new ListGprMatFragment();
                    title = "Lista Grupo Material";
                }

                if (fragment != null) {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.cadastra_gpr, fragment);
                    ft.addToBackStack(null);
                    ft.commit();
                }

                /*if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle(title);
                }*/

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

                return false;
            }
        });
    }

    public void criaMenu(){

        listDataHeader = new ArrayList<ExpandedMenuModel>();
        listDataChild = new HashMap<ExpandedMenuModel, List<String>>();

        ExpandedMenuModel item1 = new ExpandedMenuModel();
        item1.setIconName(getResources().getString(R.string.reg_ent));
        item1.setIconImg(R.drawable.add);
        // Adding data header
        listDataHeader.add(item1);

        ExpandedMenuModel item2 = new ExpandedMenuModel();
        item2.setIconName(getResources().getString(R.string.gern_gpr_mat));
        item2.setIconImg(R.drawable.config);
        listDataHeader.add(item2);

        ExpandedMenuModel item3 = new ExpandedMenuModel();
        item3.setIconName(getResources().getString(R.string.lista_ent));
        item3.setIconImg(R.drawable.add);
        listDataHeader.add(item3);

        // Adding child data
        /*List<String> heading1 = new ArrayList<String>();
        heading1.add("Submenu of item 1");*/

        List<String> heading2 = new ArrayList<String>();
        heading2.add(getResources().getString(R.string.cad_gpr_mat));
        heading2.add(getResources().getString(R.string.list_gpr_mat));

        //listDataChild.put(listDataHeader.get(0), heading1);// Header, Child data
        listDataChild.put(listDataHeader.get(1), heading2);

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
        getMenuInflater().inflate(R.menu.home, menu);
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
        return true;
    }

    @Override
    public void onGprMatSelected(GrupoMat gprMat){

        Bundle bundle=new Bundle();
        bundle.putParcelable(GprMatFragment.OBJETO_GPRMAT, gprMat);

        fragment = new GprMatFragment();
        fragment.setArguments(bundle);

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.cadastra_gpr, fragment);
            ft.addToBackStack(null);
            ft.commit();
        }

    }
}
