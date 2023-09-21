package com.example.firebasestorageexample.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.firebasestorageexample.adapter.AdapterFiles
import com.example.firebasestorageexample.data.DataFiles
import com.example.firebasestorageexample.databinding.FragmentfilesBinding


class ImageFragment : Fragment() {

    lateinit var binding: FragmentfilesBinding
    lateinit var mAdapterFiles: AdapterFiles
    val listOfFiles = arrayListOf<DataFiles>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mAdapterFiles = AdapterFiles(requireActivity(),
            onClickSelectedFile = {
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
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null
        )
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val imageIndex = cursor.getColumnIndex(MediaStore.MediaColumns.DATA)
                    val image = cursor.getString(imageIndex)

                    val file = DataFiles(image)

                    listOfFiles.add(file)

                } while (cursor.moveToNext())

                mAdapterFiles.setData(listOfFiles)
            }
        }
    }

}