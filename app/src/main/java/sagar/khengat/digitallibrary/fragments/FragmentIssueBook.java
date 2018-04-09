package sagar.khengat.digitallibrary.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import sagar.khengat.digitallibrary.Adapters.CustomBookForOther;
import sagar.khengat.digitallibrary.Adapters.CustomIssuedBooks;
import sagar.khengat.digitallibrary.R;
import sagar.khengat.digitallibrary.model.Book;
import sagar.khengat.digitallibrary.util.DatabaseHandler;
import sagar.khengat.digitallibrary.util.MyAdapterListener;

/**
 * Created by Sagar Khengat on 08/04/2018.
 */

public class FragmentIssueBook extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Book> bookList;
    private RecyclerView.Adapter adapter;
    private DatabaseHandler mDatabaseHandler;
    View view;
    Gson gson;

    ImageView imageView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_issue_book, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.product_recycler);
        layoutManager = new LinearLayoutManager(getActivity());
        imageView = (ImageView)view.findViewById(R.id.image);
        recyclerView.setLayoutManager(layoutManager);
        bookList = new ArrayList<>();
        mDatabaseHandler = new DatabaseHandler(getActivity());
        gson = new Gson();
        bookList = mDatabaseHandler.fnGetAllBooksForIssue();
        if (bookList.isEmpty()) {
            imageView.setVisibility(View.VISIBLE);
        }
        else
        {
            imageView.setVisibility(View.GONE);
        }
        adapter = new CustomIssuedBooks(bookList, getActivity(),new MyAdapterListener()
        {


            @Override
            public void issueOnClick(View v, int position) {
                Book book = bookList.get(position);
                Toast.makeText(getActivity(), "Book Issued successfully", Toast.LENGTH_SHORT).show();
                bookList.remove(book);
            }

            @Override
            public void rejectOnClick(View v, int position) {
                Book book = bookList.get(position);
                mDatabaseHandler.rejectBook(book);
                bookList.remove(book);
            }


        });
        recyclerView.setAdapter(adapter);

        return  view;
    }

}

