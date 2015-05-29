package com.code2concept.androidmruntimepermission.activity;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.code2concept.androidmruntimepermission.R;

import java.util.ArrayList;

/**
 * Created by nitesh on 29/5/15.
 */
public class ContactsDemoActivity extends FragmentActivity implements View.OnClickListener , LoaderManager.LoaderCallbacks<Cursor>{

    private static final String TAG = ContactsDemoActivity.class.getName();

    private static final String PHONE_NUMBER = "1234567890";

    private static String DUMMY_CONTACT_NAME = "Dumb Contact for Emulator";

    private Button addDummyContactBtn;

    private Button showDummyContactsBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_contacts);

        addDummyContactBtn = (Button)findViewById(R.id.addDummyContactsBtn);
        showDummyContactsBtn = (Button)findViewById(R.id.showDummyContactsBtn);

        addDummyContactBtn.setOnClickListener(this);
        showDummyContactsBtn.setOnClickListener(this);

    }

    /**
     * Restart the Loader to query the Contacts content provider to display the first contact.
     */
    private void loadContact() {
        getSupportLoaderManager().restartLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {

        String[] PROJECTION = {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME_PRIMARY};

        String ORDER = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " ASC";


        return new CursorLoader(this, ContactsContract.Contacts.CONTENT_URI, PROJECTION,
                null, null, ORDER);
    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        if (cursor != null) {
            final int totalCount = cursor.getCount();
            if (totalCount > 0) {

                cursor.moveToFirst();
                showToast("Name: "+cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));

            } else {
                showToast("No contacts");
            }
        }
    }

    private void showToast(String msg) {
        Toast.makeText(this , msg , Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    }

    private void insertDummyContact() {

        ArrayList<ContentProviderOperation> operations = new ArrayList<ContentProviderOperation>(2);

        ContentProviderOperation.Builder op =
                ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                        .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                        .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null);
        operations.add(op.build());

        op = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE,
                        ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,
                        DUMMY_CONTACT_NAME)
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER , PHONE_NUMBER);
        operations.add(op.build());


        ContentResolver resolver = getContentResolver();
        try {
            resolver.applyBatch(ContactsContract.AUTHORITY, operations);
        } catch (RemoteException e) {
            Log.d(TAG, "Cannot add new Contact || " + e.getMessage());
        } catch (OperationApplicationException e) {
            Log.d(TAG, "Cannot add new Contact || " + e.getMessage());
        }
    }

    @Override
    public void onClick(View v) {

        if (v == showDummyContactsBtn){
            loadContact();
        }else if (v == addDummyContactBtn){
            insertDummyContact();
        }

    }
}
