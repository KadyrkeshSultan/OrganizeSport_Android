package org.organizesport.android.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * This class is annotated with the Dagger {@link Module} annotation, which tells Dagger that this
 * entity will provide dependencies for a part of the application.
 *
 * @author pablol.
 * @since 1.0
 */
@Module class ModelModule(private val context: Context) {

    /**
     * Since it is annotated with the Dagger {@link Provides} annotation, this method will provide
     * a certain type of dependency; specifically, a {@link SharedPreferences} instance.
     *
     * @return the dependency to be injected in the app, at some point.
     */
    @Singleton @Provides
    fun provideContext(): Context = context
}
