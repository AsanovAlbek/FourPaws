package epic.legofullstack.fourpaws.feature.favorites.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import epic.legofullstack.fourpaws.R
import epic.legofullstack.fourpaws.databinding.FragmentFavoritesBinding
import epic.legofullstack.fourpaws.extensions.fragmentNavController
import epic.legofullstack.fourpaws.feature.base.BaseFragment
import epic.legofullstack.fourpaws.feature.favorites.domain.model.FavoritePet
import epic.legofullstack.fourpaws.feature.favorites.presentation.adapter.FavoritePetsAdapter
import epic.legofullstack.fourpaws.feature.favorites.presentation.state.FavoriteState

@AndroidEntryPoint
class FragmentFavorites : BaseFragment(R.layout.fragment_favorites) {

    private val favoritesBinding by viewBinding(FragmentFavoritesBinding::bind)
    private val favoritesViewModel : FavoritesViewModel by viewModels()
    private lateinit var favoritesAdapter: FavoritePetsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observe()
        favoritesViewModel.getFavoritePets()
    }

    private fun observe() {
        favoritesViewModel.state.observe(viewLifecycleOwner, ::handleFavorite)
        favoritesViewModel.commands.observe(viewLifecycleOwner, ::handleCommand)
    }

    private fun handleFavorite(favoriteState: FavoriteState) {
        refresh(favoriteState)
        when(favoriteState) {
            is FavoriteState.Content -> favoriteState.handleContent()
            is FavoriteState.Error -> favoriteState.handleError()
            is FavoriteState.Loading -> {}
        }
    }

    private fun FavoriteState.Content.handleContent() {
        setAdapter(pets = pets)
        favoritesAdapter.refreshFavorites(pets)
    }

    private fun FavoriteState.Error.handleError() {
        favoritesBinding.favoriteErrorPanel.apply {
            errorIcon.setImageResource(error.icon)
            errorText.setText(error.title)
        }
    }

    private fun refresh(favoriteState: FavoriteState) {
        favoritesBinding.apply {
            favoritePetsList.isVisible = favoriteState is FavoriteState.Content
            favoriteErrorPanel.root.isVisible = favoriteState is FavoriteState.Error
            favoriteLoading.isVisible = favoriteState is FavoriteState.Loading
        }
    }

    private fun setAdapter(pets: List<FavoritePet>) {
        favoritesAdapter = FavoritePetsAdapter(
            favoritePets = pets.toMutableList(), itemClick = ::openDetails)

        favoritesBinding.favoritePetsList.apply {
            adapter = favoritesAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun openDetails(favoritePetId: Int) {
        favoritesViewModel.clickToFavorite(favoriteId = favoritePetId)
    }
}