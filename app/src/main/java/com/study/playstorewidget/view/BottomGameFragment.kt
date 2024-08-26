package com.study.playstorewidget.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.study.playstorewidget.R
import com.study.playstorewidget.databinding.FragmentBottomGameBinding
import com.study.playstorewidget.gameTabLayout.FavoriteChartFragment
import com.study.playstorewidget.gameTabLayout.KidsFragment
import com.study.playstorewidget.gameTabLayout.RecommendFragment

class BottomGameFragment : Fragment() {
    private lateinit var binding: FragmentBottomGameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBottomGameBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        childFragmentManager.beginTransaction().replace(R.id.tab_layout_container, RecommendFragment()).commit()
        binding.mainFragmentTablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    val selectedFragment: Fragment = when(it.position){
                        0 -> RecommendFragment()
                        1 -> FavoriteChartFragment()
                        2 -> KidsFragment()
                        else -> {throw Exception("tab_error")}
                    }
                    childFragmentManager.beginTransaction()
                        .replace(R.id.tab_layout_container, selectedFragment)
                        .commit()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })


    }

}