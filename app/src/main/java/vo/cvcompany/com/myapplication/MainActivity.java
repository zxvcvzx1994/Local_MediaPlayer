package vo.cvcompany.com.myapplication;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vo.cvcompany.com.myapplication.Module.Mp3;

public class MainActivity extends AppCompatActivity {
    private static final String TAG ="12121212" ;
    @BindView(R.id.txtfrom)
    TextView txtFrom;
    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.txtto)
    TextView txtTo;
    @BindView(R.id.imgPause)
    ImageView imgPause;
    private int position  =0;
    private ArrayList<Mp3> arrayList;
    private MediaPlayer mediaPlayer;
    @BindView(R.id.seekbar)
    SeekBar seekBarSong;
    private Animation animation;
    @BindView(R.id.imgDisc)
    ImageView imgDisc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        animation = AnimationUtils.loadAnimation(this, R.anim.disc);
        arrayList = new ArrayList<Mp3>();
        arrayList.add(new Mp3("Noi tinh yeu",R.raw.neuluctruocemdungtoi));
        khoitaoMediaplayer();

        seekBarSong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser)
                mediaPlayer.seekTo(progress);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void updateFrom(){
        final Handler handler = new Handler();
     final    SimpleDateFormat simpleDateFormat =new SimpleDateFormat("mm:ss");
    Runnable run = new Runnable() {
        @Override
        public void run() {
            if(mediaPlayer!=null)
            {
             try {
                 seekBarSong.setProgress(mediaPlayer.getCurrentPosition());
                 txtFrom.setText("" + simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
             }catch (Exception e){

             }
                handler.postDelayed(this, 500);
            }
        }
    };
    handler.postDelayed(run, 100);
    }

    private void khoitaoMediaplayer(){
        mediaPlayer = MediaPlayer.create(this,arrayList.get(position).getLink());
        txtTitle.setText(arrayList.get(0).getTitle());
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    private boolean checkBoolean(){
        try {
            mediaPlayer.isPlaying();
           return true;
        }catch (Exception e){
                return false;
        }
    }

    @OnClick(R.id.imgPause)
    public void imgPause()   {
        try {
            if (mediaPlayer.isPlaying()) {
                Log.i(TAG, "imgPause: 111");
                mediaPlayer.pause();
                imgPause.setImageResource(R.drawable.ic_start_button);
                imgDisc.clearAnimation();
            } else {

                mediaPlayer.start();
                imgPause.setImageResource(R.drawable.ic_rounded_pause_button);
                imgDisc.setAnimation(animation);
            }
            setTotalTime();
            updateFrom();
        }catch (Exception e){
            khoitaoMediaplayer();
            mediaPlayer.start();
            imgPause.setImageResource(R.drawable.ic_rounded_pause_button);
            imgDisc.setAnimation(animation);
        }



    }

    public void setTotalTime(){
        seekBarSong.setMax(mediaPlayer.getDuration());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txtTo.setText(""+simpleDateFormat.format(mediaPlayer.getDuration()));
    }

    @OnClick(R.id.imgStop)
    public void imgStop(){
        try {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                mediaPlayer.stop();
                mediaPlayer.reset();
                mediaPlayer.release();
                imgPause.setImageResource(R.drawable.ic_start_button);
                imgDisc.clearAnimation();

            }
        }catch (Exception e){

        }
    }

    @OnClick(R.id.imgNext)
    public void imgNext(){
        try {
            position++;
            if (position > arrayList.size() - 1) {
                position = 0;
            }
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            khoitaoMediaplayer();
            mediaPlayer.start();
            imgPause.setImageResource(R.drawable.ic_rounded_pause_button);
            setTotalTime();
        }catch (Exception e){

        }
    }

    @OnClick(R.id.imgBack)
    public void imgBacj(){
        try {
            position--;
            if (position == -1) {
                position = arrayList.size() - 1;
            }
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            khoitaoMediaplayer();
            mediaPlayer.start();
            imgPause.setImageResource(R.drawable.ic_rounded_pause_button);
            setTotalTime();
        }catch (Exception e){

        }
    }


}
