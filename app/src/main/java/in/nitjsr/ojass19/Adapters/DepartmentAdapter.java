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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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

    private List<Department> departmentList;

    //getting the context and ojass department list with constructor
    public DepartmentAdapter(Context myContext, List<Department> departmentList) {
        this.myContext = myContext;
        this.departmentList = departmentList;
    }

    @NonNull
    @Override
    public DepartmentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater=LayoutInflater.from(myContext);
        View view=layoutInflater.inflate(R.layout.department_card_format,null);

        return new DepartmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DepartmentViewHolder departmentViewHolder, int position) {

        //getting the contents of the department at a specified position
        Department department=departmentList.get(position);

        departmentViewHolder.departmentName.setText(department.getDepartmentName());

        switch (position) {
            case 0:
                departmentViewHolder.departmentImage.setBackgroundColor(Color.parseColor("#33313b"));
                break;
            case 1:
                departmentViewHolder.departmentImage.setBackgroundColor(Color.parseColor("#33313b"));
                break;
            case 2:
                departmentViewHolder.departmentImage.setBackgroundColor(Color.parseColor("#33313b"));
                break;
            case 3:
                departmentViewHolder.departmentImage.setBackgroundColor(Color.parseColor("#33313b"));
                break;
            case 4:
                departmentViewHolder.departmentImage.setBackgroundColor(Color.parseColor("#33313b"));
                break;
            case 5:
                departmentViewHolder.departmentImage.setBackgroundColor(Color.parseColor("#33313b"));
                break;
            case 6:
                departmentViewHolder.departmentImage.setBackgroundColor(Color.parseColor("#33313b"));
                break;
            case 7:
                departmentViewHolder.departmentImage.setBackgroundColor(Color.parseColor("#33313b"));
                break;
            case 8:
                departmentViewHolder.departmentImage.setBackgroundColor(Color.parseColor("#33313b"));
                break;
            case 9:
                departmentViewHolder.departmentImage.setBackgroundColor(Color.parseColor("#33313b"));
                break;
            case 10: departmentViewHolder.departmentImage.setBackgroundColor(Color.parseColor("#33313b"));
                break;
            case 11:
                departmentViewHolder.departmentImage.setBackgroundColor(Color.parseColor("#33313b"));
                break;
            case 12:
                departmentViewHolder.departmentImage.setBackgroundColor(Color.parseColor("#33313b"));
                break;
            case 13:
                departmentViewHolder.departmentImage.setBackgroundColor(Color.parseColor("#33313b"));
                break;
            case 14:
                departmentViewHolder.departmentImage.setBackgroundColor(Color.parseColor("#33313b"));
                break;
            case 15:
                departmentViewHolder.departmentImage.setBackgroundColor(Color.parseColor("#33313b"));
                break;
            default:
                departmentViewHolder.departmentImage.setBackgroundColor(Color.parseColor("#33313b"));
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
            itemView.setTag(getAdapterPosition());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    departmentPosition=getAdapterPosition();
                    OjassDepartment.mViewPager.setCurrentItem(departmentPosition);


                    OjassDepartment.layoutManager.scrollToPositionWithOffset(departmentPosition-1, 1);

                }
            });
        }

    }
}