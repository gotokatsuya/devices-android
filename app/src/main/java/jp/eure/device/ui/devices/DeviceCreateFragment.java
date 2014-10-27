package jp.eure.device.ui.devices;

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
import jp.eure.device.model.Device;
import jp.eure.device.model.User;
import jp.eure.device.request.DeviceRequest;
import jp.eure.device.request.UserRequest;
import jp.eure.device.ui.BaseFragment;

public class DeviceCreateFragment extends BaseFragment {

    public static DeviceCreateFragment newInstance() {
        DeviceCreateFragment fragment = new DeviceCreateFragment();
        return fragment;
    }

    public DeviceCreateFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_device_create, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        final EditText nameEdit = getEt(view, R.id.device_name_edit);
        Button createBtn = getBtn(view, R.id.device_create_btn);
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEdit.getText().toString();
                if (!TextUtils.isEmpty(name)) {
                    DeviceRequest.create(name, null, null, null, null, null, null, 0L, null, new Response.Listener<Device>() {
                        @Override
                        public void onResponse(Device device) {
                            ToastHelper.shortMessage(getActionBarActivity(), device.getName());
                        }
                    });
                }
            }
        });
    }
}
