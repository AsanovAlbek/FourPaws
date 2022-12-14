package epic.legofullstack.fourpaws.feature.favorites.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import epic.legofullstack.fourpaws.R
import epic.legofullstack.fourpaws.databinding.ItemPetCardBinding
import epic.legofullstack.fourpaws.feature.favorites.domain.model.FavoritePet
import epic.legofullstack.fourpaws.feature.home.presentation.adapter.HomePagePetListAdapter

class FavoritePetsAdapter(
    private val favoritePets : MutableList<FavoritePet>,
    private val itemClick: ((Int) -> Unit)
): RecyclerView.Adapter<FavoritePetsAdapter.FavoriteViewHolder>() {
    inner class FavoriteViewHolder(
        private val bindItem: ItemPetCardBinding
        ): RecyclerView.ViewHolder(bindItem.root) {

        fun bind(favorite: FavoritePet) {
            bindItem.apply {
                city.text = favorite.city
                petName.text = favorite.name
                root.setOnClickListener { itemClick(favorite.id) }
                setGenderIcon(favorite)
                Glide.with(bindItem.root)
                    .load(favorite.previewImg)
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

        private fun setGenderIcon(pet: FavoritePet) {
            bindItem.maleChip.apply {
                if (pet.gender == MALE) {
                    setText(R.string.boy)
                    setChipIconResource(R.drawable.ic_male)
                    setChipBackgroundColorResource(R.color.blue)
                } else {
                    setText(R.string.girl)
                    setChipIconResource(R.drawable.ic_female)
                    setChipBackgroundColorResource(R.color.pink)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder =
        FavoriteViewHolder(
            ItemPetCardBinding.bind(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_pet_card, parent, false
                )
            )
        )

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(favoritePets[position])
    }

    override fun getItemCount(): Int = favoritePets.size

    fun refreshFavorites(newPets : List<FavoritePet>) {
        favoritePets.clear()
        favoritePets.addAll(newPets)
    }

    companion object {
        const val CORNER_RADIUS = 8f
        const val MALE = "male"
    }
}