package translit

import translit.Helpers._

object Russian extends Language {
  val uniGrams = Map(
    'a' -> 'а',
    'b' -> 'б',
    'c' -> 'ц',
    'd' -> 'д',
    'e' -> 'е',
    'f' -> 'ф',
    'g' -> 'г',
    'h' -> 'х',
    'i' -> 'и',
    'j' -> 'й',
    'k' -> 'к',
    'l' -> 'л',
    'm' -> 'м',
    'n' -> 'н',
    'o' -> 'о',
    'p' -> 'п',
    'q' -> 'щ',
    'r' -> 'р',
    's' -> 'с',
    't' -> 'т',
    'u' -> 'у',
    'v' -> 'в',
    'w' -> 'ш',
    'x' -> 'х',
    'y' -> 'ы',
    'z' -> 'з',
    '"' -> 'ъ'
  )

  val biGrams = Map(
    "ch" -> 'ч',
    "sh" -> 'ш',
    "ya" -> 'я',
    "ye" -> 'э',
    "zh" -> 'ж',
    "yo" -> 'ё',
    "yu" -> 'ю'
  )

  val fourGrams = Map(
    "shch" -> 'щ'
  )

  /**
    * Converts one character starting from `offset`
    *
    * @return (-2, c)  Replace last two characters by `c`
    *         (-1, c)  Replace last character by `c`
    *         ( 0, c)  Append character `c`
    */
  def latinToCyrillicOfs(text: String,
                         offset: Int,
                         apostrophes: Boolean = true): (Int, Char) = {
    val ofs = offset + 1
    if (ofs >= 4 &&
        fourGrams.contains(text.substring(ofs - 4, ofs).toLowerCase)) {
      val chars = text.substring(ofs - 4, ofs)
      val cyrillic = fourGrams(chars.toLowerCase)
      (-2, restoreCaseFirst(chars, cyrillic))
    } else if (ofs >= 2 &&
               biGrams.contains(text.substring(ofs - 2, ofs).toLowerCase)) {
      val chars = text.substring(ofs - 2, ofs)
      val cyrillic = biGrams(chars.toLowerCase)
      (-1, restoreCaseFirst(chars, cyrillic))
    } else if (uniGrams.contains(text(ofs - 1).toLower)) {
      val cyrillic = uniGrams(text(ofs - 1).toLower)
      (0, if (text(ofs - 1).isUpper) cyrillic.toUpper else cyrillic)
    } else if (text(ofs - 1) == '\'' && apostrophes) {
      if (text(ofs - 2).isUpper) (0, 'Ь') else (0, 'ь')
    } else {
      (0, text(ofs - 1))
    }
  }
}
