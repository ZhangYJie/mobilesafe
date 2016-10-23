package com.itheima.mobilesafe74.activity;

import com.itheima.mobilesafe74.R;
import com.itheima.mobilesafe74.utils.ConstantValue;
import com.itheima.mobilesafe74.utils.SpUtil;
import com.itheima.mobilesafe74.utils.ToastUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class Setup4Activity extends BaseSetupActivity {
	private CheckBox cb_box;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup4);
		
		initUI();
	}
	
	private void initUI() {
		cb_box = (CheckBox) findViewById(R.id.cb_box);
		//1,是否选中状态的回显
		boolean open_security = SpUtil.getBoolean(this, ConstantValue.OPEN_SECURITY, false);
		//2,根据状态,修改checkbox后续的文字显示
		cb_box.setChecked(open_security);
		if(open_security){
			cb_box.setText("安全设置已开启");
		}else{
			cb_box.setText("安全设置已关闭");
		}
		
//		cb_box.setChecked(!cb_box.isChecked());
		//3,点击过程中,监听选中状态发生改变过程,
		cb_box.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				//4,isChecked点击后的状态,存储点击后状态
				SpUtil.putBoolean(getApplicationContext(), ConstantValue.OPEN_SECURITY, isChecked);
				//5,根据开启关闭状态,去修改显示的文字
				if(isChecked){
					cb_box.setText("安全设置已开启");
				}else{
					cb_box.setText("安全设置已关闭");
				}
			}
		});

	}

	@Override
	protected void showNextPage() {
		boolean open_security = SpUtil.getBoolean(this, ConstantValue.OPEN_SECURITY, false);
		if(open_security){
			Intent intent = new Intent(getApplicationContext(), SetupOverActivity.class);
			startActivity(intent);
			
			finish();
			SpUtil.putBoolean(this, ConstantValue.SETUP_OVER, true);
			
			overridePendingTransition(R.anim.next_in_anim, R.anim.next_out_anim);
		}else{
			ToastUtil.show(getApplicationContext(), "请开启防盗保护");
		}
	}

	@Override
	protected void showPrePage() {
		Intent intent = new Intent(getApplicationContext(), Setup3Activity.class);
		startActivity(intent);
		
		finish();
		
		overridePendingTransition(R.anim.pre_in_anim, R.anim.pre_out_anim);
	}
}
