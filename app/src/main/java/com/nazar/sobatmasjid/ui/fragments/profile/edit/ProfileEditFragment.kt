package com.nazar.sobatmasjid.ui.fragments.profile.edit

import android.R.attr.fragment
import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.app.Dialog
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.target.SimpleTarget
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.nazar.sobatmasjid.R
import com.nazar.sobatmasjid.data.remote.StatusResponse
import com.nazar.sobatmasjid.databinding.FragmentProfileEditBinding
import com.nazar.sobatmasjid.preference.Preferences
import com.nazar.sobatmasjid.ui.base.BaseBottomSheetFragment
import com.nazar.sobatmasjid.utils.extensions.setImageFromUri
import com.nazar.sobatmasjid.utils.extensions.setImageFromUrl
import com.nazar.sobatmasjid.utils.setLeftDrawable
import com.nazar.sobatmasjid.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_profile_edit.*
import java.io.File


class ProfileEditFragment : BaseBottomSheetFragment(), View.OnClickListener {

    private lateinit var binding: FragmentProfileEditBinding
    private lateinit var profileEditViewModel: ProfileEditViewModel
    private var isEditing: Boolean = false
    private var imageUri: Uri? = null
    private val preferences: Preferences by lazy {
        Preferences(requireActivity().applicationContext)
    }

    companion object {
        const val REQUEST_IMAGE_GET = 1
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setOnShowListener {
            val bottomSheet =
                (it as BottomSheetDialog).findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout?
            val behavior = BottomSheetBehavior.from(bottomSheet!!)
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            behavior.isDraggable = false
        }
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireContext())
        val viewModelStore = findNavController().currentBackStackEntry?.viewModelStore!!
        profileEditViewModel =
            ViewModelProvider(viewModelStore, factory)[ProfileEditViewModel::class.java]

        init()

        profileEditViewModel.bornDate.observe(viewLifecycleOwner, {
            if (isEditing) {
                binding.edtBornDate.setText(it)
            }
        })
        profileEditViewModel.gender.observe(viewLifecycleOwner, {
            if (isEditing) {
                binding.edtGender.setText(it)
            }
        })
        binding.btnActionChange.setOnClickListener {
            val button = it as TextView
            when (button.text) {
                getString(R.string.change) -> {
                    button.text = getString(R.string.cancel)
                    onEditMode(
                        status = true,
                        isDrawableNull = true,
                        listener = this,
                        state = View.VISIBLE
                    )
                }
                getString(R.string.cancel) -> {
                    button.text = getString(R.string.change)
                    onEditMode(
                        status = false,
                        isDrawableNull = false,
                        listener = null,
                        state = View.INVISIBLE
                    )
                }
            }
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    override fun onClick(view: View?) {
        when (view) {
            binding.btnBornDateChange -> {
                findNavController().navigate(R.id.calendarProfileFragment)
            }
            binding.btnGenderChange -> {
                findNavController().navigate(R.id.genderProfileFragment)
            }
            binding.btnImageChange -> {
                val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
                    type = "image/*"
                }
                if (intent.resolveActivity(requireActivity().packageManager) != null) {
                    startActivityForResult(intent, REQUEST_IMAGE_GET)
                } else {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.notification_warning),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            binding.btnSave -> {
                val file = File(imageUri?.path!!, "")
                val files = Uri.parse(imageUri.toString())


//                val requestBody = file.asRequestBody("image/jpeg".toMediaType())
//                val date = Date.valueOf(edtBornDate.text.toString())
//                val gender = edtGender.text.toString()[0]
                profileEditViewModel.updateUser(
                    preferences.idUser,
                    file,
                    edtName.text.toString(),
                    edtBornDate.text.toString(),
                    preferences.email,
                    edtGender.text.toString(),
                    edtMotto.text.toString()
                ).observe(viewLifecycleOwner, {
                    when (it.status) {
                        StatusResponse.SUCCESS -> {
                            Log.d("LOGI", it.body.toString())
                        }
                        StatusResponse.EMPTY -> {
                            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                        }
                        StatusResponse.ERROR -> {
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.notification_warning),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                })
            }
        }
    }

    private fun onEditMode(
        status: Boolean, isDrawableNull: Boolean,
        listener: View.OnClickListener?, state: Int
    ) {
        if (isDrawableNull) {
            binding.tvName.setLeftDrawable(null)
            binding.tvBornDate.setLeftDrawable(null)
            binding.tvMotto.setLeftDrawable(null)
            binding.tvGender.setLeftDrawable(null)
        } else
            init()
        isEditing = status
        binding.edtName.isEnabled = status
        binding.edtMotto.isEnabled = status
        binding.btnSave.isEnabled = status
        binding.btnBornDateChange.visibility = state
        binding.btnGenderChange.visibility = state
        binding.btnImageChange.visibility = state
        binding.btnSave.visibility = state
        binding.btnBornDateChange.setOnClickListener(listener)
        binding.btnGenderChange.setOnClickListener(listener)
        binding.btnImageChange.setOnClickListener(listener)
        binding.btnSave.setOnClickListener(listener)
    }

    private fun init() {
        val textViews = listOf(
            binding.tvName,
            binding.tvBornDate,
            binding.tvMotto,
            binding.tvGender
        )
        val editTextViews = listOf(
            binding.edtName,
            binding.edtBornDate,
            binding.edtMotto,
            binding.edtGender
        )
        val text = listOf(
            preferences.name,
            preferences.bornDate,
            preferences.motto,
            preferences.gender
        )
        textViews.forEachIndexed { index, textView ->
            attachDrawable(
                textView,
                editTextViews[index],
                text[index]
            )
        }
        binding.imgProfilePhoto.setImageFromUrl(preferences.photoUrl)
        profileEditViewModel.setBornDate(preferences.bornDate)
        profileEditViewModel.setGender(preferences.gender)
    }

    private fun attachDrawable(textView: TextView, editView: EditText, text: String) {
        val correctMark =
            ResourcesCompat.getDrawable(resources, R.drawable.ic_circle_check_full, null)
        val incorrectMark =
            ResourcesCompat.getDrawable(resources, R.drawable.ic_circle_check_full_gray, null)
        if (text.isNotEmpty())
            textView.setLeftDrawable(correctMark)
        else
            textView.setLeftDrawable(incorrectMark)
        editView.setText(text)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_GET && resultCode == RESULT_OK && data != null && data.data != null) {
            imageUri = data.data
            if (imageUri != null) {
                binding.imgProfilePhoto.setImageFromUri(imageUri!!)
            }
        }
    }

}