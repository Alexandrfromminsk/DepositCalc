package com.by.alex.depositcalc;

import android.support.v4.app.DialogFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.GregorianCalendar;
import java.util.TimeZone;


public class MainActivity extends ActionBarActivity implements OnClickListener, OnFocusChangeListener {

    Button btnCalc, btnAdd, btnCancel;
    EditText edtCurrencyA, edtExcRateNow, edtSummAvalue, edtPercentA, edtBeginDate, edtTimeperiod;
    TextView edtDateEnd;
    SharedPreferences DefPref;

    String[] timePeriods = {"days", "months", "years"};

    public static final String APP_PREFERENCES = "calcsettings";
    public static final String CURRENCY_A = "BLR";
    public static final String BEGIN_DATE = "01.01.2015";
    public static final String END_DATE = "07.07.2017";
    public static final String EXC_RATE_NOW = "15 000";
    public static final String SUMM_A_VALUE = "1000000";
    public static final String PERCENT_A = "56";
    public static final String TIMEPERIOD = "30";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        edtCurrencyA = (EditText) findViewById(R.id.edtCurrencyA);
        edtExcRateNow = (EditText) findViewById(R.id.edtExcRateNow);
        edtSummAvalue = (EditText) findViewById(R.id.edtSummAvalue);
        edtPercentA = (EditText) findViewById(R.id.edtPercentA);
        edtBeginDate = (EditText) findViewById(R.id.edtBeginDate);
        edtTimeperiod = (EditText) findViewById(R.id.edtTimeperiod);

        edtDateEnd = (TextView) findViewById(R.id.edtEndDate);

        DefPref = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        setSavedSettings();

        Float summA = Float.parseFloat(edtSummAvalue.getText().toString());
        Float percent = Float.parseFloat(edtPercentA.getText().toString());
        Integer days = Integer.parseInt(edtTimeperiod.getText().toString());

        GregorianCalendar now = (GregorianCalendar) GregorianCalendar.getInstance( TimeZone.getDefault());

        edtBeginDate.setText(DefPref.getString(BEGIN_DATE, "01.01.2015"));

        edtBeginDate.setOnClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onPause(){
        saveSettings();

        super.onPause();
    }

    @Override
    protected void onDestroy(){
        saveSettings();

        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        DialogFragment dateDial = new DatePicker();
        dateDial.show(getSupportFragmentManager(), "datePicker");

    }

    void setSavedSettings(){
         edtCurrencyA.setText(DefPref.getString(CURRENCY_A, "Bel rubl"));

         edtDateEnd.setText(DefPref.getString(END_DATE, "01.02.2015"));
         edtExcRateNow.setText(DefPref.getString(EXC_RATE_NOW, "15000"));
         edtSummAvalue.setText(DefPref.getString(SUMM_A_VALUE, "1000000"));
         edtPercentA.setText(DefPref.getString(PERCENT_A, "50"));
         edtTimeperiod.setText(DefPref.getString(TIMEPERIOD, "30"));

    }

    void saveSettings(){

        SharedPreferences.Editor ed = DefPref.edit();
        ed.putString(TIMEPERIOD, edtTimeperiod.getText().toString());
        ed.putString(CURRENCY_A, edtCurrencyA.getText().toString());
        ed.putString(BEGIN_DATE, edtBeginDate.getText().toString());
        ed.putString(END_DATE, edtDateEnd.getText().toString());
        ed.putString(EXC_RATE_NOW, edtExcRateNow.getText().toString());
        ed.putString(SUMM_A_VALUE, edtSummAvalue.getText().toString());
        ed.putString(PERCENT_A, edtPercentA.getText().toString());

        ed.commit();

    }

    @Override
    public void onFocusChange(View view, boolean b) {

    }
}
