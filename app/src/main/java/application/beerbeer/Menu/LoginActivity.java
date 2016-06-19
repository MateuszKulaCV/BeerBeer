package application.beerbeer.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;

import application.beerbeer.R;
import application.beerbeer.ResponsePack.GetResponseAPI;
import application.beerbeer.ResponsePack.UserResponse;
import cz.msebera.android.httpclient.Header;

/**
 * Created by methyll.
 */
public class LoginActivity extends AppCompatActivity {
    EditText etEmail, etPassword;
    Button BtnLogin, BtnSignIn;
    String FINAL_URL;
    Gson gson;
    UserResponse userResponse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etEmail = (EditText) findViewById(R.id.email);
        etPassword = (EditText) findViewById(R.id.password);
        BtnLogin = (Button) findViewById(R.id.login_button);
        BtnSignIn = (Button) findViewById(R.id.singin_button);

        setView();

    }

    void setView()
    {
     BtnLogin.setOnClickListener(new View.OnClickListener() {

         @Override
         public void onClick(View v) {
             FINAL_URL = GetResponseAPI.LOGIN_URL+"?email="+etEmail.getText()+"&password="+etPassword.getText();
             Log.d("url", FINAL_URL);
             GetResponseAPI.get(FINAL_URL, null, new AsyncHttpResponseHandler() {
                 @Override
                 public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                     String strUserResponse = new String(responseBody);
                     strUserResponse = strUserResponse.trim();

                     if(strUserResponse.equals("wrong data")) {

                         Toast.makeText(getApplicationContext(), strUserResponse,Toast.LENGTH_SHORT).show();
                         Log.d("if",strUserResponse+"end");

                     }else
                     {

                        gson = new Gson();
                        userResponse = gson.fromJson(strUserResponse, UserResponse.class);
                     //   Toast.makeText(getApplicationContext(),userResponse.getEmail()+"   "+userResponse.getFavourite(),Toast.LENGTH_SHORT).show();
                        Log.d("else",strUserResponse+"end");

                         Intent intent = new Intent(getApplicationContext(),FirstActivity.class);
                         intent.putExtra("userFav",userResponse.getFavourite());
                         intent.putExtra("userEmail",userResponse.getEmail());
                         intent.putExtra("strResponse",getIntent().getExtras().getString("strResponse"));
                         startActivity(intent);

                     }


                 }

                 @Override
                 public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                     Toast.makeText(getApplicationContext(),"CONNECTION PROBLEM", Toast.LENGTH_SHORT).show();
                 }
             });
         }
     });
    }

}
