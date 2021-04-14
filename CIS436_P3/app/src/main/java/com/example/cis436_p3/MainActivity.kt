package com.example.cis436_p3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cis436_p3.ui.main.MainFragment
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import org.json.JSONObject
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    lateinit var cloudImage: ImageView
    lateinit var rainImage: ImageView
    lateinit var sunImage: ImageView
    lateinit var stormImage: ImageView
    lateinit var snowImage: ImageView
    lateinit var fogImage: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        R.drawable.cloud
        R.drawable.rain
        R.drawable.snow
        R.drawable.storm
        R.drawable.sun
        R.drawable.fog

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.defaultLow, MainFragment.newInstance())
                    .commitNow()
        }

        val queue = Volley.newRequestQueue(this)
        val currUrl = "https://api.openweathermap.org/data/2.5/weather?id=4990512&appid=45b395353ec56f36cab53022d51d87ff"
        val fourDayURL = "https://api.openweathermap.org/data/2.5/forecast?id=4990512&appid=45b395353ec56f36cab53022d51d87ff"
        val stringRequestCurr = StringRequest(Request.Method.GET, currUrl, Response.Listener<String> { response ->
            Log.d("JSONRESPONSECURR", response)
            val fahrenheitTemp:Double
            val highFahrenheitTemp:Double
            val lowFahrenheitTemp:Double
            val jsonObj = JSONObject(response)
            var weather = jsonObj.getJSONArray("weather")
            var currWeather = weather.getJSONObject(0)
            var id = currWeather.getInt("id")
            var mainWeather = currWeather.getString("main")
            var desc = currWeather.getString("description")

            var mainTemperature1 =jsonObj.getJSONObject("main")
            var temp=mainTemperature1.getDouble("temp")
            var lowTemp=mainTemperature1.getDouble("temp_min")
            var highTemp=mainTemperature1.getDouble("temp_max")


            val mainWeatherImageView=findViewById<ImageView>(R.id.dayOfWeatherImage)
            if(mainWeather.equals("Clouds")){
                mainWeatherImageView.setImageResource(R.drawable.cloud)

            }
            else if(mainWeather.equals("Clear")){
                mainWeatherImageView.setImageResource(R.drawable.sun)
            }
            else if(mainWeather.equals("Rain")){
                mainWeatherImageView.setImageResource(R.drawable.rain)
            }
            else if(mainWeather.equals("Drizzle")){
                mainWeatherImageView.setImageResource(R.drawable.rain)
            }
            else if(mainWeather.equals("Snow")){
                mainWeatherImageView.setImageResource(R.drawable.snow)
            }
            else if(mainWeather.equals("Thunderstorm")){
                mainWeatherImageView.setImageResource(R.drawable.storm)
            }
            else {
                mainWeatherImageView.setImageResource(R.drawable.fog)
            }
            val defaultWeather=findViewById<TextView>(R.id.WeatherDayOf)
            defaultWeather.setText(temp.toString())
            val highWeather=findViewById<TextView>(R.id.defaultHigh)
            highWeather.setText(highTemp.toString())
            val lowWeather=findViewById<TextView>(R.id.lowMain)
            lowWeather.setText(lowTemp.toString())
            Log.d("JSON Info", "ID: " + id)
            Log.d("JSON Info", "Main Weather: " + mainWeather)
            Log.d("JSON Info", "Description: " + desc)
            Log.d("JSON Info", "Temperature "+temp)
            Log.d("JSON Info", "Temperature "+lowTemp)
            Log.d("JSON Info", "Temperature "+highTemp)
        },
        Response.ErrorListener {Log.d("JSONRESPONSE","Error!")});

        val stringRequestFiveDay = StringRequest(Request.Method.GET, fourDayURL, Response.Listener<String> { response ->
            Log.d("JSONRESPONSE5DAY", response)
            val jsonObj = JSONObject(response)
            var listObj = jsonObj.getJSONArray("list")
            for (i in 7..39 step 8){
                var obj = listObj.getJSONObject(i)
                var dt = obj.getInt("dt")
                var weathObj = obj.getJSONArray("weather")
                var currWeath = weathObj.getJSONObject(0)
                var mainObj = obj.getJSONObject("main")

                var weather = currWeath.getString("main")
                var lowTemp = mainObj.getDouble("temp_min")
                var highTemp = mainObj.getDouble("temp_max")

                when(i){
                    7->  {
                        val day1image = findViewById<ImageView>(R.id.day1Image);
                        val day1high = findViewById<TextView>(R.id.day1High);
                        val day1low = findViewById<TextView>(R.id.day1Low);

                        day1high.setText(highTemp.toString())
                        day1low.setText(lowTemp.toString())

                        when(weather) {
                            "Clouds"-> day1image.setImageResource(R.drawable.cloud)
                            "Clear"-> day1image.setImageResource(R.drawable.sun)
                            "Rain"-> day1image.setImageResource(R.drawable.rain)
                            "Drizzle"-> day1image.setImageResource(R.drawable.rain)
                            "Snow"-> day1image.setImageResource(R.drawable.snow)
                            "Thunderstorm"-> day1image.setImageResource(R.drawable.storm)
                            else-> day1image.setImageResource(R.drawable.fog)
                        }
                    }
                    15-> {
                        val day2image = findViewById<ImageView>(R.id.day2Image);
                        val day2high = findViewById<TextView>(R.id.day2High);
                        val day2low = findViewById<TextView>(R.id.day2Low);

                        day2high.setText(highTemp.toString())
                        day2low.setText(lowTemp.toString())

                        when(weather) {
                            "Clouds"-> day2image.setImageResource(R.drawable.cloud)
                            "Clear"-> day2image.setImageResource(R.drawable.sun)
                            "Rain"-> day2image.setImageResource(R.drawable.rain)
                            "Drizzle"-> day2image.setImageResource(R.drawable.rain)
                            "Snow"-> day2image.setImageResource(R.drawable.snow)
                            "Thunderstorm"-> day2image.setImageResource(R.drawable.storm)
                            else-> day2image.setImageResource(R.drawable.fog)
                        }
                    }
                    23-> {
                        val day3image = findViewById<ImageView>(R.id.day3Image);
                        val day3high = findViewById<TextView>(R.id.day3High);
                        val day3low = findViewById<TextView>(R.id.day3Low);

                        day3high.setText(highTemp.toString())
                        day3low.setText(lowTemp.toString())

                        when(weather) {
                            "Clouds"-> day3image.setImageResource(R.drawable.cloud)
                            "Clear"-> day3image.setImageResource(R.drawable.sun)
                            "Rain"-> day3image.setImageResource(R.drawable.rain)
                            "Drizzle"-> day3image.setImageResource(R.drawable.rain)
                            "Snow"-> day3image.setImageResource(R.drawable.snow)
                            "Thunderstorm"-> day3image.setImageResource(R.drawable.storm)
                            else-> day3image.setImageResource(R.drawable.fog)
                        }
                    }
                    31-> {
                        val day4image = findViewById<ImageView>(R.id.day4Image);
                        val day4high = findViewById<TextView>(R.id.day4High);
                        val day4low = findViewById<TextView>(R.id.day4Low);

                        day4high.setText(highTemp.toString())
                        day4low.setText(lowTemp.toString())

                        when(weather) {
                            "Clouds"-> day4image.setImageResource(R.drawable.cloud)
                            "Clear"-> day4image.setImageResource(R.drawable.sun)
                            "Rain"-> day4image.setImageResource(R.drawable.rain)
                            "Drizzle"-> day4image.setImageResource(R.drawable.rain)
                            "Snow"-> day4image.setImageResource(R.drawable.snow)
                            "Thunderstorm"-> day4image.setImageResource(R.drawable.storm)
                            else-> day4image.setImageResource(R.drawable.fog)
                        }
                    }
                    else -> {Log.d("When", "" + i)}
                }
            }
        },
                Response.ErrorListener {Log.d("JSONRESPONSE","Error!")})


        queue.add(stringRequestCurr)
        queue.add(stringRequestFiveDay)
    }
}