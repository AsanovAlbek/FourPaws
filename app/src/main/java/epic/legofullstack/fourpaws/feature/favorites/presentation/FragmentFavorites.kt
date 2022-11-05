package epic.legofullstack.fourpaws.feature.favorites.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import epic.legofullstack.fourpaws.R
import epic.legofullstack.fourpaws.databinding.FragmentFavoritesBinding
import epic.legofullstack.fourpaws.feature.base.BaseFragment
import epic.legofullstack.fourpaws.feature.favorites.presentation.model.UiState

@AndroidEntryPoint
class FragmentFavorites : BaseFragment(R.layout.fragment_favorites) {

    private val favoritesBinding by viewBinding(FragmentFavoritesBinding::bind)
    private val favoritesViewModel : FavoritesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observe()
        favoritesViewModel.getFavoritePets()
    }

    private fun observe() {
        favoritesViewModel.state.observe(viewLifecycleOwner, ::handleState)
    }

    private fun handleState(uiState: UiState) {
        // todo обработка состояния
    }
}