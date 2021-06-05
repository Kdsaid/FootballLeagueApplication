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
import com.example.footballleagueapplication.data.models.teams_model.Team
import com.example.footballleagueapplication.utils.Status
import com.example.footballleagueapplication.utils.hide
import com.example.footballleagueapplication.utils.show
import com.example.footballleagueapplication.utils.toast
import com.example.footballleagueapplication.view.view_model.TeamsViewModel
import com.example.footballleagueapplication.view.adapter.TeamDataAdapter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_about.*
import kotlinx.android.synthetic.main.loader.*

class AboutFragment : Fragment(R.layout.fragment_about) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val safeArgs =
                AboutFragmentArgs.fromBundle(
                    it
                )

            displayHomeData(safeArgs.leagueId)

        }

    }
    private fun displayHomeData(leagueId: Int) {
       val teamsViewModel = ViewModelProvider(this).get(TeamsViewModel::class.java)
        activity?.let {
            teamsViewModel.
            getTeam(leagueId).observe(requireActivity(), Observer {
                when (it.status) {
                    Status.SUCCESS -> it.data?.let { getData ->
                        progressBar.hide()
                        setAdsData(getData.teams)
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
    }}

    private fun setAdsData(teamDetails: List<Team>?) {

        rv_team.apply {
            adapter = activity?.let {
                TeamDataAdapter((teamDetails as ArrayList<Team>), it)
            }
        }

    }
}