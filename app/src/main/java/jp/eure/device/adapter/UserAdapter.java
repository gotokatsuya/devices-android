package jp.eure.device.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import jp.eure.device.R;
import jp.eure.device.model.Device;
import jp.eure.device.model.User;

/**
 * Created by katsuyagoto on 2014/10/26.
 */
public class UserAdapter extends ArrayAdapter<User> {

    public UserAdapter(Context context) {
        super(context, 0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user, null);
        }
        TextView nameView   = (TextView)convertView.findViewById(R.id.item_user_name);

        User user = getItem(position);
        nameView.setText(user.getName());
        return convertView;
    }

}
