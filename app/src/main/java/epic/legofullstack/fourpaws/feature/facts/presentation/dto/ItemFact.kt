package epic.legofullstack.fourpaws.feature.facts.presentation.dto

import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.xwray.groupie.viewbinding.BindableItem
import epic.legofullstack.fourpaws.R
import epic.legofullstack.fourpaws.databinding.ItemFactBinding
import epic.legofullstack.fourpaws.feature.facts.domain.model.FactPreview

class ItemFact(private val fact: FactPreview, private val onClick: () -> Unit) : BindableItem<ItemFactBinding>() {
    override fun bind(viewBinding: ItemFactBinding, position: Int) {
        viewBinding.apply {
            titleTextView.text = fact.title
            itemFactCard.setOnClickListener { onClick() }

            Glide.with(viewBinding.root)
                .load(fact.imgUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .placeholder(R.drawable.ic_pets_paws_fill)
                .fitCenter()
                .error(R.drawable.ic_sad)
                .into(factImg)
        }
    }

    override fun getLayout(): Int = R.layout.item_fact

    override fun initializeViewBinding(view: View): ItemFactBinding = ItemFactBinding.bind(view)
}