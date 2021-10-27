package ali.baha.serverdrivenui.utils

sealed class ViewState<out R> {
    data class Success<out T>(val data: T) : ViewState<T>()
    data class Error(val exception: String) : ViewState<Nothing>()
    object Loading : ViewState<Nothing>()
}