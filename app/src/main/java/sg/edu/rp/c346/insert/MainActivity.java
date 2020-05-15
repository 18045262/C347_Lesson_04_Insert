package sg.edu.rp.c346.insert;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MainActivity extends AppCompatActivity {
    Button btnInsert, btnShow;
    EditText etNoteName;
    RadioGroup rg;
    RadioButton rb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnShow = findViewById(R.id.btnShow);
        rg = findViewById(R.id.rgStars);
        etNoteName = findViewById(R.id.etNote);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database db = new Database(MainActivity.this);

                db.insertTask("Submit RJ","1");
                db.close();

                Toast.makeText(MainActivity.this, "Inserted !", Toast.LENGTH_LONG).show();


            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(i);
            }
        });
    }
}
