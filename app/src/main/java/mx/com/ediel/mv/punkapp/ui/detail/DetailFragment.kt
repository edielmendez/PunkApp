package mx.com.ediel.mv.punkapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import mx.com.ediel.mv.punkapp.R
import mx.com.ediel.mv.punkapp.core.ext.nonNullObserve
import mx.com.ediel.mv.punkapp.data.models.Beer
import mx.com.ediel.mv.punkapp.data.models.Ingredient
import mx.com.ediel.mv.punkapp.databinding.DetailFragmentBinding
import mx.com.ediel.mv.punkapp.ui.base.PABaseFragment
import mx.com.ediel.mv.punkapp.ui.common.GenericAlertDialog
import mx.com.ediel.mv.punkapp.ui.common.UIState


private const val BEER_ID = "beer_id"

@AndroidEntryPoint
class DetailFragment : PABaseFragment() {
    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!

    private var beer_id: Int = 0

    lateinit var adapterFoodPairing: FoodPairingAdapter
    lateinit var ingredientAdapter: IngredientAdapter

    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            beer_id = it.getInt(BEER_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(beer_id == 0){
            Toast.makeText(requireActivity(), "ID no válido", Toast.LENGTH_LONG).show()
            findNavController().popBackStack()
        }
        setUpToolBar()
        subscribeUI()
        viewModel.fetchBeer(beer_id)
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
                    setUpViews(it.data)
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

    private fun setUpViews(beer: Beer){
        with(binding){
            textName.text = beer.name
            Picasso.get().load(beer.imageUrl).into(imgBeer)
            textTagline.text = beer.tagline
            textTips.text = beer.tagline
            specifications.textAbvValue.text = beer.abv
            specifications.textIbuValue.text = beer.ibu
            specifications.textOgValue.text = beer.og
            specifications.textFgValue.text = beer.fg
            textYeast.text = beer.yeast
            textBrewed.text = "First Brewed: ${beer.firstBrewed}"

            setUpAdapters(beer.foodPairing, beer.ingredients)
        }
    }

    private fun setUpAdapters(foods: List<String>, ingredients: List<Ingredient>) {
        adapterFoodPairing = FoodPairingAdapter()
        adapterFoodPairing.onClickItemListener = {}
        binding.recyclerViewFoodPairing.setHasFixedSize(true)
        binding.recyclerViewFoodPairing.itemAnimator = DefaultItemAnimator()
        binding.recyclerViewFoodPairing.adapter = adapterFoodPairing
        adapterFoodPairing.updateData(foods)

        ingredientAdapter = IngredientAdapter()
        binding.recyclerViewIngredients.setHasFixedSize(true)
        binding.recyclerViewIngredients.itemAnimator = DefaultItemAnimator()
        binding.recyclerViewIngredients.adapter = ingredientAdapter
        ingredientAdapter.updateData(ingredients)
    }

    private fun setUpToolBar() {
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
        val TAG = DetailFragment::class.java.simpleName

        @JvmStatic
        fun newInstance(beer_id: String) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putString(BEER_ID, beer_id)
                }
            }
    }
}