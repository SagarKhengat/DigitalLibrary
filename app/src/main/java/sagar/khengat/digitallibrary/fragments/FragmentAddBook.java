package sagar.khengat.digitallibrary.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import sagar.khengat.digitallibrary.Constants.Config;
import sagar.khengat.digitallibrary.R;
import sagar.khengat.digitallibrary.model.Book;
import sagar.khengat.digitallibrary.util.DatabaseHandler;
import sagar.khengat.digitallibrary.util.InputValidation;


/**
 * Created by Sagar Khengat on 04/03/2018.
 */

public class FragmentAddBook extends Fragment implements View.OnClickListener {
    private TextInputLayout textInputLayoutBookName;
    private TextInputLayout textInputLayoutBookId;
    private TextInputLayout textInputLayoutBookPublishingHouse;
    private TextInputLayout textInputLayoutBookAuther;
    private TextInputLayout textInputLayoutBookPages;
    private TextInputLayout textInputLayoutBookPrice;
    private TextInputLayout textInputLayoutBookCategory;


    private TextInputEditText textInputEditTextBookName;
    private TextInputEditText textInputEditTextBookId;
    private TextInputEditText textInputEditTextBookPublishingHouse;
    private TextInputEditText textInputEditTextBookPages;
    private TextInputEditText textInputEditTextBookAuther;
    private TextInputEditText textInputEditTextBookPrice;
    private TextInputEditText textInputEditTextBookCategory;


    Book book;
    ImageView image,iv_camera, iv_gallery;
    String FOLDER_NAME="DigitalLibrary";
    FloatingActionButton fab;
    View view;
    DatabaseHandler mDatabaseHandler;
    InputValidation inputValidation ;
    Spinner spinnerSubCategory;
    Spinner spinnerCategory;
    Gson gson;

    String mCurrentPhotoPath;
    Bitmap bitmap = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_add_product, container, false);
        inputValidation = new InputValidation(getActivity());

        mDatabaseHandler = new DatabaseHandler(getActivity());

        textInputLayoutBookName = (TextInputLayout) view.findViewById(R.id.textInputLayoutBookName);
        textInputLayoutBookId = (TextInputLayout) view.findViewById(R.id.textInputLayoutBookId);

        textInputLayoutBookAuther = (TextInputLayout) view.findViewById(R.id.textInputLayoutBookAuther);
        textInputLayoutBookCategory = (TextInputLayout) view.findViewById(R.id.textInputLayoutBookCategory);
        textInputLayoutBookPages = (TextInputLayout) view.findViewById(R.id.textInputLayoutBookPages);

        textInputLayoutBookPrice = (TextInputLayout) view.findViewById(R.id.textInputLayoutBookOriginalPrice);
        textInputLayoutBookPublishingHouse= (TextInputLayout) view.findViewById(R.id.textInputLayoutBookPublicationHouse);

        textInputEditTextBookName = (TextInputEditText) view.findViewById(R.id.textInputEditTextBookName);
        textInputEditTextBookId = (TextInputEditText) view.findViewById(R.id.textInputEditTextBookId);

        textInputEditTextBookAuther = (TextInputEditText) view.findViewById(R.id.textInputEditTextBookAuther);
        textInputEditTextBookCategory = (TextInputEditText) view.findViewById(R.id.textInputEditTextBookCategory);
        textInputEditTextBookPrice = (TextInputEditText) view.findViewById(R.id.textInputEditTextBookOriginalPrice);
        textInputEditTextBookPublishingHouse = (TextInputEditText) view.findViewById(R.id.textInputEditTextBookPublicationHouse);
        textInputEditTextBookPages = (TextInputEditText) view.findViewById(R.id.textInputEditTextBookPages);

        image = (ImageView) view.findViewById(R.id.image);
        iv_camera = (ImageView) view.findViewById(R.id.iv_camera);
        iv_gallery = (ImageView) view.findViewById(R.id.iv_gallery);

        fab = (FloatingActionButton)view.findViewById(R.id.fab);
        gson = new Gson();
        book = new Book();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }

            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 1);
            }
        }


        iv_camera.setOnClickListener(this);
        iv_gallery.setOnClickListener(this);
        fab.setOnClickListener(this);



        return view;
    }



    @Override
    public void onClick(View v) {
        switch ( v.getId() ) {
            case R.id.iv_camera:
                if (isCameraAvailable(getActivity())) {


                    try {
                        File photoFile = null;

                        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);


                        // Create the File where the photo should go

                        try {
                            photoFile = createImageFile();
                        } catch (IOException ex) {
                            // Error occurred while creating the File
                        }

                        if (photoFile != null) {
                            Uri photoURI = null;

                            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
                                photoURI = Uri.fromFile(photoFile);
                            } else {
                                photoURI = FileProvider.getUriForFile(getActivity(), "com.example.android.fileprovider",
                                        photoFile);
                            }

                            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                            startActivityForResult(cameraIntent, 0);
                        }
                    } catch (Exception e) {
                        Config.fnShowLongToastMessage(getActivity(), "Make sure camera is available.");
                    }
                } else {
                    Config.fnShowLongToastMessage(getActivity(), "Make sure camera is available.");
                }
                break;
            case R.id.iv_gallery:

                try {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent,
                            "Pick from gallery"), 1);
                } catch (Exception e) {
                    Config.fnShowLongToastMessage(getActivity(), "Something went wrong..please try again..");
                }


                break;

            case R.id.fab:


                        try {
                            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
                                inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0); // this method use to close keyboard forcefully
                                                 }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                        if (!inputValidation.isInputEditTextFilled(textInputEditTextBookName, textInputLayoutBookName, "Enter Book Name")) {
                            return;
                        }
                        else
                        {
                            book.setBookName(textInputEditTextBookName.getText().toString().trim());
                        }





                        if(!inputValidation.isInputEditTextFilled(textInputEditTextBookPrice, textInputLayoutBookPrice, "Enter Book's Original Price"))
                        {
                            return;
                        }
                        else
                        {
                            book.setBookOriginalPrice(Double.parseDouble(textInputEditTextBookPrice.getText().toString().trim()));
                        }
                if(!inputValidation.isInputEditTextFilled(textInputEditTextBookPublishingHouse, textInputLayoutBookPublishingHouse, "Enter book Publishing house"))
                {
                    return;
                }
                else
                {
                    book.setBookPublicationHouse(textInputEditTextBookPublishingHouse.getText().toString().trim());
                }
                if(!inputValidation.isInputEditTextFilled(textInputEditTextBookAuther, textInputLayoutBookAuther, "Enter book Author Name"))
                {
                    return;
                }
                else
                {
                    book.setBookAuther(textInputEditTextBookAuther.getText().toString().trim());
                }
                        if(!inputValidation.isInputEditTextFilled(textInputEditTextBookPages, textInputLayoutBookPages, "Enter Books pages"))
                        {
                            return;
                        }
                        else
                        {
                            book.setBookPages(Integer.parseInt(textInputEditTextBookPages.getText().toString().trim()));
                        }
                        if(!inputValidation.isInputEditTextFilled(textInputEditTextBookCategory, textInputLayoutBookCategory, "Enter Book Category"))
                        {
                            return;
                        }
                        else
                        {
                            book.setBookCategory(textInputEditTextBookCategory.getText().toString().trim());
                        }
                if(!inputValidation.isInputEditTextFilled(textInputEditTextBookId, textInputLayoutBookId, "Enter BT number of Book"))
                {
                    return;
                }
                else
                {
                    if(mDatabaseHandler.checkBook(textInputEditTextBookId.getText().toString().trim()))
                    {
                        Toast.makeText(getActivity(), "BT Number exist please try again..", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else {

                        book.setBookId(textInputEditTextBookId.getText().toString().trim());
                    }
                }




                        if(textInputEditTextBookName.getText().toString().equals("")){
                            Toast.makeText(getActivity(), getResources().getText(R.string.error_text_first), Toast.LENGTH_SHORT).show();
                        } else {

                            try{


                                mDatabaseHandler.addBook(book);


                                saveImageToSDCard(bitmap,book.getBookName(),book.getBookCategory());
                                Toast.makeText(getActivity(), getResources().getText(R.string.success_generate), Toast.LENGTH_LONG).show();

                            } catch (Exception e){
                                Toast.makeText(getActivity(), getResources().getText(R.string.error_generate), Toast.LENGTH_LONG).show();
                            }
                        }

                break;

        }
    }
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }
    public static boolean isCameraAvailable(Context context) {
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    //Handle choose image,
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ( resultCode == Activity.RESULT_OK ) {
            if ( requestCode == 1 ) {     //Gallery image
                try {

                    Uri selectedImageUri = data.getData();


                    bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImageUri);
                    image.setImageBitmap(bitmap);




                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Something went wrong..please try again..", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            } else {            //Captured image
                try {
                    int targetW = 500;
                    int targetH = 500;

                    // Get the dimensions of the bitmap
                    BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                    bmOptions.inJustDecodeBounds = true;
                    BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
                    int photoW = bmOptions.outWidth;
                    int photoH = bmOptions.outHeight;

                    // Determine how much to scale down the image
                    int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

                    // Decode the image file into a Bitmap sized to fill the View
                    bmOptions.inJustDecodeBounds = false;
                    bmOptions.inSampleSize = scaleFactor;
                    bmOptions.inPurgeable = true;


//				Bundle extras = data.getExtras();
                    bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
                    image.setImageBitmap(bitmap);

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
    }


public void saveImageToSDCard(Bitmap bitmap, String name, String areaName) {

        File myDir = new File(
        Environment.getExternalStorageDirectory().getPath()
        + File.separator
        +FOLDER_NAME+  File.separator
                + areaName);

        myDir.mkdirs();

        String fname = name.trim() + ".jpg";
        File file = new File(myDir, fname);
        if (file.exists())
        file.delete();
        try {
        FileOutputStream out = new FileOutputStream(file);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
        out.flush();
        out.close();

            try
            {
                DeleteRecursive(getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES));

            }catch (Exception e)
            {
                e.printStackTrace();
            }

        } catch (Exception e) {
        e.printStackTrace();

        }
        }
    public static void DeleteRecursive(File fileOrDirectory) {

        if (fileOrDirectory.isDirectory())
            for (File child : fileOrDirectory.listFiles())
                DeleteRecursive(child);

        fileOrDirectory.delete();

    }

        }
