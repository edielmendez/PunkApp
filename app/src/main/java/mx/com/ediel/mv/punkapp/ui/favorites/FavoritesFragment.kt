package mx.com.ediel.mv.punkapp.ui.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import mx.com.ediel.mv.punkapp.R
import mx.com.ediel.mv.punkapp.databinding.FavoritesFragmentBinding
import mx.com.ediel.mv.punkapp.ui.common.GenericAlertDialog


class FavoritesFragment : Fragment() {
    private var _binding: FavoritesFragmentBinding? = null
    private val binding get() = _binding!!

    lateinit var adapter: FavoritesScreenAdapter
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
        setUpAdapter()
        setUpToolBar()
    }

    private fun setUpAdapter() {
        adapter = FavoritesScreenAdapter()
        adapter.onClickItemListener = {
            //findNavController().navigate(R.id.action_mainFragment_to_detailFragment)
        }

        binding.recyclerViewFavorites.setHasFixedSize(true)
        binding.recyclerViewFavorites.itemAnimator = DefaultItemAnimator()
        binding.recyclerViewFavorites.adapter = adapter

        //adapter.updateData(FakeDate.favorites)
    }

    private fun setUpToolBar() {
        binding.toolbar.title = "PunkApp - Beer Saved"
        binding.toolbar.setNavigationIcon(R.drawable.baseline_white_arrow_back_24)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.toolbar.inflateMenu(R.menu.main_menu)
        binding.toolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.action_close -> {
                    GenericAlertDialog.showDialog(
                        context = requireActivity(),
                        title = "Aviso",
                        message = "Deseas salir de la aplicación",
                        textBtnAccept = "Aceptar",
                        textBtnCancel = "Cancelar",
                        onBtnAcceptClick = {
                            requireActivity().finish()
                        },
                        onBtnCancelClick = {}
                    )
                    true
                }
                else -> false
            }
        }
    }

    companion object {
        val TAG = FavoritesFragment::class.java.simpleName
        @JvmStatic
        fun newInstance() = FavoritesFragment()
    }
}