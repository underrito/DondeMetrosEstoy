package mx.com.underrito.metro.free.adapter;
import java.util.List;
import mx.com.underrito.metro.free.R;
import mx.com.underrito.metro.free.globalvalues.MetroGlobalValues;
import mx.com.underrito.metro.free.model.MetroJbEstacion;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MetroEstacionSpinnerAdapter extends ArrayAdapter<MetroJbEstacion> 
{
	private Context 				context				= null;
	private int						resource			= 0;
	private int						textViewResourceId	= 0;
	private List<MetroJbEstacion> 	objects				= null;
	private	Typeface				typeFace;
	public MetroEstacionSpinnerAdapter(Context context, int resource,
			int textViewResourceId, List<MetroJbEstacion> objects) {
		super(context, resource, textViewResourceId, objects);
		this.context			= context;
		this.resource			= resource;
		this.objects			= objects;
		this.textViewResourceId	= textViewResourceId;
		this.typeFace			= ((MetroGlobalValues)context.getApplicationContext()).getMetroUtil().getTypeFace();
	}
	static class ViewHolder
	{
		TextView	textView		= null;
		TextView	textViewResult	= null;
	}
	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		LayoutInflater		layoutInflater		= null;
		ViewHolder			viewHolder;
		if(convertView==null || !(convertView.getTag() instanceof ViewHolder))
		{
			viewHolder					= new ViewHolder();
			layoutInflater				= (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView					= layoutInflater.inflate(this.resource, null);
			viewHolder.textView			= (TextView)convertView.findViewById(R.id.textSimpleItem);
			viewHolder.textViewResult	= (TextView)convertView.findViewById(this.textViewResourceId);
			convertView.setTag(viewHolder);
		}
		else
			viewHolder				= (ViewHolder)convertView.getTag();
		viewHolder.textView.setText(objects.get(position).getNombre());
		viewHolder.textView.setTypeface(typeFace);
		viewHolder.textViewResult.setTypeface(typeFace);
		return convertView;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		LayoutInflater		layoutInflater		= null;
		ViewHolder			viewHolder;		        
		if(convertView==null || !(convertView.getTag() instanceof ViewHolder))
		{
			viewHolder					= new ViewHolder();
			layoutInflater				= (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView					= layoutInflater.inflate(this.resource, null);
			viewHolder.textView			= (TextView)convertView.findViewById(R.id.textSimpleItem);
			viewHolder.textViewResult	= (TextView)convertView.findViewById(this.textViewResourceId);
			convertView.setTag(viewHolder);
		}
		else
			viewHolder				= (ViewHolder)convertView.getTag();
		viewHolder.textView.setText(objects.get(position).getNombre());
		viewHolder.textView.setTypeface(typeFace);
		viewHolder.textViewResult.setTypeface(typeFace);
		return convertView;
	} 
	

}
