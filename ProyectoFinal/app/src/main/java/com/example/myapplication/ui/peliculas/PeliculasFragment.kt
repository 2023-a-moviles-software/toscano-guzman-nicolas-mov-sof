package com.example.myapplication.ui.peliculas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.ImageSliderAdapter
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentPeliculasBinding

class PeliculasFragment : Fragment() {

    private var _binding: FragmentPeliculasBinding? = null
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
            "https://www.xtrafondos.com/wallpapers/vertical/rapidos-y-furiosos-x-11694.jpg",
            "https://www.xtrafondos.com/wallpapers/vertical/barbie-pelicula-11707.jpg",
            "https://www.xtrafondos.com/wallpapers/vertical/transformers-el-despertar-de-las-bestias-11696.jpg",
            "https://www.xtrafondos.com/wallpapers/vertical/flash-11693.jpg",
            "https://www.xtrafondos.com/wallpapers/vertical/john-wick-4-keanu-reeves-11674.jpg"
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