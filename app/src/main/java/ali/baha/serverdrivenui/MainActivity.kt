package ali.baha.serverdrivenui

import ali.baha.domain.model.*
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ali.baha.serverdrivenui.utils.ViewState
import ali.baha.serverdrivenui.viewmodel.ServerDrivenUiViewModel
import android.content.Context
import android.widget.Toast
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.google.gson.JsonObject
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: ServerDrivenUiViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            lifecycleScope.launch {
                viewModel
                    .getServerDrivenUiData()
                    .catch { setContent { showError() } }
                    .collect {
                        when (it) {
                            is ViewState.Error -> setContent { showError() }
                            is ViewState.Loading -> setContent { showLoading() }
                            is ViewState.Success -> {
                                setContent {
                                    setView(
                                        data = it.data?.data!!,
                                        applicationContext = applicationContext,
                                        viewModel = viewModel,
                                        lifecycleScope = lifecycleScope
                                    )
                                }
                            }
                        }
                    }
            }
        }
    }
}

@Composable
private fun setView(
    data: ArrayList<Data>,
    applicationContext: Context,
    viewModel: ServerDrivenUiViewModel,
    lifecycleScope: LifecycleCoroutineScope
) {
    data.forEach { value ->
        checkUiType(value, applicationContext, viewModel, lifecycleScope)
    }
}

@Composable
private fun checkUiType(
    value: Data,
    applicationContext: Context,
    viewModel: ServerDrivenUiViewModel,
    lifecycleScope: LifecycleCoroutineScope
) {
    when (value.type) {
        Type.SCAFFOLD -> showScaffold(value = value, applicationContext, viewModel, lifecycleScope)
        Type.TEXT -> showText(value = value, applicationContext, viewModel, lifecycleScope)
        Type.APP_BAR -> showAppBar(value = value, applicationContext, viewModel, lifecycleScope)
        Type.IMAGE -> ImageView(value = value, applicationContext, viewModel, lifecycleScope)
        Type.VERTICAL_LIST -> verticalList(
            value = value,
            applicationContext,
            viewModel,
            lifecycleScope
        )
        Type.HORIZONTAL_LIST -> horizontalList(
            value = value,
            applicationContext,
            viewModel,
            lifecycleScope
        )
        Type.ROW -> rowView(value = value, applicationContext, viewModel, lifecycleScope)
        Type.COLUMN -> columnView(value = value, applicationContext, viewModel, lifecycleScope)
        Type.EDIT_TEXT -> EditText(value = value, applicationContext, viewModel, lifecycleScope)
        Type.UNKNOWN -> Spacer(modifier = Modifier.requiredSize(0.dp))
    }
}

@Composable
private fun showError() {
    Text(text = "There is a Problem")
}

@Composable
private fun showLoading() {
    Text(text = "w8 to load")
}

@Composable
private fun showText(
    value: Data, applicationContext: Context,
    viewModel: ServerDrivenUiViewModel,
    lifecycleScope: LifecycleCoroutineScope
) {
    Text(
        text = "${value.value}",
    )
}

@Composable
private fun showAppBar(
    value: Data, applicationContext: Context,
    viewModel: ServerDrivenUiViewModel,
    lifecycleScope: LifecycleCoroutineScope
) {
    TopAppBar(
        title = {
            setView(
                data = value.children,
                applicationContext = applicationContext,
                viewModel,
                lifecycleScope
            )
        },
        backgroundColor = Color.White,
    )
}

@Composable
private fun showScaffold(
    value: Data, applicationContext: Context,
    viewModel: ServerDrivenUiViewModel,
    lifecycleScope: LifecycleCoroutineScope
) {
    Scaffold(
        topBar = {
            setView(value.topBar, applicationContext, viewModel, lifecycleScope)
        }
    ) {
        setView(
            data = value.children,
            applicationContext = applicationContext,
            viewModel,
            lifecycleScope
        )
    }
}

@Composable
private fun verticalList(
    value: Data, applicationContext: Context,
    viewModel: ServerDrivenUiViewModel,
    lifecycleScope: LifecycleCoroutineScope
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            setView(
                data = value.children,
                applicationContext = applicationContext,
                viewModel,
                lifecycleScope
            )
        }
    }
}

@Composable
private fun horizontalList(
    value: Data, applicationContext: Context,
    viewModel: ServerDrivenUiViewModel,
    lifecycleScope: LifecycleCoroutineScope
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            setView(
                data = value.children,
                applicationContext = applicationContext,
                viewModel,
                lifecycleScope
            )
        }
    }
}

@Composable
private fun rowView(
    value: Data, applicationContext: Context,
    viewModel: ServerDrivenUiViewModel,
    lifecycleScope: LifecycleCoroutineScope
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        setView(
            data = value.children,
            applicationContext = applicationContext,
            viewModel,
            lifecycleScope
        )
    }
}

@Composable
private fun columnView(
    value: Data, applicationContext: Context,
    viewModel: ServerDrivenUiViewModel,
    lifecycleScope: LifecycleCoroutineScope
) {
    Column {
        setView(
            data = value.children,
            applicationContext = applicationContext,
            viewModel,
            lifecycleScope
        )
    }
}

@Composable
private fun ImageView(
    value: Data, applicationContext: Context,
    viewModel: ServerDrivenUiViewModel,
    lifecycleScope: LifecycleCoroutineScope
) {
    Image(
        painter = rememberImagePainter(
            data = value.value,
            builder = {
                transformations(CircleCropTransformation())
            }
        ),
        contentDescription = null,
        modifier = Modifier
            .size(56.dp)
            .clickable {
                if (value.action != null) {
                    lifecycleScope.launch {
                        val onject = JsonObject()
                        value.action?.bodies?.forEach {
                            val res = viewModel.getKeyValueInDataBase(it)
                            onject.addProperty(res.keyData, res.valueData)
                        }
                        sendReqForClick(
                            value,
                            onject,
                            applicationContext,
                            viewModel,
                            lifecycleScope
                        )
                    }
                }
            }
    )
}

fun sendReqForClick(
    value: Data,
    onject: JsonObject,
    applicationContext: Context,
    viewModel: ServerDrivenUiViewModel,
    lifecycleScope: LifecycleCoroutineScope
) {
    lifecycleScope.launch {
        viewModel.getClickData(value?.action?.url!! , onject)
            .catch {
                Toast.makeText(applicationContext, "" + it.message, Toast.LENGTH_SHORT).show()
            }
            .collect {
                when(it){
                    is ViewState.Error ->  Toast.makeText(applicationContext, "" + it.exception, Toast.LENGTH_SHORT).show()
                    is ViewState.Loading ->  Toast.makeText(applicationContext, "LOADING" , Toast.LENGTH_SHORT).show()
                    is ViewState.Success ->  Toast.makeText(applicationContext, "" + it.data, Toast.LENGTH_SHORT).show()
                }
            }
    }
}

@Composable
private fun EditText(
    value: Data,
    applicationContext: Context,
    viewModel: ServerDrivenUiViewModel,
    lifecycleScope: LifecycleCoroutineScope
) {
    var textFieldState by remember {
        mutableStateOf("")
    }

    TextField(
        value = textFieldState,
        onValueChange = {
            textFieldState = it
            lifecycleScope.launch {
                viewModel.insertKeyValueInDataBase(value.input, it)
            }
        }
    )
}
