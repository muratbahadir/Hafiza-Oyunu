package com.example.hafzaoyunu;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity implements  View.OnClickListener {
    EditText et;
    Boolean ses, goruntu; //Oyun modunda ses modu ve goruntu modu olup olmadığını kontrol eder.
    Boolean dizi[]=new Boolean[2]; //Oyun ekranına gönderilirken oyun modunu barındıran(ses,goruntu) bilgi oyun ekranına gönderilir.
    Button start, gorselMod, sesMod;
    Button seciliButon;  //Kullanıcının seçtiği butona göre oyun modu belirlenir.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button fullMod=(Button)findViewById(R.id.button5);
        seciliButon =fullMod; //Seçili mod default olarak fullmod(gorselMod+sesMod) atanır.


           start =(Button) findViewById(R.id.startGame);
           start.setOnClickListener(this);      //Oyuna başla butonuna tıklanıldığında bu sınıftaki onClick (en altdaki) fonksionu çalıştır.
           ImageButton bilgi=(ImageButton)findViewById(R.id.imageButton2);
           bilgi.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {  //Bilgi resmine tıklanıldığında oyun hakkında bilgi veren alertDialog çalıştırılır.
                   AlertDialog.Builder al=new AlertDialog.Builder(MainActivity.this);
                   al.setTitle("Oyun Hakkında");
                   al.setMessage("Bu oyun trol amaçlı yapılmıştır.\n " +
                           "3 farklı mod sizleri bekliyor:\n" +
                           "1-)Sesli hafıza:Sadece sesleri dinleyerek eşleşen iki kutuyu bulmaya çalışacaksınız.\n" +
                           "2-)Görsel hafıza:Klasik hafıza kutu oyunu ,aynı görselleri bulmaya çalışacaksınız.\n"+
                           "3-)Görsel + Ses:Hem sesleri hem görselleri görebileceksiniz.\n\n"+
                           "Puanlarınıza göre seviyeniz:\n" +
                           "3000+:Lütfen yapmayın \n"+
                           "2000-3000:Savunmadım Çıkar Göster \n"+
                           "1500-2000:Savunmadım \n"+
                           "1100-1500:Sen Menderesi savundun \n"+
                           "800-1100:Ahlaksız adam\n"+
                           "600-800:Sen döneksin\n"+
                           "400-600:Dönekliğini ilan ettin\n"+
                           "200-400:Bitane tokat atacam\n"+
                           "0-200:Alçak puşt\n"+
                           "0>:Süpriz sonlu\n\n"+
                           "Yazan:Murat BAHADIR");
                   al.setPositiveButton("Tamam",null);

                   al.show();

               }
           });



            sesMod =(Button)findViewById(R.id.button3);
           sesMod.setOnClickListener(new View.OnClickListener() {
               @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
               @Override
               public void onClick(View v) { //Ses hafızası moduna tıklanıldığında
                   sesMod.setBackground(getDrawable(android.R.drawable.button_onoff_indicator_on)); //Bu buton artık seçili olduğu için yeşil arkaplana boyanır.
                   seciliButon.setBackground(getDrawable(android.R.drawable.button_onoff_indicator_off)); //Bir önceki seçili buton gri butona boyanır.
                   seciliButon = sesMod;   //Seçilii buton sesMod olarak atanır.
               }
           });
           //Diğer modlarda da aynı mantık geçerli olur.Basılan buton yeşil arkaplan olur bir önceki seçili buton gri buton rengini alır ve secili buton değeri atanır.
            gorselMod =(Button)findViewById(R.id.button4);
           gorselMod.setOnClickListener(new View.OnClickListener() {
               @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
               @Override
               public void onClick(View v) {
                   gorselMod.setBackground(getDrawable(android.R.drawable.button_onoff_indicator_on));
                   seciliButon.setBackground(getDrawable(android.R.drawable.button_onoff_indicator_off));
                   seciliButon = gorselMod;
               }
           });
            fullMod.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View v) {
                    fullMod.setBackground(getDrawable(android.R.drawable.button_onoff_indicator_on));
                    seciliButon.setBackground(getDrawable(android.R.drawable.button_onoff_indicator_off));
                    seciliButon =fullMod;
                }
            });

    }




    @Override
    public void onClick(View v) {

         Intent ı=new Intent(this, OyunEkrani.class);
         //Seçili butona göre ses ve goruntu bilgisi (mod bilgisi) oyun ekranına gönderilir.

         if(seciliButon.equals(gorselMod))
         {
             ses=false;
             goruntu =true;

         }
         else if(seciliButon.equals(sesMod))
         {
             ses=true;
             goruntu =false;
   }
        else
        {
            ses=true;
            goruntu =true;

        }

         ı.putExtra("oyunModuSes",ses);
        ı.putExtra("oyunModuGoruntu", goruntu);
        startActivity(ı);
        overridePendingTransition(R.anim.solakaydir, R.anim.sagakaydir);
        finish();


    }
}
