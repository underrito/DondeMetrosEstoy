package mx.com.underrito.metro.free.activity;
import com.google.ads.AdView;
import mx.com.underrito.metro.free.R;
import mx.com.underrito.metro.free.constant.MetroConstant;
import mx.com.underrito.metro.free.globalvalues.MetroGlobalValues;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
public class MetroMain extends Activity 
{
	AdView			adView;
	LinearLayout	llBannerAD;
	ImageView		imageMain;
	MetroMain		metroMain;
	Typeface		typeFace;
  
	@Override
	public void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.metromain);
		typeFace					= ((MetroGlobalValues)getApplicationContext()).getMetroUtil().getTypeFace();
		if(((MetroGlobalValues)getApplicationContext()).getMetroUtil().isMultitouchDevice(getPackageManager()))
			PreferenceManager.setDefaultValues(this, R.layout.metropreferencia, false);
		else
			PreferenceManager.setDefaultValues(this, R.layout.metropreferencia, false);
		imageMain			= (ImageView)findViewById(R.id.imageSplash);		
		metroMain			= this;
		if(MetroConstant.ADMOB_MODE)
		{
		       adView	= ((MetroGlobalValues)getApplicationContext()).getMetroUtilAdMob().displayAd(this, R.id.llBannerAD);
		       //Intent intent		= new Intent(this,MetroMenu.class);				
		       Intent intent		= new Intent(this,MetroMenuFragment.class);
		       startActivity(intent);
		}
       //else
		//	this.checkForLicense();
		imageMain.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Intent	intent	= new Intent(metroMain,MetroMenu.class);
				Intent	intent	= new Intent(metroMain,MetroMenuFragment.class);
				startActivity(intent);
				
			}
		});
	}
	
	
	 @Override
	  public void onDestroy() {
		 if(MetroConstant.ADMOB_MODE)
	    	  adView.destroy();	    
	    super.onDestroy();
	  }
	
	/* private void checkForLicense()
	 {
		 String license="";
		 if(((MetroGlobalValues)getApplicationContext()).getMetroUtil().getSharedPreferences(getBaseContext()).contains(MetroConstant.PREFS_METRO))
		 {
	        	license	= ((MetroGlobalValues)getApplicationContext()).getMetroUtil().getSharedPreferences(getBaseContext()).getString(MetroConstant.PREFS_METRO, license);
	        	if(!license.contentEquals(MetroConstant.SALT.toString()))        	
	        	{
	        		((MetroGlobalValues)getApplicationContext()).getMetroCheckLicense().checkLicense(metroMain);
	        	}
	        	else
	        	{
	        		Intent intent= new Intent(metroMain,MetroMenu.class);				
					startActivity(intent);
	        	}
	        		
	        }
	        else
	        {
	        	((MetroGlobalValues)getApplicationContext()).getMetroCheckLicense().checkLicense(metroMain);
	        }
	 }*/
}
