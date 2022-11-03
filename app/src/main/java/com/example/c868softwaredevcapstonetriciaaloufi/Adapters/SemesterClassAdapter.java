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
import com.example.c868softwaredevcapstonetriciaaloufi.UserInterface.AddSemester;

import java.util.List;

public class SemesterClassAdapter extends RecyclerView.Adapter<SemesterClassAdapter.ClassViewHolder> {

    public Classes getClass(int absoluteAdapterPosition) {
        return mClasses.get(absoluteAdapterPosition);
    }

    class ClassViewHolder extends RecyclerView.ViewHolder {


        private final TextView classTitle;


        private ClassViewHolder(View classView) {
            super(classView);
            classTitle = classView.findViewById(R.id.classTitle);

            classView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final Classes current = mClasses.get(position);
                    Intent intent = new Intent(context, AddSemester.class);
                    intent.putExtra("className", current.getClassName());
                }
            });
        }
    }

    private List<Classes> mClasses;
    private final Context context;
    private final LayoutInflater mInflator;

    public SemesterClassAdapter(Context context){
        mInflator = LayoutInflater.from(context);
        this.context=context;
    }

    @NonNull
    @Override
    public SemesterClassAdapter.ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View classView = mInflator.inflate(R.layout.semester_course_item,parent, false);
        return new SemesterClassAdapter.ClassViewHolder(classView);
    }

    @Override
    public void onBindViewHolder(@NonNull SemesterClassAdapter.ClassViewHolder holder, int position) {
        if (mClasses != null) {
            Classes current = mClasses.get(position);
            String name = current.getClassName();
            holder.classTitle.setText(name);
        } else {
            holder.classTitle.setText("No Courses Exist");
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
