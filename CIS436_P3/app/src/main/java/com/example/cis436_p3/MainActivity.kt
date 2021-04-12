package com.example.cis436_p3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cis436_p3.ui.main.MainFragment
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import android.util.Log
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }

        var re = ""
        val queue = Volley.newRequestQueue(this)
        val url = "https://api.openweathermap.org/data/2.5/weather?q=London&appid=45b395353ec56f36cab53022d51d87ff"

        val stringRequest = StringRequest(Request.Method.GET, url, Response.Listener<String> { response ->
            re = response
            Log.d("JSONRESPONSE", re)
            val jsonObj = JSONObject(response)
            var weather = jsonObj.getJSONArray("weather")
            var currWeather = weather.getJSONObject(0)
            var id = currWeather.getInt("id")
            var mainWeather = currWeather.getString("main")
            var desc = currWeather.getString("description")

            Log.d("JSON Info", "ID: " + id)
            Log.d("JSON Info", "Main Weather: " + mainWeather)
            Log.d("JSON Info", "Description: " + desc)
        },
        Response.ErrorListener {re = "Error!"})

        queue.add(stringRequest)
    }
}