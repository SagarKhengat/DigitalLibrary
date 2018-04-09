package sagar.khengat.digitallibrary.Adapters;

import android.content.Context;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import sagar.khengat.digitallibrary.R;
import sagar.khengat.digitallibrary.model.Book;
import sagar.khengat.digitallibrary.util.MyAdapterListener;

/**
 * Created by sagar on 4/9/18.
 */

public class CustomIssuedBooks extends RecyclerView.Adapter<CustomIssuedBooks.ViewHolder> {


    private Context context;

    public MyAdapterListener onClickListener;
    List<Book> bookList;
    Book book;


    public CustomIssuedBooks(List<Book> books, Context context, MyAdapterListener listener){
        super();
        //Getting all the superheroes
        this.bookList = books;
//        this.alQuantity= q;
        this.context = context;
        this.onClickListener = listener;
    }
    public CustomIssuedBooks(Book book, Context context){
        super();
        //Getting all the superheroes
        this.book = book;
//        this.alQuantity= q;
        this.context = context;
    }
    @Override
    public CustomIssuedBooks.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_issued_book, parent, false);
        CustomIssuedBooks.ViewHolder viewHolder = new CustomIssuedBooks.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CustomIssuedBooks.ViewHolder holder, int position) {

        final Book book =  bookList.get(position);

        if(book.getBookFaculty()== null)
        {
            holder.textViewName.setText(book.getBookName());
            holder.textViewid.setText(book.getBookId());
            holder.textBorrowertype.setText("Student");
            holder.textBorrowerId.setText(book.getBookStudent().getStudentId());
            holder.textborrowerContact.setText(book.getBookStudent().getMobno());
            holder.textViewBorrowerName.setText(book.getBookStudent().getName());
        }

        else if(book.getBookStudent() == null)

        {
            holder.textViewName.setText(book.getBookName());
            holder.textViewid.setText(book.getBookId());
            holder.textBorrowertype.setText("Faculty");
            holder.textBorrowerId.setText(book.getBookFaculty().getFacultyId());
            holder.textborrowerContact.setText(book.getBookFaculty().getMobno());
            holder.textViewBorrowerName.setText(book.getBookFaculty().getName());
        }


    }

//    public void showCartDialog()
//    {
//
//
//    }





    @Override
    public int getItemCount() {
        return bookList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewName;
        public TextView textBorrowertype;
        public TextView textViewBorrowerName;
        public TextView textViewid;
        public TextView textborrowerContact;
        public TextView textBorrowerId;
        public ImageView issue;
        public ImageView reject;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewName = (TextView) itemView.findViewById(R.id.book_name);
            textBorrowertype = (TextView) itemView.findViewById(R.id.borrower_type);
            textViewBorrowerName= (TextView) itemView.findViewById(R.id.book_borrower);
            textViewid= (TextView) itemView.findViewById(R.id.book_id);
            textborrowerContact= (TextView) itemView.findViewById(R.id.borrower_contact);

            textBorrowerId= (TextView) itemView.findViewById(R.id.borrower_id);
            issue = (ImageView)itemView.findViewById(R.id.issue);
            reject = (ImageView)itemView.findViewById(R.id.reject);




            issue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.issueOnClick(v, getAdapterPosition());
                }
            });
            reject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.rejectOnClick(v, getAdapterPosition());
                }
            });






        }
    }
}




