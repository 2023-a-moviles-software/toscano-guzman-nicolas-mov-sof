package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

class ImageFragment : Fragment() {

    companion object {
        private const val ARG_IMAGE_URL = "image_url"

        fun newInstance(imageUrl: String): ImageFragment {
            val fragment = ImageFragment()
            val args = Bundle()
            args.putString(ARG_IMAGE_URL, imageUrl)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val imageUrl = arguments?.getString(ARG_IMAGE_URL)
        val imageView = ImageView(requireContext())

        // Carga la imagen desde la URL en el ImageView usando Glide
        imageUrl?.let {
            Glide.with(this)
                .load(it)
                .into(imageView)
        }

        return imageView
    }
}
