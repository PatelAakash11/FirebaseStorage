package com.example.firebasestorageexample.activity


import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.firebasestorageexample.adapter.AdapterRealTimeDatabase
import com.example.firebasestorageexample.data.DataRealTimeDatabase
import com.example.firebasestorageexample.databinding.ActivityRealTimeDatabaseBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage


class RealTimeDatabaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRealTimeDatabaseBinding
    private val database = Firebase.database
    private val myRef = database.getReference("Images")
    private val storage = Firebase.storage
    private val storageRef = storage.reference
    private lateinit var mAdapterRealTimeDatabase: AdapterRealTimeDatabase
    private val listOfRealTimeDatabase = arrayListOf<DataRealTimeDatabase>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRealTimeDatabaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myRef.addValueEventListener(object : ValueEventListener {

            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                listOfRealTimeDatabase.clear()

                snapshot.children.forEach {
                    listOfRealTimeDatabase.add(it.getValue(DataRealTimeDatabase::class.java)!!)
                }

                mAdapterRealTimeDatabase.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {
            }
        })

        mAdapterRealTimeDatabase =
            AdapterRealTimeDatabase(this, listOfRealTimeDatabase) {
                Log.d("TAGID", "onCreate: ${it.uniqueId}")
                myRef.child("Image_${it.uniqueId}").removeValue()
                storageRef.child("Images/ ${it.name}").delete()
            }
        binding.rcvRealTimeDatabase.layoutManager = GridLayoutManager(this, 1)
        binding.rcvRealTimeDatabase.adapter = mAdapterRealTimeDatabase

    }
}