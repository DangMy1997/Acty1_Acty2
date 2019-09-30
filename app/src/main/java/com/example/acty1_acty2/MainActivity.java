package com.example.acty1_acty2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText et_filename,et_noidung;
    Button btnNhap, btnLuu, btnXem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_filename=(EditText)findViewById(R.id.edit_filename);
        et_noidung=(EditText)findViewById(R.id.edit_content);
        btnNhap=(Button)findViewById(R.id.button_nhapmoi);
        btnLuu=(Button)findViewById(R.id.button_save);
        btnXem=(Button)findViewById(R.id.button_xem);
        btnXem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);

            }
        });
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tieude = et_filename.getText().toString();
                String noidung = et_noidung.getText().toString();

                if (tieude!= null && !tieude.isEmpty() && noidung!=null && !noidung.isEmpty()){
                    List<String> strings = new ArrayList<>();
                    strings.add(tieude);
                    FileInputStream fnamein = null;
                    try {
                        fnamein = openFileInput("filename");
                        BufferedReader br = new BufferedReader(new InputStreamReader(fnamein));

                        String hienthi = null;
                        while ((hienthi = br.readLine())!=null){
                            if (!hienthi.equalsIgnoreCase(tieude)){
                                strings.add(hienthi);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        String luufilename = "";
                        for (int i=0;i<strings.size();i++){
                            luufilename = luufilename+"\n"+strings.get(i);
                        }

                        FileOutputStream fname = openFileOutput("filename",Context.MODE_PRIVATE);
                        fname.write(luufilename.getBytes());
                        fname.close();
                        FileOutputStream fout = openFileOutput(tieude, Context.MODE_PRIVATE);
                        fout.write(noidung.getBytes());
                        xoaTrang();
                        Toast.makeText(MainActivity.this,"Da luu file vao bo nho!!!",Toast.LENGTH_LONG).show();
                        fout.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else {
                    Toast.makeText(MainActivity.this,"Ten file va noi dung khong duoc bo trong",Toast.LENGTH_LONG).show();
                }

            }
        });

        btnNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xoaTrang();
            }
        });
    }

    private void xoaTrang() {
        et_filename.setText("");
        et_noidung.setText("");
    }
}
