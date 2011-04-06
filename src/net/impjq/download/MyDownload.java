package net.impjq.download;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import net.impjq.download.DownloadManager.DownloadProgressListener;
import net.impjq.providers.downloads.Constants;
import net.impjq.providers.downloads.Downloads;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.http.AndroidHttpClient;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MyDownload extends Activity implements OnClickListener {
	private static final String TAG = MyDownload.class.getSimpleName();

	Context mContext;
	EditText mUrlInputEditText;
	Button mStartDownloadButton;
	Button mCancelButton;
	Button mPauseButton;
	Button mResumeButton;
	ProgressBar mDownloadProgressBar;
	DownloadManager mDownloadManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mContext = this;

		init();
	}

	private void init() {
		// TODO Auto-generated method stub

		mUrlInputEditText = (EditText) findViewById(R.id.url_input_edittext);
		mStartDownloadButton = (Button) findViewById(R.id.start_download_button);
		mCancelButton = (Button) findViewById(R.id.cancel_download_button);
		mPauseButton = (Button) findViewById(R.id.pause_download_button);
		mResumeButton = (Button) findViewById(R.id.resume_download_button);
		mDownloadProgressBar = (ProgressBar) findViewById(R.id.download_progress_bar);

		mStartDownloadButton.setOnClickListener(this);
		mCancelButton.setOnClickListener(this);
		mPauseButton.setOnClickListener(this);
		mResumeButton.setOnClickListener(this);

		mUrlInputEditText
				.setText("http://tools.impjq.net/Dropbox%201.0.10.exe");
		//mUrlInputEditText
		//		.setText("http://v.youku.com/v_show/id_XMjM5MDgyNDEy.html");

		mDownloadProgressListener = createDownloadProgressListener();
		mDownloadManager = new DownloadManager(mContext);
		mDownloadManager.setDownloadProgressListener(mDownloadProgressListener);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		int id = v.getId();

		switch (id) {
		case R.id.start_download_button: {
			startDownload();

			break;
		}
		
		case R.id.cancel_download_button:{
			mDownloadManager.cancelDownload();
			break;
		}
		
		case R.id.pause_download_button:{
			mDownloadManager.pauseDownload();
			break;
		}
		
		case R.id.resume_download_button:{
			mDownloadManager.resumeDownload();		
			break;		
		}

		default:
			break;
		}

	}

	DownloadProgressListener mDownloadProgressListener;

	long mDataID;

	private void startDownload() {
		// TODO Auto-generated method stub
		

		String url = mUrlInputEditText.getText().toString();

		mDownloadManager.insertDownload(url, null);
	}

	private void start() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Utils.log(TAG, "start download....");
				AndroidHttpClient client = null;
				client = AndroidHttpClient.newInstance(userAgent(), mContext);
				DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
				HttpGet request = new HttpGet("http://www.baidu.com/");
				try {
					HttpResponse response = client.execute(request);
					StatusLine statusLine = response.getStatusLine();
					Utils.log(TAG, "status code=" + statusLine.getStatusCode());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();

	}

	/**
	 * Returns the user agent provided by the initiating app, or use the default
	 * one
	 */
	private String userAgent() {
		String userAgent = null;
		if (userAgent != null) {
		}
		if (userAgent == null) {
			userAgent = "Mozilla/5.0 (X11; U; Linux i686; zh-CN; rv:1.9.2.13) Gecko/20101203 Firefox/3.6.13";
		}
		return userAgent;
	}

	/**
	 * Create the DownloadProgressListener,You can do everythin you want in the
	 * override methord: {@link DownloadProgressListener#onChange}
	 * 
	 * @return downloadProgressListener
	 */
	public net.impjq.download.DownloadManager.DownloadProgressListener createDownloadProgressListener() {
		DownloadProgressListener downloadProgressListener = null;

		downloadProgressListener = new DownloadProgressListener() {

			private int mIdColumnId;

			private int mTitleColumnId;

			private int mFilenameColumnId;

			private int mDescColumnId;

			private int mStatusColumnId;

			private int mTotalBytesColumnId;

			private int mCurrentBytesColumnId;

			private int mMimetypeColumnId;

			private int mDateColumnId;

			public void onChange(Cursor c) {
				// TODO Auto-generated method stub

				mIdColumnId = c.getColumnIndexOrThrow(Downloads._ID);
				mFilenameColumnId = c.getColumnIndexOrThrow(Downloads._DATA);
				mTitleColumnId = c
						.getColumnIndexOrThrow(Downloads.COLUMN_TITLE);
				mDescColumnId = c
						.getColumnIndexOrThrow(Downloads.COLUMN_DESCRIPTION);
				mStatusColumnId = c
						.getColumnIndexOrThrow(Downloads.COLUMN_STATUS);
				mTotalBytesColumnId = c
						.getColumnIndexOrThrow(Downloads.COLUMN_TOTAL_BYTES);
				mCurrentBytesColumnId = c
						.getColumnIndexOrThrow(Downloads.COLUMN_CURRENT_BYTES);
				mMimetypeColumnId = c
						.getColumnIndexOrThrow(Downloads.COLUMN_MIME_TYPE);
				mDateColumnId = c
						.getColumnIndexOrThrow(Downloads.COLUMN_LAST_MODIFICATION);

				if (c.moveToFirst()) {
					do {
						String filename = c.getString(mFilenameColumnId);
						String title = c.getString(mTitleColumnId);
						String desc = c.getString(mDescColumnId);
						String status = c.getString(mStatusColumnId);
						Long currentBytes = c.getLong(mCurrentBytesColumnId);
						Long totalBytes = c.getLong(mTotalBytesColumnId);
						String mimeType = c.getString(mMimetypeColumnId);
						long time = c.getLong(mDateColumnId);
						Date d = new Date(time);
						DateFormat df = DateFormat
								.getDateInstance(DateFormat.SHORT);
						String date = df.format(d);

						// Get the Downloads._ID,use it when cancel the download
						// process ,and delete the download uri.
						mDataID = c.getLong(mIdColumnId);

						if (0 >= totalBytes) {
							mDownloadProgressBar.setProgress(0);
						} else {
							int percent = (int) (currentBytes * 100 / totalBytes);
							mDownloadProgressBar.setProgress(percent);

							if (100 == percent) {
							}
						}

						Utils.log(TAG, "Filename=" + filename + ",title="
								+ title + "\n desc=" + desc + ", status="
								+ status + ",totalBytes=" + totalBytes
								+ "\n currentBytes=" + currentBytes
								+ ",mimeType=" + mimeType + "\n date=" + date
								+ ",mIdColumnId=" + mIdColumnId);
					} while (c.moveToNext());
				}
			}
		};

		return downloadProgressListener;
	}

}
