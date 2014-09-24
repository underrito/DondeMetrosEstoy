package mx.com.underrito.metro.free.activity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import com.google.android.gms.ads.AdView;
import mx.com.underrito.metro.free.R;
import mx.com.underrito.metro.free.constant.MetroConstant;
import mx.com.underrito.metro.free.globalvalues.MetroGlobalValues;
//import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

//@SuppressLint("NewApi")
public class MetroAbout extends Activity 
{
	AdView			adView;
	LinearLayout	llBannerAD;
	TextView		txtLinkFace;
	TextView		txtversion;
	TextView		txtdate;
	Typeface		typeFace;
	TextView		lbldev;
	TextView		txtdev;
	TextView		lbldate;
	TextView		lblversion;
	TextView		lblmail;
	TextView		txtmail;
	TextView		lblcountry;
	TextView		txtcountry;
	TextView		lblagradecimientos;
	TextView		txtagradecimientos;
	TextView		lblLinkFace;

	@Override
	public void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		typeFace			= ((MetroGlobalValues)getApplicationContext()).getMetroUtil().getTypeFace();
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.metroabout);
		txtLinkFace			= (TextView)this.findViewById(R.id.txtLinkFace);
		txtversion			= (TextView)this.findViewById(R.id.txtversion);
		txtdate				= (TextView)this.findViewById(R.id.txtdate);
		lbldev				= (TextView)this.findViewById(R.id.lbldev);
		txtdev				= (TextView)this.findViewById(R.id.txtdev);
		lbldate				= (TextView)this.findViewById(R.id.lbldate);		
		lblversion			= (TextView)this.findViewById(R.id.lblversion);
		lblmail				= (TextView)this.findViewById(R.id.lblmail);
		txtmail				= (TextView)this.findViewById(R.id.txtmail);
		lblcountry			= (TextView)this.findViewById(R.id.lblcountry);
		txtcountry			= (TextView)this.findViewById(R.id.txtcountry);
		lblagradecimientos	= (TextView)this.findViewById(R.id.lblagradecimientos);
		txtagradecimientos	= (TextView)this.findViewById(R.id.txtagradecimientos);
		lblLinkFace			= (TextView)this.findViewById(R.id.lblLinkFace);
		
		txtLinkFace.setTypeface(typeFace);
		txtversion.setTypeface(typeFace);
		txtversion.setTypeface(typeFace);
		lbldev.setTypeface(typeFace);
		txtdev.setTypeface(typeFace);
		txtdate.setTypeface(typeFace);
		lbldate.setTypeface(typeFace);
		lblversion.setTypeface(typeFace);
		lblmail.setTypeface(typeFace);
		txtmail.setTypeface(typeFace);
		lblcountry.setTypeface(typeFace);
		txtcountry.setTypeface(typeFace);
		lblagradecimientos.setTypeface(typeFace);
		txtagradecimientos.setTypeface(typeFace);
		lblLinkFace.setTypeface(typeFace);
		txtLinkFace.setAutoLinkMask(0);
		txtLinkFace.setText(getText(R.string.txtlinkface));
		Pattern pattern = Pattern.compile("^"+getText(R.string.txtlinkface));
		String scheme = "http://www.facebook.com/groups/167077603346266/";
		Linkify.addLinks(txtLinkFace, pattern, scheme);
		txtLinkFace.setMovementMethod(LinkMovementMethod.getInstance());
		String 			version=""; 
		String 			dateString		="";
		if(MetroConstant.ADMOB_MODE)
		       adView	= ((MetroGlobalValues)getApplicationContext()).getMetroUtilAdMob().displayAd(this, R.id.llBannerAD);

		try
		{
			PackageManager 	manager 	= getBaseContext().getPackageManager();
			PackageInfo 	info 		= manager.getPackageInfo(getBaseContext().getPackageName(), 0);
			version 	= info.versionName;
			SimpleDateFormat formatter = new SimpleDateFormat("d / MMMM / yyyy");
			dateString = formatter.format(new Date(info.lastUpdateTime));
		}
		catch(Exception e)
		{
			version 	= "1.0.0";
			dateString	= "01/01/2012";

		}
		txtversion.setText(version);
		txtdate.setText(dateString);


	}
	 @Override
	  public void onDestroy() {
		 super.onDestroy();
		 if(MetroConstant.ADMOB_MODE)
	    	  adView.destroy();
	    
	  }
	
}
