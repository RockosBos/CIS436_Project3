package com.example.cis436_p4.ui.main

import android.content.Context
import android.os.Bundle
import android.system.Os.bind
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.cis436_p4.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var activityCallback:SearchFragment.SearchListener?=null
    //private lateinit var binding: FragmentSearchBinding

    interface SearchListener{
        fun onButtonClick(whichButton: String)
    }

    override fun onAttach(context : Context) {
        super.onAttach(context)

        try {
            activityCallback = context as SearchListener
        }
        catch(e : ClassCastException) {
            throw ClassCastException(context.toString() +
                    " must implement BottomPetListener")
        }//end try -catch
    }//end onAttach


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

//        var textTitle: TextView? = view?.findViewById(R.id.title)
//        var textYear: TextView? = view?.findViewById(R.id.year)
//        var textRating: TextView? = view?.findViewById(R.id.rating)
//        var searchText: EditText? = view?.findViewById(R.id.editTextTextPersonName2)
//        var searchButton: Button? = view?.findViewById(R.id.searchButton)
//        textTitle?.setText("HEY")
//        searchButton?.setOnClickListener {
//
//            val text1= searchText?.getText().toString()
//            textTitle?.setText(text1)
//        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }
    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {

        binding = Fragment.bind(view)
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                SearchFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }

    override fun onClick(v: View?) {
        var whichButton=""
        Log.i("SearchFragment", "In the onClick at beginning")
        when(view?.id){
            R.id.searchButton->{
                whichButton="search"
            }
            R.id.SaveButton->{
                whichButton="save"
            }
        }
        activityCallback?.onButtonClick(whichButton)
    }
}