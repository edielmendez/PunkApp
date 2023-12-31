package mx.com.ediel.mv.punkapp.ui.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import dagger.hilt.android.AndroidEntryPoint
import mx.com.ediel.mv.punkapp.R
import mx.com.ediel.mv.punkapp.core.ext.nonNullObserve
import mx.com.ediel.mv.punkapp.databinding.FavoritesFragmentBinding
import mx.com.ediel.mv.punkapp.ui.base.PABaseFragment
import mx.com.ediel.mv.punkapp.ui.common.GenericAlertDialog
import mx.com.ediel.mv.punkapp.ui.common.UIState
import mx.com.ediel.mv.punkapp.ui.main.MainViewModel


@AndroidEntryPoint
class FavoritesFragment : PABaseFragment() {
    private var _binding: FavoritesFragmentBinding? = null
    private val binding get() = _binding!!

    lateinit var adapter: FavoritesScreenAdapter

    private val viewModel: FavoritesViewModel by viewModels()
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
        subscribeUI()
        viewModel.fetchFavorites()
    }

    private fun setUpAdapter() {
        adapter = FavoritesScreenAdapter()
        adapter.onClickItemListener = {}
        adapter.onRateChangeListener = { id, rate ->
            viewModel.updateRate(id, rate)
        }

        binding.recyclerViewFavorites.setHasFixedSize(true)
        binding.recyclerViewFavorites.itemAnimator = DefaultItemAnimator()
        binding.recyclerViewFavorites.adapter = adapter
    }

    private fun subscribeUI() {
        viewModel.state.nonNullObserve(viewLifecycleOwner){
            when(it){
                is UIState.Loading -> {
                    if (it.isLoading){
                        showLoader()
                    }else{
                        hideLoader()
                    }
                }
                is UIState.Success -> {
                    adapter.updateData(it.data)
                }
                is UIState.Error -> {
                    GenericAlertDialog.showDialog(
                        context = requireActivity(),
                        title = "Mensaje",
                        message = it.message,
                        textBtnAccept = "Aceptar",
                        onBtnAcceptClick = {}
                    )
                }
            }
        }
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