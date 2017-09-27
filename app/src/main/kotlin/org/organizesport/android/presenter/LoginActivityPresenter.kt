package org.organizesport.android.presenter

import android.text.TextUtils
import org.organizesport.android.LoginContract
import org.organizesport.android.interactor.LoginActivityInteractor
import org.organizesport.android.view.activities.SampleActivity

import ru.terrakok.cicerone.Router

/**
 * This class refers to the presenter attached to the {@link LoginActivity} view and related
 * entities.
 *
 * @author psor1i
 * @since 1.0
 */
class LoginActivityPresenter(private var view: LoginContract.View?,
                             private val router: Router) : LoginContract.Presenter {

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
    private var loginStatus: LoginStatus = LoginStatus.LOGIN
    private val interactor: LoginContract.Interactor by lazy { LoginActivityInteractor(this) }

    override fun buttonClicked(email: String, password: String, f: () -> Unit) {
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            interactor?.login(email, password)
        } else {
            f()
        }
    }

    override fun loginClicked(email: String, password: String) {
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            interactor?.login(email, password)
        } else {
            view?.showLoginError()
        }
    }

    override fun registerClicked(email: String, password: String) { {
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            interactor?.login(email, password)
        } else {
            view?.showLoginError()
        }
    }

    override fun accessModeStatusClicked() {
        loginStatus = loginStatus.toggle()
        when (loginStatus) {
            LoginStatus.LOGIN -> view?.showLoginUi()
            LoginStatus.REGISTER -> view?.showRegisterUi()
        }
    }

    override fun startActivityClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun viewAboutToGetClosed() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loginSuccessful() {
        view?.showLoginSuccessful()
        router.navigateTo(SampleActivity.TAG)
    }

    override fun loginError() {
        view?.showLoginError()
    }

    override fun loginFailure() {
        view?.showLoginError()
    }

    override fun onDestroy() {
        view = null
//        interactor?.unregister()
//        interactor = null
    }
}