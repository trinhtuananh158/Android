package com.example.trinhtuananh.myapplication;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by trinhtuananh on 5/18/2016.
 */
public class MyAsyncTask extends AsyncTask<Void,Integer,Void> {
    Activity MyActivity;
    int num;
    public MyAsyncTask(Activity context)
    {
        MyActivity=context;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(MyActivity,"onPreEx ",Toast.LENGTH_LONG).show();
        EditText edtTime = (EditText) MyActivity.findViewById(R.id.edtTime);
        if(edtTime.getText().toString().equals("")){
            Toast.makeText(MyActivity,"Please in put time to countdown",Toast.LENGTH_LONG).show();
        }
        else {
            num = Integer.parseInt(edtTime.getText().toString());
        }
    }

    @Override
    protected Void doInBackground(Void... params) {

        for(int i=num;i>=0;i--){
            SystemClock.sleep(1000);
            publishProgress(i);
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        TextView tvTime =(TextView) MyActivity.findViewById(R.id.tvTime);
        tvTime.setText(values[0]+"");
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Toast.makeText(MyActivity,"Done!",Toast.LENGTH_LONG).show();
    }
}
