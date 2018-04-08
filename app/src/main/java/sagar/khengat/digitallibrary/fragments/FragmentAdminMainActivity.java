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
import sagar.khengat.digitallibrary.util.DatabaseHandler;

/**
 * Created by Sagar Khengat on 04/03/2018.
 */

public class FragmentAdminMainActivity extends Fragment implements View.OnClickListener {
    private AppCompatButton appCompatButtonAddBook;
    private AppCompatButton appCompatButtonRegisterStudent;
    private AppCompatButton appCompatButtonRegisterFaculty;
    private AppCompatButton appCompatButtonViewStudents;
    private AppCompatButton appCompatButtonViewFaculty;
    private AppCompatButton appCompatButtonViewBook;
    private AppCompatButton appCompatButtonUpdateBook;
    private AppCompatButton appCompatButtonDeleteBook;
    private AppCompatButton appCompatButtonIssueBook;
    private AppCompatTextView textViewLinkWelcome ;
    View view;
    public static FragmentManager manager;
    public static FragmentTransaction ft;
    DatabaseHandler mDataBaseHandler;
    Gson gson;
    Admin retailer;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_for_retailer, container, false);
        appCompatButtonAddBook = (AppCompatButton) view.findViewById(R.id.appCompatButtonAddBook);
        appCompatButtonDeleteBook = (AppCompatButton) view.findViewById(R.id.appCompatButtonDeleteBook);
        appCompatButtonRegisterStudent = (AppCompatButton) view.findViewById(R.id.appCompatButtonAddStudent);
        appCompatButtonRegisterFaculty = (AppCompatButton) view.findViewById(R.id.appCompatButtonAddFaculty);
        appCompatButtonViewBook = (AppCompatButton) view.findViewById(R.id.appCompatButtonViewBook);
        appCompatButtonUpdateBook = (AppCompatButton) view.findViewById(R.id.appCompatButtonUpdateBook);
        appCompatButtonIssueBook = (AppCompatButton) view.findViewById(R.id.appCompatButtonIssueBook);
        appCompatButtonViewFaculty = (AppCompatButton) view.findViewById(R.id.appCompatButtonViewFacultyDetails);
        appCompatButtonViewStudents = (AppCompatButton) view.findViewById(R.id.appCompatButtonViewStudentDetails);
        textViewLinkWelcome = (AppCompatTextView)view.findViewById(R.id.textViewLinkWelcome);
        gson = new Gson();
        mDataBaseHandler = new DatabaseHandler(getActivity());
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String json = sharedPreferences.getString(Config.USER, "");
        retailer = gson.fromJson(json,Admin.class);

        textViewLinkWelcome.setText("Welcome  "+retailer.getName());

        appCompatButtonAddBook.setOnClickListener(this);
        appCompatButtonViewBook.setOnClickListener(this);
        appCompatButtonDeleteBook.setOnClickListener(this);
        appCompatButtonUpdateBook.setOnClickListener(this);
        appCompatButtonIssueBook.setOnClickListener(this);
        appCompatButtonRegisterStudent.setOnClickListener(this);
        appCompatButtonViewFaculty.setOnClickListener(this);
        appCompatButtonViewStudents.setOnClickListener(this);
        appCompatButtonRegisterFaculty.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appCompatButtonAddBook:
                setUpFragment(new FragmentAddBook());
                break;
            case R.id.appCompatButtonViewBook:
                setUpFragment(new FragmentViewBook());
                break;
            case R.id.appCompatButtonDeleteBook:
                setUpFragment(new FragmentDeleteBook());
                break;
            case R.id.appCompatButtonUpdateBook:
                setUpFragment(new FragmentUpdateBook());
                break;
            case R.id.appCompatButtonIssueBook:
                setUpFragment(new FragmentIssueBook());
                break;
            case R.id.appCompatButtonAddFaculty:
                setUpFragment(new FragmentAddFaculty());
                break;
            case R.id.appCompatButtonAddStudent:
                setUpFragment(new Fragment_Add_Student());
                break;
            case R.id.appCompatButtonViewFacultyDetails:
                setUpFragment(new FragmentViewFaculty());
                break;
            case R.id.appCompatButtonViewStudentDetails:
                setUpFragment(new FragmentViewStudents());
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
