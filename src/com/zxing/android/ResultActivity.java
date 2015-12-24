package com.zxing.android;

import java.io.UnsupportedEncodingException;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ResultActivity extends ActionBarActivity {
	private byte[] result;
	private TextView tvResult0,tvResult1,tvResult2;
	String text="例如，对于Socket读操作，若从socket中read到数据后，需要从头开始存放到缓冲区，而不是从上次的" +
			"位置开始继续/连续存放，则需要clear()，重置position指针，但此时需要注意，若read到的数据没有填满" +
			"缓冲区，则socket的read完成后，不能使用array()方法取出缓冲区的数据，因为array()返回的是整个缓冲" +
			"区的数据，而不是上次read到的数据。";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		tvResult0=(TextView) findViewById(R.id.text_result0);
		tvResult1=(TextView) findViewById(R.id.text_result1);
		tvResult2=(TextView) findViewById(R.id.text_result2);
		String str,str1,str2;
		result=getIntent().getByteArrayExtra("data");
		Log.i("elicec",ENote.bytesToHexString(result));
		Log.i("elicec",ENote.bytesToHexString(text.getBytes()));
		
		try {
			str=new String(result,"utf-8");
			Log.i("1111111",ENote.bytesToHexString(str.getBytes()));
			tvResult0.setText(str);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.result, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
