package raq.lop.io.marvelkotlinmvvm.ui.search

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import raq.lop.io.marvelkotlinmvvm.R
import raq.lop.io.marvelkotlinmvvm.databinding.FragmentSearchCharacterBinding
import raq.lop.io.marvelkotlinmvvm.ui.adapters.CharacterAdapter
import raq.lop.io.marvelkotlinmvvm.ui.base.BaseFragment
import raq.lop.io.marvelkotlinmvvm.ui.state.ResourceState
import raq.lop.io.marvelkotlinmvvm.util.Consts.DEFAULT_QUERY
import raq.lop.io.marvelkotlinmvvm.util.Consts.LAST_SEARCH_QUERY
import raq.lop.io.marvelkotlinmvvm.util.hide
import raq.lop.io.marvelkotlinmvvm.util.show
import raq.lop.io.marvelkotlinmvvm.util.toast
import timber.log.Timber

@AndroidEntryPoint
class SearchCharacterFragment: BaseFragment<FragmentSearchCharacterBinding, SearchCharacterViewModel>() {
    override val viewModel: SearchCharacterViewModel by viewModels()
    private val characterAdaper by lazy { CharacterAdapter()}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        clickAdapter()

        val query = savedInstanceState?.getString(LAST_SEARCH_QUERY) ?: DEFAULT_QUERY
        searchInit(query)

        collectObservers()
    }

    private fun collectObservers() = lifecycleScope.launch {
        viewModel.searchContainer.collect{ result ->
            when(result){
                is ResourceState.Success -> {
                    biding.progressbarSearch.hide()
                    result.data?.let{
                        characterAdaper.characters = it.data.results.toList()
                    }
                }
                is ResourceState.Error -> {
                    biding.progressbarSearch.hide()
                    result.message?.let{
                        message ->
                        Timber.tag("SearchCharacterFragment").e("Error -> $message")
                        toast(getString(R.string.an_error_occurred))
                    }

                }
                is ResourceState.Loading -> {
                    biding.progressbarSearch.show()
                }else -> {

                }

        }
        }
    }

    private fun searchInit(query: String) = with(biding){
        edSearchCharacter.setText(query)
        edSearchCharacter.setOnEditorActionListener{_,actionId,_ ->
            if (actionId == EditorInfo.IME_ACTION_GO){
                updateCharacterList()
                true
            }else{
                false
            }
        }

        edSearchCharacter.setOnKeyListener{_,keyCode,event->
            if(event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER){
                updateCharacterList()
                true
            }else{
                false
            }
        }
    }

    private fun updateCharacterList() = with(biding){
        edSearchCharacter.editableText.trim().let {
            if(it.isNotEmpty()){
                searchQuery(it.toString())
            }
        }

    }

    private fun searchQuery(query: String) {
        viewModel.fetch(query)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(LAST_SEARCH_QUERY, biding.edSearchCharacter.editableText.trim().toString())
    }

    private fun clickAdapter() {
        characterAdaper.setOnClickListener { characterModel ->
            val action = SearchCharacterFragmentDirections
                .actionSearchCharacterFragmentToDetailsCharacterFragment(characterModel)
            findNavController().navigate(action)
        }

    }

    private fun setupRecyclerView() = with(biding) {
        rvSearchCharacter.apply{
            adapter = characterAdaper
            layoutManager = LinearLayoutManager(context)
        }

    }

    override fun getViewBiding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchCharacterBinding =
        FragmentSearchCharacterBinding.inflate(inflater, container, false)
}