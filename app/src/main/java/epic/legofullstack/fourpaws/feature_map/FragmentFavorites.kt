package epic.legofullstack.fourpaws.feature_map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import epic.legofullstack.fourpaws.R
import epic.legofullstack.fourpaws.databinding.FragmentFavoritesBinding

class FragmentFavorites : Fragment(R.layout.fragment_favorites) {

    private var _binding : FragmentFavoritesBinding? = null
    private val binding get() = _binding

    override fun onCreateView (
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}