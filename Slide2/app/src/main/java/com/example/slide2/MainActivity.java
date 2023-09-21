package com.example.slide2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicReference;

public class MainActivity extends AppCompatActivity {
    private NumberPicker numberPicker;
    private EditText editAmount;
    private Button donate_btn;
    private TextView amount_text_view;
    private AtomicReference<Integer> sum = new AtomicReference<>(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.BLUE);
        toolbar.setTitleTextColor(Color.WHITE);

        //Button at the top right of the toolbar
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_settings) {
                    return true;
                } else if (item.getItemId() == R.id.action_more) {
                    return true;
                }
                return false;
            }
        });

        //NumberPicker setup
        numberPicker = findViewById(R.id.number_picker);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(1000);
        numberPicker.setWrapSelectorWheel(true);
        numberPicker.setValue(998);
        //numberPicker.setOnValueChangedListener((number_picker, oldVal, newVal) -> {});

        //other view
        editAmount = findViewById(R.id.editTextNumber);
        donate_btn = findViewById(R.id.button);
        donate_btn.setBackgroundColor(Color.BLUE);
        amount_text_view = findViewById(R.id.textView4);

        //Update donate amount
        //When user enters value in EditText
        editAmount.setOnEditorActionListener((v, actionID, event) -> {
            if (actionID == EditorInfo.IME_ACTION_DONE) {
                //get the value entered in EditText
                String str = editAmount.getText().toString().trim();

                if (!str.isEmpty()) {
                    //change string to integer and sum up with current value
                    int amount = Integer.parseInt(str);
                    sum.updateAndGet(prevSum -> prevSum + amount);
                    amount_text_view.setText("Total so far: " + sum.get() + " $");
                    editAmount.setText(""); //clear edit text
                    //close keyboard
                    InputMethodManager i = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    i.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }

                return true;
            }
            return false;
        });

        //When user clicks donate button
        donate_btn.setOnClickListener(v -> {
            Integer donateValue;
            if (editAmount.getText().toString().isEmpty())
                donateValue = Integer.parseInt(editAmount.getText().toString().trim());
            else donateValue = 0;
            sum.updateAndGet(prevSum -> prevSum + donateValue);
            amount_text_view.setText("Total so far: " + sum.get() + " $");
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
}