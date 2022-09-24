package com.example.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.fragments.databinding.Fragment1Binding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
const val ARG_PARAM1 = "param1"
const val ARG_PARAM2 = "param2"
/**
 * A simple [Fragment] subclass.
 * Use the [Fragment1.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment1 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    // assign the _binding variable initially to null and
    // also when the view is destroyed again it has to be set to null
    private var _binding : Fragment1Binding? = null
    // with the backing property of the kotlin we extract
    // the non null value of the _binding
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // inflate the layout and bind to the _binding
        _binding = Fragment1Binding.inflate(inflater, container, false)
        // retrieve the entered data by the user
        binding.doneButton1.setOnClickListener {
            // Toast.makeText(activity, "TEST", Toast.LENGTH_SHORT).show()
            val str: String = binding.editText1.text.toString()
            if (str.isNotEmpty()) {
                Toast.makeText(activity, str, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(activity, "Please Enter Data",
                    Toast.LENGTH_SHORT).show()
            }
        }
// handle the button from the host activity using findViewById method
        val submitButton: Button =
            requireActivity().findViewById(R.id.submit_button)
        submitButton.setOnClickListener {
            Toast.makeText(activity, "Host Activity Element Clicked from Fragment 1", Toast.LENGTH_SHORT).show()
        }
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
