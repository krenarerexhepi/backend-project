package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.Joke;

import udacity_project.passjoke.PassJokeActivity;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    Joke javaLibrary = new Joke();

    public void tellJoke(View view) {
        // This is code to call the joke simple from Java Library
     /*   String jokeFromLibrary = javaLibrary.getJoke();
        Toast.makeText(this, jokeFromLibrary, Toast.LENGTH_SHORT).show();*/
        Intent intent = new Intent(this, PassJokeActivity.class);
        String joke = javaLibrary.getJoke();
        intent.putExtra(PassJokeActivity.JOKE_KEY, joke);
        startActivity(intent);
    }


}
