package mx.com.underrito.metro.free.model;
import java.util.ArrayList;
public class MetroJbLinea{
	private String nombre;
	private String color;
	private String id;
	private int image;
	private String colorHex;
	private String colorHex2;
	private double longitud;
	private ArrayList<Integer> listaEstacionNumero;
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
	private int inicioTag;
	private int finalTag;
	public MetroJbLinea() {
		this.nombre 				= "";
		this.color 					= "";
		this.longitud 				= 0;
		this.id 					= "";
		this.listaEstacionNumero	= new ArrayList<Integer>();
		this.image					= 0;
		this.inicioTag				= 0;
		this.finalTag				= 0;

	}
	public String getColor() {
		return color;
	}
	public String getColorHex() {
		return colorHex;
	}
	public String getColorHex2() {
		return colorHex2;
	}
	public String getId() {
		return id;
	}
	public int getImage() {
		return image;
	}
	public ArrayList<Integer> getListaEstacionNumero() {
		return listaEstacionNumero;
	}
	public double getLongitud() {
		return longitud;
	}
	public String getNombre() {
		return nombre;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public void setColorHex(String colorHex) {
		this.colorHex = colorHex;
	}
	public void setColorHex2(String colorHex2) {
		this.colorHex2 = colorHex2;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setImage(int image) {
		this.image = image;
	}
	public void setListaEstacionNumero(ArrayList<Integer> listaEstacionNumero) {
		this.listaEstacionNumero = listaEstacionNumero;
	}
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public String toString()
	{
		return this.getNombre();
	}
}
