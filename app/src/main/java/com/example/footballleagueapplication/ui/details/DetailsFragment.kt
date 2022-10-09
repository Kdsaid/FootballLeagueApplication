package com.example.footballleagueapplication.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.footballleagueapplication.data.api.ApiHelperImpl
import com.example.footballleagueapplication.data.api.RetrofitBuilder
import com.example.footballleagueapplication.data.models.team_details_model.Squad
import com.example.footballleagueapplication.data.models.team_details_model.TeamDetailsModels
import com.example.footballleagueapplication.databinding.DetailsFragmentBinding
import com.example.footballleagueapplication.utils.*

class DetailsFragment : Fragment() {
    private var _binding: DetailsFragmentBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {


        _binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val safeArgs =
                DetailsFragmentArgs.fromBundle(
                    it
                )

            displayHomeData(safeArgs.playerId)

        }

    }

    private fun displayHomeData(playerId: Int) {
         val teamDetailsViewModel by lazy {
            ViewModelProvider(
                this, ViewModelFactory(ApiHelperImpl((RetrofitBuilder.apiService)),  playerId)
            )[TeamDetailsViewModel::class.java]
        }

        lifecycleScope.launchWhenStarted {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                teamDetailsViewModel.teamDetails.collect {
                    when (it.status) {
                        Status.SUCCESS -> it.data?.let { getData ->
                            binding.loader.progressBar.hide()
                            setAdsData(getData)

                        }
                        Status.ERROR -> {
                            binding.loader.progressBar.hide()
                            context?.toast("Something went wrong, try later")
                        }
                        Status.LOADING -> {
                            binding.loader.progressBar.show()
                        }
                    }
                }
            }
        }

    }

    private fun setAdsData(teamDetails: TeamDetailsModels?) {
        binding.tvTeamName.text = teamDetails!!.name
        binding.tvAddress.text = teamDetails.address
        binding.tvPhone.text = teamDetails.phone
        binding.tvEmail.text = teamDetails.email
        binding.tvClubColors.text = teamDetails.clubColors
        binding.tvVenue.text = teamDetails.venue
        binding.logo.loadImage(teamDetails.crestUrl)
        binding.rvTeamPlayer.apply {
            adapter = TeamPlayerAdapter((teamDetails.squad as ArrayList<Squad>))
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}


