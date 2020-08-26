package com.example.hafzaoyunu;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatDrawableManager;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;


public class OyunEkrani extends AppCompatActivity {


    Kartlar kartlar;
    private Object Button;
    private Object TextView;
    boolean sesMod;
    boolean goruntuMod;
    Intent fınalEkran;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fınalEkran =new Intent(OyunEkrani.this, SonucEkrani.class); //Sonuç ekranına gececek ıntent oluşturulur.
        Intent ı=getIntent(); //Bir önceki aktiviteden gelen mod bilgisi alınır.
        sesMod =ı.getBooleanExtra("oyunModuSes",true);
        goruntuMod =ı.getBooleanExtra("oyunModuGoruntu",true);



        setContentView(R.layout.activity_deneme);
        if (savedInstanceState == null)
        {    // Aktivitenin yeniden bir aktivite  olduğunu kontrol et
            kartlar = new Kartlar(); //Her kart üzerinde işlem yapmak için Kartlar sınıfından nesne üretilir
            Button ab = findViewById(R.id.button22);
            //Aktivite ekranındaki 16 butonun(kartın) bilgisi kartlar nesnesindeki butonlara sırasıyla eklenir.
            kartlar.b[0] = ab;
            Button bb = findViewById(R.id.button33);

            kartlar.b[1] = bb;
            Button cb = findViewById(R.id.button44);

            kartlar.b[2] = cb;
            Button db = findViewById(R.id.button55);

            kartlar.b[3] = db;
            Button eb = findViewById(R.id.button66);

            kartlar.b[4] = eb;
            Button d = findViewById(R.id.button77);

            kartlar.b[5] = d;

            Button gb = findViewById(R.id.button88);

            kartlar.b[6] = gb;
            Button hb = findViewById(R.id.button99);

            kartlar.b[7] = hb;
            Button ib = findViewById(R.id.button100);

            kartlar.b[8] = ib;
            Button jb = findViewById(R.id.button111);

            kartlar.b[9] = jb;
            Button kb = findViewById(R.id.button122);

            kartlar.b[10] = kb;
            Button mb = findViewById(R.id.button133);

            kartlar.b[11] = mb;
            Button nb = findViewById(R.id.button144);

            kartlar.b[12] = nb;
            Button ob = findViewById(R.id.button155);

            kartlar.b[13] = ob;
            Button lb = findViewById(R.id.button166);

            kartlar.b[14] = lb;
            Button yb = findViewById(R.id.button177);

            kartlar.b[15] = yb;

            for (int i = 0; i < 16; i++) //Kart dağılımları her oyunda farklı olması için 16 kez random sayı verilerek kartlar yer değiştirilir ve karıştırılır
            {
                int rg = (int) (Math.random() * 16);
                Button geçici = kartlar.b[i];
                kartlar.b[i] = kartlar.b[rg];
                kartlar.b[rg] = geçici;
            }
            for (int i = 0; i < 16; i++) { //Seçilen oyun moduna göre kartların arkaplan renkleri ayarlanır.

                if(goruntuMod)
                { //Eğer görüntü mod dahilse tüm arkaplanlar Ugur Dundar lütfen yapmayın görseli işaretlenir ve kartların kapalı olduğu bilgisi atanır.
                    @SuppressLint("RestrictedApi") final Drawable arka = AppCompatDrawableManager.get().getDrawable(this, R.drawable.lutfenyapmayin);
                    kartlar.b[i].setBackground(arka);
                    kartlar.kartKontrol[i] = false;
                }
                else
                { //Eğer görüntü mod dahil değilse tüm arkaplanlar gri tondaki bir  görselle işaretlenir ve kartların kapalı olduğu bilgisi atanır.
                     Drawable drawable = getDrawable(android.R.color.darker_gray);
                    kartlar.b[i].setBackground(drawable);
                    kartlar.kartKontrol[i] = false;

                }

            }

        }
        else { //Aktivite yeni değilse kaydedilen durumu geri yükle

            kartlar = savedInstanceState.getParcelable("karakter");

        }






        final Long baslangicSuresi=System.currentTimeMillis(); //Oyuna başlanıldığı an tutulan kronometre

        for(int i=0;i<16;i++) //Oyun içindeki kutulara tıklama anlarında uygun işlemleri yapan döngü
        {
            final Drawable arka; //Eşleşmenin yanlış olması durumunda yeniden arka renk olması için oyun moduna göre arka fotosu ayarlanır.
            if(goruntuMod)
            {
                arka  = getDrawable( R.drawable.lutfenyapmayin);
            }
            else
            {
                 arka = getDrawable( android.R.color.darker_gray);
            }



            final int id = i;

            kartlar.b[i].setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View v) {  //16 karttan herhangi birine tıklanılırsa

                    if(!kartlar.kartKontrol[id])
                    { //Karta tıklanıldığı an kart kapalıysa
                        kartlar.onPlanId = kutuFotoId(id); //Kartın ön yüzünün fotoğraf id bilgisi alınır. (Eşleşen iki kartın bu id bilgileri aynı olacaktır).
                        final Button yedek = (Button) v; //Tıklanılan buton bilgisi yedeklenir.
                        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.karartma1); //Tıklanıldığı an fotoğraf değişimi karartma animasyonuyla yapılır
                        animation.setDuration(400);  //Animasyonun çalışma süresi 400 milisaniye ayarlanır.

                        yedek.startAnimation(animation); //Animasyon uygulanır.
                        if(goruntuMod) //Görüntü mod seçiliyse kutunun fotoğraf, id bilgisine göre alınarak kutuya yerleştirilir
                        {
                            yedek.setBackground(fks(id));
                        }
                        else  //Görüntü mod kapalıysa sadece fotoğrafın açıldığını belli etmek için tüm açılan kutular aynı renge ayarlanır.
                        {
                            yedek.setBackground(getDrawable(android.R.color.holo_blue_light));
                        }


                        if (kartlar.sonKart > 0)
                        { //Daha önce açık bir kart varsa...
                            if (kartlar.sonKart == kartlar.onPlanId) //Açık kart ve şu an  açılan kartın fotoğraf id bilgileri aynı ise eşleşme bulunmuştur.
                            {
                                kartlar.sonKart = 0; //Son kart ve son kart  id bilgisi sıfırlanır
                                kartlar.sonId = 0;
                                kartlar.kartKontrol[id] = true; //Bu iki kartın acık oldukları bilgileri kalıcı olarak güncellenir
                                kartlar.kartKontrol[kartlar.sonindes] = true;
                                kartlar.hamle= kartlar.hamle+2; //Bulunan kart sayısı 2 artırılır
                            }
                            else //Son açılan kart fotoğrafları uyuşmadılarsa...
                                {
                                final Button b2 = findViewById(kartlar.sonId);
                                Handler h = new Handler(); //Kullanıcının eşleşmenin yanlış olduğunu görebilmesi için 700 milislik duraklama eklenir.
                                h.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.karartma1);
                                        animation.setDuration(400);

                                        yedek.startAnimation(animation); //Animasyonlar ile kutular yeniden arka yüz fotoğrafı ile görüntülenir
                                        b2.startAnimation(animation);
                                        b2.setBackground(arka);
                                        yedek.setBackground(arka);
                                    }
                                }, 700);

                                kartlar.kartKontrol[kartlar.sonindes]=false; //Bir önceki kartın artık kapalı olduğu bilgisi atanır.
                                kartlar.sonKart = 0; //Son kart ve son kart  id bilgisi sıfırlanır
                                kartlar.sonId = 0;
                                kartlar.hata++;  //Hatalı hamle sayısı 1 artırılır. (Skora etki edecektir.)

                            }

                        }
                        else
                            {
                            //Eğer daha önce açılmış bir kutu yoksa
                            kartlar.sonKart = kartlar.onPlanId;  //Son kartın fotoğraf id bilgisine tıklanılan fotoğrafın id bilgisi atanır.
                            kartlar.sonId = yedek.getId();  //Tıklanılan kutunun id bilgisi, son id bilgisine atanır.

                            kartlar.sonindes = id;  //Tıklanılan kutunun index bilgisi, son index bilgisine atanır.
                            kartlar.kartKontrol[id]=true; //Kart açık bilgisi atanır.
                        }

                    }
                    if(kartlar.hamle==16)  //Tüm kutular açıldıysa...
                    {
                        Long bitisSuresi=System.currentTimeMillis();  //Bitiş anı belirlenir.


                        try
                        {
                            Thread.sleep(1000); // Final ekranına geçilmesi için 1 saniye bekletilir
                        } catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                        int oyunSuresı =(int)((bitisSuresi-baslangicSuresi)/1000); //Başlangıç ve bitiş süresi arasındaki fark hesaplanır.(Saniye cinsinden)
                        fınalEkran.putExtra("süre",oyunSuresı);  //Oyun Süresi ve hata sayısı final ekranına gönderilir.
                        fınalEkran.putExtra("hata", kartlar.hata);

                        fınalEkran.putExtra("oyunModuSes", sesMod); //Kullanıcı final ekranında yeni oyun isterse aynı mod ile başlatılması için mod bilgisi final ekranına gönderilir
                        fınalEkran.putExtra("oyunModuGoruntu", goruntuMod);
                        startActivity(fınalEkran);         //Final Ekranına animasyonlu şekilde gönderilir.
                        overridePendingTransition(R.anim.solakaydir, R.anim.sagakaydir);
                        finish();


                    }
                }
            });
        }}








    MediaPlayer ses;
    public int kutuFotoId(int id)
    {//Tıklanılan kutunun fotoğraf id bilgisini döndüren fonksiyon .Ayrıca ses modu aktifse fotoğraf id bilgisine göre ses oynatır.
     //16 kutu olduğu için ve 8 tane farklı eş olduğu için mod 8 alınarak fotoğraf id bilgileri atanır.
        if (id % 8 == 0)
        {
            if(sesMod ==true) {
                if (ses != null) {  //Daha önce çalan ses varsa onu durdurarak yeni sesi oynatır
                    ses.stop();
                    ses.release();
                }
                ses = MediaPlayer.create(this, R.raw.bitanetokat);
                ses.start();
            }
            kartlar.onPlanId = R.drawable.bitanetokatatacam;
        }

        if (id % 8 == 1)
        {
            if(sesMod ==true) {
                if (ses != null) {
                    ses.stop();
                    ses.release();
                }

                ses = MediaPlayer.create(this, R.raw.ahlaksiz);
                ses.start();
            }
            kartlar.onPlanId = R.drawable.ahlakszadam;
        }

        if (id % 8 == 2)
        {
            if(sesMod ==true) {
                if (ses != null) {
                    ses.stop();
                    ses.release();
                }

                ses = MediaPlayer.create(this, R.raw.savunmadim);
                ses.start();
            }
            kartlar.onPlanId = R.drawable.savunmadm;
        }

        if (id % 8 == 3)
        {  if(sesMod ==true) {
            if (ses != null) {
                ses.stop();
                ses.release();
            }
            ses = MediaPlayer.create(this, R.raw.cikargoster);
            ses.start();
        }
            kartlar.onPlanId = R.drawable.savunmadmcikargoster;
        }

        if (id % 8 == 4)
        { if(sesMod ==true) {
            if (ses != null) {
                ses.stop();
                ses.release();
            }

            ses = MediaPlayer.create(this, R.raw.abdulhamit);
            ses.start();

        }
            kartlar.onPlanId = R.drawable.senabdulhamidisavundun;
        }

        if (id % 8 == 5)
        {  if(sesMod ==true) {
            if (ses != null) {
                ses.stop();
                ses.release();
            }

            ses = MediaPlayer.create(this, R.raw.sendoneksin);
            ses.start();
        }
            kartlar.onPlanId = R.drawable.sendoneksin;
        }

        if (id % 8 == 6)
        { if(sesMod ==true) {
            if (ses != null) {
                ses.stop();
                ses.release();
            }

            ses = MediaPlayer.create(this, R.raw.senmenderesi);
            ses.start();
        }
            kartlar.onPlanId = R.drawable.senmenderesisavundun;
        }

        if (id % 8 == 7)
        {if(sesMod ==true) {
            if (ses != null) {
                ses.stop();
                ses.release();
            }
            ses = MediaPlayer.create(this, R.raw.sikiyonetim);
            ses.start();
        }
            kartlar.onPlanId = R.drawable.skyonetmm;
        }

        return kartlar.onPlanId;
                                    }


    public Drawable fks(int id) //Parametre olarak verilen fotoğraf id bilgisine göre Drawable döndüren fonksiyon
    {

        if (id % 8 == 0)
            kartlar.onPlanId = R.drawable.bitanetokatatacam;
        if (id % 8 == 1)
            kartlar.onPlanId = R.drawable.ahlakszadam;
        if (id % 8 == 2)
            kartlar.onPlanId = R.drawable.savunmadm;
        if (id % 8 == 3)
            kartlar.onPlanId = R.drawable.savunmadmcikargoster;
        if (id % 8 == 4)
            kartlar.onPlanId = R.drawable.senabdulhamidisavundun;
        if (id % 8 == 5)
            kartlar.onPlanId = R.drawable.sendoneksin;
        if (id % 8 == 6)
            kartlar.onPlanId = R.drawable.senmenderesisavundun;
        if (id % 8 == 7)
            kartlar.onPlanId = R.drawable.skyonetmm;


        @SuppressLint("RestrictedApi") Drawable on = AppCompatDrawableManager.get().getDrawable(this, kartlar.onPlanId);
        return on;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) { //Programı geçici olarak durdurma durumunda kartların son bilgileri "karakter" anahtarı ile kaydedilir
        super.onSaveInstanceState(outState);
        outState.putParcelable("karakter", kartlar);
    }



}







