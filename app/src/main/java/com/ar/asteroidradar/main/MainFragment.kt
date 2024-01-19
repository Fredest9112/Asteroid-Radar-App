package com.ar.asteroidradar.main

import android.app.Fragment
import android.os.Build.VERSION_CODES.R
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ar.asteroidradar.R
import com.ar.asteroidradar.data.Date
import com.ar.asteroidradar.databinding.FragmentMainBinding
import com.ar.asteroidradar.model.AsteroidStatus
import com.ar.asteroidradar.model.MainViewModel
import com.ar.asteroidradar.model.MainViewModelFactory

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
            asteroidRecycler.adapter = AsteroidAdapter(AsteroidAdapter.OnClickListener {
                viewModel.displayAsteroidDetails(it)
            }).apply { asteroidAdapter = this }
        }

        viewModel.apply {
            goToAsteroidDetails.observe(viewLifecycleOwner) {
                if (it != null) {
                    findNavController().navigate(MainFragmentDirections.actionShowDetail(it))
                    viewModel.displayAsteroidComplete()
                }
            }
            asteroids.observe(viewLifecycleOwner){
                asteroidAdapter.submitList(it)
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
        return true
    }
}
