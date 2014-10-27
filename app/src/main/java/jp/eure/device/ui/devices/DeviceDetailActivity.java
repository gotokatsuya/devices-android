package jp.eure.device.ui.devices;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;

import java.util.List;

import jp.eure.device.R;
import jp.eure.device.adapter.HistoryAdapter;
import jp.eure.device.model.Device;
import jp.eure.device.model.User;
import jp.eure.device.request.UserRequest;
import jp.eure.device.ui.BaseActivity;

public class DeviceDetailActivity extends BaseActivity{

    private ListView mListView;
    private HistoryAdapter mHistoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_detail);
        mListView = (ListView)findViewById(R.id.activity_device_detail_history);
        mHistoryAdapter = new HistoryAdapter(this);
        fetch();
        Intent intent = getIntent();
        long deviceId = intent.getLongExtra("DEVICE_ID",0L);
        Device device = Device.get(deviceId);
        ((TextView) findViewById(R.id.activity_device_detail_name)).setText(device.getName());
        ((TextView) findViewById(R.id.activity_device_detail_manufacturer)).setText(device.getManufacturer());
        ((TextView) findViewById(R.id.activity_device_detail_carrier)).setText(device.getCarrier());
        ((TextView) findViewById(R.id.activity_device_detail_os)).setText(device.getOs());
        ((TextView) findViewById(R.id.activity_device_detail_screen_size)).setText(device.getSize());
        ((TextView) findViewById(R.id.activity_device_detail_resolution)).setText(device.getResolution());
        ((TextView) findViewById(R.id.activity_device_detail_memory)).setText(device.getMemory());
        ((TextView) findViewById(R.id.activity_device_detail_date_of_release_date)).setText("");
        ((TextView) findViewById(R.id.activity_device_detail_other)).setText(device.getOther());

    }

    private void fetch(){
        //Historyを取得
        UserRequest.list(new Response.Listener<List<User>>() {
            @Override
            public void onResponse(List<User> users) {
                mHistoryAdapter.clear();
//                mHistoryAdapter.addAll(users);
                mHistoryAdapter.notifyDataSetChanged();
                mListView.setAdapter(mHistoryAdapter);
            }
        });
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_device_detail;
    }

}
