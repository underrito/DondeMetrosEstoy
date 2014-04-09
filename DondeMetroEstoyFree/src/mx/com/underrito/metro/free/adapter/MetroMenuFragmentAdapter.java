package mx.com.underrito.metro.free.adapter;
import java.util.ArrayList;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
public class MetroMenuFragmentAdapter extends FragmentPagerAdapter{
	 private ArrayList<Fragment> listFragment;
	 public MetroMenuFragmentAdapter(FragmentManager fm,ArrayList<Fragment>listFragment)
	 {
		 super(fm);
		 this.listFragment	= listFragment;
	 }
	 @Override
	 public int getCount()
	 {
		 return this.listFragment.size();
	 }
	 @Override
	 public Fragment getItem(int arg0) 
	 {
		 return this.listFragment.get(arg0);	 
	 }
	
}
