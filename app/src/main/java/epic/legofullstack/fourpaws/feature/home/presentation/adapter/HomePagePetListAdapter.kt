package epic.legofullstack.fourpaws.feature.home.presentation.adapter

import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.bumptech.glide.request.RequestOptions
import epic.legofullstack.fourpaws.R
import epic.legofullstack.fourpaws.databinding.ItemPetCardBinding
import epic.legofullstack.fourpaws.feature.home.domain.model.Pet

class HomePagePetListAdapter(
    private val pets : MutableList<Pet>,
    private val itemClick: ((Int) -> Unit)
): RecyclerView.Adapter<HomePagePetListAdapter.HomePageViewHolder>() {

    inner class HomePageViewHolder(
        private val binding: ItemPetCardBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(pet : Pet) {

            binding.apply {
                city.cityName.text = pet.city
                petName.text = pet.name
                root.setOnClickListener { itemClick(pet.id) }
                setGenderIcon(pet)
                Glide.with(binding.root)
                    .load(pet.previewImg)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .transform(
                        GranularRoundedCorners(
                            CORNER_RADIUS, CORNER_RADIUS, CORNER_RADIUS, CORNER_RADIUS
                        )
                    )
                    .error(R.drawable.ic_sad)
                    .into(picture)
            }
        }

        private fun setGenderIcon(pet: Pet) {
            binding.imgGender.setImageResource(
                if (pet.gender == MALE) {
                    R.drawable.img_male
                } else {
                    R.drawable.ic_female
                }
            )
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

    companion object {
        const val MALE = "male"
        const val CORNER_RADIUS = 8f
    }
}