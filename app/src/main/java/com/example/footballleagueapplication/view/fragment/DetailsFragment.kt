package com.example.footballleagueapplication.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.footballleagueapplication.data.models.team_details_model.Squad
import com.example.footballleagueapplication.data.models.team_details_model.TeamDetailsModels
import com.example.footballleagueapplication.R
import com.example.footballleagueapplication.utils.*
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
        val teamDetailsViewModel = ViewModelProvider(requireActivity()).get(TeamDetailsViewModel::class.java)
        activity?.let {
            teamDetailsViewModel.getTeamDetails(leagueId).observe(requireActivity(), Observer {
                when (it.status) {
                    Status.SUCCESS -> it.data?.let { getData ->
                        progressBar.hide()
                        setAdsData(getData)

                    }
                    Status.ERROR -> {
                        progressBar.hide()
                        context?.toast("Something went wrong, try later")
                    }
                    Status.LOADING -> {
                        progressBar.show()
                    }
                }
            })
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


