package com.example.android_wechat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.FitWindowsViewGroup;
import android.app.Fragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceControl;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.math.BigDecimal;

public class MainActivity extends Activity implements View.OnClickListener{
    //获取按钮区域
    private LinearLayout area_weChat;
    private LinearLayout area_relationship;
    private LinearLayout area_friend;
    private LinearLayout area_setting;

    //按钮图标区域
    private ImageView img_weChat;
    private ImageView img_relationship;
    private ImageView img_friend;
    private ImageView img_setting;

    Fragment weChatfragment=new Fragment_WeChat();
    Fragment relationshipfragment=new Fragment_Relationship();
    Fragment friendfragment = new Fragment_Friend();
    Fragment settingfragment=new Fragment_Setting();

    FragmentManager fm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_main);

        initView();
        initFragment();
        initEvent();
        setSelect(0);
    }

    //形成层叠页面
    private void initFragment() {
        fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.fragmentcontent,weChatfragment);
        transaction.add(R.id.fragmentcontent,relationshipfragment);
        transaction.add(R.id.fragmentcontent,friendfragment);
        transaction.add(R.id.fragmentcontent,settingfragment);
        transaction.commit();
    }

    //获取监听区域
    private void initEvent()
    {
        area_weChat.setOnClickListener(this);
        area_relationship.setOnClickListener(this);
        area_friend.setOnClickListener(this);
        area_setting.setOnClickListener(this);
    }

    //显示切换效果
    private void initView()
    {
        area_weChat=(LinearLayout)findViewById(R.id.area_weChat);
        area_relationship=(LinearLayout)findViewById(R.id.area_relationship);
        area_friend=(LinearLayout)findViewById(R.id.area_friend);
        area_setting=(LinearLayout)findViewById(R.id.area_setting);

        img_weChat=(ImageView)findViewById(R.id.img_weChat);
        img_relationship=(ImageView)findViewById(R.id.img_relationship);
        img_friend=(ImageView)findViewById(R.id.img_friend);
        img_setting=(ImageView)findViewById(R.id.img_setting);
    }

    //按钮的选中与未选中切换
    private void setSelect(int i){
        FragmentTransaction transaction=fm.beginTransaction();
        hideFragment(transaction);
        //把图片设置为亮的
        switch (i){
            case 0:
                Log.d("setSelect","1");
                transaction.show(weChatfragment);
                img_weChat.setImageResource(R.drawable.tab_weixin_pressed);
                break;
            case 1:
                transaction.show(relationshipfragment);
                img_relationship.setImageResource(R.drawable.tab_address_pressed);
                break;
            case 2:
                transaction.show(friendfragment);
                img_friend.setImageResource(R.drawable.tab_find_frd_pressed);
                break;
            case 3:
                transaction.show(settingfragment);
                img_setting.setImageResource(R.drawable.tab_settings_pressed);
                break;
            default:
                break;

        }
        transaction.commit();
    }
    //隐藏
    private void hideFragment(FragmentTransaction transaction)
    {
        transaction.hide(weChatfragment) ;
        transaction.hide(relationshipfragment) ;
        transaction.hide(friendfragment) ;
        transaction.hide(settingfragment) ;
    }
    //监听
    @Override
    public void onClick(View v)
    {
        Log.d("onClick","1");
        resetImgs();
        switch(v.getId()){
            case R.id.area_weChat:
                Log.d("onClick","2");
                setSelect(0);
                break;
            case R.id.area_relationship:
                setSelect(1);
                break;
            case R.id.area_friend:
                setSelect(2);
                break;
            case R.id.area_setting:
                setSelect(3);
                break;
            default:
                break;
        }
    }
    //按钮图标重置
    public void resetImgs()
    {
        img_weChat.setImageResource(R.drawable.tab_weixin_normal);
        img_relationship.setImageResource(R.drawable.tab_address_normal);
        img_friend.setImageResource(R.drawable.tab_find_frd_normal);
        img_setting.setImageResource(R.drawable.tab_settings_normal);
    }

}