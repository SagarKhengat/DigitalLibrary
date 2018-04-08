package sagar.khengat.digitallibrary.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import sagar.khengat.digitallibrary.Constants.Config;
import sagar.khengat.digitallibrary.R;
import sagar.khengat.digitallibrary.model.Admin;
import sagar.khengat.digitallibrary.model.Faculty;
import sagar.khengat.digitallibrary.model.Student;
import sagar.khengat.digitallibrary.util.DatabaseHandler;

/**
 * Created by Sagar Khengat on 08/04/2018.
 */

public class FragmentOtherMainActivity extends Fragment implements View.OnClickListener {
private AppCompatButton appCompatButtonViewBooks;
private AppCompatButton appCompatButtonBorrowBooks;
private AppCompatButton appCompatButtonReturnBooks;
private AppCompatButton appCompatButtonFineStatus;

private AppCompatTextView textViewLinkWelcome ;
        View view;
public static FragmentManager manager;
public static FragmentTransaction ft;
        DatabaseHandler mDataBaseHandler;
        Gson gson;
        String who;
        Student student;
        Faculty faculty;

@Override
public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }

@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_for_other, container, false);
        appCompatButtonViewBooks = (AppCompatButton) view.findViewById(R.id.appCompatButtonViewBooks);
        appCompatButtonBorrowBooks = (AppCompatButton) view.findViewById(R.id.appCompatButtonBorrowBooks);
        appCompatButtonReturnBooks = (AppCompatButton) view.findViewById(R.id.appCompatButtonViewReturnBooks);
        appCompatButtonFineStatus = (AppCompatButton) view.findViewById(R.id.appCompatButtonViewFineStatus);

        textViewLinkWelcome = (AppCompatTextView)view.findViewById(R.id.textViewLinkWelcome);
        gson = new Gson();
        mDataBaseHandler = new DatabaseHandler(getActivity());
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        who = sharedPreferences.getString(Config.WHO, "");
        if (who.equals(Config.Student))
        {
                String json = sharedPreferences.getString(Config.USER, "");
                student = gson.fromJson(json,Student.class);
                textViewLinkWelcome.setText("Welcome  "+student.getName());
        }else if(who.equals(Config.Faculty))
        {
                String json = sharedPreferences.getString(Config.USER, "");
                faculty = gson.fromJson(json,Faculty.class);
                textViewLinkWelcome.setText("Welcome  "+faculty.getName());
                appCompatButtonFineStatus.setVisibility(View.GONE);
        }
        else
        {

        }




    appCompatButtonViewBooks.setOnClickListener(this);
    appCompatButtonBorrowBooks.setOnClickListener(this);
    appCompatButtonReturnBooks.setOnClickListener(this);
    appCompatButtonFineStatus.setOnClickListener(this);



        return view;
        }

@Override
public void onClick(View v) {
        switch (v.getId()) {
        case R.id.appCompatButtonViewBooks:
        setUpFragment(new FragmentViewBooks());
        break;
        case R.id.appCompatButtonBorrowBooks:
        setUpFragment(new FragmentBorrowBooks());
        break;
        case R.id.appCompatButtonViewReturnBooks:
        setUpFragment(new FragmentReturnBooks());
        break;
        case R.id.appCompatButtonViewFineStatus:
        setUpFragment(new FragmentFineStatus());
        break;


        }
        }


private void setUpFragment(Fragment fragment ) {
        Bundle bundle = new Bundle();

        manager = getActivity().getSupportFragmentManager();
        ft = manager.beginTransaction();
        ft.replace(android.R.id.tabcontent, fragment,"Fragment_tag");
        fragment.setArguments(bundle);
        ft.addToBackStack(null);
        ft.commit();

        }
        }

