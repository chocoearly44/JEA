[![](https://jitpack.io/v/chocoearly44/JEA.svg)](https://jitpack.io/#chocoearly44/JEA)

# JEA (Java eAsistent API)
JEA omogoča dostop do podatkov za vaš eAsistent račun.

## Dokumentacija
Vse metode so dokumentirane in opisane v odseku [Wiki](https://github.com/chocoearly44/JEA/wiki).

## Namestitev
JEA je trenutno gostovan na strežnikih JitPack. *Za uporabo prosim preberite naslednja navodila:*

**MAVEN**
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

**GRADLE**
1. Dodajte repozitorij v gradle datoteko
```gradle
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

2. Dodajte knjižnjico
```gradle
dependencies {
  implementation 'com.github.chocoearly44:JEA:Tag'
}
```

## Zahvale
Knjižnjica bazira na projektu eAsistent wrapper za Python, ki ga je naredil [@LevecGG](https://github.com/LevecGG).

## Podpora
Pošljite vprašanja, ideje ali napake v uradnem Discord Serverju.

[![Widget for the SuperLab Support guild](https://discord.com/api/guilds/807666401300316160/widget.png?style=banner1)](https://discord.gg/Wa24skGscR)
