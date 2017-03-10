package arun.com.chromer.customtabs.callbacks;

import android.app.IntentService;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import arun.com.chromer.R;

// TODO Rewrite this using broadcast receiver
public class ClipboardService extends IntentService {
    public ClipboardService() {
        super("ClipboardService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        final String urlToCopy = intent.getDataString();
        if (urlToCopy != null) {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText(getPackageName(), urlToCopy);
            clipboard.setPrimaryClip(clip);
            showToast(getString(R.string.copied) + " " + urlToCopy);
        } else {
            showToast(getString(R.string.unxp_err));
        }
    }

    private void showToast(final String msgToShow) {
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> Toast.makeText(ClipboardService.this, msgToShow, Toast.LENGTH_SHORT).show());
    }
}
