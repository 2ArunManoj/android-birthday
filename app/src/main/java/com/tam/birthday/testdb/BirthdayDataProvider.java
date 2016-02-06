package com.tam.birthday.testdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Manoj on 2/5/2016.
 */
public class BirthdayDataProvider {

    private static final String tag = "BirthdayDataProvider";

    private static BirthdayDataProvider instance;

    private SQLiteDatabase sqLiteDatabase;
    private MySQLiteHelper mySQLiteHelper;

    private BirthdayDataProvider(Context context) {
        mySQLiteHelper = new MySQLiteHelper(context);
    }

    public static BirthdayDataProvider getInstance(Context context) {
        if(instance == null) {
            instance = new BirthdayDataProvider(context);
        }
        return instance;
    }

    public void insertMockData() {
        try {
            sqLiteDatabase = mySQLiteHelper.getWritableDatabase();

            // insert Arun's data
            ContentValues arunBdayData = new ContentValues();
            arunBdayData.put(MySQLiteHelper._ID, 0);
            arunBdayData.put(MySQLiteHelper.NAME, "Arun Vijapur");
            arunBdayData.put(MySQLiteHelper.DATE_OF_BIRTH, "02/09/1983");
            arunBdayData.put(MySQLiteHelper.PHONE_NUMBER, "9900261866");
            arunBdayData.put(MySQLiteHelper.IMAGE_URL, "http://watchdbsuper.com/wp-content/uploads/2015/07/45.png");
            sqLiteDatabase.insert(MySQLiteHelper.BIRTHDAY_TABLE, null, arunBdayData);

            // insert Nutan's data
            ContentValues nutanBdayData = new ContentValues();
            nutanBdayData.put(MySQLiteHelper._ID, 1);
            nutanBdayData.put(MySQLiteHelper.NAME, "Nutan Pallegar");
            nutanBdayData.put(MySQLiteHelper.DATE_OF_BIRTH, "08/02/1991");
            nutanBdayData.put(MySQLiteHelper.PHONE_NUMBER, "8095406294");
            nutanBdayData.put(MySQLiteHelper.IMAGE_URL, "http://watchdbsuper.com/wp-content/uploads/2015/07/18.png");
            sqLiteDatabase.insert(MySQLiteHelper.BIRTHDAY_TABLE, null, nutanBdayData);

            // insert Manoj's data
            ContentValues manojBdayData = new ContentValues();
            manojBdayData.put(MySQLiteHelper._ID, 2);
            manojBdayData.put(MySQLiteHelper.NAME, "Manoj Srivatsav");
            manojBdayData.put(MySQLiteHelper.DATE_OF_BIRTH, "21/08/1988");
            manojBdayData.put(MySQLiteHelper.PHONE_NUMBER, "9632710282");
            manojBdayData.put(MySQLiteHelper.IMAGE_URL, "http://watchdbsuper.com/wp-content/uploads/2015/07/37.png");
            sqLiteDatabase.insert(MySQLiteHelper.BIRTHDAY_TABLE, null, manojBdayData);

            // insert Prashanth's data
            ContentValues prashanthBDayData = new ContentValues();
            prashanthBDayData.put(MySQLiteHelper._ID, 3);
            prashanthBDayData.put(MySQLiteHelper.NAME, "Prashanth A");
            prashanthBDayData.put(MySQLiteHelper.DATE_OF_BIRTH, "22/12/1980");
            prashanthBDayData.put(MySQLiteHelper.PHONE_NUMBER, "9886343914");
            prashanthBDayData.put(MySQLiteHelper.IMAGE_URL, "http://watchdbsuper.com/wp-content/uploads/2015/07/29.jpg");
            sqLiteDatabase.insert(MySQLiteHelper.BIRTHDAY_TABLE, null, prashanthBDayData);


            // insert Aswin's data

            // insert Leena's data





        } catch(Exception ex) {
            Log.e(tag, "Could not insert mock data into table. Reason - " + ex.getMessage());
        } finally {
            sqLiteDatabase.close();
            mySQLiteHelper.close();
        }
    }

    public Cursor getAllBirthdayInfo() {
        Cursor cursor = null;
        try {
            sqLiteDatabase = mySQLiteHelper.getReadableDatabase();

            StringBuilder selectQueryBuilder = new StringBuilder();
            selectQueryBuilder.append("SELECT * FROM ").append(MySQLiteHelper.BIRTHDAY_TABLE);

            cursor = sqLiteDatabase.rawQuery(selectQueryBuilder.toString(), null);
        } catch (Exception ex) {
            Log.e(tag, "Could not read data from mock table. Reason - " + ex.getMessage());
        } /*finally {
            sqLiteDatabase.close();
            mySQLiteHelper.close();
        }*/

        return cursor;
    }

}
