package com.example.year2mission6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int numberOfClicks;
    String name;
    EditText nameEditText;
    TextView numberOfClicksTv;
    SharedPreferences data;
    SharedPreferences.Editor editor;
    Intent in;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberOfClicksTv = (TextView) findViewById(R.id.numberOfClicks);
        nameEditText = (EditText) findViewById(R.id.nameEditText);

        data = (SharedPreferences) getSharedPreferences("PREFS_DATA", MODE_PRIVATE);
        editor = data.edit();
        name = data.getString("name", "");
        nameEditText.setText(name);

        numberOfClicks = data.getInt("numberOfClicks", 0);
        numberOfClicksTv.setText("" + numberOfClicks);
    }

    /**
     * this method saves all the data and quits the application
     * @param view
     */
    public void exit(View view) {
        name = nameEditText.getText().toString();

        editor.putString("name", name);
        editor.putInt("numberOfClicks", numberOfClicks);
        editor.commit();

        finish();
    }

    /**
     * This method increases the counter of the clicks by 1
     * @param view
     */
    public void count(View view) {
        numberOfClicks++;
        numberOfClicksTv.setText("" + numberOfClicks);
    }

    /**
     * This method resets the counter of the clicks
     * @param view
     */
    public void reset(View view) {
        numberOfClicks = 0;
        numberOfClicksTv.setText("" + numberOfClicks);
    }


    /**
     * This method is called when the user clicks on the menu, it inflates the menu onto the screen
     * @param menu the menu to move between activities
     * @return true or false
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.activities,menu);

        return super.onCreateOptionsMenu(menu);
    }

    /**
     * This method is called when the credits option on the menu is called and it goes to the credits activity
     * @param item the option from the menu that presents the activity the user wants to move to
     * @return true or false
     */
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();

        if(id == R.id.Credits){
            in = new Intent(this, Credits.class);
            startActivity(in);
        }

        return super.onOptionsItemSelected(item);
    }
}