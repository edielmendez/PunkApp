package mx.com.ediel.mv.punkapp.ui.detail

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import mx.com.ediel.mv.punkapp.R
import mx.com.ediel.mv.punkapp.data.models.FakeDate
import mx.com.ediel.mv.punkapp.databinding.DetailFragmentBinding
import mx.com.ediel.mv.punkapp.databinding.MainFragmentBinding
import mx.com.ediel.mv.punkapp.ui.common.GenericAlertDialog
import mx.com.ediel.mv.punkapp.ui.favorites.FavoritesScreenAdapter
import mx.com.ediel.mv.punkapp.ui.login.LoginFragment


private const val BEER_ID = "beer_id"

class DetailFragment : Fragment() {
    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!

    private var beer_id: Int = 0

    lateinit var adapterFoodPairing: FoodPairingAdapter
    lateinit var ingredientAdapter: IngredientAdapter

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
        setUpAdapters()
    }

    private fun setUpAdapters() {
        adapterFoodPairing = FoodPairingAdapter()
        adapterFoodPairing.onClickItemListener = {}
        binding.recyclerViewFoodPairing.setHasFixedSize(true)
        binding.recyclerViewFoodPairing.itemAnimator = DefaultItemAnimator()
        binding.recyclerViewFoodPairing.adapter = adapterFoodPairing
        adapterFoodPairing.updateData(FakeDate.foods)

        ingredientAdapter = IngredientAdapter()
        binding.recyclerViewIngredients.setHasFixedSize(true)
        binding.recyclerViewIngredients.itemAnimator = DefaultItemAnimator()
        binding.recyclerViewIngredients.adapter = ingredientAdapter
        ingredientAdapter.updateData(FakeDate.ingredients)
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