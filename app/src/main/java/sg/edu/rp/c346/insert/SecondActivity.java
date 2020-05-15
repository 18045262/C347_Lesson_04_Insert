package sg.edu.rp.c346.insert;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<Note> alNote;
    ArrayAdapter<Note> aaNote;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        lv = findViewById(R.id.lvShow);

        Intent i = getIntent();


        Database dbDetail = new Database(SecondActivity.this);
        alNote = dbDetail.getNoteContent();
        dbDetail.close();
        aaNote = new NoteAdapter(this,R.layout.row,alNote);
        lv.setAdapter(aaNote);



    }
}
