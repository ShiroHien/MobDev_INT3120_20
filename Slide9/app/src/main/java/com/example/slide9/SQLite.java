package com.example.slide9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.week9contentprovider.R;

public class SQLite extends AppCompatActivity {

    Button btn_add, btn_viewAll;
    EditText et_name, et_age;
    Switch sw_activeCustomer;
    ListView lv_customerList;

    ArrayAdapter customerArrayAdapter;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        btn_add = findViewById(R.id.btn_add);
        btn_viewAll = findViewById(R.id.btn_viewAll);
        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        sw_activeCustomer = findViewById(R.id.sw_active);
        lv_customerList = findViewById(R.id.lv_customerList);

        dataBaseHelper = new DataBaseHelper(SQLite.this);
        ShowCustomersOnListView(dataBaseHelper);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomerModel customerModel;
                try {
                    customerModel = new CustomerModel(-1, et_name.getText().toString(), Integer.parseInt(et_age.getText().toString()), sw_activeCustomer.isChecked());
                    Toast.makeText(SQLite.this, customerModel.toString(), Toast.LENGTH_SHORT).show();
                }
                catch (Exception e) {
                    Toast.makeText(SQLite.this, "Error creating customer", Toast.LENGTH_SHORT).show();
                    customerModel = new CustomerModel(-1, "error", 0, false);
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(SQLite.this);
                boolean success = dataBaseHelper.addOne(customerModel);
                Toast.makeText(SQLite.this, "Success= " + success, Toast.LENGTH_SHORT).show();

                ShowCustomersOnListView(dataBaseHelper);
            }
        });

        btn_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(SQLite.this);

                ShowCustomersOnListView(dataBaseHelper);
            }
        });

        lv_customerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CustomerModel clickedCustomer = (CustomerModel) parent.getItemAtPosition(position);
                dataBaseHelper.deleteOne(clickedCustomer);
                ShowCustomersOnListView(dataBaseHelper);
                Toast.makeText(SQLite.this, "Deleted " + clickedCustomer.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ShowCustomersOnListView(DataBaseHelper dataBaseHelper) {
        customerArrayAdapter = new ArrayAdapter<CustomerModel>(SQLite.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEveryone());
        lv_customerList.setAdapter(customerArrayAdapter);
    }
}