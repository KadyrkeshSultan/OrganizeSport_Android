package org.organizesport.android

import org.organizesport.android.presenter.LoginActivityPresenter

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
        fun showLoading()
        fun hideLoading()
        fun accessApplication()
        fun showInfoMessage(msg: String)
    }

    interface Presenter {
        // User actions
        fun loginClicked(email: String, password: String)
        fun registerClicked(email: String, password: String)
        fun accessModeStatusClicked()
        fun startActivityClicked()
        // Model updates
        fun viewAboutToGetClosed()
        fun loginSuccessful()
        fun loginError()
        fun noNetworkAccess()
        fun onDestroy()
    }

    interface Interactor {
        fun login(email: String, password: String)
        fun register(email: String, password: String)
    }

    interface InteractorOutput {
        fun onLoginSuccess()
        fun onLoginError()
    }

    interface Model {
    }
}