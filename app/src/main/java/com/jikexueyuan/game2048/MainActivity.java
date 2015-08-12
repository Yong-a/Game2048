package com.jikexueyuan.game2048;

import android.app.Activity;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity implements View.OnClickListener {

    public static MainActivity mainActivity = null;
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private TextView tvScore;
    private TextView tvHighScore;
    private Button reGame;
    private Button music;
    private Button silence;
    private GameView gameView;
    public int score = 0;
    public int highScore = 0;

    public MainActivity() {
        mainActivity = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvScore = (TextView) findViewById(R.id.tvScore);
        tvHighScore = (TextView) findViewById(R.id.tvHighScore);
        reGame = (Button) findViewById(R.id.restart);
        music = (Button) findViewById(R.id.music);
        silence = (Button) findViewById(R.id.silence);
        gameView = (GameView) findViewById(R.id.game_view);
        isVoice();
        reGame.setOnClickListener(this);
        music.setOnClickListener(this);
        silence.setOnClickListener(this);
    }

    private void isVoice() {
        if (getMusic()) {
            music.setVisibility(View.VISIBLE);
            silence.setVisibility(View.GONE);
        } else {
            music.setVisibility(View.GONE);
            silence.setVisibility(View.VISIBLE);
        }
    }

    public void clearScore() {
        score = 0;
        showScore();
    }

    public void showScore() {
        tvScore.setText(score + "");
        tvHighScore.setText(highScore + "");
    }

    public void addHighScore(int hs) {
        highScore += hs;
        showScore();
    }

    public void addScore(int s) {
        score += s;
        highScore = getHighScore();
        if (score >= highScore) {
            highScore = score;
            recordHighScore();
        } else {
            highScore = getHighScore();
        }
        showScore();
    }

    public static MainActivity getMainActivity() {
        return mainActivity;
    }

    public int getScore() {
        return score;
    }

    private void recordHighScore() {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
        editor.putInt("HighScore", highScore);
        editor.commit();
    }

    private int getHighScore() {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        return pref.getInt("HighScore", 0);
    }

    private boolean getMusic() {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        return pref.getBoolean("voice", true);
    }

    public void getVoice(int i) {
        if (getMusic()) {
            switch (i) {
                case 1:
                    if (mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    mediaPlayer = MediaPlayer.create(this, R.raw.one_1);
                    mediaPlayer.start();
                    break;
                case 2:
                    if (mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    mediaPlayer = MediaPlayer.create(this, R.raw.two_1);
                    mediaPlayer.start();
                    break;
                case 3:
                    if (mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    mediaPlayer = MediaPlayer.create(this, R.raw.three_1);
                    mediaPlayer.start();
                    break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
        switch (v.getId()) {
            case R.id.restart:
                gameView.startGame();
                break;
            case R.id.music:
                if (music.getVisibility() == View.VISIBLE) {
                    music.setVisibility(View.GONE);
                    silence.setVisibility(View.VISIBLE);
                    editor.putBoolean("voice", false);
                    editor.commit();
                }
                break;
            case R.id.silence:
                if (silence.getVisibility() == View.VISIBLE) {
                    silence.setVisibility(View.GONE);
                    music.setVisibility(View.VISIBLE);
                    editor.putBoolean("voice", true);
                    editor.commit();
                }
        }
    }
}
