package net.impjq.download;

import net.impjq.providers.downloads.Downloads;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;


public class DownloadManager {
	private static final String TAG=DownloadManager.class.getSimpleName();
	private Context mContext;	
		
    /**
     * The {@link DownloadProgressListener mDownloadProgressListener}
     */
    private DownloadProgressListener mDownloadProgressListener;
    
    /**
     * Choose Which columns should query about the downloaded Media Ojbect.
     */
    private static final String[] sProjection = new String[] {
            "_id", Downloads.COLUMN_TITLE, Downloads.COLUMN_STATUS, Downloads.COLUMN_TOTAL_BYTES,
            Downloads.COLUMN_CURRENT_BYTES, Downloads._DATA, Downloads.COLUMN_DESCRIPTION,
            Downloads.COLUMN_MIME_TYPE, Downloads.COLUMN_LAST_MODIFICATION,
            Downloads.COLUMN_VISIBILITY
    };
    
    public DownloadManager(Context context){
		// TODO Auto-generated constructor stub
    	mContext=context;
	}
    
   public void setDownloadProgressListener(DownloadProgressListener listener){
    	mDownloadProgressListener=listener;
    }

    /**
     * Use the Android Defaul Download Provider to download the MediaObject.
     * 
     * @param url
     */
    public void insertDownload(String url,String hintName) {
        Utils.log(TAG, "Start download,url=" + url);      

        ContentValues values = new ContentValues();
        values.put(Downloads.COLUMN_URI, url);       
        values.put(Downloads.COLUMN_NOTIFICATION_PACKAGE, mContext.getPackageName());
        values.put(Downloads.COLUMN_NOTIFICATION_PACKAGE, "com.bookfm");
        values.put(Downloads.COLUMN_NOTIFICATION_CLASS, "com.bookfm.download.DownloadActivity");
        values.put(Downloads.COLUMN_VISIBILITY, Downloads.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);       
        values.put(Downloads.COLUMN_FILE_NAME_HINT, hintName);      

        Uri uri = mContext.getContentResolver().insert(Downloads.CONTENT_URI, values);

        Utils.log(TAG, "Insert to Download Provider,uri=" + uri);

        setDownloadsChangeObserver(uri);
    }

    /**
     * Set the DownloadsChangeObserver
     * 
     * @param uri The inserted uri to watch.
     */
    public void setDownloadsChangeObserver(Uri uri) {
        Utils.log(TAG, "setContentObserver");
        mDownloadsObserver = new DownloadsChangeObserver(Downloads.CONTENT_URI, uri);
        mContext.getContentResolver().registerContentObserver(Downloads.CONTENT_URI, true,
                mDownloadsObserver);
    }

    /**
     * The ContentObserver
     */
    private DownloadsChangeObserver mDownloadsObserver;

    /**
     * The DownloadsChangeObserver.When the Media Object downloaded
     * changed,should notify the Activity to do something,such as update the
     * download progressbar.
     * 
     * @author Percy.Peng
     * @see DownloadProgressListener#onChange
     */
    private class DownloadsChangeObserver extends ContentObserver {
        private Uri uri;

        public DownloadsChangeObserver(Uri uri, Uri insertUri) {
            super(new Handler());
            this.uri = insertUri;

            Utils.log(TAG, "DownloadsChangeObserver,uri=" + uri + ",insertUri=" + insertUri);
        }

        @Override
        public void onChange(boolean selfChange) {
            Utils.log(TAG, "DownloadsChangeObserver,onChange");
            Cursor c = ((Activity)mContext).managedQuery(uri, sProjection, null, null, null);

            if (null == c) {
                return;
            }

            // Call the listener,then can get the details and show in the
            // Activity which call it.
            mDownloadProgressListener.onChange(c);
        }
    }
    
    /**
     * Used to pass the Download Progress do the Activity which call it through
     * the {@link DownloadProgressListener#onChange(Cursor)}
     * 
     * @author Percy.Peng
     */
    public interface DownloadProgressListener {
        /**
         * Almost the same to {@link DownloadsChangeObserver#onChange} Call it
         * when the data change.
         * 
         * @param c
         * @see DownloadsChangeObserver#onChange(boolean)
         */
        public void onChange(Cursor c);
    }
}
