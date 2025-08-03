package com.appsv.revisecontactapp.presentation.screen

import android.graphics.BitmapFactory
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.appsv.revisecontactapp.presentation.ContactState
import com.appsv.revisecontactapp.presentation.utils.CustomTextField
import com.appsv.revisecontactapp.presentation.utils.ImageCompress
import java.io.InputStream

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditScreen(navHostController: NavHostController, state: ContactState, onEvent: () -> Unit) {

    val context = LocalContext.current
    val pickMedia = rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()) { uri ->
        if(uri != null){
            val inputStream : InputStream? = uri.let{
                context.contentResolver.openInputStream(it)
            }

            val byte = inputStream?.readBytes()
            if (byte != null) {
                val compressedImage = ImageCompress(byte) // ✅ works if ImageCompress is a function or class
                if (compressedImage.size > 1024 * 1024) {
                    Toast.makeText(context, "Image size is too large", Toast.LENGTH_SHORT).show()
                } else {
                    state.image.value = compressedImage
                }
            }

        }
    }

    Scaffold (
        topBar = {
            TopAppBar(
                title = {Text(text = "Add & Edit Contact")},
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "back",
                        modifier = Modifier.clickable {
                            navHostController.popBackStack()
                        }
                    )
                }
            )
        }

    ) { innerPadding ->

        Column(
            modifier = Modifier.padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            val image = state.image.value?.let{
                BitmapFactory.decodeByteArray(it, 0, it.size)
            }?.asImageBitmap()

            Box(
                modifier = Modifier.size(150.dp),
                contentAlignment = Alignment.BottomEnd
            ){
                if(image != null){
                    Image(
                        bitmap = image,
                        contentDescription = "image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(140.dp)
                            .clip(CircleShape)
                            .background(Color.Gray)
                    )
                }else{
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "image",
                        modifier = Modifier.size(140.dp)
                            .clip(CircleShape)
                            .background(Color.Gray)
                            .padding(24.dp),
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
                IconButton(
                    onClick = {
                        pickMedia.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    },
                    modifier = Modifier.size(36.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary)
                ){

                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "add",
                        tint = Color.White,
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(
                value = state.name.value,
                onValueChange = {state.name.value = it},
                label = "Name",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                leadingIcon = Icons.Default.Person,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            CustomTextField(
                value = state.phoneNumber.value,
                onValueChange = {state.phoneNumber.value = it},
                label = "Phone Number",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                leadingIcon = Icons.Default.Call,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            CustomTextField(
                value = state.email.value,
                onValueChange = {state.email.value = it},
                label = "Email",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                leadingIcon = Icons.Default.Email,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    onEvent.invoke()
                    navHostController.navigateUp()
                },
                modifier = Modifier.fillMaxWidth()
            ){
                Text(text = "Save", fontSize = 18.sp)
            }
        }
    }
}