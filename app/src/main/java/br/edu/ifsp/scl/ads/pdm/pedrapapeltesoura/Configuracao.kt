package br.edu.ifsp.scl.ads.pdm.pedrapapeltesoura

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Configuracao(var numeroJogadores: Int = 1): Parcelable
