package com.mynta.rz;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.Gson;

import org.json.JSONObject;

/**
 * Created by rizvan .
 */

public class NetworkThread {
    public NetworkThread(){

    }

    public interface NetResponse{

        void onResponse(ImageDataModel response);

        void onError(ANError anError);
    }

    public void cancelAllNetRequest(){
        AndroidNetworking.cancelAll();
    }

    public void cancelNetReqByTag(String tag){
        AndroidNetworking.cancel(tag);
    }


    public void getImageList(final String searchTag, final NetResponse listener){
        //https://api.flickr.com/services/feeds/photos_public.gne?tags=myntra&format=json
        String httpPath = "https://api.flickr.com/services/feeds/photos_public.gne";
        AndroidNetworking.get(httpPath)
                .addQueryParameter("tags", searchTag)
                .addQueryParameter("format", "json")
                .addQueryParameter("nojsoncallback","1")
                .setTag("imageListClient")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        ImageDataModel imageDataModel = gson.fromJson(response.toString(),ImageDataModel.class);
                        listener.onResponse(imageDataModel);
                        Log.d("NETWORK_THREAD","RESPONSE : "+response.toString());
                    }

                    @Override
                    public void onError(ANError anError) {
                        //Error occured
                        listener.onError(anError);
                    }
                });
    }
}
