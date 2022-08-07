package com.glorykwon.kykdev.ui.designpatterntest.proxy

import timber.log.Timber

class GameServiceProxy(val gameService: GameService): GameService {

    override fun startGame() {
        Timber.d("보안 모듈을 시작합니다.")
        gameService.startGame()
    }

}

