package com.example.footballleagueapplication.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.footballleague.data.network.responses.team_details_model.Squad
import com.example.footballleague.data.network.responses.team_details_model.TeamDetailsModels
import com.example.footballleagueapplication.R
import com.example.footballleagueapplication.hide
import com.example.footballleagueapplication.loadImage
import com.example.footballleagueapplication.show
import com.example.footballleagueapplication.view.adapter.TeamPlayerAdapter
import com.example.footballleagueapplication.view.view_model.TeamDetailsViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.details_fragment.*
import kotlinx.android.synthetic.main.loader.*

class DetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.details_fragment, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            val safeArgs =
                DetailsFragmentArgs.fromBundle(
                    it
                )

            displayHomeData(safeArgs.playerId)

        }

    }

    private fun displayHomeData(leagueId: Int) {
        progressBar.show()
        val teamDetailsViewModel = ViewModelProvider(this).get(TeamDetailsViewModel::class.java)
        activity?.let {
            teamDetailsViewModel.getDetailsTeam(leagueId)
                .observe(it, Observer<TeamDetailsModels> { teamDetails ->
                    setAdsData(teamDetails)


                    Log.e("sadsad", "" + Gson().toJson(teamDetails))
                })
            progressBar.hide()
        }
    }

    private fun setAdsData(teamDetails: TeamDetailsModels?) {
        tvTeamName.text = teamDetails!!.name
        tvAddress.text = teamDetails.address
        tvPhone.text = teamDetails.phone
        tvEmail.text = teamDetails.email
        tvClubColors.text = teamDetails.clubColors
        tvVenue.text = teamDetails.venue
        logo.loadImage(teamDetails.crestUrl)


        rv_team_player.apply {
            adapter = activity?.let {
                TeamPlayerAdapter((teamDetails.squad as ArrayList<Squad>), it)

            }
        }
    }
}


