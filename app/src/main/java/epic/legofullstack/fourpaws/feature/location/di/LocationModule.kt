package epic.legofullstack.fourpaws.feature.location.di

import android.content.Context
import android.location.Geocoder
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import epic.legofullstack.fourpaws.feature.location.data.AreaRepositoryImpl
import epic.legofullstack.fourpaws.feature.location.domain.repository.AreaRepository
import epic.legofullstack.fourpaws.network.firebase.data.FirebaseDataSource
import java.util.Locale
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocationModule {
    @Provides
    @Singleton
    fun provideGeocoder(@ApplicationContext context: Context, locale: Locale)  = Geocoder(context, locale)

    @Provides
    @Singleton
    fun provideLocale(): Locale = Locale("ru")

    @Provides
    @Singleton
    fun provideFusedLocationClient(@ApplicationContext context: Context): FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    @Provides
    @Singleton
    fun provideCancellationTokenSource(): CancellationTokenSource = CancellationTokenSource()

    @Provides
    @Singleton
    fun provideAreaRepository(firebaseDataSource: FirebaseDataSource): AreaRepository = AreaRepositoryImpl(firebaseDataSource)
}