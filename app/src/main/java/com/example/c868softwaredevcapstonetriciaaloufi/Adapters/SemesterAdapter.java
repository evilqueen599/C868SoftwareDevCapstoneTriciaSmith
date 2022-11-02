package com.example.c868softwaredevcapstonetriciaaloufi.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c868softwaredevcapstonetriciaaloufi.Models.Semesters;
import com.example.c868softwaredevcapstonetriciaaloufi.R;
import com.example.c868softwaredevcapstonetriciaaloufi.UserInterface.AddSemester;

import java.util.List;

public class SemesterAdapter extends RecyclerView.Adapter<SemesterAdapter.SemesterViewHolder> {

    public Semesters getSemester(int absoluteAdapterPosition) {
        return mSemesters.get(absoluteAdapterPosition);
    }

    public void setSemesters(List<Semesters> semesters) {
        mSemesters = semesters;
        notifyDataSetChanged();
    }

    class SemesterViewHolder extends RecyclerView.ViewHolder  {
        private final TextView semesterTxt;
        private final TextView termTxt2;
        private final TextView termTxt3;


        public SemesterViewHolder(View semesterView) {
            super(semesterView);
            semesterTxt = semesterView.findViewById(R.id.semesterTxt);
            termTxt2 = semesterView.findViewById(R.id.termTxt2);
            termTxt3 = semesterView.findViewById(R.id.termTxt3);
            semesterView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final Semesters current = mSemesters.get(position);
                    Intent intent = new Intent(context, AddSemester.class);
                    intent.putExtra("semesterId", current.getSemesterId());
                    intent.putExtra("semesterName", current.getSemesterName());
                    intent.putExtra("startDate", current.getStartDate());
                    intent.putExtra("endDate", current.getEndDate());
                    context.startActivity(intent);
                    notifyDataSetChanged();
                }
            });
        }
    }

    private List<Semesters> mSemesters;
    private final Context context;
    private final LayoutInflater mInflator;



    public SemesterAdapter(Context context) {
        mInflator = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public SemesterAdapter.SemesterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View semesterView = mInflator.inflate(R.layout.semester_item, parent, false);
        return new SemesterViewHolder(semesterView);
    }

    @Override
    public void onBindViewHolder(@NonNull SemesterAdapter.SemesterViewHolder holder, int position) {
        if (mSemesters != null) {
            Semesters current = mSemesters.get(position);
            String name = current.getSemesterName();
            holder.semesterTxt.setText(name);
            String startDate = current.getStartDate();
            String endDate = current.getEndDate();
            holder.termTxt2.setText("Start Date: " + startDate);
            holder.termTxt3.setText("End Date: " + endDate);

        } else {
            holder.semesterTxt.setText("No Terms Exist");
        }
    }

    @Override
    public int getItemCount() {
        if (mSemesters != null) {
            return mSemesters.size();
        } else return 0;
    }
}
