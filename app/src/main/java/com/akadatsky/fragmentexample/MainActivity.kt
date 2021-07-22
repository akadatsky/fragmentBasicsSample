package com.akadatsky.fragmentexample

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.addFragment).setOnClickListener {
            addFragment()
            it.isEnabled = false
        }

    }

    private fun addFragment() {
        val mainFragment = MainFragment()
        mainFragment.arguments = bundleOf(
            "some_int" to 123,
            "some_string" to "asdf",
            "some_more_string" to "qwerty",
            "some_boolean" to true
        )

        /*
            // without fragment-ktx:
            val transaction = supportFragmentManager.beginTransaction()
            transaction.setReorderingAllowed(true)
            transaction.add(R.id.container, mainFragment)
            transaction.commit()
         */

        // with fragment-ktx:
        // add to gradle:
        // implementation 'androidx.fragment:fragment-ktx:1.3.5'
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add(R.id.container, mainFragment)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFragmentManager.popBackStack()
    }

}