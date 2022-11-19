package epic.legofullstack.fourpaws.feature.detail.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import epic.legofullstack.fourpaws.R
import epic.legofullstack.fourpaws.databinding.SliderItemBinding
import jp.wasabeef.glide.transformations.BlurTransformation

class ImagesSlider(
    private val images: List<String>
): RecyclerView.Adapter<ImagesSlider.ImagesHolder>() {

    inner class ImagesHolder(
        private val itemBinding: SliderItemBinding
    ): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(position: Int, imageUrl: String, itemsCount: Int) {
            itemBinding.apply {
                Glide.with(this.root)
                    .load(imageUrl)
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .into(petImage)
                imagesCounter.text = "${position + 1}/$itemsCount"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesHolder =
        ImagesHolder(
            SliderItemBinding.bind(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.slider_item, parent, false
                )
            )
        )

    override fun onBindViewHolder(holder: ImagesHolder, position: Int) {
        holder.bind(position, images[position], images.size)
    }

    override fun getItemCount(): Int = images.size

}