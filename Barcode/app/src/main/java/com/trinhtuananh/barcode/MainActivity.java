package com.trinhtuananh.barcode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.btnScan)
    Button btnScan;
    @Bind(R.id.tvContent)
    TextView tvContent;
    @Bind(R.id.tvFormat)
    TextView tvFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnScan)
    void onClick() {
        IntentIntegrator scanIntegrator = new IntentIntegrator(MainActivity.this);
        scanIntegrator.initiateScan();
        /*Intent intent = new Intent("com.google.zxing.client.android.SCAN");
        intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
        startActivityForResult(intent, 0);
        Toast.makeText(this, "Scan click", Toast.LENGTH_SHORT).show();*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if(scanningResult!=null){
            String scanContent = scanningResult.getContents();
            String scanFormat= scanningResult.getFormatName();
            tvContent.setText("Content "+scanContent);
            tvFormat.setText("Format "+scanFormat);
        }
        else{
            Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT).show();
        }
        /*if (requestCode == 0) {
            if (resultCode == RESULT_OK) {

                String contents = intent.getStringExtra("SCAN_RESULT");
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                tvContent.setText(contents);
                tvFormat.setText(format);
                // Handle successful scan

            } else if (resultCode == RESULT_CANCELED) {
                // Handle cancel
                Log.i("App", "Scan unsuccessful");
            }
        }*/
    }
}
