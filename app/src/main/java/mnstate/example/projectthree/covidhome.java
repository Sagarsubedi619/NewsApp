package mnstate.example.projectthree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class covidhome extends AppCompatActivity {
    Button gomap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covidhome);
        gomap=findViewById(R.id.gotomap);
        gomap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(covidhome.this,covidmainmaps.class);

                startActivity(intent);
            }
        });
    }
}
