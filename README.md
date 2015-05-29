Android M Runtime Permissions Demo.Helps to understand the runtime permission to open contacts enlisted in AndroidManifest.xml

1.Add this in AndroidManifest.xml
  
 ```
 <uses-permission-sdk-m android:name="android.permission.READ_CONTACTS" />
 <uses-permission-sdk-m android:name="android.permission.WRITE_CONTACTS" />
 ```

2.```requestPermissions(CONTACT_PERMISSIONS, REQUEST_CONTACTS_CODE); ```
	gives the callback for respective permissions into:
  
  ```public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults);```
