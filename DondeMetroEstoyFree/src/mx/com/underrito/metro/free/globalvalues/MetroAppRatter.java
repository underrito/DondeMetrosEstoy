package mx.com.underrito.metro.free.globalvalues;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import mx.com.underrito.metro.free.R;
import mx.com.underrito.metro.free.constant.MetroConstant;

public class MetroAppRatter 
{
    public void validaVota(Activity activity) 
    {
			if(!((MetroGlobalValues)activity.getApplicationContext()).getMetroUtil().getSharedPreferences(activity.getBaseContext()).getBoolean(MetroConstant.VOTAR_ACTIVO,true))
				return;
	        
	        SharedPreferences  			sharedPreferences	= ((MetroGlobalValues)activity.getApplicationContext()).getMetroUtil().getSharedPreferences(activity.getBaseContext());
	        SharedPreferences.Editor 	editor				= sharedPreferences.edit();
	        
	        // Increment launch counter
	        long contadorCorridas = sharedPreferences.getLong(MetroConstant.VOTAR_CONTADOR, 0) + 1;
	        editor.putLong(MetroConstant.VOTAR_CONTADOR, contadorCorridas);

	        // Get date of first launch
	        Long primerCorrida = sharedPreferences.getLong(MetroConstant.VOTAR_INICIO, 0);
	        if (primerCorrida == 0) {
	        	primerCorrida = System.currentTimeMillis();
	            editor.putLong(MetroConstant.VOTAR_INICIO, primerCorrida);
	        }
	        

	        
	        if (contadorCorridas >= MetroConstant.VOTAR_VECES) 
	        {
		        editor.putLong(MetroConstant.VOTAR_CONTADOR, 0);
	        	showRateDialog(activity, editor);

	        }
        
	        editor.commit();
	    }   
	    
	    public  void showRateDialog(final Activity activity, final SharedPreferences.Editor editor) 
	    {
	        final Dialog dialog = new Dialog(activity);
	        dialog.setTitle(activity.getString(R.string.lblVota) + " " + activity.getString(R.string.app_name));

	        LinearLayout ll = new LinearLayout(activity);
	        ll.setOrientation(LinearLayout.VERTICAL);
	        
	        TextView tv = new TextView(activity);
	        tv.setText(activity.getString(R.string.lblGustaIni) + activity.getString(R.string.app_name) + activity.getString(R.string.lblGustaFin));
	        tv.setWidth(240);
	        tv.setPadding(4, 0, 4, 10);
	        ll.addView(tv);
	        
	        Button b1 = new Button(activity);
	        b1.setText(activity.getString(R.string.lblGustaAhora));
	        b1.setOnClickListener(new View.OnClickListener() 
	        {
	            public void onClick(View v)
	            {
	            	if (editor != null) {
	                    editor.putBoolean(MetroConstant.VOTAR_ACTIVO, false);
	                    editor.commit();
	                }
	                activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + activity.getBaseContext().getPackageName())));
	                dialog.dismiss();
	            }
	        });        
	        ll.addView(b1);

	        Button b2 = new Button(activity);
	        b2.setText(activity.getString(R.string.lblGustaDespues));
	        b2.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	                dialog.dismiss();
	            }
	        });
	        ll.addView(b2);

	        Button b3 = new Button(activity);
	        b3.setText(activity.getString(R.string.lblGustaNunca));
	        b3.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	                if (editor != null) {
	                    editor.putBoolean(MetroConstant.VOTAR_ACTIVO, false);
	                    editor.commit();
	                }
	                dialog.dismiss();
	            }
	        });
	        ll.addView(b3);

	        dialog.setContentView(ll);        
	        dialog.show();        
	    }
	}

