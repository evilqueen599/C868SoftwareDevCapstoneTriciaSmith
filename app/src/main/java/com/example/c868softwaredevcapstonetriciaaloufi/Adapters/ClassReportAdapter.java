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

public class ClassReportAdapter extends RecyclerView.Adapter<ClassReportAdapter.ClassViewHolder> {
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
            className = classView.findViewById(R.id.classReportName);
            classDateTxt = classView.findViewById(R.id.classReportStartDate);
            endDateTxt = classView.findViewById(R.id.classReportEndDate);
            classStatusTxt = classView.findViewById(R.id.classReportClassStatus);
            instructorNameTxt = classView.findViewById(R.id.classReportInstructorName);
            instructorPhoneTxt = classView.findViewById(R.id.classReportInstructorPhone);
            instructorEmailTxt = classView.findViewById(R.id.classReportInstructorEmail);
        }
    }

    private List<Classes> mClasses;
    private final Context context;
    private final LayoutInflater mInflator;

    public ClassReportAdapter(Context context){
        mInflator = LayoutInflater.from(context);
        this.context=context;
    }

    @NonNull
    @Override
    public ClassReportAdapter.ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View classView = mInflator.inflate(R.layout.class_report_item,parent, false);
        return new ClassReportAdapter.ClassViewHolder(classView);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassReportAdapter.ClassViewHolder holder, int position) {
        if (mClasses != null) {
            Classes current = mClasses.get(position);
            String name = current.getClassName();
            holder.className.setText(name);
            String startDate = current.getStartDate();
            String endDate = current.getEndDate();
            holder.classDateTxt.setText(startDate);
            holder.endDateTxt.setText(endDate);
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