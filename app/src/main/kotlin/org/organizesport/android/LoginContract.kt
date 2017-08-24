package org.organizesport.android

/**
 * Contract interfaces for 'View' and 'Presenter' regarding {@link LoginActivity}
 *
 * @author psor1i
 * @since 1.0
 */
interface LoginContract {
    interface View {
        fun showLoginUi()
        fun showRegisterUi()
        fun accessApplication()
    }

    interface Presenter {
        // User actions
        fun loginClicked()
        fun registerClicked()
        fun accessModeStatusClicked()
        fun startActivityClicked()
        // Model updates
        fun viewAboutToGetClosed()

        fun onDestroy()
    }

    interface Interactor {
        fun login(email: String, password: String)
        fun register(email: String, password: String)
    }
    interface
    InteractorOutput {
        fun onLoginSuccess()
        fun onLoginError()
    }


    interface Model {
    }
}