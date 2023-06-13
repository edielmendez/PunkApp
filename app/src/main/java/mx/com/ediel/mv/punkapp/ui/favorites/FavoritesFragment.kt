package mx.com.ediel.mv.punkapp.ui.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.com.ediel.mv.punkapp.R
import mx.com.ediel.mv.punkapp.databinding.FavoritesFragmentBinding
import mx.com.ediel.mv.punkapp.databinding.LoginFragmentBinding


class FavoritesFragment : Fragment() {
    private var _binding: FavoritesFragmentBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FavoritesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        val TAG = FavoritesFragment::class.java.simpleName
        @JvmStatic
        fun newInstance() = FavoritesFragment()
    }
}