package com.example.android.inventory;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.inventory.data.ProductContract;


/**
 * {@link ProductCursorAdapter} is an adapter for a list or grid view
 * that uses a {@link Cursor} of pet data as its data source. This adapter knows
 * how to create list items for each row of pet data in the {@link Cursor}.
 */
public class ProductCursorAdapter extends CursorAdapter {

    /**
     * Constructs a new {@link ProductCursorAdapter}.
     *
     * @param context The context
     * @param c       The cursor from which to get the data.
     */
    public ProductCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    /**
     * Makes a new blank list item view. No data is set (or bound) to the views yet.
     *
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already
     *                moved to the correct position.
     * @param parent  The parent to which the new view is attached to
     * @return the newly created list item view.
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Inflate a list item view using the layout specified in list_item.xml
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    /**
     * This method binds the product data (in the current row pointed to by cursor) to the given
     * list item layout. For example, the name for the current product can be set on the name TextView
     * in the list item layout.
     *
     * @param view    Existing view, returned earlier by newView() method
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already moved to the
     *                correct row.
     */
    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        // Find individual views that we want to modify in the list item layout
        TextView nameTextView = (TextView) view.findViewById ( R.id.name );
        final TextView quantityTextView = (TextView) view.findViewById ( R.id.quantity );
        TextView priceTextView = (TextView) view.findViewById ( R.id.price );
        TextView supplierTextView = (TextView) view.findViewById ( R.id.supplier );
        TextView phoneTextView = (TextView) view.findViewById ( R.id.phone );
        // Find the columns of product attributes that we're interested in
        int nameColumnIndex = cursor.getColumnIndex ( ProductContract.ProductEntry.PRODUCT_NAME );
        int quantityColumnIndex = cursor.getColumnIndex ( ProductContract.ProductEntry.QUANTITY );
        int priceColumnIndex = cursor.getColumnIndex ( ProductContract.ProductEntry.PRICE );
        int supplierColumnIndex = cursor.getColumnIndex ( ProductContract.ProductEntry.SUPPLIER_NAME );
        int phoneColumnIndex = cursor.getColumnIndex ( ProductContract.ProductEntry.SUPPLIER_PHONE_NUMBER );
        // Read the pet attributes from the Cursor for the current product
        String productName = cursor.getString ( nameColumnIndex );
        final String productQuantity = cursor.getString ( quantityColumnIndex );
        String productPrice = cursor.getString ( priceColumnIndex );
        String productSupplier = cursor.getString ( supplierColumnIndex );
        String supplierPhone = cursor.getString ( phoneColumnIndex );
        // Update the TextViews with the attributes for the current product
        nameTextView.setText ( productName );
        quantityTextView.setText ( productQuantity );
        priceTextView.setText ( productPrice );
        supplierTextView.setText ( productSupplier );
        phoneTextView.setText ( supplierPhone );
        TextView soldButton = (TextView) view.findViewById(R.id.sale_button);
        final TextView saleTextView = (TextView) view.findViewById(R.id.sale_view);
        int saleColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry._ID);
        saleTextView.setText(cursor.getString(saleColumnIndex));
        //set click listener for this view
        soldButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // pull in quantity from textview
                int changeQuantity = Integer.parseInt(quantityTextView.getText().toString().trim());
                if (changeQuantity > 0) {
                    //adjust quantity
                    changeQuantity -= 1;
                    quantityTextView.setText(Integer.toString(changeQuantity));
                    // get secret id from view
                    long id_number = Integer.parseInt(saleTextView.getText().toString());
                    Uri productSelected = ContentUris.withAppendedId(ProductContract.ProductEntry.CONTENT_URI,id_number);
                    ContentValues values = new ContentValues();
                    values.put(ProductContract.ProductEntry.QUANTITY,quantityTextView.getText().toString());
                    // update datebase
                    int rowsAffected = context.getContentResolver().update(productSelected, values, null, null);
                    // Show a toast message depending on whether or not the update was successful.
                    if (rowsAffected == 0) {
                        // If no rows were affected, then there was an error with the update.
                        Toast.makeText(context, R.string.sale_eror, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, R.string.one_sold, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, R.string.no_stock, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}