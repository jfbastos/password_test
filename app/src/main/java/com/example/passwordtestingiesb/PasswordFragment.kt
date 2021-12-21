package com.example.passwordtestingiesb

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.passwordtestingiesb.databinding.FragmentPasswordBinding
import com.example.passwordtestingiesb.managers.InvalidType
import com.example.passwordtestingiesb.managers.PasswordManager
import com.example.passwordtestingiesb.managers.PasswordStatus
import com.example.passwordtestingiesb.managers.StrengthLevel


class PasswordFragment : Fragment() {

    private var _binding: FragmentPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val drawableOk = ContextCompat.getDrawable(requireActivity(), R.drawable.ic_baseline_check_24)
        val drawableError = ContextCompat.getDrawable(requireActivity(), R.drawable.ic_close)
        val colorGreen = ContextCompat.getColor(requireContext(), R.color.password_strength_strong)
        val colorRed = ContextCompat.getColor(requireContext(), R.color.password_strength_weak)


        binding.includePassword.tiePassword.addTextChangedListener {

            when (val result = PasswordManager.calculatePassword(it.toString())) {
                is PasswordStatus.Valid -> {
                    validCheckers(drawableOk, colorGreen)

                    when (result.strengthLevel) {
                        StrengthLevel.WEAK ->{
                            binding.includePassword.strengthLevelIndicatorWeak.setBackgroundColor(
                                ContextCompat.getColor(requireContext(), R.color.password_strength_weak)
                            )
                            binding.includePassword.strengthLevelIndicatorMedium.setBackgroundColor(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.password_strength_clean
                                )
                            )
                            binding.includePassword.strengthLevelIndicatorStrong.setBackgroundColor(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.password_strength_clean
                                )
                            )
                        }
                        StrengthLevel.MEDIUM -> {
                            binding.includePassword.strengthLevelIndicatorWeak.setBackgroundColor(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.password_strength_medium
                                )
                            )
                            binding.includePassword.strengthLevelIndicatorMedium.setBackgroundColor(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.password_strength_medium
                                )
                            )
                            binding.includePassword.strengthLevelIndicatorStrong.setBackgroundColor(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.password_strength_clean
                                )
                            )
                        }
                        StrengthLevel.STRONG -> {
                            binding.includePassword.strengthLevelIndicatorWeak.setBackgroundColor(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.password_strength_strong
                                )
                            )
                            binding.includePassword.strengthLevelIndicatorMedium.setBackgroundColor(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.password_strength_strong
                                )
                            )
                            binding.includePassword.strengthLevelIndicatorStrong.setBackgroundColor(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.password_strength_strong
                                )
                            )
                        }
                        else -> {
                            binding.includePassword.strengthLevelIndicatorWeak.setBackgroundColor(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.password_strength_clean
                                )
                            )
                            binding.includePassword.strengthLevelIndicatorMedium.setBackgroundColor(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.password_strength_clean
                                )
                            )
                            binding.includePassword.strengthLevelIndicatorStrong.setBackgroundColor(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.password_strength_clean
                                )
                            )
                        }
                    }
                }
                is PasswordStatus.Invalid -> {

                    if(result.type.contains(InvalidType.Minimo8Caracteres).not()){
                        binding.includePassword.eightImg.setImageDrawable(drawableOk)
                        binding.includePassword.eightImg.setColorFilter(colorGreen)
                        binding.includePassword.eightCharacters.setTextColor(colorGreen)
                    }else{
                        binding.includePassword.eightImg.setImageDrawable(drawableError)
                        binding.includePassword.eightImg.setColorFilter(colorRed)
                        binding.includePassword.eightCharacters.setTextColor(colorRed)
                    }

                    if (result.type.contains(InvalidType.Minimo1Numero).not()) {
                        binding.includePassword.numbersImg.setImageDrawable(drawableOk)
                        binding.includePassword.numbersImg.setColorFilter(colorGreen)
                        binding.includePassword.numbers.setTextColor(colorGreen)
                    }else{
                        binding.includePassword.numbersImg.setImageDrawable(drawableError)
                        binding.includePassword.numbersImg.setColorFilter(colorRed)
                        binding.includePassword.numbers.setTextColor(colorRed)
                    }

                    if (result.type.contains(InvalidType.Minimo1CaractereEspecial).not()) {
                        binding.includePassword.charactersImg.setImageDrawable(drawableOk)
                        binding.includePassword.charactersImg.setColorFilter(colorGreen)
                        binding.includePassword.characters.setTextColor(colorGreen)
                    }else{
                        binding.includePassword.charactersImg.setImageDrawable(drawableError)
                        binding.includePassword.charactersImg.setColorFilter(colorRed)
                        binding.includePassword.characters.setTextColor(colorRed)
                    }

                    if(result.type.contains(InvalidType.MaiorQue15Caracteres).not()){
                        binding.includePassword.fifteenImg.setImageDrawable(drawableOk)
                        binding.includePassword.fifteenImg.setColorFilter(colorGreen)
                        binding.includePassword.fifteenCharacters.setTextColor(colorGreen)
                    }else{
                        binding.includePassword.fifteenImg.setImageDrawable(drawableError)
                        binding.includePassword.fifteenImg.setColorFilter(colorRed)
                        binding.includePassword.fifteenCharacters.setTextColor(colorRed)
                    }

                    binding.includePassword.strengthLevelIndicatorWeak.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.password_strength_clean
                        )
                    )
                    binding.includePassword.strengthLevelIndicatorMedium.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.password_strength_clean
                        )
                    )
                    binding.includePassword.strengthLevelIndicatorStrong.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.password_strength_clean
                        )
                    )
                }
            }
        }

    }

    private fun validCheckers(drawableOk: Drawable?, colorGreen: Int) {
        binding.includePassword.eightImg.setImageDrawable(drawableOk)
        binding.includePassword.eightImg.setColorFilter(colorGreen)
        binding.includePassword.eightCharacters.setTextColor(colorGreen)

        binding.includePassword.numbersImg.setImageDrawable(drawableOk)
        binding.includePassword.numbersImg.setColorFilter(colorGreen)
        binding.includePassword.numbers.setTextColor(colorGreen)

        binding.includePassword.charactersImg.setImageDrawable(drawableOk)
        binding.includePassword.charactersImg.setColorFilter(colorGreen)
        binding.includePassword.characters.setTextColor(colorGreen)

        binding.includePassword.fifteenImg.setImageDrawable(drawableOk)
        binding.includePassword.fifteenImg.setColorFilter(colorGreen)
        binding.includePassword.fifteenCharacters.setTextColor(colorGreen)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}