package com.example.c868softwaredevcapstonetriciaaloufi.UserInterface;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c868softwaredevcapstonetriciaaloufi.Adapters.AssignmentPopupAdapter;
import com.example.c868softwaredevcapstonetriciaaloufi.Models.Assignments;
import com.example.c868softwaredevcapstonetriciaaloufi.R;

import java.util.List;

public class AssignDropDownMenu extends PopupWindow {
    private Context context;
    private List<Assignments> assessments;
    private RecyclerView assessPopUp;
    private AssignmentPopupAdapter assessmentPopUpAdapter;

    public AssignDropDownMenu(Context context, List<Assignments> assessments) {
        super(context);
        this.context = context;
        this.assessments = assessments;
        setupView();
    }

    public void setSelectedAssessListener (AssignmentPopupAdapter.SelectedAssessListener selectedAssessListener) {
        assessmentPopUpAdapter.setSelectedAssessListener(selectedAssessListener);
    }

    private void setupView() {
        View view = LayoutInflater.from(context).inflate(R.layout.popup_menu_item, null);
        assessPopUp = view.findViewById(R.id.popUp);
        assessPopUp.setHasFixedSize(true);
        assessPopUp.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        assessPopUp.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
        assessmentPopUpAdapter = new AssignmentPopupAdapter(assessments);
        assessPopUp.setAdapter(assessmentPopUpAdapter);
        setContentView(view);
    }
}
