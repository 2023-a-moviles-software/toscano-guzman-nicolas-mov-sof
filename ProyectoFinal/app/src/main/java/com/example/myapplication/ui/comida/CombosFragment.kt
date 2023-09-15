package com.example.myapplication.ui.comida

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.ImageSliderAdapter
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentCombosBinding

class CombosFragment : Fragment() {

    private var _binding: FragmentCombosBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_image_slider, container, false)

        val viewPager: ViewPager2 = root.findViewById(R.id.viewPager)

        // Desactiva el recorte de padding para asegurarte de que todas las imágenes tengan el mismo tamaño
        viewPager.clipToPadding = false

        // Establece el padding deseado en los lados izquierdo y derecho para centrar las imágenes
        val padding = resources.getDimensionPixelSize(R.dimen.view_pager_padding)
        viewPager.setPadding(padding, 0, padding, 300)

        val imageUrls = listOf(
            "https://i.pinimg.com/564x/d6/66/ae/d666ae69e6665e5c95d897cb219bb152.jpg",
            "https://i.pinimg.com/564x/b7/2a/79/b72a79eab6cb925a55101a34cbbe6f33.jpg",
            "https://i.pinimg.com/564x/1e/80/90/1e80907526bf96fff3946ce4d78aa5e9.jpg",
            "https://i.pinimg.com/564x/b6/b7/ee/b6b7ee388e73c21f6a3c77728a036b15.jpg"
        )

        val adapter = ImageSliderAdapter(requireActivity(), imageUrls)
        viewPager.adapter = adapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}