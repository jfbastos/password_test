package com.example.passwordtestingiesb.managers

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class PasswordManagerTest {


    @Test
    fun `Digitar senha menor que 8 caracteres, retornar erro de 8 caracteres`() {
        val password = buildString {
            for(i in 1 until 8) {
                append(i)
            }
        }

        val result = PasswordManager.calculatePassword(password)

        assertThat((result as PasswordStatus.Invalid).type)
            .contains(InvalidType.Minimo8Caracteres)
    }

    @Test
    fun `Digitar senha sem Numeros, retorn erro de falta de numero`() {
        val password = buildString {
            for(i in 1 until 9) {
                append("a")
            }
        }

        val result = PasswordManager.calculatePassword(password)

        assertThat((result as PasswordStatus.Invalid).type)
            .contains(InvalidType.Minimo1Numero)
    }

    @Test
    fun `Digitar senha sem um caracter especial, retorna erro de falta de caracter especial`(){
        val password = buildString {
            for (i in 1 until 9){
                append("a")
            }
        }

        val result = PasswordManager.calculatePassword(password)

        assertThat((result as PasswordStatus.Invalid).type).contains(InvalidType.Minimo1CaractereEspecial)
    }

    @Test
    fun `Digitar senha maior que 15 caracteres, retorna erro de senha maior que 15 caracteres` (){
        val password = buildString {
            for( i in 1 until 17){
                append("a")
            }
        }

        val result = PasswordManager.calculatePassword(password)

        assertThat((result as PasswordStatus.Invalid).type).contains(InvalidType.MaiorQue15Caracteres)

    }

    @Test
    fun `Digitar senha com menos que 12 caracteres sera fraca`(){
        val password = "123ABC!#"

        val result = PasswordManager.calculatePassword(password)

        assertThat((result as PasswordStatus.Valid).strengthLevel).isEqualTo(StrengthLevel.WEAK)
    }

    @Test
    fun `Digitar senha com menos que 15 e mais de 12 caracteres sera media`(){
        val password = "123ABC!$12345"

        val result = PasswordManager.calculatePassword(password)

        print(result)

        assertThat((result as PasswordStatus.Valid).strengthLevel).isEqualTo(StrengthLevel.MEDIUM)
    }

    @Test
    fun `Digitar senha com 15 caracteres sera forte`(){
        val password = "123ABC!12345641"

        val result = PasswordManager.calculatePassword(password)

        assertThat((result as PasswordStatus.Valid).strengthLevel).isEqualTo(StrengthLevel.STRONG)
    }



}