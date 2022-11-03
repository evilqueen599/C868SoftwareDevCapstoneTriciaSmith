package com.example.c868softwaredevcapstonetriciaaloufi.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c868softwaredevcapstonetriciaaloufi.Models.Assignments;
import com.example.c868softwaredevcapstonetriciaaloufi.R;

import java.util.List;

public class AssignmentPopupAdapter extends RecyclerView.Adapter<AssignmentPopupAdapter.AddAssessmentViewHolder> {

    private List<Assignments> mAssessments;

    private SelectedAssessListener selectedAssessListener;

    public AssignmentPopupAdapter(List<Assignments> assessments) {
        super();
        this.mAssessments = assessments;
    }

    public void setSelectedAssessListener(AssignmentPopupAdapter.SelectedAssessListener selectedAssessListener) {
        this.selectedAssessListener = selectedAssessListener;
    }

    @NonNull
    @Override
    public AddAssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AddAssessmentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.assignment_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AddAssessmentViewHolder holder, int position) {
        final Assignments assessments = mAssessments.get(position);
        holder.assignTitleTxt.setText(assessments.getAssignmentTitle());
        holder.itemView.setOnClickListener(view -> {
            if (selectedAssessListener != null) {
                selectedAssessListener.onAssessmentSelected(position, assessments);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAssessments.size();
    }

    static class AddAssessmentViewHolder extends RecyclerView.ViewHolder {
        TextView assignTitleTxt;

        public AddAssessmentViewHolder(View itemView) {
            super(itemView);
            assignTitleTxt = itemView.findViewById(R.id.assignmentTitleTxt);
        }
    }

    public interface SelectedAssessListener {
        void onAssessmentSelected(int position, Assignments assessments);
    }
}
