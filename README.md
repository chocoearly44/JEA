<p align="center">
    <img align="center" src="https://github.com/chocoearly44/JEA/blob/master/assets/icon.png?raw=true" height="150px">
</p>

<h1 align="center">Java eAsistent API</h1>
<h3 align="center">JEA omogoča dostop do podatkov za vaš eAsistent račun.</h3>
<br>

<div align="center">

[![JitPack][jitpack-shield]][jitpack-url]
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]

</div>

## Documentation
Vse metode so dokumentirane in opisane v odseku [Wiki][wiki-url].

## Namestitev
### Maven
1. Dodajte repozitorij v pom.xml datoteko
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

2. Dodajte knjižnjico
```xml
<dependency>
    <groupId>com.github.chocoearly44</groupId>
    <artifactId>JEA</artifactId>
    <version>Tag</version>
</dependency>
```

### Gradle
1. Dodajte repozitorij v gradle datoteko
```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

2. Dodajte knjižnjico
```groovy
dependencies {
    implementation 'com.github.chocoearly44:JEA:Tag'
}
```

## Zahvale
Knjižnjica bazira na projektu eAsistent wrapper za Python, ki ga je naredil [LevecGG](https://github.com/LevecGG).

## Poročanje o napakah
Če med uporabo knjižnice naletite na kakršne koli napake, jih prijavite v odseku [Issues][issues-url].

## Support
Dodatno podporo lahko prejmete v uradnem Discord strežniku ali v odseku [Discussions][discussions-url] tukaj na GitHubu.

[![Widget for the TSL Support guild](https://discord.com/api/guilds/807666401300316160/widget.png?style=banner1)](https://discord.gg/Wa24skGscR)

[contributors-shield]: https://img.shields.io/github/contributors/chocoearly44/JEA.svg?style=for-the-badge
[forks-shield]: https://img.shields.io/github/forks/chocoearly44/JEA.svg?style=for-the-badge
[stars-shield]: https://img.shields.io/github/stars/chocoearly44/JEA.svg?style=for-the-badge
[issues-shield]: https://img.shields.io/github/issues/chocoearly44/JEA.svg?style=for-the-badge
[license-shield]: https://img.shields.io/github/license/chocoearly44/JEA.svg?style=for-the-badge
[jitpack-shield]: https://img.shields.io/jitpack/v/github/chocoearly44/JEA?style=for-the-badge

[contributors-url]: https://github.com/chocoearly44/JEA/graphs/contributors
[forks-url]: https://github.com/chocoearly44/JEA/network/members
[stars-url]: https://github.com/chocoearly44/JEA/stargazers
[issues-url]: https://github.com/chocoearly44/JEA/issues
[license-url]: https://github.com/chocoearly44/JEA/blob/master/LICENSE
[jitpack-url]: https://jitpack.io/#chocoearly44/JEA
[wiki-url]: https://github.com/chocoearly44/JEA/wiki
[releases-url]: https://github.com/chocoearly44/JEA/releases
[discussions-url]: https://github.com/chocoearly44/JEA/discussions