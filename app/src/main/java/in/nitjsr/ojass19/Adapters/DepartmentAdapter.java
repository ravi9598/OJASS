package in.nitjsr.ojass19.Adapters;

//this is adapter class for model class "Department"

import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import in.nitjsr.ojass19.Activity.OjassDepartment;
import in.nitjsr.ojass19.Modals.Department;
import in.nitjsr.ojass19.R;

public class DepartmentAdapter extends RecyclerView.Adapter<DepartmentAdapter.DepartmentViewHolder> {

    //this context will be used to inflate the layout
    private Context myContext;
    public static int departmentPosition=0;

    //creating list to store all ojass departments
    private List<Department> departmentList;

    //getting the context and ojass department list with constructor
    public DepartmentAdapter(Context myContext, List<Department> departmentList) {
        this.myContext = myContext;
        this.departmentList = departmentList;
    }

    @NonNull
    @Override
    public DepartmentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        //inflating and returning our view holder
        LayoutInflater layoutInflater=LayoutInflater.from(myContext);
        View view=layoutInflater.inflate(R.layout.department_card_format,null);

        return new DepartmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DepartmentViewHolder departmentViewHolder, int position) {

        //getting the contents of the department at a specified position
        Department department=departmentList.get(position);

        //binding the data with view holder
        departmentViewHolder.departmentName.setText(department.getDepartmentName());

        //loading image to Cardview from URI using glide
     /*   Glide.with(myContext)
                .load(department.getDepartmentImage())
                .apply(new RequestOptions()
                        .placeholder(R.mipmap.ic_launcher)
                        .fitCenter())
                .into(departmentViewHolder.departmentImage);*/
        switch (position) {
            case 0:
                departmentViewHolder.departmentImage.setBackgroundColor(Color.parseColor("#1C1259"));
                break;
            case 1:
                departmentViewHolder.departmentImage.setBackgroundColor(Color.parseColor("#33313b"));
                break;
            case 2:
                departmentViewHolder.departmentImage.setBackgroundColor(Color.parseColor("#000000"));
                break;
            case 3:

                departmentViewHolder.departmentImage.setBackgroundColor(Color.parseColor("#3A9679"));
                break;
            case 4:
                departmentViewHolder.departmentImage.setBackgroundColor(Color.parseColor("#C40B13"));
                break;

            case 5:
                departmentViewHolder.departmentImage.setBackgroundColor(Color.parseColor("#FCD307"));
                break;
//
            case 6:
                departmentViewHolder.departmentImage.setBackgroundColor(Color.parseColor("#5D3A3A"));
                break;

            case 7:
                departmentViewHolder.departmentImage.setBackgroundColor(Color.parseColor("#309286"));
                break;

            case 8:
                departmentViewHolder.departmentImage.setBackgroundColor(Color.parseColor("#4C0045"));
                break;

            case 9:

                departmentViewHolder.departmentImage.setBackgroundColor(Color.parseColor("#10316B"));
                break;
            case 10: departmentViewHolder.departmentImage.setBackgroundColor(Color.parseColor("#0B8457"));
                break;

            case 11:
                departmentViewHolder.departmentImage.setBackgroundColor(Color.parseColor("#233142"));
                break;

            case 12:
                departmentViewHolder.departmentImage.setBackgroundColor(Color.parseColor("#831212"));
                break;
            case 13:
                departmentViewHolder.departmentImage.setBackgroundColor(Color.parseColor("#012528"));
                break;
            case 14:
                departmentViewHolder.departmentImage.setBackgroundColor(Color.parseColor("#EA168E"));
                break;
            case 15:
                departmentViewHolder.departmentImage.setBackgroundColor(Color.parseColor("#FF8B00"));
                break;
            default:

                departmentViewHolder.departmentImage.setBackgroundColor(Color.parseColor("#000000"));
                break;

        }   }

    @Override
    public int getItemCount() {
        return departmentList.size();
    }


    public static class DepartmentViewHolder extends RecyclerView.ViewHolder {

        ImageView departmentImage;
        TextView departmentName;

        public DepartmentViewHolder(@NonNull final View itemView) {
            super(itemView);
            departmentImage=itemView.findViewById(R.id.departmentImageView);
            departmentName=itemView.findViewById(R.id.departmentTextView);

            //to get the position of recycler viw
            itemView.setTag(getAdapterPosition());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    departmentPosition=getAdapterPosition();
                    OjassDepartment.mViewPager.setCurrentItem(departmentPosition);

                    // scrolling the cardview at first position
                    OjassDepartment.layoutManager.scrollToPositionWithOffset(departmentPosition-1, 1);
                  /*  new CountDownTimer(200, 5) {

                        public void onTick(long millisUntilFinished) {
                            departmentName.setTextSize(17);

                        }

                        public void onFinish() {
                            departmentName.setTextSize(15);

                        }
                    }.start();*/


                }
            });
        }
    }
}