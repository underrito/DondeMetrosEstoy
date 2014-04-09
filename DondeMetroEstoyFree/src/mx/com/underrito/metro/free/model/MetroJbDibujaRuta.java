package mx.com.underrito.metro.free.model;
import java.util.ArrayList;
import android.graphics.Canvas;
public class MetroJbDibujaRuta 
{
	private Canvas 						canvas;
	private ArrayList<MetroJbEstacion> 	ruta;
	private int 						w;
	private int 						h;
	private String 						color;
	private int 						lineaWidth;
	private int 						roundLinea;
	private int 						textSize;
	private String 						textColor;
	private String 						estacionColor;
	private int 						estacionAlpha;
	private int 						lineaAlpha;
	private float 						nivelZom;
	private boolean 					lineaCompleta;
	private int 						inicioTag;
	private int 						finalTag;
	private String 						id;
	private MetroJbEstacion				estacionTraza;
	private boolean						rutaCalculada;
	public boolean isRutaCalculada() {
		return rutaCalculada;
	}
	public void setRutaCalculada(boolean rutaCalculada) {
		this.rutaCalculada = rutaCalculada;
	}
	public MetroJbDibujaRuta()
	{
		this.init();
	}
	public void init()
	{
		this.ruta= new ArrayList<MetroJbEstacion>();
		this.w=0;
		this.h=0;
		this.color="";
		this.lineaWidth=0;
		this.roundLinea=0;
		this.textSize=0;
		this.textColor="";
		this.estacionColor="";
		this.estacionAlpha=0;
		this.lineaAlpha=0;
		this.nivelZom=0f;
		this.lineaCompleta=false;
		this.inicioTag=0;
		this.finalTag=0;
		this.id="";
		this.estacionTraza= new MetroJbEstacion();
		this.rutaCalculada=false;
		
	}
	public MetroJbEstacion getEstacionTraza() {
		return estacionTraza;
	}
	public void setEstacionTraza(MetroJbEstacion estacionTraza) {
		this.estacionTraza = estacionTraza;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getInicioTag() {
		return inicioTag;
	}
	public void setInicioTag(int inicioTag) {
		this.inicioTag = inicioTag;
	}
	public int getFinalTag() {
		return finalTag;
	}
	public void setFinalTag(int finalTag) {
		this.finalTag = finalTag;
	}
	public boolean isLineaCompleta() {
		return lineaCompleta;
	}
	public void setLineaCompleta(boolean lineaCompleta) {
		this.lineaCompleta = lineaCompleta;
	}
	public float getNivelZom() {
		return nivelZom;
	}
	public void setNivelZom(float nivelZom) {
		this.nivelZom = nivelZom;
	}
	public Canvas getCanvas() {
		return canvas;
	}
	public String getColor() {
		return color;
	}
	public int getEstacionAlpha() {
		return estacionAlpha;
	}
	public String getEstacionColor() {
		return estacionColor;
	}
	public int getH() {
		return h;
	} 
	public int getLineaAlpha() {
		return lineaAlpha;
	}
	public int getLineaWidth() {
		return lineaWidth;
	}
	public int getRoundLinea() {
		return roundLinea;
	}
	public ArrayList<MetroJbEstacion> getRuta() {
		return ruta;
	}
	public String getTextColor() {
		return textColor; 
	}
	public int getTextSize() {
		return textSize;
	}
	public int getW() {
		return w;
	}
	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public void setEstacionAlpha(int estacionAlpha) {
		this.estacionAlpha = estacionAlpha;
	}
	public void setEstacionColor(String estacionColor) {
		this.estacionColor = estacionColor;
	}
	public void setH(int h) {
		this.h = h;
	}
	public void setLineaAlpha(int lineaAlpha) {
		this.lineaAlpha = lineaAlpha;
	}
	public void setLineaWidth(int lineaWidth) {
		this.lineaWidth = lineaWidth;
	}
	public void setRoundLinea(int roundLinea) {
		this.roundLinea = roundLinea;
	}
	public void setRuta(ArrayList<MetroJbEstacion> ruta) {
		this.ruta = ruta;
	}
	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}
	public void setTextSize(int textSize) {
		this.textSize = textSize;
	}
	public void setW(int w) {
		this.w = w;
	}
}
