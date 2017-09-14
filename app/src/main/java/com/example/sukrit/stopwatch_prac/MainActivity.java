package com.example.sukrit.stopwatch_prac;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnstart;
    Button btnstop;
    Button btnpause;
    Button btncontinue;
    long initialTime;
    TextView milliSec;
    TextView sec;
    TextView min;
    TextView hr;

    int setSec=0;
    int setMin=0;
    int setHr=0;
    boolean check=false;

    int tempPause=0;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnstart=(Button)findViewById(R.id.btnStart);
        btnstop=(Button)findViewById(R.id.btnStop);
        btnpause=(Button)findViewById(R.id.btnPause);
        btncontinue=(Button)findViewById(R.id.btnContinue);

        milliSec=(TextView)findViewById(R.id.millisec);
        sec=(TextView)findViewById(R.id.sec);
        min=(TextView)findViewById(R.id.min);
        hr=(TextView)findViewById(R.id.hr);
        btncontinue.setVisibility(View.INVISIBLE);
        btnpause.setVisibility(View.INVISIBLE);
        btnstop.setVisibility(View.INVISIBLE);

        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnpause.setVisibility(View.VISIBLE);
                btnstart.setVisibility(View.INVISIBLE);
                check=false;
                MyClass myClass=new MyClass();
             myClass.execute(1000,12,14,16);}


        });
        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnpause.setVisibility(View.VISIBLE);
                btncontinue.setVisibility(View.INVISIBLE);
                btnstop.setVisibility(View.INVISIBLE);
                check=false;
                MyClass myClass=new MyClass();
                myClass.execute(1000);}


        });
        btnpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnpause.setVisibility(View.INVISIBLE);
                btncontinue.setVisibility(View.VISIBLE);
                btnstop.setVisibility(View.VISIBLE);
                check=true;
                //btnpause.setVisibility(View.GONE);
            }
        });

        btnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnstop.setVisibility(View.INVISIBLE);
                btncontinue.setVisibility(View.INVISIBLE);
                btnstart.setVisibility(View.VISIBLE);
                check=true;
                milliSec.setText("00");
                sec.setText("00");
                min.setText("00");
                hr.setText("00");
                tempPause=0;
                setSec=0;
                setMin=0;
                setHr=0;
            }
        });

    }

    public class MyClass extends AsyncTask<Integer,Integer,Long> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Long doInBackground(Integer... params) {

            Log.d("TAG", "doInBackground: "+params[0]);
            do {

                for (i = tempPause; i <= params[0]; i++) {

                    if(check==true){

                        Log.d("TAG", "doInBackground:Initial Time= "+initialTime);
                        tempPause=i;
                        return 999L;
                    }
                    tempPause=0;
                    oneLoop();
                    if (i == 999) {
                        setSec++;
                    }
                    if (setSec == 60) {
                        setMin++;
                        setSec = 0;
                    }
                    if(setMin==60)
                    {
                        setHr++;
                        setMin=0;
                    }
                    publishProgress((int) i, (int) setSec, (int) setMin ,(int) setHr);
                }
            }while (check==false);

            return 99999999L;

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            milliSec.setText(Integer.toString(values[0]));
            if(values[1]<10)
            {
                sec.setText("0"+Integer.toString(values[1]));
            }else
            {
            sec.setText(Integer.toString(values[1]));}
            if(values[2]<10)
            {
                min.setText("0"+Integer.toString(values[2]));
            }else
            {
            min.setText(Integer.toString(values[2]));}
            if(values[3]<10)
            {
                hr.setText("0"+Integer.toString(values[3]));
            }else{
            hr.setText(Integer.toString(values[3]));}
        }

        @Override
        protected void onPostExecute(Long s) {
            super.onPostExecute(s);
        }
    }
        @Override
        public void onDestroy()
        {
            Log.e("TAG", "onDestroy: ");
            super.onDestroy();
        }
        static void oneLoop()
        {
            long startTime=System.currentTimeMillis();
            while (System.currentTimeMillis()-startTime<1);

        }
    }
