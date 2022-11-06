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
    private static final int TYPE_HEAD = 0;
    private static final int TYPE_NORMAL = 1;

    class ClassViewHolder extends RecyclerView.ViewHolder {
        int view_type;

        //variables for list
        TextView className;
        TextView classDateTxt;
        TextView endDateTxt;
        TextView classStatusTxt;
        TextView instructorNameTxt;
        TextView instructorPhoneTxt;
        TextView instructorEmailTxt;

        //variables for header
        TextView titleTv;
        TextView startTv;
        TextView endTv;
        TextView statusTv;
        TextView instrucTv;
        TextView phoneTv;
        TextView emailTv;


        public ClassViewHolder(View classView, int viewType) {
            super(classView);
            //variables for list
            if (viewType == TYPE_NORMAL) {
                className = classView.findViewById(R.id.classReportName);
                classDateTxt = classView.findViewById(R.id.classReportStartDate);
                endDateTxt = classView.findViewById(R.id.classReportEndDate);
                classStatusTxt = classView.findViewById(R.id.classReportClassStatus);
                instructorNameTxt = classView.findViewById(R.id.classReportInstructorName);
                instructorPhoneTxt = classView.findViewById(R.id.classReportInstructorPhone);
                instructorEmailTxt = classView.findViewById(R.id.classReportInstructorEmail);
                view_type = 1;

                //variables for header
            } else if (viewType == TYPE_HEAD) {
                titleTv = classView.findViewById(R.id.titleTv);
                startTv = classView.findViewById(R.id.startTv);
                endTv = classView.findViewById(R.id.endTv);
                statusTv = classView.findViewById(R.id.statusTv);
                instrucTv = classView.findViewById(R.id.instructTv);
                phoneTv = classView.findViewById(R.id.phoneTv);
                emailTv = classView.findViewById(R.id.emailTv);
                view_type = 0;
            }
        }
    }

    private List<Classes> mClasses;
    private final Context context;
    private final LayoutInflater mInflator;

    public ClassReportAdapter(Context context) {
        mInflator = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public ClassReportAdapter.ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEAD) {
            View classView = mInflator.inflate(R.layout.class_table_header_item, parent, false);
            return new ClassViewHolder(classView, viewType);
        } else if (viewType == TYPE_NORMAL) {
            View classView = mInflator.inflate(R.layout.class_report_item, parent, false);
            return new ClassReportAdapter.ClassViewHolder(classView, viewType);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ClassReportAdapter.ClassViewHolder holder, int position) {
        if (mClasses != null) {

            if(holder.view_type == TYPE_NORMAL) {
            Classes current = mClasses.get(position - 1);
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

        } else if (holder.view_type == TYPE_HEAD) {
                holder.titleTv.setText("Class Name");
                holder.startTv.setText("Start Date");
                holder.endTv.setText("End Date");
                holder.statusTv.setText("Class Status");
                holder.instrucTv.setText("Instructor Name");
                holder.phoneTv.setText("Instructor Phone");
                holder.emailTv.setText("Instructor Email");

            }

            }else {
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
            return mClasses.size()+1;
        } else return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEAD;
        }
        return TYPE_NORMAL;
    }
}