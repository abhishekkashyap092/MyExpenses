package org.totschnig.myexpenses.service;

import android.app.IntentService;
import android.content.ContentResolver;
import android.content.Intent;
import android.os.Bundle;

import org.totschnig.myexpenses.provider.DatabaseConstants;
import org.totschnig.myexpenses.provider.TransactionProvider;
import org.totschnig.myexpenses.sync.GenericAccountService;
import org.totschnig.myexpenses.sync.SyncAdapter;

public class SyncNotificationDismissHandler extends IntentService {
  public SyncNotificationDismissHandler() {
    super("SyncNotificationDismissHandler");
  }

  @Override
  public void onCreate() {
    super.onCreate();
  }

  @Override
  protected void onHandleIntent(Intent intent) {
    Bundle bundle = new Bundle();
    bundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
    bundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
    bundle.putBoolean(SyncAdapter.KEY_NOTIFICATION_CANCELLED, true);
    ContentResolver.requestSync(GenericAccountService.GetAccount(intent.getStringExtra(DatabaseConstants.KEY_SYNC_ACCOUNT_NAME)),
        TransactionProvider.AUTHORITY, bundle);
  }
}
