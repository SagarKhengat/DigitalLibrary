package sagar.khengat.digitallibrary.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import sagar.khengat.digitallibrary.Adapters.SpinnerBookAdapter;
import sagar.khengat.digitallibrary.R;
import sagar.khengat.digitallibrary.model.Book;
import sagar.khengat.digitallibrary.util.DatabaseHandler;
import sagar.khengat.digitallibrary.util.InputValidation;


/**
 * Created by Sagar Khengat on 04/03/2018.
 */

public class FragmentUpdateBook extends Fragment implements View.OnClickListener {
    private TextInputLayout textInputLayoutBookName;

    private TextInputLayout textInputLayoutBookOriginalPrice;
    private TextInputLayout textInputLayoutBookCategory;
    private TextInputLayout textInputLayoutBookPages;

    private TextInputLayout textInputLayoutBookAuthor;
    private TextInputLayout textInputLayoutBookPublishinghouse;

    private TextInputEditText textInputEditTextBookName;

        ImageView imageView;
    private TextInputEditText textInputEditTextBookOriginalPrice;
    private TextInputEditText textInputEditTextBookCategory;
    private TextInputEditText textInputEditTextBookAuthor;
    private TextInputEditText textInputEditTextBookPublishingHouse;
    private TextInputEditText textInputEditTextBookPages;


    private ScrollView scrollView;
    DatabaseHandler mDatabaseHandler;
    InputValidation inputValidation ;
    private AppCompatButton appCompatButtonView;
    private AppCompatButton appCompatButtonupdate;
    Spinner spinnerProduct;
    Gson gson;
    Book book;

    private SpinnerBookAdapter bookAdapter;


    View view;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_update_product, container, false);
        inputValidation = new InputValidation(getActivity());

        mDatabaseHandler = new DatabaseHandler(getActivity());

        textInputLayoutBookName = (TextInputLayout) view.findViewById(R.id.textInputLayoutBookName);
        imageView = (ImageView)view.findViewById(R.id.image);

        textInputLayoutBookCategory = (TextInputLayout) view.findViewById(R.id.textInputLayoutBookCategory);
        textInputLayoutBookAuthor = (TextInputLayout) view.findViewById(R.id.textInputLayoutBookAuthor);
        textInputLayoutBookPages = (TextInputLayout) view.findViewById(R.id.textInputLayoutBookPages);


        textInputLayoutBookPublishinghouse= (TextInputLayout) view.findViewById(R.id.textInputLayoutBookPublicationHouse);

        textInputLayoutBookOriginalPrice= (TextInputLayout) view.findViewById(R.id.textInputLayoutBookOriginalPrice);

        textInputEditTextBookOriginalPrice = (TextInputEditText) view.findViewById(R.id.textInputEditTextProductOriginalPrice);
        scrollView = (ScrollView)view.findViewById(R.id.scrollView);


        textInputEditTextBookName = (TextInputEditText) view.findViewById(R.id.textInputEditTextBookName);
        textInputEditTextBookCategory = (TextInputEditText) view.findViewById(R.id.textInputEditTextBookCategory);
        textInputEditTextBookPages = (TextInputEditText) view.findViewById(R.id.textInputEditTextBookPages);
        textInputEditTextBookAuthor = (TextInputEditText) view.findViewById(R.id.textInputEditTextBookAuthor);
        textInputEditTextBookPublishingHouse = (TextInputEditText) view.findViewById(R.id.textInputEditTextBookPublicationHouse);
        gson = new Gson();

        spinnerProduct = (Spinner) view.findViewById(R.id.spinnerProductId);
        appCompatButtonView = (AppCompatButton) view.findViewById(R.id.appCompatButtonView);
        appCompatButtonupdate = (AppCompatButton) view.findViewById(R.id.appCompatButtonUpdate);

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
        book = new Book();

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
            case R.id.appCompatButtonUpdate:
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





                if(!inputValidation.isInputEditTextFilled(textInputEditTextBookOriginalPrice, textInputLayoutBookOriginalPrice, "Enter Book's Original Price"))
                {
                    return;
                }
                else
                {
                    book.setBookOriginalPrice(Double.parseDouble(textInputEditTextBookOriginalPrice.getText().toString().trim()));
                }
                if(!inputValidation.isInputEditTextFilled(textInputEditTextBookPublishingHouse, textInputLayoutBookPublishinghouse, "Enter book Publishing house"))
                {
                    return;
                }
                else
                {
                    book.setBookPublicationHouse(textInputEditTextBookPublishingHouse.getText().toString().trim());
                }
                if(!inputValidation.isInputEditTextFilled(textInputEditTextBookAuthor, textInputLayoutBookAuthor, "Enter book Author Name"))
                {
                    return;
                }
                else
                {
                    book.setBookAuther(textInputEditTextBookAuthor.getText().toString().trim());
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





                if(textInputEditTextBookName.getText().toString().equals("")){
                    Toast.makeText(getActivity(), getResources().getText(R.string.error_text_first), Toast.LENGTH_SHORT).show();
                } else {
                                mDatabaseHandler.updateBook(book);
                    try{
                        Toast.makeText(getActivity(), "Book updated successFully", Toast.LENGTH_LONG).show();
                        scrollView.setVisibility(View.GONE);
                    } catch (Exception e){
                        Toast.makeText(getActivity(), getResources().getText(R.string.error_generate), Toast.LENGTH_LONG).show();
                    }
                }
                break;
            case R.id.appCompatButtonView:
                scrollView.setVisibility(View.VISIBLE);
                textInputEditTextBookName.setText(book.getBookName());


                textInputEditTextBookCategory.setText(book.getBookCategory());
                textInputEditTextBookAuthor.setText(book.getBookAuther());

                textInputEditTextBookOriginalPrice.setText(Double.toString(book.getBookOriginalPrice()));

                textInputEditTextBookPublishingHouse.setText(book.getBookPublicationHouse());

                textInputEditTextBookPages.setText(Integer.toString(book.getBookPages()));

                break;
        }
    }

    private void loadSpinnerProducts() {
        // database handler


        List<Book> categories = mDatabaseHandler.fnGetAllBook();

        // Creating adapter for spinner
        bookAdapter = new SpinnerBookAdapter(getActivity(),
                android.R.layout.simple_spinner_item,
                categories);

        // Drop down layout style - list view with radio button


        // attaching data adapter to spinner
        spinnerProduct.setAdapter(bookAdapter);
    }

}
