package epic.legofullstack.fourpaws.feature.base

import androidx.lifecycle.ViewModel

open class BaseViewModel: ViewModel() {
    val commands = SingleLiveEvent<ViewCommand>()

}