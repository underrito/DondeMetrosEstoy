package mx.com.underrito.metro.free.activity;
import mx.com.underrito.metro.free.R;
import mx.com.underrito.metro.free.globalvalues.MetroGlobalValues;
import android.os.Bundle;
import android.preference.PreferenceActivity;

public class MetroPreferencia extends PreferenceActivity
{

	@Override
	public void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);		
		if(((MetroGlobalValues)getApplicationContext()).getMetroUtil().isMultitouchDevice(getPackageManager()))
			this.addPreferencesFromResource(R.layout.metropreferencia);
		else
			this.addPreferencesFromResource(R.layout.metropreferencia);
		
	}
}
