package com.example.myapp.nav_fragment

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.myapp.R
import com.example.myapp.databinding.FragmentProfileBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar

class ProfileFragment : Fragment() {

    private val REQUEST_IMAGE_CAPTURE = 123
    private lateinit var binding: FragmentProfileBinding
    private var capturedImageBitmap: Bitmap? = null
    private lateinit var perfilImage: ImageButton
    private lateinit var profileImg: ImageView
    private lateinit var userNameTxt: TextView
    private lateinit var emailTxt: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        perfilImage = binding.perfilImage

        val navigationView = requireActivity().findViewById<NavigationView>(R.id.navigationView)
        val headerView = navigationView.getHeaderView(0)
        profileImg = headerView.findViewById(R.id.profileImg)
        userNameTxt = headerView.findViewById(R.id.userNameTxt)
        emailTxt = headerView.findViewById(R.id.emailTxt)

        perfilImage.setOnClickListener {
            takePhoto()
        }

        binding.buttonEnter.setOnClickListener {
            updateProfile()
        }

        savedInstanceState?.let {
            capturedImageBitmap = it.getParcelable("capturedImage")
            capturedImageBitmap?.let { bitmap ->
                perfilImage.setImageBitmap(bitmap)
            }
        }
    }

    private fun takePhoto() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            capturedImageBitmap = data?.extras?.get("data") as Bitmap
            perfilImage.setImageBitmap(capturedImageBitmap)
        }
    }

    private fun updateProfile() {
        val name = binding.nameInput.text.toString()
        val email = binding.emailInput.text.toString()

        if (name.isEmpty()) {
            binding.nameInput.error = "Nome não pode estar vazio"
            return
        }

        if (!isValidEmail(email)) {
            binding.emailInput.error = "E-mail inválido"
            return
        }

        if (capturedImageBitmap == null) {
            Snackbar.make(binding.root, "Tire uma foto antes de atualizar o perfil", Snackbar.LENGTH_SHORT).show()
            return
        }

        profileImg.setImageBitmap(capturedImageBitmap)
        profileImg.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in))

        userNameTxt.text = name
        emailTxt.text = email

        Snackbar.make(binding.root, "Perfil atualizado com sucesso", Snackbar.LENGTH_SHORT).show()

        clearProfileFields()
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        capturedImageBitmap?.let {
            outState.putParcelable("capturedImage", it)
        }
    }

    private fun clearProfileFields() {
        binding.nameInput.setText("")
        binding.emailInput.setText("")
        perfilImage.setImageResource(R.drawable.ic_profile_24)
        capturedImageBitmap = null
    }
}
