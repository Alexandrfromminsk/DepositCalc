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

        date.setText(day + "-" + (month+1) + "-" + year);

/*
        GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
        cal.set(year, month, day);

        EditText timeperiod = (EditText) getActivity().findViewById(R.id.edtTimeperiod);
        int tpr = Integer.parseInt(timeperiod.getText().toString());

        Spinner spnTimeperiod = (Spinner) getActivity().findViewById(R.id.spnTimeperiod);
        int dmy = spnTimeperiod.getSelectedItemPosition();

        if (dmy==0) cal.add(Calendar.DAY_OF_MONTH, tpr);
        else if (dmy==1) cal.add(Calendar.MONTH, tpr);
        else cal.add(Calendar.YEAR,tpr);


        TextView dateEnd = (TextView) getActivity().findViewById(R.id.edtEndDate);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        dateEnd.setText(sdf.format(cal.getTime()).toString());
*/

    }


}
