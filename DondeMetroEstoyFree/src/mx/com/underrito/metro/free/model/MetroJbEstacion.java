package mx.com.underrito.metro.free.model;
import android.os.Parcel;
import android.os.Parcelable;
public class MetroJbEstacion  implements Parcelable 
{
	private String 	nombre;
	private int 	orientacion;
	private double 	xMaps;
	private double 	yMaps;
	private int 	x;
	private int 	y;
	private int		image1;
	private int		image2;
	private int		image1Visible;
	private int 	image2Visible;
	private int 	id;
	private String	direccion;
	private int		transborde;

	public static final Parcelable.Creator<MetroJbEstacion> CREATOR= new Creator<MetroJbEstacion>() 
	{
		@Override
		public MetroJbEstacion createFromParcel(Parcel source) {
			MetroJbEstacion estacion= new MetroJbEstacion();
			estacion.nombre=source.readString();
			estacion.id=source.readInt(); 
			estacion.image1=source.readInt();
			estacion.image1Visible=source.readInt();
			estacion.image2=source.readInt();
			estacion.image2Visible=source.readInt();
			estacion.x=source.readInt();
			estacion.y=source.readInt();
			estacion.xMaps=source.readDouble();
			estacion.yMaps=source.readDouble();
			estacion.orientacion=source.readInt();
			estacion.direccion=source.readString();
			estacion.transborde=source.readInt();
			return estacion;
		}
		
		@Override
		public MetroJbEstacion[] newArray(int size) {
			return new MetroJbEstacion[size];			
		}
	};
	public MetroJbEstacion() {
		this.nombre = "";
		this.id=0;
		this.image1=0;
		this.image2=0;
		this.image1Visible=0;
		this.image2Visible=0;
		this.x=0;
		this.y=0;
		this.xMaps=0;
		this.yMaps=0;
		this.orientacion=0;
		this.direccion="";
		this.transborde=0;
	}
	public MetroJbEstacion(int id,String nombre,int x,int y,Double xMaps,Double yMaps,int orientacion,int transborde) {
		this.nombre = nombre;
		this.id=id;
		this.image1=0;
		this.image2=0;
		this.image1Visible=0;
		this.image2Visible=0;
		this.x=x;
		this.y=y;
		this.xMaps=xMaps;
		this.yMaps=yMaps;
		this.orientacion=orientacion;
		this.direccion="";
		this.transborde=transborde;
		
	}
	public MetroJbEstacion(int id,String nombre, String imagen,int transborde) {
		this.nombre = nombre;
		this.id=id;
		this.image1=0;
		this.image2=0;
		this.image1Visible=0;
		this.image2Visible=0;
		this.x=0;
		this.y=0;
		this.xMaps=0;
		this.yMaps=0;
		this.orientacion=0;
		this.direccion="";
		this.transborde=transborde;
	}

	@Override
	public int describeContents() {
		return 0;
	}
	public int getId() {
		return id;
	}
	public int getImage1() {
		return image1;
	}
	public int getImage1Visible() {
		return image1Visible;
	}
	public int getImage2() {
		return image2;
	}
	public int getImage2Visible() {
		return image2Visible;
	}
	public String getNombre() {
		return nombre;
	}
	public int getOrientacion() {
		return orientacion;
	}
	public int getX() {
		return x;
	}
	public double getxMaps() {
		return xMaps;
	}
	public int getY() {
		return y;
	}
	public double getyMaps() {
		return yMaps;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setImage1(int image1) {
		this.image1 = image1;
	}
	public void setImage1Visible(int image1Visible) {
		this.image1Visible = image1Visible;
	}
	public void setImage2(int image2) {
		this.image2 = image2;
	}
	public void setImage2Visible(int image2Visible) {
		this.image2Visible = image2Visible;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setOrientacion(int orientacion) {
		this.orientacion = orientacion;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setxMaps(double xMaps) {
		this.xMaps = xMaps;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setyMaps(double yMaps) {
		this.yMaps = yMaps;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getTransborde()
	{
		return this.transborde;
	}
	public void setTransborde(int transborde)
	{
		this.transborde=transborde;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(nombre);
		dest.writeInt(id);
		dest.writeInt(image1);
		dest.writeInt(image1Visible);
		dest.writeInt(image2);
		dest.writeInt(image2Visible);
		dest.writeInt(x);
		dest.writeInt(y);
		dest.writeDouble(xMaps);
		dest.writeDouble(yMaps);
		dest.writeInt(orientacion);
		dest.writeString(direccion);
		dest.writeInt(transborde);
	}
	@Override
	public String toString()
	{
		return this.getNombre();
	}
}