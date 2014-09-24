package mx.com.underrito.metro.free.activity;
import java.util.ArrayList;
import com.google.android.gms.ads.AdView;
import mx.com.underrito.metro.free.R;
import mx.com.underrito.metro.free.adapter.MetroLineaSpinnerAdapter;
import mx.com.underrito.metro.free.constant.MetroConstant;
import mx.com.underrito.metro.free.globalvalues.MetroGlobalValues;
import mx.com.underrito.metro.free.model.MetroJbEstacion;
import mx.com.underrito.metro.free.model.MetroJbLinea;
import mx.com.underrito.metro.free.model.MetroJbSegmento;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class MetroBuscaLinea extends Activity 
{
    /** Called when the activity is first created. */
    Spinner 							spinnerDestinoLinea;
    ImageView							btnOkLinea;
    ImageView							btnOkLineaRuta;	
	MetroBuscaLinea 				seleccionaLinea;
	MetroLineaSpinnerAdapter			metroCustomSpinnerAdapter;
	Bundle								bundle;
	ArrayList<MetroJbEstacion>			listaEstacion;
	ArrayList<MetroJbSegmento>			listaSegmento;
	String								lineaSeleccionada;
	AdView								adView;
	LinearLayout						llBannerAD;
	private 	boolean					reloadMap	= false;
	Typeface							typeFace;


    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
	    requestWindowFeature(Window.FEATURE_NO_TITLE);    	
        setContentView(R.layout.metrobuscalinea);
        typeFace					= ((MetroGlobalValues)getApplicationContext()).getMetroUtil().getTypeFace();        
        spinnerDestinoLinea			= (Spinner)this.findViewById(R.id.SpinnerLinea);
        btnOkLinea					= (ImageView)this.findViewById(R.id.btnOkLinea);
        btnOkLineaRuta				= (ImageView)this.findViewById(R.id.btnOkLineaMapa);
        listaSegmento				= new ArrayList<MetroJbSegmento>();
        lineaSeleccionada			= "";
        metroCustomSpinnerAdapter	=	new  MetroLineaSpinnerAdapter(this, R.layout.metrospinneritemcomplex,R.id.textComplexItem,((MetroGlobalValues)getApplicationContext()).getMetroJbRed().getListaLinea());
        metroCustomSpinnerAdapter.setDropDownViewResource(R.layout.metrospinneritemcomplex);
        spinnerDestinoLinea.setAdapter(metroCustomSpinnerAdapter);

        seleccionaLinea	= this;
		if(MetroConstant.ADMOB_MODE)
		       adView	= ((MetroGlobalValues)getApplicationContext()).getMetroUtilAdMob().displayAd(this, R.id.llBannerAD);
        spinnerDestinoLinea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) 
			{
				String valueDestino	=	((MetroJbLinea)arg0.getAdapter().getItem(arg2)).getNombre();
				for(MetroJbLinea lineaDestino:((MetroGlobalValues)getApplicationContext()).getMetroJbRed().getListaLinea())
				{
					
					if(lineaDestino.getNombre().equals(valueDestino))
					{
						lineaSeleccionada				= lineaDestino.getId();
						seleccionaLinea.listaEstacion	= ((MetroGlobalValues)getApplicationContext()).getMetroUtil().getRutaLinea(((MetroGlobalValues)getApplicationContext()).getMetroJbRed(),lineaDestino);
					}
				}
					
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			
			}
        });
        btnOkLinea.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				getResult(false);
			}
		});
        btnOkLineaRuta.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				getResult(true);
			}
		});
	
    }
    @Override
    public void onDestroy() {
    	if(MetroConstant.ADMOB_MODE)
      	  adView.destroy();
      super.onDestroy();
    }
    private void getResult(boolean result)
    {
    	Intent	intent;
    	this.reloadMap	= false;
		if(listaEstacion!=null)
		{
			bundle		= new Bundle();
			if(result)
				intent		= new Intent(seleccionaLinea,MetroMapa.class);
			else
			{
				intent		= new Intent(seleccionaLinea,MetroRuta.class);
				bundle.putString(MetroConstant.LINEA_PARAM, lineaSeleccionada);
				bundle.putParcelableArrayList(MetroConstant.SEGMENTO_PARAM,seleccionaLinea.listaSegmento);
			}
			bundle.putParcelableArrayList(MetroConstant.RUTA_PARAM,seleccionaLinea.listaEstacion);
			intent.putExtras(bundle);
			startActivity(intent);
		}		
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
  				this.reloadMap			= true;
  				intent				= new  Intent(this,MetroPreferencia.class);
  				startActivity(intent);
  				return true;
  		}
  		return super.onOptionsItemSelected(item);
      }
      
      
      @Override
      protected void onSaveInstanceState(Bundle savedInstanceState)
      {
     	 savedInstanceState.putBoolean("reloadMap", this.reloadMap);
    	 savedInstanceState.putInt("spinnerDestinoValue", this.spinnerDestinoLinea.getSelectedItemPosition());

      }
       
       
      @Override
      protected void onRestoreInstanceState(Bundle savedInstanceState)
      {
     	this.reloadMap = savedInstanceState.getBoolean("reloadMap");
     	 if(this.spinnerDestinoLinea.getAdapter().getItem(savedInstanceState.getInt("spinnerDestinoValue")) !=null)
     	 {
	   	 	this.spinnerDestinoLinea.setSelection(savedInstanceState.getInt("spinnerDestinoValue"));
	   	 	this.spinnerDestinoLinea.setSelected(true);
	     }

      }
      
	@Override
	protected void onResume() {
		super.onResume();
    	if(this.reloadMap)
    	{
    		int destinoTmp=this.spinnerDestinoLinea.getSelectedItemPosition();
    		metroCustomSpinnerAdapter.clear();
			spinnerDestinoLinea.clearDisappearingChildren();
			((MetroGlobalValues)getApplicationContext()).getMetroJbRed().reload();
		    metroCustomSpinnerAdapter	=	new  MetroLineaSpinnerAdapter(this, R.layout.metrospinneritemcomplex,R.id.textComplexItem,((MetroGlobalValues)getApplicationContext()).getMetroJbRed().getListaLinea());
	        metroCustomSpinnerAdapter.setDropDownViewResource(R.layout.metrospinneritemcomplex);
	        spinnerDestinoLinea.setAdapter(metroCustomSpinnerAdapter);

	        
	        this.reloadMap	= false;
	        if(this.spinnerDestinoLinea.getAdapter().getCount()>destinoTmp)
	        {
	   	 		this.spinnerDestinoLinea.setSelection(destinoTmp);
	   	 		this.spinnerDestinoLinea.setSelected(true);
	        }

    	}
	}
	
}