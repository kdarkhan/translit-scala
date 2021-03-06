# <img src="http://sparse.tech/opensource/icons/translit.svg" width="50%">
[![Build Status](https://travis-ci.org/sparsetech/translit-scala.svg)](https://travis-ci.org/sparsetech/translit-scala)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/tech.sparse/translit-scala_2.12/badge.svg)](https://maven-badges.herokuapp.com/maven-central/tech.sparse/translit-scala_2.12)

translit-scala is a transliteration library for Scala and Scala.js. It implements transliteration rules for Slavic languages. It supports converting texts from the Latin to the Cyrillic alphabet.

## Compatibility
| Back end   | Scala versions |
|:-----------|:---------------|
| JVM        | 2.11, 2.12     |
| JavaScript | 2.11, 2.12     |

### Dependencies
```scala
libraryDependencies += "tech.sparse" %%  "translit-scala" % "0.1.0"  // JVM
libraryDependencies += "tech.sparse" %%% "translit-scala" % "0.1.0"  // JavaScript
```

## Examples
```scala
translit.Ukrainian.latinToCyrillic("Kyyiv")  // Київ
translit.Russian.latinToCyrillic("pal'ma")   // пальма
```

The transliteration to Cyrillic also restores soft signs (ь) and apostrophes ('):

```scala
translit.Ukrainian.latinToCyrillic("Mar'yana")  // Мар'яна
translit.Ukrainian.latinToCyrillic("p'yat'")    // п'ять
```

## Ukrainian
There have been several attempts to standardise transliteration rules. For example, *Український правопис* could be transliterated as one of the following:

* Ukrayins'kyy pravopys (BGN/PCGN 1965)
* Ukrains'kyi pravopys (National 1996)
* Ukrainskyi pravopys ([National 2010](http://zakon1.rada.gov.ua/laws/show/55-2010-%D0%BF))
* Ukrayins'kyj pravopys (*translit-scala*)

Furthermore, there are language-specific transliterations, e.g. in German and French, that use the spelling conventions of the respective language (*sch* in German instead of *sh* in English).

Please refer to [Romanisation of Ukrainian](http://en.wikipedia.org/wiki/Romanization_of_Ukrainian) for more details.

*National 2010* is the most recent standard which is mainly used for transliterating names and cities. A limitation is that it requires to drop soft signs and apostrophes. For better appearance, some letters can have multiple spellings depending on the position in the word. For example, я is transliterated as *ya* (prefix) or *ia* (infix). These restrictions make the conversion irreversible for many words.

Our transliteration was initially based on National 2010, but modified in the process to make the conversion reversible. Now it is most similar to BGN/PCGN. The rules are a compromise between compactness and simplicity.

### Differences from National 2010
#### и, й, я, є, ї, ю
We decompose letters in their Latin transliteration more consistently than National 2010. The letter и always gets transcribed as *y*:

* Volodymyr (Володимир)
* blyz'ko (близько)

The Latin letter *y* is also the phonetic basis of four letters in the Slavic alphabet: я, є, ї, ю. They get transliterated accordingly:

* ya → я
* ye → є
* yi → ї
* yu → ю

Unlike National 2010, we always use the same transliteration regardless of the position in the word.

The accented counterpart of и is й and is represented by a separate letter, *j*.

*Example:* Zghurs'kyj (Згурський)

#### Soft Signs and Apostrophes
The second change to National 2010 is that we try to restore soft signs and apostrophes:

* Ukrayins'kyj (Український)
* malen'kyj (маленький)

This feature is experimental and can be disabled by setting `apostrophes` to `false`.

#### Convenience mappings
Another modification was to provide the following mappings:

* c → ц
* q → щ
* w → ш
* x → х

Note that these mappings are phonetically inaccurate. However, using them still has a few advantages:

* Every letter of the Latin alphabet is covered
* When the user types *ch*, we can map the first letter to *ц*, then replace it by *ч*
* *w* has a similar shape to *ш*
* *q* and *w* are located next to each other on the English keyboard, *щ* is therefore easy to find
* *x* has the same shape as its Cyrillic counterpart

## Russian
We use rules similar to the previous rules from _Ukrainian_ transliteration.

### Mapping
| Latin | Cyrillic |
|-------|----------|
| a     | а        |
| b     | б        |
| c     | ц        |
| d     | д        |
| e     | е        |
| f     | ф        |
| g     | г        |
| h     | х        |
| i     | и        |
| j     | й        |
| k     | к        |
| l     | л        |
| m     | м        |
| n     | н        |
| o     | о        |
| p     | п        |
| q     | щ        |
| r     | р        |
| s     | с        |
| t     | т        |
| u     | у        |
| v     | в        |
| w     | ш        |
| x     | х        |
| y     | ы        |
| z     | з        |
| "     | ъ        |
| ch    | ч        |
| sh    | ш        |
| ya    | я        |
| ye    | э        |
| zh    | ж        |
| yo    | ё        |
| yu    | ю        |
| shch  | щ        |

#### Examples
| Russian | Transliterated |
|---------|----------------|
| Привет  | Privet         |
| Съел    | S"el           |
| Щётка   | Shchyotka      |
| Льдина  | L'dina         |

### Credits
The rules and examples were adapted from the following libraries:

* [translit-english-ukrainian](https://github.com/MarkovSergii/translit-english-ukrainian)
* [translit-ua](https://github.com/dchaplinsky/translit-ua)
* [translit.net](http://translit.net/)

## Licence
translit-scala is licensed under the terms of the Apache v2.0 licence.

## Contributors
* Tim Nieradzik
