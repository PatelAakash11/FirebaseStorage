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
import com.example.firebasestorageexample.adapter.AdapterAudio
import com.example.firebasestorageexample.data.DataAudio
import com.example.firebasestorageexample.databinding.FragmentfilesBinding


class AudioFragment : Fragment() {

    lateinit var binding: FragmentfilesBinding
    private lateinit var mAdapterAudio: AdapterAudio
    private val listOfFile = arrayListOf<DataAudio>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        mAdapterAudio = AdapterAudio(requireActivity(), onClickSelectedAudio = {
            val returnIntent = Intent()
            returnIntent.putExtra("result", it.audio)
            requireActivity().setResult(Activity.RESULT_OK, returnIntent)
            requireActivity().finish()
        })
        binding = FragmentfilesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rcvFiles.layoutManager = GridLayoutManager(requireActivity(), 1)
        binding.rcvFiles.adapter = mAdapterAudio
        permission()
    }

    private fun permission() {

         val cursor = requireActivity().contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null
        )
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val audioIndex = cursor.getColumnIndex(MediaStore.MediaColumns.DATA)
                    val audio = cursor.getString(audioIndex)

                    val nameIndex =
                        cursor.getColumnIndex(MediaStore.MediaColumns.DISPLAY_NAME)
                    val name = cursor.getString(nameIndex)

                    val file = DataAudio(audio, name)

                    listOfFile.add(file)

                } while (cursor.moveToNext())

                mAdapterAudio.setData(listOfFile)

            }
        }
    }
}