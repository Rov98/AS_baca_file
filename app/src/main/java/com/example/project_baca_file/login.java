package com.example.project_baca_file;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.Buffer;

public class login extends AppCompatActivity {

    public static final String namaUser = "user.txt";
    public static final String passUser = "pass.txt";

    Button btnLogin, btnDaftar;
    TextView txtUser, txtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnDaftar = findViewById(R.id.btnDaftar);
        btnLogin = findViewById(R.id.btnLogin);
        txtUser = findViewById(R.id.txtUser);
        txtPass = findViewById(R.id.txtPass);

        buatFile();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginFile();
            }
        });

    }
    void buatFile(){
        File fileUser = new File(getFilesDir(), namaUser);
        File filePass = new File(getFilesDir(), passUser);
        String isiNama = "demo";
        String isiPass = "123";

        if (!fileUser.exists() && !filePass.exists()){
            FileOutputStream outputStreamUser = null;
            FileOutputStream outputStreamaPass = null;
            if (!fileUser.exists() && !filePass.exists()) {
                try {
                    fileUser.createNewFile();
                    filePass.createNewFile();
                    outputStreamUser = new FileOutputStream(fileUser, false);
                    outputStreamaPass = new FileOutputStream(filePass, false);
                    outputStreamUser.write(isiNama.getBytes());
                    outputStreamaPass.write(isiPass.getBytes());
                    outputStreamUser.flush();
                    outputStreamaPass.flush();
                    outputStreamUser.close();
                    outputStreamaPass.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "File Sudah Ada", Toast.LENGTH_LONG);
                toast.show();
            }
        } else {
            fileUser.delete();
            filePass.delete();
            buatFile();
        }
    }

    void loginFile(){
        File fileUser = new File(getFilesDir(), namaUser);
        File filePass = new File(getFilesDir(), passUser);

            StringBuilder textUser = new StringBuilder();
            StringBuilder textpass = new StringBuilder();
            try {
                BufferedReader brUser = new BufferedReader(new FileReader(fileUser));
                BufferedReader brPass = new BufferedReader(new FileReader(filePass));
                String lineUser = brUser.readLine();
                String linePass = brPass.readLine();
                while (lineUser != null && linePass != null){
                    textUser.append(lineUser);
                    textpass.append(linePass);
                    lineUser = brUser.readLine();
                    linePass = brPass.readLine();
                }
                brPass.close();
                brUser.close();
            } catch (IOException e){
                System.out.println("Error" + e.getMessage());
            }

            if (txtUser.getText().toString().equals(textUser.toString())  && txtPass.getText().toString().equals(textpass.toString())){

                startActivity(new Intent(login.this, menu_utama.class));

            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "Username Atau Password Salah", Toast.LENGTH_LONG);
                toast.show();
            }
    }
}