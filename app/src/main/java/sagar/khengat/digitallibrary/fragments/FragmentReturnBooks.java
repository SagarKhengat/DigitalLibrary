package sagar.khengat.digitallibrary.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import sagar.khengat.digitallibrary.Adapters.SpinnerBookAdapter;
import sagar.khengat.digitallibrary.Constants.Config;
import sagar.khengat.digitallibrary.R;
import sagar.khengat.digitallibrary.activities.MainActivityForAdmin;
import sagar.khengat.digitallibrary.activities.MainActivityforOther;
import sagar.khengat.digitallibrary.model.Book;
import sagar.khengat.digitallibrary.model.Faculty;
import sagar.khengat.digitallibrary.model.Student;
import sagar.khengat.digitallibrary.util.DatabaseHandler;
import sagar.khengat.digitallibrary.util.InputValidation;

/**
 * Created by Sagar Khengat on 08/04/2018.
 */

public class FragmentReturnBooks extends Fragment implements View.OnClickListener {
    private TextInputLayout textInputLayoutProductName;

    private TextInputLayout textInputLayoutProductOriginalPrice;
    private TextInputLayout textInputLayoutProductGstPrice;
    private TextInputLayout textInputLayoutProductUnit;

    private TextInputLayout textInputLayoutProductSize;
    private TextInputLayout textInputLayoutbookpages;
    private TextInputLayout textInputLayoutpublishinghouse;

    private TextInputEditText textInputEditTextProductName;


    private TextInputEditText textInputEditTextProductOriginalPrice;
    private TextInputEditText textInputEditTextProductGstPrice;
    private TextInputEditText textInputEditTextProductUnit;
    private TextInputEditText textInputEditTextProductSize;
    private TextInputEditText textInputEditTextProductbookpages;
    private TextInputEditText textInputEditTextProductpublishinghouse;

    ImageView imageView;
    private ScrollView scrollView;
    DatabaseHandler mDatabaseHandler;
    InputValidation inputValidation ;
    private AppCompatButton appCompatButtonView;
    private AppCompatButton appCompatButtonupdate;
    Spinner spinnerProduct;
    Gson gson;

    private SpinnerBookAdapter bookAdapter;
    private Book book;
    public static FragmentManager manager;
    public static FragmentTransaction ft;
    View view;
    String who;
    Student student;
    Faculty faculty;
    SharedPreferences sharedPreferences;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_return_books, container, false);
        inputValidation = new InputValidation(getActivity());

        mDatabaseHandler = new DatabaseHandler(getActivity());

        textInputLayoutProductName = (TextInputLayout) view.findViewById(R.id.textInputLayoutProductName);

        imageView = (ImageView)view.findViewById(R.id.image);
        textInputLayoutProductOriginalPrice = (TextInputLayout) view.findViewById(R.id.textInputLayoutProductOriginalPrice);
        textInputLayoutProductGstPrice = (TextInputLayout) view.findViewById(R.id.textInputLayoutProductGstPrice);
        textInputLayoutProductUnit = (TextInputLayout) view.findViewById(R.id.textInputLayoutProductUnit);


        textInputLayoutProductSize= (TextInputLayout) view.findViewById(R.id.textInputLayoutProductQuantity);
        textInputLayoutbookpages= (TextInputLayout) view.findViewById(R.id.textInputLayoutBookPages);
        textInputLayoutpublishinghouse= (TextInputLayout) view.findViewById(R.id.textInputLayoutpublishinghouse);

        textInputEditTextProductName = (TextInputEditText) view.findViewById(R.id.textInputEditTextProductName);
        scrollView = (ScrollView)view.findViewById(R.id.scrollView);


        textInputEditTextProductOriginalPrice = (TextInputEditText) view.findViewById(R.id.textInputEditTextProductOriginalPrice);
        textInputEditTextProductGstPrice = (TextInputEditText) view.findViewById(R.id.textInputEditTextProductGstPrice);
        textInputEditTextProductUnit = (TextInputEditText) view.findViewById(R.id.textInputEditTextProductUnit);
        textInputEditTextProductSize = (TextInputEditText) view.findViewById(R.id.textInputEditTextProductQuantity);
        textInputEditTextProductbookpages = (TextInputEditText) view.findViewById(R.id.textInputEditTextBookPages);
        textInputEditTextProductpublishinghouse = (TextInputEditText) view.findViewById(R.id.textInputEditTextPublishinghouse);
        gson = new Gson();
        book = new Book();

        spinnerProduct = (Spinner) view.findViewById(R.id.spinnerProductId);
        appCompatButtonView = (AppCompatButton) view.findViewById(R.id.appCompatButtonView);
        appCompatButtonupdate = (AppCompatButton) view.findViewById(R.id.appCompatButtonDelete);
        sharedPreferences = getActivity().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        who = sharedPreferences.getString(Config.WHO, "");

        if (who.equals(Config.Student))
        {
            String json = sharedPreferences.getString(Config.USER, "");
            student = gson.fromJson(json,Student.class);
        }else if(who.equals(Config.Faculty))
        {
            String json = sharedPreferences.getString(Config.USER, "");
            faculty = gson.fromJson(json,Faculty.class);
        }
        else
        {

        }

        List<Book> categories = mDatabaseHandler.fnGetAllBook();
        if (categories.isEmpty()) {
            imageView.setVisibility(View.VISIBLE);
            spinnerProduct.setVisibility(View.GONE);
            appCompatButtonView.setVisibility(View.GONE);
        }
        else
        {
            imageView.setVisibility(View.GONE);
            spinnerProduct.setVisibility(View.VISIBLE);
            appCompatButtonView.setVisibility(View.VISIBLE);
        }
        appCompatButtonView.setOnClickListener(this);
        appCompatButtonupdate.setOnClickListener(this);


        loadSpinnerProducts();


        spinnerProduct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                book  = bookAdapter.getItem(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;

    }


    @Override
    public void onClick(View v) {
        switch ( v.getId() ) {
            case R.id.appCompatButtonDelete:

                if(textInputEditTextProductName.getText().toString().equals("")){
                    Toast.makeText(getActivity(), getResources().getText(R.string.error_text_first), Toast.LENGTH_SHORT).show();
                } else {

                    try{

                        if (who.equals(Config.Student))
                        {
                            mDatabaseHandler.returnBookofStudent(book,student);
                        }else if(who.equals(Config.Faculty))
                        {
                            mDatabaseHandler.returnBookofFaculty(book,faculty);
                        }
                        else
                        {

                        }

                        Toast.makeText(getActivity(), "Book Returned successFully", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getActivity(), MainActivityforOther.class));
                        getActivity().finish();
                    } catch (Exception e){
                        Toast.makeText(getActivity(), getResources().getText(R.string.error_generate), Toast.LENGTH_LONG).show();
                    }
                }
                break;
            case R.id.appCompatButtonView:
                scrollView.setVisibility(View.VISIBLE);
                textInputEditTextProductName.setText(book.getBookName());
                textInputEditTextProductName.setEnabled(false);
                textInputEditTextProductOriginalPrice.setText(book.getBookId());
                textInputEditTextProductOriginalPrice.setEnabled(false);
                textInputEditTextProductGstPrice.setText(book.getBookCategory());
                textInputEditTextProductGstPrice.setEnabled(false);
                textInputEditTextProductSize.setText(book.getBookAuther());
                textInputEditTextProductSize.setEnabled(false);
                textInputEditTextProductUnit.setText(Double.toString(book.getBookOriginalPrice()));
                textInputEditTextProductUnit.setEnabled(false);
                textInputEditTextProductpublishinghouse.setText(book.getBookPublicationHouse());
                textInputEditTextProductpublishinghouse.setEnabled(false);
                textInputEditTextProductbookpages.setText(Integer.toString(book.getBookPages()));
                textInputEditTextProductbookpages.setEnabled(false);
                break;
        }
    }

    private void loadSpinnerProducts() {
        // database handler
        List<Book> categories = new ArrayList<>();
        if (who.equals(Config.Student))
        {
            categories = mDatabaseHandler.getAvailableBooksofStudent(student);
        }else if(who.equals(Config.Faculty))
        {
          categories  = mDatabaseHandler.getAvailableBooksofFaculty(faculty);
        }
        else
        {

        }



        // Creating adapter for spinner
        bookAdapter = new SpinnerBookAdapter(getActivity(),
                android.R.layout.simple_spinner_item,
                categories);

        // Drop down layout style - list view with radio button


        // attaching data adapter to spinner
        spinnerProduct.setAdapter(bookAdapter);


        if (categories.isEmpty()) {
            imageView.setVisibility(View.VISIBLE);
            spinnerProduct.setVisibility(View.GONE);
            appCompatButtonView.setVisibility(View.GONE);
        }
        else
        {
            imageView.setVisibility(View.GONE);
            spinnerProduct.setVisibility(View.VISIBLE);
            appCompatButtonView.setVisibility(View.VISIBLE);
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



