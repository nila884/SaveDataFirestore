package com.nila.saveandstore.ui.fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.nila.saveandstore.Models.PhoneDescription;
import com.nila.saveandstore.Models.User;
import com.nila.saveandstore.R;
import com.nila.saveandstore.singleton.UserClient;

public class SaveDataActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText b_name;
    private EditText b_prename;
    private EditText p_model;
    private EditText p_mark;
    private EditText p_color;
    private EditText p_imeione;
    private EditText p_imeitwo;
    private EditText p_sn;
    private EditText p_price;

    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_data);

        b_name=findViewById(R.id.buyer_name);
        b_prename=findViewById(R.id.buyer_prename);
        p_model=findViewById(R.id.phone_model);
        p_color=findViewById(R.id.phone_color);
        p_mark=findViewById(R.id.phone_mark);
        p_imeione=findViewById(R.id.phone_imeiOne);
        p_imeitwo=findViewById(R.id.phone_imeiTwo);
        p_sn=findViewById(R.id.phone_sn);
        p_price=findViewById(R.id.phone_price);

        db=FirebaseFirestore.getInstance();

        findViewById(R.id.saveData).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        saveDataInFirestore();
    }

    private void saveDataInFirestore() {

        //store in string edit texts

        String buyer_name=b_name.getText().toString();
        String buyer_prename=b_prename.getText().toString();
        String phone_color=p_color.getText().toString();
        String phone_model=p_model.getText().toString();
        String phone_mark=p_mark.getText().toString();
        String phone_imeione=p_imeione.getText().toString();
        String phone_imeitwo=p_imeitwo.getText().toString();
        String phone_sn=p_sn.getText().toString();
        String phone_price=p_price.getText().toString();

        //check if there is no empty field
        if(!buyer_name.isEmpty()&&!buyer_prename.isEmpty()&&!phone_color.isEmpty()&&!phone_model.isEmpty()&&!phone_mark.isEmpty()
                &&!phone_imeione.isEmpty()&&!phone_imeitwo.isEmpty()&&!phone_sn.isEmpty()&&!phone_price.isEmpty()){


            User user = ((UserClient)(getApplicationContext())).getUser();
            PhoneDescription phoneDescription=new PhoneDescription();
            phoneDescription.setBuyer_name(buyer_name);
            phoneDescription.setBuyer_prename(buyer_prename);
            phoneDescription.setPhone_color(phone_color);
            phoneDescription.setPhone_mark(phone_mark);
            phoneDescription.setPhone_model(phone_model);
            phoneDescription.setPhone_imeione(phone_imeione);
            phoneDescription.setPhone_imeitwo(phone_imeitwo);
            phoneDescription.setPhone_sn(phone_sn);
            phoneDescription.setPhone_price(phone_price);
            phoneDescription.setUser(user);

            Log.d("phone", "insertNewMessage: retrieved user client: " + phoneDescription.toString());
            //store in firestore
            db.collection("data_save").document().set(phoneDescription).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if(task.isSuccessful()){
                        Toast.makeText(SaveDataActivity.this, "data save successfully", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(SaveDataActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            });


            return ;
        }

        else {

            Toast.makeText(SaveDataActivity.this, "Form invalid", Toast.LENGTH_SHORT).show();
            return;
        }

    }

}
