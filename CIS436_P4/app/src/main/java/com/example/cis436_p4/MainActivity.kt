package com.example.cis436_p4

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.cis436_p4.ui.main.SectionsPagerAdapter
import com.example.cis436_p4.R
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import android.util.Log
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        // api request variables
        val queue = Volley.newRequestQueue(this)

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
                    Log.i("JSONRESPONSE", resp);

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
        getMovie("Avengers Endgame")
        getMovie("I am Legend")
    }
}