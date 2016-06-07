package com.eduluz.produtos;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements MainFragment.OnFragmentInteractionListener {
    private Realm realm;
    private TextView scanBar;
    private Button listar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
        }
        scanBar = (TextView) findViewById(R.id.scan_bar);

        findViewById(R.id.do_listar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                ListFragment listFragment = new ListFragment();
                listFragment.setArguments(getIntent().getExtras());
                transaction.replace(R.id.fragment_container, listFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        findViewById(R.id.do_cadastrar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                MainFragment mainFragment = new MainFragment();
                mainFragment.setArguments(getIntent().getExtras());
                transaction.replace(R.id.fragment_container, mainFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        realm.close();
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
//        super.onActivityResult(requestCode, resultCode, intent);
//        System.out.println("the code is catch");
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        String scanData = (scanningResult != null) ? scanningResult.getContents() : "";

        if (scanData == null || scanData.isEmpty()){
            scanBar.setText("No data");
        } else {
            scanBar.setText(scanData);
        }
    }
}
