package jp.eure.device.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import jp.eure.device.R;
import jp.eure.device.model.Device;

/**
 * Created by katsuyagoto on 2014/10/26.
 */
public class DeviceAdapter extends ArrayAdapter<Device> {

    public DeviceAdapter(Context context) {
        super(context, 0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_device, null);
        }
        TextView nameView   = (TextView)convertView.findViewById(R.id.item_device_name);

        Device device = getItem(position);
        nameView.setText(device.getName());
        return convertView;
    }

}
