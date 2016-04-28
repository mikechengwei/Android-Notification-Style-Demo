package cw.per.cn;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {
    private String Tag="nofification";
    private static NotificationCreator notificationCreator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationCreator=NotificationCreator.getInstance(this);
        ButterKnife.bind(this);

    }
    @OnClick(R.id.simple_notification)
    public  void displaySimpleNotification()
    {
        notificationCreator.createSimpleNotification(this);
    }

    @OnClick(R.id.progress_notification)
    public  void displayProgressNotification()
    {
        notificationCreator.createProgressNotification(this);
    }
    @OnClick(R.id.BigPictureStyle_notifcation)
    public  void displayBigPictureStyleNotification()
    {
        notificationCreator.createBigPictureStyle(this);
    }
    @OnClick(R.id.BigTextStyle_notifcation)
    public  void displayBigTextStyleNotification()
    {
        notificationCreator.createBigTextStyle(this);
    }
    @OnClick(R.id.InBoxStyle_notifcation)
    public  void displayInBoxStyleNotification()
    {
        notificationCreator.createInBoxStyle(this);
    }
    @OnClick(R.id.HangUpStyle_notifcation)
    public  void displayHangUpStyleNotification()
    {
        notificationCreator.createHangUpStyle(this);
    }
    @OnClick(R.id.MediaStyle_notifcation)
    public  void displayMediaStyleNotification()
    {
        notificationCreator.createMediaStyle(this);
    }
    @OnClick(R.id.template_notifcation)
    public  void displayTemplateNotification()
    {
        notificationCreator.createTemplateStyleNotification(this);
    }
}
