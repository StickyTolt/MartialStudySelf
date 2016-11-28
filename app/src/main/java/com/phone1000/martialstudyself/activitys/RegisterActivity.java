package com.phone1000.martialstudyself.activitys;

import android.content.Context;
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
@ContentView(R.layout.activity_register)
public class RegisterActivity extends BaseActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    @ViewInject(R.id.register_back)
    private ImageView backImg;
    @ViewInject(R.id.register_user)
    private EditText userName;
    @ViewInject(R.id.register_password)
    private EditText password;
    @ViewInject(R.id.register_confirm)
    private EditText passwordConfirm;
    @ViewInject(R.id.register_mailbox)
    private EditText mailBox;
    @ViewInject(R.id.register_register)
    private Button register ;
    @ViewInject(R.id.register_login)
    private TextView logIn;
    @ViewInject(R.id.register_see)
    private CheckBox see;
    @ViewInject(R.id.register_see_see)
    private CheckBox seeS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initView();
    }

    private void initView() {
        backImg.setOnClickListener(this);
        see.setOnCheckedChangeListener(this);
        seeS.setOnCheckedChangeListener(this);
        register.setOnClickListener(this);
        logIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_back:
                finish();
                break;
            case R.id.register_register:
                String name = userName.getText().toString();
                if (name.equals("")) {
                    Toast.makeText(RegisterActivity.this, "请输入账号", Toast.LENGTH_SHORT).show();
                }else {
                    String password = this.password.getText().toString();
                    if (password.equals("")) {
                        Toast.makeText(RegisterActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    }else if (!passwordConfirm.getText().toString().equals(password)) {
                        Toast.makeText(RegisterActivity.this, "两次输入密码不相同", Toast.LENGTH_SHORT).show();
                    }else {
                        String mail = mailBox.getText().toString();
                        if (mail.equals("")) {
                            Toast.makeText(RegisterActivity.this, "请填写邮箱", Toast.LENGTH_SHORT).show();
                        }else {
                            // 这里做数据的保存
                            SharedPreferences user = getSharedPreferences("user", Context.MODE_PRIVATE);
                            SharedPreferences.Editor edit = user.edit();
                            edit.putString(name, password);
                            edit.putString(mail,name+"|"+password);
                            edit.putString(name+password , mail);
                            edit.commit();
                            finish();
                        }
                    }
                }
                break;
            case R.id.register_login:
                finish();
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.register_see:
                if (!isChecked) {
                    password.setTransformationMethod(new PasswordTransformationMethod());
                }else {
                    password.setTransformationMethod(null);
                }
                break;
            case R.id.register_see_see:
                if (!isChecked) {
                    passwordConfirm.setTransformationMethod(new PasswordTransformationMethod());
                }else {
                    passwordConfirm.setTransformationMethod(null);
                }
                break;
        }
    }
}
