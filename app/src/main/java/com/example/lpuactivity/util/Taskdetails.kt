package com.example.lpuactivity.util

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.biometric.BiometricPrompt
import com.example.lpuactivity.R
import com.example.lpuactivity.Retrofit_requests.api.RetrofitClient
import com.example.lpuactivity.models.defaultResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main2.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import java.util.concurrent.Executors



var id=""
class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        supportActionBar?.hide()


        println(intent.getStringExtra("Price"))

        var intent = intent
        var title=intent.getStringExtra("Title")

        val image=intent.getStringExtra("Image")
        val postby=intent.getStringExtra("Postby")
        val price: String? ="₹ "+intent.getStringExtra("Price")
        val description=intent.getStringExtra("Description")
        id = intent.getStringExtra("Id").toString()


        if (postby != null) {
            detail_name.text=postby.toLowerCase(Locale.getDefault())?.capitalize(Locale.ROOT)
        }
        if (title != null) {
            mainactivity2textview.text=title.toLowerCase(Locale.getDefault())?.capitalize(Locale.ROOT)
        }
        detail_price.text=price
        detail_description.text=description

        Picasso.get()
            .load(image)
            .fit()
            .centerCrop()
            .into(detailimage)



        detail_button.setOnClickListener {
            FingerPrintManagementUtil(this, Executors.newSingleThreadExecutor(), callback).showBiometricPrompt()

        }




    }



    private val callback = object : BiometricPrompt.AuthenticationCallback(){
        override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
            super.onAuthenticationError(errorCode, errString)
            Log.e("MainActivity", errString.toString())
        }

        override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
            super.onAuthenticationSucceeded(result)
            runOnUiThread {
                Toast.makeText(this@MainActivity2, id, Toast.LENGTH_LONG).show()
                RetrofitClient.instance.updateTask(id).enqueue(object: Callback<defaultResponse>{
                    override fun onResponse(
                        call: Call<defaultResponse>,
                        response: Response<defaultResponse>
                    ) {

                        Toast.makeText(this@MainActivity2, "Task Accepted", Toast.LENGTH_LONG).show()


                    }

                    override fun onFailure(call: Call<defaultResponse>, t: Throwable) {
                        Toast.makeText(this@MainActivity2, "Task failed to Accept", Toast.LENGTH_LONG).show()

                    }

                })

            }
        }

        override fun onAuthenticationFailed() {
            super.onAuthenticationFailed()
            Log.e("MainActivity", " Failed login")
        }
    }
}




