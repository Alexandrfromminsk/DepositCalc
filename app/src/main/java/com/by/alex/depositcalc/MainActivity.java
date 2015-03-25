package com.by.alex.depositcalc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class MainActivity extends ActionBarActivity implements OnClickListener, OnFocusChangeListener {

    Button btnCalc, btnAdd, btnCancel;
    EditText edtCurrencyA, edtExcRateNow, edtSummAvalue, edtPercentA, edtBeginDate, edtTimeperiod;
    TextView edtDateEnd, txtProfitAValue, txtGrowValue, txtFullSummValue;
    CheckBox chbAddPercentOn;
    Spinner spnTimeperiod, spnCapital;
    SharedPreferences DefPref;

    String[] timePeriods = {"days", "months", "years"};
    String[] capitals = {"at the end", "daily", "montly", "once per kvartal", "every year",};

    public static final String APP_PREFERENCES = "calcsettings";
    public static final String CURRENCY_A = "BLR";
    public static final String BEGIN_DATE = "01.01.2015";
    public static final String END_DATE = "07.07.2017";
    public static final String EXC_RATE_NOW = "15 000";
    public static final String SUMM_A_VALUE = "1000000";
    public static final String PERCENT_A = "56";
    public static final String TIMEPERIOD = "30";
    public static final String ADD_PERCENT = "if_add_precent";




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

        chbAddPercentOn = (CheckBox) findViewById(R.id.chbAddPercentOn);

        edtDateEnd = (TextView) findViewById(R.id.edtEndDate);
        txtProfitAValue = (TextView) findViewById(R.id.txtProfitAValue);
        txtGrowValue = (TextView)findViewById(R.id.txtGrowValue);
        txtFullSummValue =(TextView)findViewById(R.id.txtFullSummValue);

        spnTimeperiod = (Spinner) findViewById(R.id.spnTimeperiod);
        spnCapital = (Spinner) findViewById(R.id.spnCapital);

        ArrayAdapter<String> timeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, timePeriods);
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spnTimeperiod.setAdapter(timeAdapter);
        spnTimeperiod.setSelection(0);

        ArrayAdapter<String> capitalAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, capitals);
        capitalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spnCapital.setAdapter(capitalAdapter);
        spnCapital.setSelection(0);
        spnCapital.setOnItemSelectedListener(new OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view,
                                                       int position, long id) {
                                calc_it();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });

        DefPref = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        setSavedSettings();

        Float summA = Float.parseFloat(edtSummAvalue.getText().toString());
        Float percent = Float.parseFloat(edtPercentA.getText().toString());
        Integer days = Integer.parseInt(edtTimeperiod.getText().toString());

        //GregorianCalendar now = (GregorianCalendar) GregorianCalendar.getInstance( TimeZone.getDefault());

        //edtBeginDate.setText(DefPref.getString(BEGIN_DATE, "01.01.2015"));

        edtBeginDate.setOnClickListener(this);
        edtTimeperiod.setOnClickListener(this);

        spnTimeperiod.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                setEndDate();
                calc_it();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        edtSummAvalue.setOnFocusChangeListener(this);
        edtTimeperiod.setOnFocusChangeListener(this);
        edtPercentA.setOnFocusChangeListener(this);

        chbAddPercentOn.setOnClickListener(this);
        edtSummAvalue.setOnClickListener(this);

        calc_it();

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

        switch (view.getId()) {
            case R.id.edtBeginDate:
                DialogFragment dateDial = new DatePicker();
                dateDial.show(getSupportFragmentManager(), "datePicker");
                setEndDate();
                break;

            case R.id.btnAdd:
                Intent compareIntent = new Intent(MainActivity.this, ComparisonActivity.class);
                startActivity(compareIntent);
                break;
            default:
                calc_it();
        }

    }

    void setSavedSettings(){
        edtCurrencyA.setText(DefPref.getString(CURRENCY_A, "Bel rubl"));

        edtDateEnd.setText(DefPref.getString(END_DATE, "01.02.2015"));
        edtExcRateNow.setText(DefPref.getString(EXC_RATE_NOW, "15000"));
        edtSummAvalue.setText(DefPref.getString(SUMM_A_VALUE, "1000000"));
        edtPercentA.setText(DefPref.getString(PERCENT_A, "50"));
        edtTimeperiod.setText(DefPref.getString(TIMEPERIOD, "30"));
        if (DefPref.contains(ADD_PERCENT))
            chbAddPercentOn.setChecked(DefPref.getBoolean(ADD_PERCENT, false));

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
        ed.putBoolean(ADD_PERCENT, chbAddPercentOn.isChecked());

        ed.commit();

    }
//not used
    public void getAllData() {
        Float s = Float.parseFloat(edtSummAvalue.getText().toString());
        Float pr = Float.parseFloat(edtPercentA.getText().toString());
        Integer d = Integer.parseInt(edtTimeperiod.getText().toString());
        int dmy = spnTimeperiod.getSelectedItemPosition();
        if (dmy==1) d *= 30;
        if (dmy==2) d *= 365;
        boolean add = chbAddPercentOn.isChecked();

    }

    @Override
    public void onFocusChange(View view, boolean b) {

        calc_it();

        switch (view.getId()) {
            case R.id.edtTimeperiod:
                // Timeperiod field
                setEndDate();
                break;
        }

    }

    public void calc_it(){
        if (ifFieldsWithData()) {
            Float s = Float.parseFloat(edtSummAvalue.getText().toString());
            Float pr = Float.parseFloat(edtPercentA.getText().toString());
            Integer d = Integer.parseInt(edtTimeperiod.getText().toString());
            int dmy = spnTimeperiod.getSelectedItemPosition();
            if (dmy==1) d*=30;
            if (dmy==2) d*=365;
            int cap = spnCapital.getSelectedItemPosition();
            Float[] profit = Calculator.calcProfit(s, pr, d,cap);

            txtGrowValue.setText(profit[Calculator.PERCENT].toString());
            txtProfitAValue.setText(profit[Calculator.PROFIT].toString());
            txtFullSummValue.setText(profit[Calculator.FULLSUMM].toString());
        }

    }


    public boolean ifFieldsWithData(){
        return (edtExcRateNow.getText().length()>0)&(edtSummAvalue.getText().length()>0)&(edtTimeperiod.getText().length()>0);

    }

    public  void setEndDate(){

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
        try {
            Date date = sdf.parse(edtBeginDate.getText().toString());
            cal.setTime(date);



        int tpr = Integer.parseInt(edtTimeperiod.getText().toString());

        //Spinner spnTimeperiod = (Spinner) findViewById(R.id.spnTimeperiod);
        int dmy = spnTimeperiod.getSelectedItemPosition();

        if (dmy==0) cal.add(Calendar.DAY_OF_MONTH, tpr);
        else if (dmy==1) cal.add(Calendar.MONTH, tpr);
        else cal.add(Calendar.YEAR,tpr);


        edtDateEnd.setText(sdf.format(cal.getTime()).toString());

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
