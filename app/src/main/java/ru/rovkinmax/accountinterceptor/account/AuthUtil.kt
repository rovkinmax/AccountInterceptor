package ru.rovkinmax.accountinterceptor.account

import android.accounts.Account
import android.accounts.AccountManager
import android.content.Context
import android.os.Bundle
import ru.rovkinmax.accountinterceptor.BuildConfig


object AuthUtil {

    private const val TOKEN_TYPE = "Base"

    fun getToken(context: Context): String? {
        val account = getUserAccount(context)
        return account?.let { AccountManager.get(context).peekAuthToken(account, TOKEN_TYPE) }
    }

    fun getUserAccount(context: Context): Account? {
        val am = AccountManager.get(context)
        val accounts = am.getAccountsByType(BuildConfig.ACCOUNT_TYPE)
        return accounts.firstOrNull()
    }

    fun peekAccount(am: AccountManager, login: String): Account? {
        val accounts = am.getAccountsByType(BuildConfig.ACCOUNT_TYPE)
        return accounts.firstOrNull { login.equals(it.name) }
    }

    fun getOrCreateAccount(context: Context, login: String): Account {
        val am = AccountManager.get(context)
        var account = peekAccount(am, login)
        if (account == null) {
            account = Account(login, BuildConfig.ACCOUNT_TYPE)
            am.addAccountExplicitly(account, "", Bundle.EMPTY)
        }
        return account
    }
}
