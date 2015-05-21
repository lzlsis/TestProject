package com.test.project.activity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.StringTokenizer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.navigation.drawer.activity.R;
import com.test.project.utility.BloodPressureEntry;

/**
 * @author Andy
 * 
 */
public class BloodActivity extends BaseActivity {
	public static final String BLOOD_PRESSURE_FILE = "bpData.txt";
	private ArrayList<BloodPressureEntry> dataSet;
	
	private TableLayout tl;
	private TableRow tr;
	private TextView tvDate, tvSystolic, tvDiastolic, tvPulse;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/**
		 * Adding our layout to parent class frame layout.
		 */
		getLayoutInflater().inflate(R.layout.activity_blood, frameLayout);

//		((ImageView)findViewById(R.id.image_view)).setBackgroundResource(R.drawable.image2);
		/**
		 * Setting title and itemChecked
		 */

		//setContentView(R.layout.activity_blood);
		mDrawerList.setItemChecked(position, true);
		setTitle(listArray[position]);
		
//		readData();
		tl = (TableLayout) findViewById(R.id.dataTable);
		displayTable();
		// 

	}

	private void displayTable() {
		tl.removeAllViews();
		addHeaders();
		addData();
	}
	
	public void addHeaders(){
		 //tvDate, tvSystolic, tvDiastolic, tvPulse;
        /** Create a TableRow dynamically **/
       tr = new TableRow(this);
       tr.setLayoutParams(new LayoutParams(
               LayoutParams.FILL_PARENT,
               LayoutParams.WRAP_CONTENT));

       /** Creating a TextView to add to the row **/
       tvDate = new TextView(this);
       tvDate.setText("Date");
       tvDate.setTextColor(Color.GRAY);
       tvDate.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
       tvDate.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
       tvDate.setPadding(5, 5, 5, 0);
       tr.addView(tvDate);  // Adding textView to tablerow.

       /** Creating another textview **/
       tvSystolic = new TextView(this);
       tvSystolic.setText("Systolic");
       tvSystolic.setTextColor(Color.GRAY);
       tvSystolic.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
       tvSystolic.setPadding(5, 5, 5, 0);
       tvSystolic.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
       tr.addView(tvSystolic); // Adding textView to tablerow.
       
       /** Creating another textview **/
       tvDiastolic = new TextView(this);
       tvDiastolic.setText("Diastolic");
       tvDiastolic.setTextColor(Color.GRAY);
       tvDiastolic.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
       tvDiastolic.setPadding(5, 5, 5, 0);
       tvDiastolic.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
       tr.addView(tvDiastolic); // Adding textView to tablerow.
       
       /** Creating another textview **/
       tvPulse = new TextView(this);
       tvPulse.setText("Pulse");
       tvPulse.setTextColor(Color.GRAY);
       tvPulse.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
       tvPulse.setPadding(5, 5, 5, 0);
       tvPulse.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
       tr.addView(tvPulse); // Adding textView to tablerow.

       // Add the TableRow to the TableLayout
       tl.addView(tr, new TableLayout.LayoutParams(
               LayoutParams.FILL_PARENT,
               LayoutParams.WRAP_CONTENT));

       // we are adding two textviews for the divider because we have two columns
       tr = new TableRow(this);
       tr.setLayoutParams(new LayoutParams(
               LayoutParams.FILL_PARENT,
               LayoutParams.WRAP_CONTENT));

       /** Creating another textview **/
       TextView divider = new TextView(this);
       divider.setText("-------------");
       divider.setTextColor(Color.GREEN);
       divider.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
       divider.setPadding(5, 0, 0, 0);
       divider.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
       tr.addView(divider); // Adding textView to tablerow.

       TextView divider2 = new TextView(this);
       divider2.setText("-------------");
       divider2.setTextColor(Color.GREEN);
       divider2.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
       divider2.setPadding(5, 0, 0, 0);
       divider2.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
       tr.addView(divider2); // Adding textView to tablerow.
       
       TextView divider3 = new TextView(this);
       divider3.setText("-------------");
       divider3.setTextColor(Color.GREEN);
       divider3.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
       divider3.setPadding(5, 0, 0, 0);
       divider3.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
       tr.addView(divider3);
       
       TextView divider4 = new TextView(this);
       divider4.setText("-------------");
       divider4.setTextColor(Color.GREEN);
       divider4.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
       divider4.setPadding(5, 0, 0, 0);
       divider4.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
       tr.addView(divider4);

       // Add the TableRow to the TableLayout
       tl.addView(tr, new TableLayout.LayoutParams(
               LayoutParams.FILL_PARENT,
               LayoutParams.WRAP_CONTENT));
   }

	public void addData(){
		readData();
		 //tvDate, tvSystolic, tvDiastolic, tvPulse;
		for(BloodPressureEntry b : dataSet){
			String dataSting = "";
            /** Create a TableRow dynamically **/
            tr = new TableRow(this);
            tr.setLayoutParams(new LayoutParams(
                    LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));
 
            /** Creating a TextView to add to the row **/
            tvDate = new TextView(this);
            tvDate.setText(b.getDateString());
            tvDate.setTextColor(Color.RED);
            tvDate.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tvDate.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
            tvDate.setPadding(5, 5, 5, 5);
            tr.addView(tvDate);  // Adding textView to tablerow.
 
            tvSystolic = new TextView(this);
            dataSting = ""+b.getSystolic();
            tvSystolic.setText(dataSting);
            tvSystolic.setTextColor(Color.BLUE);
            tvSystolic.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tvSystolic.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
            tvSystolic.setPadding(25, 5, 5, 5);
            tr.addView(tvSystolic);
            
            tvDiastolic = new TextView(this);
            dataSting = ""+b.getDiastolic();

            tvDiastolic.setText(dataSting);
            tvDiastolic.setTextColor(Color.BLUE);
            tvDiastolic.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tvDiastolic.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
            tvDiastolic.setPadding(25, 5, 5, 5);
            tr.addView(tvDiastolic);
            
            tvPulse = new TextView(this);
            dataSting = (""+b.getPulse());
            tvPulse.setText(dataSting);
            tvPulse.setTextColor(Color.BLUE);
            tvPulse.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tvPulse.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
            tvPulse.setPadding(25, 5, 5, 5);
            tr.addView(tvPulse);
 
            // Add the TableRow to the TableLayout
            tl.addView(tr, new TableLayout.LayoutParams(
                    LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));
        }
    }
	
	private void testW() {
		dataSet = new ArrayList<BloodPressureEntry>();

		BloodPressureEntry bp1 = new BloodPressureEntry(2015, 2, 13, 123, 56, 78);
		BloodPressureEntry bp2 = new BloodPressureEntry(2015, 2, 12, 123, 56, 758);
		dataSet.add(bp1);
		dataSet.add(bp2);
		
		
	}

	public void clearData(View v){
		dataSet.clear();
		updateData();
	}

	public void addEntry(View v) {
		// get prompts.xml view
		LayoutInflater layoutInflater = LayoutInflater.from(this);


		View promptView = layoutInflater.inflate(R.layout.input_blood, null);
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

		// set prompts.xml to be the layout file of the alertdialog builder
		alertDialogBuilder.setView(promptView);

		final NumberPicker iSystolic = (NumberPicker) promptView
				.findViewById(R.id.np_systolic);
		final NumberPicker iDiastolic = (NumberPicker) promptView
				.findViewById(R.id.np_diastolic);
		final NumberPicker iPulse = (NumberPicker) promptView
				.findViewById(R.id.np_pulse);
		
		iSystolic.setMaxValue(300);
		iSystolic.setMinValue(10);
		iSystolic.setValue(120);
		iSystolic.setWrapSelectorWheel(true); 
		
		iDiastolic.setMaxValue(300);
		iDiastolic.setMinValue(10);
		iDiastolic.setValue(80);
		iDiastolic.setWrapSelectorWheel(true); 
		
		iPulse.setMaxValue(200);
		iPulse.setMinValue(10);
		iPulse.setValue(60);
		iPulse.setWrapSelectorWheel(true); 

		// setup a dialog window
		alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// get user input and set it to result

						int nSystolic = iSystolic.getValue();
						int nDiastolic = iDiastolic.getValue();
						int nPulse = iPulse.getValue();
						writeEntry(nSystolic, nDiastolic, nPulse);
					}
				})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});

		// create an alert dialog
		AlertDialog alertD = alertDialogBuilder.create();

		alertD.show();
	}

	protected void writeEntry(int nSystolic, int nDiastolic, int nPulse) {
		// TODO Auto-generated method stub
		Calendar c = Calendar.getInstance(); 
		int nYear = c.get(Calendar.YEAR);
		int nMonth = c.get(Calendar.MONTH);
		int nDay = c.get(Calendar.DAY_OF_MONTH);
		BloodPressureEntry data = 
				new BloodPressureEntry(nYear, nMonth, nDay,nSystolic, nDiastolic, nPulse);
		String dateString = ""+ nYear + "-" + nMonth + "-" + nDay;
//		Toast.makeText(getBaseContext(),
//				"Try to write data into file:"
//				+ "\n" + data.toString()
//				+ "\n On " + dateString
//				+ "\n Systolic: "+nSystolic
//				+ "\n Diastolic: "+nDiastolic
//				+ "\n Pulse: "+nPulse
//				, Toast.LENGTH_SHORT)
//				.show();
		dataSet.add(data);
		updateData();
	}
	
	
	private void readData() {		

        dataSet = new ArrayList<BloodPressureEntry>();
		try {
            InputStream inputStream = openFileInput(BLOOD_PRESSURE_FILE);
            if ( inputStream != null ) {
                InputStreamReader isr = new InputStreamReader(inputStream);
                BufferedReader br = new BufferedReader(isr);
                String line = "";
                while ( ( line = br.readLine()) != null ) {
                	StringTokenizer st = new StringTokenizer(line, " ");
    				int year = Integer.parseInt(st.nextToken());
    				int month = Integer.parseInt(st.nextToken());
    				int day = Integer.parseInt(st.nextToken());
    				int systolic = Integer.parseInt(st.nextToken());
    				int diastolic = Integer.parseInt(st.nextToken());
    				int pulse = Integer.parseInt(st.nextToken());
    				BloodPressureEntry bpe = 
    						new BloodPressureEntry(year, month, day, systolic, diastolic, pulse);
    				dataSet.add(bpe);
                }
                inputStream.close();
                br.close();
    			if(dataSet.isEmpty()){
    				Toast.makeText(getBaseContext(),
    						"file is empty"
    						, Toast.LENGTH_SHORT)
    						.show();
    			}else{
//    				Toast.makeText(getBaseContext(),
//    						"Number of entries: "+dataSet.size()
//    						, Toast.LENGTH_SHORT)
//    						.show();

                    Collections.sort(dataSet);
    			}
            }else{
				Toast.makeText(getBaseContext(),
						"file is not there"
						, Toast.LENGTH_SHORT)
						.show();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("Reading", "File not found: " + e.toString());

			Toast.makeText(getBaseContext(),
					"File not found: "+ e.toString()
					, Toast.LENGTH_SHORT)
					.show();
        } catch (IOException e) {
            Log.e("Reading", "Can not read file: " + e.toString());

			Toast.makeText(getBaseContext(),
					"Can not read file: "+ e.toString()
					, Toast.LENGTH_SHORT)
					.show();
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Toast.makeText(getBaseContext(),
					"error in reading file: "+ e.toString()
					, Toast.LENGTH_SHORT)
					.show();
		}
		
	}
	
	private void updateData(){
		try {
			FileOutputStream fileout = openFileOutput(BLOOD_PRESSURE_FILE,
					MODE_PRIVATE);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fileout));
			for(BloodPressureEntry b : dataSet){
				bw.write(b.toFileString());
				bw.newLine();
				Log.d("BA", b.toString());
			}

			// display file saved message
//			Toast.makeText(getBaseContext(),
//					"File saved successfully!", Toast.LENGTH_SHORT)
//					.show();
			bw.flush();
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			displayTable();
		}
	}
	
}
