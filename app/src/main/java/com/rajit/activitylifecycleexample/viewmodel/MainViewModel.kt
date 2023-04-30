package com.rajit.activitylifecycleexample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.rajit.activitylifecycleexample.util.AppConstants.COUNTER

/*
*   ViewModel
*/
class MainViewModel(
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _counter: MutableLiveData<Int> = MutableLiveData(0)
    val counter: LiveData<Int> get() = _counter

    fun setCounter() {
        _counter.postValue((counter.value?.plus(1)) ?: 0)
    }

}