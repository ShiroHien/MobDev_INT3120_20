package com.example.slide4;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

public class GridViewActivity extends Activity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
    TextView selection;
    String[] items = {"", "Android", "iOS", "Windows", "Blackberry"};
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.grid_view_test);
        selection = (TextView) findViewById(R.id.selection);
        GridView gv = (GridView) findViewById(R.id.grid);
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        gv.setAdapter(aa);
        gv.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        selection.setText(items[i]);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
