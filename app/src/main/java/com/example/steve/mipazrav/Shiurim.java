package com.example.steve.mipazrav;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

public class Shiurim extends Fragment {
    GridView shiurimGridView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shiurim__fragment, container, false);
        return view;
    }

    private final AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {


            processClickOnSpace(position);

        }
    };

    private void processClickOnSpace(int position) {
        //TODO: launch list of shiurim Activity
        Intent intent = new Intent(shiurimGridView.getContext(), DummyListActivity.class);
//        intent.putExtra("POSITION", position);
        startActivity(intent);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //shiurimGridView = (GridView) getView().findViewById(R.id.shiurimGridView);

//        if (shiurimGridView != null)
//            shiurimGridView.setAdapter(new ImageAdapter(getActivity(),getContext()));
//        else
//            Log.d("null", "shiurimGridView is null");
//
//        shiurimGridView.setOnItemClickListener(listener);
    }
}
