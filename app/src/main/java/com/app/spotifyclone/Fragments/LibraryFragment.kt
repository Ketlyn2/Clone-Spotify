package com.app.spotifyclone.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.spotifyclone.FragmentsTab.Albuns
import com.app.spotifyclone.FragmentsTab.Artistas
import com.app.spotifyclone.FragmentsTab.Playlists
import com.app.spotifyclone.R
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems
import kotlinx.android.synthetic.main.fragment_library.*

class LibraryFragment : Fragment() {

    companion object {
        fun newInstance(): LibraryFragment {
            val fragmentLibrary = LibraryFragment()
            val argumentos = Bundle()
            fragmentLibrary.arguments = argumentos
            return fragmentLibrary
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_library, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var adapter = FragmentPagerItemAdapter(fragmentManager, FragmentPagerItems.with(context)
            .add("Playlists", Playlists::class.java)
            .add("Artistas", Artistas::class.java)
            .add("Álbuns", Albuns::class.java)
            .create())

        viewpager.adapter = adapter
        viewpagertab.setViewPager(viewpager)
    }

}