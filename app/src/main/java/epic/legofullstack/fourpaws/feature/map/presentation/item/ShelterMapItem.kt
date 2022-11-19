package epic.legofullstack.fourpaws.feature.map.presentation.item

import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import com.xwray.groupie.viewbinding.BindableItem
import epic.legofullstack.fourpaws.R
import epic.legofullstack.fourpaws.databinding.ItemMapBinding
import epic.legofullstack.fourpaws.feature.map.domain.model.Shelter


class ShelterMapItem(
    private val shelter: Shelter,
    private val openMap: () -> Unit,
    private val copyAddress: () -> Unit
) :
    BindableItem<ItemMapBinding>(), PopupMenu.OnMenuItemClickListener {

    override fun bind(viewBinding: ItemMapBinding, position: Int) {
        viewBinding.apply {
            nameShelterTextView.text = shelter.name
            addressShelterTextView.text = shelter.address
            textViewOptions.setOnClickListener {
                showPopupMenu(it)
            }
        }
    }

    override fun getLayout(): Int = R.layout.item_map

    override fun initializeViewBinding(view: View): ItemMapBinding = ItemMapBinding.bind(view)

    private fun showPopupMenu(view: View) {
        PopupMenu(view.context, view).apply {
            inflate(R.menu.popup_menu_map)
            setOnMenuItemClickListener(this@ShelterMapItem)
            show()
        }
    }

    override fun onMenuItemClick(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.copy_address -> {
                copyAddress.invoke()
                true
            }
            R.id.open_on_map -> {
                openMap.invoke()
                true
            }
            else -> false
        }
}