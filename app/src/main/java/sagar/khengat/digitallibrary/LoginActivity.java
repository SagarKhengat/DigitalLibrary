package sagar.khengat.digitallibrary;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import sagar.khengat.digitallibrary.Constants.Config;

import sagar.khengat.digitallibrary.activities.ChangePassword;
import sagar.khengat.digitallibrary.activities.MainActivityForAdmin;


import sagar.khengat.digitallibrary.activities.MainActivityforOther;
import sagar.khengat.digitallibrary.model.Admin;
import sagar.khengat.digitallibrary.model.Faculty;
import sagar.khengat.digitallibrary.model.Student;
import sagar.khengat.digitallibrary.util.DatabaseHandler;
import sagar.khengat.digitallibrary.util.InputValidation;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private final AppCompatActivity activity = LoginActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;

    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;

    private AppCompatButton appCompatButtonLogin;

    private AppCompatTextView textViewLinkRegister;
    private AppCompatTextView textViewLinkForgotPassword;

    private InputValidation inputValidation;
    private DatabaseHandler databaseHelper;
    private String who;
    private Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
         who = sharedPreferences.getString(Config.WHO, "");
        gson = new Gson();
        initViews();
        initListeners();
        initObjects();
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {

        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);

        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);

        appCompatButtonLogin = (AppCompatButton) findViewById(R.id.appCompatButtonLogin);

        textViewLinkRegister = (AppCompatTextView) findViewById(R.id.textViewLinkRegister);
        textViewLinkForgotPassword = (AppCompatTextView) findViewById(R.id.textViewLinkForgotPassword);

    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonLogin.setOnClickListener(this);
        textViewLinkRegister.setOnClickListener(this);
        textViewLinkForgotPassword.setOnClickListener(this);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        databaseHelper = new DatabaseHandler(activity);
        inputValidation = new InputValidation(activity);
        if(!who.equals("Admin"))
        {
            textViewLinkRegister.setVisibility(View.GONE);
            textInputEditTextEmail.setHint("User Id");
        }
        else
        {
            textViewLinkRegister.setVisibility(View.VISIBLE);
            textInputEditTextEmail.setHint("User Name");
        }
    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appCompatButtonLogin:
                if(who.equals(Config.Admin)) {
                    verifyFromSQLiteAdmin();
                }
                else if(who.equals(Config.Faculty))
                {
                    verifyFromSQLiteFaculty();
                }
                else if(who.equals(Config.Student))
                {
                    verifyFromSQLiteStudent();
                }
                else
                {
                    Toast.makeText(activity, "something went wrong..please try again", Toast.LENGTH_SHORT).show();
                    Intent intentPreLogin = new Intent(activity, PreLoginActivity.class);
                    startActivity(intentPreLogin);
                    finish();
                }

                break;
            case R.id.textViewLinkRegister:
                // Navigate to RegisterActivity
                    Intent intentRegister = new Intent(activity, Register.class);
                    startActivity(intentRegister);
                    finish();
                break;
            case R.id.textViewLinkForgotPassword:
                if(who.equals(Config.Admin)) {
                    Intent intent = new Intent(activity, ChangePassword.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(activity, "Contact Admin for update Password", Toast.LENGTH_SHORT).show();
                }
        }
    }

    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     */
    private void verifyFromSQLiteAdmin() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, "Enter Username")) {
            return;
        }

        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, "Enter password")) {
            return;
        }

        if (databaseHelper.checkAdmin(textInputEditTextEmail.getText().toString().trim()
                , textInputEditTextPassword.getText().toString().trim())) {
            //Creating a shared preference
            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                      //Creating editor to store values to shared preferences
                        SharedPreferences.Editor editor = sharedPreferences.edit();
            Admin customer = databaseHelper.getAdmin(textInputEditTextEmail.getText().toString().trim()
                    , textInputEditTextPassword.getText().toString().trim());
            String json = gson.toJson(customer);
            editor.putString(Config.USER,json);
                        //Adding values to editor
                        editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
//                        editor.putString(Config.NAME, userFirstName);
//
//                        editor.putInt(Config.USERTOKEN,userToken);

                        //Saving values to editor
                        editor.apply();
            Intent accountsIntent = new Intent(activity, MainActivityForAdmin.class);
            accountsIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
            emptyInputEditText();
            startActivity(accountsIntent);
            finish();
            Toast.makeText(activity, "Login Success", Toast.LENGTH_SHORT).show();


        } else {
            // Snack Bar to show success message that record is wrong
            if (databaseHelper.checkAdmin((textInputEditTextEmail.getText().toString().trim()))) {
                Toast.makeText(activity, "wrong password..please try again..", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(activity, "No username found, please create account", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void verifyFromSQLiteFaculty() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }

        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_email))) {
            return;
        }

        if (databaseHelper.checkFaculty(textInputEditTextEmail.getText().toString().trim()
                , textInputEditTextPassword.getText().toString().trim())) {


            Faculty retailer = databaseHelper.getFaculty(textInputEditTextEmail.getText().toString().trim()
                    , textInputEditTextPassword.getText().toString().trim());
            String json = gson.toJson(retailer);

            //Creating a shared preference
            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
            //Creating editor to store values to shared preferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(Config.NAME,textInputEditTextEmail.getText().toString().trim());
            //Adding values to editor
            editor.putString(Config.USER,json);
            editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
//                        editor.putString(Config.NAME, userFirstName);
//
//                        editor.putInt(Config.USERTOKEN,userToken);

            //Saving values to editor
            editor.apply();
            Intent accountsIntent = new Intent(activity, MainActivityforOther.class);
            accountsIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
            emptyInputEditText();
            startActivity(accountsIntent);
            finish();
            Toast.makeText(activity, "Login Success", Toast.LENGTH_SHORT).show();


        } else {
            // Snack Bar to show success message that record is wrong
            if (databaseHelper.checkFaculty((textInputEditTextEmail.getText().toString().trim()))) {
                Toast.makeText(activity, "wrong password..please try again..", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(activity, "No Username found, please contact Admin", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void verifyFromSQLiteStudent() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }

        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_email))) {
            return;
        }

        if (databaseHelper.checkStudent(textInputEditTextEmail.getText().toString().trim()
                , textInputEditTextPassword.getText().toString().trim())) {


            Student retailer = databaseHelper.getStudent(textInputEditTextEmail.getText().toString().trim()
                    , textInputEditTextPassword.getText().toString().trim());
            String json = gson.toJson(retailer);

            //Creating a shared preference
            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
            //Creating editor to store values to shared preferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(Config.NAME,textInputEditTextEmail.getText().toString().trim());
            //Adding values to editor
            editor.putString(Config.USER,json);
            editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
//                        editor.putString(Config.NAME, userFirstName);
//
//                        editor.putInt(Config.USERTOKEN,userToken);

            //Saving values to editor
            editor.apply();
            Intent accountsIntent = new Intent(activity, MainActivityforOther.class);
            accountsIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
            emptyInputEditText();
            startActivity(accountsIntent);
            finish();
            Toast.makeText(activity, "Login Success", Toast.LENGTH_SHORT).show();


        } else {
            // Snack Bar to show success message that record is wrong
            if (databaseHelper.checkStudent((textInputEditTextEmail.getText().toString().trim()))) {
                Toast.makeText(activity, "wrong password..please try again..", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(activity, "No Username found, please contact Admin", Toast.LENGTH_SHORT).show();
            }
        }
    }
    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
    }
}


