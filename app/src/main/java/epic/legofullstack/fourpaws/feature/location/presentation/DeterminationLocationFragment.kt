package epic.legofullstack.fourpaws.feature.location.presentation

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import epic.legofullstack.fourpaws.R
import epic.legofullstack.fourpaws.databinding.FragmentDeterminationLocationBinding
import epic.legofullstack.fourpaws.extensions.activityNavController
import epic.legofullstack.fourpaws.extensions.navigateSafely

class DeterminationLocationFragment : Fragment(R.layout.fragment_determination_location) {
    private lateinit var bindingDL: FragmentDeterminationLocationBinding
    private val viewModel: DeterminationLocationViewModel by viewModels()
    private lateinit var  sharedPreferences: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingDL = FragmentDeterminationLocationBinding.inflate(inflater, container, false)
        sharedPreferences = this.requireActivity().getSharedPreferences("settings_4Paws", AppCompatActivity.MODE_PRIVATE)

        bindingDL.bChooseCity.setOnClickListener {
            activityNavController().navigateSafely(R.id.action_global_mainFlowFragment)
        }

        return bindingDL.root
    }

    override fun onStart() {
        super.onStart()

//        val city = viewModel.getCity()
//        if (city != null) {
//            editSharedPreferences(USER_LAST_CITY, city)
//            println("город определен: ${city}")
//            bindingDL.tvCity.text = city
            bindingDL.bChooseCity.isEnabled = true
//        } else {
//            Toast.makeText(
//                bindingDL.root.context,
//                "Определить местоположение не удалось",
//                Toast.LENGTH_LONG
//            ).show()
//        }
    }


    private fun editSharedPreferences(nameValue: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(nameValue, value)
        editor.apply()
    }

    companion object {
        private const val TAG = "DeterminationLocationFragment"
        private const val USER_LAST_CITY = "LAST_CITY"
    }
}