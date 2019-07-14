package com.loogs.alc_4_0.phase1.challenge.android;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MyProfileActivity extends AppCompatActivity {
    private ImageView profilePic;
    private TextView profileName;
    private TextView profileOtherInfo;
    private static String profilePicURL = "https://media.licdn.com/dms/image/C5603AQEYyHX6W22aMQ/profile-displayphoto-shrink_200_200/0?e=1568246400&v=beta&t=7A4GVFblHfNgVYI8YuaMGvZwmTX5e77kRl8eDQn3ED4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
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

        this.profileName = findViewById(R.id.textViewProfileName);
        // below underlines the text
        // profileName.setPaintFlags(profileName.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        this.profileOtherInfo = findViewById(R.id.textViewProfileOtherInfo);

        // https://stackoverflow.com/questions/2471935/how-to-load-an-imageview-by-url-in-android
        this.profilePic = findViewById(R.id.ImageViewProfilePic);
        new DownLoadImageTask(this.profilePic)
                .execute(profilePicURL);
    }

}
