package com.by.alex.depositcalc;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.EditText;

import java.util.Calendar;

//http://androiddocs.ru/datepickerdialog-vidzhet-vybora-daty/
public class DatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    @Override
    public Dialog onCreateDialog(Bundle savedInstance){
        // определяем текущую дату
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        Dialog datePicker = new DatePickerDialog(getActivity(), this, year, month, day);
        datePicker.setTitle(R.string.choose_data);
        return datePicker;
    }

    @Override
    public void onDateSet(android.widget.DatePicker datePicker, int year, int month, int day) {

        System.out.println(day + "-" + month + "-" + year);

    }

    public void setDateAtView(android.widget.DatePicker datePicker, int year, int month, int day, EditText v) {

        v.setText(day + "-" + month + "-" + year);

    }
}
