package com.tam.birthday.testdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Manoj Srivatsav on 2/4/2016.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {
    private static String tag = "MySQLiteHelper";

    private static final String DATABASE_NAME = "birthdaydb";
    private static final int DATABASE_VERSION = 1;

    // table names
    public static final String BIRTHDAY_TABLE = "birthday_book";

    // columns
    public static final String _ID = "_id";
    public static final String IMAGE_URL = "imageurl";
    public static final String NAME = "name";
    public static final String DATE_OF_BIRTH = "dob";
    public static final String PHONE_NUMBER = "phoneNumber";


    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder createTableBuilder = new StringBuilder();
        createTableBuilder.append("CREATE TABLE ").append(BIRTHDAY_TABLE).append(" (")
                .append(_ID).append(" INTEGER NOT NULL, ")
                .append(NAME).append(" TEXT NOT NULL, ")
                .append(DATE_OF_BIRTH).append(" TEXT NOT NULL, ")
                .append(PHONE_NUMBER).append(" TEXT NOT NULL, ")
                .append(IMAGE_URL).append(" TEXT NOT NULL" ).append(")");

        db.execSQL(createTableBuilder.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        StringBuilder dropTableBuilder = new StringBuilder();
        dropTableBuilder.append("DROP TABLE IF EXIST ").append(BIRTHDAY_TABLE);

        db.execSQL(dropTableBuilder.toString());
    }
}
