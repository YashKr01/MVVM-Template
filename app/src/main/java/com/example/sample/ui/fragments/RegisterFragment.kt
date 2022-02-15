package com.example.sample.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.sample.R
import com.example.sample.databinding.FragmentRegisterBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding: FragmentRegisterBinding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        // navigate to login screen
        binding.txtLogin.setOnClickListener { findNavController().popBackStack() }

        // register user with given inputs
        binding.btnSignUp.setOnClickListener {
            val inputEmail = binding.signInEmail.text.toString()
            val inputPassword = binding.signInPassword.text.toString()
            if (inputEmail.isEmpty() || inputPassword.isEmpty())
                Snackbar.make(
                    requireContext(),
                    binding.root,
                    resources.getString(R.string.invalid_input),
                    Snackbar.LENGTH_SHORT
                ).show()
            else {
                auth.createUserWithEmailAndPassword(inputEmail, inputPassword)
                    .addOnSuccessListener {
                        findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToHomeFragment())
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

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}