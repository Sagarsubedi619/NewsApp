package mnstate.example.projectthree;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class register extends AppCompatActivity implements AdapterView.OnItemSelectedListener   {
    EditText Username,password,phonenum;
    TextView Datepicker1;
    RadioGroup gender;
    Button confirm, summ;
    Spinner spinner1;
    String tmpgen;
    String item;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Username=findViewById(R.id.regUname);
        password=findViewById(R.id.password1);
        Datepicker1=findViewById(R.id.date);

        phonenum=findViewById(R.id.phnnum);
        spinner1=findViewById(R.id.spinner1);
        gender=findViewById(R.id.radioGroup);
        confirm=findViewById(R.id.confirm);
        summ=findViewById(R.id.summary);

        databaseHelper=new DatabaseHelper(this);


        //for gender


        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radio1:
                        tmpgen="Male";

                        break;
                    case R.id.radio2:
                        tmpgen="Female";
                        break;
                    case R.id.radio3:
                        tmpgen="Other";
                        break;

                }
            }
        });

        //for spinner



        List<String> categories=new ArrayList<>();
        categories.add("Student");
        categories.add("Teacher");
        categories.add("Other");


        ArrayAdapter<String> dataAdapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(dataAdapter);
        spinner1.setOnItemSelectedListener(this);

        //spinner ends above


        //for datepick


        Datepicker1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day

                DatePickerDialog datePickerDialog = new DatePickerDialog(register.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                Datepicker1.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        //date ends above













        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UsernameValue=Username.getText().toString();
                String passwordValue=password.getText().toString();
                String phonenumValue=phonenum.getText().toString();
                String genderValue=tmpgen;
                String spinnerValue=item;
                String DatepickerValue=Datepicker1.getText().toString();









                if(UsernameValue.length()>=1 && passwordValue.length()>=1 && phonenumValue.length()>=1 && DatepickerValue.length()>=1){
                    ContentValues contentValues= new ContentValues();
                    contentValues.put("username",UsernameValue);
                    contentValues.put("password",passwordValue);
                    contentValues.put("phnnum",phonenumValue);
                    contentValues.put("status",spinnerValue);
                    contentValues.put("gender",genderValue);
                    contentValues.put("dob",DatepickerValue);
                    databaseHelper.insertUser(contentValues);
                    Toast.makeText(register.this,"Successfully registered",Toast.LENGTH_LONG).show();

                }
                else{
                    Toast.makeText(register.this,"Oops ! you are missing something ",Toast.LENGTH_LONG).show();
                }



            }
        });

        summ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(register.this,retrieveActivity.class);

                startActivity(intent);
            }
        });

    }




    //for spinners
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        item=parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}