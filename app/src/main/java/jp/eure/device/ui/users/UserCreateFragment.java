package jp.eure.device.ui.users;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Response;

import jp.eure.device.R;
import jp.eure.device.helper.ToastHelper;
import jp.eure.device.model.User;
import jp.eure.device.request.UserRequest;
import jp.eure.device.ui.BaseFragment;

public class UserCreateFragment extends BaseFragment {

    public static UserCreateFragment newInstance() {
        UserCreateFragment fragment = new UserCreateFragment();
        return fragment;
    }

    public UserCreateFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_create, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        final EditText nameEdit = getEt(view, R.id.username_edit);
        Button createBtn = getBtn(view, R.id.user_create_btn);
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEdit.getText().toString();
                if (!TextUtils.isEmpty(name)) {
                    UserRequest.create(name, new Response.Listener<User>() {
                        @Override
                        public void onResponse(User user) {
                            ToastHelper.shortMessage(getActionBarActivity(), user.getName());
                        }
                    });
                }
            }
        });
    }
}
