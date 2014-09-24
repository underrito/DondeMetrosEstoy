package mx.com.underrito.metro.free.globalvalues;
import java.util.ArrayList;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import mx.com.underrito.metro.free.model.MetroJbEstacion;
import mx.com.underrito.metro.free.model.MetroJbLinea;
import mx.com.underrito.metro.free.model.MetroJbSegmento;
public class MetroUtil 
{
	private Context context;
	public MetroUtil(Context context)
	{
		this.context	= context;
	}
	public ArrayList<MetroJbEstacion> getRutaLinea(MetroRed red,MetroJbLinea linea)
	{
		ArrayList<MetroJbEstacion> listaLinea	= new ArrayList<MetroJbEstacion>();
		for(int estacion:linea.getListaEstacionNumero())
		{
			listaLinea.add(red.getMapaEstacion().get(estacion));
		}
		return listaLinea;
	}
	public boolean isMultitouchDevice(PackageManager packageManager)
	{
		return packageManager.hasSystemFeature(PackageManager.FEATURE_TOUCHSCREEN_MULTITOUCH);
	}
	public SharedPreferences getSharedPreferences(Context context)
	{
		return PreferenceManager.getDefaultSharedPreferences(context);
	}
	public Typeface getTypeFace()
	{
		return Typeface.createFromAsset(context.getAssets(), "fonts/metrostc.otf");
	}
	public MetroJbSegmento getSegmento(MetroJbEstacion estacion, MetroJbEstacion objetivo,MetroRed red) 
	{	
		for (MetroJbSegmento segmento : red.getSegmentos())
		{
			if (segmento.getOrigen().equals(estacion) && segmento.getDestino().equals(objetivo)) 
				return segmento;
		}
		throw new RuntimeException("Error al obtener segmento");
	}
}
