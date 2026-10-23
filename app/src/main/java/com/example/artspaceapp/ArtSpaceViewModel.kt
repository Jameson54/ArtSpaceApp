package com.example.artspaceapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class ArtSpaceUiState(
    val currentIndex: Int = 0,
    val title: String = "Artwork Title",
    val artist: String = "Artist (Year)",
    val imageResId: Int = 0
)

class ArtSpaceViewModel : ViewModel() {
    private val artworks = listOf(
        ArtSpaceUiState(0, "Mona Lisa", "Leonardo da Vinci (1503)", 0),
        ArtSpaceUiState(1, "The Starry Night", "Vincent van Gogh (1889)", 0),
        ArtSpaceUiState(2, "The Scream", "Edvard Munch (1893)", 0)
    )

    private val _uiState = MutableStateFlow(artworks[0])
    val uiState: StateFlow<ArtSpaceUiState> = _uiState.asStateFlow()

    fun nextArtwork() {
        val nextIndex = (_uiState.value.currentIndex + 1) % artworks.size
        _uiState.value = artworks[nextIndex]
    }

    fun previousArtwork() {
        val prevIndex = if (_uiState.value.currentIndex > 0) {
            _uiState.value.currentIndex - 1
        } else {
            artworks.size - 1
        }
        _uiState.value = artworks[prevIndex]
    }
}