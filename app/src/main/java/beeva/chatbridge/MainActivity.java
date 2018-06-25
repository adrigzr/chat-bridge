package beeva.chatbridge;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebMessage;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private WebView mWebview;
    private Button mButton;
    private EditText mEditText;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get webview
        mWebview = this.findViewById(R.id.webview);

        WebSettings settings = mWebview.getSettings();

        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);

        mWebview.setWebContentsDebuggingEnabled(true);
        mWebview.addJavascriptInterface(this, "Chat");
        mWebview.loadUrl("file:///android_asset/index.html");

        // Get button
        mButton = this.findViewById(R.id.button);
        mButton.setOnClickListener(this);

        // Get edit
        mEditText = this.findViewById(R.id.editText);

        // Get text view
        mTextView = this.findViewById(R.id.textView);
    }

    @JavascriptInterface
    public void send(String str) {
        mTextView.append(str + '\n');
    }

    public void onClick(View v) {
        Editable text = mEditText.getText();

        // Clean edit
        mEditText.setText(null);

        // Post to webview
        mWebview.postWebMessage(new WebMessage(text.toString()), Uri.EMPTY);
    }

}
