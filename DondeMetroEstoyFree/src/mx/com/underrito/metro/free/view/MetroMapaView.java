package mx.com.underrito.metro.free.view;
import java.util.ArrayList;
import mx.com.underrito.metro.free.constant.MetroConstant;
import mx.com.underrito.metro.free.globalvalues.MetroGlobalValues;
import mx.com.underrito.metro.free.model.MetroJbDibujaRuta;
import mx.com.underrito.metro.free.model.MetroJbEstacion;
import mx.com.underrito.metro.free.model.MetroJbGraphicHelper;
import mx.com.underrito.metro.free.model.MetroJbLinea;
import mx.com.underrito.metro.free.util.MetroDibujaRedUtil;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

public class MetroMapaView extends View{
	//private static float 			MIN_ZOOM = 1f;	
	//private static float 			MAX_ZOOM = 5f;
	private float 					scaleFactor = 1.f;	
	private ScaleGestureDetector 	scaleGestureDetector;
	private float 					canvasY;
	private float 					canvasX;
	private float 					maxCanvasX;
	private float 					maxCanvasY;
	private float 					lastTouchCanvasY;
	private float 					lastTouchCanvasX;
	private int 					activePointerId;
	private static final int 		INVALID_POINTER_ID = -1;
	boolean							trazaRuta			= false;    
    boolean							trazaEstacion		= false;
    MetroJbGraphicHelper			metroJbGraphicHelper;
    ArrayList<MetroJbEstacion> 		ruta;
    MetroJbEstacion					estacionRuta;
    MetroJbDibujaRuta				dibujaRuta;
		
	public MetroMapaView(Context context) 
	{
		this(context, null);
		
	}
	public MetroMapaView(Context context, AttributeSet attrs) 
	{
		super(context, attrs);		
		this.scaleGestureDetector 	= new ScaleGestureDetector(getContext(), new ScaleListener());
		this.metroJbGraphicHelper	= this.getSettings(context);
		this.ruta					= new ArrayList<MetroJbEstacion>();
		this.estacionRuta			= new MetroJbEstacion();
		this.dibujaRuta				= new MetroJbDibujaRuta();
	}

	
	@Override
	public boolean onTouchEvent(MotionEvent event) 
	{	
		scaleGestureDetector.onTouchEvent(event);
		float touchX = event.getX();
	    float touchY = event.getY();	    
	   switch(event.getAction())
	   {
	   		case MotionEvent.ACTION_DOWN:
	   		{
	           this.lastTouchCanvasX = event.getX();
	           this.lastTouchCanvasY = event.getY();
	           this.activePointerId = event.getPointerId(0);
	           break;
	   		}
	   		case MotionEvent.ACTION_MOVE:
	   		{
	   			final int pointerIndex = event.findPointerIndex(this.activePointerId);
	   			final float x = event.getX(pointerIndex);
	            final float y = event.getY(pointerIndex);
	   			if (!scaleGestureDetector.isInProgress()) 
	   			{
	   				final float dx = x - this.lastTouchCanvasX;
	                final float dy = y - this.lastTouchCanvasY;
	                canvasX = Math.max((maxCanvasX*-1), Math.min((canvasX+dx), maxCanvasX));
	                canvasY = Math.max((maxCanvasY*-1), Math.min((canvasY+dy), maxCanvasY));
	   				invalidate();
	   				
   		 		}	   			
	   			this.lastTouchCanvasX=touchX;
	   			this.lastTouchCanvasY=touchY;
	   			
	   			break;
	   		}
	   		case MotionEvent.ACTION_UP: 
	   		{
	            this.activePointerId= INVALID_POINTER_ID;
	            break;
	   		}
	   		case MotionEvent.ACTION_CANCEL: 
	   		{
	   			this.activePointerId= INVALID_POINTER_ID;
	            break;
	        }
	   		case MotionEvent.ACTION_POINTER_UP: 
	   		{
	            final int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK)>> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
	            final int pointerId = event.getPointerId(pointerIndex);
	            if (pointerId == this.activePointerId) 
	            {
	                // This was our active pointer going up. Choose a new
	                // active pointer and adjust accordingly.
	                final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
	                this.lastTouchCanvasX = event.getX(newPointerIndex);
	                this.lastTouchCanvasY = event.getY(newPointerIndex);
	                this.activePointerId = event.getPointerId(newPointerIndex);
	            }
	            break;
	        }
	   }
		return true;	
	}
	
	@Override
	public void onDraw(Canvas canvas) 
	{
		super.onDraw(canvas);
		ArrayList<MetroJbEstacion> 	listaEstacion;
		int	w						= canvas.getWidth();
		int h						= canvas.getHeight();
		canvas.drawColor(Color.WHITE);
		canvas.save();
		canvas.translate(canvasX, canvasY);		
		canvas.drawColor(Color.WHITE);
		for(MetroJbLinea linea : ((MetroGlobalValues)getContext().getApplicationContext()).getMetroJbRed().getListaLinea())
		{
			//listaEstacion		= new ArrayList<MetroJbEstacion>();
			listaEstacion		= ((MetroGlobalValues)getContext().getApplicationContext()).getMetroUtil().getRutaLinea(((MetroGlobalValues)getContext().getApplicationContext()).getMetroJbRed(), linea);
			this.dibujaRuta.init();
			this.dibujaRuta.setCanvas(canvas);
			this.dibujaRuta.setRuta(listaEstacion);
			this.dibujaRuta.setW(w);
			this.dibujaRuta.setH(h);
			this.dibujaRuta.setColor(linea.getColorHex());
			this.dibujaRuta.setLineaWidth(this.metroJbGraphicHelper.getLineaSize());
			this.dibujaRuta.setLineaAlpha((this.trazaRuta)?100:255);
			this.dibujaRuta.setRoundLinea(this.metroJbGraphicHelper.getEstacionSize());
			this.dibujaRuta.setTextSize(this.metroJbGraphicHelper.getTextSize());
			this.dibujaRuta.setTextColor(MetroConstant.ESTACION_TEXTO_COLOR);
			this.dibujaRuta.setEstacionColor(MetroConstant.ESTACION_COLOR);
			this.dibujaRuta.setEstacionAlpha((this.trazaRuta)?100:255);
			this.dibujaRuta.setNivelZom(this.scaleFactor);
			this.dibujaRuta.setLineaCompleta(true);
			this.dibujaRuta.setId(linea.getId());
			this.dibujaRuta.setInicioTag(linea.getInicioTag());
			this.dibujaRuta.setFinalTag(linea.getFinalTag());
			this.dibujaRuta.setRutaCalculada(false);
			MetroDibujaRedUtil.dibujaRuta(dibujaRuta);
		}
			if(this.trazaRuta)
			{	
				this.dibujaRuta.init();
				this.dibujaRuta.setCanvas(canvas);
				this.dibujaRuta.setRuta(this.ruta);
				this.dibujaRuta.setW(w);
				this.dibujaRuta.setH(h);
				this.dibujaRuta.setLineaAlpha(255);
				this.dibujaRuta.setColor(MetroConstant.SEGMENTO_SEL_COLOR);
				this.dibujaRuta.setLineaWidth(this.metroJbGraphicHelper.getLineaSelSize());
				this.dibujaRuta.setRoundLinea(this.metroJbGraphicHelper.getEstacionSelSize());
				this.dibujaRuta.setTextSize(this.metroJbGraphicHelper.getTextSelSize());
				this.dibujaRuta.setTextColor(MetroConstant.ESTACION_TEXTO_SEL_COLOR);
				this.dibujaRuta.setEstacionColor(MetroConstant.ESTACION_SEL_COLOR);
				this.dibujaRuta.setEstacionAlpha(255);
				this.dibujaRuta.setNivelZom(this.scaleFactor);
				this.dibujaRuta.setLineaCompleta(false);
				this.dibujaRuta.setRutaCalculada(true);
				MetroDibujaRedUtil.dibujaRuta(dibujaRuta);			
			}
			if(this.trazaEstacion)
			{			
				this.dibujaRuta.init();
				this.dibujaRuta.setCanvas(canvas);				
				this.dibujaRuta.setW(w);
				this.dibujaRuta.setH(h);
				this.dibujaRuta.setLineaAlpha(255);
				this.dibujaRuta.setColor(MetroConstant.SEGMENTO_SEL_COLOR);
				this.dibujaRuta.setLineaWidth(this.metroJbGraphicHelper.getLineaSelSize());
				this.dibujaRuta.setRoundLinea(this.metroJbGraphicHelper.getEstacionSelSize());
				this.dibujaRuta.setTextSize(this.metroJbGraphicHelper.getTextSelSize());
				this.dibujaRuta.setTextColor(MetroConstant.ESTACION_TEXTO_SEL_COLOR);
				this.dibujaRuta.setEstacionColor(MetroConstant.ESTACION_TRAZA_COLOR);
				this.dibujaRuta.setEstacionAlpha(255);
				this.dibujaRuta.setNivelZom(MetroConstant.ESCALA_MAXIMA);
				this.dibujaRuta.setEstacionTraza(this.estacionRuta);
				this.dibujaRuta.setRutaCalculada(true);
				//MetroDibujaRedUtil.dibujaEstacion(dibujaRuta);
				
			}
			canvas.scale(scaleFactor, scaleFactor);
			canvas.restore();
			invalidate();
			
	}
	public void setRuta(ArrayList<MetroJbEstacion> ruta)
	{
		if(ruta.size()>=0)
		{
			this.ruta			= ruta;
			this.trazaRuta		= true;
		}
	}
	public void setEstacion(MetroJbEstacion estacionRuta)
	{
		this.estacionRuta		= estacionRuta;
		this.trazaEstacion		= true;
	}
	public MetroJbGraphicHelper getSettings(Context context)
	{
		MetroJbGraphicHelper	metroJbGraphicHelper	= new MetroJbGraphicHelper();
		Log.i("ROD","Density:"+context.getResources().getDisplayMetrics().densityDpi);
		switch (context.getResources().getDisplayMetrics().densityDpi) 
		{ 
			case DisplayMetrics.DENSITY_LOW:
				metroJbGraphicHelper.setLineaSize(1);
				metroJbGraphicHelper.setTextSize(4);
				metroJbGraphicHelper.setEstacionSize(1);
				metroJbGraphicHelper.setLineaSelSize(2);
				metroJbGraphicHelper.setTextSelSize(4);
				metroJbGraphicHelper.setEstacionSelSize(2);
				metroJbGraphicHelper.setEstacionTrazaSize(3);
			    break;
			case DisplayMetrics.DENSITY_MEDIUM:
				metroJbGraphicHelper.setLineaSize(2);
				metroJbGraphicHelper.setTextSize(6);
				metroJbGraphicHelper.setEstacionSize(2);
				metroJbGraphicHelper.setLineaSelSize(3);
				metroJbGraphicHelper.setTextSelSize(6);
				metroJbGraphicHelper.setEstacionSelSize(3);
				metroJbGraphicHelper.setEstacionTrazaSize(4);
			    break;
			case DisplayMetrics.DENSITY_HIGH:
				metroJbGraphicHelper.setLineaSize(3);
				metroJbGraphicHelper.setTextSize(7);
				metroJbGraphicHelper.setEstacionSize(3);
				metroJbGraphicHelper.setLineaSelSize(4);
				metroJbGraphicHelper.setTextSelSize(7);
				metroJbGraphicHelper.setEstacionSelSize(4);
				metroJbGraphicHelper.setEstacionTrazaSize(5);
				break;
			case DisplayMetrics.DENSITY_XHIGH:
				metroJbGraphicHelper.setLineaSize(4);
				metroJbGraphicHelper.setTextSize(8);
				metroJbGraphicHelper.setEstacionSize(4);
				metroJbGraphicHelper.setLineaSelSize(5);
				metroJbGraphicHelper.setTextSelSize(8);
				metroJbGraphicHelper.setEstacionSelSize(5);
				metroJbGraphicHelper.setEstacionTrazaSize(6);
			    break;
			default:
				metroJbGraphicHelper.setLineaSize(4);
				metroJbGraphicHelper.setTextSize(8);
				metroJbGraphicHelper.setEstacionSize(4);
				metroJbGraphicHelper.setLineaSelSize(5);
				metroJbGraphicHelper.setTextSelSize(8);
				metroJbGraphicHelper.setEstacionSelSize(5);
				metroJbGraphicHelper.setEstacionTrazaSize(6);
				break;
		}
		return metroJbGraphicHelper;
	}
	private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener 
	{
		@Override
		public boolean onScale(ScaleGestureDetector detector) 
		{
			scaleFactor *= detector.getScaleFactor();
			scaleFactor = Math.max(MetroConstant.ESCALA_MINIMA, Math.min(scaleFactor, MetroConstant.ESCALA_MAXIMA));			
			maxCanvasX	= ((getWidth()*MetroConstant.MOVER_MAXIMO)*scaleFactor);
			maxCanvasY	= ((getHeight()*MetroConstant.MOVER_MAXIMO)*scaleFactor);
			invalidate();
			return true;
		}
	}
	public void onZoom(boolean zoomIn)
	{
		this.onZoom(zoomIn,1f);
	}
	public void onZoom(boolean zoomIn,float distance)
	{
		distance	= (zoomIn)?this.scaleFactor+distance:this.scaleFactor-distance;	
		this.onZoom(distance);
	}
	public void onZoom(float distance)
	{
		this.scaleFactor	= (distance>MetroConstant.ESCALA_MAXIMA)?MetroConstant.ESCALA_MAXIMA:(distance<MetroConstant.ESCALA_MINIMA)?MetroConstant.ESCALA_MINIMA:distance;
		maxCanvasX	= ((getWidth()*MetroConstant.MOVER_MAXIMO)*scaleFactor);
		maxCanvasY	= ((getHeight()*MetroConstant.MOVER_MAXIMO)*scaleFactor);
	}
	
}
