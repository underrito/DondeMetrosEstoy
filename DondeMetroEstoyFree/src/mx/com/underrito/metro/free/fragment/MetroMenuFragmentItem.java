package mx.com.underrito.metro.free.fragment;
import mx.com.underrito.metro.free.R;
import mx.com.underrito.metro.free.activity.MetroAbout;
import mx.com.underrito.metro.free.activity.MetroBuscaNombre;
import mx.com.underrito.metro.free.activity.MetroMapa;
import mx.com.underrito.metro.free.activity.MetroBuscaEstacion;
import mx.com.underrito.metro.free.activity.MetroBuscaLinea;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
public class MetroMenuFragmentItem extends Fragment  
{
	private int 		imgResource=0;
	private Context 	context;
	public MetroMenuFragmentItem()
	{
		context=null;
		imgResource=0;

	}
	public int getImgResource() {
		return imgResource;
	}
	public void setImgResource(int imgResource) {
		this.imgResource = imgResource;
	}
	public Context getContext() {
		return context;
	}
	public void setContext(Context context) {
		this.context = context;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
	{
		View view	= inflater.inflate(R.layout.metromenuitemfragment, container,false);		
		ImageView imageView;
		LinearLayout linearLayout;
		linearLayout	=(LinearLayout)view.findViewById(R.id.llMenuFragment);
		imageView		= (ImageView)view.findViewById(R.id.imgMenuFragment);
		imageView.setImageResource(this.imgResource);
		imageView.setOnClickListener(new View.OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				selectClassUrl(context, imgResource);
				
			}
		});
		linearLayout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				selectClassUrl(context, imgResource);
				
			}
		});
		return view;
	}
	public void selectClassUrl(Context context,int imgId)
	{
		Intent intent	= null;
		try
		{
			if(imgId==R.drawable.metrobuscaestacion)
			{
				intent= new Intent(context,MetroBuscaEstacion.class);
			}
			else if(imgId==R.drawable.metrobuscalinea)
			{
				intent= new Intent(context,MetroBuscaLinea.class);
			}
			else if(imgId==R.drawable.metrobuscamapa)
			{
				intent= new Intent(context,MetroMapa.class);
			}
			else if(imgId==R.drawable.metrobuscanombre)
			{
				intent= new Intent(context,MetroBuscaNombre.class);
			}
			else if(imgId==R.drawable.metroabout)
			{
				intent= new Intent(context,MetroAbout.class);
			}
	        else
	            intent= new Intent(context,MetroBuscaEstacion.class);
		}
		catch(Exception e)
		{
			intent= new Intent(context,MetroBuscaEstacion.class);
		}
        context.startActivity(intent);
	}
}
