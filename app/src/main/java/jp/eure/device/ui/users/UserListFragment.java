package jp.eure.device.ui.users;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Response;

import java.util.List;

import jp.eure.device.adapter.DeviceAdapter;
import jp.eure.device.adapter.UserAdapter;
import jp.eure.device.model.Device;
import jp.eure.device.model.User;
import jp.eure.device.request.DeviceRequest;
import jp.eure.device.request.UserRequest;

public class UserListFragment extends ListFragment {

    public static UserListFragment newInstance() {
        UserListFragment fragment = new UserListFragment();
        return fragment;
    }

    public UserListFragment() {
    }

    private UserAdapter mUserAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserAdapter = new UserAdapter(getActivity());
        setListAdapter(mUserAdapter);
        fetch();
    }

    private void fetch(){
        UserRequest.list(new Response.Listener<List<User>>() {
            @Override
            public void onResponse(List<User> users) {
                mUserAdapter.addAll(users);
            }
        });
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }

}
