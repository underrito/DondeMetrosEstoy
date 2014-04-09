package mx.com.underrito.metro.free.model;
import java.util.ArrayList;
import android.util.SparseArray;
public class MetroJbGrafo 
{
	private final SparseArray<MetroJbEstacion> 		estaciones;
	private final ArrayList<MetroJbSegmento> 		segmentos;
	public MetroJbGrafo(SparseArray<MetroJbEstacion> estaciones, ArrayList<MetroJbSegmento> segmentos) 
	{
		this.estaciones 	= estaciones;
		this.segmentos 		= segmentos;
	}
	public SparseArray<MetroJbEstacion> getEstaciones() 
	{
		return estaciones;
	}
	public ArrayList<MetroJbSegmento> getSegmentos() 
	{
		return segmentos;
	}
}