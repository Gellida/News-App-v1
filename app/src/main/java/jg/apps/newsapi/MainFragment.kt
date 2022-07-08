package jg.apps.newsapi



import android.content.pm.PackageManager
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import jg.apps.newsapi.databinding.FragmentMainBinding


class MainFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentMainBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding = FragmentMainBinding.inflate(inflater, container, false)

        initUI()
        return binding.root
    }

    private fun initUI() {

        activity?.let {

            viewModel.let {
                binding.button.setOnClickListener {

                    if (isLocationPermissionGranted()){
                        Toast.makeText(requireContext(),"Permisos concedidos",Toast.LENGTH_SHORT).show()
                    }else{
                        binding.button.isInvisible = true
                        binding.tvPermissionDenied.isInvisible = false
                        requestLocationPermission()
                    }



                }
            }


        }

    }
    private fun isLocationPermissionGranted() = ContextCompat.checkSelfPermission(
        requireContext(),
        android.Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    private fun requestLocationPermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION)
        ){
               Toast.makeText(requireContext(),"Tendrás que aceptar los permisos",Toast.LENGTH_SHORT).show()
        }else{
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION),
               0)
        }

    }

}