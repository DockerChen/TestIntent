package com.example.cr.testintent;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by cr on 2016/9/19.
 */
public class FirstActivity extends Activity {
    private EditText name;
    private EditText age;
    private Button send;
    //定义SharedPreference的名称
    private static final String PREFERENCE_NAME = "SaveSetting";
    //定义SharedPreferences的访问模式MODE
    public static int MODE = MODE_PRIVATE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);          //设置布局文件
        name = (EditText) findViewById(R.id.name);      //得到name的值
        age = (EditText) findViewById(R.id.age);        //得到age的值
        send = (Button) findViewById(R.id.send);        //得到send的值

        send.setOnClickListener(new View.OnClickListener() {    //为send设置监听器
            @Override
            public void onClick(View view) {    //重写onClick方法
                String name = FirstActivity.this.name.getText().toString();
                String age = FirstActivity.this.age.getText().toString();
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);  //创建Intent组件
                intent.putExtra("name", name);   //传递参数
                intent.putExtra("age", age);
                startActivityForResult(intent, 1);   //执行Intent组件

            }
        });

    }

    @Override
    //在onStart()函数中调用loadSharedPreferences()函数，读取保存在SharedPreferences中的姓名、年龄，并显示在用户界面上
    protected void onStart() {
        super.onStart();
        loadSharedPreferences();

    }
    //在onStop()函数调用saveSharedPreferences()，保存界面上的信息
    @Override
    protected void onStop() {
        super.onStop();
        saveSharedPreferences();
    }

    private void loadSharedPreferences() {
        String name = FirstActivity.this.name.getText().toString();
        String age = FirstActivity.this.age.getText().toString();
        //调用getSharedPreferences函数获取SharedPreferences对象
        SharedPreferences sharedPreferences=getSharedPreferences(PREFERENCE_NAME,MODE);
        String stored_name =sharedPreferences.getString("name",name);
        String stored_age=sharedPreferences.getString("age",age);
        FirstActivity.this.name.setText(stored_name);
        FirstActivity.this.age.setText(stored_age);

    }
    private void saveSharedPreferences(){
        SharedPreferences sharedPreferences=getSharedPreferences(PREFERENCE_NAME,MODE);
        //通过SharedPreferences.Editor类对SharedPreferences进行修改，最后调用commit()函数保存修改内容
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("name",FirstActivity.this.name.getText().toString());
        editor.putString("age",FirstActivity.this.age.getText().toString());
        editor.commit();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String returnedData = data.getStringExtra("data_return");
                    Toast.makeText(this, returnedData, Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }
}
