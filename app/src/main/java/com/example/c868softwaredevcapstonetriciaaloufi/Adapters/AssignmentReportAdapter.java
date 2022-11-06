package com.example.c868softwaredevcapstonetriciaaloufi.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c868softwaredevcapstonetriciaaloufi.Models.Assignments;
import com.example.c868softwaredevcapstonetriciaaloufi.R;

import java.util.List;

public class AssignmentReportAdapter extends RecyclerView.Adapter<AssignmentReportAdapter.AssignmentViewHolder> {
    private static final int TYPE_HEAD = 0;
    private static final int TYPE_NORMAL = 1;

    class AssignmentViewHolder extends RecyclerView.ViewHolder {
        int view_type;

        //variables for list
        TextView assignmentTitleTxt;
        TextView assignmentTypeTxt;
        TextView dueDateTxt;
        TextView startDateTxt;

        //variables for header
        TextView assignTitleTv;
        TextView assignStartTv;
        TextView assignEndTv;
        TextView assignTypeTv;



        public AssignmentViewHolder(View classView, int viewType) {
            super(classView);
            //variables for list
            if (viewType == TYPE_NORMAL) {
                assignmentTitleTxt = classView.findViewById(R.id.assignReportName);
                startDateTxt = classView.findViewById(R.id.assignReportStartDate);
                dueDateTxt = classView.findViewById(R.id.assignReportEndDate);
                assignmentTypeTxt = classView.findViewById(R.id.assignType);
                view_type = 1;

                //variables for header
            } else if (viewType == TYPE_HEAD) {
                assignTitleTv = classView.findViewById(R.id.assignTitleTv);
                assignStartTv = classView.findViewById(R.id.assignStartTv);
                assignEndTv = classView.findViewById(R.id.assignEndTv);
                assignTypeTv = classView.findViewById(R.id.assignTypeTv);
                view_type = 0;
            }
        }
    }

    private List<Assignments> mAssignments;
    private final Context context;
    private final LayoutInflater mInflator;

    public AssignmentReportAdapter(Context context) {
        mInflator = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public AssignmentReportAdapter.AssignmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEAD) {
            View classView = mInflator.inflate(R.layout.assign_header_item, parent, false);
            return new AssignmentReportAdapter.AssignmentViewHolder(classView, viewType);
        } else if (viewType == TYPE_NORMAL) {
            View classView = mInflator.inflate(R.layout.assign_report_item, parent, false);
            return new AssignmentReportAdapter.AssignmentViewHolder(classView, viewType);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AssignmentReportAdapter.AssignmentViewHolder holder, int position) {
        if (mAssignments != null) {

            if(holder.view_type == TYPE_NORMAL) {
                Assignments current = mAssignments.get(position - 1);
                String name = current.getAssignmentTitle();
                holder.assignmentTitleTxt.setText(name);
                String startDate = current.getStartDate();
                String endDate = current.getEndDate();
                holder.startDateTxt.setText(startDate);
                holder.dueDateTxt.setText(endDate);
                String status = current.getAssignmentType();
                holder.assignmentTypeTxt.setText(status);

            } else if (holder.view_type == TYPE_HEAD) {
                holder.assignTitleTv.setText("Assignment Name");
                holder.assignStartTv.setText("Goal Start Date");
                holder.assignEndTv.setText("Due Date");
                holder.assignTypeTv.setText("Assignment Type");
            }

        }else {
            holder.assignmentTitleTxt.setText("No Assignments Exist");
        }
    }

    public void setAssignments(List<Assignments> assignments) {
        mAssignments = assignments;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mAssignments != null) {
            return mAssignments.size()+1;
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
