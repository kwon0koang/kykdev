package com.glorykwon.kykdev.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.glorykwon.kykdev.MainApplication
import timber.log.Timber

abstract class BaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainApplication.setActivityContext(this)

        savedInstanceState?.let {
            Timber.d("BaseActivity / onCreate / ${it.getString("test")}")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Timber.d("BaseActivity / onSaveInstanceState")
        outState.putString("test", System.currentTimeMillis().toString())
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        savedInstanceState?.let {
            Timber.d("BaseActivity / onRestoreInstanceState / ${it.getString("test")}")
        }
    }


    open class Animal
    class Dog: Animal() {
        val leg = 4
    }
    class Bird: Animal() {
        val leg = 2
    }

    fun testFunc(animalParam: Animal){
//        val animal = when(animalParam){
//            is Dog      -> { animalParam as Dog }
//            is Bird     -> { animalParam as Bird }
//            else        -> { null }
//        }
//
//        //animal이란 변수가 상황에 따라 Dog도 되었다가 Bird도 되었다가
//        //가능할까요 ?
//        print(animal.leg)
    }


}