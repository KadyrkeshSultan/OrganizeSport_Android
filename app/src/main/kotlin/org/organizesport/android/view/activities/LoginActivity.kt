package org.organizesport.android.view.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*

import com.jakewharton.rxbinding2.view.clicks

import io.reactivex.android.schedulers.AndroidSchedulers

import kotlinx.android.synthetic.main.activity_login.*

import org.jetbrains.anko.toast
import org.organizesport.android.BaseApplication
import org.organizesport.android.LoginContract
import org.organizesport.android.R
import org.organizesport.android.presenter.LoginActivityPresenter
import ru.terrakok.cicerone.Navigator

import ru.terrakok.cicerone.commands.*

/**
 * This class refers to the 'LoginActivity' View, following the MVP architectural pattern.
 *
 * The 'LoginContract.View' interface provides associated methods for communication with the
 * Presenter only
 *
 * @author pablol.
 * @since 1.0
 */
class LoginActivity : AppCompatActivity(), LoginContract.View {

    companion object {
        val TAG = "LoginActivity"
    }

    // Reference to an interface, so that polymorphism is used later
    private var presenter: LoginContract.Presenter? = null
    private val navigator: Navigator? by lazy {
        object : Navigator {
            override fun applyCommand(command: Command) {
                if (command is Forward) {
                    forward(command)
                }
            }

            private fun forward(command: Forward) {
                when (command.screenKey) {
                    SportsListActivity.TAG -> startActivity(Intent(this@LoginActivity, SportsListActivity::class.java))
                    else -> Log.e("Cicerone", "Unknown screen: " + command.screenKey)
                }
            }
        }
    }
    private val tvTitle: TextView? by lazy { tv_title_activity_login }
    private val etEmail: EditText? by lazy { et_email_activity_login }
    private val etPassword: EditText? by lazy { et_password_activity_login }
    private val btnLogin: Button? by lazy { btn_login_activity_login }
    private val btnRegister: Button? by lazy { btn_register_activity_login }
    private val tbAccessModeStatus: ToggleButton? by lazy { tb_access_mode_status_activity_login }
    private val progressBar: ProgressBar? by lazy { pb_loading_activity_login }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter = LoginActivityPresenter(this)

        btnLogin?.addClickAction({
            presenter?.loginClicked(etEmail?.text.toString(), etPassword?.text.toString())
        })
        tbAccessModeStatus?.addClickAction({
            presenter?.accessModeStatusClicked()
        })
        btnRegister?.addClickAction({
            presenter?.registerClicked(etEmail?.text.toString(), etPassword?.text.toString())
        })
    }

    override fun onResume() {
        super.onResume()
        BaseApplication.INSTANCE.cicerone.navigatorHolder.setNavigator(this.navigator)
        presenter?.onViewCreated()
    }

    override fun onPause() {
        super.onPause()
        BaseApplication.INSTANCE.cicerone.navigatorHolder.removeNavigator()
    }

    /**
     *
     */
    private fun Button.addClickAction(f: () -> Unit): Unit {
        this.clicks()
                .observeOn(AndroidSchedulers.mainThread())   // subscribers post on the UI thread
                .subscribe { f() }
    }

    override fun showLoginUi() {
        Log.d(TAG, "In-showLoginUi()")
        tvTitle?.text = this.getString(R.string.tv_login_text)
        btnRegister?.visibility = View.INVISIBLE
        btnLogin?.visibility = View.VISIBLE
    }

    override fun showRegisterUi() {
        Log.d(TAG, "In-showRegisterUI()")
        tvTitle?.text = this.getString(R.string.tv_register_text)
        btnRegister?.visibility = View.VISIBLE
        btnLogin?.visibility = View.INVISIBLE
    }

    override fun showLoading() {
        progressBar?.visibility = View.VISIBLE
        etEmail?.isEnabled = false
        etPassword?.isEnabled = false
        btnLogin?.isEnabled = false
        tbAccessModeStatus?.isEnabled = false
        btnRegister?.isEnabled = false
    }

    override fun hideLoading() {
        progressBar?.visibility = View.GONE
        etEmail?.isEnabled = true
        etPassword?.isEnabled = true
        btnLogin?.isEnabled = true
        tbAccessModeStatus?.isEnabled = true
        btnRegister?.isEnabled = true
    }

    override fun showInfoMessage(msg: String) {
        toast(msg)
    }

    override fun clearTextFields() {
//        etEmail?.setText("")
        etPassword?.setText("")
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        presenter = null
        super.onDestroy()
    }
}
