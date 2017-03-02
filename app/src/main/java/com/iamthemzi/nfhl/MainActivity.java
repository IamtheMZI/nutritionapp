package com.iamthemzi.nfhl;

import android.app.*;
import android.os.*;
import android.view.View.*;
import android.view.*;
import android.widget.*;
import android.webkit.*;
import android.graphics.*;

import android.content.Context;
import android.net.ConnectivityManager;



class DetectConnection {             
	public static boolean checkInternetConnection(Context context) {   

		ConnectivityManager con_manager = (ConnectivityManager) 
			context.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (con_manager.getActiveNetworkInfo() != null
			&& con_manager.getActiveNetworkInfo().isAvailable()
			&& con_manager.getActiveNetworkInfo().isConnected()) {
			return true;
		} else {
			return false;
		}
	}
}


public class MainActivity extends Activity 
{
	volatile double Pal = 30.0;
	ViewFlipper vf;
	String name = "ব্যবহারকারী";
	WebView wv;
	private EditText weight;
	private EditText height;
	TextView tv,tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		//vf = (ViewFlipper) findViewById(R.id.
    }
	
	protected void signin(){
		EditText username = (EditText) findViewById(R.id.username);
		EditText password = (EditText) findViewById(R.id.password);
		if(username.getText().toString().equals("")){
			setContentView(R.layout.menu);
		}
	}
	
	protected void showAccount(){
		setContentView(R.layout.account);
	}
	
	protected void showBMI(){
		setContentView(R.layout.bmi);
		weight = (EditText) findViewById(R.id.bmiWeightText1);
		height = (EditText) findViewById(R.id.bmiHeightText1);
		tv = (TextView) findViewById(R.id.bmiOutput1);
	}
	
	protected void calculateBMI(){
		double bmi;
		if(weight.getText().toString().equals("") || height.getText().toString().equals("")){
			tv.setText("আবার চেষ্ঠা করুন");
			return;
		}
		double wt = Double.parseDouble(weight.getText().toString());
		double ht = Double.parseDouble(height.getText().toString());
		bmi = wt/(ht*ht);
		if(bmi < 18.5){
			tv.setTextColor(Color.RED);
			tv.setText("কম ওজন");
		} else if(bmi > 25){
			tv.setTextColor(Color.RED);
			tv.setText("স্থূলতা");
		} else if(bmi >= 18.5 && bmi <=23){
			tv.setTextColor(Color.GREEN);
			tv.setText("সঠিক ওজন");
		}else {
			tv.setTextColor(Color.MAGENTA);
			tv.setText("মাত্রাতিরিক্ত");
		}
		tv.append("\n"+String.format("%.2f",bmi));
	}
	
	protected void showIBW(){
		setContentView(R.layout.ibw);
		height = (EditText) findViewById(R.id.ibwHeightText1);
		tv = (TextView) findViewById(R.id.ibwOutputebw);
		tv2 = (TextView) findViewById(R.id.ibwOutputibw);
	}
	
	protected void calculateIBW(){
		double ibw,ebwup,ebwlow;
		if(height.getText().toString().equals("")){
			tv.setText("আবার চেষ্ঠা করুন");
			return;
		}
		//double wt = Double.parseDouble(weight.getText().toString());
		double ht = Double.parseDouble(height.getText().toString());
		ibw = 20.75*(ht*ht);
		ebwup = 23*ht*ht;
		ebwlow = 18.5*ht*ht;
		tv.setText(""+String.format("%.2f - %.2f",ebwlow,ebwup));
		tv2.setText(""+String.format("%.2f",ibw));
	}
	
	protected void calculateCalorie(){
		double ibw,ebwup,ebwlow;
		if(height.getText().toString().equals("")){
			tv.setText("আবার চেষ্ঠা করুন");
			return;
		}
		//double wt = Double.parseDouble(weight.getText().toString());
		double ht = Double.parseDouble(height.getText().toString());
		ibw = Pal*ht;
		tv.setText(""+String.format("%.2f",ibw));
	}
	
	protected void showcalreq(){
		setContentView(R.layout.calreq);
		height = (EditText) findViewById(R.id.calreqIBW1);
		tv = (TextView) findViewById(R.id.calreqOutput1);
	}
	
	protected void showMore(String url){
		//if (!DetectConnection.checkInternetConnection(this)) {
		//	Toast.makeText(getApplicationContext(), "No Internet!", Toast.LENGTH_SHORT).show();
		//} else {  
			setContentView(R.layout.webview);
			wv = (WebView) findViewById(R.id.wv1);
			wv.getSettings().setJavaScriptEnabled(true);
			wv.setWebViewClient(new WebViewClient());
			wv.loadUrl(url);
		//}
	}
	
	public void onClick(View view){
		switch(view.getId()){
			case R.id.logo:
				setContentView(R.layout.calorie);
				//showBMI();
				break;
			case R.id.signinButton:
				signin();
				break;
			case R.id.menuAccountButton:
				setContentView(R.layout.account);
				break;
			case R.id.menuCalorieButton:
				setContentView(R.layout.calorie);
				break;
			case R.id.back:
				setContentView(R.layout.calorie);
				break;
			case R.id.menuMoreinfoButton:
				{String url = "http://nfhlbd.wordpress.com";
				showMore(url);}
				break;
			case R.id.calorieBMI:
				showBMI();
				break;
			case R.id.bmiCalculate1:
				calculateBMI();
				break;
			case R.id.calorieIBW:
				showIBW();
				break;
			case R.id.ibwCalculate1:
				calculateIBW();
				break;
			case R.id.calorieReq:
				showcalreq();
				break;
			case R.id.calorieMore:
				{String url = "http://iamthemzi.com/samihaapp/104.html";
				showMore(url);}
				break;
				
			case R.id.calreqHalka:
				Pal = 30.0;
				calculateCalorie();
				break;
			case R.id.calreqMed:
				Pal = 40.0;
				calculateCalorie();
				break;
			case R.id.calreqHeavy:
				Pal = 50.0;
				calculateCalorie();
				break;
			case R.id.calorieCmnFood:
				{String url = "http://iamthemzi.com/samihaapp/101.html";
				showMore(url);}
				break;
			case R.id.calorieGuide:
				{String url = "http://iamthemzi.com/samihaapp/102.html";
				showMore(url);}
				break;
			case R.id.calorieMalNut:
				{String url = "http://iamthemzi.com/samihaapp/103.html";
				showMore(url);}
				break;
			default:
				break;
		}
	}
}
