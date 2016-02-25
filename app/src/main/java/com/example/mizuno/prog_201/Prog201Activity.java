package com.example.mizuno.prog_201;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class Prog201Activity extends AppCompatActivity {

    Button button;
    EditText editText;
    TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prog201);

        button = (Button)findViewById(R.id.button);
        editText = (EditText)findViewById(R.id.editText);
        textview = (TextView)findViewById(R.id.textView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String data = Calendar.getInstance().getTime().toString();
                data += "\n";
                data += editText.getText();
                data += "-end-";
                //data += "\n";

                //textview.setText(data);
                editText.setText("");


                try {
                    textview.setText("");
                    FileOutputStream fileOutputStream = openFileOutput("myfile1.txt", MODE_APPEND);
                    String writeString = data;
                    fileOutputStream.write(writeString.getBytes());
                } catch (FileNotFoundException e) {
                } catch (IOException e) {}

                try {
                    FileInputStream fileInputStream;
                    fileInputStream = openFileInput("myfile1.txt");
                    byte[] readBytes = new byte[fileInputStream.available()];
                    fileInputStream.read(readBytes);
                    String readString = new String(readBytes);

                    String[] temp = new String[readString.split("-end-").length];
                    temp = readString.split("-end-");
                    for (int i = temp.length-1;i>=0;i--){
                        textview.append(temp[i]);
                        textview.append("\n");
                    }//-------

                    //textview.setText(readString);
                } catch (FileNotFoundException e) {
                } catch (IOException e) {}

            }
        });

        }
    }
