package com.tam.birthday.fragments;

import android.annotation.SuppressLint;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.tam.birthday.R;
import com.tam.birthday.adapters.ContactsAdapter;
import com.tam.birthday.listeners.OnContactsInteractionListener;
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
    private ContactsAdapter cursorAdapter;

    // Contact selected listener that allows the activity holding this fragment to be notified of
    // a contact being selected
    private OnContactsInteractionListener contactsInteractionListener;

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

        //set up the cursorAdapter for the list
        /*cursorAdapter = new SimpleCursorAdapter(getActivity(),
                R.layout.custom_list_view,
                null,
                FROM_COLUMNS, TO_IDS,
                0);*/
        cursorAdapter = new ContactsAdapter(getContext());

        // set the adapter to the cursor.
        contactsList.setAdapter(cursorAdapter);

        contactsList.setOnItemClickListener(this);

        // initialize the loader to get the data from either from facebook, google+ or local db if it populated.
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            contactsInteractionListener = (OnContactsInteractionListener) super.getActivity();
        } catch (ClassCastException classCastEx) {
            throw new ClassCastException(super.getActivity().toString() + " must implement the interface OnContactsInteractionListener");
        }

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
        //TODO: need to find a view in which we can communicate the unique ID of a contact back to the activity
        //TODO: so that it can display the details section for it.

        contactsInteractionListener.onContactSelected();
    }


}
