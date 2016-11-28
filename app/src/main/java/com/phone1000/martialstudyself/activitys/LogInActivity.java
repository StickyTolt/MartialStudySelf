package com.phone1000.martialstudyself.activitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.phone1000.martialstudyself.R;
import com.phone1000.martialstudyself.bases.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;
@ContentView(R.layout.activity_log_in)
public class LogInActivity extends BaseActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    @ViewInject(R.id.log_in_back)
    private ImageView back ;
    @ViewInject(R.id.log_in_name)
    private  EditText name ;
    @ViewInject(R.id.log_in_password)
    private EditText password;
    @ViewInject(R.id.log_in_see)
    private  CheckBox isSee ;
    @ViewInject(R.id.log_in_log)
    private Button logIn;
    @ViewInject(R.id.log_in_register)
    private TextView register ;
    @ViewInject(R.id.log_in_forget_password)
    private TextView forgetpassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initView();
    }

    private void initView() {
        back.setOnClickListener(this);
        logIn.setOnClickListener(this);
        register.setOnClickListener(this);
        forgetpassword.setOnClickListener(this);
        isSee.setOnCheckedChangeListener(this);

        name.clearFocus();
        password.clearFocus();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.log_in_back:
                finish();
                break;
            case R.id.log_in_log:
                String userName = name.getText().toString();
                String userPassword = password.getText().toString();
                if ("".equals(userName)) {
                    Toast.makeText(LogInActivity.this, "请输入用户名和邮箱", Toast.LENGTH_SHORT).show();
                }else if ("".equals(userPassword)){
                    Toast.makeText(LogInActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                }else {
                    SharedPreferences user = getSharedPreferences("user", Context.MODE_PRIVATE);
                    String nameStr = user.getString("userName", "null");
                    String passwordStr = user.getString(userName, "null");
                    if (passwordStr.equals("null")) {
                        Toast.makeText(LogInActivity.this, "账号不存在，请重新输入", Toast.LENGTH_SHORT).show();
                    }else if (!passwordStr.equals(userPassword)){
                        Toast.makeText(LogInActivity.this, "密码错误，请重新输入", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(LogInActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        user.edit().putString("isLogIn","true").commit();
                        finish();
                    }
                }


                break;
            case R.id.log_in_register:
                // 跳转到注册界面
                startActivity(new Intent(this,RegisterActivity.class));
                break;

            case R.id.log_in_forget_password:
                // 跳转到找回界面
                startActivity(new Intent(this,ForgetActivity.class));
                break;

        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            password.setTransformationMethod(null);
        }else {
            password.setTransformationMethod(new PasswordTransformationMethod());
        }
    }
}
