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
import com.example.footballleagueapplication.data.network.responses.teams_model.Team
import com.example.footballleagueapplication.hide
import com.example.footballleagueapplication.show
import com.example.footballleagueapplication.view.view_model.TeamsViewModel
import com.example.footballleagueapplication.view.adapter.TeamDataAdapter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_about.*
import kotlinx.android.synthetic.main.loader.*

class AboutFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.let {
            val safeArgs =
                AboutFragmentArgs.fromBundle(
                    it
                )

            displayHomeData(safeArgs.leagueId)

        }

    }
    private fun displayHomeData(leagueId: Int) {
        progressBar.show()
       val teamsViewModel = ViewModelProvider(this).get(TeamsViewModel::class.java)
        activity?.let {
            teamsViewModel.getAllTeam(leagueId).observe(it, Observer<List<Team>> { teamDetails ->
                setAdsData(teamDetails)


                Log.e("sadsad",""+ Gson().toJson(teamDetails))
            })
            progressBar.hide()
        }
    }

    private fun setAdsData(teamDetails: List<Team>?) {

        rv_team.apply {
            adapter = activity?.let {
                TeamDataAdapter((teamDetails as ArrayList<Team>), it)
            }
        }

    }
}