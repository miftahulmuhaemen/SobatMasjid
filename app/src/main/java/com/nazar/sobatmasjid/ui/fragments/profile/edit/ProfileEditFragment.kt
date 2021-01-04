package com.nazar.sobatmasjid.ui.fragments.profile.edit

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.app.Dialog
import android.location.Location
import android.os.Bundle
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
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.nazar.sobatmasjid.R
import com.nazar.sobatmasjid.data.remote.StatusResponse
import com.nazar.sobatmasjid.databinding.FragmentProfileEditBinding
import com.nazar.sobatmasjid.preference.Preferences
import com.nazar.sobatmasjid.ui.base.BaseBottomSheetFragment
import com.nazar.sobatmasjid.ui.fragments.location.LocationViewModel
import com.nazar.sobatmasjid.ui.fragments.profile.ProfileViewModel
import com.nazar.sobatmasjid.utils.extensions.setImageFromUri
import com.nazar.sobatmasjid.utils.extensions.setImageFromUrl
import com.nazar.sobatmasjid.utils.setLeftDrawable
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlinx.android.synthetic.main.fragment_profile_edit.*
import org.koin.android.ext.android.inject
import java.io.File


class ProfileEditFragment : BaseBottomSheetFragment(), View.OnClickListener {

    private lateinit var binding: FragmentProfileEditBinding
    private val profileViewModel by sharedViewModel<ProfileViewModel>()
    private var isEditing: Boolean = false
    private var imageFile: File? = null
    private val preferences: Preferences by inject()

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

        init()

        profileViewModel.bornDate.observe(viewLifecycleOwner, {
            if (isEditing) {
                binding.edtBornDate.setText(it)
            }
        })
        profileViewModel.gender.observe(viewLifecycleOwner, {
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
                ImagePicker.with(this)
                    .compress(1024)
                    .maxResultSize(1080, 1080)
                    .start { resultCode, data ->
                        when (resultCode) {
                            RESULT_OK -> {
                                val fileUri = data?.data
                                binding.imgProfilePhoto.setImageFromUri(fileUri!!)
                                imageFile = ImagePicker.getFile(data)!!
                            }
                            ImagePicker.RESULT_ERROR -> {
                                Toast.makeText(
                                    context,
                                    ImagePicker.getError(data),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
            }
            binding.btnSave -> {
                profileViewModel.updateUser(
                    preferences.idUser,
                    imageFile,
                    edtName.text.toString(),
                    edtBornDate.text.toString(),
                    preferences.email,
                    edtGender.text.toString(),
                    edtMotto.text.toString(),
                    preferences.latitude,
                    preferences.longitude
                ).observe(viewLifecycleOwner, { user ->
                    when (user.status) {
                        StatusResponse.SUCCESS -> {
                            if (user.body != null)
                                with(user.body) {
                                    profileViewModel.setUser(this)
                                }
                            dismiss()
                        }
                        StatusResponse.EMPTY -> {
                            Toast.makeText(requireContext(), user.message, Toast.LENGTH_SHORT)
                                .show()
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
        binding.btnSave
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
        profileViewModel.setBornDate(preferences.bornDate)
        profileViewModel.setGender(preferences.gender)
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

}