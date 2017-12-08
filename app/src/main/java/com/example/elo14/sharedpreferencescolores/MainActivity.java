package com.example.elo14.sharedpreferencescolores;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {

    SeekBar sbRojo;
    SeekBar sbVerde;
    SeekBar sbAzul;
    SeekBar sbAlfa;
    TextView tvRojo;
    TextView tvVerde;
    TextView tvAzul;
    TextView tvAlfa;
    Button btnSharedPreferences;
    SurfaceView sfv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sbRojo=(SeekBar)findViewById(R.id.sbRojo);
        sbAzul=(SeekBar)findViewById(R.id.sbAzul);
        sbVerde=(SeekBar)findViewById(R.id.sbVerde);
        sbAlfa=(SeekBar)findViewById(R.id.sbAlfa);
        tvRojo=(TextView)findViewById(R.id.tvRojo);
        tvAzul=(TextView)findViewById(R.id.tvAzul);
        tvVerde=(TextView)findViewById(R.id.tvVerde);
        tvAlfa=(TextView)findViewById(R.id.tvAlfa);
        btnSharedPreferences=(Button)findViewById(R.id.btnSharedPreferences);
        sfv=(SurfaceView)findViewById(R.id.sfv);

        //agregar escuchadores
        sbRojo.setOnSeekBarChangeListener(this);
        sbVerde.setOnSeekBarChangeListener(this);
        sbAzul.setOnSeekBarChangeListener(this);
        sbAlfa.setOnSeekBarChangeListener(this);
        btnSharedPreferences.setOnClickListener(this);

        //incorporamos objeto sharedprefernces
        SharedPreferences prefe=getSharedPreferences("datos", Context.MODE_PRIVATE);
        tvRojo.setText(prefe.getString("rojo", "0"));
        tvVerde.setText(prefe.getString("verde", "0"));
        tvAzul.setText(prefe.getString("azul", "0"));
        tvAlfa.setText(prefe.getString("alfa", "255"));

        //ajustamos las seekbars al valor de las sharedp
        sbRojo.setProgress(Integer.parseInt(tvRojo.getText().toString()));
        sbVerde.setProgress(Integer.parseInt(tvVerde.getText().toString()));
        sbAzul.setProgress(Integer.parseInt(tvAzul.getText().toString()));
        sbAlfa.setProgress(Integer.parseInt(tvAlfa.getText().toString()));

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        int id=seekBar.getId();

        if(id==sbRojo.getId()){
            tvRojo.setText(new Integer(progress).toString());
        }

        if(id==sbVerde.getId()){
            tvVerde.setText(new Integer(progress).toString());
        }

        if(id==sbAzul.getId()){
            tvAzul.setText(new Integer(progress).toString());
        }

        if(id==sbAlfa.getId()){
            tvAlfa.setText(new Integer(progress).toString());
        }

        sfv.setBackgroundColor(Color.argb(Integer.parseInt(tvAlfa.getText().toString()),
                Integer.parseInt(tvRojo.getText().toString()),
                Integer.parseInt(tvAzul.getText().toString()),
                Integer.parseInt(tvVerde.getText().toString())));

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onClick(View v) {
        SharedPreferences preferencias=getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferencias.edit();
        editor.putString("rojo",tvRojo.getText().toString());
        editor.putString("verde",tvVerde.getText().toString());
        editor.putString("azul",tvAzul.getText().toString());
        editor.putString("alfa",tvAlfa.getText().toString());
        editor.commit();
        Toast.makeText(this, "Guardado", Toast.LENGTH_LONG).show();
    }
}
