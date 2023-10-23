# Trabalho Prático 1 @DCO-LEI-FCUL

## Overview

Este é um projeto Eclipse que contém o código e artefactos fornecidos para
o Trabalho Prático 1, organizado de acordo com a solução de desenho fornecida.

O objetivo deste trabalho é que os alunos exercitem a utilização das técnicas e
dos princípios de programação OO lecionados na disciplina. O trabalho centra-se
numa aplicação que é um gestor e reprodutor de músicas em suporte digital.

Esta versão contém uma interface gráfica (GUIClient) que suporta algumas das
funcionalidades cobertas, nomeadamente ver o conteúdo da biblioteca, das
playlists existentes, e tocar uma música a partir da biblioteca ou de uma
playlist. Para além disso existe um programa cliente (SimpleClient) que
exercita algumas funcionalidades da aplicação.

### Decisões tomadas

- Extendemos a AbsQListWithSelection na class MusicLibrary, ArrayQListWithSelection
e PlaylistList para reduzir codigo repetido e redundante

- Temos uma classe SongMaker para criar musicas e assim deixar o codigo mais modular

- Decidimos que iriamos querer as SmartPlaylists sorted.

- Fizemos equals nas classes Song, AbsQListWithSelection, MusicLibrary e Playlist

- Decidimos que o nosso Rate seria um enumerado nao precisando entao de se implementar um equals,
 com um rank de 0 a 5.

- Decidimos implementar quase todos os metodos nas classes abstratas dando override nos metodos
e funcoes que possivelmente poderiam mudar de classe para classe.

- Na MusicLibrary temos uma Song que eh a Song selecionada na MusicLibrary.

- Na AbsPlaylist temos uma Song que eh a selecionada da playlist.

- Na classe PlaylistList extendemos AbsQListWithSelection tendo assim uma playlist selecionada.

### Trabalho realizado por

- João Pereira @fc58189
- Daniel Nunes @fc58257
