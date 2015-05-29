![first_capture](https://cloud.githubusercontent.com/assets/10304040/7883031/ee96f3f0-0630-11e5-8b77-44b696bea53a.png)

Install from SDK Manager:

![androidmpreview](https://cloud.githubusercontent.com/assets/10304040/7883137/c07486e4-0631-11e5-9865-7d9d64046365.png)


Android M Runtime Permissions Demo ,helps to understand the runtime permission to open contacts enlisted in AndroidManifest.xml

1.Add this in AndroidManifest.xml
  
 ```
 <uses-permission-sdk-m android:name="android.permission.READ_CONTACTS" />
 <uses-permission-sdk-m android:name="android.permission.WRITE_CONTACTS" />
 ```

2.```requestPermissions(CONTACT_PERMISSIONS, REQUEST_CONTACTS_CODE); ```
	gives the callback for respective permissions into:
  
  ```public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults);```
