package com.example.googlecontactapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.googlecontactapp.models.Contact;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.googlecontactapp.CONTACT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void saveContact(View view) {
        EditText mFirstName = findViewById(R.id.et_first_name);
        EditText mLastName = findViewById(R.id.et_last_name);
        EditText mPhoneNumber = findViewById(R.id.et_phone_number);
        EditText mEmail = findViewById(R.id.et_email);
        EditText mAddress = findViewById(R.id.et_address);
        EditText mWebsite = findViewById(R.id.et_website);

        Contact mContact = new Contact();
        mContact.setFirstName(mFirstName.getText().toString());
        mContact.setLastName(mLastName.getText().toString());
        mContact.setPhoneNumber(mPhoneNumber.getText().toString());
        mContact.setEmail(mEmail.getText().toString());
        mContact.setAddress(mAddress.getText().toString());
        mContact.setWebsite(mWebsite.getText().toString());

        Intent displayContact = new Intent(this, DisplayContactActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_MESSAGE, mContact);
        displayContact.putExtras(bundle);
        startActivity(displayContact);
    }
}
