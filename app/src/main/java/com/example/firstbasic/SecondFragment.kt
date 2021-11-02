package com.example.firstbasic

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.firstbasic.databinding.FragmentSecondBinding
import androidx.navigation.fragment.navArgs

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    val args: SecondFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //This is the previous button
        binding.buttonSecond.setOnClickListener {
            //Sends you back to the first fragment
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        //Gets myArg from safeargs (passed through when first fragment navigated to this fragment
        val count = args.myArg
        //Gets the text we want to replace
        //To be honest I have no idea how this works
        //I thin that getString will replace %data (ie %d or %s) with passed in arguments
        val countText = getString(R.string.random_heading, count)
        //Actually replaces the text
        view.findViewById<TextView>(R.id.textview_second).text = countText

        //Normal java random number generator
        val random = java.util.Random()
        var randomNumber = 0
        //No points in generating a number if it is negative (this should never happen though)
        if (count > 0){
            randomNumber = random.nextInt(count+1)
        }
        //Replaces the number in the middle with a randomly generated number
        view.findViewById<TextView>(R.id.textView2).text = randomNumber.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}