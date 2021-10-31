package com.example.lab1.ui.number

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.lab1.R
import com.example.lab1.viewmodels.NumberViewModel

class NumberFragment : Fragment() {

    private val numberViewModel by viewModels<NumberViewModel>()

    private lateinit var editTextNumberOne: EditText
    private lateinit var editTextNumberTwo: EditText
    private lateinit var materialButtonSum: Button
    private lateinit var textViewSum: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_number, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editTextNumberOne = view.findViewById(R.id.editTextNumberOneText)
        editTextNumberTwo = view.findViewById(R.id.editTextNumberTwoText)
        materialButtonSum = view.findViewById(R.id.materialButtonSum)
        textViewSum = view.findViewById(R.id.textViewSum)

        materialButtonSum.setOnClickListener {
            val numberOne = editTextNumberOne.text.toString().toDouble()
            val numberTwo = editTextNumberTwo.text.toString().toDouble()
            numberViewModel.calculateSum(numberOne, numberTwo)
        }

        numberViewModel.result.observe(viewLifecycleOwner) { sum ->
            textViewSum.text = sum.toString()
            Toast.makeText(requireContext(), sum.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        fun newInstance() = NumberFragment()
    }
}