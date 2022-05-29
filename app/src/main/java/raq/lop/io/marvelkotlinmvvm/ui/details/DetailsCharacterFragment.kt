package raq.lop.io.marvelkotlinmvvm.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import raq.lop.io.marvelkotlinmvvm.databinding.FragmentDetailsCharacterBinding
import raq.lop.io.marvelkotlinmvvm.ui.base.BaseFragment

class DetailsCharacterFragment : BaseFragment<FragmentDetailsCharacterBinding, DetailsCharacterViewModel>() {
    override val viewModel: DetailsCharacterViewModel by viewModels()
    override fun getViewBiding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailsCharacterBinding =
        FragmentDetailsCharacterBinding.inflate(inflater, container,false)
}