package epic.legofullstack.fourpaws.feature.home.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import epic.legofullstack.fourpaws.R
import epic.legofullstack.fourpaws.databinding.ItemPetCardBinding
import epic.legofullstack.fourpaws.feature.home.domain.model.Pet

class HomePagePetListAdapter(
    private val pets : MutableList<Pet>,
    private val itemClickEvent: ((Int) -> Unit)
): RecyclerView.Adapter<HomePagePetListAdapter.HomePageViewHolder>() {

    inner class HomePageViewHolder(
        private val binding: ItemPetCardBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(pet : Pet) {
            binding.apply {
                city.cityName.text = pet.city
                petName.text = pet.name
                petCard.setOnClickListener { itemClickEvent.invoke(pet.id) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomePageViewHolder =
        HomePageViewHolder(
            ItemPetCardBinding.bind(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_pet_card, parent, false)
            )
        )

    override fun onBindViewHolder(holder: HomePageViewHolder, position: Int) {
        holder.bind(pets[position])
    }

    override fun getItemCount() = pets.size

    fun refreshPets(newPets : List<Pet>) {
        pets.clear()
        pets.addAll(newPets)
    }
}