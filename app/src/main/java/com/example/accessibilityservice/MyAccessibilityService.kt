package com.example.accessibilityservice

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.os.Build
import android.util.Log
import android.view.KeyEvent
import android.view.accessibility.AccessibilityEvent
import android.widget.Toast
import androidx.annotation.RequiresApi

class MyAccessibilityService : AccessibilityService() {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        // Check if WhatsApp is launched
//        if (event?.packageName == "com.whatsapp" && event.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
//            // Display a toast message
//            Toast.makeText(this, "WhatsApp is launched", Toast.LENGTH_SHORT).show()
//
//        }
//        if (event?.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
//            // Get the name of the package that generated the event
//            val packageName = event.packageName?.toString()
//            // Check if the package name is not null and is not our own package
//            if (!packageName.isNullOrEmpty() && packageName != packageName) {
//                // Display a toast message with the name of the package
//                val packageManager = packageManager
//                val applicationInfo = packageManager.getApplicationInfo(packageName, 0)
//                val applicationName = packageManager.getApplicationLabel(applicationInfo).toString()
//                Toast.makeText(this, "$applicationName is launched", Toast.LENGTH_SHORT).show()
//                Log.d("tag","$applicationName")
//            }
//        }
        if (event?.eventType == AccessibilityEvent.TYPE_VIEW_CLICKED) {
            // Get the name of the package that generated the event
            val packageName = event.text?.toString()
            Toast.makeText(applicationContext, "$packageName is launched", Toast.LENGTH_SHORT).show()

            // Check if the package name is not null and is not our own package
            if (!packageName.isNullOrEmpty() && packageName != applicationContext.packageName) {
                // Display a toast message with the name of the package
//                val packageManager = packageManager
//                val applicationInfo = packageManager.getApplicationInfo(packageName, 0)
//                val applicationName = packageManager.getApplicationLabel(applicationInfo).toString()
//                Toast.makeText(applicationContext, "$applicationName is launched", Toast.LENGTH_SHORT).show()
//                Log.d("tag","$applicationName")
            }else{
                Log.d("tag","222222")
            }
        }else{
            val packageName = event!!.windowChanges?.toString()
            Log.d("tag","111111")
            Log.d("tag","$event?.eventType")
            Log.d("tag","$packageName")
            Log.d("tag","${AccessibilityEvent.TYPE_VIEW_CLICKED}")
            Log.d("tag","${AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED}")

        }
    }

    override fun onInterrupt() {
        // Handle service interruption here
    }
    override fun onKeyEvent(event: KeyEvent): Boolean {
        val action: Int = event.getAction()
        val keyCode: Int = event.getKeyCode()
        // the service listens for both pressing and releasing the key
        // so the below code executes twice, i.e. you would encounter two Toasts
        // in order to avoid this, we wrap the code inside an if statement
        // which executes only when the key is released
        if (action == KeyEvent.ACTION_UP) {
            if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
                Log.d("Check", "KeyUp")
                Toast.makeText(this, "KeyUp", Toast.LENGTH_SHORT).show()
            } else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
                Log.d("Check", "KeyDown")
                Toast.makeText(this, "KeyDown", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onKeyEvent(event)
    }

    override fun onServiceConnected() {
        val  info = AccessibilityServiceInfo()
        info.apply {
            // Set the type of events that this service wants to listen to. Others
            // won't be passed to this service.
            eventTypes = AccessibilityEvent.TYPE_VIEW_CLICKED or AccessibilityEvent.TYPE_VIEW_FOCUSED or AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED

            // If you only want this service to work with specific applications, set their
            // package names here. Otherwise, when the service is activated, it will listen
            // to events from all applications.
//            packageNames = arrayOf("com.example.android.myFirstApp", "com.example.android.mySecondApp")

            // Set the type of feedback your service will provide.
            feedbackType = AccessibilityServiceInfo.FEEDBACK_SPOKEN

            // Default services are invoked only if no package-specific ones are present
            // for the type of AccessibilityEvent generated. This service *is*
            // application-specific, so the flag isn't necessary. If this was a
            // general-purpose service, it would be worth considering setting the
            // DEFAULT flag.

            // flags = AccessibilityServiceInfo.DEFAULT;

            notificationTimeout = 100
        }

        this.serviceInfo = info

    }
}
