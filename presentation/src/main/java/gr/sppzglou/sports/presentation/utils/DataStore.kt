package gr.sppzglou.sports.presentation.utils

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import okio.Path.Companion.toPath

typealias PrefsDataStore = DataStore<Preferences>

private val dataStoreInstanceMap = mutableMapOf<String, PrefsDataStore>()

fun createDataStore(producePath: () -> String): DataStore<Preferences> =
    PreferenceDataStoreFactory.createWithPath(
        produceFile = { producePath().toPath() },
    )

fun createDataStoreSingleton(producePath: () -> String): PrefsDataStore {
    val path = producePath()
    return dataStoreInstanceMap.getOrPut(path) {
        createDataStore(producePath)
    }
}

const val dataStoreFileName = "dice.preferences_pb"

fun getDataStore(context: Context): PrefsDataStore =
    createDataStoreSingleton(
        producePath = { context.filesDir.resolve(dataStoreFileName).absolutePath },
    )

@Composable
fun rememberDataStore(): PrefsDataStore {
    val context = LocalContext.current
    return remember {
        getDataStore(context)
    }
}

@Composable
fun <T, V> PrefsDataStore.collect(
    key: Preferences.Key<String>,
    default: T,
    map: (T) -> V,
): State<V> = get(key, default, map).collectAsStateWithLifecycle(map(default))

fun <T, V> PrefsDataStore.get(
    key: Preferences.Key<String>,
    default: T,
    map: (T) -> V,
): Flow<V> =
    data.map { preferences ->
        map(
            when (default) {
                is Int, is Double, is String, is Boolean, is Float, is Long, is ByteArray ->
                    preferences[key] ?: default

                is Set<*> -> {
                    if (default.all { it is String }) {
                        preferences[key] ?: default
                    } else {
                        throw UnsupportedOperationException("Default value must be a Set<String>")
                    }
                }

                else -> throw UnsupportedOperationException(
                    "Unsupported type for key '$key': $default. Please implement this operation.",
                )
            } as T, // Τελικό casting στο γενικό τύπο T
        )
    }