package mx.com.underrito.metro.free.activity;
import java.util.ArrayList;
import java.util.List;
import mx.com.underrito.metro.free.adapter.MetroRutaAdapter;
import mx.com.underrito.metro.free.constant.MetroConstant;
import mx.com.underrito.metro.free.model.MetroJbEstacion;
import mx.com.underrito.metro.free.model.MetroJbSegmento;
import mx.com.underrito.metro.free.util.MetroComplementoRuta;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MetroRuta extends ListActivity
{
    Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{ 
		super.onCreate(savedInstanceState);
        context = this;
		MetroComplementoRuta 			complementoRuta;
	    ArrayAdapter<MetroJbEstacion> 	adapter;
		Bundle 							bundle			= this.getIntent().getExtras();
		List<MetroJbEstacion> 			listaEstacion	= new ArrayList<MetroJbEstacion>();
		ArrayList<MetroJbSegmento>		listaSegmento	= new ArrayList<MetroJbSegmento>();		
		String 							linea			= "";
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		if(bundle!=null)
		{
			linea			= bundle.getString(MetroConstant.LINEA_PARAM);
			listaEstacion	= bundle.getParcelableArrayList(MetroConstant.RUTA_PARAM);
			listaSegmento	= bundle.getParcelableArrayList(MetroConstant.SEGMENTO_PARAM);
		}
		complementoRuta		= new MetroComplementoRuta(linea,(ArrayList<MetroJbEstacion>)listaEstacion, listaSegmento);
		listaEstacion		= complementoRuta.completaRuta();
		adapter				= new MetroRutaAdapter(this,listaEstacion);
		setListAdapter(adapter);
	}

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id)
    {
        super.onListItemClick(l, v, position, id);
        Toast.makeText(context,"elemento:"+l.getAdapter().getItem(position).toString(),Toast.LENGTH_LONG).show();

    }
}
