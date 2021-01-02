package com.example.lpuactivity.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.lpuactivity.R
import com.example.lpuactivity.Retrofit_requests.api.sevice.Builder
import com.example.lpuactivity.Retrofit_requests.api.sevice.Dservice
import com.example.lpuactivity.models.Video
import com.example.lpuactivity.util.access
import io.supercharge.shimmerlayout.ShimmerLayout
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


var skeletonLayout: LinearLayout? = null
var shimmer: ShimmerLayout? = null
var inflater: LayoutInflater? = null

class HomeFragment : Fragment() {
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

        fetchJson()  //fetch tasks


//        homefragmentrecycle.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                if (dy > 0 && nav_view.isShown) {
//                    nav_view.visibility = View.GONE
//                } else if (dy < 0) {
//                    nav_view.visibility = View.VISIBLE
//                }
//            }
//
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//            }
//        })

//        waveHeader?.setStartColor(R.color.colorPrimary);
//        waveHeader?.setCloseColor(R.color.colorPrimaryDark);
//        waveHeader?.setColorAlpha(.5f);
//
//        waveHeader?.setWaveHeight(50);
//        waveHeader?.setGradientAngle(360);
//        waveHeader?.setProgress(.8f);
//        waveHeader?.setVelocity(1f);
//        waveHeader?.setScaleY(-1f);
//
//        waveHeader?.setWaves("PairWave");
//
//        waveHeader?.start();
//        waveHeader?.stop();
//        waveHeader?.isRunning();



        val radius = 70 // corner radius, higher value = more rounded

        val margin = 10

//        val gifUrl = "https://firebasestorage.googleapis.com/v0/b/virtusa-58806.appspot.com/o/ezgif.com-gif-maker.gif?alt=media&token=8edf6c7f-8c8d-45be-9ceb-981cb956f4f8"
//        Glide
//            .with(context!!)
//            .load(gifUrl)
//            .transform(RoundedCorners(radius))
//
//
//            .into(home_image)



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        onResume()

        skeletonLayout_show.visibility=View.VISIBLE
        shimmer_view_container.startShimmerAnimation();

        if(home_ui_no_task==null){
            home_ui_no_task?.text="no task"
        }

        homefragmentrecycle.setOnClickListener {

            Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show()

        }
    }







    override fun onResume() {
        super.onResume()

        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }


    fun fetchJson(){

        // for fragment do run on ui thread

        doAsync {

            val handler = Handler(Looper.getMainLooper())
            handler.post {


                val Dservice = Builder.buildService(Dservice::class.java)

                val requestCall = Dservice.getTask(access!!)
                requestCall.enqueue(object : Callback<List<Video>> {
                    override fun onResponse(
                        call: Call<List<Video>>,
                        response: Response<List<Video>>
                    ) {

                        if (response.isSuccessful) {


                            if (response.body()!!.count() <= 0) {

                                home_ui_no_task?.text = "No Task available in your region!! "
                            } else {
                                skeletonLayout_show?.visibility = View.GONE

                                shimmer_view_container?.stopShimmerAnimation();
                            }
                            val dservice = response.body()!!.reversed()
                            println(dservice)
                            homefragmentrecycle?.layoutManager =
                                GridLayoutManager(activity, 2) // homeadapter grid layout

                            homefragmentrecycle?.adapter = Home_Adapter(dservice)

                        }
                    }

                    override fun onFailure(call: Call<List<Video>>, t: Throwable) {
                        Toast.makeText(context, "Unable to load tasks", Toast.LENGTH_SHORT).show()
                        skeletonLayout_show.visibility = View.GONE
                        shimmer_view_container.stopShimmerAnimation();


                    }

                })
            }
        }

        }


    }





