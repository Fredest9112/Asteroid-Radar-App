package com.udacity.asteroidradar.main

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding
import com.udacity.asteroidradar.model.AsteroidStatus
import com.udacity.asteroidradar.model.MainViewModel
import com.udacity.asteroidradar.model.MainViewModelFactory

class MainFragment : Fragment() {

    private lateinit var asteroidAdapter: AsteroidAdapter

    private val viewModel: MainViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(
            this,
            MainViewModelFactory(activity.application)
        )[MainViewModel::class.java]
    }
    private var binding: FragmentMainBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentMainBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        setHasOptionsMenu(true)
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            mainViewModel = viewModel
            lifecycleOwner = viewLifecycleOwner
        }

        binding?.asteroidRecycler?.adapter = AsteroidAdapter(AsteroidAdapter.OnClickListener {
            viewModel.displayAsteroidDetails(it)
        }).apply { asteroidAdapter = this }

        viewModel.goToAsteroidDetails.observe(viewLifecycleOwner) {
            if (it != null) {
                findNavController().navigate(MainFragmentDirections.actionShowDetail(it))
                viewModel.displayAsteroidComplete()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.updateAsteroidStatus(
            when(item.itemId){
                R.id.show_today_asteroids -> AsteroidStatus.DAY
                R.id.show_week_asteroids -> AsteroidStatus.WEEK
                else -> AsteroidStatus.ALL
            }
        )
        viewModel.asteroids.observe(viewLifecycleOwner){
            asteroidAdapter.submitList(it)
        }
        return true
    }
}
