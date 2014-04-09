package mx.com.underrito.metro.free.adapter;
import java.util.ArrayList;
import java.util.List;
import mx.com.underrito.metro.free.R;
import mx.com.underrito.metro.free.adapter.MetroEstacionSpinnerAdapter.ViewHolder;
import mx.com.underrito.metro.free.globalvalues.MetroGlobalValues;
import mx.com.underrito.metro.free.model.MetroJbEstacion;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Filter;

public class MetroBuscaRutaAdapter extends ArrayAdapter<MetroJbEstacion>
{
	private final List<MetroJbEstacion> objects;
	private List<MetroJbEstacion> 		filteredObjects;
	private final Context 				context;
	private final int					resource;
	private final int					textViewResourceId;
	Typeface							typeFace;
	public MetroBuscaRutaAdapter(Context context, int resource,
			int textViewResourceId, List<MetroJbEstacion> objects) 
	{
		super(context, resource, textViewResourceId, objects);	
	
		this.context				= context;
		this.objects				= objects;
		this.resource				= resource;
		this.filteredObjects		= this.objects;
		this.textViewResourceId		= textViewResourceId;
		typeFace					= ((MetroGlobalValues)context.getApplicationContext()).getMetroUtil().getTypeFace();
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
			viewHolder.textView		= (TextView)convertView.findViewById(R.id.textSimpleItem);
			convertView.setTag(viewHolder);
		}
		else
			viewHolder				= (ViewHolder)convertView.getTag();
		convertView.findViewById(this.textViewResourceId);
		viewHolder.textView.setTypeface(typeFace);
		
		return convertView;
	}



	@Override
	public View getView (int position, View convertView, ViewGroup parent)
	{
	        final LayoutInflater inflater = LayoutInflater.from(getContext());
	        final TextView view 	= (TextView) inflater.inflate(resource, null);
	        if(position<filteredObjects.size())
	        {
	        	view.setText(filteredObjects.get(position).getNombre());
	        	view.setTypeface(typeFace);
	        }
	        return view;
	    }
	@Override
	public int getCount()
	{
		return this.filteredObjects.size();
	}
	
	@Override
	public MetroJbEstacion getItem(int index)
	{
		return this.filteredObjects.get(index);
	}
	@Override
	public Filter getFilter()
	{
		Filter filter	= new Filter() {

			@Override
			protected FilterResults performFiltering(CharSequence constraint)
			{
				FilterResults filterResults = new FilterResults();
				List<MetroJbEstacion>listaEstacionFilter	= new ArrayList<MetroJbEstacion>();
				if(constraint!=null)
				{
					for(MetroJbEstacion estacion: objects)
					{ 
						if(estacion.getNombre().toLowerCase().contains(constraint.toString().toLowerCase()))
						{
							listaEstacionFilter.add(estacion);
						}
					}
					
					filterResults.values	= listaEstacionFilter;
					filterResults.count		= listaEstacionFilter.size();
				}
				return filterResults;
			}

			@SuppressWarnings("unchecked")
			@Override
			protected void publishResults(CharSequence constraint,FilterResults results)
			{
				if(results != null && results.count>0)
				{
					filteredObjects	=(List<MetroJbEstacion>) results.values;
					notifyDataSetChanged();
				}
				else
				{
					filteredObjects	= new ArrayList<MetroJbEstacion>();
					notifyDataSetChanged();
				}
			}
			 @Override
			 public String convertResultToString(Object resultValue) 
			 {
			    return ((MetroJbEstacion)(resultValue)).getNombre();
			 }
		
		};
		return filter;
	}
}
