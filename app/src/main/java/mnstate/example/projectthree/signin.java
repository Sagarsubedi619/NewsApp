package mnstate.example.projectthree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class signin extends AppCompatActivity implements TextWatcher, CompoundButton.OnCheckedChangeListener {
    EditText Username,Password;
    Button Signin;
    DatabaseHelper databaseHelper;

    //for shared preference
    CheckBox rem_username;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final String PREF_NAME="prefs";
    private static final String KEY_REMEMBER="remember";
    private static final String KEY_USERNAMEE="username";
    private static final String KEY_PASS="password";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        Username=findViewById(R.id.Username);
        Password=findViewById(R.id.password);
        Signin=findViewById(R.id.RealSignin);
        rem_username=findViewById(R.id.ceckbox);
        databaseHelper=new DatabaseHelper(this);

        //shared
        sharedPreferences=getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();


        if(sharedPreferences.getBoolean(KEY_REMEMBER,false))
            rem_username.setChecked(true);
        else
            rem_username.setChecked(false);

        Username.setText(sharedPreferences.getString(KEY_USERNAMEE,""));
        Password.setText(sharedPreferences.getString(KEY_PASS,""));

        Username.addTextChangedListener(this);
        Password.addTextChangedListener(this);
        rem_username.setOnCheckedChangeListener(this);

        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameValue=Username.getText().toString();
                String PasswordValue=Password.getText().toString();
                Bundle bundle=new Bundle();
                bundle.putString("usernamego",usernameValue);

                if (databaseHelper.isLoginValid(usernameValue,PasswordValue)){
                    Intent intent=new Intent(signin.this,homeactivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);

                }
                else{
                    Toast.makeText(signin.this,"Invalid Username or Password",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        managePrefs();

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        managePrefs();

    }

    @Override
    public void afterTextChanged(Editable s) {
        managePrefs();

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        managePrefs();

    }

    private void managePrefs(){

        if(rem_username.isChecked()){
            editor.putString(KEY_USERNAMEE,Username.getText().toString().trim());
            editor.putString(KEY_PASS,Password.getText().toString().trim());
            editor.putBoolean(KEY_REMEMBER,true);
            editor.apply();
        }
        else{
            editor.putBoolean(KEY_REMEMBER,false);
            editor.remove(KEY_PASS);
            editor.remove(KEY_USERNAMEE);
            editor.apply();
        }
    }
}