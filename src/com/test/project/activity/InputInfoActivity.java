package com.test.project.activity;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.navigation.drawer.activity.R;

public class InputInfoActivity extends BaseActivity {
	public static final String INFO_FILE_NAME = "info.txt";
	private String name;
	private boolean male;
	private int year, height, weight;
	private EditText etName;
	private NumberPicker npYear, npHeight, npWeight;
	private RadioGroup rgGender;
	private RadioButton rMale, rFemale;
	@Override
		protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getLayoutInflater().inflate(R.layout.input_info, frameLayout);
//		setContentView(R.layout.input_info);
		setTitle("Edit Your Personal Details");
		
		etName = (EditText) findViewById(R.id.etName);
		rgGender = (RadioGroup) findViewById(R.id.rgGender);
		rMale = (RadioButton) findViewById(R.id.rMale);
		rFemale = (RadioButton) findViewById(R.id.rFemale);
		npYear = (NumberPicker) findViewById(R.id.npYear);
		npHeight = (NumberPicker) findViewById(R.id.npHeight);
		npWeight = (NumberPicker) findViewById(R.id.npWeight);
		
		initForm();
	}
	
	private void initForm() {
		Calendar c = Calendar.getInstance(); 
		int currentYear = c.get(Calendar.YEAR);
		readFromFile();
		etName.setText(name);
		
		npYear.setMaxValue(currentYear);
		npYear.setMinValue(currentYear-150);
		npYear.setValue(year);
		npYear.setWrapSelectorWheel(true); 
		
		rMale.setChecked(male);
		rFemale.setChecked(!male);
		
		npHeight.setMaxValue(250);
		npHeight.setMinValue(50);
		npHeight.setValue(height);
		npHeight.setWrapSelectorWheel(true); 
		
		npWeight.setMaxValue(200);
		npWeight.setMinValue(20);
		npWeight.setValue(weight);
		npWeight.setWrapSelectorWheel(true); 
	}

	public void saveInfo(View v) {
		name = etName.getText().toString();
		year = npYear.getValue();
		height = npHeight.getValue();
		weight = npWeight.getValue();
		male = rgGender.getCheckedRadioButtonId() == R.id.rMale;
		if(name.length() ==0){
			Toast.makeText(getBaseContext(),
					"Please Enter A Name!", Toast.LENGTH_SHORT)
					.show();
		}else{
			writeToFile();
		}
	}
	
	public void writeToFile(){
		try {
			FileOutputStream fo = openFileOutput(INFO_FILE_NAME,
					MODE_PRIVATE);
			DataOutputStream dos = new DataOutputStream(fo);
			dos.writeChars(name);
			dos.writeChars("\n");
			dos.writeInt(year);
			dos.writeBoolean(male);
			dos.writeInt(height);
			dos.writeInt(weight);
			dos.flush();
			dos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finish();
	}
	
	public boolean readFromFile(){
		try {
			FileInputStream fi = openFileInput(INFO_FILE_NAME);
			DataInputStream dis = new DataInputStream(fi);
			name = dis.readLine();
			year = dis.readInt();
			male = dis.readBoolean();
			height = dis.readInt();
			weight = dis.readInt();
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			setNewUser();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			setNewUser();
			return false;
		}
	}
	
	private void setNewUser(){
		name = "";
		year = 1960;
		male = true;
		height = 150;
		weight = 60;
	}
}
