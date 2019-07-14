package com.loogs.alc_4_0.phase1.challenge.android;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class AboutALCActivity extends AppCompatActivity {

    private WebView aboutALC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_alc);
        Toolbar toolbar = findViewById(R.id.toolbar);

        // Source: https://medium.com/android-grid/how-to-implement-back-up-button-on-toolbar-android-studio-c272bbc0f1b0
        // First we need to initialize the toolbar to support action bar-
        setSupportActionBar(toolbar);

        // Now we are going to add a call for up button.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setBackgroundColor(Color.parseColor("#3359DE")); //#1433c4
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Get webview
        aboutALC = (WebView) findViewById(R.id.webview_about_alc_page);

        startWebView("https://andela.com/alc/");
    }

    private void startWebView(String url) {

        //Create new webview Client to show progress dialog
        //When opening a url or click on link

        aboutALC.setWebViewClient(new WebViewClient() {
            ProgressDialog progressDialog;

            //If you will not use this method url links are open in new browser not in webview
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            //Show loader on url load
            public void onLoadResource (WebView view, String url) {
                if (progressDialog == null) {
                    // in standard case YourActivity.this
                    progressDialog = new ProgressDialog(AboutALCActivity.this);
                    progressDialog.setMessage("Loading...");
                    progressDialog.show();
                }
            }

            public void onPageFinished(WebView view, String url) {
                try{
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                        progressDialog = null;
                    }
                }catch(Exception exception){
                    exception.printStackTrace();
                }
            }

        });

        // Javascript enabled on webview
        aboutALC.getSettings().setJavaScriptEnabled(true);
        MyBrowserClient webViewClient = new MyBrowserClient(AboutALCActivity.this);
        aboutALC.setWebViewClient(webViewClient);

        // Other webview options
        //aboutALC.getSettings().setLoadWithOverviewMode(true);
        //aboutALC.getSettings().setUseWideViewPort(true);
        //aboutALC.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        //aboutALC.setScrollbarFadingEnabled(false);
        //aboutALC.getSettings().setBuiltInZoomControls(true);

        //Load url in webview
        aboutALC.loadUrl(url);
    }

    // Open previous opened link from history on webview when back button pressed

    @Override
    // Detect when the back button is pressed
    public void onBackPressed() {
        if(aboutALC.canGoBack()) {
            aboutALC.goBack();
        } else {
            // Let the system handle the back button
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && this.aboutALC.canGoBack()) {
            this.aboutALC.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
