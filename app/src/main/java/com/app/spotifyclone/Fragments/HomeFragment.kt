package com.app.spotifyclone.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.spotifyclone.Detalhes
import com.app.spotifyclone.Model.*
import com.app.spotifyclone.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.album_item.view.*
import kotlinx.android.synthetic.main.category_item.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.RecursiveAction

class HomeFragment : Fragment() {

    private lateinit var categoriaAdapter: CategoriaAdapter

    companion object {
        fun newInstance(): HomeFragment {
            val fragmentHome = HomeFragment()
            val argumentos = Bundle()
            fragmentHome.arguments = argumentos
            return fragmentHome
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val categorias = arrayListOf<Category>()

        categoriaAdapter = CategoriaAdapter(categorias)
        recycler_view_category.adapter = categoriaAdapter
        recycler_view_category.layoutManager = LinearLayoutManager(context)

        retrofit().create(SpotifyApi::class.java)
            .ListCategorias().enqueue(object : Callback<Categorias>{
                override fun onFailure(call: Call<Categorias>, t: Throwable) {
                    Toast.makeText(context, "Erro no Servidor", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<Categorias>, response: Response<Categorias>) {
                    if(response.isSuccessful){
                        response.body()?.let {
                            categoriaAdapter.categorias.clear()
                            categoriaAdapter.categorias.addAll(it.categorias)
                            categoriaAdapter.notifyDataSetChanged()
                        }
                    }
                }
            })
    }

    private inner class CategoriaAdapter(internal val categorias: MutableList<Category>): RecyclerView.Adapter<CategoriaHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaHolder {
            return CategoriaHolder(layoutInflater.inflate(R.layout.category_item, parent, false))
        }

        override fun getItemCount(): Int = categorias.size

        override fun onBindViewHolder(holder: CategoriaHolder, position: Int) {
            val categoria= categorias[position]
            holder.bind(categoria)
        }
    }

    private inner class CategoriaHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(categoria: Category) {
            itemView.txt_title.text =  categoria.title
            itemView.recycler_albuns.adapter = AlbunsAdapter(categoria.albuns) { album ->
                val intent = Intent(context, Detalhes::class.java)
                intent.putExtra("album", album.album)
                intent.putExtra("titulos", titulos[album.id])
                startActivity(intent)
            }
            itemView.recycler_albuns.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }
    }

    //Albuns
    private inner class AlbunsAdapter(private val albuns: List<Album>, private val listener: ((Album) -> Unit)?): RecyclerView.Adapter<AlbunsHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbunsHolder = AlbunsHolder(
            layoutInflater.inflate(R.layout.album_item, parent, false),listener)

        override fun getItemCount(): Int = albuns.size

        override fun onBindViewHolder(holder: AlbunsHolder, position: Int) {
            val album = albuns[position]
            holder.bind(album)
        }
    }

    private inner class AlbunsHolder(itemView: View, val onClick: ((Album) -> Unit)?): RecyclerView.ViewHolder(itemView) {
        fun bind(album: Album) {
           //itemView.img_album.setImageResource(album.album)
            Picasso.get().load(album.album).placeholder(R.drawable.placeholder).fit().into(itemView.img_album)
            itemView.img_album.setOnClickListener {
                onClick?.invoke(album)
            }
        }
    }
}