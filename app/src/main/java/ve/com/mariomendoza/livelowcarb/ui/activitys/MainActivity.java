package ve.com.mariomendoza.livelowcarb.ui.activitys;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import ve.com.mariomendoza.livelowcarb.R;
import ve.com.mariomendoza.livelowcarb.ui.fragments.foodsearchfragment.Foodsearch;
import ve.com.mariomendoza.livelowcarb.ui.fragments.gadgetsfragment.Gadgets;

public class MainActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public static class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    Foodsearch tab1 = new Foodsearch();
                    return tab1;

                case 1:
                    Gadgets tab2 = new Gadgets();
                    return tab2;

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Me muestra un total de X paginas donde el retorno es el nro total de paginas
            return 2;
        }
    }

    //este codigo se utiliza para colocarle nomnbres a las pestañas
    //@Override
    //public CharSequence getPageTitle(int position) {
    //    switch (position) {
    //        case 0:
    //            return "HOME";
    //        case 1:
    //            return "WIGETS";
    //       // case 2:
    //      //      return "ALGO";
    //    }
    //    return null;
    //}
}
