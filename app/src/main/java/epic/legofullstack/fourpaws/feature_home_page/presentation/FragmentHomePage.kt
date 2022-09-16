package epic.legofullstack.fourpaws.feature_home_page.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import epic.legofullstack.fourpaws.R
import epic.legofullstack.fourpaws.databinding.FragmentHomePageBinding
import epic.legofullstack.fourpaws.feature_home_page.data.repository.PetsRepositoryImpl
import epic.legofullstack.fourpaws.feature_home_page.data.storage.LocalDataSource
import epic.legofullstack.fourpaws.feature_home_page.domain.usecase.GetAllPetsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.withContext

class FragmentHomePage : Fragment(R.layout.fragment_home_page) {

    private var _binding : FragmentHomePageBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomePageBinding.inflate(layoutInflater)

        // Проверка на получение данныХ, потом уберу
        val getAllPetsUseCase = GetAllPetsUseCase(PetsRepositoryImpl(LocalDataSource()))
        lifecycleScope.launchWhenCreated {
            withContext(Dispatchers.IO) {
                val petsList = getAllPetsUseCase.invoke().toList().joinToString()
                println(petsList)
            }
        }

        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}