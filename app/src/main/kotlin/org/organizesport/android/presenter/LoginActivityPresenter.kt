package org.organizesport.android.presenter

import android.text.TextUtils
import org.organizesport.android.BaseApplication
import org.organizesport.android.LoginContract
import org.organizesport.android.interactor.LoginActivityInteractor
import org.organizesport.android.view.activities.SampleActivity
import org.organizesport.android.view.activities.SportsListActivity

import ru.terrakok.cicerone.Router

/**
 * This class refers to the presenter attached to the {@link LoginActivity} view and related
 * entities.
 *
 * @author pablol.
 * @since 1.0
 */
class LoginActivityPresenter(private var view: LoginContract.View?) : LoginContract.Presenter, LoginContract.InteractorOutput {

    companion object {
        private val TAG: String = "LoginActivityPresenter"
    }

    private enum class LoginStatus {
        LOGIN, REGISTER;

        fun toggle(): LoginStatus {
            return (when (this.name == "LOGIN") {
                true -> REGISTER
                false -> LOGIN
            })
        }
    }
    private var userEmail: String? = null
    private var loginStatus: LoginStatus = LoginStatus.LOGIN
    private var interactor: LoginContract.Interactor? = LoginActivityInteractor(this)
    private val router: Router? by lazy { BaseApplication.INSTANCE.cicerone.router }

    override fun loginClicked(email: String, password: String) {
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            view?.showLoading()
            interactor?.login(email, password)
        } else {
            view?.showInfoMessage("Text fields cannot be empty")
        }
    }

    override fun registerClicked(email: String, password: String) {
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            view?.showLoading()
            userEmail = email
            interactor?.register(email, password)
        } else {
            view?.showInfoMessage("Register error")
        }
    }

    override fun accessModeStatusClicked() {
        loginStatus = loginStatus.toggle()
        when (loginStatus) {
            LoginStatus.LOGIN -> view?.showLoginUi()
            LoginStatus.REGISTER -> view?.showRegisterUi()
        }
    }

    override fun onRegisterError() {
        view?.hideLoading()
        view?.showInfoMessage("Register error")
        view?.clearTextFields()
    }

    override fun onRegisterSuccess() {
        interactor?.createUserIfNotExisting(userEmail)
        userEmail = null
        view?.hideLoading()
        view?.showInfoMessage("Register successful")
        router?.navigateTo(SportsListActivity.TAG)
    }

    override fun noNetworkAccess() {
        view?.hideLoading()
        view?.showInfoMessage("No network access")
    }

    override fun onLoginSuccess() {
        view?.hideLoading()
        router?.navigateTo(SportsListActivity.TAG)
    }

    override fun onLoginError() {
        view?.hideLoading()
        view?.showInfoMessage("Login error")
        view?.clearTextFields()
    }

    override fun onViewCreated() {
        view?.showLoginUi()
        view?.clearTextFields()
    }

    override fun onDestroy() {
        view = null
        interactor?.unregister()
        interactor = null
    }
}
