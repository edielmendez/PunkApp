package mx.com.ediel.mv.punkapp.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import mx.com.ediel.mv.punkapp.R
import mx.com.ediel.mv.punkapp.databinding.LoginFragmentBinding


class LoginFragment : Fragment() {

    private var _binding: LoginFragmentBinding? = null
    private val binding get() = _binding!!

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
    }

    private fun setUpListeners() {
        with(binding){
            btnLogin.setOnClickListener {
                attemptLogin()
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
        findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
    }

    companion object {
        val TAG = LoginFragment::class.java.simpleName
        @JvmStatic
        fun newInstance() = LoginFragment()
    }
}