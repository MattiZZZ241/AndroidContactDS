package fr.isen.bernet.androidcontactds

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.isen.bernet.androidcontactds.databinding.ActivityContactDetailsBinding

class ContactDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val firstName = intent.extras?.getString("contactFirstName")
        binding.contactFirstName.text = firstName
    }
}
