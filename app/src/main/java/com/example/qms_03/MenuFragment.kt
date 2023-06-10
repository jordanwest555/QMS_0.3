package com.example.qms_03

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qms_03.R.layout.fragment_menu

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenuFragment() : Fragment(), Parcelable {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    constructor(parcel: Parcel) : this() {
        param1 = parcel.readString()
        param2 = parcel.readString()
    }

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
        // Inflate the layout for this fragment
        return inflater.inflate(fragment_menu, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MenuFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MenuFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(param1)
        parcel.writeString(param2)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MenuFragment> {
        override fun createFromParcel(parcel: Parcel): MenuFragment {
            return MenuFragment(parcel)
        }

        override fun newArray(size: Int): Array<MenuFragment?> {
            return arrayOfNulls(size)
        }
    }
}
class MenuFragment() : Fragment(), Parcelable {

    private lateinit var logQueryButton: Button
    private lateinit var queryStatusButton: Button
    private lateinit var queryHistoryButton: Button
    private lateinit var rateQmsButton: Button

    constructor(parcel: Parcel) : this() {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_menu, container, false)

        logQueryButton = view.findViewById(R.id.log_query_button)
        queryStatusButton = view.findViewById(R.id.query_status_button)
        queryHistoryButton = view.findViewById(R.id.query_history_button)
        rateQmsButton = view.findViewById(R.id.rate_qms_button)

        logQueryButton.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_logQueryFragment)
        }

        queryStatusButton.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_queryStatusFragment)
        }

        queryHistoryButton.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_queryHistoryFragment)
        }

        rateQmsButton.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_rateQmsFragment)
        }

        return view
    }

    private fun findNavController(): Any {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MenuFragment> {
        override fun createFromParcel(parcel: Parcel): MenuFragment {
            return MenuFragment(parcel)
        }

        override fun newArray(size: Int): Array<MenuFragment?> {
            return arrayOfNulls(size)
        }
    }
}

class Button {

}
