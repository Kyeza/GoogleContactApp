package com.example.googlecontactapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.example.googlecontactapp.adaptors.ActionButtonsAdaptor;
import com.example.googlecontactapp.models.Contact;

public class DisplayContactActivity extends AppCompatActivity implements
        ActionButtonsAdaptor.ActionButtonsAdapterOnClickHandler {

    private Contact mContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_contact);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            mContact = (Contact) bundle.getSerializable(MainActivity.EXTRA_MESSAGE);
        }

        TextView mFirstNameTV = findViewById(R.id.tv_full_name);
        TextView mPhoneTV = findViewById(R.id.tv_phone);
        TextView mAddressTV = findViewById(R.id.tv_address);
        TextView mEmailTV = findViewById(R.id.tv_email);
        TextView mWebsiteTV = findViewById(R.id.tv_website);

        mFirstNameTV.setText(String.format("%s %s", mContact.getFirstName(),
                mContact.getLastName()));
        mPhoneTV.setText(mContact.getPhoneNumber());
        mAddressTV.setText(mContact.getAddress());
        mEmailTV.setText(mContact.getEmail());
        mWebsiteTV.setText(mContact.getWebsite());

        initRecyclerView();
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        RecyclerView mButtonList = findViewById(R.id.rv_action_btns);
        mButtonList.setLayoutManager(layoutManager);
        ActionButtonsAdaptor buttonsAdaptor = new ActionButtonsAdaptor(this);
        mButtonList.setAdapter(buttonsAdaptor);
    }

    @Override
    public void onClick(int buttonId) {
        Intent intent;
        switch (buttonId + 1) {
            case 1:
                intent = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:" + mContact.getPhoneNumber().trim()));
                break;
            case 2:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("sms:" + mContact.getPhoneNumber()));
                break;
            case 3:
                intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", mContact.getEmail(), null));
                break;
            case 4:
                intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.google.com/maps/search/?api=1&" + mContact.getAddress()));
                break;
            case 5:
                intent = new Intent(Intent.ACTION_VIEW);
                String url = mContact.getWebsite();
                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    url = "http://" + url;
                }
                intent.setData(Uri.parse(url));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + buttonId);
        }

        try {
            startActivity(intent);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }
}
