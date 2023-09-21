package com.example.firebasestorageexample.activity

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.firebasestorageexample.R
import com.example.firebasestorageexample.databinding.ActivityMainBinding
import com.example.firebasestorageexample.utils.openActivity
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import io.github.inflationx.viewpump.ViewPumpContextWrapper

class MainActivity : AppCompatActivity() {

    private lateinit var binder: NotificationCompat.Builder

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase !!))
    }

    private lateinit var binding: ActivityMainBinding

    // Create an explicit intent for an Activity in your app

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        bindClick()
        askPermission()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library

        val intent = Intent(this@MainActivity, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent =
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        binder = NotificationCompat.Builder(this, "1")
            .setSmallIcon(R.drawable.ic_delete)
            .setContentTitle("My notification")
            .setContentText("Hello World!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            // Set the intent that will fire when the user taps the notification
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "ABCD"
            val descriptionText = "Hello Sir!!"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("1", name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun bindClick() {

        binding.btnUpload.setOnClickListener {
            openActivity(UploadActivity::class.java)
        }
        binding.btnRealTimeDatabase.setOnClickListener {
            openActivity(RealTimeDatabaseActivity::class.java)

            val notificationManager = NotificationManagerCompat.from(this)

// notificationId is a unique int for each notification that you must define

// notificationId is a unique int for each notification that you must define
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return@setOnClickListener
            }
            notificationManager.notify(1, binder.build())
        }
        binding.btnStorage.setOnClickListener {
            openActivity(StorageAcitivity::class.java)
        }
    }

    private fun askPermission() {
        val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arrayListOf(
                Manifest.permission.READ_MEDIA_VIDEO,
                Manifest.permission.READ_MEDIA_IMAGES,
                Manifest.permission.READ_MEDIA_AUDIO
            )
        } else {
            arrayListOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        }
        Dexter.withContext(this).withPermissions(permission)
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(p0: MultiplePermissionsReport?) {
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: MutableList<PermissionRequest>?, p1: PermissionToken?
                ) {
                }
            }).check()
    }
}