package com.example.notificationsapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.nio.file.attribute.AclEntry

class MainActivity : AppCompatActivity() {
    private val channelId = "myapp.notifications"
    private val description = "Notification App Example"
    lateinit var builder: Notification.Builder
    lateinit var showbtn: Button
    lateinit var edMsg: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edMsg = findViewById(R.id.edMsg)
        showbtn = findViewById(R.id.showbtn)
        showbtn.setOnClickListener {
            if (edMsg.text.toString().isNotEmpty()) {
                val notificationManager =
                    getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                val intent = Intent(this, NotificationActivity::class.java)
                val pendingIntent =
                    PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    var notificationChannel = NotificationChannel(
                        channelId,
                        description,
                        NotificationManager.IMPORTANCE_HIGH
                    )
                    notificationManager.createNotificationChannel(notificationChannel)
                    builder = Notification.Builder(this, channelId)
                        .setSmallIcon(R.drawable.ic_notification)
                        .setLargeIcon(
                            BitmapFactory.decodeResource(
                                this.resources,
                                R.drawable.ic_notification
                            )
                        )
                        .setContentIntent(pendingIntent)
                        .setContentTitle("My Notification")
                        .setContentText(edMsg.text.toString())
                } else {
                    builder = Notification.Builder(this)
                        .setSmallIcon(R.drawable.ic_notification)
                        .setLargeIcon(
                            BitmapFactory.decodeResource(
                                this.resources,
                                R.drawable.ic_notification
                            )
                        )
                        .setContentIntent(pendingIntent)
                        .setContentTitle("My Notification")
                        .setContentText(edMsg.text.toString())
                }
                notificationManager.notify(1234, builder.build())
            } else {
                Toast.makeText(this, "Enter Massage", Toast.LENGTH_SHORT).show()
            }
        }
    }
}