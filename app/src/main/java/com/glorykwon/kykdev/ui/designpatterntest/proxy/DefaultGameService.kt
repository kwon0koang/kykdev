package com.glorykwon.kykdev.ui.designpatterntest.proxy

import timber.log.Timber

class DefaultGameService: GameService {

    override fun startGame() {
        Timber.d("게임을 시작합니다.")
    }

}


