package com.example.c868softwaredevcapstonetriciaaloufi.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c868softwaredevcapstonetriciaaloufi.Models.Assignments;
import com.example.c868softwaredevcapstonetriciaaloufi.R;
import com.example.c868softwaredevcapstonetriciaaloufi.UserInterface.AddClass;

import java.util.List;

public class ClassAssignmentAdapter extends RecyclerView.Adapter<ClassAssignmentAdapter.AddAssignmentViewHolder> {
    public Assignments getAssignment(int absoluteAdapterPosition) {
        return mAssignments.get(absoluteAdapterPosition);
    }

    class AddAssignmentViewHolder extends RecyclerView.ViewHolder {
        private final TextView assignmentTitle;



        private AddAssignmentViewHolder(View assessmentView) {
            super(assessmentView);
            assignmentTitle = assessmentView.findViewById(R.id.assignmentTitle);


            assessmentView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final Assignments current = mAssignments.get(position);
                    Intent intent = new Intent(context, AddClass.class);
                    intent.putExtra("assignmentTitle", current.getAssignmentTitle());

                }
            });
        }
    }

    private List<Assignments> mAssignments;
    private final Context context;
    private final LayoutInflater mInflator;

    public ClassAssignmentAdapter(Context context){
        mInflator = LayoutInflater.from(context);
        this.context=context;
    }

    @NonNull
    @Override
    public ClassAssignmentAdapter.AddAssignmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View assessmentView = mInflator.inflate(R.layout.class_assignment_item,parent, false);
        return new ClassAssignmentAdapter.AddAssignmentViewHolder(assessmentView);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassAssignmentAdapter.AddAssignmentViewHolder holder, int position) {
        if (mAssignments != null) {
            Assignments current = mAssignments.get(position);
            String name = current.getAssignmentTitle();
            holder.assignmentTitle.setText(name);
        } else {
            holder.assignmentTitle.setText("No Assignments Exist");
        }
    }
    public void setAssignments(List<Assignments> assessments) {
        mAssignments = assessments;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mAssignments != null) {
            return mAssignments.size();
        } else return 0;
    }
}