package com.sq.mcaguide;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firestore.admin.v1beta1.Progress;

public class MainActivity extends AppCompatActivity {
    //EditText etUsername, etPassword;
    private static final String TAG = "MainActivity";
    public static Context context;
    //Button btnLogin;
    ProgressBar loading;
    private boolean start=false;
    private long backPressedTime;
    @Override
    protected void onPostResume() {
        super.onPostResume();
        if(start)
            finish();
    }

    @Override
    public void onBackPressed() {
        if(backPressedTime + 2000 > System.currentTimeMillis())
        {
            super.onBackPressed();
            return;
        }
        else
        {
            Toast.makeText(getBaseContext(), "Press back again to Exit",Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
          context=MainActivity.this;
        loading = findViewById(R.id.loadingBar);
        final Intent dashboard = new Intent(MainActivity.this,Dashboard.class);

        loading.setProgress(0,true);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                start=true;
                loading.setProgress(100,true);
                startActivity(dashboard);
            }
        }, 1500);
        //etUsername = findViewById(R.id.etUsername);
        //etPassword = findViewById(R.id.etPassword);
        //btnLogin = findViewById(R.id.btnLogin);

//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(etUsername.getText().toString().equalsIgnoreCase("admin"))
//                {
//                    if(etPassword.getText().toString().equals("pass"))
//                    {
//                        Intent intent = new Intent(MainActivity.this,Dashboard.class);
//                        startActivity(intent);
//                    }
//                    else
//                    {
//                        Toast.makeText(MainActivity.this,"Invalid Password", Toast.LENGTH_LONG).show();
//                    }
//                }
//                else
//                {
//                    Toast.makeText(MainActivity.this,"UnAuthorized Access", Toast.LENGTH_LONG).show();
//                }
//            }
//        });

    }

}
