package com.app.spotifyclone.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.spotifyclone.Model.Secao
import com.app.spotifyclone.Model.dados
import com.app.spotifyclone.Model.secao
import com.app.spotifyclone.R
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.secao_item.view.*

class SearchFragment : Fragment() {

    private lateinit var secaoAdapter: SecaoAdapter

    companion object {
        fun newInstance(): SearchFragment {
            val fragmentSearch = SearchFragment()
            val argumentos = Bundle()
            fragmentSearch.arguments = argumentos
            return fragmentSearch
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        secaoAdapter = SecaoAdapter(dados())
        recycler_view_secao.adapter = secaoAdapter
        recycler_view_secao.layoutManager = GridLayoutManager(context, 2)
    }

    private inner class SecaoAdapter(private val secoes: MutableList<Secao>): RecyclerView.Adapter<SecaoHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SecaoHolder {
            return SecaoHolder(layoutInflater.inflate(R.layout.secao_item, parent, false))
        }

        override fun getItemCount(): Int = secoes.size

        override fun onBindViewHolder(holder: SecaoHolder, position: Int) {
           val secao = secoes[position]
            holder.bind(secao)
        }
    }

    private inner class SecaoHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(secao: Secao) {
            itemView.img_secao.setImageResource(secao.secao)
            itemView.nome_secao.text = secao.nomeSecao
        }
    }

}