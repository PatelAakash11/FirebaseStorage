package com.example.firebasestorageexample.activity


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.firebasestorageexample.adapter.AdapterImages
import com.example.firebasestorageexample.data.DataRealTimeDatabase
import com.example.firebasestorageexample.databinding.ActivityStorageAcitivityBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class StorageAcitivity : AppCompatActivity() {

    lateinit var binding: ActivityStorageAcitivityBinding
    private val database = Firebase.database
    private val myRef = database.getReference("Images")
    val listOfImages = arrayListOf<DataRealTimeDatabase>()
    lateinit var mAdapterImages: AdapterImages

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityStorageAcitivityBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        myRef.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                listOfImages.clear()

                snapshot.children.forEach {
                    listOfImages.add(it.getValue(DataRealTimeDatabase::class.java)!!)
                }

                mAdapterImages.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        mAdapterImages = AdapterImages(this, listOfImages)
        binding.rcvStorage.layoutManager = GridLayoutManager(this, 3)
        binding.rcvStorage.adapter = mAdapterImages
    }
}