package org.organizesport.android.entity

/**
 * This class refers to the 'Entity' layer (VIPER architecture) to be used across the app.
 *
 * @author pablol.
 * @since 1.0
 */
data class User(val email: String?, var sports: List<String>)