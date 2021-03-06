package com.example.steve.mipazrav;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/*
TODO: change menu items add functionality to the menu items and the gridView buttons
 */
public class MainActivity extends AppCompatActivity {


  @Override
  public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
  }

  @Override
  protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);

  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(myToolbar);

    ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);

    viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), getApplicationContext()));

    TabLayout tabLayout = (TabLayout)
            findViewById(R.id.slidingTabs);

    tabLayout.setupWithViewPager(viewPager);


  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {

    getMenuInflater().inflate(R.menu.menu_main, menu);
    return super.onCreateOptionsMenu(menu);
  }

  public void aboutClickHandler(MenuItem item) {


    showTTTDialog("ABOUT", getString(R.string.Designers));

  }


  private void showTTTDialog(String title, String message) {
    // Create listener for use with dialog window (could also be created anonymously below...
    DialogInterface.OnClickListener dialogOnClickListener =
            createTTTOnClickListener();

    // Create dialog window
    AlertDialog TTTAlertDialog = initDialog(title, message, dialogOnClickListener);

    // Show the dialog window
    TTTAlertDialog.show();

  }

  private AlertDialog initDialog(String title, String message,
                                 DialogInterface.OnClickListener dialogOnClickListener) {
    AlertDialog TTTAlertDialog;
    TTTAlertDialog = new AlertDialog.Builder(MainActivity.this).create();
    TTTAlertDialog.setTitle(title);
    TTTAlertDialog.setIcon(R.mipmap.ic_launcher);
    TTTAlertDialog.setMessage(message);
    TTTAlertDialog.setButton(DialogInterface.BUTTON_NEUTRAL,
            "OK", dialogOnClickListener);
    return TTTAlertDialog;
  }

  private DialogInterface.OnClickListener createTTTOnClickListener() {
    return new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        // nothing to do
      }
    };
  }


  public void sendEmail(String recipient, String body) {

    BackgroundMail bm = new BackgroundMail(this);
    bm.setGmailUserName("oodahl94@gmail.com");
    bm.setGmailPassword("***********");
    bm.setMailTo(recipient);
    bm.setFormSubject("Mipaz Rav App Question");
    bm.setFormBody(body);
    int i = 0;

    bm.send();

  }


  public void sendQuestion(View view) {
//TODO DATA VALIDATION for fields (and parsing) split comment up into some amount of words and insert \n

    String name,
            telephone,
            email,
            comment;

    EditText editTextName = (EditText) findViewById(R.id.fullName);
    name = editTextName.getText().toString();
    EditText editTextTelephone = (EditText) findViewById(R.id.telephone);
    telephone = editTextTelephone.getText().toString();
    EditText editTextEmail = (EditText) findViewById(R.id.email);
    email = editTextEmail.getText().toString();
    EditText editTextComment = (EditText) findViewById(R.id.comment);
    comment = editTextComment.getText().toString();


    String messageForm = "Name: " + name + "\nPhone: " + telephone + "\nEmail Address: " + email
            + "\nComment:" + comment;

    sendEmail("joshuaegoldmeier@gmail.com", messageForm);
  }


  public void button_listener(View view) {
    String downloadArg = "";

    switch (view.getId()) {

      case R.id.Parsha:
        downloadArg = "categoryID=1";

        break;

      case R.id.Navi:
        downloadArg = "categoryID=2";
        break;

      case R.id.Megillah:
        downloadArg = "categoryID=3";

        break;

      case R.id.Tehillim:
        downloadArg = "categoryID=17";
        break;

      case R.id.Holiday:
        downloadArg = "categoryID=4";

        break;

      case R.id.Prayer:
        downloadArg = "categoryID=5";

        break;

      case R.id.Literature:
        downloadArg = "categoryID=6";
        new DownloadFromWebservice(this, DownloadFromWebservice.QueryType.PDF, downloadArg);
        return;

      case R.id.Nutshell:
        downloadArg = "categoryID=7";

        break;
      case R.id.Philosophy:
        downloadArg = "categoryID=8";

        break;
      case R.id.Halacha:

        downloadArg = "categoryID=9";
        break;
      case R.id.Derech_Etz_Chaim:

        downloadArg = "categoryID=10";
        break;
      case R.id.People_in_Tanach:

        downloadArg = "categoryID=15";
        break;
      case R.id.Yahrteit:
        downloadArg="categoryId=18";
        break;
      case R.id.Pirkei_Avot:
        downloadArg="categoryId=20";
        break;
      case R.id.Oral_Torah:
        downloadArg="categoryId=21";
        break;
      case R.id.Ramchal:
        downloadArg="categoryId=22";
        break;
      case R.id.Kinyan_Torah:
        downloadArg="categoryId=19";
    }

    new DownloadFromWebservice(this, DownloadFromWebservice.QueryType.SHIUR, downloadArg);

  }
}