package com.example.signapp;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;



public class SettingsMain extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings_layout);

        Bundle settings = getIntent().getExtras();

        final EditText signTextEV = (EditText) findViewById(R.id.editSignature);
	    signTextEV.setText(settings.getString("signText"));

        final EditText fontSizeEV = (EditText) findViewById(R.id.editText01);
        fontSizeEV.setText(String.valueOf(settings.getInt("fontSize")));

        final Spinner colorS = (Spinner) findViewById(R.id.spinnerColor);

        colorS.setSelection(settings.getInt("color"));
 
        Button save = (Button)findViewById(R.id.button_save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String signText = String.valueOf(signTextEV.getText());
                int fontSize = Integer.valueOf(String.valueOf(fontSizeEV.getText()));
                int color = colorS.getSelectedItemPosition();

                SQLiteDatabase db = openOrCreateDatabase("MyDB", MODE_PRIVATE, null);
                String sql_update = "UPDATE Daten SET signText ='"+signText+"', color="+color+", fontSize= "+fontSize + " where id=1;";
                db.execSQL(sql_update);
                db.close();
                
                Toast.makeText(getApplicationContext(), "Save Sucess!", 
                		Toast.LENGTH_SHORT).show();
                
                finish();
            }
        });


    }
}