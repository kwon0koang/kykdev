package com.glorykwon.kykdev.ui.designpatterntest.proxy

/**
 * 프록시 패턴
 * 특정 객체에 대한 접근을 제어하거나 기능을 추가할 수 있음
 */
interface GameService {
    fun startGame()
}

class DefaultGameService: GameService {
    override fun startGame() {
        println("게임을 시작합니다.")
    }
}

class GameServiceProxy(val gameService: GameService): GameService {
    override fun startGame() {
        println("보안 모듈을 시작합니다.")
        gameService.startGame()
    }
}

fun main(){
    // 프록시 패턴 미사용
    DefaultGameService().startGame()

    // 프록시 패턴 사용
    GameServiceProxy(DefaultGameService()).startGame()
}