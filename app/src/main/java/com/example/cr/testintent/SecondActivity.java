package com.example.cr.testintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by cr on 2016/9/19.
 */
public class SecondActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        Intent intent = getIntent();                //获取用于启动SecondActivity的Intent组件
        String name = intent.getStringExtra("name");    //获取传递的数据
        String age = intent.getStringExtra("age");
        Button button = (Button) findViewById(R.id.button);
        Toast.makeText(this, name + " " + age, Toast.LENGTH_SHORT).show();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent();
                intent1.putExtra("data_return", "Hello FirstActivity!");
                setResult(RESULT_OK, intent1);  //用于向上一个活动返回数据
                finish();   //销毁当前活动

            }
        });


    }
}
