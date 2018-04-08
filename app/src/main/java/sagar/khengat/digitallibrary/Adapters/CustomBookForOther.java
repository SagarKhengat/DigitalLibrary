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
 * Created by Sagar Khengat on 08/04/2018.
 */

public class CustomBookForOther extends RecyclerView.Adapter<CustomBookForOther.ViewHolder> {


    private Context context;

    public MyAdapterListener onClickListener;
    List<Book> bookList;
    Book book;


    public CustomBookForOther(List<Book> books, Context context, MyAdapterListener listener){
        super();
        //Getting all the superheroes
        this.bookList = books;
//        this.alQuantity= q;
        this.context = context;
        this.onClickListener = listener;
    }
    public CustomBookForOther(Book book, Context context){
        super();
        //Getting all the superheroes
        this.book = book;
//        this.alQuantity= q;
        this.context = context;
    }
    @Override
    public CustomBookForOther.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_book_for_other, parent, false);
        CustomBookForOther.ViewHolder viewHolder = new CustomBookForOther.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CustomBookForOther.ViewHolder holder, int position) {

        final Book book =  bookList.get(position);






        holder.textViewName.setText(book.getBookName());
        holder.textViewid.setText(book.getBookId());

        holder.tvpublishing.setText(book.getBookPublicationHouse());
        holder.textpages.setText(Integer.toString(book.getBookPages()));
        holder.textViewCategory.setText(book.getBookCategory());
        holder.tvauther.setText(book.getBookAuther());
        holder.textSellingPrice.setText(Double.toString(book.getBookOriginalPrice()));

        if(book.isAvailable())
        {
            holder.tvstatus.setText("Available");
        }
        else
        {
            holder.tvstatus.setText("Not Available");
        }

        Picasso.with(context).load(new File(  Environment.getExternalStorageDirectory().getPath()
                + File.separator
                +"DigitalLibrary"+  File.separator
                + book.getBookCategory()+ File.separator+book.getBookName().trim()+".jpg"))
                .placeholder(R.drawable.noimage)
                .fit()
                .into(holder.imageView);


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
        public ImageView imageView;
        public TextView textViewName;
        public TextView textViewCategory;
        public TextView textViewid;
        public TextView textpages;
        public TextView textSellingPrice;
        public TextView tvauther;
        public TextView tvstatus;

        public TextView tvpublishing;

        public Button add;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.product_image);
            textViewName = (TextView) itemView.findViewById(R.id.book_name);
            textViewCategory= (TextView) itemView.findViewById(R.id.book_category);
            textViewid= (TextView) itemView.findViewById(R.id.book_id);
            textpages= (TextView) itemView.findViewById(R.id.book_pages);
            tvstatus= (TextView) itemView.findViewById(R.id.book_status);
            textSellingPrice= (TextView) itemView.findViewById(R.id.book_price);


            tvauther = (TextView) itemView.findViewById(R.id.book_author);
            tvpublishing = (TextView) itemView.findViewById(R.id.book_publishing);








        }
    }
}



