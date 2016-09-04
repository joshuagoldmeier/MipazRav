package com.example.steve.mipazrav;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ShiurimList extends AppCompatActivity {

  private ArrayList resultsListItems;

  ListView listView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_shiurim_list);



    resultsListItems = Result.r;
    listView = (ListView) findViewById(R.id.list);
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      Boolean playAudio = getIntent().getBooleanExtra("playAudio", false);
      Boolean pdfRead = getIntent().getBooleanExtra("loadPDF", false);

      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        shiurObject r = (shiurObject) resultsListItems.get(i);
        if (playAudio) {
          Uri myUri = Uri.parse("http://www.mipazrav.com/audio/" + r.getDescription().replace(" ", "%20"));//server expects whitespaces as %20

          Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
          intent.setDataAndType(myUri, "audio/*");
          startActivity(intent);
        } else if (pdfRead) {
          Intent j = new Intent(ShiurimList.this,ViewPDF.class);
          j.putExtra("filename",r.getDescription());
          startActivity(j);
        } else {
          new DownloadFromWebservice(ShiurimList.this, DownloadFromWebservice.QueryType.SHIUR, "subcategoryid=" + r.getRecID());

        }


      }

    });
    CustomerCriteriaAdapter mAdapter = new CustomerCriteriaAdapter(this, R.layout.customer_criteria_item, resultsListItems);
    listView.setAdapter(mAdapter);

    assert listView != null;


  }


  class CustomerCriteriaAdapter extends ArrayAdapter<shiurObject> {

    public CustomerCriteriaAdapter(Context context, int textViewResourceId, List<shiurObject> objects) {
      super(context, textViewResourceId, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      return getViewOptimize(position, convertView, parent);
    }

    public View getViewOptimize(int position, View convertView, ViewGroup parent) {
      shiurObject result = getItem(position);

      View row = convertView;
      ViewHolder viewHolder = null;
      if (row == null) {
        LayoutInflater inflater = (LayoutInflater)
                ShiurimList.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(R.layout.customer_criteria_item, parent, false);
        viewHolder = new ViewHolder();
        viewHolder.name = (TextView) row.findViewById(R.id.itemName);

        row.setTag(viewHolder);

      } else {
        viewHolder = (ViewHolder) row.getTag();
      }
      setTexviewsText(viewHolder, result);
      return row;
    }

    private void setTexviewsText(ViewHolder viewHolder, shiurObject result) {
      viewHolder.name.setText(result.getName());
      Typeface TrajanProRegular = Typeface.createFromAsset(ShiurimList.this.getAssets(), "fonts/TrajanPro-Regular.ttf");
      viewHolder.name.setTypeface(TrajanProRegular);
    }

    private class ViewHolder {


      public TextView name;


    }

    private boolean notNull(String r) {
      if (r != null) {
        return true;
      }
      return false;
    }
  }

}