package raq.lop.io.marvelkotlinmvvm.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import raq.lop.io.marvelkotlinmvvm.R
import raq.lop.io.marvelkotlinmvvm.databinding.FragmentListCharacterBinding
import raq.lop.io.marvelkotlinmvvm.ui.adapters.CharacterAdapter
import raq.lop.io.marvelkotlinmvvm.ui.base.BaseFragment
import raq.lop.io.marvelkotlinmvvm.ui.state.ResourceState
import raq.lop.io.marvelkotlinmvvm.util.hide
import raq.lop.io.marvelkotlinmvvm.util.show
import raq.lop.io.marvelkotlinmvvm.util.toast
import timber.log.Timber

@AndroidEntryPoint
class ListCharacterFragment : BaseFragment<FragmentListCharacterBinding, ListCharacterViewModel>() {
    override val viewModel: ListCharacterViewModel by viewModels()
    private val characterAdapter by lazy {
        CharacterAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        clickAdapter()
        colectObservsers()

    }

    private fun colectObservsers() = lifecycleScope.launch {
        viewModel.list.collect { resource ->
            when(resource){
                is ResourceState.Success -> {
                    resource.data?.let {
                        values ->
                        biding.progressCircular.hide()
                        characterAdapter.characters = values.data.results.toList()
                    }
                }
                is ResourceState.Error -> {
                    biding.progressCircular.hide()
                    resource.message?.let { message ->
                        toast(getString((R.string.on_error)))
                        Timber.tag("ListCharacterFragment").e("Error")
                    }
                }
                is ResourceState.Loading -> {
                    biding.progressCircular.show()
                }
                else -> {

                }
            }

        }
    }

    private fun setupRecyclerView() = with(biding){
        rvCharacters.apply {
            adapter = characterAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

    }

    private fun clickAdapter(){
        characterAdapter.setOnClickListener {
            characterModel ->
            val action = ListCharacterFragmentDirections
                .actionListCharacterFragmentToDetailsCharacterFragment(characterModel)
                findNavController().navigate(action)
        }
    }

    override fun getViewBiding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentListCharacterBinding = FragmentListCharacterBinding.inflate(inflater, container, false)


}