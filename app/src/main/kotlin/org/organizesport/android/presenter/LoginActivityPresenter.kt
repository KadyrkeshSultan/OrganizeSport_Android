package org.organizesport.android.presenter

import org.organizesport.android.LoginContract

/**
 * This class refers to the presenter attached to the {@link LoginActivity} view and related
 * entities.
 *
 * @author psor1i
 * @since 1.0
 */
class LoginActivityPresenter(var view: LoginContract.View?) : LoginContract.Presenter {

    private val TAG: String = LoginActivityPresenter::class.java.simpleName

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

    override fun registerClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun accessModeStatusClicked() {
//        loginStatus.toggle()
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

    override fun loginClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDestroy() {
        view = null
//        interactor?.unregister()
//        interactor = null
    }
}