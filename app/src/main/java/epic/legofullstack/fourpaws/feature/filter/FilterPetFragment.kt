package epic.legofullstack.fourpaws.feature.filter

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import epic.legofullstack.fourpaws.R
import epic.legofullstack.fourpaws.databinding.FragmentFilterPetBinding

class FilterPetFragment : Fragment(R.layout.fragment_filter_pet) {
    private val bindingFilter by viewBinding(FragmentFilterPetBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}