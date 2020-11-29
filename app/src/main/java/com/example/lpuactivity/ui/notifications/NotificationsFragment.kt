package com.example.lpuactivity.ui.notifications

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.lpuactivity.R
import com.example.lpuactivity.api.Api
import com.example.lpuactivity.api.RetrofitClient
import com.example.lpuactivity.models.defaultResponse
import com.example.lpuactivity.sevice.Builder
import com.example.lpuactivity.sevice.Dservice
import com.example.lpuactivity.ui.home.Home_Adapter
import com.example.lpuactivity.ui.home.Video
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_notifications.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.content.SharedPreferences
import android.os.Looper
import com.example.lpuactivity.email
import com.example.lpuactivity.models.Userinfo
import android.os.Handler
import org.jetbrains.anko.doAsync
import java.util.*


class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {

        })
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        getuser()
//        println(email)

//        buttonSignUp.setOnClickListener {
////            val email=editTextEmail.text.toString().trim()
////            val Password=editTextPassword.text.toString().trim()
////
////
////            if(email.isEmpty())
////            {
////                editTextEmail.error="Email required"
////                editTextEmail.requestFocus()
////                return@setOnClickListener
////            }
////            if(Password.isEmpty())
////            {
////                editTextPassword.error="Email required"
////                editTextPassword.requestFocus()
////                return@setOnClickListener
////            }
//
//            RetrofitClient.instance.createUser(
//                email,
//                Password,
//            ).enqueue(object:Callback<defaultResponse>{
//                override fun onResponse(
//                    call: Call<defaultResponse>,
//                    response: Response<defaultResponse>
//                ) {
//                    Toast.makeText(context,"done",Toast.LENGTH_SHORT).show()
//
//
//                }
//
//                override fun onFailure(call: Call<defaultResponse>, t: Throwable) {
//                    Toast.makeText(context,"Fail",Toast.LENGTH_SHORT).show()
//                }
//
//            }
//
//            )
//
//        }



    }




    fun getuser() {
        doAsync {
            val handler = Handler(Looper.getMainLooper())
            handler.post {
                val Dservice = Builder.buildService(Dservice::class.java)
                val requestCall = Dservice.getUser(email)

                requestCall.enqueue(object : Callback<Userinfo> {
                    override fun onResponse(
                        call: Call<Userinfo>,
                        response: Response<Userinfo>
                    ) {
                 
                        val dservice = response.body()!!
                        println(response.body()!!)
                        if (response.isSuccessful) {


                            profile_username.text =
                                dservice.Firstname.toLowerCase(Locale.getDefault()).capitalize(
                                    Locale.ROOT)
                            profile_Number.text =
                                dservice.contactNo.toString().toLowerCase(Locale.getDefault())
                                    .capitalize(Locale.ROOT)
                            profile_Email.text = dservice.Email.toLowerCase(Locale.getDefault())
                                .capitalize(Locale.ROOT)


                        }
                    }

                    override fun onFailure(call: Call<Userinfo>, t: Throwable) {
                        print("not found")
                    }

                })

            }
        }
    }
}