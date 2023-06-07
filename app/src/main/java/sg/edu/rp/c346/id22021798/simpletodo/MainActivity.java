package sg.edu.rp.c346.id22021798.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etTodo;
    Button btnAdd;
    Button btnDel;
    Button btnClear;
    ListView lvTodo;
    Spinner spinnerOptions;

    ArrayList<String> alTodo;
    ArrayAdapter<String> aaTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTodo=findViewById(R.id.etToDo);
        btnAdd=findViewById(R.id.btnAdd);
        btnDel=findViewById(R.id.btnDel);
        btnClear=findViewById(R.id.btnClear);
        lvTodo=findViewById(R.id.lvToDo);
        spinnerOptions=findViewById(R.id.spinnerOptions);

        alTodo=new ArrayList<>();
        aaTodo=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alTodo);
        lvTodo.setAdapter(aaTodo);

        spinnerOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedOption=parent.getItemAtPosition(position).toString();
                if (selectedOption.equals("Add a new task")){
                    etTodo.setHint("Type in new task here");
                    btnDel.setEnabled(false);
                    btnAdd.setEnabled(true);
                }else if (selectedOption.equals("Remove a task")){
                    etTodo.setHint("Type in the index of the task to be removed");
                    btnDel.setEnabled(true);
                    btnAdd.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // do nothing
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTodo=etTodo.getText().toString();
                alTodo.add(newTodo);
                aaTodo.notifyDataSetChanged();
                etTodo.setText(null);
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String indexStr=etTodo.getText().toString();
                if (!indexStr.isEmpty()){
                    int index=Integer.parseInt(indexStr);
                    if (index>=0&&index<alTodo.size()){
                        alTodo.remove(index);
                        aaTodo.notifyDataSetChanged();
                        etTodo.setText(null);
                    }else{
                        Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                        Log.d("MainActivity", "Wrong index number: " + index);
                    }
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alTodo.clear();
                aaTodo.notifyDataSetChanged();
            }
        });
    }
}
