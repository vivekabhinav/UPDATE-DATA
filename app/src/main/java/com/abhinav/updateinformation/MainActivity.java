package com.abhinav.updateinformation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference ref;
    EditText name,phone,symptom;
    int maxid = 0;
    Member member;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=findViewById(R.id.et_name);
        phone=findViewById(R.id.et_phone);
        symptom=findViewById(R.id.et_symptoms);
        btn = findViewById(R.id.btn_submit);

        member = new Member();
        ref = database.getInstance().getReference().child("User");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    maxid = (int) dataSnapshot.getChildrenCount();

                }
                else
                {
                    ///
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                member.setName(name.getText().toString());
                member.setName(phone.getText().toString());
                member.setName(symptom.getText().toString());

                ref.child(String.valueOf(maxid+1)).setValue(member);

            }
        });

    }
}