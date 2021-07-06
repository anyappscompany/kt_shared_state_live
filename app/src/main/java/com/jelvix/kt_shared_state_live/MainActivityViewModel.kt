package com.jelvix.kt_shared_state_live

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainActivityViewModel: ViewModel() {

    private val _fullNameSharedFlow = MutableSharedFlow<String>()
    val fullNameSharedFlow: SharedFlow<String> = _fullNameSharedFlow

    private val _fullNameStateFlow = MutableStateFlow<String>("DefaultValue(StateFlow)")
    val fullNameStateFlow: StateFlow<String> = _fullNameStateFlow

    private val _fullNameLiveData = MutableLiveData<String>()
    val fullNameLiveData: LiveData<String> = _fullNameLiveData

    fun setValues(value: String){

        viewModelScope.launch {
            _fullNameSharedFlow.emit(value)
        }

        viewModelScope.launch {
            _fullNameStateFlow.value = value
        }

        _fullNameLiveData.postValue(value)
    }
}