package com.study.typeui.interfaces

import com.study.typeui.dto.PlayStoreContent

interface OnPlayStoreCustomAppItem {
    fun onAppClick(playStoreContent: PlayStoreContent, isInstalled: Boolean)
}