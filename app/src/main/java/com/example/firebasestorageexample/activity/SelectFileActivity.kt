package com.example.firebasestorageexample.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.example.firebasestorageexample.R
import com.example.firebasestorageexample.adapter.AdapterFragment
import com.example.firebasestorageexample.databinding.ActivitySelectFileBinding
import com.example.firebasestorageexample.fragments.AudioFragment
import com.example.firebasestorageexample.fragments.ImageFragment
import com.example.firebasestorageexample.fragments.VideoFragment

class SelectFileActivity : AppCompatActivity() {

    lateinit var binding: ActivitySelectFileBinding

    private val mAdapterFragment = AdapterFragment(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySelectFileBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        bindListener()
        bindClick()

        resetTabs(binding.tvImage, binding.ivImage)
        binding.viewPager.offscreenPageLimit = 3

        mAdapterFragment.addFragment(ImageFragment())
        mAdapterFragment.addFragment(VideoFragment())
        mAdapterFragment.addFragment(AudioFragment())
        binding.viewPager.adapter = mAdapterFragment
    }

    private fun bindClick() {
        binding.crdImages.setOnClickListener {
            binding.viewPager.currentItem = 0
            resetTabs(binding.tvImage, binding.ivImage)
        }
        binding.crdVideos.setOnClickListener {
            binding.viewPager.currentItem = 1
            resetTabs(binding.tvVideo, binding.ivVideo)
        }
        binding.crdAudios.setOnClickListener {
            binding.viewPager.currentItem = 2
            resetTabs(binding.tvAudio, binding.ivAudio)
        }
    }

    private fun bindListener() {
        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {

                when (position) {
                    0 -> {
                        resetTabs(binding.tvImage, binding.ivImage)
                    }

                    1 -> {
                        resetTabs(binding.tvVideo, binding.ivVideo)
                    }

                    2 -> {
                        resetTabs(binding.tvAudio, binding.ivAudio)
                    }
                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })

    }

    fun resetTabs(selectedText: TextView, selectedImage: ImageView) {

        binding.tvImage.setTextColor(ContextCompat.getColor(this, R.color.iconColor2))
        binding.tvVideo.setTextColor(ContextCompat.getColor(this, R.color.iconColor2))
        binding.tvAudio.setTextColor(ContextCompat.getColor(this, R.color.iconColor2))

        binding.ivImage.setColorFilter(ContextCompat.getColor(this, R.color.iconColor2))
        binding.ivVideo.setColorFilter(ContextCompat.getColor(this, R.color.iconColor2))
        binding.ivAudio.setColorFilter(ContextCompat.getColor(this, R.color.iconColor2))

        selectedText.setTextColor(ContextCompat.getColor(this, R.color.iconColor1))
        selectedImage.setColorFilter(ContextCompat.getColor(this, R.color.iconColor1))

    }
}