package epic.legofullstack.fourpaws.feature.favorites.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import epic.legofullstack.fourpaws.R
import epic.legofullstack.fourpaws.databinding.ItemPetCardBinding
import epic.legofullstack.fourpaws.feature.favorites.domain.model.FavoritePet

class FavoritePetsAdapter(
    private val favoritePets : MutableList<FavoritePet>,
    private val itemClick: ((Int) -> Unit)
): RecyclerView.Adapter<FavoritePetsAdapter.FavoriteViewHolder>() {
    inner class FavoriteViewHolder(
        private val bindItem: ItemPetCardBinding
        ): RecyclerView.ViewHolder(bindItem.root) {

        fun bind(favorite: FavoritePet) {
            bindItem.apply {
                city.cityName.text = favorite.city
                petName.text = favorite.name
                root.setOnClickListener { itemClick.invoke(favorite.id) }
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
}