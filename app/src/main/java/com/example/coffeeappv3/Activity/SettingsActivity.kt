package com.example.coffeeappv3.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
import com.example.coffeeappv3.R
import com.google.android.material.switchmaterial.SwitchMaterial

class SettingsActivity : AppCompatActivity() {

    private lateinit var usernameTextView: TextView
    private var username: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        // Retrieve username from the intent
        username = intent.getStringExtra("USERNAME_KEY")

        usernameTextView = findViewById(R.id.username_text) // Replace with actual ID


        // Display the username if it exists
        if (username != null) {
            usernameTextView.text = username
        } else {
            usernameTextView.text = "User" // Default display if username is null
        }

        // Initialize all clickable items
        setupClickListeners()

        // Initialize dark mode switch
        setupDarkModeSwitch()
    }

    private fun setupClickListeners() {
        // List of all clickable items except dark mode
        val clickableItems = listOf(
            R.id.emailLayout to "Email",
            R.id.notificationsLayout to "Notifications",
            R.id.mobileNumberLayout to "Mobile Number",
            R.id.privacyLayout to "Privacy",
            R.id.textSizeLayout to "Text Size",
            R.id.languageLayout to "Language",
            R.id.switchAccountLayout to "Switch Account",
            R.id.accountSettingsLayout to "Account Settings",
            R.id.helpLayout to "Help"
        )

        // Set up click listeners for all items
        clickableItems.forEach { (id, name) ->
            findViewById<LinearLayout>(id).setOnClickListener {
                handleItemClick(name)
            }
        }

        // Set up profile section click listener
        findViewById<LinearLayout>(R.id.profileSection).setOnClickListener {
            handleItemClick("Profile")
        }
    }

    private fun setupDarkModeSwitch() {
        val darkModeSwitch = findViewById<SwitchMaterial>(R.id.darkModeSwitch)

        // Set initial state (you might want to get this from SharedPreferences)
        darkModeSwitch.isChecked = false

        darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            handleDarkModeChange(isChecked)
        }
    }

    private fun handleItemClick(itemName: String) {
        when (itemName) {
            "Email" -> {
                // Handle email settings
                navigateToEmailSettings()
            }
            "Notifications" -> {
                // Handle notifications settings
                navigateToNotificationSettings()
            }
            "Mobile Number" -> {
                // Handle mobile number settings
                navigateToMobileNumberSettings()
            }
            "Privacy" -> {
                // Handle privacy settings
                navigateToPrivacySettings()
            }
            "Text Size" -> {
                // Handle text size settings
                navigateToTextSizeSettings()
            }
            "Language" -> {
                // Handle language settings
                navigateToLanguageSettings()
            }
            "Switch Account" -> {
                // Handle account switching
                navigateToAccountSwitching()
            }
            "Account Settings" -> {
                // Handle account settings
                navigateToAccountSettings()
            }
            "Help" -> {
                // Handle help section
                navigateToHelp()
            }
            "Profile" -> {
                // Handle profile section click
                navigateToProfile()
            }
        }
    }

    private fun handleDarkModeChange(isEnabled: Boolean) {
        // Implement dark mode logic here
        // You might want to use AppCompatDelegate or a custom theme system
        Toast.makeText(
            this,
            "Dark mode ${if (isEnabled) "enabled" else "disabled"}",
            Toast.LENGTH_SHORT
        ).show()
    }

    // Navigation functions
    private fun navigateToEmailSettings() {
        // Implement navigation to email settings
        Toast.makeText(this, "Email settings clicked", Toast.LENGTH_SHORT).show()
    }

    private fun navigateToNotificationSettings() {
        // Implement navigation to notification settings
        Toast.makeText(this, "Notification settings clicked", Toast.LENGTH_SHORT).show()
    }

    private fun navigateToMobileNumberSettings() {
        // Implement navigation to mobile number settings
        Toast.makeText(this, "Mobile number settings clicked", Toast.LENGTH_SHORT).show()
    }

    private fun navigateToPrivacySettings() {
        // Implement navigation to privacy settings
        Toast.makeText(this, "Privacy settings clicked", Toast.LENGTH_SHORT).show()
    }

    private fun navigateToTextSizeSettings() {
        // Implement navigation to text size settings
        Toast.makeText(this, "Text size settings clicked", Toast.LENGTH_SHORT).show()
    }

    private fun navigateToLanguageSettings() {
        // Implement navigation to language settings
        Toast.makeText(this, "Language settings clicked", Toast.LENGTH_SHORT).show()
    }

    private fun navigateToAccountSwitching() {
        // Implement navigation to account switching
        Toast.makeText(this, "Switch account clicked", Toast.LENGTH_SHORT).show()
    }

    private fun navigateToAccountSettings() {
        // Implement navigation to account settings
        Toast.makeText(this, "Account settings clicked", Toast.LENGTH_SHORT).show()
    }

    private fun navigateToHelp() {
        // Implement navigation to help section
        Toast.makeText(this, "Help clicked", Toast.LENGTH_SHORT).show()
    }

    private fun navigateToProfile() {
        // Implement navigation to profile section
        Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show()
    }
}