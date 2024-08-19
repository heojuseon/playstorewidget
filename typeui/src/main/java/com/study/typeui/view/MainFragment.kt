package com.study.typeui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.study.typeui.databinding.FragmentMainBinding
import com.study.typeui.dto.PlayStoreFake
import java.io.IOException

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val json = loadJson()
        val gson = Gson()
        val playStoreFake = gson.fromJson(json, PlayStoreFake::class.java)
        binding.bannerList.setJsonData(playStoreFake.content)
        binding.horizontalList.setJsonData(playStoreFake.content)

    }

    private fun loadJson(): String? {
        val json: String
        try {
            json = requireContext().assets.open("googleplay_game_action_30.json")
                .bufferedReader()
                .use { it.readText() }
        }catch (e: IOException){
            e.printStackTrace()
            return null
        }
        return json
    }
}