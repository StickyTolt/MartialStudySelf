package com.phone1000.martialstudyself.activitys;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.phone1000.martialstudyself.R;
import com.phone1000.martialstudyself.bases.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_forget)
public class ForgetActivity extends BaseActivity implements View.OnClickListener {
    @ViewInject(R.id.forget_back_image)
    private ImageView backImg;
    @ViewInject(R.id.forget_name)
    private EditText nameAmail;
    @ViewInject(R.id.forget_submit)
    private Button submit ;
    @ViewInject(R.id.forget_back_text)
    private TextView backText ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initView();
    }

    private void initView() {
        backImg.setOnClickListener(this);
        submit.setOnClickListener(this);
        backText.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.forget_back_image:
                finish();
                break;
            case R.id.forget_back_text:
                finish();
                break;
            case R.id.forget_submit:
                String name = nameAmail.getText().toString();
                if ("".equals(name)) {
                    Toast.makeText(ForgetActivity.this, "请输入账号", Toast.LENGTH_SHORT).show();
                }else {
                    SharedPreferences user = getSharedPreferences("user", Context.MODE_PRIVATE);
                    if (user.getString(name,"null").equals("null")) {
                        Toast.makeText(ForgetActivity.this, "您提交的信息有误，请核对", Toast.LENGTH_SHORT).show();
                    }else {
                        String someStr = user.getString(name, "null");
                        String mailStr = user.getString(name + someStr, "null");
                        if (mailStr.equals("null")) {
                            Toast.makeText(ForgetActivity.this, "提交的邮箱信息正确", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(ForgetActivity.this, "提交的用户名正确", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;

        }
    }
}
