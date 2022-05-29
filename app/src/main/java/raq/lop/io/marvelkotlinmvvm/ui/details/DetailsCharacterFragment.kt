package raq.lop.io.marvelkotlinmvvm.ui.details

import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import raq.lop.io.marvelkotlinmvvm.R
import raq.lop.io.marvelkotlinmvvm.data.model.character.CharacterModel
import raq.lop.io.marvelkotlinmvvm.databinding.FragmentDetailsCharacterBinding
import raq.lop.io.marvelkotlinmvvm.ui.adapters.ComicAdapter
import raq.lop.io.marvelkotlinmvvm.ui.base.BaseFragment
import raq.lop.io.marvelkotlinmvvm.ui.state.ResourceState
import raq.lop.io.marvelkotlinmvvm.util.hide
import raq.lop.io.marvelkotlinmvvm.util.limitText
import raq.lop.io.marvelkotlinmvvm.util.show
import raq.lop.io.marvelkotlinmvvm.util.toast
import timber.log.Timber

@AndroidEntryPoint
class DetailsCharacterFragment : BaseFragment<FragmentDetailsCharacterBinding, DetailsCharacterViewModel>() {
    override val viewModel: DetailsCharacterViewModel by viewModels()

    private val args: DetailsCharacterFragmentArgs by navArgs()
    private val comicAdapter by lazy { ComicAdapter() }
    private lateinit var characterModel: CharacterModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        characterModel = args.characterid
        viewModel.fetch(characterModel.id)
        setupRecyclerView()
        onLoadedCharacter(characterModel)
        collectObservers()
        biding.tvDescriptionCharacterDetails.setOnClickListener {
            onShowDialog(characterModel)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    private fun onShowDialog(characterModel: CharacterModel) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(characterModel.name)
            .setMessage(characterModel.description)
            .setNegativeButton(getString(R.string.close_dialog)){
                dialog, _ -> dialog.dismiss()
            }
            .show()
    }

    private fun collectObservers() = lifecycleScope.launch {
            viewModel.searchContainer.collect{ result ->
                when(result){
                    is ResourceState.Success -> {
                        biding.progressBarDetail.hide()
                        result.data?.let{ values ->
                            if(values.data.result.isNotEmpty()){
                                comicAdapter.comics = values.data.result.toList()
                            }else{
                                toast(getString(R.string.an_error_occurred))
                            }
                        }

                    }
                    is ResourceState.Error -> {
                        biding.progressBarDetail.hide()
                        result.message?.let{
                                message ->
                            Timber.tag("DetailCharacterFragment").e("Error -> $message")
                            toast(getString(R.string.an_error_occurred))
                        }

                    }
                    is ResourceState.Loading -> {
                        biding.progressBarDetail.show()
                    }else -> {

                }

                }
            }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_details, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.favorite -> {
                //viewModel.insert(characterModel)
                toast(getString(R.string.an_error_occurred))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onLoadedCharacter(characterModel: CharacterModel) = with(biding){
        tvNameCharacterDetails.text = characterModel.name
        if(characterModel.description.isEmpty()){
            tvDescriptionCharacterDetails.text=
                requireContext().getString(R.string.text_description_empty).limitText(80)
        }else{
            tvDescriptionCharacterDetails.text = characterModel.description
        }

        Glide.with(requireContext())
            .load(characterModel.thumbnail.path + "."+ characterModel.thumbnail.extension)
    }

    private fun setupRecyclerView() = with(biding) {
        rvComics.apply {
            adapter = comicAdapter
            layoutManager = LinearLayoutManager(context)

        }
    }

    override fun getViewBiding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailsCharacterBinding =
        FragmentDetailsCharacterBinding.inflate(inflater, container,false)
}