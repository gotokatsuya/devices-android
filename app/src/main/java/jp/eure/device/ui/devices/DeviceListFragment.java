package jp.eure.device.ui.devices;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.ListFragment;
import android.support.v7.internal.widget.ActivityChooserModel;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Response;

import java.util.List;

import jp.eure.device.adapter.DeviceAdapter;
import jp.eure.device.model.Device;
import jp.eure.device.request.DeviceRequest;
import jp.eure.device.ui.BaseActivity;

public class DeviceListFragment extends ListFragment {

    public static DeviceListFragment newInstance() {
        DeviceListFragment fragment = new DeviceListFragment();
        return fragment;
    }

    public DeviceListFragment() {
    }

    private DeviceAdapter mDeviceAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDeviceAdapter = new DeviceAdapter(getActivity());
        setListAdapter(mDeviceAdapter);
        fetch();
    }

    private void fetch(){
        DeviceRequest.list(new Response.Listener<List<Device>>() {
            @Override
            public void onResponse(List<Device> devices) {
                mDeviceAdapter.addAll(devices);
            }
        });
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }

}
