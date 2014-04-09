package mx.com.underrito.metro.free.util;
import java.util.Locale;
import mx.com.underrito.metro.free.constant.MetroConstant;
import mx.com.underrito.metro.free.model.MetroJbDibujaRuta;
import mx.com.underrito.metro.free.model.MetroJbEstacion;
import android.graphics.Color;
import android.graphics.Paint;
public class MetroDibujaRedUtil 
{
	
	public static void dibujaRuta(MetroJbDibujaRuta dibujaRuta)
	{
		double  			wDrawUnit	= 800;
		double  			hDrawUnit	= 1000;
		double				wUnit		= (dibujaRuta.getW()/100);
		double				hUnit		= (dibujaRuta.getH()/100);
		Paint				paint;
		MetroJbEstacion 	estacion;
		MetroJbEstacion 	estacionNext;
		paint				= new Paint(Paint.ANTI_ALIAS_FLAG);
		
		wDrawUnit			= wDrawUnit*dibujaRuta.getNivelZom();
		hDrawUnit			= hDrawUnit*dibujaRuta.getNivelZom();
		wUnit				= wUnit*dibujaRuta.getNivelZom();
		hUnit				= hUnit*dibujaRuta.getNivelZom();
		
		paint.setAlpha(dibujaRuta.getLineaAlpha());

		if(dibujaRuta.isLineaCompleta())
		{
			MetroJbEstacion		estacionInicio	= dibujaRuta.getRuta().get(0);
			paintSquare(dibujaRuta,estacionInicio,paint,wDrawUnit,hDrawUnit,wUnit,hUnit,dibujaRuta.getInicioTag(),dibujaRuta.getTextSize());
		}
		if(dibujaRuta.isLineaCompleta())
		{
			MetroJbEstacion		estacionFinal	= dibujaRuta.getRuta().get( dibujaRuta.getRuta().size()-1);
			paintSquare(dibujaRuta,estacionFinal,paint,wDrawUnit,hDrawUnit,wUnit,hUnit,dibujaRuta.getFinalTag(),dibujaRuta.getTextSize());
		}

		for(int j=0;j<dibujaRuta.getRuta().size();j++)
		{
			
			
			float xEstacion		= 0;
			float yEstacion		= 0;
			float xEstacionNext	= 0;
			float yEstacionNext	= 0;
			estacion			= dibujaRuta.getRuta().get(j);	
			if((j+1)<dibujaRuta.getRuta().size())
			{
				estacionNext	= dibujaRuta.getRuta().get(j+1);
				xEstacion		= (int)(((estacion.getX()*100)/wDrawUnit)* wUnit);
				yEstacion		= (int)(((estacion.getY()*100)/hDrawUnit)* hUnit);
				xEstacionNext	= (int)(((estacionNext.getX()*100)/wDrawUnit)* wUnit);
				yEstacionNext	= (int)(((estacionNext.getY()*100)/hDrawUnit)* hUnit);
				
				
				xEstacion		= xEstacion*dibujaRuta.getNivelZom();
				yEstacion		= yEstacion*dibujaRuta.getNivelZom();
				xEstacionNext	= xEstacionNext*dibujaRuta.getNivelZom();
				yEstacionNext	= yEstacionNext*dibujaRuta.getNivelZom();;
				
				paint.setColor(Color.parseColor(dibujaRuta.getColor()));
				paint.setStrokeWidth(dibujaRuta.getLineaWidth()*dibujaRuta.getNivelZom());
				paint.setAlpha(dibujaRuta.getLineaAlpha());
				dibujaRuta.getCanvas().drawLine(xEstacion, yEstacion, xEstacionNext, yEstacionNext, paint);
			}
		}
		for(int j=0;j<dibujaRuta.getRuta().size();j++)
		{
				float xEstacion				= 0;
				float yEstacion				= 0;
				float labelPosX				= 0;
				float labelPosY				= 0;
				estacion					= dibujaRuta.getRuta().get(j);					
				xEstacion					= (int)(((estacion.getX()*100)/wDrawUnit)* wUnit);
				yEstacion					= (int)(((estacion.getY()*100)/hDrawUnit)* hUnit);				
				xEstacion					= xEstacion*dibujaRuta.getNivelZom();
				yEstacion					= yEstacion*dibujaRuta.getNivelZom();
				
				if(estacion.getTransborde()==1 && dibujaRuta.isRutaCalculada())
				{
					paint.setAlpha(1);				
					paint.setColor(Color.YELLOW);
					dibujaRuta.getCanvas().drawCircle(xEstacion-2,yEstacion-2, dibujaRuta.getRoundLinea()+6*dibujaRuta.getNivelZom(), paint);
					
					paint.setAlpha(1);				
					paint.setColor(Color.BLACK);
					dibujaRuta.getCanvas().drawCircle(xEstacion-2,yEstacion-2, dibujaRuta.getRoundLinea()+6*dibujaRuta.getNivelZom(), paint);	
				}
				else
				{
					paint.setAlpha(dibujaRuta.getEstacionAlpha());				
					paint.setColor(Color.BLACK);
					dibujaRuta.getCanvas().drawCircle(xEstacion-2,yEstacion-2, dibujaRuta.getRoundLinea()+6*dibujaRuta.getNivelZom(), paint);				
					
					paint.setColor(Color.WHITE);				
					paint.setAlpha(dibujaRuta.getEstacionAlpha());
					dibujaRuta.getCanvas().drawCircle(xEstacion-1,yEstacion-1, dibujaRuta.getRoundLinea()+4*dibujaRuta.getNivelZom(), paint);
				
					paint.setColor(Color.parseColor(dibujaRuta.getEstacionColor()));
					paint.setAlpha(dibujaRuta.getEstacionAlpha());
					dibujaRuta.getCanvas().drawCircle(xEstacion,yEstacion, dibujaRuta.getRoundLinea()*dibujaRuta.getNivelZom(), paint);
				}
				
				
				paint.setColor(Color.parseColor(dibujaRuta.getTextColor()));
				paint.setTextSize(dibujaRuta.getTextSize()*dibujaRuta.getNivelZom());
				dibujaRuta.getCanvas().save();
				switch(estacion.getOrientacion())
				{
					case MetroConstant.VERTICAL_DERECHO:
						labelPosX	= xEstacion+(MetroConstant.ESTACION_TEXTO_PADDING*dibujaRuta.getNivelZom());
						labelPosY	= yEstacion;
						break;
					case MetroConstant.VERTICAL_IZQUIERDO:
						labelPosX	= xEstacion-((MetroConstant.ESTACION_TEXTO_PADDING*dibujaRuta.getNivelZom())+((int)paint.measureText(estacion.getNombre())));
						labelPosY	= yEstacion;
						break;
					case MetroConstant.HORIZONTAL_ABAJO:
						labelPosX	= xEstacion;
						labelPosY	= yEstacion+(MetroConstant.ESTACION_TEXTO_PADDING*dibujaRuta.getNivelZom());												
						dibujaRuta.getCanvas().rotate(90,labelPosX,labelPosY);
						break;
					case MetroConstant.HORIZONTAL_ARRIBA:
						labelPosX	= xEstacion;
						labelPosY	= yEstacion-(MetroConstant.ESTACION_TEXTO_PADDING*dibujaRuta.getNivelZom());
						dibujaRuta.getCanvas().rotate(-90,labelPosX,labelPosY);												
						break;
					
					case MetroConstant.INCLINADO_45:
						labelPosX	= xEstacion+MetroConstant.ESTACION_TEXTO_PADDING;
						labelPosY	= yEstacion+MetroConstant.ESTACION_TEXTO_PADDING;
						dibujaRuta.getCanvas().rotate(45,labelPosX,labelPosY);												
						break;
					case MetroConstant.INCLINADO_135:
						labelPosX	= xEstacion+MetroConstant.ESTACION_TEXTO_PADDING;
						labelPosY	= yEstacion+MetroConstant.ESTACION_TEXTO_PADDING;						
						dibujaRuta.getCanvas().rotate(135,labelPosX,labelPosY);
						break;
					case MetroConstant.INCLINADO_225:
						labelPosX	= xEstacion+MetroConstant.ESTACION_TEXTO_PADDING;
						labelPosY	= yEstacion+MetroConstant.ESTACION_TEXTO_PADDING;
						dibujaRuta.getCanvas().rotate(225,labelPosX,labelPosY);												
						break;
					case MetroConstant.INCLINADO_315:
						labelPosX	= xEstacion+MetroConstant.ESTACION_TEXTO_PADDING;
						labelPosY	= yEstacion+MetroConstant.ESTACION_TEXTO_PADDING;
						dibujaRuta.getCanvas().rotate(315,labelPosX,labelPosY);												
						break;
				}		
				paint.setAlpha(dibujaRuta.getEstacionAlpha());
				dibujaRuta.getCanvas().drawText(estacion.getNombre(),labelPosX, labelPosY, paint);
				dibujaRuta.getCanvas().restore();
		}		
	}
	
	public static void paintSquare(MetroJbDibujaRuta dibujaRuta,MetroJbEstacion estacion,Paint paint,double wDrawUnit,double hDrawUnit,double wUnit,double hUnit,int orientation,int textSize)
	{
		float xEstacionInicio		= 0;
		float yEstacionInicio		= 0;
		float xEstacionLabel		= 0;
		float yEstacionLabel		= 0;
		dibujaRuta.getCanvas().save();
		xEstacionInicio		= (int)(((estacion.getX()*100)/wDrawUnit)* wUnit);
		yEstacionInicio		= (int)(((estacion.getY()*100)/hDrawUnit)* hUnit);
		xEstacionInicio		= xEstacionInicio*dibujaRuta.getNivelZom();
		yEstacionInicio		= yEstacionInicio*dibujaRuta.getNivelZom();
		xEstacionLabel		= xEstacionInicio;
		yEstacionLabel		= yEstacionInicio;
		paint.setStrokeWidth(dibujaRuta.getLineaWidth()*dibujaRuta.getNivelZom());
		paint.setColor(Color.parseColor(dibujaRuta.getColor()));
		switch(orientation)
		{
			case MetroConstant.VERTICAL_DERECHO:
				yEstacionInicio	= yEstacionInicio-(10*dibujaRuta.getNivelZom());
				xEstacionLabel	= xEstacionLabel+(6*dibujaRuta.getNivelZom());
				yEstacionLabel	= yEstacionLabel+(6*dibujaRuta.getNivelZom());
				break;
			case MetroConstant.VERTICAL_IZQUIERDO:
				xEstacionInicio	= xEstacionInicio-(20*dibujaRuta.getNivelZom());
				yEstacionInicio	= yEstacionInicio-(10*dibujaRuta.getNivelZom());
				xEstacionLabel	= xEstacionLabel-(16*dibujaRuta.getNivelZom());
				yEstacionLabel	= yEstacionLabel+(6*dibujaRuta.getNivelZom());
				break;
			case MetroConstant.HORIZONTAL_ABAJO:
				xEstacionInicio	= xEstacionInicio-(10*dibujaRuta.getNivelZom());
				xEstacionLabel	= xEstacionLabel-(4*dibujaRuta.getNivelZom());
				yEstacionLabel	= yEstacionLabel+(16*dibujaRuta.getNivelZom());
				break;
			case MetroConstant.HORIZONTAL_ARRIBA:
				xEstacionInicio	= xEstacionInicio-(10*dibujaRuta.getNivelZom());
				yEstacionInicio	= yEstacionInicio-(20*dibujaRuta.getNivelZom());
				xEstacionLabel	= xEstacionLabel-(6*dibujaRuta.getNivelZom());
				yEstacionLabel	= yEstacionLabel-(4*dibujaRuta.getNivelZom());
				break;

		}
		dibujaRuta.getCanvas().drawRect( 
				xEstacionInicio			,
				yEstacionInicio			,
				xEstacionInicio			+(20*dibujaRuta.getNivelZom()),
				yEstacionInicio			+(20*dibujaRuta.getNivelZom()),
				paint);
		paint.setColor(Color.WHITE);
		paint.setTextSize(textSize*2);
		dibujaRuta.getCanvas().drawText(dibujaRuta.getId().toUpperCase(new Locale("es_MX")), xEstacionLabel, yEstacionLabel, paint);
		dibujaRuta.getCanvas().restore();
	}
}
