package com.example.c868softwaredevcapstonetriciaaloufi.UserInterface;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c868softwaredevcapstonetriciaaloufi.Adapters.ClassPopupAdapter;
import com.example.c868softwaredevcapstonetriciaaloufi.Models.Classes;
import com.example.c868softwaredevcapstonetriciaaloufi.R;

import java.util.List;

public class ClassDropDownMenu extends PopupWindow {
    private Context context;
    private List<Classes> classes;
    private RecyclerView coursePopup;
    private ClassPopupAdapter classPopUpAdapter;

    public ClassDropDownMenu(Context context, List<Classes> classes) {
        super(context);
        this.context = context;
        this.classes = classes;
        setupView();
    }

    public void setSelectedClassListener (ClassPopupAdapter.SelectedClassListener selectedClassListener) {
        classPopUpAdapter.setSelectedClassListener(selectedClassListener);
    }

    private void setupView() {
        View view = LayoutInflater.from(context).inflate(R.layout.popup_menu_item, null);
        coursePopup = view.findViewById(R.id.popUp);
        coursePopup.setHasFixedSize(true);
        coursePopup.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        coursePopup.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
        classPopUpAdapter = new ClassPopupAdapter(classes);
        coursePopup.setAdapter(classPopUpAdapter);
        setContentView(view);
    }
}
