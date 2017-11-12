package org.organizesport.android

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.spy
import org.mockito.Mockito.verify
import org.organizesport.android.presenter.LoginActivityPresenter

/**
 * This class relates to the unit tests of the 'LoginActivityPresenter' Presenter
 * (unit tests).
 *
 * @author psor1i
 * @since 1.0
 */
class LoginActivityPresenterUnitTest {

    @Before
    fun setUp() {
        // This is needed for Dagger 2 injections only on tests
        BaseApplication.INSTANCE = BaseApplication()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun whenAnyTextfieldIsEmptyLoginReportsAnError() {
        //given
        val view = Mockito.mock(LoginContract.View::class.java)
        val presenter = LoginActivityPresenter(view)

        val target = spy(presenter)
        // when
        target.loginClicked("", "password")
        // then
        verify(view).showInfoMessage("Text fields cannot be empty")
    }

    @Test
    fun whenBothTextfieldsAreNotEmptySportsListActivityIsShown() {
        //given
        val view = Mockito.mock(LoginContract.View::class.java)
        val presenter = LoginActivityPresenter(view)

        val target = spy(presenter)

        // TODO: need to add 'PowerMock' to mock static methods
        //when
//        target.loginClicked("email", "password")
        //then
//        verify(view, never()).showInfoMessage("Text fields cannot be empty")
    }
}
