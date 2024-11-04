package com.example.coffeeappv3.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.coffeeappv3.R

class ProfilePage : AppCompatActivity() {

    private lateinit var profileImage: ImageView
    private lateinit var profileName: TextView
    private lateinit var profileEmail: TextView
    private lateinit var myReviewsButton: Button
    private lateinit var accountSettingsButton: Button
    private lateinit var personalInfoButton: Button
    private lateinit var notificationButton: Button
    private lateinit var fingerprintSettingsButton: Button
    private lateinit var backButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_page)

        profileImage = findViewById(R.id.profileImage)
        profileName = findViewById(R.id.profileName)
        profileEmail = findViewById(R.id.profileEmail)
        myReviewsButton = findViewById(R.id.myReviewsButton)
        accountSettingsButton = findViewById(R.id.accountSettingsButton)
        personalInfoButton = findViewById(R.id.personalInfoButton)
        notificationButton = findViewById(R.id.notificationButton)
        fingerprintSettingsButton = findViewById(R.id.fingerprintSettingsButton)
        backButton = findViewById(R.id.backButton)

        // Set default profile info (this could come from an API in a real app)
        profileName.text = "Flair Rhodes"
        profileEmail.text = "flairdjy@gmail.com"

        // Set listeners for buttons to handle clicks
        myReviewsButton.setOnClickListener {
            // Handle "My Reviews" button click
            Toast.makeText(this, "My Reviews clicked", Toast.LENGTH_SHORT).show()
        }

        accountSettingsButton.setOnClickListener {
            // Handle "Account Settings" button click
            Toast.makeText(this, "Account Settings clicked", Toast.LENGTH_SHORT).show()
        }

        personalInfoButton.setOnClickListener {
            // Handle "Personal Information" button click
            Toast.makeText(this, "Personal Information clicked", Toast.LENGTH_SHORT).show()
        }

        notificationButton.setOnClickListener {
            // Handle "Notification" button click
            Toast.makeText(this, "Notification clicked", Toast.LENGTH_SHORT).show()
        }

        fingerprintSettingsButton.setOnClickListener {
            // Handle "Fingerprint Settings" button click
            Toast.makeText(this, "Fingerprint Settings clicked", Toast.LENGTH_SHORT).show()
        }

        backButton.setOnClickListener {
            // Handle "Back to Main page" button click
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}