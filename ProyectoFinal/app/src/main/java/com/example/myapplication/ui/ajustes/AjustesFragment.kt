package com.example.myapplication.ui.ajustes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentAjustesBinding

class AjustesFragment : Fragment() {

    private var _binding: FragmentAjustesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.cuenta, container, false)

        val imageProfile: ImageView = root.findViewById(R.id.image_profile)
        val textUsername: TextView = root.findViewById(R.id.text_username)

        imageProfile.setImageResource(R.drawable.perfil)

        textUsername.text = "Andrea Sanchez"

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}