package com.vokaltest.vokaltestapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

    private static final int PICKFILE_REQUEST_CODE = 1;
    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView filePath;
    private Button Browse;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        filePath = (TextView) findViewById(R.id.file_path);
        Browse = (Button) findViewById(R.id.browse);
        Browse.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.browse:
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                startActivityForResult(intent, PICKFILE_REQUEST_CODE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Uri fileUri = data.getData();
            Log.d("image selected path", fileUri.getPath());
            filePath.setText(fileUri.getPath());
            Intent intent = new Intent(this, WordsListActivity.class);
            intent.setData(data.getData());
            startActivity(intent);
        }
    }


}
