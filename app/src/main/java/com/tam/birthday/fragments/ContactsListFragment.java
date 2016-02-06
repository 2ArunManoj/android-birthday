package com.tam.birthday.fragments;

import android.annotation.SuppressLint;

import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.test.mock.MockCursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.tam.birthday.R;
import com.tam.birthday.loaders.MockedCursorLoader;
import com.tam.birthday.testdb.MySQLiteHelper;

/**
 * A placeholder fragment containing a simple view.
 */
public class ContactsListFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>,
        AdapterView.OnItemClickListener {


    /*
     * Defines an array that contains column names to move from
     * the Cursor to the ListView.
     */
    @SuppressLint("InlinedApi")
    private final static String[] FROM_COLUMNS = {
            /*ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
            ContactsContract.Contacts._ID*/
            MySQLiteHelper.NAME,
            MySQLiteHelper.DATE_OF_BIRTH,
            MySQLiteHelper.IMAGE_URL
            /*,
            MySQLiteHelper.PHONE_NUMBER*/
    };
    /*
     * Defines an array that contains resource ids for the layout views
     * that get the Cursor column contents. The id is pre-defined in
     * the Android framework, so it is prefaced with "android.R.id"
     */
    private final static int[] TO_IDS = {
            R.id.contactName,
            R.id.dateOfBirth,
            R.id.contactImage
    };
    // Define global mutable variables
    // Define a ListView object
    ListView contactsList;
    // Define variables for the contact the user selects
    // The contact's _ID value
    long contactId;
    // The contact's LOOKUP_KEY
    String contactKey;
    // A content URI for the selected contact
    Uri contactUri;
    // An adapter that binds the result Cursor to the ListView
    private SimpleCursorAdapter cursorAdapter;

    private static final String[] PROJECTION = {
            // TODO: these needs to be changed according what we retrieve from facebook or google APIs
            /*ContactsContract.Contacts._ID,
            ContactsContract.Contacts.LOOKUP_KEY,
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY*/
            MySQLiteHelper.NAME,
            MySQLiteHelper.DATE_OF_BIRTH,
            MySQLiteHelper.PHONE_NUMBER
    };

    public ContactsListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_birthday, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        contactsList = (ListView) getActivity().findViewById(android.R.id.list);

        contactsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "Item clicked", Toast.LENGTH_LONG);
            }
        });

        contactsList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "Item selected - onItemSelected", Toast.LENGTH_LONG);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getActivity(), "Item selected - onNothingSelected", Toast.LENGTH_LONG);
            }
        });

        //set up the cursorAdapter for the list
        cursorAdapter = new SimpleCursorAdapter(getActivity(),
                R.layout.custom_list_view,
                null,
                FROM_COLUMNS, TO_IDS,
                0);

        contactsList.setAdapter(cursorAdapter);



        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new MockedCursorLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        cursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        cursorAdapter.swapCursor(null);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(parent.getContext(), "An item got selected. display the details of this item",
                Toast.LENGTH_LONG);
    }


}
