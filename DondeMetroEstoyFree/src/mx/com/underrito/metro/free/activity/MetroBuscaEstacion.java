package mx.com.underrito.metro.free.activity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import mx.com.underrito.metro.free.R;
import mx.com.underrito.metro.free.adapter.MetroEstacionSpinnerAdapter;
import mx.com.underrito.metro.free.adapter.MetroLineaSpinnerAdapter;
import mx.com.underrito.metro.free.constant.MetroConstant;
import mx.com.underrito.metro.free.globalvalues.MetroGlobalValues;
import mx.com.underrito.metro.free.model.MetroJbEstacion;
import mx.com.underrito.metro.free.model.MetroJbGrafo;
import mx.com.underrito.metro.free.model.MetroJbLinea;
import mx.com.underrito.metro.free.model.MetroJbResultado;
import mx.com.underrito.metro.free.model.MetroJbSegmento;
import mx.com.underrito.metro.free.util.MetroCalculaRuta;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.android.gms.ads.AdView;
public class MetroBuscaEstacion extends Activity {
    /** Called when the activity is first created. */
    Spinner 							spinnerDestino;
    Spinner 							spinnerOrigen;
    Spinner 							spinnerOrigenEstacion;
    Spinner 							spinnerDestinoEstacion;
    ImageView							btnOk;
    ImageView							btnOkRuta;
	ListView							listView1;	
	MetroJbResultado					resultado;    
	ArrayList<String>					pathList;
	ArrayList<String>					listaEstacionOrigenStr;
	ArrayList<String>					listaEstacionDestinoStr;
	MetroBuscaEstacion 				seleccionaDestino;
	int									estacionOrigen;
	int									estacionDestino;
	Bundle								bundle;
	AdView								adView;
	LinearLayout						llBannerAD;
	MetroJbLinea						lineaOrigenSel;
	MetroJbLinea						lineaDestinoSel;
	MetroLineaSpinnerAdapter			metroCustomSpinnerAdapter;
	private 	boolean					reloadMap	= false;

    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
    	estacionDestino			= 0;
    	estacionOrigen			= 0;
    	pathList				= new ArrayList<String>();
    	lineaDestinoSel			= new MetroJbLinea();
    	lineaOrigenSel			= new MetroJbLinea();

        super.onCreate(savedInstanceState);
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.metrobuscaestacion);
        spinnerDestino			= (Spinner)this.findViewById(R.id.SpinnerDestino);
        spinnerOrigen			= (Spinner)this.findViewById(R.id.SpinnerOrigen);
        spinnerOrigenEstacion	= (Spinner)this.findViewById(R.id.SpinnerOrigenEstacion);
        spinnerDestinoEstacion	= (Spinner)this.findViewById(R.id.SpinnerDestinoEstacion);
        btnOk					= (ImageView)this.findViewById(R.id.btnOk);
        btnOkRuta				= (ImageView)this.findViewById(R.id.btnOkLineaMapa);
        metroCustomSpinnerAdapter	=	new  MetroLineaSpinnerAdapter(this, R.layout.metrospinneritemcomplex,R.id.textComplexItem,((MetroGlobalValues)getApplicationContext()).getMetroJbRed().getListaLinea());
        metroCustomSpinnerAdapter.setDropDownViewResource(R.layout.metrospinneritemcomplex);
        spinnerDestino.setAdapter(metroCustomSpinnerAdapter);
        spinnerOrigen.setAdapter(metroCustomSpinnerAdapter);  
        seleccionaDestino		= this;
		if(MetroConstant.ADMOB_MODE)
		       adView	= ((MetroGlobalValues)getApplicationContext()).getMetroUtilAdMob().displayAd(this, R.id.llBannerAD);

        spinnerDestino.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) 
			{
				ArrayList<MetroJbEstacion> 		listaEstacionDestino;
				listaEstacionDestino			= new ArrayList<MetroJbEstacion>();
				MetroJbLinea valueDestino		= ((MetroJbLinea)arg0.getAdapter().getItem(arg2));
				for(MetroJbLinea lineaDestino : ((MetroGlobalValues)getApplicationContext()).getMetroJbRed().getListaLinea())
				{
					if(lineaDestino.equals(valueDestino))
					{
						lineaDestinoSel			= lineaDestino;
						listaEstacionDestino	= ((MetroGlobalValues)getApplicationContext()).getMetroUtil().getRutaLinea(((MetroGlobalValues)getApplicationContext()).getMetroJbRed(),lineaDestino);
						MetroEstacionSpinnerAdapter adapterEstacionDestino		= new MetroEstacionSpinnerAdapter(seleccionaDestino,R.layout.metrospinneritemsimple,R.id.textSimpleItem,listaEstacionDestino );
						adapterEstacionDestino.setDropDownViewResource(R.layout.metrospinneritemsimple);
						seleccionaDestino.spinnerDestinoEstacion.setAdapter(adapterEstacionDestino);
						break;
					}
				}				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
		});
        spinnerOrigen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) 
			{
				ArrayList<MetroJbEstacion> 	listaEstacionOrigen;
				listaEstacionOrigen			= new ArrayList<MetroJbEstacion>();
				MetroJbLinea valueOrigen	= ((MetroJbLinea)arg0.getAdapter().getItem(arg2));
				for(MetroJbLinea lineaOrigen:((MetroGlobalValues)getApplicationContext()).getMetroJbRed().getListaLinea())
				{
					if(lineaOrigen.equals(valueOrigen))
					{
						lineaOrigenSel		= lineaOrigen;
						
						listaEstacionOrigen	= ((MetroGlobalValues)getApplicationContext()).getMetroUtil().getRutaLinea(((MetroGlobalValues)getApplicationContext()).getMetroJbRed(),lineaOrigen);
						MetroEstacionSpinnerAdapter adapterEstacionOrigen		= new MetroEstacionSpinnerAdapter(seleccionaDestino,R.layout.metrospinneritemsimple,R.id.textSimpleItem,listaEstacionOrigen );
						adapterEstacionOrigen.setDropDownViewResource(R.layout.metrospinneritemsimple);
						seleccionaDestino.spinnerOrigenEstacion.setAdapter(adapterEstacionOrigen);
						break;
					}
				}				
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
		});
		spinnerOrigenEstacion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
		 {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) 
			{
				MetroJbEstacion estacionOrigenStr				= (MetroJbEstacion)arg0.getAdapter().getItem(arg2);
				for(int i=0;i<((MetroGlobalValues)getApplicationContext()).getMetroJbRed().getMapaEstacion().size();i++)
				{
					MetroJbEstacion estacionMapaOrigen	= ((MetroGlobalValues)getApplicationContext()).getMetroJbRed().getMapaEstacion().get(i);
					if(estacionMapaOrigen.equals(estacionOrigenStr))
					{
						seleccionaDestino.estacionOrigen=i;
						break;
					}
				}
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			
			}
			 
		 });
		spinnerDestinoEstacion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) 
			{
				MetroJbEstacion estacionDestinoStr				= (MetroJbEstacion)arg0.getAdapter().getItem(arg2);
				for(int i=0;i<((MetroGlobalValues)getApplicationContext()).getMetroJbRed().getMapaEstacion().size();i++)
				{
					MetroJbEstacion estacionMapaDestino	= ((MetroGlobalValues)getApplicationContext()).getMetroJbRed().getMapaEstacion().get(i);
					if(estacionMapaDestino.equals(estacionDestinoStr))
					{
						seleccionaDestino.estacionDestino = i;					
						break;
					}
				}
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
			 
		 });
		 btnOk.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) 
			{
				
				getResult(0);
			}
		});
		 btnOkRuta.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getResult(1);
			}
		});
    }
    @Override
    public void onDestroy() {
    	if(MetroConstant.ADMOB_MODE)
      	  adView.destroy();
      super.onDestroy();
    }
    
    private void getResult(int tipo)
    {
    	MetroCalculaRuta 	dijkstra;
		MetroJbGrafo 		grafo;
		Intent 				intent			= new Intent();
		this.reloadMap						= false;
		if(!(estacionDestino==estacionOrigen))
		{
			if(lineaOrigenSel.equals(lineaDestinoSel))
			{
				int origenLista							= 0;
				int destinoLista						= 0;
				List<MetroJbEstacion>lineaTmp			= new ArrayList<MetroJbEstacion>();
				ArrayList<MetroJbEstacion>lineaTmpArray	= new ArrayList<MetroJbEstacion>();
				lineaTmp		= ((MetroGlobalValues)getApplicationContext()).getMetroUtil().getRutaLinea(((MetroGlobalValues)getApplicationContext()).getMetroJbRed(), lineaOrigenSel);
				origenLista		= lineaTmp.lastIndexOf(((MetroGlobalValues)getApplicationContext()).getMetroJbRed().getMapaEstacion().get(estacionOrigen));
				destinoLista	= lineaTmp.lastIndexOf(((MetroGlobalValues)getApplicationContext()).getMetroJbRed().getMapaEstacion().get(estacionDestino));
				if(origenLista>destinoLista)
				{
					lineaTmp 	= lineaTmp.subList(destinoLista,origenLista+1);
					Collections.reverse(lineaTmp);
				}
				else
				{
					lineaTmp	= lineaTmp.subList(origenLista, destinoLista+1);
					
				}
				lineaTmpArray.addAll(lineaTmp);
				intent		= (tipo==1)?new Intent(seleccionaDestino,MetroMapa.class):new Intent(seleccionaDestino,MetroRuta.class);				
				bundle		= new Bundle();
				bundle.putString(MetroConstant.LINEA_PARAM, lineaOrigenSel.getId());
				bundle.putParcelableArrayList(MetroConstant.RUTA_PARAM,lineaTmpArray);					
				intent.putExtras(bundle);
				startActivity(intent);
			}	
			else
			{
				resultado		= new MetroJbResultado();
				grafo 			= new MetroJbGrafo(((MetroGlobalValues)getApplicationContext()).getMetroJbRed().getMapaEstacion(),((MetroGlobalValues)getApplicationContext()).getMetroJbRed().getSegmentos());
				dijkstra 		= new MetroCalculaRuta();
				resultado		= dijkstra.getRuta(grafo,((MetroGlobalValues)getApplicationContext()).getMetroJbRed().getMapaEstacion().get(estacionOrigen),((MetroGlobalValues)getApplicationContext()).getMetroJbRed().getMapaEstacion().get(estacionDestino));
				if(resultado.getRuta()!=null)
				{	
					intent		= (tipo==1)?new Intent(seleccionaDestino,MetroMapa.class):new Intent(seleccionaDestino,MetroRuta.class);				
					bundle		= new Bundle();
					bundle.putParcelableArrayList(MetroConstant.RUTA_PARAM, (ArrayList<MetroJbEstacion>)resultado.getRuta());
					bundle.putParcelableArrayList(MetroConstant.SEGMENTO_PARAM, (ArrayList<MetroJbSegmento>)resultado.getListaSegmento());
					intent.putExtras(bundle);
					startActivity(intent);
				}
			}
		}
		else
		{
			Toast toast= Toast.makeText(seleccionaDestino,R.string.mismoDestino,Toast.LENGTH_SHORT);
			toast.show();
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
    	 savedInstanceState.putInt("spinnerDestino", this.spinnerDestino.getSelectedItemPosition());
    	 savedInstanceState.putInt("spinnerOrigen", this.spinnerOrigen.getSelectedItemPosition());
    	 savedInstanceState.putInt("spinnerDestinoEstacion", this.spinnerDestinoEstacion.getSelectedItemPosition());
    	 savedInstanceState.putInt("spinnerOrigenEstacion", this.spinnerOrigenEstacion.getSelectedItemPosition());
    	 

     }
      
      
     @Override
     protected void onRestoreInstanceState(Bundle savedInstanceState)
     {
    	this.reloadMap = savedInstanceState.getBoolean("reloadMap");
	   	 if(this.spinnerDestino.getAdapter().getItem(savedInstanceState.getInt("spinnerDestino")) !=null)
	 	 {
	   	 	this.spinnerDestino.setSelection(savedInstanceState.getInt("spinnerDestino"));
	   	 	this.spinnerDestino.setSelected(true);
	     }
	   	 if(this.spinnerOrigen.getAdapter().getItem(savedInstanceState.getInt("spinnerOrigen")) !=null)
	 	 {
	   	 	this.spinnerOrigen.setSelection(savedInstanceState.getInt("spinnerOrigen"));
	   	 	this.spinnerOrigen.setSelected(true);
	     }
/*	   	 if(this.spinnerDestinoEstacion.getAdapter().getItem(savedInstanceState.getInt("spinnerDestinoEstacion")) !=null)
	 	 {
	   	 	this.spinnerDestinoEstacion.setSelection(savedInstanceState.getInt("spinnerDestinoEstacion"));
	   	 	this.spinnerDestinoEstacion.setSelected(true);
	     }
	   	 if(this.spinnerOrigenEstacion.getAdapter().getItem(savedInstanceState.getInt("spinnerDestinoValue")) !=null)
	 	 {
	   	 	this.spinnerOrigenEstacion.setSelection(savedInstanceState.getInt("spinnerOrigenEstacion"));
	   	 	this.spinnerOrigenEstacion.setSelected(true);
	     }
	     */
   	 
     }
    @Override
  	protected void onResume() {
    	super.onResume();
    	if(this.reloadMap)
    	{
    		int spinnerDestinoValue=this.spinnerDestino.getSelectedItemPosition();
    		int spinnerOrigenValue=this.spinnerOrigen.getSelectedItemPosition();
  	  		metroCustomSpinnerAdapter.clear();
	        spinnerDestino.clearDisappearingChildren();
	        spinnerOrigen.clearDisappearingChildren();
	        ((MetroGlobalValues)getApplicationContext()).getMetroJbRed().reload();
	  		    
	        metroCustomSpinnerAdapter	=	new  MetroLineaSpinnerAdapter(this, R.layout.metrospinneritemcomplex,R.id.textComplexItem,((MetroGlobalValues)getApplicationContext()).getMetroJbRed().getListaLinea());
	        metroCustomSpinnerAdapter.setDropDownViewResource(R.layout.metrospinneritemcomplex);
	        spinnerDestino.setAdapter(metroCustomSpinnerAdapter);
		    spinnerOrigen.setAdapter(metroCustomSpinnerAdapter); 
		    this.reloadMap	= false;
		    if(this.spinnerDestino.getAdapter().getCount()>spinnerDestinoValue)
		 	 {
		   	 	this.spinnerDestino.setSelection(spinnerDestinoValue);
		   	 	this.spinnerDestino.setSelected(true);
		     }
		   	 if(this.spinnerOrigen.getAdapter().getCount()>spinnerOrigenValue)
		 	 {
		   	 	this.spinnerOrigen.setSelection(spinnerOrigenValue);
		   	 	this.spinnerOrigen.setSelected(true);
		     }


    	}
  	}
	
}