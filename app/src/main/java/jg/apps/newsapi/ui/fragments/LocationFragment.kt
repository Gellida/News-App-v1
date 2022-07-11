package jg.apps.newsapi.ui.fragments


import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import jg.apps.newsapi.R
import jg.apps.newsapi.databinding.FragmentLocationBinding
import jg.apps.newsapi.ui.MainViewModel
import okhttp3.internal.wait


class LocationFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentLocationBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding = FragmentLocationBinding.inflate(inflater, container, false)

        initUI()
        return binding.root
    }

    @SuppressLint("ResourceType")
    private fun initUI() {

        activity?.let {

            viewModel.let {

                binding.button.setOnClickListener {

                    if (isLocationPermissionGranted()) {
                        findNavController().navigate(R.id.action_locationFragment_to_newsFragment)
                        Toast.makeText(requireContext(), "Permisos concedidos", Toast.LENGTH_SHORT)
                            .show()

                    } else {

                        requestLocationPermission()
                        Thread.sleep(3000)
                        if (isLocationPermissionGranted()) {
                            findNavController().navigate(R.id.action_locationFragment_to_newsFragment)
                        } else {
                            binding.button.isInvisible = true
                            binding.tvPermissionDenied.isInvisible = false
                        }


                    }

                }
            }
        }


    }

    private fun isLocationPermissionGranted() = ContextCompat.checkSelfPermission(
        requireContext(),
        android.Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    private fun requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
        ) {
            Toast.makeText(requireContext(), "Tendrás que aceptar los permisos", Toast.LENGTH_SHORT)
                .show()
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION),
                0
            )
        }

    }


}