package com.daon.admin_onorder;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.daon.admin_onorder.model.PrintOrderModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sam4s.printer.Sam4sBuilder;
import com.sam4s.printer.Sam4sPrint;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    ImageView paymentBtn;
    ImageView orderBtn;
    ImageView serviceBtn;
    ImageView menuBtn;
    SharedPreferences pref;
    ImageView tableBtn;

    ImageView bottom_home;
    ImageView bottom_service;
    ImageView bottom_order;
    ImageView bottom_payment;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    AdminApplication app = new AdminApplication();
    String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pref = getSharedPreferences("pref", MODE_PRIVATE);

        BackThread thread = new BackThread();  // 작업스레드 생성
        thread.setDaemon(true);  // 메인스레드와 종료 동기화
        thread.start();

        bottom_order = findViewById(R.id.bottom_menu3);
        bottom_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                startActivity(intent);
                finish();
            }
        });
        bottom_service = findViewById(R.id.bottom_menu2);
        bottom_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ServiceActivity.class);
                startActivity(intent);
                finish();
            }
        });
        bottom_payment = findViewById(R.id.bottom_menu4);
        bottom_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "준비중 입니다.", Toast.LENGTH_SHORT).show();

            }
        });
        menuBtn = findViewById(R.id.menu3);
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
//                startActivity(intent);
//                finish();
                Toast.makeText(MainActivity.this, "준비중 입니다.", Toast.LENGTH_SHORT).show();

            }
        });

        serviceBtn = findViewById(R.id.menu1);
        serviceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ServiceActivity.class);
                startActivity(intent);
//                finish();
            }
        });

        paymentBtn = findViewById(R.id.menu4);
        paymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, PaymentActivity.class);
//                startActivity(intent);
//                finish();
                Toast.makeText(MainActivity.this, "준비중 입니다.", Toast.LENGTH_SHORT).show();
            }
        });

        orderBtn = findViewById(R.id.menu2);
        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "준비중 입니다.", Toast.LENGTH_SHORT).show();

//                Intent intent = new Intent(MainActivity.this, OrderActivity.class);
//                startActivity(intent);
//                finish();
            }
        });
        tableBtn = findViewById(R.id.menu5);
        tableBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "준비중 입니다.", Toast.LENGTH_SHORT).show();
            }
        });
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        time = format2.format(calendar.getTime());
        String time2 = format2.format(calendar.getTime());
        FirebaseDatabase.getInstance().getReference().child("order").child("휘도명가").child(time).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot item : snapshot.getChildren()) {
                    PrintOrderModel printOrderModel = item.getValue(PrintOrderModel.class);
                    if (printOrderModel.getPrintStatus().equals("x")) {
                        printOrderModel.setPrintStatus("o");
                        FirebaseDatabase.getInstance().getReference().child("order").child("휘도명가").child(time).child(item.getKey()).setValue(printOrderModel);
                        print(printOrderModel);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        FirebaseDatabase.getInstance().getReference().child("service").child(pref.getString("storename", "")).child(time).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot item : snapshot.getChildren()) {
                    PrintOrderModel printOrderModel = item.getValue(PrintOrderModel.class);
                    if (printOrderModel.getPrintStatus().equals("x")) {
                        printOrderModel.setPrintStatus("o");
                        FirebaseDatabase.getInstance().getReference().child("service").child("휘도명가").child(time).child(item.getKey()).setValue(printOrderModel);
                        print(printOrderModel);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void initFirebase(){

    }

    public void print(PrintOrderModel printOrderModel) {

        Sam4sPrint sam4sPrint = new Sam4sPrint();
        Sam4sPrint sam4sPrint2 = new Sam4sPrint();
        isPrinter isPrinter = new isPrinter();
//        try {
//            Thread.sleep(300);
//            sam4sPrint.openPrinter(Sam4sPrint.DEVTYPE_ETHERNET, "172.30.1.45", 9100);
//            sam4sPrint.resetPrinter();
//            sam4sPrint2.openPrinter(Sam4sPrint.DEVTYPE_ETHERNET, "172.30.1.59", 9100);
//            sam4sPrint2.resetPrinter();
//        } catch (Exception e) {
//            e.printStackTrace();
//            Log.d("daon", "print error = "+e.getMessage());
//        }

        sam4sPrint = isPrinter.setPrinter1();
        sam4sPrint2 = isPrinter.setPrinter2();
        Sam4sBuilder builder = new Sam4sBuilder("ELLIX30", Sam4sBuilder.LANG_KO);
        try {
            builder.addTextAlign(Sam4sBuilder.ALIGN_CENTER);
            builder.addFeedLine(2);
            builder.addTextSize(3, 3);
            builder.addText(printOrderModel.getTable());
            builder.addFeedLine(2);
            builder.addTextSize(2, 2);
            builder.addTextAlign(Sam4sBuilder.ALIGN_RIGHT);
            builder.addText(printOrderModel.getOrder());
            builder.addFeedLine(2);
            builder.addTextSize(1, 1);
            builder.addText(printOrderModel.getTime());
            builder.addFeedLine(1);
            builder.addCut(Sam4sBuilder.CUT_FEED);
            if (printOrderModel.getTable().contains("주문")) {
                sam4sPrint.sendData(builder);
                sam4sPrint2.sendData(builder);
            } else {
                sam4sPrint.sendData(builder);
//                sam4sPrint2.sendData(builder);
            }
            MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.bell);
            mp.start();

            isPrinter.closePrint1(sam4sPrint);
            isPrinter.closePrint2(sam4sPrint2);

        } catch (Exception e) {
            e.printStackTrace();
        }

//        if (!sam4sPrint.IsConnected(Sam4sPrint.DEVTYPE_ETHERNET) || !sam4sPrint.IsConnected(Sam4sPrint.DEVTYPE_ETHERNET)) {
//            AlertDialog.Builder oDialog = new AlertDialog.Builder(this,
//                    android.R.style.Theme_DeviceDefault_Light_Dialog);
//
//            oDialog.setMessage("프린터 이상")
//                    .setNeutralButton("예", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//
//                        }
//                    })
//                    .setCancelable(false) // 백버튼으로 팝업창이 닫히지 않도록 한다.
//                    .show();
//            MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.bell);
//            mp.start();
//        } else {
//            Sam4sBuilder builder = new Sam4sBuilder("ELLIX30", Sam4sBuilder.LANG_KO);
//            try {
//                builder.addTextAlign(Sam4sBuilder.ALIGN_CENTER);
//                builder.addFeedLine(2);
//                builder.addTextSize(3, 3);
//                builder.addText(printOrderModel.getTable());
//                builder.addFeedLine(2);
//                builder.addTextSize(2, 2);
//                builder.addTextAlign(Sam4sBuilder.ALIGN_RIGHT);
//                builder.addText(printOrderModel.getOrder());
//                builder.addFeedLine(2);
//                builder.addTextSize(1, 1);
//                builder.addText(printOrderModel.getTime());
//                builder.addFeedLine(1);
//                builder.addCut(Sam4sBuilder.CUT_FEED);
//                if (printOrderModel.getTable().contains("주문")) {
//                    sam4sPrint.sendData(builder);
//                    sam4sPrint2.sendData(builder);
//                } else {
//                    sam4sPrint.sendData(builder);
//                }
//                MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.bell);
//                mp.start();
//
//                isPrinter.closePrint1(sam4sPrint);
//                isPrinter.closePrint2(sam4sPrint2);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

    }

    class BackThread extends Thread {  // Thread 를 상속받은 작업스레드 생성
        @Override
        public void run() {
            while (true) {
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

                time = format2.format(calendar.getTime());
                Log.d("daon_test", "time = " + time);
                try {
                    Thread.sleep(60000);   // 1000ms, 즉 1초 단위로 작업스레드 실행
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}