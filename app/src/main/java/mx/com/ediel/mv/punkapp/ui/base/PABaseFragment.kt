package mx.com.ediel.mv.punkapp.ui.base

import androidx.fragment.app.Fragment
import mx.com.ediel.mv.punkapp.ui.common.LoaderFragment

abstract class PABaseFragment: Fragment() {
    private val loader by lazy {
        LoaderFragment()
    }
    fun showLoader(){
        if (!loader.isAdded){
            loader.show(parentFragmentManager, LoaderFragment.TAG)
        }
    }

    fun hideLoader(){
        loader?.let {
            it.dismiss()
        }
    }
}