package mx.com.underrito.metro.free.activity;
import mx.com.underrito.metro.free.R;
import mx.com.underrito.metro.free.constant.MetroConstant;
import mx.com.underrito.metro.free.globalvalues.MetroGlobalValues;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.ads.AdView;

public class MetroMenu extends Activity {
    /** Called when the activity is first created. */
	ImageView			btnCalculaRuta;
	ImageView			btnDetLinea;
	ImageView			btnCalculaRuta2;
	ImageView			btnAbout;
	ImageView			btnMenuBuscar;
	MetroMenu			menuObj;
	Bundle				bundle;
	AdView				adView;
	LinearLayout		llBannerAD;
	Typeface			typeFace;

    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.metromenu);
        typeFace				= ((MetroGlobalValues)getApplicationContext()).getMetroUtil().getTypeFace();
        btnCalculaRuta			= (ImageView)this.findViewById(R.id.calculaRutaImg);
        btnDetLinea				= (ImageView)this.findViewById(R.id.detLineaImg);
        btnCalculaRuta2			= (ImageView)this.findViewById(R.id.calculaRutaImg2);
        btnAbout				= (ImageView)this.findViewById(R.id.acercaImg2);
        btnMenuBuscar			= (ImageView)this.findViewById(R.id.btnMenuBuscar);
	    menuObj					= this;
		if(MetroConstant.ADMOB_MODE)
	       adView	= ((MetroGlobalValues)getApplicationContext()).getMetroUtilAdMob().displayAd(this, R.id.llBannerAD);
	    ((MetroGlobalValues)getApplicationContext()).getMetroAppRatter().validaVota(this);

		btnCalculaRuta.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent= new Intent(menuObj,MetroBuscaEstacion.class);
				startActivity(intent);
				
			}
		});
        btnDetLinea.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				Intent intent= new Intent(menuObj,MetroBuscaLinea.class);				
				startActivity(intent);
				
			}
		});
        btnCalculaRuta2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent= new Intent(menuObj,MetroMapa.class);
				startActivity(intent);
			}
		});
        btnAbout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent= new Intent(menuObj,MetroAbout.class);
				startActivity(intent);
			}
		});
        btnMenuBuscar.setOnClickListener(new View.OnClickListener() {
    		
    		@Override
    		public void onClick(View v) {
    			Intent intent= new Intent(menuObj,MetroBuscaNombre.class);
    			startActivity(intent);
    		}
    	});
        
    }
    @Override
    public void onDestroy() {
      
      super.onDestroy();
      if(MetroConstant.ADMOB_MODE)
    	  adView.destroy();
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
		protected void onResume() {
			super.onResume();
	  			((MetroGlobalValues)getApplicationContext()).getMetroJbRed().reload();
		}
}