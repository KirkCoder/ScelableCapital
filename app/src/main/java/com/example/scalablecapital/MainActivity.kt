package com.example.scalablecapital

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.scalablecapital.base.BaseFragment

class MainActivity : AppCompatActivity() {

    private val flowFragment
        get() = supportFragmentManager.findFragmentById(R.id.container_content) as? StarterFlowFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container_content, StarterFlowFragment.newInstance())
                .commit()
        }
    }

    fun showFragment(
        toFragment: BaseFragment,
        tag: String
    ) {
        flowFragment?.showFragment(
            toFragment, tag
        )
    }

    override fun onBackPressed() {
        val back = flowFragment?.popBackStack()
        if (back == null || back == false) {
            super.onBackPressed()
        }
    }
}