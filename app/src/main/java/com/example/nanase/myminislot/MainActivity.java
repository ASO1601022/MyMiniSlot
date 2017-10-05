package com.example.nanase.myminislot;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        int haveCoin = pref.getInt("HAVE_COIN", 1000);
        int betCoin = pref.getInt("BET_COIN", 10);
        TextView have = (TextView) findViewById(R.id.have_coin);
        TextView bet = (TextView) findViewById(R.id.bet_coin);
        String h = String.valueOf(haveCoin);
        String b = String.valueOf(betCoin);
        have.setText(h);
        bet.setText(b);

        Button betCoinUp = (Button)findViewById(R.id.bet_coin_up);
        betCoinUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                String text = onBetCoinUpTapped();
                TextView bet = (TextView) findViewById(R.id.bet_coin);
                bet.setText(text);
            }
        });
        Button betCoinDown = (Button) findViewById(R.id.bet_coin_down);
        betCoinDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = onBetCoinDownTapped();
                TextView bet = (TextView) findViewById(R.id.bet_coin);
                bet.setText(text);
            }
        });
        Button resetButton = (Button) findViewById(R.id.reset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = onResetButtonTapped();
                TextView hc = (TextView) findViewById(R.id.have_coin);
                hc.setText(text);
            }
        });

    }

    public void onStartButtonTapped(View view){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        int haveCoin = pref.getInt("HAVE_COIN", 1000);
        if(haveCoin > 0){
            Intent intent = new Intent(this,ResultActivity.class);
            int resultCode = 39;
            startActivityForResult(intent, resultCode);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        if(resultCode == RESULT_OK){
            TextView haveCoin = (TextView) findViewById(R.id.have_coin);
            int have = intent.getIntExtra("HAVE_COIN", 1000);
            String h = String.valueOf(have);
            haveCoin.setText(h);
        }
    }

    public String onBetCoinUpTapped(){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = pref.edit();
        int haveCoin = pref.getInt("HAVE_COIN", 1000);
        int betCoin = pref.getInt("BET_COIN", 10);
        if(haveCoin > betCoin){
            betCoin = betCoin + 10;
            editor.putInt("BET_COIN", betCoin);
        }
        editor.commit();
        String b = String.valueOf(betCoin);
        String ret = b;
        return ret;
    }
    public String onBetCoinDownTapped(){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = pref.edit();
        int betCoin = pref.getInt("BET_COIN", 10);
        if(betCoin > 10){
            betCoin = betCoin - 10;
            editor.putInt("BET_COIN", betCoin);
        }
        editor.commit();
        String b = String.valueOf(betCoin);
        String ret = b;
        return ret;
    }
    public String onResetButtonTapped(){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("HAVE_COIN", 1000);
        editor.commit();
        int have = pref.getInt("HAVE_COIN", 1000);
        String h = String.valueOf(have);
        return h;
    }
}
