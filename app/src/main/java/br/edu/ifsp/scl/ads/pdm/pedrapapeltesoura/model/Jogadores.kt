package br.edu.ifsp.scl.ads.pdm.pedrapapeltesoura.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Jogadores  (
    val numeroJogadores: Int = 1
) : Parcelable

