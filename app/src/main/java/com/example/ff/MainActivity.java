package com.example.ff;



import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.text.SpannableStringBuilder;
import android.webkit.WebViewClient;
import android.os.*;

//public class MainActivity extends AppCompatActivity {
public class MainActivity extends Activity implements OnClickListener{
//public class Test02_01 extends Activity implements OnClickListener{

 //private final int FP = ViewGroup.LayoutParams.FILL_PARENT;
 //private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;

 private EditText textUrl;
 private Button buttonGo;
 private WebView webview;

 @Override protected void onCreate(Bundle icicle) {
  super.onCreate(icicle);

  setContentView(R.layout.activity_main);

  //WebView webview = (WebView) findViewById(R.id.webView1);
  webview = (WebView) findViewById(R.id.webView);

  webview.clearCache(true);
  webview.clearFormData();
  webview.clearHistory();


  webview.getSettings().setJavaScriptEnabled(true);
  webview.getSettings().setBuiltInZoomControls(true);

  String pdfUrl = "https://www.data.jma.go.jp/fcd/yoho/data/jishin/kaisetsu_tanki_latest.pdf";
  String url = "http://docs.google.com/gview?embedded=true&url=" + pdfUrl;

  //webview.invalidate();
  webview.loadUrl(url);

/*
  private static class MyWebViewClient extends WebViewClient {
   private boolean receivederror;
   private boolean timeout;
   private boolean succeed = false;
   @Override
   public void onPageStarted(final WebView view, String url, Bitmap favicon) {
    super.onPageStarted(view, url, favicon);
    timeout = false;
    receivederror = false
    new Handler(Looper.getMainLooper()).postDelayed(new Runnable(){
     @Override
     public void run(){
      if(!succeed){
       timeout = true;
       view.reload();
      }
     }
    },10000); //ã¿ã¤ã ã¢ã¦ã10ç§
    // èª­ã¿è¾¼ã¿éå§
   }
   @Override
   public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
    super.onReceivedError(view, request, error);
    Log.d("WebView", "Webview Error");
    receivederror = true;
   }
   @Override
   public void onPageFinished(WebView view, String url) {
    super.onPageFinished(view, url);
    if(timeout || receivederror){
     Log.d("WebView","Timeout Error");
    }
    else{
     succeed = true;
     Log.d("WebView","succeeded");
    }
    // èª­ã¿è¾¼ã¿çµäº
   }
  }
*/

  webview.setWebViewClient(new WebViewClient() {
    boolean checkOnPageStartedCalled = false;
    private boolean receivederror;
   private boolean timeout = false;

   private boolean succeed = false;
   @Override
   public void onPageStarted(final WebView view, String url, Bitmap favicon) {
    super.onPageStarted(view, url, favicon);
    //setProgressBarIndeterminateVisibility(true);
    timeout = false;
    receivederror = false;
    new Handler(Looper.getMainLooper()).postDelayed(new Runnable(){
     @Override
     public void run(){
      Log.d("WebViewrun","run");
      if(!succeed){
       timeout = true;
       view.reload();
      }
     }
    },10000); //ã¿ã¤ã ã¢ã¦ã10ç§
    // èª­ã¿è¾¼ã¿éå§
   }

  // @Override
  // public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
  //  super.onReceivedError(view, request, error);
   // Log.d("WebView", "Webview Error");
   // receivederror = true;
   //}
   @Override
   public void onPageFinished(WebView view, String url) {
    super.onPageFinished(view, url);

    if(timeout || receivederror){
     //Log.d("WebView","Timeout Error");
    }
    else{
     succeed = true;
     Log.d("WebView","succeeded");
     //webview.loadUrl(url);
     showPdfFile(url);
    }
    // èª­ã¿è¾¼ã¿çµäº
   }

/*
   @Override
   public void onPageStarted(WebView view, String url, Bitmap favicon) {
    checkOnPageStartedCalled = true;
   }

   @Override
   public void onPageFinished(WebView view, String url) {
    if (checkOnPageStartedCalled) {
     //pdfView.loadUrl(removePdfTopIcon);
     //hideProgress();
    } else {
     showPdfFile(url);
     //webview.loadUrl(url);
    }
   }
*/
  });

 }

 private LinearLayout.LayoutParams createParam(int w, int h){
  return new LinearLayout.LayoutParams(w, h);
 }
 public void onClick(View v) {
  SpannableStringBuilder url = (SpannableStringBuilder)textUrl.getText();
  webview.loadUrl(url.toString());
 }

 private void showPdfFile(final String urlString) {
  Log.d("showPdfFile","Isucceeded");
  //showProgress();
  webview.invalidate();
  webview.getSettings().setJavaScriptEnabled(true);
  //webview.getSettings().setSupportZoom(true);
  webview.loadUrl(urlString);
  webview.setWebViewClient(new WebViewClient() {
   boolean checkOnPageStartedCalled = false;

   @Override
   public void onPageStarted(WebView view, String url, Bitmap favicon) {
    checkOnPageStartedCalled = true;
   }

   @Override
   public void onPageFinished(WebView view, String url) {
    if (checkOnPageStartedCalled) {
     //webview.loadUrl(removePdfTopIcon);
     //hideProgress();
    } else {
     showPdfFile(urlString);
    }
   }
  });
 }
}
/*
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
*/