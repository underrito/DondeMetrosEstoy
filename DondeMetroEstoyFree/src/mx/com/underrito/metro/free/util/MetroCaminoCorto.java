package mx.com.underrito.metro.free.util;
import java.util.ArrayList;

import mx.com.underrito.metro.free.model.*;

public class MetroCaminoCorto 
{
	private MetroJbGrafo grafo;
	//private MetroJbEstacion origen;
	double distancia=0d;
	public MetroCaminoCorto()
	{
		this.grafo=null;
		//this.origen=null;
		this.distancia=0;
	}
	public void calculaCaminoCorto(MetroJbGrafo grafo,MetroJbEstacion origen)
	{
		this.grafo=grafo;
		//this.origen=origen;
		
		//buscando estacion origen
		this.distancia=0;
		MetroJbSegmento segmentoInicial= this.getSegmentoCorto(origen);
		this.distancia=distancia+segmentoInicial.getPeso();
		
		
	}
	private ArrayList<MetroJbSegmento> getSegmentosVecinos(MetroJbEstacion estacion)
	{
		ArrayList<MetroJbSegmento> listaSegmentosVecinos	 = new ArrayList<MetroJbSegmento>();
		try
		{			
			for(MetroJbSegmento segmento:this.grafo.getSegmentos())
			{
				if(segmento.getOrigen().equals(estacion))
					listaSegmentosVecinos.add(segmento);
			}
		}
		catch(Exception e)
		{
			listaSegmentosVecinos	 = new ArrayList<MetroJbSegmento>();
		}
		return listaSegmentosVecinos;
	}
	
	private MetroJbSegmento getSegmentoCorto(MetroJbEstacion estacion)
	{
		double pesoMasCorto=0;
		double pesoSegmento=0;
		ArrayList<MetroJbSegmento> listaSegmentosVacios;
		MetroJbSegmento segmentoResultado= null;
		
		listaSegmentosVacios	= this.getSegmentosVecinos(estacion);
		for(MetroJbSegmento segmento:listaSegmentosVacios)
		{
			pesoSegmento=0;			
			pesoSegmento=segmento.getPeso();
			if(pesoMasCorto==0||(pesoMasCorto!=0 && pesoMasCorto>pesoSegmento))
			{
				pesoMasCorto=pesoSegmento;
				segmentoResultado=segmento;
			}			
		}
		return segmentoResultado;
	}
	
}
