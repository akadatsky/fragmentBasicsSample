package com.akadatsky.fragmentexample

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit

class MainFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val paramSample = it.getInt("some_int")
            Log.i("MY_TAG", "params: $paramSample")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_main, container, false)

    /*
        The onActivityCreated() method is now deprecated.
        Code touching the fragment's view should be done in onViewCreated()
        and other initialization code should be in onCreate()
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.next).setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.container, SecondFragment()).addToBackStack(null)
            }

            if (activity != null) {
                Toast.makeText(activity, "Hello from fragment", Toast.LENGTH_LONG).show()
            }
            // Toast.makeText(App.appContext, "Hello from fragment", Toast.LENGTH_LONG).show()
        }
    }

}
