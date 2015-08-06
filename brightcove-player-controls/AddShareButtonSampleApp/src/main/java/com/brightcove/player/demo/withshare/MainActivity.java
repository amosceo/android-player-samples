package com.brightcove.player.demo.withshare;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.brightcove.player.mediacontroller.BrightcoveMediaController;
import com.brightcove.player.model.Video;
import com.brightcove.player.view.BrightcovePlayer;
import com.brightcove.player.view.BrightcoveVideoView;

/**
 * This app illustrates customizing the rewind button glyph using the Brightcove media controller.  The code for the
 * rewind button glyph is a customizable resource.  See res/values/strings.xml for the gory detail.
 *
 * @author Paul Michael Reilly
 */
public class MainActivity extends BrightcovePlayer {

    // Private class constants

    private final String TAG = this.getClass().getSimpleName();

    @Override protected void onCreate(Bundle savedInstanceState) {
        // When extending the BrightcovePlayer, we must assign the BrightcoveVideoView before
        // entering the superclass. This allows for some stock video player lifecycle
        // management.  Establish the video object and use it's event emitter to get important
        // notifications and to control logging.
        setContentView(R.layout.default_activity_main);
        brightcoveVideoView = (BrightcoveVideoView) findViewById(R.id.brightcove_video_view);
        super.onCreate(savedInstanceState);

        // Add a test video from the res/raw directory to the BrightcoveVideoView.
        String PACKAGE_NAME = getApplicationContext().getPackageName();
        Uri video = Uri.parse("android.resource://" + PACKAGE_NAME + "/" + R.raw.shark);
        brightcoveVideoView.add(Video.createVideo(video.toString()));

        // Add an onclick handler for the share button.
        BrightcoveMediaController controller = new BrightcoveMediaController(brightcoveVideoView);
        Button shareButton = (Button) controller.getBrightcoveControlBar().findViewById(R.id.share);
        shareButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(final View view) {
                    // Deal with sharing by showing a message, for simplicity.
                    Context context = getApplicationContext();
                    CharSequence text = "Video shared.";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            });
    }

}
