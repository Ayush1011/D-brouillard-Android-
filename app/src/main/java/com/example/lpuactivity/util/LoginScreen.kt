package com.example.lpuactivity.util

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lpuactivity.R
import com.example.lpuactivity.Retrofit_requests.api.RetrofitClient
import com.example.lpuactivity.models.defaultResponse
import com.example.lpuactivity.ui.notifications.NotificationsFragment
import kotlinx.android.synthetic.main.activity_login_screen.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

var email:String?=""
class LoginScreen : AppCompatActivity() {
    private val final:String="example.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        LoadData() //load data

        signup_button.setOnClickListener {
             email =username.text.toString().trim()
            val Password=password.text.toString().trim()


            if(email!!.isEmpty())
            {
                username.error="Email required"
                username.requestFocus()
                return@setOnClickListener
            }
            if(Password.isEmpty())
            {
               password.error="Email required"
                password.requestFocus()
                return@setOnClickListener
            }

            RetrofitClient.instance.createUser(
                email,
                Password,
            ).enqueue(object : Callback<defaultResponse> {
                override fun onResponse(
                    call: Call<defaultResponse>,
                    response: Response<defaultResponse>
                ) {

                    println(response.body())
                    if (response.body() != null) {
                        saveData()
//                        val fragment = NotificationsFragment()
//                        fragment.getuser()
                        val intent = Intent(this@LoginScreen, MainActivity::class.java)
                        startActivity(intent)

                    } else {
                        Toast.makeText(this@LoginScreen, "Fail", Toast.LENGTH_SHORT).show()

                    }


                }

                override fun onFailure(call: Call<defaultResponse>, t: Throwable) {
                    Toast.makeText(this@LoginScreen, "Fail", Toast.LENGTH_SHORT).show()
                }

            }

            )

        }

    }








        fun saveData()
        {
            val text:String="true"

            val sharedPreferences:SharedPreferences=getSharedPreferences(
                "sharedPrefs",
                Context.MODE_PRIVATE
            )
            val editor=sharedPreferences.edit()
            editor.apply{
                putString("email", email)
                putBoolean("BOOLEAN_KEY", true)

            }.apply()
            Toast.makeText(this@LoginScreen, "saved ", Toast.LENGTH_LONG).show()



        }




    fun LoadData()

    {
        val sharedPreferences: SharedPreferences =
            getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)!!

//        val preferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
//        val editor = preferences.edit()
//        editor.clear()
//        editor.apply()
//        finish()
        val savedString = sharedPreferences.getString("STRING_KEY", null)
        email = sharedPreferences.getString("email", "fail")?.toString()
        val savedBoolean = sharedPreferences.getBoolean("BOOLEAN_KEY", false)


        println(savedBoolean)
        if(savedBoolean){

            val intent = Intent(this@LoginScreen, MainActivity::class.java)
            startActivity(intent)
        }else{
            println(savedBoolean)
        }

    }







}