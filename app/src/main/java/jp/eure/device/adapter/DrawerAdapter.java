package jp.eure.device.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import jp.eure.device.R;

/**
 * Created by katsuyagoto on 2014/10/26.
 */
public class DrawerAdapter extends ArrayAdapter<DrawerAdapter.Item> {

    public static class Item {
        enum Type{
            TITLE,ELEMENT;
        }
        public Type type;
        public String text;

        public Item(Type type, String text) {
            this.type = type;
            this.text = text;
        }
    }

    public DrawerAdapter(Context context) {
        super(context, 0);
    }

    public void add(String title, String[] elements){
        Item item = null;
        item = new Item(Item.Type.TITLE, title);
        add(item);

        for (int i=0, n=elements.length; i<n; i++) {
            item = new Item(Item.Type.ELEMENT, elements[i]);
            add(item);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_drawer, null);
        }
        TextView titleView   = (TextView)convertView.findViewById(R.id.item_drawer_title);
        TextView elementView = (TextView)convertView.findViewById(R.id.item_drawer_element);

        Item item = getItem(position);
        switch (item.type){
            case TITLE:
                titleView.setVisibility(View.VISIBLE);
                elementView.setVisibility(View.GONE);
                titleView.setText(item.text);
                convertView.setEnabled(false);
                break;
            case ELEMENT:
                titleView.setVisibility(View.GONE);
                elementView.setVisibility(View.VISIBLE);
                elementView.setText(item.text);
                convertView.setBackgroundResource(R.drawable.selector_primary);
                convertView.setEnabled(true);
                break;
        }
        return convertView;
    }

}
