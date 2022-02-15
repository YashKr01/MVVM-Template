package com.example.sample.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.sample.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        if (auth.currentUser != null) findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())

        // Login user on button click
        binding.btnSignIn.setOnClickListener {
            val inputEmail = binding.signInEmail.text.toString()
            val inputPassword = binding.signInPassword.text.toString()
            if (inputEmail.isEmpty() || inputPassword.isEmpty())
                Snackbar.make(
                    requireContext(),
                    binding.root,
                    "Validate all fields",
                    Snackbar.LENGTH_SHORT
                ).show()
            else {
                auth.signInWithEmailAndPassword(inputEmail, inputPassword)
                    .addOnSuccessListener {
                        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
                        findNavController().popBackStack()
                    }
                    .addOnFailureListener {
                        Snackbar.make(
                            requireContext(),
                            binding.root,
                            "ERROR : ${it.message.toString()}",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
            }
        }

        // navigate to register screen
        binding.txtSignUp.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}