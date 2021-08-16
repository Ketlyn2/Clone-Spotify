package com.app.spotifyclone.Model

import com.app.spotifyclone.R
import com.google.gson.annotations.SerializedName

class Category(
    @SerializedName("titulo") var title: String = "",
    @SerializedName("albuns") var albuns: List<Album> = arrayListOf()
)

data class Album(
    @SerializedName("url_imagem") var album: String = "",
    @SerializedName("id") var id: Int = 0
)

data class Categorias(
    @SerializedName("categoria") val categorias: List<Category>
)

//---

data class Secao (
    var secao: Int,
    var nomeSecao: String
)

class SecaoBuilder {
    var secao: Int = 0
    var nomeSecao: String = ""

    fun build(): Secao = Secao(secao, nomeSecao)
}

fun secao(block: SecaoBuilder.() -> Unit): Secao = SecaoBuilder().apply(block).build()

fun dados(): MutableList<Secao> = mutableListOf(
    secao {
        secao = R.drawable.secao1
        nomeSecao = "Podcast"
    },

    secao {
        secao = R.drawable.secao2
        nomeSecao = "Lançamentos"
    },

    secao {
        secao = R.drawable.secao1
        nomeSecao = "Em casa"
    },

    secao {
        secao = R.drawable.secao3
        nomeSecao = "Paradas"
    },

    secao {
        secao = R.drawable.secao4
        nomeSecao = "Show"
    },

    secao {
        secao = R.drawable.secao2
        nomeSecao = "Made for You"
    },

    secao {
        secao = R.drawable.secao1
        nomeSecao = "Brasil"
    },

    secao {
        secao = R.drawable.secao2
        nomeSecao = "Funk"
    }
)
