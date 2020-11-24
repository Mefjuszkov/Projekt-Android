package com.example.zaliczenie;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    MediaPlayer odtwarzacz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void play(View v) {
        //tworze odtwarzacz w tej metodzie aby uruchamial sie po wcisnieciu przycisku a nie po uruchomieniu aplikacji
        if (odtwarzacz == null) { //sprawdzam czy odtwarzacz zostal juz stworzony
            odtwarzacz = MediaPlayer.create(this, R.raw.masno);
            odtwarzacz.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // kiedy utwor sie skonczy chcemy przerwac odtwarzacz i zwolnic zasoby
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopodtwarzacz();
                }
            });
        }

        odtwarzacz.start();
    }
    public void pause(View v) {
        if (odtwarzacz != null){
            odtwarzacz.pause();
        }
    }

    public void stop(View v) {
        stopodtwarzacz();
    }

    private void stopodtwarzacz() {
        if (odtwarzacz !=null){
            odtwarzacz.release(); // zwalniamy zasoby i zmieniamy wartosc odtwarzacza na null, co go wylacza
            odtwarzacz = null;
        }
    }

    @Override
    protected void onStop() { // po wylaczeniu aplikacji odwtarzacz sie zatrzyma i zwolni zasoby
        super.onStop();
        stopodtwarzacz();
    }
}