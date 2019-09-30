package com.example.android.inventory.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.inventory.data.ProductContract.ProductEntry;
/**
 * Database helper for Products app. Manages database creation and version management.
 */
public class ProductDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = ProductDbHelper.class.getSimpleName();

    /**
     * Name of the database file
     */
    private static final String DATABASE_NAME = "products.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link ProductDbHelper}.
     *
     * @param context of the app
     */
    public ProductDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create a string that contains the SQL statement to create the product's table
        String SQL_CREATE_PRODUCTS_TABLE = "CREATE TABLE " + ProductEntry.TABLE_NAME + " ("
                + ProductEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ProductEntry.PRODUCT_NAME + " TEXT NOT NULL, "
                + ProductEntry.PRICE + " DOUBLE NOT NULL, "
                + ProductEntry.COLUMNS_PRODUCT_CAN_SELL + "  INTEGER, "
                + ProductEntry.QUANTITY + " INTEGER NOT NULL DEFAULT 0, "
                + ProductEntry.SUPPLIER_NAME + " TEXT NOT NULL, "
                + ProductEntry.SUPPLIER_PHONE_NUMBER + " INTEGER NOT NULL);";
        //Execute the SQL statement
        db.execSQL(SQL_CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade ( db, oldVersion, newVersion );
    }
}