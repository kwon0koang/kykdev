package com.glorykwon.kykdev.designpatterntest.iterator

import org.junit.Test

/**
 * 이터레이터 패턴
 * 집합 객체(list, map, set, ...) 내부 구조를 노출시키지 않고, 순회하는 방법을 제공
 * 집합 객체를 순회하는 클라이언트 코드를 변경할 필요 X
 * 클라이언트는 list, map, set, tree 등등 어떤 집합 객체를 순회해야 하는지 알 필요 X
 */
class Board {
    val mPosts = mutableListOf<Post>()
    fun getRecentPostIterator() = RecentPostIterator(mPosts)
}

data class Post(val content: String, val time: Long)

class RecentPostIterator: Iterator<Post> {

    var mIterator: Iterator<Post>

    constructor(posts: Collection<Post>) {
        mIterator = posts.sortedByDescending { it.time }.iterator()
    }

    override fun hasNext(): Boolean {
        return mIterator.hasNext()
    }

    override fun next(): Post {
        return mIterator.next()
    }

}

class DPIterator {

    @Test
    fun test() {
        val board = Board().apply {
            mPosts.add(Post("test post 1", System.currentTimeMillis()))
            mPosts.add(Post("test post 2", System.currentTimeMillis()+1000))
            mPosts.add(Post("test post 3", System.currentTimeMillis()+2000))
        }

        // 순회 (이터레이터 패턴 사용 X)
        board.mPosts.forEach { post ->
            println(post)
        }

        // 순회 (이터레이터 패턴 사용 O)
        board.mPosts.iterator().let { iterator ->
            while(iterator.hasNext()) {
                println(iterator.next())
            }
        }

        // 순회 (이터레이터 패턴 사용 O, 이터레이터 커스텀해서 역순으로 출력)
        board.getRecentPostIterator().let { iterator ->
            while(iterator.hasNext()) {
                println(iterator.next())
            }
        }
    }

}