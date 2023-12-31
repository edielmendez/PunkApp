package mx.com.ediel.mv.punkapp.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import mx.com.ediel.mv.punkapp.R
import mx.com.ediel.mv.punkapp.core.ext.nonNullObserve
import mx.com.ediel.mv.punkapp.databinding.LoginFragmentBinding
import mx.com.ediel.mv.punkapp.ui.base.PABaseFragment
import mx.com.ediel.mv.punkapp.ui.common.UIState


@AndroidEntryPoint
class LoginFragment : PABaseFragment() {

    private var _binding: LoginFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListeners()
        subscribeUI()
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
                    if(it.data){
                        findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                    }else{
                        binding.textAlert.text = "Username o contraseña incorrectos"
                        binding.cardAlert.visibility = View.VISIBLE
                    }
                    //adapter.setRecipes(list = it.data)
                }
                is UIState.Error -> {
                    binding.textAlert.text = it.message
                    binding.cardAlert.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setUpListeners() {
        with(binding){
            btnLogin.setOnClickListener {
                attemptLogin()
            }
            imgCloseAlert.setOnClickListener {
                binding.cardAlert.visibility = View.GONE
            }
        }
    }

    private fun attemptLogin(){
        val username = binding.editUsername.text.toString()
        val password = binding.editPassword.text.toString()
        if(username.isNullOrEmpty()){
            binding.editUsername.error = getString(R.string.text_empty_field)
            binding.editUsername.requestFocus()
            return
        }
        if(password.isNullOrEmpty()){
            binding.editPassword.error = getString(R.string.text_empty_field)
            binding.editPassword.requestFocus()
            return
        }
        viewModel.login(username, password)
    }

    companion object {
        val TAG = LoginFragment::class.java.simpleName
        @JvmStatic
        fun newInstance() = LoginFragment()
    }
}