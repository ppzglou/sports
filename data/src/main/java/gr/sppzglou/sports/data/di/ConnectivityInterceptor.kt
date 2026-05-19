package gr.sppzglou.sports.data.di

import android.Manifest
import androidx.annotation.RequiresPermission
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ConnectivityInterceptor(private val connectivityHelper: ConnectivityHelper) : Interceptor {
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!connectivityHelper.checkInternetConnection()) {
            throw NoInternetException()
        }
        return chain.proceed(chain.request())
    }
}

open class NoInternetException : IOException()