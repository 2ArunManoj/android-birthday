package com.tam.birthday.loaders;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.ContentResolverCompat;
import android.support.v4.content.CursorLoader;
import android.support.v4.os.CancellationSignal;
import android.support.v4.os.OperationCanceledException;

import com.tam.birthday.testdb.BirthdayDataProvider;

/**
 * Created by Manoj on 2/5/2016.
 */
public class MockedCursorLoader extends CursorLoader {

    public MockedCursorLoader(Context context) {
        super(context);
    }

    @Override
    public Cursor loadInBackground() {
        synchronized (this) {
            if (isLoadInBackgroundCanceled()) {
                throw new OperationCanceledException();
            }

        }
        try {
            BirthdayDataProvider birthdayDataProvider = BirthdayDataProvider.getInstance(getContext());
            // Didn't like the same data coming up again and again.
            birthdayDataProvider.cleanUpOldData();

            birthdayDataProvider.insertMockData();

            Cursor cursor = birthdayDataProvider.getAllBirthdayInfo();
            if (cursor != null) {
                try {
                    // Ensure the cursor window is filled.
                    cursor.getCount();
                    //cursor.registerContentObserver(mObserver);
                } catch (RuntimeException ex) {
                    cursor.close();
                    throw ex;
                }
            }
            return cursor;
        } finally {
            synchronized (this) {
                //mCancellationSignal = null;
            }
        }
    }
}
