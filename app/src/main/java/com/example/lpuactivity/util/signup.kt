package com.example.lpuactivity.util

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.lpuactivity.R
import com.example.lpuactivity.Retrofit_requests.api.RetrofitClient
import com.example.lpuactivity.models.accessToken
import com.example.lpuactivity.models.defaultResponse
import kotlinx.android.synthetic.main.activity_login_screen.*
import kotlinx.android.synthetic.main.activity_signup.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class signup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)


        supportActionBar?.hide()
//        LoadData() //load data

        signup_main_button.setOnClickListener {
            email = signup_email.text.toString().trim()
            var Password = signup_password.text.toString().trim()
            var names=signup_name.text.toString().trim()
            var contactNo=signup_number.text.toString().toLong()


            if (email!!.isEmpty()) {
                signup_email.error = "Email required"
                signup_email.requestFocus()
                return@setOnClickListener
            }
            if (Password.isEmpty()) {
                signup_password.error = "Password required"
                signup_password.requestFocus()
                return@setOnClickListener
            }
            if (names.isEmpty()) {
                signup_name.error = "name required"
                signup_name.requestFocus()
                return@setOnClickListener
            }
            val maincontact=contactNo.toString()
            if (maincontact.isEmpty()) {
                signup_number.error = "Phn.no required"
                signup_number.requestFocus()
                return@setOnClickListener
            }
            RetrofitClient.instance.signupuser(
                names,
                email,
                contactNo,
                Password,
            ).enqueue(object : Callback<accessToken> {
                override fun onResponse(
                    call: Call<accessToken>,
                    response: Response<accessToken>
                ) {


                    println(response.body())

                         val intent = Intent(this@signup, LoginScreen::class.java)

                        intent.putExtra("access", access)
                        intent.putExtra("BOOLEAN_KEY", true)
                        intent.putExtra("email", email)
                        intent.putExtra("password",Password)

                        startActivity(intent)





                    }




                override fun onFailure(call: Call<accessToken>, t: Throwable) {
                    println("hiiii"+t.localizedMessage)
                    Toast.makeText(this@signup, "Failed to sign up", Toast.LENGTH_SHORT).show()
                }

            }

            )

        }

    }


    fun saveData() {
        val text: String = "true"

        val sharedPreferences: SharedPreferences = getSharedPreferences(
            "sharedPrefs",
            Context.MODE_PRIVATE
        )
        val editor = sharedPreferences.edit()
        editor.apply {
            putString("email", email)
            putBoolean("BOOLEAN_KEY", true)
            putString("accessToken", access)

        }.apply()

        Toast.makeText(this@signup, "saved ", Toast.LENGTH_LONG).show()


    }



}







