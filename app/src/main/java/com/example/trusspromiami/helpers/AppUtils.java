package com.example.trusspromiami.helpers;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static com.example.trusspromiami.BuildConfig.DEBUG;

public class AppUtils {

    public static Toast mToast;
    public static final String AUTHORITY = "com.ianhanniballake.localstorage.documents";

    public static long getLongDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    public static long getOneMonthAgoDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.MONTH, -1);
        return calendar.getTimeInMillis();
    }

    public static String getStringDate() {
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        SimpleDateFormat format1 = new SimpleDateFormat(AppConstants.DATE_FORMAT_TEXT);

        return format1.format(calendar.getTime());
    }

/*
    public static Intent getShareIntent() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

    sendIntent.putExtra(Intent.EXTRA_TEXT,
                AppConstants.SHARE_CONTENT + "\n " + AppConstants.GOOGLE_PLAY_URL + AppConstants.mContext.getPackageName());
        sendIntent.setType("text/plain");
        return sendIntent;
    }
*/


    public static Intent getProductShareIntent(String productUrl) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        sendIntent.putExtra(Intent.EXTRA_TEXT, productUrl);
        sendIntent.setType("text/plain");
        return sendIntent;
    }

/*    public static Intent getLikeUsIntent() {
        return new Intent(Intent.ACTION_VIEW, Uri.parse(AppConstants.GOOGLE_PLAY_URL + AppConstants.mContext.getPackageName()));
    }*/

    public static Intent getOpenUrlIntent(String url) {
        return new Intent(Intent.ACTION_VIEW, Uri.parse(url));
    }

/*    public static boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) AppConstants.mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        return false;
    }*/

    public static void showAToast(AppCompatActivity activity, String message) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(activity, message, Toast.LENGTH_SHORT);
        mToast.show();
    }

    public static String insertNewLine(String name) {
        return name.replace(" ", "\n");
    }

    public static String formatPriceString(String price) {

        String[] prices = price.split("-");

        if (!prices[0].isEmpty()) {

            try {

                prices[0] = NumberFormat.getNumberInstance(Locale.US).format(Double.parseDouble(prices[0]));

                if (prices.length > 1) {
                    prices[1] = NumberFormat.getNumberInstance(Locale.US).format(Double.parseDouble(prices[1]));
                    return "PKR " + prices[0] + "-" + prices[1];
                } else {
                    return "PKR " + prices[0];
                }
            } catch (NumberFormatException e) {

                e.printStackTrace();
                return "PKR " + price;
            }

        } else {

            try {
                // for minus values
                prices[1] = NumberFormat.getNumberInstance(Locale.US).format(Double.parseDouble(prices[1]));
                return "PKR " + prices[0] + "-" + prices[1];
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return "PKR " + price;
            }
        }

    }

    public static String formatForCartPriceString(String price, Integer count) {

        String[] prices = price.split("-");

        Double itemPrice = Double.parseDouble(prices[0]);
        Double itemTotalPrice = itemPrice * count;

        try {

            prices[0] = NumberFormat.getNumberInstance(Locale.US).format(itemTotalPrice);

            if (prices.length > 1) {
                prices[1] = NumberFormat.getNumberInstance(Locale.US).format(Double.parseDouble(prices[1]));
                return "PKR " + prices[0] + "-" + prices[1];
            } else {
                return "PKR " + prices[0];
            }
        } catch (NumberFormatException e) {

            e.printStackTrace();

            return "PKR " + itemTotalPrice;
        }
    }

    public static String formatName(String str) {

        return "Name: " + str;
    }

    public static String showRangePrice(String minPrice, String maxPrice) {

        StringBuilder price = new StringBuilder();

        minPrice = NumberFormat.getNumberInstance(Locale.US).format(Float.parseFloat(minPrice));
        maxPrice = NumberFormat.getNumberInstance(Locale.US).format(Float.parseFloat(maxPrice));

        price.append("PKR ");
        price.append(minPrice);

        if (!minPrice.equals(maxPrice)) {
            price.append("-");
            price.append(maxPrice);
        }
        return price.toString();
    }

    public static String toString(Integer value) {

        return String.valueOf(value);
    }

    public static String toString(Double value) {

        return String.valueOf(value);
    }

    public static String toLowerCase(String str) {

        return str.substring(0, 1).toUpperCase().concat(str.substring(1).toLowerCase());
    }

    public static boolean isPriceEqual(String salePrice, String actualPrice) {

        return salePrice.equals(actualPrice);
    }

    public static boolean isClaimed(String claim) {

        // 1 ==> not claimed
        // 2 ==>
        return claim.equals("2");
    }

    public static String getClaimType(Integer claimType) {

        // null pending,
        // 0 reject with reason
        // 1 approved

        if (claimType == null) {
            return "Pending";
        }
        if (claimType == 0) {
            return "Rejected";
        } else if (claimType == 1) {
            return "Approved";
        } else {
            return "Pending";
        }
    }

    public static String appendIntoBracket(String str) {

        if (str == null)
            return "";

        String mStr = "( ";
        mStr = mStr.concat(str);
        mStr = mStr.concat(" )");

        return mStr;
    }

    public static String trimUserFirstName(String str) {

        if (str == null)
            return "";

        String[] names = str.split(" ");

        return names[0];
    }


    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static String concatString(String str1, String str2) {

        return str1.concat(" ").concat(str2);
    }

    public static String concatString(Integer str1, String str2) {

        return String.valueOf(str1).concat(" ").concat(str2);
    }

    public static String concatString(String str1, Integer str2) {

        return str1.concat(" ").concat(String.valueOf(str2));
    }

    public static String concatString(Integer str1, Integer str2) {

        return String.valueOf(str1).concat(" ").concat(String.valueOf(str2));
    }

    public static String formatSearchUrl(String str) {

        if (str != null && (str.contains("-")))
            return str.replace("-", " ");
        else
            return str;
    }

    public static String formatSearchUrlRemoveSlash(String str) {

        if (str != null && str.contains("/"))
            return str.replace("/", " ").replace("-", " ");
        else
            return str;
    }

    public static Integer toInteger(String quantity) {

        return Integer.parseInt(quantity);
    }

    public static String caculatePercentage(String actualPrice, String salePrice) {

        Double aPrice, sPrice, difference, percentage;

        aPrice = Double.parseDouble(actualPrice);
        sPrice = Double.parseDouble(salePrice);

        difference = aPrice - sPrice;
        percentage = (difference * 100) / aPrice;

        return String.valueOf(Math.round(percentage)).concat("% OFF");
    }

    public static void showStrikeOnTextView(TextView textView) {
        textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);  // making price for sales
    }

    public static String trimLastComa(String trimString) {

        StringBuffer sb = new StringBuffer(trimString);
        sb.replace(trimString.lastIndexOf(","), trimString.lastIndexOf(",") + 1, "");

        return sb.toString();
    }

    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;

        return toLowerCase(manufacturer);
    }

    public static String getDeviceModel() {

        String model = Build.MODEL;

        return toLowerCase(model);
    }

    public static String getDeviceOS() {

        String operatingSystem = "sdk int:" + Build.VERSION.SDK_INT + " and Release version:" + Build.VERSION.RELEASE;
        return operatingSystem;
    }


/*    public static String getBuildVersion() {

        try {
            PackageInfo pInfo = AppConstants.mContext.getPackageManager().getPackageInfo(AppConstants.mContext.getPackageName(), 0);
            String version = pInfo.versionName;
            int verCode = pInfo.versionCode;
            return version;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }*/

/*    public static String getDeviceId() {

        @SuppressLint("HardwareIds")
        String androidDeviceId = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);

        return androidDeviceId;
    }*/

    public static String createProductUrl(String str) {
        str = str.replace(" ", "-");
        str = str.toLowerCase();

        return str;
    }

    public static File getFile(Context context, Uri uri) {
        if (uri != null) {
            String path = getPath(context, uri);
            if (path != null && isLocal(path)) {
                return new File(path);
            }
        }
        return null;
    }

    public static String getPath(final Context context, final Uri uri) {

        if (DEBUG)
            Log.d("TAG" + " File -",
                    "Authority: " + uri.getAuthority() +
                            ", Fragment: " + uri.getFragment() +
                            ", Port: " + uri.getPort() +
                            ", Query: " + uri.getQuery() +
                            ", Scheme: " + uri.getScheme() +
                            ", Host: " + uri.getHost() +
                            ", Segments: " + uri.getPathSegments().toString()
            );

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // LocalStorageProvider
            if (isLocalStorageDocument(uri)) {
                // The path is the id
                return DocumentsContract.getDocumentId(uri);
            }
            // ExternalStorageProvider
            else if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }


    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                if (DEBUG)
                    DatabaseUtils.dumpCursor(cursor);

                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    public static boolean isLocalStorageDocument(Uri uri) {
        return AUTHORITY.equals(uri.getAuthority());
    }

    public static boolean isLocal(String url) {
        if (url != null && !url.startsWith("http://") && !url.startsWith("https://")) {
            return true;
        }
        return false;
    }

    public static Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    public static Bitmap FileToBitmap(File file) {
        File mSaveBit = file; // Your image file
        String filePath = mSaveBit.getPath();
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        return bitmap;
        //  mImageView.setImageBitmap(bitmap);
    }

    public static File bitmapToFile(Bitmap bmp) throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 60, bytes);
        File f = new File(Environment.getExternalStorageDirectory()
                + File.separator + "testimage.jpg");
        f.createNewFile();
        FileOutputStream fo = new FileOutputStream(f);
        fo.write(bytes.toByteArray());
        fo.close();
        return f;
    }

    public static void blockUi(Activity activity) {

        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public static void unBlockUi(Activity activity) {
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public static void printHashKey(Context pContext) {
        try {
            PackageInfo info = pContext.getPackageManager().getPackageInfo(pContext.getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String hashKey = new String(Base64.encode(md.digest(), 0));
                Log.i("TAG", "printHashKey() Hash Key: " + hashKey);
            }
        } catch (NoSuchAlgorithmException e) {
            Log.e("TAG", "printHashKey()", e);
        } catch (Exception e) {
            Log.e("TAG", "printHashKey()", e);
        }
    }

    public static boolean compareCartItemStatus(String str1) {

        return str1.equals("1");
    }


  /*  public static void getStaticsFromPreferences() {

        AppPreference appPreference = AppPreference.getInstance(AppConstants.mContext);

        ABOUT_US_URL = appPreference.getString(ABOUT_US_URL_KEY);
        RETURN_POLICY_URL = appPreference.getString(RETURN_POLICY_URL_KEY);
        TERMS_AND_CONDITIONS_URL = appPreference.getString(TERMS_AND_CONDITIONS_URL_KEY);
        FAQ_URL = appPreference.getString(FAQ_URL_KEY);
        BLOGS = appPreference.getString(BLOGS_URL_KEY);

        PRODUCT_STORAGE_BASE_URL = appPreference.getString(PRODUCT_STORAGE_BASE_URL_KEY);
        MEDIUM_PRODUCT_STORAGE_BASE_URL = appPreference.getString(MEDIUM_PRODUCT_STORAGE_BASE_URL_KEY);
        SMALL_PRODUCT_STORAGE_BASE_URL = appPreference.getString(SMALL_PRODUCT_STORAGE_BASE_URL_KEY);
        THUMBNAIL_PRODUCT_STORAGE_BASE_URL = appPreference.getString(THUMBNAIL_PRODUCT_STORAGE_BASE_URL_KEY);

        BRAND_STORAGE_BASE_URL = appPreference.getString(BRAND_STORAGE_BASE_URL_KEY);
        BRAND_FOCUS_STORAGE_BASE_URL = appPreference.getString(BRAND_FOCUS_STORAGE_BASE_URL_KEY);
        CATEGORY_FOCUS_STORAGE_BASE_URL = appPreference.getString(CATEGORY_FOCUS_STORAGE_BASE_URL_KEY);
        ACTION_STORAGE_BASE_URL = appPreference.getString(ACTION_STORAGE_BASE_URL_KEY);

        CUSTOM_PRODUCT_URL = appPreference.getString(CUSTOM_PRODUCT_URL_KEY);
        USER_STORAGE_BASE_URL = appPreference.getString(USER_STORAGE_BASE_URL_KEY);
        BANNER_STORAGE_BASE_URL = appPreference.getString(BANNER_STORAGE_BASE_URL_KEY);
        BLOG_URLS = appPreference.getString(BLOG_URLS_KEY);
        ADVERTISEMENT_URL = appPreference.getString(ADVERTISEMENT_URL_KEY);

        AppConstants.LOAD_HOME_FRAGMENT_INDEX = appPreference.getInt(LOAD_HOME_FRAGMENT_INDEX_KEY);

    }*/

    public static File saveBitmapToFile(File file) {
        try {

            // BitmapFactory options to downsize the image
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            o.inSampleSize = 8;
            // factor of downsizing the image

            FileInputStream inputStream = new FileInputStream(file);
            //Bitmap selectedBitmap = null;
            BitmapFactory.decodeStream(inputStream, null, o);
            inputStream.close();

            // The new size we want to scale to
            final int REQUIRED_SIZE = 75;

            // Find the correct scale value. It should be the power of 2.
            int scale = 1;
            while (o.outWidth / scale / 2 >= REQUIRED_SIZE &&
                    o.outHeight / scale / 2 >= REQUIRED_SIZE) {
                scale *= 2;
            }

            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            inputStream = new FileInputStream(file);

            Bitmap selectedBitmap = BitmapFactory.decodeStream(inputStream, null, o2);
            inputStream.close();

            // here i override the original image file
            file.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(file);

            selectedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);

            return file;
        } catch (Exception e) {
            return null;
        }
    }
}