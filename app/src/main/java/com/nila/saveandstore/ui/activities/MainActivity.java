package com.nila.saveandstore.ui.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.nila.saveandstore.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        findViewById(R.id.sign_out).setOnClickListener(this);
        findViewById(R.id.add).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
 switch (v.getId()){
            case R.id.sign_out:{
                signOut();
                break;
            }

            case R.id.add:{
                Intent registerActivity=new Intent(this,SaveDataActivity.class);
                startActivity(registerActivity);
                break;
            }
        }


    }



    private void signOut(){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        signOut();
    }
}
