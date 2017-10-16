package majorproject.gpsgsm.com.api;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import majorproject.gpsgsm.com.api.Background.LoginRegisterBackground;

public class LoginRegisterActivity extends AppCompatActivity {


    EditText usernameEditText,passwordEditText;
    Button registerButton,loginButton;
    String username,password,token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        usernameEditText=(EditText)findViewById(R.id.usernameEditText);
        passwordEditText=(EditText)findViewById(R.id.passwordEditText);
        loginButton=(Button)findViewById(R.id.loginButton);
        registerButton=(Button)findViewById(R.id.registerButton);

        loginButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        username=usernameEditText.getText().toString();
                        password=passwordEditText.getText().toString();


                        //validate here

                        LoginRegisterBackground loginRegisterBackground=new LoginRegisterBackground(LoginRegisterActivity.this);
                        loginRegisterBackground.execute(username,password);





                    }
                }
        );




    }
}
