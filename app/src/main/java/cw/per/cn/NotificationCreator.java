package cw.per.cn;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;

import android.media.RingtoneManager;
import android.os.Build;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v7.app.NotificationCompat;
import android.widget.RemoteViews;

/**
 * Created by chengwei on 4/26/16.
 */
public class NotificationCreator {
    private String TAG="nofification";
    //Notification Priority
    public static final int TYPE_Normal = 1;
    public static final int TYPE_Progress = 2;
    public static final int TYPE_BigText = 3;
    public static final int TYPE_Inbox = 4;
    public static final int TYPE_BigPicture = 5;
    public static final int TYPE_Hangup = 6;
    public static final int TYPE_Media = 7;
    public static final int TYPE_Customer = 8;


    private static NotificationCreator notificationCreator;
    private static NotificationManager notificationManager;
    public static synchronized  NotificationCreator getInstance(Context context){
        if(notificationCreator==null){
            notificationCreator=new NotificationCreator();
            notificationManager =
                    (NotificationManager) context.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        }
        return notificationCreator;

    }
    public  void createSimpleNotification(Context context){
        //为了版本兼容  选择V7包下的NotificationCompat进行构造
        android.support.v4.app.NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setTicker("简单Notification")      //Ticker是状态栏显示的提示
                .setContentTitle("标题")            //第一行内容  通常作为通知栏标题
                .setContentText("通知内容")         //第二行内容 通常是通知正文
                .setSubText("这里显示的是通知第三行内容！")  //第三行内容 通常是内容摘要什么的 在低版本机器上不一定显示
                .setNumber(2)  //number设计用来显示同种通知的数量和ContentInfo的位置一样，如果设置了ContentInfo则number会被隐藏.ContentInfo 在通知的右侧 时间的下面 用来展示一些其他信息
                .setAutoCancel(true)//可以点击通知栏的删除按钮删除
                .setSmallIcon(R.drawable.ic_app)//系统状态栏显示的小图标
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_app_large)); //下拉显示的大图标
        Intent intent = new Intent(context,TestActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(context,1,intent,0);
        builder.setContentIntent(pIntent)//点击跳转的intent
                .setDefaults(NotificationCompat.DEFAULT_ALL);//通知默认的声音 震动 呼吸灯
        Notification notification = builder.build();
        notificationManager.notify(TYPE_Normal,notification);

    }
    public void createProgressNotification(Context context){
        android.support.v4.app.NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_app)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_app_large))
                .setAutoCancel(false)//禁止用户点击删除按钮删除
                .setOngoing(true) //禁止滑动删除
                .setShowWhen(false)  //取消右上角的时间显示
                .setContentTitle("下载中..."+7+"%")
                .setProgress(100,10,false);;
        Intent intent = new Intent(context,TestActivity.class);
        intent.putExtra("command",1);
        Notification notification = builder.build();
        notificationManager.notify(TYPE_Progress,notification);
    }
    public void createBigTextStyle(Context context){

        android.support.v4.app.NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentTitle("BigTextStyle")
                .setContentText("BigTextStyle演示示例")
                .setSmallIcon(R.drawable.ic_app)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_app_large));
        android.support.v4.app.NotificationCompat.BigTextStyle style = new android.support.v4.app.NotificationCompat.BigTextStyle();
        style.bigText("这里是点击通知后要显示的正文，可以换行可以显示很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长");
        style.setBigContentTitle("点击后的标题");
        //SummaryText没什么用 可以不设置
        style.setSummaryText("末尾只一行的文字内容");
        builder.setStyle(style)
              .setAutoCancel(true);
        Intent intent = new Intent(context,TestActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(context,1,intent,0);
        builder.setContentIntent(pIntent)
               .setDefaults(NotificationCompat.DEFAULT_ALL);
        Notification notification = builder.build();
        notificationManager.notify(TYPE_BigText,notification);
    }
    public void createInBoxStyle(Context context){
        android.support.v4.app.NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentTitle("InboxStyle")
                .setContentText("InboxStyle演示示例")
                .setSmallIcon(R.drawable.ic_app)
               .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_app_large));
        android.support.v4.app.NotificationCompat.InboxStyle style = new android.support.v4.app.NotificationCompat.InboxStyle();
        style.setBigContentTitle("BigContentTitle")
                .addLine("第一行，第一行，第一行，第一行，第一行，第一行，第一行")
                .addLine("第二行")
                .addLine("第三行")
                .addLine("第四行")
                .addLine("第五行")
                .setSummaryText("SummaryText");
        builder.setStyle(style)
               .setAutoCancel(true);
        Intent intent = new Intent(context,TestActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(context,1,intent,0);
        builder.setContentIntent(pIntent)
                .setDefaults(NotificationCompat.DEFAULT_ALL);
        Notification notification = builder.build();
        notificationManager.notify(TYPE_Inbox,notification);
    }
    public void createBigPictureStyle(Context context){
        android.support.v4.app.NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentTitle("BigPictureStyle")
                .setContentText("BigPicture演示示例")
                 .setSmallIcon(R.drawable.ic_app)
                 .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_app_large));
        android.support.v4.app.NotificationCompat.BigPictureStyle style = new android.support.v4.app.NotificationCompat.BigPictureStyle();
        style.setBigContentTitle("BigContentTitle");
        style.setSummaryText("SummaryText");
        style.bigPicture(BitmapFactory.decodeResource(context.getResources(),R.drawable.pic));
        builder.setStyle(style)
             .setAutoCancel(true);
        Intent intent = new Intent(context,TestActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(context,1,intent,0);
        //设置点击大图后跳转
        builder.setContentIntent(pIntent);
        Notification notification = builder.build();
        notificationManager.notify(TYPE_BigPicture,notification);
    }
    public  void createHangUpStyle(Context context){
        android.support.v4.app.NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentTitle("横幅通知")
                .setContentText("请在设置通知管理中开启消息横幅提醒权限")
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setSmallIcon(R.drawable.ic_app)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_app_large));
        Intent intent = new Intent(context,TestActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(context,1,intent,0);
        builder.setContentIntent(pIntent);
        //这句是重点
        builder.setFullScreenIntent(pIntent,true)
              .setAutoCancel(true);
        Notification notification = builder.build();
        notificationManager.notify(TYPE_Hangup,notification);
    }
    public void createMediaStyle(Context context){
        android.support.v4.app.NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentTitle("MediaStyle")
                .setContentText("Song Title")
               .setSmallIcon(R.drawable.ic_app)
               .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_app))
              .setDefaults(NotificationCompat.DEFAULT_ALL);
        Intent intent = new Intent(context,TestActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(context,1,intent,0);
        builder.setContentIntent(pIntent);
        //第一个参数是图标资源id 第二个是图标显示的名称，第三个图标点击要启动的PendingIntent
        builder.addAction(R.drawable.ic_pre,"",null)
             .addAction(R.drawable.ic_bofang,"",null)
             .addAction(R.drawable.ic_next,"",pIntent);


        NotificationCompat.MediaStyle style = new NotificationCompat.MediaStyle();
        style.setMediaSession(new MediaSessionCompat(context,"MediaSession",
                new ComponentName(context,Intent.ACTION_MEDIA_BUTTON),null).getSessionToken());
        //CancelButton在5.0以下的机器有效
        style.setCancelButtonIntent(pIntent)
              .setShowCancelButton(true);
        //设置要现实在通知右方的图标 最多三个
        style.setShowActionsInCompactView(2,2);
        builder.setStyle(style)
              .setShowWhen(false);
        Notification notification = builder.build();
        notificationManager.notify(TYPE_Media,notification);
    }
    //自定义布局的Notification
    public void createTemplateStyleNotification(Context context){
        RemoteViews customView = new RemoteViews(context.getPackageName(),
                R.layout.notification_template);
        Intent intent = new Intent(context,TestActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(context,1,intent,0);
        Notification mNotification = new NotificationCompat.Builder(context)
                // 设置小图标
                .setSmallIcon(R.drawable.ic_launcher)

                // 设置状态栏文本标题
                .setTicker("你的系统有更新")

                //设置自定义布局
                .setContent(customView)

                // 设置ContentIntent
                .setContentIntent(pIntent)

                // 设置Notification提示铃声为系统默认铃声
                .setSound(
                        RingtoneManager.getActualDefaultRingtoneUri(
                                context,
                                RingtoneManager.TYPE_NOTIFICATION))

                // 点击Notification的时候自动移除
                .setAutoCancel(true).build();

                /*
                 * 当API level 低于14的时候使用setContent()方法是没有用的
                 * 需要对contentView字段直接赋值才能生效
                 */
        if (Build.VERSION.SDK_INT < 14) {
            mNotification.contentView = customView;
        }

        notificationManager.notify(0, mNotification);
    }

}
