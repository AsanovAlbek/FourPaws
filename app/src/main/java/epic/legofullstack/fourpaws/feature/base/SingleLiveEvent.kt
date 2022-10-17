package epic.legofullstack.fourpaws.feature.base

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

class SingleLiveEvent<T> : MutableLiveData<T>() {
    private val pending = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if(hasActiveObservers())
            Log.w(TAG, "Зарегистрировано несколько наблюдателей, но только один будет уведомлен об изменениях.")

        super.observe(owner, object : Observer<T> {
            override fun onChanged(t: T) {
                if(pending.compareAndSet(true, false)) {
                    observer.onChanged(t)
                }
            }
        })
    }

    @MainThread
    override fun setValue(value: T?) {
        pending.set(true)
        super.setValue(value)
    }

    @MainThread
    fun call() {
        setValue(null)
    }

    companion object {
        private const val TAG = "SingleLiveEvent-FP"
    }
}