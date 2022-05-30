package raq.lop.io.marvelkotlinmvvm.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import raq.lop.io.marvelkotlinmvvm.R
import raq.lop.io.marvelkotlinmvvm.databinding.FragmentFavoriteCharacterBinding
import raq.lop.io.marvelkotlinmvvm.ui.adapters.CharacterAdapter
import raq.lop.io.marvelkotlinmvvm.ui.base.BaseFragment
import raq.lop.io.marvelkotlinmvvm.ui.state.ResourceState
import raq.lop.io.marvelkotlinmvvm.util.hide
import raq.lop.io.marvelkotlinmvvm.util.show
import raq.lop.io.marvelkotlinmvvm.util.toast

@AndroidEntryPoint
class FavoriteCharacterFragment: BaseFragment<FragmentFavoriteCharacterBinding,FavoriteCharacterViewModel>() {
    override val viewModel: FavoriteCharacterViewModel by viewModels()
    private val characterAdapter by lazy{CharacterAdapter()}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        clickAdapters()
        observers()
    }

    private fun observers() = lifecycleScope.launch{
        viewModel.favorite.collect{ resource ->
            when(resource){
                is ResourceState.Success -> {
                    resource.data?.let {
                        biding.tvEmptyList.hide()
                        characterAdapter.characters = it.toList()
                    }
                }else -> {

                }

            }
        }
    }

    private fun clickAdapters() {
        characterAdapter.setOnClickListener {
            characterModel ->
            val action = FavoriteCharacterFragmentDirections
                .actionFavoriteCharacterFragmentToDetailsCharacterFragment(characterModel)
            findNavController().navigate(action)
        }
    }

    private fun setupRecyclerView()  = with(biding){
        rvFavoriteCharacter.apply {
            adapter = characterAdapter
            layoutManager = LinearLayoutManager(context)
        }
        ItemTouchHelper(itemTouchHelperCallback())
            .attachToRecyclerView(rvFavoriteCharacter)
    }

    private fun itemTouchHelperCallback(): ItemTouchHelper.SimpleCallback{
        return object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val character = characterAdapter.getCharacterPosition(viewHolder.adapterPosition)
                viewModel.delete(character).also{
                    toast(getString(R.string.message_delete_character))
                }
            }
        }

    }

    override fun getViewBiding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFavoriteCharacterBinding =
        FragmentFavoriteCharacterBinding.inflate(inflater, container, false)
}