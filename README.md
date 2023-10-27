# Practical Work 1 @DCO-LEI-FCUL

## Overview

The objective of this work is to exercise the use of techniques and
of the OO programming principles taught in the course. The work focuses
in an application that is a digital music manager and player.

This version contains a graphical interface (GUIClient) that supports some of the
functionalities covered, namely viewing the contents of the library,
existing playlists, and play a song from the library or a
playlist. Furthermore, there is a client program (SimpleClient) that
exercises some of the application's functionalities.

### Taken decisions

- We extend AbsQListWithSelection in the MusicLibrary, ArrayQListWithSelection class
and PlaylistList to reduce repeated and redundant code

- We have a SongMaker class to create songs and thus make the code more modular

- We decided that we would want the SmartPlaylists sorted.

- We made equals in the Song, AbsQListWithSelection, MusicLibrary and Playlist classes

- We decided that our Rate would be a listed one, therefore not needing to implement an equals,
  with a rank of 0 to 5.

- We decided to implement almost all methods in abstract classes by overriding the methods
and functions that could possibly change from class to class.

- In MusicLibrary we have a Song that is the Song selected in MusicLibrary.

- In AbsPlaylist we have a Song that is selected from the playlist.

- In the PlaylistList class we extend AbsQListWithSelection, thus having a selected playlist.

### Work done by

- João Pereira @fc58189
- Daniel Nunes @fc58257

The final grade of this project was 16/20.
