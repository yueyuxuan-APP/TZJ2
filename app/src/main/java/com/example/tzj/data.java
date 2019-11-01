package com.example.tzj;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;

public class data extends AppCompatActivity {
    private EditText etvalue1;
    private EditText etvalue2;
    private EditText etvalue3;
    private EditText etvalue4;         //   分别用于从四个框框中获取数据
    private double value1;
    private double value2;
    private String value3;
    private double value4;             //    分别用于将获取的数据转化为需要的类型
    private double result;             //    BMI
    private double result2;            //    体脂率
    private double result3;            //    肌肉量
    private double result4;            //    体水分率
    private double result5;            //    基础代谢
    private double result6;            //    骨质


    private int zhishu;                //    指数，男为1，女为2




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar !=null){
            actionBar.hide();                                     //隐藏标题栏
        }
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etvalue1 = (EditText)findViewById(R.id.edit_text1);
                etvalue2 = (EditText)findViewById(R.id.edit_text2);
                etvalue3 = (EditText)findViewById(R.id.edit_text3);
                etvalue4 = (EditText)findViewById(R.id.edit_text4);//获得数据

                if("".equals(etvalue1.getText().toString())||"".equals(etvalue2.getText().toString())||"".equals(etvalue3.getText().toString())||"".equals(etvalue4.getText().toString()))
                {

                    Toast.makeText(data.this,"请您输入完整的数据!",Toast.LENGTH_SHORT).show();

                }//在点击button（开始测试）之后触发点击事件，先对第一个if进行判断，如果有一个为空则弹出一个Toast
                else
                {
                    value1 = Double.parseDouble(etvalue1.getText().toString());
                    value2 = Double.parseDouble(etvalue2.getText().toString());
                    value3 = etvalue3.getText().toString();
                    value4 = Double.parseDouble(etvalue4.getText().toString());
                    //如果不是空输入，那么就可以获得数据啦。将第1.2.4个editText中获得的数据转化为双精度浮点型


                    if(value1==0||value2==0||value4==0)
                    {

                        Toast.makeText(data.this,"输入数据不能为0!",Toast.LENGTH_SHORT).show();

                    }//然后对获得的数据进行输入不能为0的限制
                    else
                        //输入合法，则进行一下运算
                    {
                        result = value1/(value2*value2);
                        if ("男".equals(value3))
                            zhishu = 1;
                        else
                            zhishu = 0;
                        result2 = 1.2*result+0.23*value4-5.4-10.8*zhishu;
                        if(zhishu==1)
                            result3 = 78/(2*value1);
                        else
                            result3 = 56/(2*value1);
                        if(zhishu==1)
                            result4=result3*1.2;
                        else
                            result4=result3*1.3;
                        if(zhishu==1)
                            result5=13.7*value1+5.0*value2*100-6.8*value4+66;
                        else
                            result5=9.6*value1+1.8*value2*100-4.7*value4+655;
                        result6 = (value1-value4)*0.2;
                        Intent intent = new Intent(data.this,jieguo.class);//跳转到jieguo页面
                        Bundle bundle = new Bundle();
                        bundle.putDouble("BMI",result);
                        bundle.putDouble("TZL",result2);
                        bundle.putDouble("JRL",result3);
                        bundle.putDouble("TS",result4);
                        bundle.putDouble("DX",result5);
                        bundle.putDouble("GZ",result6);
                        bundle.putDouble("SG",value2);
                        bundle.putDouble("TZ",value1);
                        bundle.putInt("ZS",zhishu);
                        intent.putExtras(bundle);//把数据放到bundle里，再把bundle放到intent里，避免使用多个intent来传递数据，感觉有点麻烦
                        startActivity(intent);
                        finish();//然后把这个界面结束掉，从健康报告过来的时候可以再次输入
                    }



                }

            }
        });




    }}
