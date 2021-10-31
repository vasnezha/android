package com.example.lab1.ui.string

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.example.lab1.LabApplication
import com.example.lab1.R
import com.example.lab1.database.Result
import com.example.lab1.util.ListContainer
import com.example.lab1.viewmodels.StringViewModel

class StringFragment : Fragment() {

    private val stringViewModel by viewModels<StringViewModel> {
        StringViewModel.Factory(
            (requireActivity().application as LabApplication).repository
        )
    }

    private lateinit var personName: EditText
    private lateinit var personSurname: EditText
    private lateinit var outputFullname: Button
    private lateinit var viewFullname: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_string, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        personName = view.findViewById(R.id.editTextPersonNameText)
        personSurname = view.findViewById(R.id.editTextPersonSurnameText)
        outputFullname = view.findViewById(R.id.materialButtonOutputFullname)
        viewFullname = view.findViewById(R.id.textViewFullname)

        outputFullname.isEnabled = false

        outputFullname.setOnClickListener {
            stringViewModel.concatenateStrings(
                personName.text.toString(),
                personSurname.text.toString()
            )
        }

        personName.addTextChangedListener { text ->
            outputFullname.isEnabled = !text.isNullOrEmpty() && !personSurname.text.isNullOrEmpty()
        }
        personSurname.addTextChangedListener { text ->
            outputFullname.isEnabled = !text.isNullOrEmpty() && !personName.text.isNullOrEmpty()
        }

        stringViewModel.result.observe(viewLifecycleOwner) {
            stringViewModel.saveResult(Result(0, it))
            viewFullname.text = it
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }
    }



    companion object {
        fun newInstance() = StringFragment()
    }
}