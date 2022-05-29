package raq.lop.io.marvelkotlinmvvm.ui.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import raq.lop.io.marvelkotlinmvvm.databinding.FragmentFavoriteCharacterBinding
import raq.lop.io.marvelkotlinmvvm.ui.base.BaseFragment

@AndroidEntryPoint
class FavoriteCharacterFragment: BaseFragment<FragmentFavoriteCharacterBinding,FavoriteCharacterViewModel>() {
    override val viewModel: FavoriteCharacterViewModel by viewModels()

    override fun getViewBiding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFavoriteCharacterBinding =
        FragmentFavoriteCharacterBinding.inflate(inflater, container, false)
}