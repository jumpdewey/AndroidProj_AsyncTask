package com.weidu.downloadfromweb_asynctask;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private static final String TAG = "AsyncTaskFragment";
    String myUrl =
            "http://www.androidcentral.com/sites/androidcentral.com/files/styles/w680h550/public/postimages/9274/android_kitkat.jpg";
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        Button button = (Button)getActivity().findViewById(R.id.at_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"onClick: "+myUrl);
                new DownloadTask().execute(myUrl);
            }
        });
    }

    private Drawable loadImageFromWeb(String url){
        Drawable img = null;
        try{
            InputStream in = (InputStream) new URL(url).getContent();
            if(in!=null){
                img = Drawable.createFromStream(in,"src Name");
            }
        }catch(Exception e){
            System.out.println(e.getStackTrace());
        }
        return img;
    }

    private class DownloadTask extends AsyncTask<String,Integer,Drawable>{
        @Override
        protected void onPreExecute(){
        }

        @Override
        protected Drawable doInBackground(String... params){
            Log.d(TAG, "PlaceholderFragment : doInBackground entry "
                    + params[0]);
            Drawable myDrawable = loadImageFromWeb(params[0]);
            return myDrawable;
        }

        @Override
        protected void onProgressUpdate(Integer...progress){
        }

        @Override
        protected void onPostExecute(Drawable result){
            Log.d(TAG, "PlaceholderFragment : onPostExecute entry");
            ImageView imageView = (ImageView)getActivity().findViewById(R.id.at_image);
            if(result!=null){
                imageView.setImageDrawable(result);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            } else {
                Log.d(TAG, "PlaceholderFragment : result is null");
            }
        }
    }
}
