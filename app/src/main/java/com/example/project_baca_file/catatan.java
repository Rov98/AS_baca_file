package com.example.project_baca_file;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class catatan extends AppCompatActivity {
    public static final String namaFile = "fileCatatan.txt";
    Button btndelete, btnsave;
    EditText txtInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catatan);

        btndelete = findViewById(R.id.hapusfile);
        btnsave = findViewById(R.id.simpanfile);
        txtInput = findViewById(R.id.textinput);
        txtInput.setText("");
        bacaFile();

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpanFile();
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hapusFile();
            }
        });
    }

    void buatFile(){
        File file = new File(getFilesDir(), namaFile);
        String isiFile = txtInput.getText().toString();

        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, false);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void simpanFile(){
        String ubah = txtInput.getText().toString();
        File file = new File(getFilesDir(), namaFile);
        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, false);
            outputStream.write(ubah.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    void bacaFile(){
        File file = new File(getFilesDir(), namaFile);
        if (file.exists()){
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();
                while (line != null){
                    text.append(line);
                    line = br.readLine();
                }
                br.close();
            } catch (IOException e){
                System.out.println("Error" + e.getMessage());
            }
            txtInput.setText(text.toString());
        } else {
            buatFile();
        }
    }

    void hapusFile(){
        File file = new File(getFilesDir(), namaFile);
        if (file.exists()){
            file.delete();
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "File Sudah Dihapus", Toast.LENGTH_LONG);
            toast.show();
        }
    }

}