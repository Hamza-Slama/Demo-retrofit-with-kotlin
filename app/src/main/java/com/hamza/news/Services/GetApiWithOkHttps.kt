package com.hamza.news.Services

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.net.ConnectivityManager
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.gson.Gson
import com.hamza.news.Constant.ENISO_URL
import com.hamza.news.Models.JsonIp
import com.hamza.news.Models.UserInformation
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL

/**
 * Created by hamza on 25/01/18.
 */
var User: UserInformation? = null
var jsonip: JsonIp? = null

class getDataLoginFromApi() : AsyncTask<Void, Void, String>() {


    var context: Context? = null
    var url: String? = null

    constructor(context: Context, url: String) : this() {
        this.context = context
        this.url = url
    }

    lateinit var progressDialog: ProgressDialog
    var hasInternet = false

    override fun onPreExecute() {
        super.onPreExecute()
        progressDialog = ProgressDialog(context)
        progressDialog.setMessage("Downloading Data ...")
        progressDialog.setCancelable(false)
        progressDialog.show()
    }

    override fun doInBackground(vararg p0: Void?): String {

        if (isNetworkAvailable()) {
            hasInternet = true
            val client = OkHttpClient()
            val request = Request.Builder().url(url).build()
            val response = client.newCall(request).execute()
            return response.body()?.string().toString()
        } else {
            return ""
        }
    }

    @SuppressLint("NewApi")
    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        progressDialog.dismiss()

        if (hasInternet) {
            try {
                if (url == ENISO_URL) {
                    var gson = Gson()
                    val resultArrays = JSONObject(result)
                    var r = resultArrays.getJSONObject("$1")
                    User = gson.fromJson(r.toString(), UserInformation::class.java)
                }
                var gson = Gson()
                jsonip = gson.fromJson(result.toString(), JsonIp::class.java)

            } catch (e: JSONException) { }

        }
    }
    fun isNetworkAvailable(): Boolean {
        val connectivityManager = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}


//                val url = "http://eniso.info/ws/login/toto?password=toto1243"

// DoInBackgorund
/*
//
//        var ulr = URL ("http://eniso.info/ws/login/toto?password=toto1243")
//        var jsonArray :JSONArray
//        var urlConnection = ulr.openConnection()
//        var inputStrem = InputStreamReader(urlConnection.getInputStream())
//        var buffer = BufferedReader(inputStrem)
//        var ligne =""
//        ligne=buffer.readLine()
//        while (ligne !=null){
//        jsonArray= JSONArray(ligne)
//            ligne=buffer.readLine()
//        }
//        Log.e("resultJson", jsonArray)
 */