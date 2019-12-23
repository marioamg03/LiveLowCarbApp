package livelowcarb.livelowcarbapp.ui.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import livelowcarb.livelowcarbapp.R;
import livelowcarb.livelowcarbapp.ui.fragments.bmi;
import livelowcarb.livelowcarbapp.ui.fragments.bmr;
import livelowcarb.livelowcarbapp.ui.fragments.home1;
import livelowcarb.livelowcarbapp.ui.fragments.home2;
import livelowcarb.livelowcarbapp.ui.fragments.whr;

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
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    home1 tab1 = new home1();
                    return tab1;

                case 1:
                    home2 tab2 = new home2();
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

    // Code of Home1

    // Code of Home 2
    public void irbmr(View view) {
        Intent i = new Intent(this, bmr.class );
        startActivity(i);
    }
    public void irwhr(View view) {
        Intent i = new Intent(this, whr.class );
        startActivity(i);
    }
    public void irbmi(View view) {
        Intent i = new Intent(this, bmi.class );
        startActivity(i);
    }

}
