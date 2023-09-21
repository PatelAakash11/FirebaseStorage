package com.example.firebasestorageexample.activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.firebasestorageexample.data.DataRealTimeDatabase
import com.example.firebasestorageexample.databinding.ActivityUploadBinding
import com.example.firebasestorageexample.utils.clearIt
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import java.io.File


class UploadActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase !!))
    }

    private lateinit var binding: ActivityUploadBinding
    private val database = Firebase.database
    private val myRef = database.getReference("Images")
//    private val listOfRealTimeDatabase = arrayListOf<DataRealTimeDatabase>()

    private val storage = Firebase.storage
    private val storageRef = storage.reference

    private var id: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityUploadBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.edtFilePath.isFocusable = false
        bindClick()

    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == 10) {
                val result = data?.getStringExtra("result")
//                Log.d("TAG-RESULT", "onActivityResult: $result")
                binding.edtFilePath.setText(result)
            }
        }
    }

    private fun bindClick() {

        binding.edtFilePath.setOnClickListener {
            val i = Intent(this, SelectFileActivity::class.java)
            startActivityForResult(i, 10)
        }

        binding.btnUploadFile.setOnClickListener {
            val uniqueId = if (id == 0L) {

                System.currentTimeMillis()
            } else {
                id
            }

            // Log.d("TAG_TIME_STAMP", "onCreate: onClickUpdate => $uniqueId")

            val name = binding.edtFileName.text.toString()
            val filePath = binding.edtFilePath.text.toString()
            val file = Uri.fromFile(File(filePath))
            Log.d("TAG_FILE", "bindClick: $file")


                val imageRef = storageRef.child("Images/$name")
            val upload = imageRef.putFile(file)

            binding.pbUpload.visibility = View.VISIBLE

            upload.addOnFailureListener {

            }
            myRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                }

                override fun onCancelled(error: DatabaseError) {

                }
            })

            upload.addOnSuccessListener { taskSnapshot ->
                taskSnapshot.metadata?.reference?.downloadUrl?.addOnCompleteListener {
                    Log.d("TAG_STORAGE", "bindClick: ${it.result}")
                    myRef.child("Image_${uniqueId}")
                        .setValue((DataRealTimeDatabase(uniqueId, name, it.result.toString())))
                    binding.pbUpload.visibility = View.GONE
                    finish()
                }
            }

            upload.addOnProgressListener {
                val progress = (100.0 * it.bytesTransferred) / it.totalByteCount
                Log.d("Progress", "bindClick: ${progress}")
                binding.pbUpload.progress = progress.toInt()
            }
            binding.edtFilePath.clearIt()
            binding.edtFileName.clearIt()
        }
    }
}