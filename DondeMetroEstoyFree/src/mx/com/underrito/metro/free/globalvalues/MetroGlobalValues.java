package mx.com.underrito.metro.free.globalvalues;
import android.app.Application;
public class MetroGlobalValues extends Application
{
	public static MetroGlobalValues 	metroGlobalValues;
	private static MetroRed 			metroJbRed; 
	private static MetroUtil			metroUtil;
	private static MetroUtilAdMob		metroUtilAdMob;
	private static MetroCheckLicense	metroCheckLicense;
	private static MetroAppRatter		metroAppRatter;
	public  MetroAppRatter getMetroAppRatter() {
		return metroAppRatter;
	}
	public  void setMetroAppRatter(MetroAppRatter metroAppRatter) {
		MetroGlobalValues.metroAppRatter = metroAppRatter;
	}
	public MetroUtil getMetroUtil() {
		return metroUtil;
	}
	public  MetroCheckLicense getMetroCheckLicense() {
		return metroCheckLicense;
	}
	public  void setMetroCheckLicense(MetroCheckLicense metroCheckLicense) {
		MetroGlobalValues.metroCheckLicense = metroCheckLicense;
	}
	public void setMetroUtil(MetroUtil metroUtil) {
		MetroGlobalValues.metroUtil = metroUtil;
	}
	public MetroUtilAdMob getMetroUtilAdMob() {
		return metroUtilAdMob;
	}
	public void setMetroUtilAdMob(MetroUtilAdMob metroUtilAdMob) {
		MetroGlobalValues.metroUtilAdMob = metroUtilAdMob;
	}
	public MetroRed getMetroJbRed() {
		return MetroGlobalValues.metroJbRed ;
	}
	public void setMetroJbRed(MetroRed metroJbRed) {
		MetroGlobalValues.metroJbRed = metroJbRed;
	}
	
	@Override
	public void onCreate() 
	{
		super.onCreate();
		if(metroGlobalValues	== null)
			metroGlobalValues	= new MetroGlobalValues();
		metroGlobalValues 		= this;  
		metroUtil				= new MetroUtil(this.getApplicationContext()); 
		metroUtilAdMob			= new MetroUtilAdMob();
		metroCheckLicense		= new MetroCheckLicense();
		metroJbRed				= new MetroRed(this.getApplicationContext(),this.getBaseContext());
		metroAppRatter			= new MetroAppRatter();
	}
	
}
