package sagar.khengat.digitallibrary.Adapters;

import android.content.Context;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.text.DecimalFormat;
import java.util.List;


;import sagar.khengat.digitallibrary.R;
import sagar.khengat.digitallibrary.model.Faculty;
import sagar.khengat.digitallibrary.util.MyAdapterListener;


public class CustomFaculty extends RecyclerView.Adapter<CustomFaculty.ViewHolder> {


    private Context context;
    public MyAdapterListener onClickListener;


    List<Faculty> productList;
    Faculty product;
//

    public CustomFaculty(List<Faculty> products, Context context, MyAdapterListener listener){
        super();
        //Getting all the superheroes
        this.productList = products;
//        this.alQuantity= q;
        this.context = context;
        this.onClickListener = listener;
    }
    public CustomFaculty(Faculty product, Context context){
        super();
        //Getting all the superheroes
        this.product = product;
//        this.alQuantity= q;
        this.context = context;
    }
    @Override
    public CustomFaculty.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_faculty, parent, false);
        CustomFaculty.ViewHolder viewHolder = new CustomFaculty.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CustomFaculty.ViewHolder holder, int position) {

        final Faculty product =  productList.get(position);







        holder.faculty_id.setText(product.getFacultyId());
        holder.faculty_name.setText(product.getName());
        holder.faculty_address.setText(product.getAddress());
        holder.faculty_email.setText(product.getEmail());
        holder.faculty_contact.setText(product.getMobno());
        holder.faculty_password.setText(product.getPassword());
    
       




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

        
        
        public TextView faculty_id;
        public TextView faculty_name;
        public TextView faculty_address;
        public TextView faculty_email;
        public TextView faculty_contact;
        public TextView faculty_password;
       

        public ViewHolder(View itemView) {
            super(itemView);

            
            faculty_id = (TextView) itemView.findViewById(R.id.faculty_id);
            faculty_name= (TextView) itemView.findViewById(R.id.faculty_name);
            faculty_address= (TextView) itemView.findViewById(R.id.faculty_address);
            faculty_email= (TextView) itemView.findViewById(R.id.faculty_email);
            faculty_contact= (TextView) itemView.findViewById(R.id.faculty_contact);
            faculty_password = (TextView) itemView.findViewById(R.id.faculty_password);
        
        }
        }
    
}