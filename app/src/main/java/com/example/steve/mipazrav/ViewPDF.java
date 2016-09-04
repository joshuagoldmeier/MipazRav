package com.example.steve.mipazrav;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.DownloadListener;
import android.webkit.WebView;

public class ViewPDF extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_iew_pdf);
    final String filename=getIntent().getStringExtra("filename");

    WebView webView = (WebView) findViewById(R.id.webview);
    webView.loadUrl("http://www.mipazrav.com/audio/"+filename.replace(" ","%20"));
    webView.setDownloadListener(new DownloadListener() {
      public void onDownloadStart(String url, String userAgent,
                                  String contentDisposition, String mimetype,
                                  long contentLength) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
      }
    });

  }
}