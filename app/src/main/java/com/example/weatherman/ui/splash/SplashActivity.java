package com.example.weatherman.ui.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.example.weatherman.R;
import com.example.weatherman.ui.base.ViewModelFactory;
import com.example.weatherman.ui.mainmenu.MainActivity;
import com.example.weatherman.ui.tutorial.TutorialActivity;

public class SplashActivity extends AppCompatActivity {

    SplashViewModel splashViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewModelFactory factory = new ViewModelFactory(getApplication());
        splashViewModel = new ViewModelProvider(this,factory).get(SplashViewModel.class);

        splashViewModel.getFirstRun().observe(this, flag -> {
            if (flag) {
                loadActivity(TutorialActivity.class);
            } else {
                loadActivity(MainActivity.class);
            }

        });
    }

    private void loadActivity(Class classz) {
        startActivity(new Intent(SplashActivity.this, classz));
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        splashViewModel.checkFirstRun();
    }
}

