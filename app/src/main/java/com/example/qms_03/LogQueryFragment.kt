package com.example.qms_03

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class LogQueryFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var etTitle: EditText
    private lateinit var etQueryDescription: EditText
    private lateinit var etDepartment: EditText
    private lateinit var etDate: EditText

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
        val view = inflater.inflate(R.layout.fragment_log_query, container, false)

        etTitle = view.findViewById(R.id.et_title)
        etQueryDescription = view.findViewById(R.id.et_query_description)
        etDepartment = view.findViewById(R.id.et_department)
        etDate = view.findViewById(R.id.et_date)

        // Add a click listener for the button
        val buttonNavigateToMenu = view.findViewById<Button>(R.id.button_navigate_to_menu)
        buttonNavigateToMenu.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_logQueryFragment_to_menuFragment)
        }

        return view
    }
}
