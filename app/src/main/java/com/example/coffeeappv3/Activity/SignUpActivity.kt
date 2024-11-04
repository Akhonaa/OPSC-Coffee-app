package com.example.coffeeappv3.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.coffeeappv3.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignUpActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmEditText: EditText
    private lateinit var signupButton: Button
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // Initialize views
        usernameEditText = findViewById(R.id.username_text)
        emailEditText = findViewById(R.id.email_text)
        passwordEditText = findViewById(R.id.password_text)
        confirmEditText = findViewById(R.id.confirm_text)
        signupButton = findViewById(R.id.signup_btn)

        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
        // Set up button click listener
        signupButton.setOnClickListener {
            performSignup()
        }
    }

    private fun performSignup() {
        val username = usernameEditText.text.toString()
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()
        val confirmPassword = confirmEditText.text.toString()

        // Basic validation
        if (username.isEmpty()) {
            Toast.makeText(this, "Username cannot be empty", Toast.LENGTH_SHORT).show()
            return


        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show()
            return
        }

        if (password.isEmpty()) {
            Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_SHORT).show()
            return
        }

        if (password != confirmPassword) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            return
        }

        // Firebase Authentication to create a new user
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Get the current user's UID
                val userId = firebaseAuth.currentUser?.uid

                if (userId != null) {
                    // Save username to Firestore under the user's UID
                    val userMap = hashMapOf(
                        "username" to username
                    )
                    firestore.collection("users").document(userId).set(userMap).addOnSuccessListener {
                        // Move to MainActivity after successful signup
                        Toast.makeText(this, "Signup successful", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        intent.putExtra("USERNAME_KEY", username)
                        startActivity(intent)
                        finish() // Prevent going back to SignUpActivity
                    }.addOnFailureListener { e ->
                        Toast.makeText(this, "Failed to save username: ${e.message}", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(this, "Error: User ID is null", Toast.LENGTH_LONG).show()
                }
            } else {
                // Show error message if signup fails
                Toast.makeText(this, "Signup failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
            }
        }.addOnFailureListener { exception ->
            // Handle any specific errors
            Toast.makeText(this, "Error: ${exception.message}", Toast.LENGTH_LONG).show()
        }


    }}
