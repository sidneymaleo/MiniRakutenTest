package com.rakutentest.android.ui.UIEvent

sealed class UIEvent {
    data class ShowMessage(val message: String): UIEvent()
}
