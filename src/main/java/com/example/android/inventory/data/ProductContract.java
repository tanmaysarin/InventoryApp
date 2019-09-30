package com.example.android.inventory.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public final class ProductContract {

    private ProductContract() {}


    public static final String CONTENT_AUTHORITY = "com.example.android.inventory";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_PRODUCTS = "products";

        public static final class ProductEntry implements BaseColumns{

            public final static String _ID = BaseColumns._ID;
            public final static String PRODUCT_NAME = "product_Name";
            public final static String TABLE_NAME = "products";
            public final static String PRICE = "price";
            public final static String QUANTITY = "quantity";
            public final static String SUPPLIER_NAME = "supplier_Name";
            public final static String SUPPLIER_PHONE_NUMBER = "supplier_Phone_Number";

            public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PRODUCTS);

            /**
             * The MIME type of the {@link #CONTENT_URI} for a list of pets.
             */
            public static final String CONTENT_LIST_TYPE =
                    ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PRODUCTS;

            /**
             * The MIME type of the {@link #CONTENT_URI} for a single pet.
             */
            public static final String CONTENT_ITEM_TYPE =
                    ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PRODUCTS;

            // Sell option
            public final static String COLUMNS_PRODUCT_CAN_SELL = "sell";
            //Possible values for if the product can be sold or not.
            public static final int CAN_SELL_UNKNOWN = 0;
            public static final int CAN_SELL_YES = 1;
            public static final int CAN_SELL_NO = 2;


        }
}
