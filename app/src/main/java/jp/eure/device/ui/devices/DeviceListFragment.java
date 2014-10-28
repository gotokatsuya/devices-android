package jp.eure.device.ui.devices;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.ListFragment;
import android.support.v7.internal.widget.ActivityChooserModel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;

import com.android.volley.Response;

import java.util.List;

import jp.eure.device.R;
import jp.eure.device.adapter.DeviceAdapter;
import jp.eure.device.model.Device;
import jp.eure.device.request.DeviceRequest;
import jp.eure.device.ui.BaseActivity;
import jp.eure.device.ui.BaseFragment;

public class DeviceListFragment extends BaseFragment {

    private GridView mGridView;

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
        fetch();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_device_list, container, false);
        mGridView = (GridView) root.findViewById(R.id.fragment_device_list_gridview);
        mGridView.setAdapter(mDeviceAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GridView gridView = (GridView)parent;
                Device device = (Device)gridView.getItemAtPosition(position);
                Intent intent = new Intent(getActionBarActivity(), DeviceDetailActivity.class);
                intent.putExtra("DEVICE_ID", device.getIdentity());
                startActivity(intent);
            }
        });
        return root;
    }

    private void fetch(){
        DeviceRequest.list(new Response.Listener<List<Device>>() {
            @Override
            public void onResponse(List<Device> devices) {
                mDeviceAdapter.addAll(devices);
            }
        });
    }

}
