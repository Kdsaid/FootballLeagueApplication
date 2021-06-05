package com.example.footballleagueapplication.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.footballleagueapplication.R
import com.example.footballleagueapplication.data.models.leagues_model.Competition
import com.example.footballleagueapplication.utils.*

import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.loader.*

class HomeFragment : Fragment(R.layout.home_fragment) {
    private lateinit var teamsViewModel: LeaguesViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayHomeData()
    }


    private fun displayHomeData() {
        teamsViewModel = ViewModelProvider(requireActivity()).get(LeaguesViewModel::class.java)
        teamsViewModel.getLeagues().observe(requireActivity(), Observer {
            when (it.status) {
                Status.SUCCESS -> it.data?.let { getData ->
                    progressBar.hide()
                    setAdsData(getData.competitions)
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


    private fun setAdsData(competition: List<Competition>?) {
        leaguesRecycler.apply {

            adapter = LeaguesDataAdapter(competition) { view, id ->
                val nextAction = HomeFragmentDirections.nextAction()
                nextAction.leagueId = id
                Navigation.findNavController(view).navigate(nextAction)
            }
            animateItems()
            scheduleLayoutAnimation()
        }
    }
}









