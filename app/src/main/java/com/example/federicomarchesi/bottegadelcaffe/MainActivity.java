package com.example.federicomarchesi.bottegadelcaffe;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    DBHelper mydb;
    ArrayList<CoffeeType> arrayCoffeeType = new ArrayList<>();
    CoffeeAdapter coffeeAdapter;
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                onCreateNewCoffeeType();
                addNewCoffee();
//
            }
        });
        FloatingActionButton speak = (FloatingActionButton) findViewById(R.id.speak);
        speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderCoffees();
//
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        init();
    }

    private void addNewCoffee() {

        arrayCoffeeType.add(new CoffeeType());
        coffeeAdapter.notifyDataSetChanged();
    }

    private void init(){
        arrayCoffeeType.add(new CoffeeType());

        coffeeAdapter = new CoffeeAdapter(this, arrayCoffeeType);



        ListView obj = (ListView) findViewById(R.id.list);
        obj.setAdapter(coffeeAdapter);



//        coffeeAdapter.notifyDataSetChanged();

    }

    public void onPause() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onPause();
    }

    private void orderCoffees() {
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.ITALIAN);
                    String toSpeak = "3 2 macchia con, un america";
                    textToSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                    textToSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null, null);
                }
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        mydb.saveAllCoffeeTypesToDB(arrayCoffeeType);
    }

    private void intDB() {
        mydb = new DBHelper(this);
        arrayCoffeeType = mydb.getAllCoffeeTypes();

        coffeeAdapter = new CoffeeAdapter(this, arrayCoffeeType);

        ListView obj = (ListView) findViewById(R.id.list);
        obj.setAdapter(coffeeAdapter);

        obj.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                int id_To_Search = arg2 + 1;

                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", id_To_Search);

//                Intent intent = new Intent(getApplicationContext(),DisplayContact.class);
//
//                intent.putExtras(dataBundle);
//                startActivity(intent);
            }
        });
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

        if (id == R.id.nav_coffee_type) {
            Intent intent = new Intent(MainActivity.this, CoffeTypesActivity.class);

            startActivity(intent);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void onCreateNewCoffeeType() {
//        final Player player = getCurrentPlayer();
        InputDialog inputDialog = new InputDialog(this, R.string.coffe_type_name, R.string.coffe_type_name);
//        inputDialog.setInitialInput(player.getName());
        inputDialog.setInputListener(new InputDialog.InputListener() {
            @Override
            public InputDialog.ValidationResult isInputValid(String newCoffeeType) {
                if (newCoffeeType.isEmpty()) {
                    return new InputDialog.ValidationResult(false, R.string.error_empty_name);
                }
//                Player playerWithSameName = gameManager.findPlayerByName(newCoffeeType);
//                if (playerWithSameName != null && playerWithSameName.getId() != getCurrentPlayer().getId()) {
//                    return new InputDialog.ValidationResult(false, R.string.error_duplicate_name);
//                }
                return new InputDialog.ValidationResult(true, 0);
            }

            @Override
            public void onConfirm(String newCoffeeType, String newCoffeeDescr) {
//                DBHelper db = new DBHelper(MainActivity.this);
//                db.insertCoffeeType(newCoffeeType, newCoffeeDescr);
                arrayCoffeeType.add(new CoffeeType(newCoffeeType, newCoffeeDescr));
                coffeeAdapter.notifyDataSetChanged();
//                Toast.makeText(MainActivity.this, "records: " + db.numberOfRows(), Toast.LENGTH_SHORT).show();
            }
        });
        inputDialog.show();
    }
}
