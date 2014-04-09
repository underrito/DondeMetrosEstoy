package mx.com.underrito.metro.free.util;
import java.util.ArrayList;
import android.view.View;
import mx.com.underrito.metro.free.R;
import mx.com.underrito.metro.free.model.MetroJbEstacion;
import mx.com.underrito.metro.free.model.MetroJbSegmento;
public class MetroComplementoRuta 
{
	private String 						linea;
	private ArrayList<MetroJbEstacion> 	listaEstacion;
	private ArrayList<MetroJbSegmento> 	listaSegmentos;
	
	public MetroComplementoRuta(String linea, ArrayList<MetroJbEstacion> listaEstacion,ArrayList<MetroJbSegmento> listaSegmentos)
	{
		this.linea				= "";
		this.listaEstacion		= new ArrayList<MetroJbEstacion>();
		this.listaSegmentos		= new ArrayList<MetroJbSegmento>();
		this.linea				= linea;
		this.listaEstacion		= listaEstacion;
		this.listaSegmentos		= listaSegmentos;
	}
	public ArrayList<MetroJbEstacion> completaRuta()
	{
		int 	image1				= 0;
		int		image2				= 0;
		int		image1Visibility	= 0;
		int		image2Visibility	= 0;
		int		position			= 0;
		String 	correspondencia		= "";
		String  correspondenciaAnt	= "";
		String  direccion			= "";
		MetroJbSegmento segmentoDireccion;
		ArrayList<MetroJbEstacion>	listaEstacionFinal	= new ArrayList<MetroJbEstacion>();
		for(MetroJbEstacion estacion:this.listaEstacion)
		{
			if(this.linea==null ||this.linea.contentEquals(""))
			{	
				if((position+1)<this.listaEstacion.size())
				{
					MetroJbEstacion origen	= this.listaEstacion.get(position+1);
					MetroJbEstacion destino	= this.listaEstacion.get(position);
					segmentoDireccion		= this.getSegmento(origen, destino);
					correspondencia			= segmentoDireccion.getLinea();
					if(position==0)
						correspondenciaAnt	= correspondencia;
					if(!(correspondenciaAnt.contentEquals(correspondencia)))
					{
						image2 				= this.getResource(correspondencia);
						image2Visibility 	= View.VISIBLE;
						image1 				= this.getResource(correspondenciaAnt);
						image1Visibility 	= View.VISIBLE;
						correspondenciaAnt	= correspondencia;
						direccion			= segmentoDireccion.getDireccion().getNombre();

					}
					else					
					{
						image1 				= this.getResource(correspondencia);
						image1Visibility 	= View.VISIBLE;
						image2 				= this.getResource(correspondencia);
						image2Visibility 	= View.INVISIBLE;
						direccion			= "";
					}
				
				}
				else
				{
					image1					= this.getResource(correspondencia);
					image1Visibility 		= View.VISIBLE;
					image2 					= this.getResource(correspondencia);
					image2Visibility 		= View.INVISIBLE;
					direccion				= "";	 
					
				}
			}
			else
			{
				correspondencia				= this.linea;
				image1						= getResource(correspondencia);
				image1Visibility 			= View.VISIBLE;
				image2 						= getResource(correspondencia);
				image2Visibility 			= View.INVISIBLE;
				direccion					= ""; 
			}
			estacion.setImage1(image1);
			estacion.setImage1Visible(image1Visibility);
			estacion.setImage2(image2);
			estacion.setImage2Visible(image2Visibility);
			estacion.setDireccion(direccion);
			listaEstacionFinal.add(estacion);
			position++;
		}
		return listaEstacionFinal;
	}
	private int getResource(String correspondencia)
	{  
		int resource=R.drawable.lineaa;
		if(correspondencia.contentEquals("1") )
			resource= R.drawable.linea1;
		else if(correspondencia.contentEquals("2"))
			resource= R.drawable.linea2;
		else if(correspondencia.contentEquals("3"))
			resource= R.drawable.linea3;
		else if(correspondencia.contentEquals("4"))
			resource= R.drawable.linea4;
		else if(correspondencia.contentEquals("5"))
			resource= R.drawable.linea5;
		else if(correspondencia.contentEquals("6"))
			resource= R.drawable.linea6;
		else if(correspondencia.contentEquals("7"))
			resource= R.drawable.linea7;
		else if(correspondencia.contentEquals("8"))
			resource= R.drawable.linea8;
		else if(correspondencia.contentEquals("9"))
			resource= R.drawable.linea9;
		else if(correspondencia.contentEquals("a"))
			resource= R.drawable.lineaa;
		else if(correspondencia.contentEquals("b"))
			resource= R.drawable.lineab;
		else if(correspondencia.contentEquals("12"))
			resource= R.drawable.linea12;
		else 
			resource= R.drawable.linea1;
		return resource;
	}
	private MetroJbSegmento getSegmento(MetroJbEstacion origen, MetroJbEstacion destino) 
	{
		for (MetroJbSegmento segmento : this.listaSegmentos)
		{
			if (segmento.getOrigen().getNombre().contentEquals(origen.getNombre()) && segmento.getDestino().getNombre().contentEquals(destino.getNombre())) 
				return segmento;			
		}
		throw new RuntimeException("Error al obtener segmento");
	}
}
