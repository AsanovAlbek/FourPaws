package epic.legofullstack.fourpaws.feature.favorites.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import epic.legofullstack.fourpaws.R
import epic.legofullstack.fourpaws.databinding.FragmentFavoritesBinding
import epic.legofullstack.fourpaws.feature.favorites.domain.model.FavoritePet

@AndroidEntryPoint
class FragmentFavorites : Fragment(R.layout.fragment_favorites) {

    private var _binding : FragmentFavoritesBinding? = null
    private val binding get() = _binding
    private val favoritesViewModel : FavoritesViewModel by viewModels()

    override fun onCreateView (
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(layoutInflater)
        observe()
        favoritesViewModel.getFavoritePets()
        return binding!!.root
    }

    private fun observe() {
        favoritesViewModel.favoritePets.observe(viewLifecycleOwner, ::handleFavorite)
    }

    private fun handleFavorite(pets: List<FavoritePet>) {
        pets.forEach { println(it) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}