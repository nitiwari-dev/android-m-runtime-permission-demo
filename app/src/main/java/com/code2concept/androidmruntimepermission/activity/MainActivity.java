package com.code2concept.androidmruntimepermission.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.code2concept.androidmruntimepermission.R;
import com.code2concept.androidmruntimepermission.Utils.AppUtils;


public class MainActivity extends FragmentActivity {


    /**
     * Read and write permission for contacts listed here.
     */
    private static String CONTACT_PERMISSIONS[] = new String[]{
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS
    };


    private final static int REQUEST_CONTACTS_CODE = 100;

    private Button showContactButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    private void init() {

        showContactButton       = (Button) findViewById(R.id.showContactsButton);
        showContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showContacts();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {

        if (requestCode == REQUEST_CONTACTS_CODE){

            if (AppUtils.verifyAllPermissions(grantResults)) {
                Toast.makeText(this, "Permission Granted.Contacts available", Toast.LENGTH_SHORT)
                        .show();
            } else {
                Toast.makeText(this, "Permission Not Granted", Toast.LENGTH_SHORT).show();
            }

        }else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }



    public void showContacts() {
        if (AppUtils.hasSelfPermission(this, CONTACT_PERMISSIONS)) {
            showContactFragment();
        } else {
            requestPermissions(CONTACT_PERMISSIONS, REQUEST_CONTACTS_CODE);
        }
    }

    private void showContactFragment() {
        startActivity(new Intent(this, ContactsDemoActivity.class));
    }
}
