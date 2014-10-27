package jp.eure.device.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import jp.eure.device.R;
import jp.eure.device.helper.ToastHelper;
import jp.eure.device.model.Device;
import jp.eure.device.model.User;

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
        TextView nameView = (TextView)convertView.findViewById(R.id.item_device_name);
        TextView osView = (TextView)convertView.findViewById(R.id.item_device_os);
        TextView screenSizeView = (TextView)convertView.findViewById(R.id.item_device_screen_size);
        TextView taiwanView = (TextView)convertView.findViewById(R.id.item_device_taiwan);
        TextView statusView = (TextView)convertView.findViewById(R.id.item_device_status);
        Button button = (Button)convertView.findViewById(R.id.item_device_button);

        final Device device = getItem(position);
        nameView.setText(device.getName());
        osView.setText(device.getOs());
        screenSizeView.setText(device.getSize());
        taiwanView.setText("台湾版：NG");
//        taiwanView.setText("台湾版："+device.getTaiwan());
/*        statusView.setText(device.getStatusText());
        if (device.getStatus == Device.RENTED_OUT) {
            statusView.setTextColor(Color.RED);
        } else {
            statusView.setTextColor(Color.WHITE);
        }*/
        if (device.getUser() != null) {
            statusView.setText("貸出中");
            statusView.setTextColor(Color.RED);
            button.setText("貸出");
        } else {
            statusView.setText("貸出可");
            button.setText("返却");
        }
        button.setClickable(true);
        button.setEnabled(true);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (device.getUser() != null) {
                    device.setUser(null);
                } else {
                    User user = new User();
                    device.setUser(user);
                }
            }
        });
        return convertView;
    }

}
