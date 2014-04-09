package mx.com.underrito.metro.free.globalvalues;
public class MetroLicenseCheckerCallback //implements LicenseCheckerCallback
{
		/*private Activity			activity;
		public MetroLicenseCheckerCallback(Activity activity)
		{
			this.activity				= activity;
		}

			@Override
			public void allow() {
				if(activity.isFinishing())
				{
					return;
				}
				((MetroGlobalValues)activity.getApplicationContext()).getMetroUtil().getSharedPreferences(activity.getBaseContext()).edit().putString(MetroConstant.PREFS_METRO, MetroConstant.SALT.toString()).commit();			
				((MetroGlobalValues)activity.getApplicationContext()).getMetroCheckLicense().displayResult(activity,activity.getString(R.string.successLicense));
				Intent intent= new Intent(activity,MetroMenu.class);				
				activity.startActivity(intent);
			}

			@Override
			public void applicationError(ApplicationErrorCode errorCode) {
				if(activity.isFinishing())
				{
					return;
				}
				((MetroGlobalValues)activity.getApplicationContext()).getMetroCheckLicense().displayResult(activity,activity.getString(R.string.errorLicense));
				activity.finish();
			}

			@Override
			public void dontAllow() 
			{
				if(activity.isFinishing())
				{
					return;
				}
				((MetroGlobalValues)activity.getApplicationContext()).getMetroCheckLicense().displayResult(activity,activity.getString(R.string.noLicense));
				activity.finish();
			}
			*/
}
