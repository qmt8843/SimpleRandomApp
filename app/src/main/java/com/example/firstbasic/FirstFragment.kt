package com.example.firstbasic

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.firstbasic.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Button to bring you to random fragment
        binding.randomButton.setOnClickListener {
            //Gets the text view with the number
            val showCountTextView = view.findViewById<TextView>(R.id.textview_first)
            //Gets the int value of the text inside the text view
            val currentCount = showCountTextView.text.toString().toInt()
            //Creates an action, which is something? And gives the currentCount
            //We can pass this in because we are using the SafeArgs library, and created an
            //argument in SecondFragment
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(currentCount)
            //Executes the navigation with the action
            findNavController().navigate(action)
        }

        //Toast button, sends a toast message to the OS?
        binding.toastButton.setOnClickListener {
            //Creation of the toast, instead of hardcoding text, it uses a resource file\
            //Pretty sure the LENGTH_SHORT is how long is lasts on screen
            val myToast = Toast.makeText(context, resources.getString(R.string.toast_text), Toast.LENGTH_SHORT)
            //Makes the toast appear
            myToast.show()
        }

        //Count button, increases number on screen
        //This number is also sent to fragment 2 to base its random range
        binding.countButton.setOnClickListener {
            //A call to countMe function
            countMe(view)
        }
    }

    //This function adds one to the number on screen. It takes this fragments view as a parameter
    private fun countMe(view: View) {
        //Find the number text view on screen (its sort of like a container)
        val showCountTextView = view.findViewById<TextView>(R.id.textview_first)
        //Gets the string stored in the text view
        val countString = showCountTextView.text.toString()
        //Turns the string into an int so we can increase
        var count = countString.toInt()
        //Increases the value
        count++
        //Sets the string version of the value to the text view's text
        showCountTextView.text = count.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}