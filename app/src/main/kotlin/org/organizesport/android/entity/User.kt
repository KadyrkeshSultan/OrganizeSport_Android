package org.organizesport.android.entity

/**
 * This class refers to the 'Entity' layer (VIPER architecture) to be used across the app,
 * specifically to the 'User' model.
 *
 * @author pablol.
 * @since 1.0
 */
data class User(private val email: String?, private var sports: List<Sport>)
