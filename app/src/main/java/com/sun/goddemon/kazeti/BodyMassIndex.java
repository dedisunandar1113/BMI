package com.sun.goddemon.kazeti;

/**
 * Created by God Demon on 17/12/2017.
 */

public class BodyMassIndex {
    double tinggiBadan;
    double beratBadan;

    public BodyMassIndex (double berat,double tinggi){
        beratBadan = berat;
        tinggiBadan = tinggi;
    }
    public int getBMI(){
        int bmi;
        double tinggiDalamMeter = tinggiBadan / 100;
        double hasil = beratBadan / (tinggiDalamMeter * tinggiDalamMeter);
        bmi = (int) Math.round(hasil);
        return bmi;
    }
    public BodyType getjenisBody(){
        BodyType b;
        if (getBMI()>30) {
            b = BodyType.Obesitas;
        } else if (getBMI()>25) {
            b = BodyType.Gemuk;
        } else if (getBMI()>20) {
            b = BodyType.Normal;
        } else {
            b = BodyType.Kurus;
        }
        return b;
    }
}

