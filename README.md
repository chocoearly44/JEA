[![](https://jitpack.io/v/JurijTSL/JEA.svg)](https://jitpack.io/#JurijTSL/JEA)

# JEA (Java eAsistent API)
JEA omogoča dostop do podatkov za vaš eAsistent račun.

## Dokumentacija
Vse metode so dokumentirane in opisane v odseku [Wiki](https://github.com/JurijTSL/JEA/wiki).

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
  <groupId>com.github.JurijTSL</groupId>
  <artifactId>JEA</artifactId>
  <version>TAG</version>
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
  implementation 'com.github.JurijTSL:JEA:TAG'
}
```

## Zahvale
Knjižnjica bazira na raziskavi [@LevecGG](https://github.com/LevecGG), ki je naredil [eAsistent wrapper za Python](https://github.com/LevecGG/eAsistentAPI).

## Podpora
Pošljite vprašanja ideje ali napake v uradnem Discord Serverju.

[![Widget for the TSL Support guild](https://discord.com/api/guilds/807666401300316160/widget.png?style=banner1)](https://discord.gg/Wa24skGscR)
