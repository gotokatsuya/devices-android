package jp.eure.device.request.volley;


import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;

public class ExRetryPolicy extends DefaultRetryPolicy {
    private static final int CONNECTION_TIMEOUT = 20*1000;
    private static final int CONNECTION_RETRY_COUNT = 1;
    private static final float CONNECTION_BACKOFF_MULT = 1.0f;
    protected long mInterval = 3000;

    private Request<?> mRequest;

    public ExRetryPolicy(Request<?> request) {
        super(CONNECTION_TIMEOUT, CONNECTION_RETRY_COUNT, CONNECTION_BACKOFF_MULT);
        this.mRequest = request;
    }

    @Override
    public void retry(VolleyError error) throws VolleyError {
        NetworkResponse response = error.networkResponse;
        if (response != null && response.statusCode >= 500 && response.statusCode < 600) {
            // サーバーエラー時はリトライしない
            throw error;
        }
        if(mRequest != null && mRequest.isCanceled()) {
            // キャンセル済みならリトライしない
            throw error;
        }

        if (mInterval > 0) {
            try {
                Thread.sleep(mInterval);
            } catch (InterruptedException e) {
            }
        }
        VolleyLog.d("Network Retry count : %d", getCurrentRetryCount());
        super.retry(error);
    }

}