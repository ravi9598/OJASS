package in.nitjsr.ojass19.Utils;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;

public class FirebaseInstanceIdService extends com.google.firebase.iid.FirebaseInstanceIdService {

    private static final String TAG="FirebaseInsIdService";

    @Override
    public void onTokenRefresh() {
        String RefreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG,"New Token : "+RefreshedToken);
    }
}
