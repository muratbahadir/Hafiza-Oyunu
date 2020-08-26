package com.example.hafzaoyunu;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SonucEkrani extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonuc);
         Intent a=getIntent();
         final Boolean sesMod=a.getBooleanExtra("oyunModuSes",true); //Oyun ekranından mod bilgisi alınır
         final Boolean goruntuMod=a.getBooleanExtra("oyunModuGoruntu",true);

        ImageView ıv=(ImageView)findViewById(R.id.ıw);
        Button yeniOyun=(Button)findViewById(R.id.yenidenoyna);
        yeniOyun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  //Kullanıcı yeni oyun butonuna tıklarsa son oynadığı mod  oyun ekranına gönderilerek oyun başlatılır.
                Intent ı=new Intent(SonucEkrani.this, OyunEkrani.class);
                ı.putExtra("oyunModuSes",sesMod);
                ı.putExtra("oyunModuGoruntu",goruntuMod);
                startActivity(ı);
                overridePendingTransition(R.anim.solakaydir, R.anim.sagakaydir);
                finish();
            }
        });
        Button menu=(Button)findViewById(R.id.menüyedön);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //Ana menüye gönderilir
                Intent menü=new Intent(SonucEkrani.this,MainActivity.class);
                startActivity(menü);
                overridePendingTransition(R.anim.solakaydir, R.anim.sagakaydir);
                finish();
            }
        });

        Button cikis=(Button)findViewById(R.id.çıkış);
        cikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  // Çıkış butonuna basıldığında oyunu kapatır.


                finish();

            }
        });

    
        int süre=a.getIntExtra("süre",-1);  //Süre ve hata bilgileri oyun ekranından alınır.
        int hata=a.getIntExtra("hata",-1);

        int puan=((150-süre)*20-20*hata);   //Belirlenen formülle puan hesaplanır.
        //Alınan puana göre ekranda fotoğraf gözükür ve ona uygun ses oynatılır.
        if(puan<=0)
        {
            MediaPlayer mp=MediaPlayer.create(this,R.raw.negerizekalisin);
            mp.start();
            ıv.setImageResource(R.drawable.rte);
        }
        if(puan<=200 && puan>0)
        {
            MediaPlayer mp=MediaPlayer.create(this,R.raw.alcakpust);
            mp.start();
            ıv.setImageResource(R.drawable.alcakp);
        }
        if(puan<=400 && puan>200)
        {
            MediaPlayer mp=MediaPlayer.create(this,R.raw.bitanetokat);
            mp.start();
            ıv.setImageResource(R.drawable.bitanetokatatacam);
        }
        if(puan<=600 && puan>400)
        {
            MediaPlayer mp=MediaPlayer.create(this,R.raw.sikiyonetim);
            mp.start();
            ıv.setImageResource(R.drawable.skyonetmm);
        }
        if(puan<=800 && puan>600)
        {
            MediaPlayer mp=MediaPlayer.create(this,R.raw.sendoneksin);
            mp.start();
            ıv.setImageResource(R.drawable.sendoneksin);
        }
        if(puan<=1100 && puan>800)
        {
            MediaPlayer mp=MediaPlayer.create(this,R.raw.ahlaksiz);
            mp.start();
            ıv.setImageResource(R.drawable.ahlakszadam);
        }
        if(puan>1100 && puan<=1500)
        {
            MediaPlayer mp=MediaPlayer.create(this,R.raw.senmenderesi);
            mp.start();
            ıv.setImageResource(R.drawable.senmenderesisavundun);
        }
        if(puan>1500 && puan<=2000)
        {
            MediaPlayer mp=MediaPlayer.create(this,R.raw.savunmadim);
            mp.start();
            ıv.setImageResource(R.drawable.savunmadm);
        }
        if(puan>2000 && puan<=3000)
        {
            MediaPlayer mp=MediaPlayer.create(this,R.raw.cikargoster);
            mp.start();
            ıv.setImageResource(R.drawable.savunmadmcikargoster);
        }
        if( puan>3000)
        {
            MediaPlayer mp=MediaPlayer.create(this,R.raw.lutfenyapmayin);
            mp.start();ıv.setImageResource(R.drawable.lutfenyapmayin);
        }

        //Süre ,hata ve puan bilgileri ilgili ekrana yazılır.
        TextView t1=(TextView)findViewById(R.id.textView7);
        t1.setText("Tamamlama süreniz="+süre+"sn.");

        TextView t2=(TextView)findViewById(R.id.textView8);
        t2.setText("Hamle sayınız:"+hata);
        TextView t3=(TextView)findViewById(R.id.textView9);
        t3.setText("Puanınız:"+((150-süre)*20-20*hata));


    }
}
