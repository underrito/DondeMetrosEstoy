package mx.com.underrito.metro.free.activity;
import java.util.ArrayList;
import mx.com.underrito.metro.free.R;
import mx.com.underrito.metro.free.adapter.MetroMenuFragmentAdapter;
import mx.com.underrito.metro.free.constant.MetroConstant;
import mx.com.underrito.metro.free.fragment.MetroMenuFragmentItem;
import mx.com.underrito.metro.free.globalvalues.MetroGlobalValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.*;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.ads.AdView;

public class MetroMenuFragment extends FragmentActivity {
    /** Called when the activity is first created. */
	MetroMenuFragment			menuObj;
	Bundle						bundle;
	AdView						adView;
	LinearLayout				llBannerAD;
	ViewPager					viewPager;
	MetroMenuFragmentAdapter	metroMenuFragmentAdapter;
	ArrayList<Fragment>		listFragment	= new ArrayList<Fragment>();
	private MetroMenuFragmentItem				fragment1;
	private MetroMenuFragmentItem	fragment2;
	private MetroMenuFragmentItem	fragment3;
	private MetroMenuFragmentItem	fragment4;
	private MetroMenuFragmentItem	fragment5;
	TextView		menu;
	TextView		menu2;
	Typeface		typeFace;
	MetroMenuFragment metroMenuFragment;
	@Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        metroMenuFragment	= this;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.metromenufragment);
        menu		= (TextView)this.findViewById(R.id.menu);
        menu2		= (TextView)this.findViewById(R.id.menu2);
		typeFace	= ((MetroGlobalValues)getApplicationContext()).getMetroUtil().getTypeFace();
		menu.setTypeface(typeFace);
		menu2.setTypeface(typeFace);

        	 fragment1	= new MetroMenuFragmentItem();
        	 fragment1.setContext(this);
        	 fragment1.setImgResource(R.drawable.metrobuscaestacion);
        	 
        	 fragment2	= new MetroMenuFragmentItem();
        	 fragment2.setContext(this);
        	 fragment2.setImgResource(R.drawable.metrobuscalinea);
        	 
        	 fragment3	= new MetroMenuFragmentItem();
        	 fragment3.setContext(this);
        	 fragment3.setImgResource(R.drawable.metrobuscamapa);
        	 
        	 fragment4	= new MetroMenuFragmentItem();
        	 fragment4.setContext(this);
        	 fragment4.setImgResource(R.drawable.metrobuscanombre);
        	
        	 fragment5	= new MetroMenuFragmentItem();
        	 fragment5.setContext(this);
        	 fragment5.setImgResource(R.drawable.metroabout);
        	 listFragment= new ArrayList<Fragment>();
        listFragment.add(fragment1);
        listFragment.add(fragment2);
        listFragment.add(fragment3);
        listFragment.add(fragment4);
        listFragment.add(fragment5);
        viewPager					= (ViewPager)this.findViewById(R.id.viewPager);
        metroMenuFragmentAdapter	= new MetroMenuFragmentAdapter(getSupportFragmentManager(),listFragment);
        viewPager.setAdapter(metroMenuFragmentAdapter);
	    menuObj						= this;
	  
	if(MetroConstant.ADMOB_MODE)
	       adView	= ((MetroGlobalValues)getApplicationContext()).getMetroUtilAdMob().displayAd(this, R.id.llBannerAD);
	    ((MetroGlobalValues)getApplicationContext()).getMetroAppRatter().validaVota(this);
    }
		        
   @Override
   protected void onSaveInstanceState(Bundle outState) 
   {
	   super.onSaveInstanceState(outState);
	  
	 
   }
   @Override
   protected void onRestoreInstanceState(Bundle savedInstanceState) {
	   super.onRestoreInstanceState(savedInstanceState);
  }
    @Override
    public void onDestroy() {
      
      super.onDestroy();
      //if(MetroConstant.ADMOB_MODE)
    	//  adView.destroy();
    }
    
    @Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
    	super.onCreateOptionsMenu(menu);
    	MenuInflater menuInflame= this.getMenuInflater();
	    menuInflame.inflate(R.layout.metroappmenu, menu);
	    return true;
	}
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
    	Intent 			intent;
    	if(item.getItemId()==R.id.configMenu) 
    	{
				intent				= new  Intent(this,MetroPreferencia.class);
				startActivity(intent);
				return true;
		}
		return super.onOptionsItemSelected(item);
    }
	 @Override
	protected void onResume() 
	 {
		super.onResume();
	}

}