package com.example.footballleagueapplication.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.footballleagueapplication.data.models.teams_model.Team
import com.example.footballleagueapplication.databinding.FragmentAboutBinding
import com.example.footballleagueapplication.utils.Status
import com.example.footballleagueapplication.utils.hide
import com.example.footballleagueapplication.utils.show
import com.example.footballleagueapplication.utils.toast

class AboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }


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
            teamsViewModel.getTeam(leagueId).observe(requireActivity(), Observer {
                when (it.status) {
                    Status.SUCCESS -> it.data?.let { getData ->
                        binding.loader.progressBar.hide()
                        setAdsData(getData.teams)
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
    }

    private fun setAdsData(teamDetails: List<Team>?) {

        binding.rvTeam.apply {
            adapter =
                TeamDataAdapter((teamDetails as ArrayList<Team>)) { view, id ->
                    val nextAction = AboutFragmentDirections.nextAction()
                    nextAction.playerId = id
                    Navigation.findNavController(view).navigate(nextAction)

                }
        }

    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()


    }
}