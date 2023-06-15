package mx.com.ediel.mv.punkapp.ui.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import dagger.hilt.android.AndroidEntryPoint
import mx.com.ediel.mv.punkapp.R
import mx.com.ediel.mv.punkapp.core.ext.nonNullObserve
import mx.com.ediel.mv.punkapp.databinding.MainFragmentBinding
import mx.com.ediel.mv.punkapp.ui.base.PABaseFragment
import mx.com.ediel.mv.punkapp.ui.common.GenericAlertDialog
import mx.com.ediel.mv.punkapp.ui.common.UIState

@AndroidEntryPoint
class MainFragment : PABaseFragment() {
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    lateinit var adapter: MainScreenAdapter

    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolBar()
        setUpAdapter()
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_favoritesFragment)
        }
        viewModel.fetchBeers()
        subscribeUI()
    }

    private fun subscribeUI() {
        viewModel.state.nonNullObserve(viewLifecycleOwner){
            when(it){
                is UIState.Loading -> {
                    if (it.isLoading){
                        //findNavController().navigate(R.id.action_mainFragment_to_loaderFragment)
                        showLoader()
                    }else{
                        //findNavController().popBackStack()
                        hideLoader()
                    }
                }
                is UIState.Success -> {
                    adapter.updateData(it.data)
                    //adapter.setRecipes(list = it.data)
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

    private fun setUpAdapter() {
        adapter = MainScreenAdapter()
        adapter.onClickItemListener = {
            findNavController().navigate(R.id.action_mainFragment_to_detailFragment,
                bundleOf(
                    "beer_id" to it.id
                )
            )
        }
        adapter.onClickFavImgListener = {
        }

        binding.recyclerViewMain.setHasFixedSize(true)
        binding.recyclerViewMain.itemAnimator = DefaultItemAnimator()
        binding.recyclerViewMain.adapter = adapter
    }

    private fun setUpToolBar() {
        binding.toolbar.title = "PunkApp"
        //binding.toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_white_24)
        /*binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }*/
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
        val TAG = MainFragment::class.java.simpleName
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}