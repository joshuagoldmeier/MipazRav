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

public class links extends Fragment {

    GridView linksGridView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.links, container, false);
        return view;
    }

    private final AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {


            processClickOnSpace(position);

        }
    };

    private void processClickOnSpace(int position) {
        Intent intent = new Intent(linksGridView.getContext(), DummyListActivity2.class);
        intent.putExtra("POSITION", position);
        startActivity(intent);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        linksGridView = (GridView) getView().findViewById(R.id.linkGridView);

        if (linksGridView != null)
            linksGridView.setAdapter(new linksImageAdapter(getActivity(), getActivity().getApplicationContext()));//clean this up by getting activty once
        else
            Log.d("null", "shiurimGridView is null");

        linksGridView.setOnItemClickListener(listener);
    }


}