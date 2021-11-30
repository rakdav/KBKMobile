package com.example.kbk.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kbk.*
import com.example.kbk.model.KBKEvent
import com.example.kbk.model.KBKEvents
import com.example.kbk.network.Api
import com.example.kbk.network.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var adapter: RecyclerView.Adapter<*>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val rec: RecyclerView = root.findViewById(R.id.home_recycler_view)
        rec.setHasFixedSize(true)
        rec.setLayoutManager(LinearLayoutManager(getActivity()))
        val retrofit = Retrofit.Builder()
            .baseUrl(ServiceBuilder.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service: Api = retrofit.create(Api::class.java)

        rec.layoutManager = LinearLayoutManager(context)


        //crating an arraylist to store users using the data class user
        //val eve = ArrayList<KBKEvent>()

        val call: Call<KBKEvents> = service.allevents()

        call.enqueue(object : Callback<KBKEvents> {
            override fun onResponse(call: Call<KBKEvents>, response: Response<KBKEvents>)
            {
                var list:ArrayList<KBKEvent> = response.body()!!.events
                rec.adapter = EventsAdapter(list)
/*                adapter = EventsAdapter(response.body()!!.events)
                rec.setAdapter(adapter)*/
            }

            override fun onFailure(call: Call<KBKEvents>, t: Throwable?) {
                Log.d("adapter",t.toString())
            }
        })


/*
        //creating our adapter
        val adapter = EventsAdapter(eve)

        //now adding the adapter to recyclerview
        rec.adapter = adapter*/
        /*      val textView: TextView = root.findViewById(R.id.text_home)
              homeViewModel.text.observe(viewLifecycleOwner, Observer {
                  textView.text = it
              })*/
        return root
    }
}