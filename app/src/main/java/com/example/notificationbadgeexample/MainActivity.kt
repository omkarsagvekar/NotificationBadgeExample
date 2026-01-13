package com.example.notificationbadgeexample

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.OptIn
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.notificationbadgeexample.databinding.ActivityMainBinding
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import com.google.android.material.badge.ExperimentalBadgeUtils

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var notificationBadge: BadgeDrawable
    private var count:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.cardViewNotificationBanner.post {
            setupNotificationBadge()
        }
        binding.cardViewNotificationBanner.setOnClickListener {
            updateNotificationCount()
        }
    }

    @OptIn(ExperimentalBadgeUtils::class)
    private fun setupNotificationBadge() {
        notificationBadge = BadgeDrawable.create(this).apply {
            isVisible = false
            maxCharacterCount = 3
            badgeGravity = BadgeDrawable.TOP_END
            //horizontalOffset = 6
            //verticalOffset = 6
        }

        BadgeUtils.attachBadgeDrawable(
            notificationBadge,
            binding.cardViewNotificationBanner,
            binding.flNotificationContainer // parent layout (FrameLayout or toolbar)
        )
    }


    private fun updateNotificationCount() {
        count++
        notificationBadge.number = count
        notificationBadge.maxCharacterCount = 3   // enables 99+
        notificationBadge.isVisible = true
    }
}