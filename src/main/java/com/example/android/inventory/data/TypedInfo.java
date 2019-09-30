package com.example.android.inventory.data;

import android.text.TextUtils;

class TypedInfo {public boolean TypedInfoTrue (String name, String quantity, String price, String supplier, String phone ) {
    boolean isGood = true;
    if (TextUtils.isEmpty(name)) {
        isGood = false;
    }
    //check quantity
    if (TextUtils.isEmpty(quantity)) {
        isGood = false;
    }
    //check price
    if (TextUtils.isEmpty(price)) {
        isGood = false;
    }
    //check supplier
    if (TextUtils.isEmpty(supplier)) {
        isGood = false;
    }
    //check phone number
    if (TextUtils.isEmpty(phone)) {
        isGood = false;
    }
    return isGood;
}
}