package com.jelvix.kt_shared_state_live

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {

    private val mainActivityViewModel: MainActivityViewModel by viewModels()

    private lateinit var btnTest: Button
    private lateinit var etFullName: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnTest = findViewById(R.id.btnTest)
        etFullName = findViewById(R.id.etFullName)

        btnTest.setOnClickListener(View.OnClickListener {
            mainActivityViewModel.setValues(etFullName.text.toString())
        })

        lifecycleScope.launchWhenStarted {
            mainActivityViewModel.fullNameSharedFlow.collect {
                Log.d("debapp", "Shared flow: ${it}")
            }
        }

        lifecycleScope.launchWhenStarted {
            mainActivityViewModel.fullNameStateFlow.collect {
                Log.d("debapp", "State flow: ${it}")
            }
        }

        mainActivityViewModel.fullNameLiveData.observe(this, Observer {
            Log.d("debapp", "Live data: ${it}")
        })
    }
}