package jp.eure.device.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import jp.eure.device.R;
import jp.eure.device.model.History;
import jp.eure.device.model.User;

/**
 * Created by takasaki on 2014/10/27.
 */
public class HistoryAdapter extends ArrayAdapter<History>{
    public HistoryAdapter(Context context) {
        super(context, 0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_history, null);
        }
        TextView nameView   = (TextView)convertView.findViewById(R.id.item_user_name);

        History history = getItem(position);
        nameView.setText(history.getName());
        return convertView;
    }}
