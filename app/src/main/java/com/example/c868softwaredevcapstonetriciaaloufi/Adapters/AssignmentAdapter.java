package com.example.c868softwaredevcapstonetriciaaloufi.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c868softwaredevcapstonetriciaaloufi.Models.Assignments;
import com.example.c868softwaredevcapstonetriciaaloufi.R;
import com.example.c868softwaredevcapstonetriciaaloufi.UserInterface.AddAssignment;

import java.util.ArrayList;
import java.util.List;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.AssignmentViewHolder> {
    public void setAssignments(List<Assignments> assignments) {
        mAssignments = assignments;
        notifyDataSetChanged();
    }



    class AssignmentViewHolder extends RecyclerView.ViewHolder {
        private final TextView assignmentTitleTxt;
        private final TextView assignmentTypeTxt;
        private final TextView dueDateTxt;
        private final TextView startDateTxt;

        public AssignmentViewHolder(View assignmentView) {
            super(assignmentView);
            assignmentTitleTxt = assignmentView.findViewById(R.id.assignmentTitleTxt);
            assignmentTypeTxt = assignmentView.findViewById(R.id.assignmentTypeTxt);
            startDateTxt = assignmentView.findViewById(R.id.startTxt);
            dueDateTxt = assignmentView.findViewById(R.id.dueDateTxt);


            assignmentView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final Assignments current = mAssignments.get(position);
                    Intent intent = new Intent(context, AddAssignment.class);
                    intent.putExtra("assignmentId", current.getAssignmentId());
                    intent.putExtra("assignmentTitle", current.getAssignmentTitle());
                    intent.putExtra("assignmentType", current.getAssignmentType());
                    intent.putExtra("startDate", current.getStartDate());
                    intent.putExtra("endDate", current.getEndDate());
                    intent.putExtra("classId", current.getClassId());
                    context.startActivity(intent);
                }
            });
        }
    }

    private List<Assignments> mAssignments;
    private  List<Assignments> search;
    private final Context context;
    private final LayoutInflater mInflator;

    public AssignmentAdapter(Context context){
        mInflator = LayoutInflater.from(context);
        this.context=context;
    }

    @NonNull
    @Override
    public AssignmentAdapter.AssignmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View assignmentView = mInflator.inflate(R.layout.assignment_item,parent, false);
        return new AssignmentViewHolder(assignmentView);
    }

    @Override
    public void onBindViewHolder(@NonNull AssignmentAdapter.AssignmentViewHolder holder, int position) {
        if (mAssignments != null) {
            Assignments current = mAssignments.get(position);
            String name = current.getAssignmentTitle();
            holder.assignmentTitleTxt.setText(name);
            String type =current.getAssignmentType();
            holder.assignmentTypeTxt.setText(type);
            String start = current.getStartDate();
            holder.startDateTxt.setText("Start Date: " +start);
            String dueDate = current.getEndDate();
            holder.dueDateTxt.setText("Due Date: " + dueDate);
        } else {
            holder.assignmentTitleTxt.setText("No Assessments Exist");
        }
    }

    @Override
    public int getItemCount() {
        if (mAssignments != null) {
            return mAssignments.size();
        } else return 0;
    }
}
