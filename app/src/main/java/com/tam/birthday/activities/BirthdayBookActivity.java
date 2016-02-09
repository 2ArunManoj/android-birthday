package com.tam.birthday.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.tam.birthday.R;
import com.tam.birthday.listeners.OnContactsInteractionListener;

public class BirthdayBookActivity extends AppCompatActivity implements OnContactsInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday_book);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // TODO: setup a searchable metadata
        // TODO: http://developer.android.com/training/search/setup.html
        // TODO: http://developer.android.com/guide/topics/search/search-dialog.html

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //TODO: all our settings has to go here. Like notify x time before the birthday
        //TODO: example is the bday is on 10th March, then notification will start a 7 days before so that he can buy a gift
        //TODO: There will be one more notification when it is just 2 or 3 days are left.
        //TODO: On the day of bday also there will be notification where you can leave a wish via whatsapp.
        //TODO: A LOT OF THINGS CAN BE DONE HERE in the settings like have a fixed message for everyone etc.

        //TODO: we will use shared preference to store the settings data
        getMenuInflater().inflate(R.menu.menu_birthday, menu);
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

    @Override
    public void onContactSelected() {
        Toast.makeText(getBaseContext(), "A contact has been selected", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSelectionCleared() {

    }
}
