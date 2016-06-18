package application.beerbeer.Menu;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.loopj.android.http.AsyncHttpResponseHandler;


import application.beerbeer.R;
import application.beerbeer.ResponsePack.GetResponseAPI;
import cz.msebera.android.httpclient.Header;

/**
 * Created by methyll.
 */
public class MainMenu extends AppCompatActivity {
    public final String fav = "Kontynuacja/NOWE/Marynka";
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);
        imageView = (ImageView) findViewById(R.id.imageView);
        SetConn();



    }





    void SetConn()
    {
        GetResponseAPI.get(GetResponseAPI.DBCON_URL, null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    String strResponse = new String(responseBody);

                Intent intent = new Intent(getApplicationContext(),FirstActivity.class);
                intent.putExtra("strResponse", strResponse);
                startActivity(intent);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

 /*   void PrepareList(String favpub)
    {


    }
*/

}
