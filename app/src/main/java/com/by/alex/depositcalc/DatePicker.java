package com.by.alex.depositcalc;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
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

        Dialog picker = new DatePickerDialog(getActivity(), this, year, month, day);
        picker.setTitle(R.string.choose_data);
        return picker;
    }

    @Override
    public void onDateSet(android.widget.DatePicker datePicker, int year, int month, int day) {

        EditText date= (EditText)getActivity().findViewById(R.id.edtBeginDate);
        date.setText(day + "-" + month + "-" + year);

    }

    public void setDateAtView(android.widget.DatePicker datePicker, int year, int month, int day, EditText v) {

        v.setText(day + "-" + month + "-" + year);

    }
}
