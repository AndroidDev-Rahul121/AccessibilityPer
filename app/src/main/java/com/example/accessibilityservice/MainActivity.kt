package com.example.accessibilityservice

import android.accessibilityservice.AccessibilityServiceInfo
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.provider.Settings
import android.view.accessibility.AccessibilityManager
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    lateinit var button: MaterialButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.tv)


        button.setOnClickListener {
            val accessibilityManager =
                getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager
            val enabledServices = accessibilityManager.getEnabledAccessibilityServiceList(
                AccessibilityServiceInfo.FEEDBACK_GENERIC
            )
            val isServiceEnabled = enabledServices.any { it.id == "Accessibility Test Service" }
            Log.d("tag", "$enabledServices")
            Log.d("tag", "$isServiceEnabled")
            if (isServiceEnabled) {
                // Accessibility service is enabled
                Log.d("tag", "222211")
            } else {

                val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
                startActivity(intent)
                Log.d("tag", "112222")
            }
        }
    }
}

//// Accessibility service is not enabled
//for (enabledService in enabledServices) {
//    val componentName = enabledService.resolveInfo.serviceInfo.name
//    Log.d("tag","$componentName")
//    if (componentName == "ScreenMirroringAccessibilityService") {
//        // The service is enabled, do something here
//        Toast.makeText(this, "KeyDown", Toast.LENGTH_SHORT).show()
//        break
//    }
//}