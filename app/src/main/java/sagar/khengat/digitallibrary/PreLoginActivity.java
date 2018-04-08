package sagar.khengat.digitallibrary;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import sagar.khengat.digitallibrary.Constants.Config;
import sagar.khengat.digitallibrary.util.DatabaseHandler;


public class PreLoginActivity extends AppCompatActivity {
    private AppCompatButton appCompatButtonLoginAdmin;
    private AppCompatButton appCompatButtonLoginFaculty;
    private AppCompatButton appCompatButtonLoginStudent;
    private final AppCompatActivity activity = PreLoginActivity.this;
    private DatabaseHandler databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_login);
        appCompatButtonLoginAdmin = (AppCompatButton) findViewById(R.id.appCompatButtonLoginAdmin);
        appCompatButtonLoginStudent = (AppCompatButton) findViewById(R.id.appCompatButtonLoginStudent);
        appCompatButtonLoginFaculty = (AppCompatButton) findViewById(R.id.appCompatButtonLoginFaculty);
        databaseHelper = new DatabaseHandler(activity);
        appCompatButtonLoginAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(Config.WHO, Config.Admin);
                editor.apply();

                startActivity(new Intent(PreLoginActivity.this,LoginActivity.class));
                finish();
            }
        });
        appCompatButtonLoginFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(Config.WHO, Config.Faculty);
                editor.apply();

                startActivity(new Intent(PreLoginActivity.this,LoginActivity.class));
                finish();
            }
        });

        appCompatButtonLoginStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(Config.WHO, Config.Student);
                editor.apply();

                startActivity(new Intent(PreLoginActivity.this,LoginActivity.class));
                finish();
            }
        });
    }
}
