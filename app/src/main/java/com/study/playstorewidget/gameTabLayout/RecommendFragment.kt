package com.study.playstorewidget.gameTabLayout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.study.playstorewidget.databinding.FragmentRecommendBinding
import com.study.typeui.dto.PlayStoreAppData
import java.io.IOException

class RecommendFragment : Fragment() {
    private lateinit var binding: FragmentRecommendBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecommendBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gson = Gson()
        val playStoreAppData = listOf(
            loadJson("googleplay_game_banner_5.json"),
            loadJson("googleplay_game_sports_30.json"),
            loadJson("googleplay_game_action_30.json")
        ).map {
            gson.fromJson(it, PlayStoreAppData::class.java)
        }

        binding.bannerList.setJsonData(playStoreAppData[0].content)
        binding.horizontalList.setJsonData(playStoreAppData[1].content)
        binding.iconList.setJsonData(playStoreAppData[2].content)

    }

    private fun loadJson(fileName: String): String? {
        return try {
            requireContext().assets.open(fileName)
                .bufferedReader()
                .use { it.readText() }
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}