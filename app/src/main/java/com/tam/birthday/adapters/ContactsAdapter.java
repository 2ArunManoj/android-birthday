package com.tam.birthday.adapters;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.tam.birthday.R;
import com.tam.birthday.testdb.MySQLiteHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * This is a custom cursor adapter that fills up the list view
 * Created by Manoj on 2/6/2016.
 */
public class ContactsAdapter extends CursorAdapter {

    private static final String tag = "ContactsAdapter";

    private LayoutInflater layoutInflater;

    public ContactsAdapter(Context context) {
        super(context, null, 0);
        layoutInflater =LayoutInflater.from(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        final View listItemLayout = layoutInflater.inflate(R.layout.custom_list_view, parent, false);

        // create a holder to store the handles of each view resource in the list item layout.
        // This will be used in bindView.
        final LayoutResourceHolder resourceHolder = new LayoutResourceHolder();

        resourceHolder.contactImageView = (ImageView) listItemLayout.findViewById(R.id.contactImage);
        resourceHolder.contactNameTextView = (TextView) listItemLayout.findViewById(R.id.contactName);
        resourceHolder.whenIsBdayTextView = (TextView) listItemLayout.findViewById(R.id.whenIsBday);
        resourceHolder.dobTextView = (TextView) listItemLayout.findViewById(R.id.dateOfBirth);
        resourceHolder.contactAgeTextView = (TextView) listItemLayout.findViewById(R.id.contactAge);

        listItemLayout.setTag(resourceHolder);

        return listItemLayout;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        // Get the handles to the view resources
        LayoutResourceHolder resourceHolder = (LayoutResourceHolder) view.getTag();

        final String contactName = cursor.getString(cursor.getColumnIndex(MySQLiteHelper.NAME));
        final String dob = cursor.getString(cursor.getColumnIndex(MySQLiteHelper.DATE_OF_BIRTH));

        // calculate when the bday is and the age from dob.
        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/mm/yyyy");
            Date date = dateFormatter.parse(dob);

            Calendar calendar = Calendar.getInstance();
            int currentYear = calendar.get(Calendar.YEAR);

            calendar.setTime(date);
            int bornYear = calendar.get(Calendar.YEAR);

            resourceHolder.contactAgeTextView.setText(Integer.toString(currentYear - bornYear));


        } catch(ParseException parseEx) {
            Log.e(tag, "dob not in format dd/mm/yyyy. Reason - " + parseEx.getMessage());
        }



        resourceHolder.contactNameTextView.setText(contactName);
        resourceHolder.dobTextView.setText(dob);

    }

    private class LayoutResourceHolder {
        // LHS
        ImageView contactImageView;
        TextView contactNameTextView;

        // RHS
        TextView whenIsBdayTextView;
        TextView dobTextView;
        TextView contactAgeTextView;
    }
}
