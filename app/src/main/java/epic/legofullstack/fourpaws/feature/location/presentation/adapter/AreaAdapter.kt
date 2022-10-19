package epic.legofullstack.fourpaws.feature.location.presentation.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.TextView
import androidx.annotation.LayoutRes
import epic.legofullstack.fourpaws.R
import epic.legofullstack.fourpaws.core.domain.model.Area

class AreaAdapter(
    context: Context,
    @LayoutRes private val layoutResourceId: Int,
    private val areas: List<Area>
) : ArrayAdapter<Area>(context, layoutResourceId, areas) {
    var currentAreas: MutableList<Area> = ArrayList(areas)

    override fun getCount(): Int {
        return currentAreas.size
    }

    override fun getItem(position: Int): Area {
        return currentAreas[position]
    }

    override fun getItemId(position: Int): Long {
        return currentAreas[position].id.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val currentView = convertView ?: LayoutInflater.from(context).inflate(layoutResourceId, parent, false)

        val area = getItem(position)
        val textView = currentView.findViewById<View>(R.id.autoCompleteTextViewItem) as TextView
        textView.text = area.title

        return currentView
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun convertResultToString(resultValue: Any?): CharSequence {
                return (resultValue as Area).title
            }

            override fun performFiltering(value: CharSequence?): FilterResults {
                var query = value?.toString()?.lowercase()
                var filteredAreas: MutableList<Area> = mutableListOf()


                if (query == null || query.isEmpty()) {
                    filteredAreas.addAll(areas)
                } else {
                    filteredAreas =
                        areas.filter { it.title.lowercase().contains(query) }.toMutableList()
                }

                val filterRes = FilterResults()
                filterRes.count = filteredAreas.size
                filterRes.values = filteredAreas

                return filterRes
            }

            override fun publishResults(value: CharSequence?, result: FilterResults?) {
                currentAreas.clear()
                currentAreas.addAll(result?.values as Collection<Area>)
                notifyDataSetChanged()
            }
        }
    }
}