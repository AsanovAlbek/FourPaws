package epic.legofullstack.fourpaws.feature.map.presentation.item

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import epic.legofullstack.fourpaws.R
import epic.legofullstack.fourpaws.databinding.ItemMapBinding
import epic.legofullstack.fourpaws.feature.map.domain.model.Shelter

class ShelterMapItem(private val shelter: Shelter, private val onClick: () -> Unit) :
    BindableItem<ItemMapBinding>() {
    override fun bind(viewBinding: ItemMapBinding, position: Int) {
        viewBinding.apply {
            nameShelterTextView.text = shelter.name
            addressShelterTextView.text = shelter.address
            itemMapLayout.setOnClickListener {
                onClick()
            }
        }
    }

    override fun getLayout(): Int = R.layout.item_map

    override fun initializeViewBinding(view: View): ItemMapBinding = ItemMapBinding.bind(view)
}