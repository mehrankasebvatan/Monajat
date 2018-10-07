package app.mehran.monajat.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import app.mehran.monajat.R;
import app.mehran.monajat.adapters.DoaAdapter;
import app.mehran.monajat.models.DoaModel;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class AmirActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private ImageView imageView ;
    private TextView textView , persian;
    private DoaAdapter amirRVAdapter ;
    private ArrayList<DoaModel> amirRVArrayList ;
    private MediaPlayer mediaPlayer ;
    private FloatingActionButton play;
    LinearLayoutManager layoutManager;
    private TextView time_txt;
    private SeekBar seekBar;
    private Timer timer ;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amir);

        recyclerView = findViewById(R.id.Doa_View);
        persian = findViewById(R.id.Persian);
        textView = findViewById(R.id.Text_toolbar);
        textView.setText(R.string.Amir);
        imageView = findViewById(R.id.Img_toolbar);
        imageView.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.back,null));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(AmirActivity.this,MainActivity.class);
                startActivity(back);
                finish();
                mediaPlayer.stop();
            }
        });



        mediaPlayer = MediaPlayer.create(AmirActivity.this, R.raw.amir);
        play = findViewById(R.id.Play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    play.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.play,null));
                }else {
                    mediaPlayer.start();
                    play.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.pause,null));
                }

            }
        });


        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                play.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.play,null));
                mediaPlayer.seekTo(0);
            }
        });

        time_txt = findViewById(R.id.Time);
        seekBar = findViewById(R.id.SeekBar);
        seekBar.setMax(mediaPlayer.getDuration());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
              if(fromUser) {
                  mediaPlayer.seekTo(progress);
              }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        timer = new Timer();
        timer.schedule(new MainTimer(),0,1000);











        findviewById();





    }

    private class MainTimer extends TimerTask{

        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    time_txt.setText(formatDuration(Long.valueOf(mediaPlayer.getCurrentPosition())));
                }
            });
        }
    }




    private void setListValues() {

        amirRVArrayList = new ArrayList<DoaModel>();
        amirRVArrayList.add(new DoaModel("بِسْمِ اللهِ الرَّحْمنِ الرَّحِیم" , "1", "به نام خداوند بخشنده مهربان"));
        amirRVArrayList.add(new DoaModel("اَللَّهُمَّ إِنِّی أَسْأَلُکَ الْأَمَانَ یَوْمَ لاَ یَنْفَعُ مَالٌ وَ لاَ بَنُونَ إِلاَّ مَنْ أَتَى اللَّهَ بِقَلْبٍ سَلِیمٍ" , "1", "پروردگارا من از تو درخواست ایمنى مى\u200F کنم آن روز سختى که مال و فرزند هیچ نفع نبخشد و چیزى جز آنکه با قلب پاک و سالم حضور خدا آید سود ندهد"));
        amirRVArrayList.add(new DoaModel("وَ أَسْأَلُکَ الْأَمَانَ یَوْمَ یَعَضُّ الظَّالِمُ عَلَى یَدَیْهِ یَقُولُ (یَا لَیْتَنِی اتَّخَذْتُ مَعَ الرَّسُولِ سَبِیلاً)" , "1", "اى خدا من از تو درخواست ایمنى مى\u200F کنم آن روز سختى که ظالم از پشیمانى و حسرت انگشت به دندان مى\u200F خاید و مى \u200Fگوید «اى کاش من با رسول حق راه طاعت پیش مى\u200F گرفتم»"));
        amirRVArrayList.add(new DoaModel("وَ أَسْأَلُکَ الْأَمَانَ یَوْمَ یُعْرَفُ الْمُجْرِمُونَ بِسِیمَاهُمْ فَیُؤْخَذُ بِالنَّوَاصِی وَ الْأَقْدَامِ" , "1", "و از تو درخواست ایمنى مى\u200Fکنم آن روز سختى که گنهکاران به سیمایشان شناخته مى\u200Fشوند که پس موى پیشانى آنها را با پایهاشان بگیرند"));
        amirRVArrayList.add(new DoaModel("وَ أَسْأَلُکَ الْأَمَانَ یَوْمَ لاَ یَجْزِی وَالِدٌ عَنْ وَلَدِهِ وَ لاَ مَوْلُودٌ هُوَ جَازٍ عَنْ وَالِدِهِ شَیْئًا إِنَّ وَعْدَ اللَّهِ حَقٌ" , "1", "و از تو درخواست ایمنى مى\u200Fکنم در روز سختى که نه پدرى به جاى فرزند و نه فرزندى به جاى پدر جزا و کیفر شود و البته آن روز وعده خدا حق و حقیقت است"));
        amirRVArrayList.add(new DoaModel("وَ أَسْأَلُکَ الْأَمَانَ یَوْمَ لاَ یَنْفَعُ الظَّالِمِینَ مَعْذِرَتُهُمْ وَ لَهُمُ اللَّعْنَهُ وَ لَهُمْ سُوءُ الدَّارِ" , "1", "و از تو درخواست ایمنى مى\u200Fکنم در روز سختى که مردم ظالم ستمگر را عذرخواهى سود نبخشد و بر آنان لعن و منزلگاه بد است"));
        amirRVArrayList.add(new DoaModel("وَ أَسْأَلُکَ الْأَمَانَ یَوْمَ لاَ تَمْلِکُ نَفْسٌ لِنَفْسٍ شَیْئًا وَ الْأَمْرُ یَوْمَئِذٍ لِلَّهِ" , "1", "و از تو درخواست ایمنى مى\u200Fکنم در روز سختى که هیچکس را قدرت بر کس دیگر نیست و فرمان در آن روز خاص خداست"));
        amirRVArrayList.add(new DoaModel("وَ أَسْأَلُکَ الْأَمَانَ یَوْمَ یَفِرُّ الْمَرْءُ مِنْ أَخِیهِ وَ أُمِّهِ وَ أَبِیهِ وَ صَاحِبَتِهِ وَ بَنِیهِ" , "1", "و از تو درخواست ایمنى مى\u200Fکنم در روز سختى\u200Fکه هر شخص از برادر و مادر و پدر و زن و فرزندانش مى\u200Fگریزد"));
        amirRVArrayList.add(new DoaModel("لِکُلِّ امْرِى\u200Fءٍ مِنْهُمْ یَوْمَئِذٍ شَأْنٌ یُغْنِیهِ" , "1", "که هر کس در آن روز توجه به کار خویش از غیرش بى\u200Fنیاز دارد"));
        amirRVArrayList.add(new DoaModel("وَ أَسْأَلُکَ الْأَمَانَ یَوْمَ یَوَدُّ الْمُجْرِمُ لَوْ یَفْتَدِی مِنْ عَذَابِ یَوْمِئِذٍ بِبَنِیهِ وَ صَاحِبَتِهِ وَ أَخِیهِ" , "1", "و از تو درخواست ایمنى مى\u200Fکنم در روزى که کافر بدکار آرزو کند که اى کاش توانستى فرزندانش را فداى خود سازد و از عذاب برهد و هم زن و برادر"));
        amirRVArrayList.add(new DoaModel("وَ فَصِیلَتِهِ الَّتِی تُؤْوِیهِ وَ مَنْ فِی الْأَرْضِ جَمِیعاً ثُمَّ یُنْجِیهِ کَلاَّ إِنَّهَا لَظَى نَزَّاعَهً لِلشَّوَى" , "1", "و قبیله\u200Fاش که همیشه بحمایتش برمى\u200Fخواستند و هر که در روى زمین است همه را فداى خود گرداند تا از عذاب نجات یابد و هرگز نجات نخواهد یافت که آتش دوزخ بر او شعله\u200Fور است تا سر و صورت و اندامش پاک بسوزد"));
        amirRVArrayList.add(new DoaModel("مَوْلاَیَ یَا مَوْلاَیَ أَنْتَ الْمَوْلَى وَ أَنَا الْعَبْدُ وَ هَلْ یَرْحَمُ الْعَبْدَ إِلاَّ الْمَوْلَى" , "1", "اى آقاى من اى آقاى من تویى مولاى من و من بنده توام و آیا در حق بنده جز مولایش که ترحم خواهد کرد؟"));
        amirRVArrayList.add(new DoaModel("مَوْلاَیَ یَا مَوْلاَیَ أَنْتَ الْمَالِکُ وَ أَنَا الْمَمْلُوکُ وَ هَلْ یَرْحَمُ الْمَمْلُوکَ إِلاَّ الْمَالِکُ\u200F" , "1", "اى آقاى من اى آقاى من تویى مالک وجود من و من مملوک توام و آیا در حق مملوک جز مالکش که ترحم خواهد کرد؟"));
        amirRVArrayList.add(new DoaModel("مَوْلاَیَ یَا مَوْلاَیَ أَنْتَ الْعَزِیزُ وَ أَنَا الذَّلِیلُ وَ هَلْ یَرْحَمُ الذَّلِیلَ إِلاَّ الْعَزِیزُ" , "1", "اى آقاى من اى آقاى من تویى با عزت و اقتدار و من بنده ذلیلم و آیا در حق شخص ذلیل جز ذات با عزت و اقتدار که ترحم خواهد کرد؟"));
        amirRVArrayList.add(new DoaModel("مَوْلاَیَ یَا مَوْلاَیَ أَنْتَ الْخَالِقُ وَ أَنَا الْمَخْلُوقُ وَ هَلْ یَرْحَمُ الْمَخْلُوقَ إِلاَّ الْخَالِقُ" , "1", "اى آقاى من اى آقاى من تویى آفریننده من و من مخلوق توام و آیا در حق مخلوق جز آفریننده او که ترحم خواهد کرد؟"));
        amirRVArrayList.add(new DoaModel("مَوْلاَیَ یَا مَوْلاَیَ أَنْتَ الْعَظِیمُ وَ أَنَا الْحَقِیرُ وَ هَلْ یَرْحَمُ الْحَقِیرَ إِلاَّ الْعَظِیمُ" , "1", "اى آقاى من اى آقاى من تویى خداى بزرگ و من بنده حقیر ناچیز و آیا در حق بنده ناچیزى جز خداى بزرگ که ترحم خواهد کرد؟"));
        amirRVArrayList.add(new DoaModel("مَوْلاَیَ یَا مَوْلاَیَ أَنْتَ الْقَوِیُّ وَ أَنَا الضَّعِیفُ وَ هَلْ یَرْحَمُ الضَّعِیفَ إِلاَّ الْقَوِیُ\u200F" , "1", "اى آقاى من اى آقاى من تویى قوى و توانا و من ضعیف و ناتوان و آیا در حق ضعیفى ناتوان جز شخص قوى توانا که ترحم خواهد کرد؟"));
        amirRVArrayList.add(new DoaModel("مَوْلاَیَ یَا مَوْلاَیَ أَنْتَ الْغَنِیُّ وَ أَنَا الْفَقِیرُ وَ هَلْ یَرْحَمُ الْفَقِیرَ إِلاَّ الْغَنِیُ" , "1", "اى آقاى من اى آقاى من تویى بى\u200Fنیاز و من فقیر و آیا در حق فقیرى محتاج جز غنى بى\u200Fنیاز که ترحم خواهد کرد؟"));
        amirRVArrayList.add(new DoaModel("مَوْلاَیَ یَا مَوْلاَیَ أَنْتَ الْمُعْطِی وَ أَنَا السَّائِلُ وَ هَلْ یَرْحَمُ السَّائِلَ إِلاَّ الْمُعْطِی" , "1", "اى آقاى من اى آقاى من تویى عطا بخش سائلان و من به درگاهت سائلم و آیا در حق سائل جز عطا بخشنده که ترحم خواهد کرد؟"));
        amirRVArrayList.add(new DoaModel("مَوْلاَیَ یَا مَوْلاَیَ أَنْتَ الْحَیُّ وَ أَنَا الْمَیِّتُ وَ هَلْ یَرْحَمُ الْمَیِّتَ إِلاَّ الْحَیُ" , "1", "اى آقاى من اى آقاى من تویى زنده ابدى و من مرده بى\u200Fروح و آیا در حق مرده\u200Fاى بى\u200Fروح جز زنده ابدى که ترحم خواهد کرد؟"));
        amirRVArrayList.add(new DoaModel("مَوْلاَیَ یَا مَوْلاَیَ أَنْتَ الْبَاقِی وَ أَنَا الْفَانِی وَ هَلْ یَرْحَمُ الْفَانِیَ إِلاَّ الْبَاقِی\u200F" , "1", "اى آقاى من اى آقاى من تویى وجود باقى و من مخلوقى تباه و فانى و نابودم و آیا در حق فانى نابود شدنى جز ذات باقى سرمدى که ترحم خواهد کرد؟"));
        amirRVArrayList.add(new DoaModel("مَوْلاَیَ یَا مَوْلاَیَ أَنْتَ الدَّائِمُ وَ أَنَا الزَّائِلُ وَ هَلْ یَرْحَمُ الزَّائِلَ إِلاَّ الدَّائِمُ" , "1", "اى آقاى من اى آقاى من تویى موجود دایم ازلى و من موجودى زوال پذیر و آیا در حق موجودى زوال پذیر جز ذات دایم ازلى که ترحم خواهد کرد؟"));
        amirRVArrayList.add(new DoaModel("مَوْلاَیَ یَا مَوْلاَیَ أَنْتَ الرَّازِقُ وَ أَنَا الْمَرْزُوقُ وَ هَلْ یَرْحَمُ الْمَرْزُوقَ إِلاَّ الرَّازِقُ\u200F" , "1", "اى آقاى من اى آقاى من تویى روزى دهنده خلق و من روزى خواهم و آیا در حق روزى\u200Fخواهان جز رازق و روزى دهنده خلق که ترحم خواهد کرد؟"));
        amirRVArrayList.add(new DoaModel("مَوْلاَیَ یَا مَوْلاَیَ أَنْتَ الْجَوَادُ وَ أَنَا الْبَخِیلُ وَ هَلْ یَرْحَمُ الْبَخِیلَ إِلاَّ الْجَوَادُ" , "1", "اى آقاى من اى آقاى من تویى صاحب جود و احسان و من بخیل و آیا در حق بخیل جز شخص با جود و احسان که ترحم خواهد کرد؟"));
        amirRVArrayList.add(new DoaModel("مَوْلاَیَ یَا مَوْلاَیَ أَنْتَ الْمُعَافِی وَ أَنَا الْمُبْتَلَى وَ هَلْ یَرْحَمُ الْمُبْتَلَى إِلاَّ الْمُعَافِی" , "1", "اى آقاى من اى آقاى من تویى عافیت بخش و شفا بخش و من مبتلا و آیا در حق مبتلا جز عافیت و شفا بخش که ترحم خواهد کرد؟"));
        amirRVArrayList.add(new DoaModel("مَوْلاَیَ یَا مَوْلاَیَ أَنْتَ الْکَبِیرُ وَ أَنَا الصَّغِیرُ وَ هَلْ یَرْحَمُ الصَّغِیرَ إِلاَّ الْکَبِیرُ" , "1", "اى آقاى من اى آقاى من تویى خداى بزرگ و من بنده کوچک و آیا در حق بنده صغیرى جز خداى بزرگ که ترحم خواهد کرد؟"));
        amirRVArrayList.add(new DoaModel("مَوْلاَیَ یَا مَوْلاَیَ أَنْتَ الْهَادِی وَ أَنَا الضَّالُّ وَ هَلْ یَرْحَمُ الضَّالَّ إِلاَّ الْهَادِی" , "1", "اى آقاى من اى آقاى من تویى رهنماى خلق و من بنده گمراه و آیا در حق بنده گمراه جز رهنماى عالم که ترحم خواهد کرد؟"));
        amirRVArrayList.add(new DoaModel("مَوْلاَیَ یَا مَوْلاَیَ أَنْتَ الرَّحْمَنُ وَ أَنَا الْمَرْحُومُ وَ هَلْ یَرْحَمُ الْمَرْحُومَ إِلاَّ الرَّحْمَنُ" , "1", "اى آقاى من اى آقاى من تویى خداى بخشاینده و من بنده قابل ترحم و بخشش و آیا در حق بنده قابل بخشش جز خداى بخشاینده که ترحم خواهد کرد؟"));
        amirRVArrayList.add(new DoaModel("مَوْلاَیَ یَا مَوْلاَیَ أَنْتَ السُّلْطَانُ وَ أَنَا الْمُمْتَحَنُ وَ هَلْ یَرْحَمُ الْمُمْتَحَنَ إِلاَّ السُّلْطَانُ" , "1", "اى آقاى من اى آقاى من تویى سلطان و من بنده امتحان شده و آیا در حق بنده امتحان شده جز سلطان عالم که ترحم خواهد کرد؟"));
        amirRVArrayList.add(new DoaModel("مَوْلاَیَ یَا مَوْلاَیَ أَنْتَ الدَّلِیلُ وَ أَنَا الْمُتَحَیِّرُ وَ هَلْ یَرْحَمُ الْمُتَحَیِّرَ إِلاَّ الدَّلِیلُ" , "1", "اى آقاى من اى آقاى من تویى رهبر و دلیل و من متحیر و سرگردان و آیا در حق متحیر سرگردان جز دلیل و رهبر که ترحم خواهد کرد؟"));
        amirRVArrayList.add(new DoaModel("مَوْلاَیَ یَا مَوْلاَیَ أَنْتَ الْغَفُورُ وَ أَنَا الْمُذْنِبُ وَ هَلْ یَرْحَمُ الْمُذْنِبَ إِلاَّ الْغَفُورُ" , "1", "اى آقاى من اى آقاى من تویى خداى غفور آمرزنده و من بنده گنهکار و آیا در حق بنده گنهکار جز خداى غفور که ترحم خواهد کرد؟"));
        amirRVArrayList.add(new DoaModel("مَوْلاَیَ یَا مَوْلاَیَ أَنْتَ الْغَالِبُ وَ أَنَا الْمَغْلُوبُ وَ هَلْ یَرْحَمُ الْمَغْلُوبَ إِلاَّ الْغَالِبُ" , "1", "اى آقاى من اى آقاى من تویى خداى غالب و قاهر و من بنده عاجز مغلوب و آیا در حق بنده مغلوب مقهور جز خداى غالب قاهر که ترحم خواهد کرد؟"));
        amirRVArrayList.add(new DoaModel("مَوْلاَیَ یَا مَوْلاَیَ أَنْتَ الرَّبُّ وَ أَنَا الْمَرْبُوبُ وَ هَلْ یَرْحَمُ الْمَرْبُوبَ إِلاَّ الرَّبُ" , "1", "اى آقاى من اى آقاى من تویى پروردگار پرورنده خلق و من\u200Fمربوب و پرورش یافته توام و آیا در حق مربوبى جز آنکه پروردگار اوست که ترحم خواهد کرد؟"));
        amirRVArrayList.add(new DoaModel("مَوْلاَیَ یَا مَوْلاَیَ أَنْتَ الْمُتَکَبِّرُ وَ أَنَا الْخَاشِعُ وَ هَلْ یَرْحَمُ الْخَاشِعَ إِلاَّ الْمُتَکَبِّرُ" , "1", "اى آقاى من اى آقاى من تویى خداى با کبریا و بزرگى و من بنده فروتن و عاجز و آیا در حق بنده عاجز فروتن جز خداى باکبریاى بزرگ که ترحم خواهد کرد؟"));
        amirRVArrayList.add(new DoaModel("مَوْلاَیَ یَا مَوْلاَیَ ارْحَمْنِی بِرَحْمَتِکَ وَ ارْضَ عَنِّی بِجُودِکَ وَ کَرَمِکَ وَ فَضْلِکَ" , "1", "اى آقاى من اى آقاى من برحمتت ترحم کن و به جود و کرمت و فضل و احسانت از من راضى و خشنود باش"));
        amirRVArrayList.add(new DoaModel("یَا ذَا الْجُودِ وَ الْإِحْسَانِ وَ الطَّوْلِ وَ الاِمْتِنَانِ بِرَحْمَتِکَ یَا أَرْحَمَ الرَّاحِمِینَ" , "1", "اى خداى صاحب جود و احسان و فضل و نعمت به حق رحمتت اى مهربانترین مهربانان عالم"));






        amirRVAdapter = new DoaAdapter(AmirActivity.this,amirRVArrayList);
    final LinearLayoutManager layoutManager = new LinearLayoutManager(AmirActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(amirRVAdapter);

}

    private void findviewById(){
        layoutManager = new LinearLayoutManager(AmirActivity.this);


    }
    @Override
    public void onResume() {
        super.onResume();
        setListValues();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent ba = new Intent(AmirActivity.this, MainActivity.class);
        startActivity(ba);
        finish();
        mediaPlayer.stop();
    }


    private String formatDuration(Long duration){
        int seconds = (int) (duration/1000);
        int minutes = seconds/60;
        seconds%=60;
        return String.format(Locale.ENGLISH, "%02d",minutes)+":"+String.format(Locale.ENGLISH, "%02d",seconds);

    }


    @Override
    protected void onDestroy() {
       mediaPlayer.release();
       timer.purge();
       timer.cancel();
        super.onDestroy();
    }
}
