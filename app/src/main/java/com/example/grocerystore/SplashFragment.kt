package com.example.grocerystore

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.grocery.R


class SplashFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       Handler().postDelayed({
           findNavController().navigate(R.id.action_splashFragment_to_mainActivityFragment2)
       },3000)

        return inflater.inflate(R.layout.fragment_splash, container, false)
    }


}