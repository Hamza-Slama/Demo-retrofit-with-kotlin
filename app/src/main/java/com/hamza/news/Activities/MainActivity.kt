package com.hamza.news.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.hamza.news.R
import com.hamza.news.Services.User
import com.hamza.news.Services.getDataLoginFromApi
import com.hamza.news.Services.jsonip
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import com.hamza.news.Constant.ENISO_URL
import com.hamza.news.Constant.JSON_URL

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var i = 0
        btn.setOnClickListener {
            if (i % 2 == 0) {
                getDataLoginFromApi(this, ENISO_URL).execute()
                println("User =${User}")
                println("User to string= ${User.toString()}")
                Toast.makeText(applicationContext, "${User.toString()}", Toast.LENGTH_LONG).show()
                tvShow.text= User.toString()
            } else {
                getDataLoginFromApi(this, JSON_URL).execute()
                println("User =${jsonip}")
                println("User to string= ${jsonip.toString()}")
                Toast.makeText(applicationContext, "${jsonip.toString()}", Toast.LENGTH_LONG).show()
                tvShow.text= jsonip.toString()
            }
            i++
        }
    }
}
