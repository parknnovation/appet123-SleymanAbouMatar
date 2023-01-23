package com.example.appet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appet.adapter.AnimalsAdapter
import com.example.appet.animalsdata.AnimalData
import com.firebase.ui.auth.AuthUI
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    lateinit var mDataBase: DatabaseReference
    private lateinit var animaList: ArrayList<AnimalData>
    private lateinit var mAdapter: AnimalsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        /**initialized*/
        animaList = ArrayList()
        mAdapter = AnimalsAdapter(this, animaList)
        recyclerAnimals.layoutManager = LinearLayoutManager(this)
        recyclerAnimals.setHasFixedSize(true)
        // recyclerAnimals.adapter = mAdapter
        /**getData firebase*/
        getAnimalsData()

    }

    /**ok now create new activity*/


    private fun getAnimalsData() {

        mDataBase = FirebaseDatabase.getInstance().getReference("Animals")
        mDataBase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (animalSnapshot in snapshot.children) {
                        val animal = animalSnapshot.getValue(AnimalData::class.java)
                        animaList.add(animal!!)
                    }
                    recyclerAnimals.adapter = mAdapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    this@MainActivity,
                    error.message, Toast.LENGTH_SHORT
                ).show()
                
            }

        })


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.example_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item1 -> {
                Toast.makeText(this, "settings..", Toast.LENGTH_LONG).show()
                return true


            }
            R.id.item2 -> {
                Toast.makeText(this, "logged out", Toast.LENGTH_LONG).show()
                AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener {
                        // ...
                        finish()
                    }

                return true


            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }



}