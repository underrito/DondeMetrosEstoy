package mx.com.underrito.metro.free.model;
import java.util.ArrayList;
public class MetroJbResultado {
	private ArrayList<MetroJbEstacion> 	ruta;
	private ArrayList<MetroJbSegmento>	listaSegmento;
	private MetroJbEstacion	estacionTraza;
	public MetroJbEstacion getEstacionTraza() {
		return estacionTraza;
	}
	public void setEstacionTraza(MetroJbEstacion estacionTraza) {
		this.estacionTraza = estacionTraza;
	}
	public ArrayList<MetroJbEstacion> getRuta() {
		return ruta;
	}
	public void setRuta(ArrayList<MetroJbEstacion> ruta) {
		this.ruta = ruta;
	}
	public ArrayList<MetroJbSegmento> getListaSegmento() {
		return listaSegmento;
	}
	public void setListaSegmento(ArrayList<MetroJbSegmento> listaSegmento) {
		this.listaSegmento = listaSegmento;
	}
}