package com.glorykwon.kykdev.util.kt

/**
 * SingleLiveEvent 와 동일한 역할을 하지만 observer 여러개 등록 가능
 */
open class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set // Allow external read but not write

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) { // 이벤트가 이미 처리되었다면
            null // null을 반환
        } else {
            hasBeenHandled = true // 이벤트가 처리되었다고 표시한 후에
            content // 값을 반환
        }
    }

    /**
     * 이벤트 처리 여부와 관계 없이 값을 반환
     */
    fun peekContent(): T = content

}