package mx.com.underrito.metro.free.adapter;
import java.util.List;
import mx.com.underrito.metro.free.R;
import mx.com.underrito.metro.free.globalvalues.MetroGlobalValues;
import mx.com.underrito.metro.free.model.MetroJbLinea;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MetroLineaSpinnerAdapter extends ArrayAdapter<MetroJbLinea> 
{
	private Context 			context				= null;
	private int					resource			= 0;
	private List<MetroJbLinea> 	objects				= null;
	private	Typeface			typeFace;

	public MetroLineaSpinnerAdapter(Context context, int textViewResourceId,List<MetroJbLinea> objects) {
		super(context, textViewResourceId,objects);
		this.context			= context;
		this.resource			= textViewResourceId;
		this.objects			= objects;
		this.typeFace			= ((MetroGlobalValues)context.getApplicationContext()).getMetroUtil().getTypeFace();
	}
	
	public MetroLineaSpinnerAdapter(Context context, int resource,
			int textViewResourceId, List<MetroJbLinea> objects) {
		super(context, resource, textViewResourceId, objects);
		this.context			= context;
		this.resource			= resource;
		this.objects			= objects;
		this.typeFace			= ((MetroGlobalValues)context.getApplicationContext()).getMetroUtil().getTypeFace();
	}
	

	static class ViewHolder
	{
		TextView	textView		= null;
		ImageView	imageView		= null;
		LinearLayout linearLayout	= null;		
	}
	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		LayoutInflater		layoutInflater		= null;
		ViewHolder			viewHolder;
		if(convertView==null || !(convertView.getTag() instanceof ViewHolder))
		{
			viewHolder				= new ViewHolder();
			layoutInflater			= (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView				= layoutInflater.inflate(this.resource, null);
			viewHolder.textView		= (TextView)convertView.findViewById(R.id.textComplexItem);
			viewHolder.imageView	= (ImageView)convertView.findViewById(R.id.imageComplexItem);
			viewHolder.linearLayout	= (LinearLayout)convertView.findViewById(R.id.layoutComplexItem);
			convertView.setTag(viewHolder);
		}
		else
			viewHolder				= (ViewHolder)convertView.getTag();
		viewHolder.textView.setBackgroundColor(Color.parseColor(objects.get(position).getColorHex()));
		viewHolder.textView.setText(objects.get(position).getNombre());
		viewHolder.imageView.setImageResource(objects.get(position).getImage());
		viewHolder.imageView.setBackgroundColor(Color.parseColor(objects.get(position).getColorHex()));
		viewHolder.imageView.getBackground().setAlpha(0);
		viewHolder.textView.setTypeface(typeFace);
		viewHolder.linearLayout.setBackgroundColor(Color.parseColor(objects.get(position).getColorHex()));
		return convertView;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		LayoutInflater		layoutInflater		= null;
		ViewHolder			viewHolder;
		if(convertView==null || !(convertView.getTag() instanceof ViewHolder))
		{
			viewHolder				= new ViewHolder();
			layoutInflater			= (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView				= layoutInflater.inflate(this.resource, null);
			viewHolder.textView		= (TextView)convertView.findViewById(R.id.textComplexItem);
			viewHolder.imageView	= (ImageView)convertView.findViewById(R.id.imageComplexItem);
			viewHolder.linearLayout	= (LinearLayout)convertView.findViewById(R.id.layoutComplexItem);
			convertView.setTag(viewHolder);
		}
		else
			viewHolder				= (ViewHolder)convertView.getTag();
		viewHolder.textView.setBackgroundColor(Color.parseColor(objects.get(position).getColorHex()));
		viewHolder.textView.setText(objects.get(position).getNombre());
		viewHolder.imageView.setImageResource(objects.get(position).getImage());
		viewHolder.imageView.setBackgroundColor(Color.parseColor(objects.get(position).getColorHex()));
		viewHolder.imageView.getBackground().setAlpha(0);
		viewHolder.textView.setTypeface(typeFace);
		viewHolder.linearLayout.setBackgroundColor(Color.parseColor(objects.get(position).getColorHex()));
		parent.setBackgroundColor(Color.parseColor(objects.get(position).getColorHex()));
		return convertView;
	} 
	

}
