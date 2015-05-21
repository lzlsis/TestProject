package com.test.project.activity;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.navigation.drawer.activity.R;
/**
 * @author dipenp
 *
 */
public class HomeActivity extends BaseActivity {

	private String userName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/**
		 *  We will not use setContentView in this activty 
		 *  Rather than we will use layout inflater to add view in FrameLayout of our base activity layout*/
		
		/**
		 * Adding our layout to parent class frame layout.
		 */
		getLayoutInflater().inflate(R.layout.activity_home, frameLayout);
		
		/**
		 * Setting title and itemChecked  
		 */
		mDrawerList.setItemChecked(position, true);
		setTitle(listArray[position]);
		userName = "";
		
		if(isNew()){
			((TextView) findViewById(R.id.tv_greeding)).setText("");
			editInfo(null);
		}else{
			((TextView) findViewById(R.id.tv_greeding)).setText("Hello "+userName);
		}
	}
	
	public void editInfo(View v){
		Intent in = new Intent(this, InputInfoActivity.class);
		startActivity(in);
	}
	
	public boolean isNew(){
		try {
			FileInputStream fi = openFileInput(InputInfoActivity.INFO_FILE_NAME);
			DataInputStream dis = new DataInputStream(fi);
			userName = dis.readLine();
			int year = dis.readInt();
			boolean male = dis.readBoolean();
			int height = dis.readInt();
			int weight = dis.readInt();
			dis.close();
			return false;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return true;
		}
	}
}
