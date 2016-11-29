package com.phone1000.martialstudyself.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.phone1000.martialstudyself.R;
import com.phone1000.martialstudyself.bases.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_comment)
public class CommentActivity extends BaseActivity implements View.OnClickListener {
    @ViewInject(R.id.comment_back_text)
    private TextView backText;
    @ViewInject(R.id.comment_publish_text)
    private TextView publish;
    @ViewInject(R.id.comment_title)
    private EditText title;
    @ViewInject(R.id.comment_content)
    private EditText content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initView();
    }

    private void initView() {
        backText.setOnClickListener(this);
        publish.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.comment_back_text:
                finish();
                break;
            case R.id.comment_publish_text:
                //这里做数据库的保存

                Toast.makeText(CommentActivity.this, "评论成功！", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }
}
