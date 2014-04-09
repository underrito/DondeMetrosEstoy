package mx.com.underrito.metro.free.adapter;
import java.util.List;
import mx.com.underrito.metro.free.R;
import mx.com.underrito.metro.free.globalvalues.MetroGlobalValues;
import mx.com.underrito.metro.free.model.MetroJbEstacion;
import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MetroRutaAdapter extends ArrayAdapter<MetroJbEstacion>
{
	private final List<MetroJbEstacion> listaEstacion;
	private final Activity 				activity;
	private Typeface					typeFace;
	String 								correspondencia			= "";
	String 								correspondenciaAnt		= "";
	public MetroRutaAdapter(Activity activity,List<MetroJbEstacion> objects) 
	{
		
		super(activity, R.layout.metroruta, objects);
		this.activity				= activity;
		this.listaEstacion			= objects;
		this.correspondencia		= "";
		this.correspondenciaAnt		= "";
		typeFace					= ((MetroGlobalValues)activity.getApplicationContext()).getMetroUtil().getTypeFace();
	}	
	
	static class ViewHolder 
	{
		protected TextView 	label;
		protected ImageView image1;
		protected ImageView image2;	
		protected TextView  label2;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		ViewHolder viewHolder;
		if(convertView==null || !(convertView.getTag() instanceof ViewHolder))
		{
			LayoutInflater 	inflator	= activity.getLayoutInflater();
			convertView					= inflator.inflate(R.layout.metroruta,null,true);
			viewHolder					= new ViewHolder();
			viewHolder.label			= (TextView)convertView.findViewById(R.id.rutaDetalleLabel);
			viewHolder.image1			= (ImageView)convertView.findViewById(R.id.rutaDetalleImage1);
			viewHolder.image2			= (ImageView)convertView.findViewById(R.id.rutaDetalleImage2);
			viewHolder.label2			= (TextView)convertView.findViewById(R.id.rutaDireccionLabel);
			convertView.setTag(viewHolder);
		}
		else
		{
			viewHolder					= (ViewHolder)convertView.getTag();
		}
		viewHolder.label.setText(listaEstacion.get(position).getNombre());
		viewHolder.label.setTypeface(typeFace);
		viewHolder.image1.setImageResource(listaEstacion.get(position).getImage1());
		viewHolder.image1.setVisibility(listaEstacion.get(position).getImage1Visible());
		viewHolder.image2.setImageResource(listaEstacion.get(position).getImage2());
		viewHolder.image2.setVisibility(listaEstacion.get(position).getImage2Visible());
		viewHolder.label2.setTypeface(typeFace);
		if(listaEstacion.get(position).getDireccion().contentEquals(""))
		{
			viewHolder.label2.setText("");
			viewHolder.label2.setVisibility(View.GONE);
		}
		else
		{
			viewHolder.label2.setText(convertView.getResources().getText(R.string.lbldireccion)+listaEstacion.get(position).getDireccion());
			viewHolder.label2.setVisibility(View.VISIBLE);
		}
		return convertView;		
	}
	

}
