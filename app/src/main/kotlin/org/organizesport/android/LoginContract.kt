package org.organizesport.android

/**
 * Contract interfaces for 'View' and 'Presenter' regarding {@link LoginActivity}
 *
 * @author pablol.
 * @since 1.0
 */
interface LoginContract {
    interface View {
        fun showLoginUi()
        fun showRegisterUi()
        fun showLoading()
        fun hideLoading()
        fun showInfoMessage(msg: String)
        fun clearTextFields()
    }

    interface Presenter {
        // User actions
        fun loginClicked(email: String, password: String)
        fun registerClicked(email: String, password: String)
        fun accessModeStatusClicked()
        // Model updates
        fun onViewCreated()
        fun onDestroy()
    }

    interface Interactor {
        fun login(email: String, password: String)
        fun register(email: String, password: String)
        fun unregister()
    }

    interface InteractorOutput {
        fun onLoginSuccess()
        fun onLoginError()
        fun noNetworkAccess()
        fun onRegisterError()
        fun onRegisterSuccess()
    }
}
