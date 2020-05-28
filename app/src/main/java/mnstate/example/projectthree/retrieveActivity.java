package mnstate.example.projectthree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class retrieveActivity extends AppCompatActivity {
    DatabaseHelper db;
    TextView textView, gen, sta,dobb,ph;
    Button confirmbtn,goback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve);
        db=new DatabaseHelper(this);
        confirmbtn=findViewById(R.id.okbtn);
        goback=findViewById(R.id.gobackbtn);

        confirmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(retrieveActivity.this,signin.class);

                startActivity(intent);
            }
        });


        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(retrieveActivity.this,register.class);

                startActivity(intent);

            }
        });












        textView=findViewById(R.id.firstnau);
      gen=findViewById(R.id._gender);
      sta=findViewById(R.id._status);
      dobb=findViewById(R.id._dob);
      ph=findViewById(R.id._phnn);

       Cursor cursor =db.alldata();
       if(cursor.getCount()==0){
           Toast.makeText(getApplicationContext(),"No Data",Toast.LENGTH_SHORT).show();
       }
       else{
           while(cursor.moveToNext()) {

               textView.setText("First Name:  " + cursor.getString(1));
               gen.setText("Gender:  " + cursor.getString(3));
               sta.setText("Status:  " + cursor.getString(4));
               dobb.setText("Date of Birth:  " + cursor.getString(5));
               ph.setText("Phone num:  " + cursor.getString(6));
           }


       }
    }
}
