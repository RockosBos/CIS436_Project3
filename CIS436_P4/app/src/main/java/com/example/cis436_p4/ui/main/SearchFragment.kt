package com.example.cis436_p4.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.cis436_p4.R
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val inputFragmentView: View = inflater.inflate(R.layout.fragment_search, container, false)

        // api request variables
        val queue = Volley.newRequestQueue(activity?.applicationContext)
        var searchBut = view?.findViewById<Button>(R.id.searchButtonSearch)
        fun getMovie(title: String) {
            var splitString = title.split(" ").toTypedArray()
            val newTitle = splitString.joinToString("%20")
            var url = "https://movie-database-imdb-alternative.p.rapidapi.com/?s=&page=1&r=json";
            url = url.substring(0, 58) + newTitle + url.substring(58, url.length)
            val keyHeader = "aca0de8833mshd95dc54b87093a2p17c456jsnfd0d32ee10eb";
            val hostHeader = "movie-database-imdb-alternative.p.rapidapi.com"

            val req = object :
                StringRequest(Request.Method.GET, url, Response.Listener<String> { response ->
                    val resp = response.toString()
                    Log.d("JSONRESPONSE", resp);

                    // Gets json of search
                    val jsonObj = JSONObject(response)
                    val jsonArr = jsonObj.getJSONArray("Search")
                    // index of first movie in list (best match?)
                    val firstMovie = jsonArr.getJSONObject(0)

                    // all values within the first movie
                    val movieTitle = firstMovie.getString("Title")
                    val movieYear = firstMovie.getString("Year")
                    val movieType = firstMovie.getString("Type")
                    val moviePoster = firstMovie.getString("Poster")
                    // Change widgets
                    val titleWid = view?.findViewById<TextView>(R.id.titleSearch)
                    searchBut = view?.findViewById<Button>(R.id.searchButtonSearch)
                    Log.d("searchbutton", searchBut?.text.toString())
                    titleWid?.setText(movieTitle)

                }, Response.ErrorListener { Log.d("JSONRESPONSE", "Error!") }) {
                override fun getHeaders(): MutableMap<String, String> {
                    val headers = HashMap<String, String>()
                    headers["x-rapidapi-key"] = keyHeader;
                    headers["x-rapidapi-host"] = hostHeader;
                    return headers
                }
            }


            queue.add(req)
        }

        fun LOL(title: String) {
            var splitString = title.split(" ").toTypedArray()
            val newTitle = splitString.joinToString("%20")
            var url = "https://movie-database-imdb-alternative.p.rapidapi.com/?s=&page=1&r=json";
            url = url.substring(0, 58) + newTitle + url.substring(58, url.length)
            val keyHeader = "aca0de8833mshd95dc54b87093a2p17c456jsnfd0d32ee10eb";
            val hostHeader = "movie-database-imdb-alternative.p.rapidapi.com"

            val req = object :
                StringRequest(Request.Method.GET, url, Response.Listener<String> { response ->

                    searchBut = view?.findViewById<Button>(R.id.searchButtonSearch)
                    searchBut?.setOnClickListener {
                        val searchText = view?.findViewById<EditText>(R.id.editTextTextPersonNameSearch)
                        Log.d("ONclickpressed", searchText?.text.toString())
                        getMovie(searchText?.text.toString())
                    }
                    Log.d("searchbutton", searchBut?.text.toString())

                }, Response.ErrorListener { Log.d("JSONRESPONSE", "Error!") }) {
                override fun getHeaders(): MutableMap<String, String> {
                    val headers = HashMap<String, String>()
                    headers["x-rapidapi-key"] = keyHeader;
                    headers["x-rapidapi-host"] = hostHeader;
                    return headers
                }
            }


            queue.add(req)
        }

        LOL("Avengers Endgame")
        getMovie("Avengers Endgame")
        getMovie("I am Legend")

        // Inflate the layout for this fragment
        return inputFragmentView
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
}