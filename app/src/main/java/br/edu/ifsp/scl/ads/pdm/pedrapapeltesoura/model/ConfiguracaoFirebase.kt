package br.edu.ifsp.scl.ads.pdm.pedrapapeltesoura.model

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class ConfiguracaoFirebase {
    companion object {
        private val BD_JOGADORES_ROOT = "jogadores"
    }

    val configuracaoDatabase = Firebase.database.getReference().database.getReference(BD_JOGADORES_ROOT)
    private var configuracaoResposta: Int = 0
    private var flag: Boolean = false

    init {
        /* Utilizei esta forma alternativa de construir o listener porque estava tendo problemas
        para buscar a configuração na iniciação do aplicativo */
        configuracaoDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.getValue<Int>()
                if (value != null) {
                    configuracaoResposta = value
                    Log.d("Interno", configuracaoResposta.toString())

                }
                flag = true
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }

        })
    }

    fun criaOuAtualizaConfiguracao(numeroJogadores: Int) {
        configuracaoDatabase.setValue(numeroJogadores)
    }

    fun recuperaConfiguracao(): Int {
        val configuracaoResposta2 = configuracaoResposta
        return configuracaoResposta2
    }
}






