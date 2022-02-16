package com.example.sample.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.sample.adapters.weather.WeatherAdapter
import com.example.sample.databinding.FragmentWeatherBinding
import com.example.sample.model.WeatherItem
import com.example.sample.model.WeatherResponse
import com.example.sample.utils.ExtensionFunctions.hide
import com.example.sample.utils.ExtensionFunctions.show
import com.example.sample.utils.Resource
import com.example.sample.viewmodels.WeatherViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherFragment : Fragment() {

    private var _binding: FragmentWeatherBinding? = null
    private val binding: FragmentWeatherBinding get() = _binding!!

    private val viewModel by viewModels<WeatherViewModel>()

    private val weatherAdapter = WeatherAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewWeatherDetails.apply {
            setHasFixedSize(false)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = weatherAdapter
        }

        viewModel.weatherResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    binding.progressBar.show()
                }
                is Resource.Error -> {
                    binding.progressBar.hide()
                    Snackbar.make(
                        requireContext(),
                        binding.root,
                        "ERROR : ${it.message.toString()}",
                        Snackbar.LENGTH_INDEFINITE
                    ).setAction("RETRY") {
                        if (binding.txtInputQuery.text.toString().isNotEmpty())
                            viewModel.getWeatherResponse(binding.txtInputQuery.text.toString())
                    }.show()
                }
                is Resource.Success -> {
                    binding.progressBar.hide()
                    it.data?.let { response -> updateUI(response) }
                }
            }
        }

        binding.btnShowWeather.setOnClickListener {
            viewModel.getWeatherResponse(binding.txtInputQuery.text.toString())
        }

    }

    private fun updateUI(response: WeatherResponse) {

        binding.apply {
            txtCityName.text = response.location.name
            txtCountryName.text = response.location.country
            txtCondition.text = response.current.condition.condition
            txtTemperature.text = response.current.temperature.toString()
            txtDate.text = response.location.date.substring(0, 10)
            Glide.with(requireContext())
                .load("https:${response.current.condition.icon}")
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(this.imgWeather)
        }

        val list = listOf(
            WeatherItem(title = "Wind", value = "${response.current.windSpeed} kph"),
            WeatherItem(title = "Humidity", value = "${response.current.humidity} %"),
            WeatherItem(title = "Feels Like", value = "${response.current.feelsLike} C"),
            WeatherItem(
                title = "Coordinate",
                value = "${response.location.latitude}, ${response.location.longitude}"
            )
        )

        weatherAdapter.submitList(list)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}