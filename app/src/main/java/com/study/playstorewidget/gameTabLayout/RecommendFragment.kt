package com.study.playstorewidget.gameTabLayout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.gson.Gson
import com.study.playstorewidget.databinding.FragmentRecommendBinding
import com.study.typeui.dto.PlayStoreAppData
import com.study.typeui.dto.PlayStoreContent
import com.study.typeui.interfaces.OnPlayStoreCustomAppItem
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
        binding.bannerList.setPlayStoreAppItemClickListener(playStoreAppItemClickListener)

        binding.horizontalList.setJsonData(playStoreAppData[1].content)
        binding.horizontalList.setPlayStoreAppItemClickListener(playStoreAppItemClickListener)

        binding.iconList.setJsonData(playStoreAppData[2].content)
        binding.iconList.setPlayStoreAppItemClickListener(playStoreAppItemClickListener)

    }

    private val playStoreAppItemClickListener = object : OnPlayStoreCustomAppItem {
        override fun onAppClick(playStoreContent: PlayStoreContent, isInstalled: Boolean) {
            //앱 설치 유무
            if (isInstalled) {
                //설치 true
                val launchIntent = context?.packageManager?.getLaunchIntentForPackage(playStoreContent.pkg)
                if (launchIntent != null) {
                    context?.startActivity(launchIntent)
                }
                Toast.makeText(context, "앱을 실행합니다", Toast.LENGTH_SHORT).show()
            } else {
                //설치x false
                Toast.makeText(
                    context,
                    "pkg: ${playStoreContent.pkg} 상세페이지로 이동",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
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