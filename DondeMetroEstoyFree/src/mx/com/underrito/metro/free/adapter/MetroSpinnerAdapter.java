package mx.com.underrito.metro.free.adapter;
import java.util.ArrayList;
import java.util.List;
import mx.com.underrito.metro.free.R;
import mx.com.underrito.metro.free.model.MetroJbLinea;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
public class MetroSpinnerAdapter extends ArrayAdapter<MetroJbLinea>
{
	private final List<MetroJbLinea> 	listaLinea;
	private final Activity 				activity;
	public MetroSpinnerAdapter(Activity activity,ArrayList<MetroJbLinea> objects) 
	{
		super(activity, R.layout.metrospinneritemimage, objects);
		this.activity				= activity;
		this.listaLinea				= objects;
	}	
	static class ViewHolder 
	{
		protected TextView 			label;
		protected ImageView 		image;				
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		ViewHolder viewHolder;
		if(convertView==null || !(convertView.getTag() instanceof ViewHolder))
		{
			LayoutInflater 	inflator	= activity.getLayoutInflater();
			convertView					= inflator.inflate(R.layout.metrospinneritemimage,null,true);
			viewHolder					= new ViewHolder();
			viewHolder.label			= (TextView)convertView.findViewById(R.id.textViewSpinnerItem);
			viewHolder.image			= (ImageView)convertView.findViewById(R.id.imageSpinnerItem);
			convertView.setTag(viewHolder);
		}
		else
		{
			viewHolder					= (ViewHolder)convertView.getTag();
		}
		viewHolder.label.setText(listaLinea.get(position).getNombre());
		viewHolder.image.setImageResource(listaLinea.get(position).getImage());
		return convertView;		
	}
	

}
