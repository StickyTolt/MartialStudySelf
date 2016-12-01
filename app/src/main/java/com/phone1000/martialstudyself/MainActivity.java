package com.phone1000.martialstudyself;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.phone1000.martialstudyself.fragment.BookFragment;
import com.phone1000.martialstudyself.fragment.HomePageFargment;
import com.phone1000.martialstudyself.fragment.MartialHundredFragment;
import com.phone1000.martialstudyself.fragment.TalkMartialFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.lang.reflect.InvocationTargetException;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    @ViewInject(R.id.main_controller)
    private RadioGroup mController;
    @ViewInject(R.id.main_search)
    private ImageView found ;
    @ViewInject(R.id.main_personal)
    private ImageView user;

    private Fragment mShowFragment;

    private long backTime = 0;
    private static int ISBACK = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        x.view().inject(this);
        initView();
    }

    private void initView() {

        mController.setOnCheckedChangeListener(this);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        mShowFragment = new HomePageFargment();
        transaction.add(R.id.main_container, mShowFragment, HomePageFargment.TAG);
        transaction.commit();
        user.setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.home_page:
                swapPage(HomePageFargment.TAG, HomePageFargment.class);
                break;
            case R.id.book:
                swapPage(BookFragment.TAG, BookFragment.class);
                break;
            case R.id.martial_hundred:
                swapPage(MartialHundredFragment.TAG, MartialHundredFragment.class);
                break;
            case R.id.talk_martial:
                swapPage(TalkMartialFragment.TAG, TalkMartialFragment.class);
                break;
        }
    }

    private void swapPage(String tag, Class<? extends Fragment> cls) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.hide(mShowFragment);
        mShowFragment = fragmentManager.findFragmentByTag(tag);
        if (mShowFragment != null) {
            transaction.show(mShowFragment);
        } else {
            try {
                mShowFragment = cls.getConstructor().newInstance();
                transaction.add(R.id.main_container, mShowFragment, tag);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        transaction.commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (ISBACK == 0) {
                Toast.makeText(MainActivity.this, "再按一次退出自学武术", Toast.LENGTH_SHORT).show();
                ISBACK++;
                backTime = System.currentTimeMillis();
                return true;
            }else if (System.currentTimeMillis() - backTime >1000){
                Toast.makeText(MainActivity.this, "再按一次退出自学武术", Toast.LENGTH_SHORT).show();
                backTime = System.currentTimeMillis();
                return true;
            }
        }
        BaseApp.removeAllActivity();
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {

    }
}
