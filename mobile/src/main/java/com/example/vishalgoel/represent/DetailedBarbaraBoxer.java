package com.example.vishalgoel.represent;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class DetailedBarbaraBoxer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_barbara_boxer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView view1 = (TextView) findViewById(R.id.Committees);
        view1.setText("Committees \n " +
                "   - Senate Select Committee on Ethics" +
                "\n    - Senate Committee on Environment and Public Works " +
                "\n    - Subcommittee on Transportation and Infrastructure " +
                "\n    - Senate Committee on Foreign Relations");

        TextView view2 = (TextView) findViewById(R.id.Bills);
        view2.setText("Recent Bills" +
                "   - S. 2487: Female Veteran Suicide Prevention Act\n" +
                "   - S. 2204: End of Suffering Act of 2015\n" +
                "   - S. 2157: SAFE DRONE Act of 2015\n" +
                "   - S. 2155: West Coast Ocean Protection Act of 2015");

    }


}
