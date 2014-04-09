package mx.com.underrito.metro.free.util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import android.util.Log;
import mx.com.underrito.metro.free.constant.MetroConstant;
import mx.com.underrito.metro.free.model.MetroJbEstacion;
import mx.com.underrito.metro.free.model.MetroJbGrafo;
import mx.com.underrito.metro.free.model.MetroJbResultado;
import mx.com.underrito.metro.free.model.MetroJbSegmento;
public class MetroCalculaRuta 
{
	private ArrayList<MetroJbSegmento> 				segmentos;
	private Set<MetroJbEstacion> 					estacionesSinAsignar;
	private Set<MetroJbEstacion> 					estacionesExistentes;
	private Map<MetroJbEstacion, MetroJbEstacion> 	predecesores;
	private Map<MetroJbEstacion, Double> 			distancia;
	private ArrayList<MetroJbSegmento>				listaSegmento;
	//private String 									lineaOrigen;			
	public MetroCalculaRuta() 
	{
		this.segmentos				= new ArrayList<MetroJbSegmento>();		
		this.listaSegmento			= new ArrayList<MetroJbSegmento>();
		this.estacionesExistentes 	= new HashSet<MetroJbEstacion>();
		this.estacionesSinAsignar 	= new HashSet<MetroJbEstacion>();
		this.distancia 				= new HashMap<MetroJbEstacion, Double>();
		this.predecesores 			= new HashMap<MetroJbEstacion, MetroJbEstacion>();
		//this.lineaOrigen			= "";
	}
	public void execute(MetroJbGrafo graph,MetroJbEstacion origen) 
	{
		int contador=0;
		String lineaOrigen			= "";
		MetroJbEstacion				estacionAnterior= new MetroJbEstacion();
		this.segmentos 				= graph.getSegmentos();
		this.distancia.put(origen, 0d);				//nodo origen distancia en cero
		this.estacionesSinAsignar.add(origen);		//se agrega estacion origen
		while (estacionesSinAsignar.size() > 0) 
		{
			MetroJbEstacion estacion = getMinimo(estacionesSinAsignar);
			
			if(contador==0)
			{
				lineaOrigen="";
				estacionAnterior=origen;
				contador++;
			}
			else
			{
				
				try
				{
					lineaOrigen=this.getSegmento(estacionAnterior, estacion).getLinea();
				}
				catch(Exception e)
				{
					lineaOrigen="";
				}
				estacionAnterior=estacion;
			}
			//if(lineaOrigen.equals("") && !origen.getNombre().equals(estacion.getNombre()))				
				
			this.estacionesExistentes.add(estacion);
			this.estacionesSinAsignar.remove(estacion);
			this.encuentaDistanciaMinima(estacion,lineaOrigen);
		}
	}

	private void encuentaDistanciaMinima(MetroJbEstacion estacion,String lineaOrigen) 
	{
		int adicionalTrasborde=0;
		ArrayList<MetroJbEstacion> adjacentNodes = this.getVecinos(estacion);				
		for (MetroJbEstacion objetivo : adjacentNodes) 
		{
			//Log.i("ROD","DistanciaMasCorta:"+this.getDistanciaMasCorta(objetivo)+" distancia"+this.getDistancia(estacion, objetivo));
			MetroJbSegmento segmentoAdyacente= null;
			segmentoAdyacente	= this.getSegmento(estacion, objetivo);
			if(lineaOrigen.equals("") || lineaOrigen.equals(segmentoAdyacente.getLinea()) )
			{
				adicionalTrasborde=0;
				//Log.i("ROD","CORRESPONDENCIA:"+estacion.getNombre()+" objetivo:"+objetivo.getNombre()+" peso:"+peso);						
			}
			else
			{
				adicionalTrasborde=MetroConstant.PESO_TRANSBORDE;
				//Log.i("ROD","TRANSBORDE LINEA"+segmento.getLinea()+" A LINEA"+segmentoAdyacente.getLinea()+":"+estacion.getNombre()+" objetivo:"+objetivo.getNombre()+" peso:"+peso);
			}
			
			
			if (this.getDistanciaMasCorta(objetivo) > this.getDistanciaMasCorta(estacion) + this.getDistancia(estacion, objetivo,adicionalTrasborde)) 
			{
				this.distancia.put(objetivo, this.getDistanciaMasCorta(estacion)+ this.getDistancia(estacion, objetivo,adicionalTrasborde));				
				this.predecesores.put(objetivo, estacion);
				this.estacionesSinAsignar.add(objetivo);
			}
		}
	}

	

	private double getDistancia(MetroJbEstacion estacion, MetroJbEstacion objetivo,int pesoAdicional) 
	{
		for (MetroJbSegmento segmento : segmentos)
		{
			if (segmento.getOrigen().equals(estacion) && segmento.getDestino().equals(objetivo)) 
			{
				if(pesoAdicional!=0)
					Log.i("ROD", "TRASBORDE:"+estacion.getNombre()+" destino:" +objetivo.getNombre());
				return segmento.getPeso()+pesoAdicional;
			}
		}
		throw new RuntimeException("Error al obtener distancia");
	}
	
	
	/*private double getDistancia(MetroJbEstacion estacion, MetroJbEstacion objetivo) 
	{
		double peso=0d;
		for (MetroJbSegmento segmento : this.segmentos)
		{
			if (segmento.getOrigen().equals(estacion) && segmento.getDestino().equals(objetivo))
			{
				ArrayList<MetroJbEstacion> adjacentNodes = this.getVecinos(estacion);
				for(MetroJbEstacion nodoAdyacente:adjacentNodes)
				{
					MetroJbSegmento segmentoAdyacente= null;
					segmentoAdyacente	= this.getSegmento(estacion, nodoAdyacente);
					if(segmento.getLinea().equals(segmentoAdyacente.getLinea()))
					{
						peso=segmento.getPeso();
						//Log.i("ROD","CORRESPONDENCIA:"+estacion.getNombre()+" objetivo:"+objetivo.getNombre()+" peso:"+peso);						
					}
					else
					{
						peso=(segmento.getPeso()+MetroConstant.PESO_TRANSBORDE);
						//Log.i("ROD","TRANSBORDE LINEA"+segmento.getLinea()+" A LINEA"+segmentoAdyacente.getLinea()+":"+estacion.getNombre()+" objetivo:"+objetivo.getNombre()+" peso:"+peso);
					}
					
				}
				if(adjacentNodes.size()>2)
				{
					
					
					if(adjacentNodes.size()>2)
					{
						Log.i("ROD","CORRESPONDENCIA:"+estacion.getNombre()+" objetivo:"+objetivo.getNombre()+" peso:"+segmento.getPeso()+" pesoT:"+MetroConstant.PESO_TRANSBORDE);
						return segmento.getPeso()+MetroConstant.PESO_TRANSBORDE;
					}
					else
						return segmento.getPeso();
				}
			}
			return peso;
		}
		throw new RuntimeException("Error al obtener distancia");
	}*/

	public MetroJbSegmento getSegmento(MetroJbEstacion estacion, MetroJbEstacion objetivo) 
	{
		for (MetroJbSegmento segmento : this.segmentos)
		{
			if (segmento.getOrigen().equals(estacion) && segmento.getDestino().equals(objetivo)) 
				return segmento;
		}
		throw new RuntimeException("Error al obtener segmento");
	}

	private ArrayList<MetroJbEstacion> getVecinos(MetroJbEstacion estacion) 
	{
		ArrayList<MetroJbEstacion> vecinos = new ArrayList<MetroJbEstacion>();
		for (MetroJbSegmento segmento : this.segmentos) 
		{
			if (segmento.getOrigen().equals(estacion) && !existeEstacion(segmento.getDestino())) 
				vecinos.add(segmento.getDestino());
		}
		return vecinos;
	}

	private MetroJbEstacion getMinimo(Set<MetroJbEstacion> estaciones) 
	{
		MetroJbEstacion minimo = null;
		for (MetroJbEstacion estacion : estaciones) 
		{
			if (minimo == null) 
				minimo = estacion;
			else 
			{
				if (getDistanciaMasCorta(estacion) < getDistanciaMasCorta(minimo)) 
					minimo = estacion;
			}			
		}
		return minimo;
	}

	private boolean existeEstacion(MetroJbEstacion estacion) 
	{
		return this.estacionesExistentes.contains(estacion);
	}

	private double getDistanciaMasCorta(MetroJbEstacion destino) 
	{
		Double d = distancia.get(destino);
		if (d == null) 
			return Integer.MAX_VALUE;
		else 
			return d;
	
	}

	public MetroJbResultado getRuta(MetroJbGrafo grafo,
			MetroJbEstacion estacionOrigen,
			MetroJbEstacion estacionDestino) 
	{
		ArrayList<MetroJbEstacion> 		ruta 		= new ArrayList<MetroJbEstacion>();
		MetroJbEstacion 				origen		= new MetroJbEstacion();
		MetroJbEstacion 				destino		= new MetroJbEstacion();
		MetroJbResultado				resultado	= new MetroJbResultado();
		
		this.execute(grafo,estacionOrigen);
		if (this.predecesores.get(estacionDestino) == null)
			return null;
		ruta.add(estacionDestino);
		while (predecesores.get(estacionDestino) != null) 
		{
			origen				= estacionDestino;
			estacionDestino 	= this.predecesores.get(estacionDestino);
			destino				= estacionDestino;
			if(!(origen==null || destino==null))
				this.listaSegmento.add(getSegmento(origen,  destino));
			ruta.add(estacionDestino);
		}
		Collections.reverse(ruta);
		Collections.reverse(this.listaSegmento);
		this.marcaTransbordeRuta(ruta);
		resultado.setRuta(ruta);
		resultado.setListaSegmento(this.listaSegmento);
		
		return resultado;
	}
	public void marcaTransbordeRuta(ArrayList<MetroJbEstacion> listaEstacion)	
	{
		ArrayList<MetroJbEstacion> listaEstacionResultado	= new ArrayList<MetroJbEstacion>();
		int position=0;
		int listaEstacionSize=0;
		MetroJbEstacion	origen		= new MetroJbEstacion();
		MetroJbEstacion	destino		= new MetroJbEstacion();
		MetroJbSegmento segmento;
		String correspondencia		= "";
		String correspondenciaAnt	= "";
		listaEstacionSize	= listaEstacion.size();
		for(MetroJbEstacion estacion:listaEstacion)
		{
			if((position+1)<listaEstacionSize)
			{
				origen			= listaEstacion.get(position);
				destino			= listaEstacion.get(position+1);
				segmento		= this.getSegmento(origen, destino);
				correspondencia	= segmento.getLinea();
				if(position		== 0)
					correspondenciaAnt	= correspondencia;
				if(!(correspondenciaAnt.contentEquals(correspondencia)))
				{
					estacion.setTransborde(1);
				}
				else
				{
					estacion.setTransborde(0);
				}
				
			}
			correspondenciaAnt=correspondencia;
			listaEstacionResultado.add(estacion);
			position++;
			
		}
		listaEstacion=listaEstacionResultado;
	}
}