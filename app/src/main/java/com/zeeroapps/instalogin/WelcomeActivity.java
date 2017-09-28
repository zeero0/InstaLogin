package com.zeeroapps.instalogin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import static com.zeeroapps.instalogin.CONSTANTS.SP;
import static com.zeeroapps.instalogin.CONSTANTS.SP_DP;
import static com.zeeroapps.instalogin.CONSTANTS.SP_NAME;
import static com.zeeroapps.instalogin.CONSTANTS.SP_TOKEN;

public class WelcomeActivity extends AppCompatActivity {

    SharedPreferences spUser;

    ImageView ivProfile;
    TextView tvName;

    String name, dp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        tvName = (TextView) findViewById(R.id.tv_full_name);
        ivProfile = (ImageView) findViewById(R.id.iv_dp);

        spUser = getSharedPreferences(SP, MODE_PRIVATE);
        name = spUser.getString(SP_NAME, null);
        dp = spUser.getString(SP_DP, null);

        if (name != null){
            tvName.setText(name);
            Glide.with(this).load(dp).into(ivProfile);
        }
    }

    public void onClickLogout(View v){

        CookieSyncManager.createInstance(getApplicationContext());
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();

        spUser.edit().putString(SP_TOKEN, null).commit();
        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
        finish();
    }
}
