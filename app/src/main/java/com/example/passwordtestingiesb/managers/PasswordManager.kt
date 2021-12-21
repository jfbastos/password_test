package com.example.passwordtestingiesb.managers

import com.example.passwordtestingiesb.R
import com.example.passwordtestingiesb.utils.ValidationsUtils

object PasswordManager {

    fun calculatePassword(password: String): PasswordStatus {

        val invalidTypes = invalidPassword(password)

        if(invalidTypes.isEmpty()){
            if(password.length >= 8) {
                if (password.length >= 12) {
                    if (password.length == 15) {
                        return PasswordStatus.Valid(StrengthLevel.STRONG)
                    }
                    return PasswordStatus.Valid(StrengthLevel.MEDIUM)
                }
                return PasswordStatus.Valid(StrengthLevel.WEAK)
            }
        }

        return PasswordStatus.Invalid(invalidTypes)
    }

    private fun invalidPassword(password: String): MutableList<InvalidType> {
        val invalidTypes = mutableListOf<InvalidType>()

        if (password.length < 8) {
            invalidTypes.add(InvalidType.Minimo8Caracteres)
        }

        if (password.length > 15) {
            invalidTypes.add(InvalidType.MaiorQue15Caracteres)
        }

        if (ValidationsUtils.isContainDigit(password).not()) {
            invalidTypes.add(InvalidType.Minimo1Numero)
        }

        if (ValidationsUtils.isContainSpecialCharacter(password).not()) {
            invalidTypes.add(InvalidType.Minimo1CaractereEspecial)
        }

        return invalidTypes
    }
}


sealed class PasswordStatus {

    class Invalid(
        val type: List<InvalidType>
    ) : PasswordStatus()

    class Valid(
        val strengthLevel: StrengthLevel
    ) : PasswordStatus()

}

enum class InvalidType(val reason: String) {
    Minimo8Caracteres("No minimo 8 caracteres"),
    Minimo1Numero("Ao menos 1 numero"),
    Minimo1CaractereEspecial("Ao menos 1 caractere especial"),
    MaiorQue15Caracteres("Maior que 15 caracteres")
}


// Weak - Requerimentos minimos para senha estar valida
// Medium - Mais Do que 8 caracteres
// Strong - Mais do que 10 caracteres
enum class StrengthLevel(val color: Int) {
    NONE(R.color.password_strength_clean),
    WEAK(R.color.password_strength_weak),
    MEDIUM(R.color.password_strength_medium),
    STRONG(R.color.password_strength_strong)
}

