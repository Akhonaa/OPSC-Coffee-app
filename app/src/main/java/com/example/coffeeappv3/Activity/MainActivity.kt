package com.example.coffeeappv3.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeeappv3.Adapter.CategoryAdapter
import com.example.coffeeappv3.Adapter.OfferAdapter
import com.example.coffeeappv3.Adapter.PopularAdapter
import com.example.coffeeappv3.R
import com.example.coffeeappv3.ViewModel.MainViewModel
import com.example.coffeeappv3.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel = MainViewModel()
    private lateinit var firebaseAuth: FirebaseAuth
    private var username: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize FirebaseAuth instance
        firebaseAuth = FirebaseAuth.getInstance()

        // Check if the user is logged in, if not redirect to LoginActivity
        if (firebaseAuth.currentUser == null) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // Prevent going back to MainActivity without login
            return
        }

        // Retrieve username from Intent or Firestore
        username = intent.getStringExtra("USERNAME_KEY")
        if (username == null) {
            fetchUsernameFromFirestore()
        } else {
            initViews()
        }
    }

    // Function to fetch username from Firestore if it’s not in the Intent
    private fun fetchUsernameFromFirestore() {
        val userId = firebaseAuth.currentUser?.uid ?: return
        val firestore = FirebaseFirestore.getInstance()

        firestore.collection("users").document(userId).get()
            .addOnSuccessListener { document ->
                if (document != null && document.contains("username")) {
                    username = document.getString("username")
                    initViews() // Initialize views after retrieving username
                } else {
                    Toast.makeText(this, "Username not found in Firestore", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Failed to fetch username: ${e.message}", Toast.LENGTH_LONG).show()
            }
    }

    // Initialize views and setup menus after username retrieval
    private fun initViews() {
        initCategory()
        initPopular()
        initOffers()
        bottomMenu()
    }

    private fun bottomMenu() {
        binding.cartBtn.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }

        binding.settingsIcon.setOnClickListener {
            val settingsIntent = Intent(this, SettingsActivity::class.java)
            settingsIntent.putExtra("USERNAME_KEY", username) // Pass the username
            startActivity(settingsIntent)
        }

        binding.profileBtn.setOnClickListener {
            startActivity(Intent(this, ProfilePage::class.java))
        }
    }

    private fun initOffers() {
        binding.progressBarOffer.visibility = View.VISIBLE
        viewModel.offer.observe(this, Observer {
            binding.recyclerViewOffer.layoutManager =
                LinearLayoutManager(
                    this@MainActivity,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            binding.recyclerViewOffer.adapter = OfferAdapter(it)
            binding.progressBarOffer.visibility = View.GONE
        })

        viewModel.loadOffer()
    }

    private fun initPopular() {
        binding.progressBarPopular.visibility = View.VISIBLE
        viewModel.popular.observe(this, Observer {
            binding.recyclerViewPopular.layoutManager =
                LinearLayoutManager(
                    this@MainActivity,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            binding.recyclerViewPopular.adapter = PopularAdapter(it)
            binding.progressBarPopular.visibility = View.GONE
        })

        viewModel.loadPopular()
    }

    private fun initCategory() {
        binding.progressBarCategory.visibility = View.VISIBLE
        viewModel.category.observe(this, Observer {
            binding.recyclerViewCategory.layoutManager =
                LinearLayoutManager(
                    this@MainActivity,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            binding.recyclerViewCategory.adapter = CategoryAdapter(it)
            binding.progressBarCategory.visibility = View.GONE
        })

        viewModel.loadCategory()
    }
}