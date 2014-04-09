package mx.com.underrito.metro.free.model;
import android.os.Parcel;
import android.os.Parcelable;
public class MetroJbSegmento  implements Parcelable{
	private final String id; 
	private final MetroJbEstacion origen;
	private final MetroJbEstacion destino;
	private final double peso; 
	private final String linea;
	private final MetroJbEstacion direccion;
	

	public static final Parcelable.Creator<MetroJbSegmento> CREATOR= new Creator<MetroJbSegmento>(){
		@Override
		public MetroJbSegmento createFromParcel(Parcel source) {
			MetroJbEstacion destino=source.readParcelable(MetroJbEstacion.class.getClassLoader());
			MetroJbEstacion origen=source.readParcelable(MetroJbEstacion.class.getClassLoader());
			String id=source.readString();
			String linea=source.readString();
			double peso=source.readDouble();
			MetroJbEstacion direccion=source.readParcelable(MetroJbEstacion.class.getClassLoader());
			MetroJbSegmento segmento= new MetroJbSegmento(id, origen, destino, peso, linea,direccion);
			return segmento;
		}
		@Override
		public MetroJbSegmento[] newArray(int size) {
			return new MetroJbSegmento[size];
		}
	};
	public MetroJbSegmento(String id, MetroJbEstacion origen, MetroJbEstacion destino, double peso,String linea,MetroJbEstacion direccion) {
		this.id = id;
		this.origen = origen;
		this.destino = destino;
		this.peso = peso;
		this.linea=linea;
		this.direccion=direccion;
	}
	@Override
	public int describeContents() {
		return 0;
	}
	public MetroJbEstacion getDestino() {
		return destino;
	}
	public String getId() {
		return id;
	}
	public String getLinea() {
		return linea;
	}
	public MetroJbEstacion getOrigen() {
		return origen;
	}
	public double getPeso() {
		return peso;
	}
	public MetroJbEstacion getDireccion() {
		return direccion;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(destino, flags);
		dest.writeParcelable(origen, flags);
		dest.writeString(id);
		dest.writeString(linea);
		dest.writeDouble(peso);
		dest.writeParcelable(direccion, flags);
	}
}