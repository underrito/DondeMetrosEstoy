package mx.com.underrito.metro.free.activity;
import java.util.ArrayList;
import com.google.ads.AdView;
import mx.com.underrito.metro.free.R;
import mx.com.underrito.metro.free.adapter.MetroBuscaRutaAdapter;
import mx.com.underrito.metro.free.constant.MetroConstant;
import mx.com.underrito.metro.free.globalvalues.MetroGlobalValues;
import mx.com.underrito.metro.free.model.MetroJbEstacion;
import mx.com.underrito.metro.free.model.MetroJbGrafo;
import mx.com.underrito.metro.free.model.MetroJbResultado;
import mx.com.underrito.metro.free.model.MetroJbSegmento;
import mx.com.underrito.metro.free.util.MetroCalculaRuta;
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
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Toast;

public class MetroBuscaNombre extends Activity
{

	private 	ImageView				btnOkBuscarDetalle;
	private 	ImageView				btnOkBuscarMapa;
	private 	AutoCompleteTextView	txtOrigen;
	private 	AutoCompleteTextView	txtDestino;
	private 	MetroJbEstacion			estacionOrigen;
	private 	MetroJbEstacion			estacionDestino;
	private 	MetroBuscaNombre		metroBuscaEstacion;
	private 	Bundle					bundle;
	private		MetroJbResultado		resultado; 
	private 	boolean					reloadMap	= false;
	AdView								adView;
	Typeface							typeFace;
	@Override
	public void onCreate(Bundle savedBundle)
	{
		super.onCreate(savedBundle);
		metroBuscaEstacion			= this;
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        typeFace					= ((MetroGlobalValues)getApplicationContext()).getMetroUtil().getTypeFace();
		this.setContentView(R.layout.metrobuscanombre);		
		if(MetroConstant.ADMOB_MODE)
		       adView	= ((MetroGlobalValues)getApplicationContext()).getMetroUtilAdMob().displayAd(this, R.id.llBannerAD);
		this.estacionOrigen			= new MetroJbEstacion();
		this.estacionDestino		= new MetroJbEstacion();
		this.btnOkBuscarMapa		= (ImageView)findViewById(R.id.btnOkBuscarMapa);
		this.btnOkBuscarDetalle		= (ImageView)findViewById(R.id.btnOkBuscarDetalle);
		this.txtOrigen				= (AutoCompleteTextView)findViewById(R.id.txtOrigen);
		this.txtDestino				= (AutoCompleteTextView)findViewById(R.id.txtDestino);
		txtOrigen.setTypeface(typeFace);
		txtDestino.setTypeface(typeFace);
		MetroBuscaRutaAdapter metroBuscaRutaAdapter	= new MetroBuscaRutaAdapter(this, R.layout.metrospinneritemsimple,R.id.textSimpleItem,((MetroGlobalValues)this.getApplicationContext()).getMetroJbRed().getListaEstacionList());
		this.txtOrigen.setAdapter(metroBuscaRutaAdapter);
		this.txtDestino.setAdapter(metroBuscaRutaAdapter);
		this.txtOrigen.setOnItemClickListener(new AdapterView.OnItemClickListener() 
		{
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3)
			{
				estacionOrigen	=(MetroJbEstacion)arg0.getAdapter().getItem(arg2);
			}
		});
		this.txtDestino.setOnItemClickListener(new AdapterView.OnItemClickListener() 
		{
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3)
			{
				estacionDestino	=(MetroJbEstacion)arg0.getAdapter().getItem(arg2);
			}
		});
		this.btnOkBuscarDetalle.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				getResult(0);
			}
		});
		this.btnOkBuscarMapa.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				getResult(1);
			}
		});
	}
	
	private void getResult(int tipo)
	{
		MetroCalculaRuta 	dijkstra;
		MetroJbGrafo 		grafo;
		Intent 				intent			= new Intent();
		this.reloadMap		= false;
		if(estacionDestino.getNombre().contentEquals(""))
			estacionDestino = new MetroJbEstacion();
			
		if(estacionOrigen.getNombre().contentEquals(""))
			estacionOrigen = new MetroJbEstacion();
			
		if( estacionOrigen.getNombre().contentEquals("") && 
			estacionDestino.getNombre().contentEquals(""))
		{ 
			Toast toast= Toast.makeText(metroBuscaEstacion,R.string.rutavacia,Toast.LENGTH_SHORT);
			toast.show();
		}
		else if( !estacionOrigen.getNombre().contentEquals("") && 
				  estacionDestino.getNombre().contentEquals(""))
		{
			resultado		= new MetroJbResultado();				
			resultado.setEstacionTraza(estacionOrigen);
			intent		= (tipo==1)?new Intent(metroBuscaEstacion,MetroMapa.class):new Intent(metroBuscaEstacion,MetroRuta.class);				
			bundle		= new Bundle();
			bundle.putParcelable(MetroConstant.ESTACION_TRAZA, (MetroJbEstacion)resultado.getEstacionTraza());
			intent.putExtras(bundle);
			startActivity(intent);				
		}
		else if(!(estacionDestino==estacionOrigen))
		{
			resultado		= new MetroJbResultado();
			grafo 			= new MetroJbGrafo(((MetroGlobalValues)getApplicationContext()).getMetroJbRed().getMapaEstacion(),((MetroGlobalValues)getApplicationContext()).getMetroJbRed().getSegmentos());
			dijkstra 		= new MetroCalculaRuta();
			resultado		= dijkstra.getRuta(grafo,((MetroGlobalValues)getApplicationContext()).getMetroJbRed().getMapaEstacion().get(estacionOrigen.getId()),((MetroGlobalValues)getApplicationContext()).getMetroJbRed().getMapaEstacion().get(estacionDestino.getId()));
			if(resultado.getRuta()!=null)
			{	
				intent		= (tipo==1)?new Intent(metroBuscaEstacion,MetroMapa.class):new Intent(metroBuscaEstacion,MetroRuta.class);				
				bundle		= new Bundle();
				bundle.putParcelableArrayList(MetroConstant.RUTA_PARAM, (ArrayList<MetroJbEstacion>)resultado.getRuta());
				bundle.putParcelableArrayList(MetroConstant.SEGMENTO_PARAM, (ArrayList<MetroJbSegmento>)resultado.getListaSegmento());
				intent.putExtras(bundle);
				startActivity(intent);
			}				
		}
		else
		{
			Toast toast= Toast.makeText(metroBuscaEstacion,R.string.mismoDestino,Toast.LENGTH_SHORT);
			toast.show();
		}
	}
	@Override
	protected void onSaveInstanceState(Bundle savedInstanceState)
	{
		savedInstanceState.putParcelable("estacionOrigen", estacionOrigen);
		savedInstanceState.putParcelable("estacionDestino", estacionDestino);
	}
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState)
	{
		this.estacionOrigen		= savedInstanceState.getParcelable("estacionOrigen");
		this.estacionDestino	= savedInstanceState.getParcelable("estacionDestino");
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
			intent					= new  Intent(this,MetroPreferencia.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	      
	@Override
	protected void onResume() 
	{
		super.onResume();
		if(this.reloadMap)
		{
			((MetroGlobalValues)getApplicationContext()).getMetroJbRed().reload();
			this.reloadMap	= false;
		}
	}
}
