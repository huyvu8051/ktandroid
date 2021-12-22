package com.example.ktandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class FormActivity extends AppCompatActivity {

    private EditText txtName, txtPrice, txtDescription, txtImage;

    private Button btnSave, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        txtName = findViewById(R.id.txtTen);
        txtPrice = findViewById(R.id.txtGia);
        txtDescription = findViewById(R.id.txtDescription);
        txtImage = findViewById(R.id.txtAnh);

        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        txtPrice.setTransformationMethod(null);

        ProductPost product = (ProductPost) getIntent().getSerializableExtra("product");


        if(product != null){
            txtName.setText(product.getName());
            txtDescription.setText(product.getDescription());
            txtPrice.setText(product.getPrice() + "");
            txtImage.setText(product.getPicture());
        }


        btnSave.setOnClickListener(view -> {
            Intent i = new Intent();

            ProductPost pro = ProductPost.builder()
                    .Name(txtName.getText().toString())
                    .Description(txtDescription.getText().toString())
                    .Price(Integer.parseInt(txtPrice.getText().toString()))
                    .Picture(txtImage.getText().toString())
                    .build();

            if(product != null){
                pro.setId(product.getId());
                pro.setGroupName(product.getGroupName());
            }

            i.putExtra("product", pro);

            setResult(RESULT_OK, i);
            finish();

        });

        btnCancel.setOnClickListener(view -> {
            this.onBackPressed();

        });

    }


    @Override
    public void onBackPressed() {

        Intent i = new Intent();
        setResult(RESULT_CANCELED, i);
        finish();
    }
}