package jp.eure.device.ui;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import jp.eure.device.R;

/**
 * Created by katsuyagoto on 2014/10/26.
 */
public abstract class BaseFragment extends Fragment {

    private ActionBarActivity mActivity;

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        mActivity = (ActionBarActivity) activity;
    }

    protected ActionBarActivity getActionBarActivity(){
        if(mActivity != null){
            return mActivity;
        }else {
            return (ActionBarActivity)getActivity();
        }
    }

    protected ActionBar getActionBar(){
        return getActionBarActivity().getSupportActionBar();
    }

    protected Toolbar getToolbar(){
        return  (Toolbar) getActionBarActivity().findViewById(R.id.toolbar);
    }


    protected Button getBtn(View view, int id) {
        return (Button) view.findViewById(id);
    }
    protected ImageButton getIBtn(View view, int id) {
        return (ImageButton) view.findViewById(id);
    }
    protected TextView getTv(View view, int id) {
        return (TextView) view.findViewById(id);
    }
    protected ImageView getIv(View view, int id) {
        return (ImageView) view.findViewById(id);
    }
    protected EditText getEt(View view, int id) {
        return (EditText) view.findViewById(id);
    }
    protected ViewPager getVp(View view, int id) {
        return (ViewPager) view.findViewById(id);
    }
    protected ProgressBar getPb(View view, int id) {
        return (ProgressBar) view.findViewById(id);
    }
    protected ListView getLv(View view, int id) {
        return (ListView) view.findViewById(id);
    }
    protected GridView getGv(View view, int id) {
        return (GridView) view.findViewById(id);
    }
    protected View getV(View view, int id) {
        return (View) view.findViewById(id);
    }
    protected ScrollView getSv(View view, int id) {
        return (ScrollView) view.findViewById(id);
    }
}
