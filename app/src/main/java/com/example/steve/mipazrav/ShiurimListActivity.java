package com.example.steve.mipazrav;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.ComponentName;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Path;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.net.URLConnection;

public class ShiurimListActivity extends ListActivity
        implements LoaderManager.LoaderCallbacks<Cursor> {
    private SimpleCursorAdapter mAdapter;

    ShiurimListActivity mShiurimListActivity;
    // These are the Contacts rows that we will retrieve
    //TODO: hook this into DB and get shiurim (also make list collapseable)
    static final String[] PROJECTION = new String[]{ContactsContract.Data._ID,
            ContactsContract.Data.DISPLAY_NAME};

    // This is the select criteria
    static final String SELECTION = "((" +
            ContactsContract.Data.DISPLAY_NAME + " NOTNULL) AND (" +
            ContactsContract.Data.DISPLAY_NAME + " != '' ))";
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_shiurim_list);
//
//
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create a progress bar to display while the list loads
        ProgressBar progressBar = new ProgressBar(this);
        progressBar.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        progressBar.setIndeterminate(true);
        getListView().setEmptyView(progressBar);

        // Must add the progress bar to the root of the layout
        ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
        root.addView(progressBar);

        // For the cursor adapter, specify which columns go into which views
        String[] fromColumns = {ContactsContract.Data.DISPLAY_NAME};
        int[] toViews = {android.R.id.text1}; // The TextView in simple_list_item_1

        // Create an empty adapter we will use to display the loaded data.
        // We pass null for the cursor, then update it in onLoadFinished()
        //TODO change this back to plug into db for production
        mAdapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1, null,
                fromColumns, toViews, 0);
        setListAdapter(mAdapter);

        // Prepare the loader.  Either re-connect with an existing one,
        // or start a new one.
        getLoaderManager().initLoader(0, null, this);
    }

    // Called when a new Loader needs to be created
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        // Now create and return a CursorLoader that will take care of
        // creating a Cursor for the data being displayed.
        return new CursorLoader(this, ContactsContract.Data.CONTENT_URI,
                PROJECTION, SELECTION, null, null);
    }

    // Called when a previously created loader has finished loading
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        // Swap the new cursor in.  (The framework will take care of closing the
        // old cursor once we return.)
        mAdapter.swapCursor(data);
    }

    // Called when a previously created loader is reset, making the data unavailable
    public void onLoaderReset(Loader<Cursor> loader) {
        // This is called when the last Cursor provided to onLoadFinished()
        // above is about to be closed.  We need to make sure we are no
        // longer using it.
        mAdapter.swapCursor(null);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        //TODO: launch media associated with the clicked item (by sending query to DB)

//
//        Intent intent = new Intent(this, MediaPlayback.class);
//        startActivity(intent);


//        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.chinese_music);
//        mediaPlayer.start();

//        Intent intent = new Intent();
//        intent.setAction(android.content.Intent.CATEGORY_APP_MUSIC);
//        intent.setDataAndType(Uri.parse("android.resource://com.example.steve.mipazrav/raw/chinese_music.mp3"),"audio/*");
//        startActivity(intent);
//        Intent intent = new Intent();
//        ComponentName comp = new ComponentName("com.android.music", "com.android.music.MediaPlaybackActivity");
//        intent.setComponent(comp);
//        intent.setAction(android.content.Intent.ACTION_VIEW);
//     //  viewIntent.setData(Uri.parse("android.resource://com.example.steve.mipazrav;/raw/chinese_music.mp3"));
//        intent.setDataAndType(Uri.parse("android.resource://com.example.steve.mipazrav//raw//chinese_music.mp3"),"mp3");
//        startActivity(Intent.createChooser(intent, null));

//        String videoPath = Environment.getExternalStorageDirectory().getPath() + "\\MipazRav\\app\\src\\main\\res\\raw\\chinese_music.mp3";
//        File videoFile = new File(
//                videoPath);
//        if (videoFile.exists()) {
//            Uri fileUri = Uri.fromFile(videoFile);
//            Intent intent = new Intent();
//            intent.setAction(Intent.ACTION_VIEW);
//            intent.setDataAndType(fileUri,
//                    URLConnection.guessContentTypeFromName(fileUri.toString()));
//            startActivity(intent);
//        } else {
//            Toast.makeText(this, Environment.getExternalStorageDirectory()+" file does not exist",
//                    Toast.LENGTH_LONG).show();

//       String filePath = Environment.getExternalStorageDirectory() + "\\MipazRav\\app\\src\\main\\res\\raw";
//
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_VIEW);
//        intent.setDataAndType(Uri.fromFile(new File(filePath)),"audio/*");
//        startActivity(intent);
//        Intent intent = new Intent(this, MediaPlayback.class);
//        startActivity(intent);
    }
}



