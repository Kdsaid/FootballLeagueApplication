package com.example.footballleagueapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.footballleagueapplication.R
import com.example.footballleagueapplication.data.models.leagues_model.Competition
import com.example.footballleagueapplication.databinding.HomeFragmentBinding
import com.example.footballleagueapplication.utils.*


class HomeFragment : Fragment() {
    private lateinit var teamsViewModel: LeaguesViewModel

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayHomeData()
    }


    private fun displayHomeData() {
        teamsViewModel = ViewModelProvider(requireActivity()).get(LeaguesViewModel::class.java)
        teamsViewModel.getLeagues().observe(requireActivity(), {
            when (it.status) {
                Status.SUCCESS -> it.data?.let { getData ->
                    binding.loader.progressBar.hide()
                    setAdsData(getData.competitions)
                }
                Status.ERROR -> {
                    binding.loader.progressBar.hide()
                    context?.toast("Something went wrong, try later")
                }
                Status.LOADING -> {
                    binding.loader.progressBar.show()
                }
            }
        })
    }


    private fun setAdsData(competition: List<Competition>?) {
        binding.leaguesRecycler.apply {

            adapter = LeaguesDataAdapter(competition) { view, id ->
                val nextAction = HomeFragmentDirections.nextAction()
                nextAction.leagueId = id
                Navigation.findNavController(view).navigate(nextAction)
            }
            animateItems()
            scheduleLayoutAnimation()
        }
    }

    override fun onDestroy() {
        _binding = null

        super.onDestroy()


    }
}









