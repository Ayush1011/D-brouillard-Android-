package com.example.lpuactivity.ui.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.lpuactivity.R
import com.example.lpuactivity.api.Api
import com.example.lpuactivity.api.RetrofitClient
import com.example.lpuactivity.sevice.Builder
import com.example.lpuactivity.sevice.Dservice
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_home.*
import com.google.gson.annotations.SerializedName
import org.jetbrains.anko.doAsync
import java.net.URL
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    private val final:String="example.txt"

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        homeViewModel.text.observe(viewLifecycleOwner, Observer {

        })
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)




        fetchJson()




    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        onResume()

        LoadData()
        homefragmentrecycle.setOnClickListener {

            Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show()

        }
    }

        fun LoadData()

        {
            val sharedPreferences: SharedPreferences =
                activity?.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)!!
            val savedString = sharedPreferences.getString("STRING_KEY",null)
            val savedBoolean = sharedPreferences.getBoolean("BOOLEAN_KEY",false)


            Toast.makeText(context, savedString, Toast.LENGTH_SHORT).show()

        }






    override fun onResume() {
        super.onResume()

        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }






    fun fetchJson(){
//
//        var url= URL("https://finepointmobile.com/data/products.json").readText()
//       var request=Request.Builder().url(url).build()
//        var client = OkHttpClient()
//        client.newCall(request).enqueue(object :Callback{
//            override fun onFailure(call: Call, e: IOException) {
//                println("Failed to fetch")
//            }
//
//            override fun onResponse(call: Call, response: Response) {
//                val body = response?.body?.string()
//
//
//               println(body)
//
//                val gson = GsonBuilder().create()
//
//
//
//                var homefeed = gson.fromJson(body,Homefeed::class.java)
//
//
//
//
//                val handler = Handler(Looper.getMainLooper())
//                handler.post {
//                    homefragmentrecycle.adapter = Home_Adapter(homefeed)
//
//                }
//
//
//
//
//
//
//
//
//            }
//
//        })






        doAsync {
//            var url= URL("http://192.168.1.2:3000/tasks").readText()




            val handler = Handler(Looper.getMainLooper())
            handler.post {


                val Dservice = Builder.buildService(Dservice::class.java)
                val requestCall = Dservice.getTask()
                requestCall.enqueue(object :Callback<List<Video>>{
                    override fun onResponse(
                        call: Call<List<Video>>,
                        response: Response<List<Video>>
                    ) {

                        if(response.isSuccessful){
                            val dservice = response.body()!!
                            println(dservice)
                            homefragmentrecycle.layoutManager = GridLayoutManager(activity,2)

                            homefragmentrecycle.adapter = Home_Adapter(dservice)
                        }
                    }

                    override fun onFailure(call: Call<List<Video>>, t: Throwable) {
                       print("lolllllllllllllllllllllllllllllllllll")
                    }

                })


//                val product = Gson().fromJson(url,Array<Video>::class.java).toList()
//                homefragmentrecycle.layoutManager = GridLayoutManager(activity,2)
//
//                homefragmentrecycle.adapter = Home_Adapter(product)

            }





            }








        }













    }


    data class Homefeed(val videos:Array<Video>)

    data class Video(
        @SerializedName("TaskTitle")
        val title:String,
        @SerializedName("TaskPic")
        val photos:String,
        @SerializedName("TaskDis")
        val description:String,
        @SerializedName("Price")
        val price:String,
        @SerializedName("PostBy")
        val postby:String,
        @SerializedName("Upvote")
        val upvote:Int,
        @SerializedName("Email")
        val email:String,
        @SerializedName("IsDone")
        val isdone:Boolean,
        @SerializedName("_id")
        val id:String




    )




