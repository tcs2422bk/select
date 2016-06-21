package com.example.iii_user.select;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends AppCompatActivity {
private EditText password,account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        password = (EditText) findViewById(R.id.password);
        account = (EditText) findViewById(R.id.account);
    }
    public void login(View v){

        new Thread(){

            @Override
            public void run() {
                doLogin();
            }
        }.start();

    }
    private void doLogin(){
        String intA = account.getText().toString();
        String intP = password.getText().toString();

        try{
            MultipartUtility mu =
                    new MultipartUtility("http://10.2.1.123/select02.php","UTF-8");
            mu.addFormField("accou",intA);
            mu.addFormField("passwo",intP);
            //要多這一筆  不然抓不到
            mu.addFormField("","");
            List<String> ret = mu.finish();

            String result = ret.get(0);
            //Log.i("Jack",result);
            if(result.equals("OKK")){

                Log.i("Jack","Login OK~");
            }else{

                Log.i("Jack","GGWP");
            }

        }catch(Exception e){
            Log.i("Jack",e.toString());

        }
    }
}
