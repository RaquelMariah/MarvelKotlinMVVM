package raq.lop.io.marvelkotlinmvvm.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import raq.lop.io.marvelkotlinmvvm.databinding.FragmentSearchCharacterBinding
import raq.lop.io.marvelkotlinmvvm.ui.base.BaseFragment

class SearchCharacterFragment: BaseFragment<FragmentSearchCharacterBinding, SearchCharacterViewModel>() {
    override val viewModel: SearchCharacterViewModel by viewModels()
    override fun getViewBiding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchCharacterBinding =
        FragmentSearchCharacterBinding.inflate(inflater, container, false)
}