package mx.com.underrito.metro.free.globalvalues;
import java.util.ArrayList;
import android.app.Activity;
import android.widget.LinearLayout;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import mx.com.underrito.metro.free.constant.MetroConstant;
import mx.com.underrito.metro.free.model.MetroJbEstacion;
import mx.com.underrito.metro.free.model.MetroJbLinea;
public class MetroUtilAdMob 
{
	public static ArrayList<MetroJbEstacion> getRutaLinea(MetroRed red,MetroJbLinea linea)
	{
		ArrayList<MetroJbEstacion> listaLinea	= new ArrayList<MetroJbEstacion>();
		for(int estacion:linea.getListaEstacionNumero())
		{
			listaLinea.add(red.getMapaEstacion().get(estacion));
		}
		return listaLinea;
	}
	public AdView displayAd(Activity activity,int id)
	{
		AdView				adView;
		LinearLayout		llBannerAD;
		llBannerAD			= (LinearLayout)activity.findViewById(id);
	  	adView				= new AdView(activity, AdSize.BANNER,MetroConstant.CAPANIA);
	    AdRequest			request= new AdRequest();
	    request.addTestDevice(AdRequest.TEST_EMULATOR);
	    request.addTestDevice(MetroConstant.DEVICE_DEV);
	    adView.loadAd(request);
	    llBannerAD.addView(adView);
	    return adView;
	        
	}
	
	
}
