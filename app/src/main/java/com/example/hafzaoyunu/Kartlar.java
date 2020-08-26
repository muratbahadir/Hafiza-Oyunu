package com.example.hafzaoyunu;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Button;


public class Kartlar implements Parcelable { //Oyun ekranında kullanılan kart bilgilerini tutan sınıf
    Button[] b =new Button[16]; //Ekranda 16 buton olduğu için 16 ayrı buton tanımlanır.
    Boolean[] kartKontrol =new Boolean[16]; //Açıp kapatma durumlarının takibi için her kart için kartın açık olduğunu kontrol eden Boolean tanımlanır.

    int sonId; //Bir önceki açılan kart bilgisini tutar
    int sonKart=0;   //Tıklanılan kart harici eşleşmeyi bekleyen açık kart olup olmadığını tutar. 0 ise açık kart yok .0 dan farklı bir id değerine sahipse açık kart var.
    int onPlanId = 0; //Tıklanılan kartın ön yüzündeki fotoğraf id bilgisini tutar

    public Kartlar() {

    }

    public Button[] getB() {
        return b;
    }

    public void setB(Button[] b) {
        this.b = b;
    }

    public Kartlar(Button[] b) {
        this.b = b;
    }

    int hata;   //Sonuc ekranında gözükecek ve puan hesabında kullanılacak  değerler:Hata,Hamle
    int hamle=0;
    int sonindes;

    //Aşağıdaki fonksiyonlar Parcelable sınıfından implement edildiği için gelmiştir ve aktivite kaydedilip geri yüklenilmesinde işe yararlar.
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeArray(this.kartKontrol);
        dest.writeInt(this.sonId);
        dest.writeInt(this.sonKart);
        dest.writeInt(this.onPlanId);
        dest.writeInt(this.hata);
        dest.writeInt(this.hamle);
        dest.writeInt(this.sonindes);

    }

    protected Kartlar(Parcel in) {
        this.b = (Button[]) in.readArray(Button[].class.getClassLoader());
        this.kartKontrol = (Boolean[]) in.readArray(Boolean[].class.getClassLoader());


        this.sonId = in.readInt();
        this.sonKart = in.readInt();
        this.onPlanId = in.readInt();
        this.hata = in.readInt();
        this.hamle = in.readInt();
        this.sonindes = in.readInt();

    }

    public static final Parcelable.Creator<Kartlar> CREATOR = new Parcelable.Creator<Kartlar>() {
        @Override
        public Kartlar createFromParcel(Parcel source) {
            return new Kartlar(source);
        }

        @Override
        public Kartlar[] newArray(int size) {
            return new Kartlar[size];
        }
    };



}
