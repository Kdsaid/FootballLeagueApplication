package com.example.footballleagueapplication.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.footballleagueapplication.R
import com.example.footballleagueapplication.data.network.responses.leagues_model.Competition
import com.example.footballleagueapplication.hide
import com.example.footballleagueapplication.show
import com.example.footballleagueapplication.view.view_model.LeaguesViewModel
import com.example.footballleagueapplication.view.adapter.LeaguesDataAdapter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.loader.*

class HomeFragment : Fragment() {
    lateinit var teamsViewModel: LeaguesViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        progressBar.show()

        displayHomeData()


    }

    private fun displayHomeData() {
        teamsViewModel = ViewModelProvider(this).get(LeaguesViewModel::class.java)
        activity?.let {
            teamsViewModel.getAllTeam().observe(it, Observer<List<Competition>> { teamDetails ->
                setAdsData(teamDetails)


                Log.e("sadsad",""+Gson().toJson(teamDetails))
            })
        }
        progressBar.hide()
    }

    private fun setAdsData(competition: List<Competition>?) {
        leaguesRecycler.apply {
            adapter = activity?.let {
                LeaguesDataAdapter((competition ), it)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        teamsViewModel = ViewModelProvider(this).get(LeaguesViewModel::class.java)

    }
}



