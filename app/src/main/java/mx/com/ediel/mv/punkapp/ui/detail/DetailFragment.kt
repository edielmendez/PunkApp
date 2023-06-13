package mx.com.ediel.mv.punkapp.ui.detail

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import mx.com.ediel.mv.punkapp.R
import mx.com.ediel.mv.punkapp.data.models.FakeDate
import mx.com.ediel.mv.punkapp.databinding.DetailFragmentBinding
import mx.com.ediel.mv.punkapp.databinding.MainFragmentBinding
import mx.com.ediel.mv.punkapp.ui.common.GenericAlertDialog
import mx.com.ediel.mv.punkapp.ui.favorites.FavoritesScreenAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {
    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var adapterFoodPairing: FoodPairingAdapter
    lateinit var ingredientAdapter: IngredientAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
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

    /*override fun getTheme(): Int {
        super.getTheme()
        return R.style.DialogTheme
    }*/

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}