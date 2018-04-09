package sagar.khengat.digitallibrary.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import sagar.khengat.digitallibrary.LoginActivity;
import sagar.khengat.digitallibrary.R;
import sagar.khengat.digitallibrary.Register;
import sagar.khengat.digitallibrary.activities.MainActivityForAdmin;
import sagar.khengat.digitallibrary.model.Student;
import sagar.khengat.digitallibrary.util.DatabaseHandler;
import sagar.khengat.digitallibrary.util.InputValidation;

/**
 * Created by Sagar Khengat on 08/04/2018.
 */

public class Fragment_Add_Student extends Fragment implements View.OnClickListener {
    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutId;
    private TextInputLayout textInputLayoutAddress;
    private TextInputLayout textInputLayoutContactNo;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfirmPassword;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutBranch;

    private TextInputEditText textInputEditTextName;
    private TextInputEditText textInputEditTextId;
    private TextInputEditText textInputEditTextAddress;
    private TextInputEditText textInputEditTextContactNo;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextConfirmPassword;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextBranch;

    private AppCompatButton appCompatButtonRegister;


    Student student;

    View view;
    DatabaseHandler mDatabaseHandler;
    InputValidation inputValidation ;

    Gson gson;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_add_student, container, false);
        inputValidation = new InputValidation(getActivity());

        mDatabaseHandler = new DatabaseHandler(getActivity());
        student = new Student();
        nestedScrollView = (NestedScrollView) view.findViewById(R.id.nestedScrollView);

        textInputLayoutName = (TextInputLayout) view.findViewById(R.id.textInputLayoutName);
        textInputLayoutContactNo = (TextInputLayout) view.findViewById(R.id.textInputLayoutContact);
        textInputLayoutPassword = (TextInputLayout) view.findViewById(R.id.textInputLayoutPassword);
        textInputLayoutConfirmPassword = (TextInputLayout) view.findViewById(R.id.textInputLayoutConfirmPassword);
        textInputLayoutEmail= (TextInputLayout) view.findViewById(R.id.textInputLayoutCustomerEmail);
        textInputLayoutAddress= (TextInputLayout) view.findViewById(R.id.textInputLayoutCustomerAddress);
        textInputLayoutId= (TextInputLayout) view.findViewById(R.id.textInputLayoutId);
        textInputLayoutBranch= (TextInputLayout) view.findViewById(R.id.textInputLayoutBranch);

        textInputEditTextName = (TextInputEditText) view.findViewById(R.id.textInputEditTextName);
        textInputEditTextContactNo = (TextInputEditText) view.findViewById(R.id.textInputEditTextContact);
        textInputEditTextPassword = (TextInputEditText) view.findViewById(R.id.textInputEditTextPassword);
        textInputEditTextConfirmPassword = (TextInputEditText) view.findViewById(R.id.textInputEditTextConfirmPassword);
        textInputEditTextEmail= (TextInputEditText) view.findViewById(R.id.textInputEditTextCustomerEmail);
        textInputEditTextAddress= (TextInputEditText) view.findViewById(R.id.textInputEditTextCustomerAddress);
        textInputEditTextId= (TextInputEditText) view.findViewById(R.id.textInputEditTextId);
        textInputEditTextBranch= (TextInputEditText) view.findViewById(R.id.textInputEditTextBranch);

        appCompatButtonRegister = (AppCompatButton) view.findViewById(R.id.appCompatButtonRegister);

        appCompatButtonRegister.setOnClickListener(this);


        return view;
    }



    @Override
    public void onClick(View v) {
        switch ( v.getId() ) {


            case R.id.appCompatButtonRegister:


                try {
                    InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0); // this method use to close keyboard forcefully
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                if (!inputValidation.isInputEditTextFilled(textInputEditTextName, textInputLayoutName, getString(R.string.error_message_name))) {
                    return;
                }
                if (!inputValidation.isInputEditTextFilled(textInputEditTextBranch, textInputLayoutBranch, "Enter Student Branch")) {
                    return;
                }
                if (!inputValidation.isInputEditTextFilled(textInputEditTextContactNo, textInputLayoutContactNo, getString(R.string.error_message_email))) {
                    return;
                }
                if (!inputValidation.isInputEditTextMobile(textInputEditTextContactNo, textInputLayoutContactNo, getString(R.string.error_message_email))) {
                    return;
                }
                if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, "Enter Email Id")) {
                    return;
                }
                if (!inputValidation.isInputEditTextFilled(textInputEditTextAddress, textInputLayoutAddress, "Enter Address")) {
                    return;
                }
                if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, "Enter Valid Email Id")) {
                    return;
                }
                if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
                    return;
                }
                if (!inputValidation.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword,
                        textInputLayoutConfirmPassword, getString(R.string.error_password_match))) {
                    return;
                }
                if(!inputValidation.isInputEditTextFilled(textInputEditTextId, textInputLayoutId, "Enter Id of Student"))
                {
                    return;
                }
                else
                {
                    if(mDatabaseHandler.checkStudent(textInputEditTextId.getText().toString().trim()))
                    {
                        Toast.makeText(getActivity(), "Id of student exist please try again..", Toast.LENGTH_SHORT).show();
                        return;
                    }

                }

                if (!mDatabaseHandler.checkStudent(textInputEditTextId.getText().toString().trim())) {
                    student.setStudentId(textInputEditTextId.getText().toString().trim());
                    student.setName(textInputEditTextName.getText().toString().trim());
                    student.setMobno(textInputEditTextContactNo.getText().toString().trim());
                    student.setPassword(textInputEditTextPassword.getText().toString().trim());
                    student.setAddress(textInputEditTextAddress.getText().toString().trim());
                    student.setEmail(textInputEditTextEmail.getText().toString().trim());
                    student.setBranch(textInputEditTextBranch.getText().toString().trim());

                    mDatabaseHandler.addStudent(student);

                    // Snack Bar to show success message that record saved successfully
                    Toast.makeText(getActivity(), "Student data saved Successfully", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getActivity(),MainActivityForAdmin.class));
                    getActivity().finish();


                } else {
                    // Snack Bar to show error message that record already exists
                    Toast.makeText(getActivity(), "Student already exist", Toast.LENGTH_LONG).show();
                }


                break;

        }
    }

}
