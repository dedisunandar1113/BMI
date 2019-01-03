package com.sun.goddemon.kazeti;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.goddemon.myapplication.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {
    EditText txtBerat,txtTinggi,txtBMI;
    TextView ket, tip;
    Button btnHitung,hapus;
    Toolbar toolbar;
    AdView ads;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("APP. BMI");
        setSupportActionBar(toolbar);

        MobileAds.initialize(getApplicationContext(), getString(R.string.banner1));
        ads = (AdView) findViewById(R.id.adview1);

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice(getString(R.string.banner1))
                .build();

        ads.loadAd(adRequest);

        txtBerat = (EditText) findViewById(R.id.beratBadan);
        txtTinggi = (EditText) findViewById(R.id.tinggiBadan);
        txtBMI = (EditText) findViewById(R.id.bmi);
        ket = (TextView) findViewById(R.id.txtKet);
        tip = (TextView) findViewById(R.id.txtTip);
        btnHitung = (Button) findViewById(R.id.btnHitung);
        hapus = (Button) findViewById(R.id.hapus);

        Button forward = (Button) findViewById(R.id.txtTip1);

        tip.setSelected(true);
        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtBerat.setText("");
                txtTinggi.setText("");
                txtBMI.setText("");
            }
        });

        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    hitungBMI();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AboutUs.class);
                startActivity(intent);

            }



        });
    }

    private double getValBMI() {
        double berat, tinggi;
        berat = Double.parseDouble(txtBerat.getText().toString());
        tinggi = Double.parseDouble(txtTinggi.getText().toString());
        BodyMassIndex orang1 = new BodyMassIndex(berat, tinggi);
        txtBMI.setText(String.valueOf(orang1.getBMI()));
        return orang1.getBMI();
    }


    private void hitungBMI() {
        double berat, tinggi;
        berat = Double.parseDouble(txtBerat.getText().toString());
        tinggi = Double.parseDouble(txtTinggi.getText().toString());
        BodyMassIndex orang1 = new BodyMassIndex(berat, tinggi);
        txtBMI.setText(String.valueOf(orang1.getBMI()));
        getKeterangan();
    }

    private void getKeterangan() {
        Double nilai = getValBMI();
        if (nilai >= 30) {
            ket.setText("Obesitas");
            tip.setText("Anda harus rutin berolahraga,Perbanyak mengonsumsi buah dan sayuran segar,Perbanyak minum air putih setiap hari,Usahakan makan lebih sering dan teratur,Ganti dari mengonsumsi nasi putih dengan nasi merah");
        } else if (nilai >= 25) {
            ket.setText("Gemuk");
            tip.setText("Jaga pola makan Anda dan kurangi makanan yang berlemak serta berminyak");
        } else if (nilai >= 20) {
            ket.setText("Ideal");
            tip.setText("Cek berat badan secara teratur, Istirahat yang cukup,Menjaga pola makan yang sehat,Konsumsi buah dan sayur,Rajin berolah raga,Atur porsi makan,Konsumsi air putih");
        } else {
            ket.setText("Kurus");
            tip.setText("Tambahkan kalori yang sehat,Pilih makanan padat gizi,Ngemil,Makan dalam porsi kecil,Latihan beban");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_more) {
            startActivity(new Intent(MainActivity.this, AboutUs.class));
        }
        return true;
    }

}
