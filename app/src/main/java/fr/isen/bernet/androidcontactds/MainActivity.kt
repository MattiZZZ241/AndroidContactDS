package fr.isen.bernet.androidcontactds

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.bernet.androidcontactds.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.recyclerContact

        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = ContactAdapter(arrayListOf()) {
            val intent = Intent(this@MainActivity, ContactDetailsActivity::class.java)
            intent.putExtra("contactFirstName", it)
            startActivity(intent)
        }

        loadContactFromAPI()
    }

    private fun loadContactFromAPI() {
        Volley.newRequestQueue(this)

        val url = "https://randomuser.me/api/?results=10&nat=fr"
        val jsonRequest = JsonObjectRequest(Request.Method.GET, url, null, {
            Log.w("API", "response : $it")
            handleAPIData(it.toString())
        }, {
            Log.w("API", "erreur : $it")
            Toast.makeText(this@MainActivity, "Erreur API", Toast.LENGTH_SHORT).show()
        })
        Volley.newRequestQueue(this).add(jsonRequest)
    }

    private fun handleAPIData(data: String) {
        val ContactResult = Gson().fromJson(data, ContactDetails::class.java)
        val contact = ContactResult.results

        val adapter = binding.recyclerContact.adapter as ContactAdapter
        adapter.refreshList(contact)
    }
}