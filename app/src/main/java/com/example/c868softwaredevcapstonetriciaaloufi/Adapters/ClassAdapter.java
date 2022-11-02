package com.example.c868softwaredevcapstonetriciaaloufi.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c868softwaredevcapstonetriciaaloufi.Models.Classes;
import com.example.c868softwaredevcapstonetriciaaloufi.R;
import com.example.c868softwaredevcapstonetriciaaloufi.UserInterface.AddClass;

import java.util.List;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassViewHolder> {
    public Classes getClasses(int absoluteAdapterPosition) {
        return mClasses.get(absoluteAdapterPosition);
    }

    class ClassViewHolder extends RecyclerView.ViewHolder {
        private final TextView className;
        private final TextView classDateTxt;
        private final TextView endDateTxt;
        private final TextView classStatusTxt;
        private final TextView instructorNameTxt;
        private final TextView instructorPhoneTxt;
        private final TextView instructorEmailTxt;


        public ClassViewHolder(View classView) {
            super(classView);
            className = classView.findViewById(R.id.classTitleTxt);
            classDateTxt = classView.findViewById(R.id.classDateTxt);
            endDateTxt = classView.findViewById(R.id.endDateTxt);
            classStatusTxt = classView.findViewById(R.id.classStatusTxt);
            instructorNameTxt = classView.findViewById(R.id.instructorNameTxt);
            instructorPhoneTxt = classView.findViewById(R.id.instructorPhoneTxt);
            instructorEmailTxt = classView.findViewById(R.id.instructorEmailAddressTxt);

            classView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final Classes current = mClasses.get(position);
                    Intent intent = new Intent(context, AddClass.class);
                    intent.putExtra("classId", current.getClassId());
                    intent.putExtra("className", current.getClassName());
                    intent.putExtra("startDate", current.getStartDate());
                    intent.putExtra("endDate", current.getEndDate());
                    intent.putExtra("classStatus",current.getCourseStatus());
                    intent.putExtra("instructorName",current.getInstructorName());
                    intent.putExtra("instructorPhone", current.getInstructorPhone());
                    intent.putExtra("instructorEmail",current.getInstructorEmail());
                    intent.putExtra("classNote", current.getClassNotes());
                    intent.putExtra("semesterId", current.getSemesterId());
                    context.startActivity(intent);
                }
            });
        }
    }

    private List<Classes> mClasses;
    private final Context context;
    private final LayoutInflater mInflator;

    public ClassAdapter(Context context){
        mInflator = LayoutInflater.from(context);
        this.context=context;
    }

    @NonNull
    @Override
    public ClassAdapter.ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View classView = mInflator.inflate(R.layout.class_item,parent, false);
        return new ClassAdapter.ClassViewHolder(classView);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassAdapter.ClassViewHolder holder, int position) {
        if (mClasses != null) {
            Classes current = mClasses.get(position);
            String name = current.getClassName();
            holder.className.setText(name);
            String startDate = current.getStartDate();
            String endDate = current.getEndDate();
            holder.classDateTxt.setText("Start Date: " + startDate);
            holder.endDateTxt.setText("End Date: " + endDate);
            String status = current.getCourseStatus();
            holder.classStatusTxt.setText(status);
            String instructorName = current.getInstructorName();
            holder.instructorNameTxt.setText(instructorName);
            String instructorPhone = current.getInstructorPhone();
            holder.instructorPhoneTxt.setText(instructorPhone);
            String instructorEmail = current.getInstructorEmail();
            holder.instructorEmailTxt.setText(instructorEmail);

        } else {
            holder.className.setText("No Courses Exist");
        }
    }
    public void setClasses(List<Classes> classes) {
        mClasses = classes;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mClasses != null) {
            return mClasses.size();
        } else return 0;
    }
}
