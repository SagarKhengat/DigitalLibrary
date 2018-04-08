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
import java.text.DecimalFormat;
import java.util.List;

import sagar.khengat.digitallibrary.R;
import sagar.khengat.digitallibrary.model.Student;
import sagar.khengat.digitallibrary.util.MyAdapterListener;


/**
 * Created by sagar on 3/7/18.
 */

public class CustomStudent extends RecyclerView.Adapter<CustomStudent.ViewHolder> {


    private Context context;
    public MyAdapterListener onClickListener;


    List<Student> productList;
    Student product;
//

    public CustomStudent(List<Student> products, Context context, MyAdapterListener listener){
        super();
        //Getting all the superheroes
        this.productList = products;
//        this.alQuantity= q;
        this.context = context;
        this.onClickListener = listener;
    }
    public CustomStudent(Student product, Context context){
        super();
        //Getting all the superheroes
        this.product = product;
//        this.alQuantity= q;
        this.context = context;
    }
    @Override
    public CustomStudent.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_student, parent, false);
        CustomStudent.ViewHolder viewHolder = new CustomStudent.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CustomStudent.ViewHolder holder, int position) {

        final Student product =  productList.get(position);




        holder.student_id.setText(product.getStudentId());
        holder.student_name.setText(product.getName());
        holder.student_address.setText(product.getAddress());
        holder.student_email.setText(product.getEmail());
        holder.student_contact.setText(product.getMobno());
        holder.student_password.setText(product.getPassword());






    }

//    public void showCartDialog()
//    {
//
//
//    }





    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{



        public TextView student_id;
        public TextView student_name;
        public TextView student_address;
        public TextView student_email;
        public TextView student_contact;
        public TextView student_password;


        public ViewHolder(View itemView) {
            super(itemView);


            student_id = (TextView) itemView.findViewById(R.id.student_id);
            student_name= (TextView) itemView.findViewById(R.id.student_name);
            student_address= (TextView) itemView.findViewById(R.id.student_address);
            student_email= (TextView) itemView.findViewById(R.id.student_email);
            student_contact= (TextView) itemView.findViewById(R.id.student_contact);
            student_password = (TextView) itemView.findViewById(R.id.student_password);

        }
    }

}