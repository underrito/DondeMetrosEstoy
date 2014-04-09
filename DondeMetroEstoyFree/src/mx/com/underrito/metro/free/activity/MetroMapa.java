package mx.com.underrito.metro.free.activity;
import java.util.ArrayList;

import mx.com.underrito.metro.free.R;
import mx.com.underrito.metro.free.constant.MetroConstant;
import mx.com.underrito.metro.free.globalvalues.MetroGlobalValues;
import mx.com.underrito.metro.free.model.MetroJbEstacion;
import mx.com.underrito.metro.free.view.MetroMapaView;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ZoomControls;
public class MetroMapa extends Activity

{
	ArrayList<MetroJbEstacion> 		listaEstacion;
	MetroMapaView					metroMapaView;
	MetroJbEstacion					estacionTraza;
	Bundle 							bundleIntent;
	ZoomControls					zoomControls;
	Typeface						typeFace;
	@Override	
	public void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.metromapa);
		typeFace						= ((MetroGlobalValues)getApplicationContext()).getMetroUtil().getTypeFace();
		listaEstacion					= new ArrayList<MetroJbEstacion>();
		metroMapaView					= (MetroMapaView)findViewById(R.id.metroMapaView);
		zoomControls					= (ZoomControls)findViewById(R.id.zoomControls2);
		bundleIntent					= this.getIntent().getExtras();
		if(!((MetroGlobalValues)getApplicationContext()).getMetroUtil().isMultitouchDevice(getPackageManager()))
			zoomControls.setVisibility(View.VISIBLE);
		else
		{
			//if(((MetroGlobalValues)getBaseContext().getApplicationContext()).getMetroUtil().getSharedPreferences(getBaseContext()).getBoolean(MetroConstant.PREFERENCIA_BOTONZOOM,false))
				zoomControls.setVisibility(View.VISIBLE);
			//else
				//zoomControls.setVisibility(View.INVISIBLE);
		}
		if(bundleIntent!=null)
		{			
		
			estacionTraza		= bundleIntent.getParcelable(MetroConstant.ESTACION_TRAZA);
			if(estacionTraza!=null)
			{
				metroMapaView.setEstacion(estacionTraza);
			}
			else
			{
				listaEstacion		= bundleIntent.getParcelableArrayList(MetroConstant.RUTA_PARAM);
				metroMapaView.setRuta(listaEstacion);//		= new MetroDibujaRedView(this, listaEstacion);
			}
		}
		zoomControls.setOnZoomInClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				metroMapaView.onZoom(true);
				
			}
		});
		zoomControls.setOnZoomOutClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				metroMapaView.onZoom(false);
			}
		});

	} 
	@Override
	public void onConfigurationChanged(Configuration newConfig)
	{
	    super.onConfigurationChanged(newConfig);
	    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}
	
}
