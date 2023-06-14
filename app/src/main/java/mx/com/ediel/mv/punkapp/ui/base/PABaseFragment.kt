package mx.com.ediel.mv.punkapp.ui.base

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import mx.com.ediel.mv.punkapp.R
import mx.com.ediel.mv.punkapp.ui.common.LoaderFragment

abstract class PABaseFragment: Fragment() {
    fun showLoader(){
        findNavController().navigate(R.id.loaderFragment)
    }

    fun hideLoader(){
        findNavController().popBackStack()
    }
}