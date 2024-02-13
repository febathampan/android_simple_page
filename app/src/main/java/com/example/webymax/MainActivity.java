package com.example.webymax;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.webymax.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

import models.HostingCost;

public class MainActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener, View.OnClickListener,
        RadioGroup.OnCheckedChangeListener {

    double planCost = 0;
    double additionalCost = 0;
    double dbCost = 0;
    double stagingCost = 0;
    String webSpace;
    double webSpaceCost = 0;
    String province;
    double totalCost = 0;
    ActivityMainBinding binding;

    DatePickerDialog datePicker;
    HostingCost hCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.rdGrowBig.setChecked(false);
        binding.rdPremium.setChecked(false);
        binding.rdStartUp.setChecked(false);

        binding.acProvince.setThreshold(2);
        binding.acProvince.setInputType(InputType.TYPE_NULL);

        binding.edtDate.setInputType(InputType.TYPE_NULL);
        ArrayAdapter<CharSequence> adapterSpace = ArrayAdapter.createFromResource(this,
                R.array.webspace_array, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        binding.spnSpace.setAdapter(adapterSpace);

        ArrayAdapter<CharSequence> adapterProvince = ArrayAdapter.createFromResource(this,
                R.array.province_array, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        binding.acProvince.setAdapter(adapterProvince);

        binding.edtDate.setInputType(InputType.TYPE_NULL);
        setListeners();
    }

    private void setListeners() {
        binding.spnSpace.setOnItemSelectedListener(this);
        binding.acProvince.setOnItemClickListener(this);
        binding.chkStaging.setOnClickListener(this);
        binding.chkDb.setOnClickListener(this);
        binding.rgHostingPlan.setOnCheckedChangeListener(this);
        binding.edtDate.setOnClickListener(this);
        binding.btnSubmit.setOnClickListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        webSpace = binding.spnSpace.getItemAtPosition(position).toString();
        Toast.makeText(getApplicationContext(),webSpace, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        province = parent.getItemAtPosition(position).toString();
        Toast.makeText(getApplicationContext(),province, Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==binding.chkDb.getId()){
            if(binding.chkDb.isChecked()){
                dbCost = 13.20;
            }
            else {
                dbCost = 0;
            }
        }else if(v.getId()==R.id.chkStaging){
            if(binding.chkStaging.isChecked()){
                stagingCost = 8.90;
            }
            else {
                stagingCost = 0;
            }
        }else if(v.getId()==R.id.edtDate){
            Calendar cal = Calendar.getInstance();
            int dayOfSales = cal.get(Calendar.DAY_OF_MONTH);
            int monthOfSales = cal.get(Calendar.MONTH);
            int yearOfSales = cal.get(Calendar.YEAR);
            datePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    binding.edtDate.setText(dayOfMonth+"/"+ (month+1)+"/"+year);
                }
            },yearOfSales,monthOfSales,dayOfSales);
            datePicker.show();

        }else if(v.getId()==R.id.btnSubmit){
            hCost = new HostingCost(binding.edtName.getText().toString(), province,webSpace, binding.edtDate.getText().toString(),
                    dbCost, stagingCost, planCost);
            String result = hCost.getHostingCost();
           // Toast.makeText(getApplicationContext(), result.toString(), Toast.LENGTH_LONG).show();
            Snackbar.make(binding.ltHost, result,Snackbar.LENGTH_INDEFINITE).setAction("okay", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "Thanks", Toast.LENGTH_LONG).show();
                }
        }).show();
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if(binding.rgHostingPlan.getCheckedRadioButtonId()==R.id.rdStartUp){
                planCost = 25.38;
        }else if(binding.rgHostingPlan.getCheckedRadioButtonId()==R.id.rdGrowBig){
            planCost = 30.10;
        }else{
            planCost = 32.65;
        }
    }
}