package com.mynta.rz;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidnetworking.error.ANError;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Rizvan
 */
public class MainActivity extends AppCompatActivity {

    @Bind(R.id.newgame_btn)
    Button newGameBtn;
    @Bind(R.id.timer_game_btn)
    Button timerGameBtn;
    @Bind(R.id.image_main)
    ImageView randomPicView;
    @Bind(R.id.status_txt_view)
    TextView statusTextView;
    private ImageItem curImageItem = new ImageItem();
    private ImageListAdapter imageListAdapter = null;
    private List list = new ArrayList();
    private Map<Integer, ImageItem> map = new HashMap<Integer, ImageItem>();
    private RecyclerView recyclerView;
    private int min_index = 0;
    private int max_index = 9;
    ImageListAdapter.ImageSelection gridListener = new ImageListAdapter.ImageSelection() {
        @Override
        public Boolean imageSelected(int position, String imagePath) {
            Log.d("DATA_HOLD", " POSITION : " + position + " IMAGEPATH : " + imagePath);
            if (curImageItem.getImagePath().equals(imagePath)) {
                statusTextView.setText("MATCH, NEXT!");
                return setNewImage(position);
            } else {
                statusTextView.setText("NOPE, TRY AGAIN!");
            }
            return false;
        }
    };

    @OnClick(R.id.newgame_btn)
    public void startGameAgain(){
        statusTextView.setText("Let's play");
        map.clear();
        startImageDownload();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        recyclerView =  (RecyclerView) findViewById(R.id.recycler_view);

        startImageDownload();
    }

    private void showGridItems(ImageDataModel imageDataModel){

        int count = imageDataModel.getItems().size() > max_index ? max_index : imageDataModel.getItems().size();
        for (int i = 0; i < count ; i++) {
            ImageDataModel.Item dataItem = imageDataModel.getItems().get(i);
            list.add(dataItem.getMedia().getM());

            ImageItem item = new ImageItem();
            item.setImagePath(dataItem.getMedia().getM());
            item.setPosition(i);
            item.setVisited(true);
            map.put(i,item);

            if (i == 0) {
                curImageItem = item;
                Picasso.with(this).load(item.getImagePath()).fit().centerCrop().into(randomPicView);
            }
        }


        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(gridLayoutManager);
        imageListAdapter = new ImageListAdapter(this,map,gridListener);
        recyclerView.setAdapter(imageListAdapter);
        timerRun();
    }

    private void timerRun(){
        new CountDownTimer(15000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timerGameBtn.setText(0+":"+millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                timerGameBtn.setText("0:0");
                updateFlipViews();

            }
        }.start();
    }

    private void updateFlipViews(){
        Map<Integer,ImageItem> tempMap ;
        for (int i = 0; i < map.size(); i++) {
            Log.d("MAP","UDPATED : "+i);
            map.get(i).setVisited(false);
        }
        tempMap = map;
        imageListAdapter = new ImageListAdapter(MainActivity.this,tempMap,gridListener);
        recyclerView.setAdapter(imageListAdapter);
        recyclerView.invalidate();
        tempMap = null;
    }

    /**
     * Get all the search tag related data in json format
     */
    private void startImageDownload(){

        this.list.clear();
        timerGameBtn.setText("0:)0");
        NetworkThread networkThread = new NetworkThread();
        networkThread.getImageList("cars", new NetworkThread.NetResponse() {
            @Override
            public void onResponse(ImageDataModel response) {
                    Log.d("NETWORK_THREAD","RESPONSE RECIEVED "+response.getItems().get(0).getMedia().getM());
                showGridItems(response);
            }

            @Override
            public void onError(ANError anError) {

            }
        });
    }

    private Boolean setNewImage(int key){
        int i = 0;
        while (i <= max_index) {

            int nextNumber = new Random().nextInt((max_index - min_index) + 1)+ min_index;
            Log.d("IMAGE_TURN",""+nextNumber);
            if (map.containsKey(nextNumber) && !map.get(nextNumber).getVisited() && nextNumber != key) {
                Log.d("IMAGE_TURN","NOT VISITED ");
                curImageItem = map.get(key);
                curImageItem.setVisited(true);
                map.put(key,curImageItem);
                Picasso.with(this).load(map.get(nextNumber).getImagePath()).fit().centerCrop().into(randomPicView);
                curImageItem = map.get(nextNumber);
                break;
            }
            if (nextNumber > max_index) continue;

            i++;
            Log.d("IMAGE_TURN","NEXT INDEX "+i);
        }
        if (i >= max_index) statusTextView.setText("WINNER, START NEW!");

        return true;
    }
}
