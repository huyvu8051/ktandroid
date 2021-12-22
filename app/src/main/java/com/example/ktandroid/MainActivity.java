package com.example.ktandroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    List<ProductGet> rowssv;
    SinhVienAdapter sinhVienAdapter;
    ListView listViewSinhvien;


    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getDataFromServer();

        listViewSinhvien = findViewById(R.id.lvSinhVien);

        btnAdd = findViewById(R.id.add);

        btnAdd.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), FormActivity.class);

            startActivityForResult(intent, 0);

        });

        listViewSinhvien.setOnItemClickListener((adapterView, view, i, l) -> {
            ProductGet productGet = rowssv.get(i);

            Intent intent = new Intent(getBaseContext(), FormActivity.class);

            ProductPost productPost = ProductPost.builder()
                    .Id(productGet.getId())
                    .Name(productGet.getName())
                    .Price(productGet.getPrice())
                    .Description(productGet.getDescription())
                    .Picture(productGet.getPicture())
                    .GroupName(productGet.getGroupName())
                    .build();

            intent.putExtra("product", productPost);

            startActivityForResult(intent, 1);
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK) {
            ProductPost product = (ProductPost) data.getSerializableExtra("product");

            product.setId("00000000-0000-0000-0000-000000000000");
            product.setGroupName("18DTHA2-nhom2");
            ApiService.apiService.save(product).enqueue(new Callback<PostResponseDto>() {
                @Override
                public void onResponse(Call<PostResponseDto> call, Response<PostResponseDto> response) {
                    System.out.println(response.body());
                }

                @Override
                public void onFailure(Call<PostResponseDto> call, Throwable t) {

                    System.out.println(t.getMessage());
                }
            });


        }

        if (requestCode == 0 && resultCode == RESULT_CANCELED) {

        }

        if (requestCode == 1 && resultCode == RESULT_OK) {
            ProductPost product = (ProductPost) data.getSerializableExtra("product");

            ApiService.apiService.save(product).enqueue(new Callback<PostResponseDto>() {
                @Override
                public void onResponse(Call<PostResponseDto> call, Response<PostResponseDto> response) {
                    System.out.println(response.body());
                }

                @Override
                public void onFailure(Call<PostResponseDto> call, Throwable t) {

                    System.out.println(t.getMessage());
                }
            });

        }

        getDataFromServer();
        listViewSinhvien.deferNotifyDataSetChanged();
    }


    private void getDataFromServer() {
        ApiService.apiService.getAll().enqueue(new Callback<GetResponseDto>() {
            @Override
            public void onResponse(Call<GetResponseDto> call, Response<GetResponseDto> response) {
                GetResponseDto body = response.body();
                System.out.println(body);


               if(body != null){
                   rowssv = body.getData();

                   listViewSinhvien = (ListView) findViewById(R.id.lvSinhVien);

                   sinhVienAdapter = new SinhVienAdapter(MainActivity.this, R.layout.activity_main, rowssv);
                   //

                   listViewSinhvien.setAdapter(sinhVienAdapter);
               }

            }

            @Override
            public void onFailure(Call<GetResponseDto> call, Throwable t) {
                System.out.println("body");
            }
        });
    }
}