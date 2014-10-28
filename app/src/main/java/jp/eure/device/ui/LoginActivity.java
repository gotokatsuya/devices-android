package jp.eure.device.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;

import com.android.volley.Response;

import java.util.List;

import jp.eure.device.R;
import jp.eure.device.adapter.UserAdapter;
import jp.eure.device.helper.ToastHelper;
import jp.eure.device.model.User;
import jp.eure.device.request.UserRequest;

public class LoginActivity extends BaseActivity {

    private NavigationDrawerFragment mNavigationDrawerFragment;

    private CharSequence mTitle;

    private UserAdapter mUserAdapter;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_login);
        mListView = (ListView)findViewById(R.id.activity_login_user_listview);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listView = (ListView)parent;
                User user = (User)listView.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("USER_ID", user.getIdentity());
                startActivity(intent);
            }
        });
        mUserAdapter = new UserAdapter(this);
        fetch();

        final EditText nameEdit = (EditText)findViewById(R.id.username_edit);
        Button createBtn = (Button)findViewById(R.id.user_create_btn);
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEdit.getText().toString();
                if (!TextUtils.isEmpty(name)) {
                    UserRequest.create(name, new Response.Listener<User>() {
                        @Override
                        public void onResponse(User user) {
                            ToastHelper.shortMessage(getApplicationContext(), user.getName());
                            fetch();
                        }
                    });
                }
            }
        });

    }

    private void fetch(){
        UserRequest.list(new Response.Listener<List<User>>() {
            @Override
            public void onResponse(List<User> users) {
                mUserAdapter.clear();
                mUserAdapter.addAll(users);
                mUserAdapter.notifyDataSetChanged();
                mListView.setAdapter(mUserAdapter);
            }
        });
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_login;
    }

}
