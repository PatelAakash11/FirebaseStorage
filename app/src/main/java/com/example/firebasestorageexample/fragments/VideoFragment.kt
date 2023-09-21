package com.example.firebasestorageexample.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.firebasestorageexample.adapter.AdapterFiles
import com.example.firebasestorageexample.data.DataFiles
import com.example.firebasestorageexample.databinding.FragmentfilesBinding


class VideoFragment : Fragment() {

    lateinit var binding: FragmentfilesBinding
    private lateinit var mAdapterFiles: AdapterFiles
    private val listOfFiles = arrayListOf<DataFiles>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mAdapterFiles = AdapterFiles(requireActivity(), onClickSelectedFile = {
            val returnIntent = Intent()
            returnIntent.putExtra("result", it.file)
            requireActivity().setResult(Activity.RESULT_OK, returnIntent)
            requireActivity().finish()
        })
        binding = FragmentfilesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rcvFiles.layoutManager = GridLayoutManager(requireActivity(), 3)
        binding.rcvFiles.adapter = mAdapterFiles
        permission()
    }

    private fun permission() {

        val cursor = requireActivity().contentResolver.query(
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI, null, null, null, null
        )
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val videoIndex = cursor.getColumnIndex(MediaStore.MediaColumns.DATA)
                    val video = cursor.getString(videoIndex)

                    Log.d("TAGVideo", "permission: $video")

                    val file = DataFiles(video)

                    listOfFiles.add(file)

                } while (cursor.moveToNext())

                mAdapterFiles.setData(listOfFiles)

            }
        }
    }


}