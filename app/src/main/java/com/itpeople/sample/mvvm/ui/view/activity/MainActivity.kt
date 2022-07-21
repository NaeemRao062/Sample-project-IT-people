package com.itpeople.sample.mvvm.ui.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.itpeople.sample.mvvm.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        this.supportActionBar!!.setTitle(resources.getString(R.string.app_name))
        this.supportActionBar?.setDisplayHomeAsUpEnabled(false);
        super.onBackPressed()
    }
    override fun onResume() {
        super.onResume()
    }
}