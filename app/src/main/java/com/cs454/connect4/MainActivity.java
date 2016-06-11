package com.cs454.connect4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        addListenerOnButton();
        playComputerListenerButton();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void addListenerOnButton() {
        ImageButton pvp = (ImageButton) findViewById(R.id.pvpButton);
        final Intent intent = new Intent(this, Playervsplayer.class);
        pvp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });

        ImageButton pvc = (ImageButton) findViewById(R.id.pvcButton);
        final Intent computerIntent = new Intent(this, PlayervsComputer.class);
        pvc.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(computerIntent);
            }
        });
    }

    public void playComputerListenerButton()
    {
        ImageButton pvc = (ImageButton) findViewById(R.id.pvcButton);
        final Intent intent = new Intent(this, PlayervsComputer.class);
        pvc.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }
}