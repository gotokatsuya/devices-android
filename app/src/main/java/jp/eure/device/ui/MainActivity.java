package jp.eure.device.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;

import com.android.volley.Response;

import jp.eure.device.R;
import jp.eure.device.helper.ToastHelper;
import jp.eure.device.model.User;
import jp.eure.device.request.UserRequest;
import jp.eure.device.ui.devices.DeviceCreateFragment;
import jp.eure.device.ui.devices.DeviceListFragment;
import jp.eure.device.ui.users.UserCreateFragment;
import jp.eure.device.ui.users.UserListFragment;

public class MainActivity extends BaseActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private NavigationDrawerFragment mNavigationDrawerFragment;

    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = null;
        switch (position){
            case 1:
                mTitle = "Device Create";
                fragment = DeviceCreateFragment.newInstance();
                break;
            case 2:
                mTitle = "Device List";
                fragment = DeviceListFragment.newInstance();
                break;
            case 4:
                mTitle = "User Create";
                fragment = UserCreateFragment.newInstance();
                break;
            case 5:
                mTitle = "User List";
                fragment = UserListFragment.newInstance();
                break;
        }

        if (fragment != null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
        }
    }



    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

}
