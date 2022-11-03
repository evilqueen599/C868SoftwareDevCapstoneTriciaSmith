package com.example.c868softwaredevcapstonetriciaaloufi.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c868softwaredevcapstonetriciaaloufi.Models.Classes;
import com.example.c868softwaredevcapstonetriciaaloufi.R;

import java.util.List;

public class ClassPopupAdapter extends RecyclerView.Adapter<ClassPopupAdapter.AddClassViewHolder> {

    private List<Classes> mClasses;

    private SelectedClassListener selectedClassListener;

    public ClassPopupAdapter(List<Classes> classes){
        super();
        this.mClasses = classes;
    }

    public void setSelectedClassListener (ClassPopupAdapter.SelectedClassListener selectedClassListener) {
        this.selectedClassListener = selectedClassListener;
    }

    @NonNull
    @Override

    public AddClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AddClassViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.semester_course_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AddClassViewHolder holder, int position) {
        final Classes classes = mClasses.get(position);
        holder.classTitleTxt.setText(classes.getClassName());
        holder.itemView.setOnClickListener (view -> {
            if (selectedClassListener != null) {
                selectedClassListener.onClassSelected(position, classes);
            }

        });
    }

    @Override
    public int getItemCount() {
        return mClasses.size();
    }

    static class AddClassViewHolder extends RecyclerView.ViewHolder {
        TextView classTitleTxt;

        public AddClassViewHolder(View itemView) {
            super(itemView);
            classTitleTxt = itemView.findViewById(R.id.classTitle);
        }
    }

    public interface SelectedClassListener {
        void onClassSelected(int position, Classes classes);
    }
}
